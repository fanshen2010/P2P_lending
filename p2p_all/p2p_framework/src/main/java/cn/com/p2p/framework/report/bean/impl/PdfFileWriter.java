package cn.com.p2p.framework.report.bean.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;
import cn.com.p2p.framework.report.ExcelTable.HtmlCellEnum;
import cn.com.p2p.framework.report.ExcleTablWorkBook;
import cn.com.p2p.framework.report.bean.Writer;
import cn.com.p2p.framework.report.tool.ParserRule;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;


public class PdfFileWriter implements Writer{

	private static float BORDER_SIZE =0.5f;
	
	private Map<Integer,Font> mapFont = new HashMap<Integer,Font>();

	public void writer(OutputStream out,
			ExcleTablWorkBook workBook, Map<String, Object> dataSource) {
		try {
			Document pdfDoc = createPdfDocument(PageSize.A4, 10, 10, 20, 10,out);
			pdfDoc.open();
			//换页
			for(int i=0;i<workBook.getSheetTable().size();i++){
				Map<String,?> foramt = workBook.getSheetTable().get(i).getHtmlData();
//System.out.println("PDF Create Tabel = "+foramt.get("sheetName"));				
				PdfPTable table = createTable(foramt,dataSource);
				pdfDoc.add(table);
				//换页
				pdfDoc.newPage();
			}
			pdfDoc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 创建PDF文档
	 * @param outputfile
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	private Document createPdfDocument(String outputfile) throws FileNotFoundException, DocumentException{
    	// step 1
        Document document = new Document(PageSize.A4, 2, 2, 20, 8);
		PdfWriter.getInstance(document, new FileOutputStream(outputfile));
        return document;
	}
	/**
	 * 创建PDF文档
	 * @param outputfile
	 * @return
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	private Document createPdfDocument(Rectangle range,int x,int y,int top,int bottom,OutputStream out) throws FileNotFoundException, DocumentException{
    	// step 1
        Document document = new Document(PageSize.A4, x, y, top, bottom);
		PdfWriter.getInstance(document, out);
        return document;
	}
	/**
	 * 获取PDF文档的中文字体
	 * @param fontSize 文字大小
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private Font getChineseFont(int fontSize) throws DocumentException, IOException{
	  // 定义中文字体
	  BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	  Font fontCN = new Font(bfChinese, fontSize,Font.NORMAL);
	  return fontCN;
	}
	
	/***
	 * 输出PDFTabel表格
	 * @return
	 * @throws DocumentException 
	 * @throws IOException 
	 */
	private PdfPTable createTable(Map<String,?> foramt,Map<String, Object> dataSource) throws DocumentException, IOException{
		
		String cellCount = (String)foramt.get("cell_count");
		String cellsWidth = (String)foramt.get("celw_arry");
		int cell_count = Integer.parseInt(cellCount);
		List<List<Map<String,Object>>> tabl_list =(List<List<Map<String, Object>>>) foramt.get("tdata");
		//=========================================================================
		cellsWidth = cellsWidth.replaceAll("^\\[(.+)\\]$", "$1");
		String[] cells_w = cellsWidth.split(",");
		//设置表格每一列的宽度
		int[] cell_width = new int[cells_w.length];
		for(int i = 0;i < cells_w.length;i++){
			cell_width[i]= Integer.parseInt(cells_w[i].trim());
		}
		//========================================================================
		//设置表格宽度
		PdfPTable table = new PdfPTable(cell_count);
		table.setWidths(cell_width);
	    table.setTotalWidth(PageSize.A4.getWidth()*0.85f);
	    table.setLockedWidth(true);
	    //--------------------------------------------------------------
	    //infinite table loop : row content is larger than page
	    table.setSplitLate(false); 
	    table.setSplitRows(true);
	    //行
	    Font fontCN = null;
	    for(List<Map<String,Object>> row:tabl_list){
	    	for(Map<String,Object> cell:row){
	    		String value = (String) cell.get(HtmlCellEnum.h_data_field.getName());
	    		//合并列数
	    		String colspan = (String) cell.get(HtmlCellEnum.h_colspan.getName());
	    		colspan =colspan==null ||colspan.trim().length()==0?"-1":colspan;
	    		//合并的行数
	    		String rowspan = (String) cell.get(HtmlCellEnum.h_rowspan.getName());
	    		rowspan =rowspan==null ||rowspan.trim().length()==0?"-1":rowspan;
	    		//列的高度
	    		String height = (String) cell.get(HtmlCellEnum.h_height.getName());
	    		height =height==null ||height.trim().length()==0?"-1":height;
	    		int iColspan = Integer.parseInt(colspan.trim());
	    		int iRowspan = Integer.parseInt(rowspan.trim());
	    		int cHeight = Integer.parseInt(height.trim());
	    		
	    		//============================================================
	    		//字体大小
	            String font_size = (String) cell.get(HtmlCellEnum.font_size.getName());
	            font_size =font_size==null ||font_size.trim().length()==0?"7":font_size;
	            int iFont_size = Integer.parseInt(font_size.trim());
	            iFont_size = iFont_size<=0?7:iFont_size;
	            fontCN = getFont(iFont_size);
	            //**************************************************************************
	            PdfPCell pef_cell = null;
	            //是否是图片字段
	    		if(isImage(value)){
	    			Image img = createImage(value,dataSource);
	    			if(img==null){
			            Paragraph  phrase = new Paragraph("",fontCN);
			            phrase.setIndentationLeft(50f);
			            phrase.setSpacingBefore(15f);
			            pef_cell= new PdfPCell(phrase);	
	    			}else{
	    				pef_cell = new PdfPCell(img);
	    			}
	    		}else{ //文字列
		            String value_str = fillData(value,dataSource);
		            //填充数据
		            Paragraph  phrase = new Paragraph(value_str,fontCN);
		            phrase.setIndentationLeft(50f);
		            phrase.setSpacingBefore(15f);
		            pef_cell= new PdfPCell(phrase);		
	    		}
	    		//****************************************************************************
	            pef_cell.setBorderWidth(BORDER_SIZE);//边框宽度
	            pef_cell.setPaddingBottom(2f);//文字下边距
	            pef_cell.setPaddingLeft(2f);//文字左边距
	            pef_cell.setPaddingRight(0f);
	            pef_cell.setPaddingTop(0f);
	            if(iColspan>0){
	            	pef_cell.setColspan(iColspan);
	            }
	            if(iRowspan>0){
	            	pef_cell.setRowspan(iRowspan);
	            }
	            if(cHeight>0){
	            	pef_cell.setFixedHeight(cHeight);
	            }
	            //------------------------------------------------------------------
	            //单元格边框样式
	            String bottomStyle = (String) cell.get(HtmlCellEnum.bottom_style.getName());
	            bottomStyle =bottomStyle==null ||bottomStyle.trim().length()==0?"-1":bottomStyle;
	            
	            String topStyle = (String) cell.get(HtmlCellEnum.top_style.getName());
	            topStyle =topStyle==null ||topStyle.trim().length()==0?"-1":topStyle;
	            
	            String leftStyle = (String) cell.get(HtmlCellEnum.left_style.getName());
	            leftStyle =leftStyle==null ||leftStyle.trim().length()==0?"-1":leftStyle;
	            
	            String rightStyle = (String) cell.get(HtmlCellEnum.right_style.getName());
	            rightStyle =rightStyle==null ||rightStyle.trim().length()==0?"-1":rightStyle;
	            
	            int ibottomStyle = Integer.parseInt(bottomStyle.trim());
	            if(ibottomStyle <= 0){
	            	pef_cell.disableBorderSide(2);
	            }
	            int itopStyle = Integer.parseInt(topStyle.trim());
	            if(itopStyle<=0){
	            	pef_cell.disableBorderSide(1);
	            }
	            int ileftStyle = Integer.parseInt(leftStyle.trim());
	            if(ileftStyle<=0){
	            	pef_cell.disableBorderSide(4);
	            }
	            int irightStyle = Integer.parseInt(rightStyle.trim());
	            if(irightStyle<=0){
	            	pef_cell.disableBorderSide(8);
	            }
	            //---------------------------------------------------------------------------
	            //对齐及字号
	            String align = (String) cell.get(HtmlCellEnum.align_style.getName());
	            align =align==null ||align.trim().length()==0?"-1":align;
	            // 水平对齐
	            int iAlign = Integer.parseInt(align.trim());
	            if(iAlign==2){//ALIGN_CENTER
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	            }else if(iAlign==1){//ALIGN_LEFT
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
	            }else if(iAlign==3){//ALIGN_RIGHT
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
	            }
	            
	            //垂直对齐
	            String valign = (String) cell.get(HtmlCellEnum.valign_style.getName());
	            valign =valign==null ||valign.trim().length()==0?"1":valign;//默认在表格的中间
	            int iValign = Integer.parseInt(valign.trim());
	            if(iValign==1){//VERTICAL_CENTER
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	            }else if(iValign==2){//VERTICAL_BOTTOM
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
	            }else if(iValign==3){//VERTICAL_TOP
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
	            }else{
	            	pef_cell.setUseAscender(true); 
	            	pef_cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);
	            }
	            
	            //--------------------------------------------------------------------------
//System.out.printf("PDF=%s,bottom=%s,left=%s,right=%s,top=%s\r\n",value,ibottomStyle,ileftStyle
//						,irightStyle,itopStyle); 	            
	            //------------------------------------------------------------------
	            table.addCell(pef_cell);
	    	}
	    }
	    //=======================================================================
		return table;
	}
	/***
	 * 填充数据
	 * @param field
	 * @param dataSource
	 * @return
	 */
	private String fillData(String fieldName,Map<String, Object> dataSource){
		String data = " ";
		if(StringUtils.isEmpty(fieldName)){
			return data;
		}else if(!ParserRule.isVarField(fieldName)){ //文字直接输出
			data = fieldName.replaceAll("[Tt]-", "");
		}else if(dataSource!=null){
			String[] field_arry = fieldName.split(";");
			for(String field:field_arry){
				if(StringUtils.isEmpty(field)||field.startsWith("#h")){
					continue;//不输出隐藏域的值
				}
				String field_name = ParserRule.getFieldName(field);
				//===========================================================
				//取值方式修正，使用Ognl表达式取值
				//String value = dataSource.get(field_name);
				if(!StringUtils.isEmpty(field_name)){
					Object objValue=null;
					try {
						objValue = Ognl.getValue(field_name, dataSource);
					} catch (OgnlException e) {
	System.out.println("#################\t\t["+field_name+"]");					
					}
					//=============================================================
					String value = getValue(objValue);
					if(value !=null){
						data += value;
					}					
				}
			}
		}
		return data;
	}
	
	public Font getFont(int font_size) throws DocumentException, IOException{
		if(this.mapFont.containsKey(font_size)){
			return this.mapFont.get(font_size);
		}else{
			Font fontCN = this.getChineseFont(font_size);
			this.mapFont.put(font_size, fontCN);
			return fontCN;
		}
	}
	
	/***
	 * 将Object类型的值转换成String类型
	 * @param obj
	 * @return
	 */
	private String getValue(Object obj){
		String value = "";
		if(obj instanceof Date){
			if(obj != null){
				value = DateUtils.formatDate((Date)obj, "yyyy-MM-dd");
			}
		}else{
			if(obj != null){
				value = String.valueOf(obj);
			}
		}
		return value;
	}
	
	/**
	 * 创建一个Image对象
	 * @param img_url
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public Image createImage(String img_info,Map<String, Object> dataSource) throws DocumentException, IOException{
		String IMAGE_PATH = getImagePath();
		if(img_info.startsWith("&p")){
			String field_name = ParserRule.getFieldName(img_info);
			Map<String,Object> params = ParserRule.getFiledParams(img_info);
			String data_val = dataSource==null||dataSource.get(field_name)==null?null:String.valueOf(dataSource.get(field_name));
			String image_src = data_val;
			if(image_src==null ||image_src.trim().length()==0){
				Object obj = params.get("src");
				image_src = obj==null?null:String.valueOf(obj);
			}
			if(image_src == null||image_src.trim().length()==0) return null;
			return Image.getInstance(IMAGE_PATH+image_src);
		}
		return null;
	}
	
	/**
	 * 验证是否是图片字段
	 * @param fieldValue
	 * @return
	 */
	public boolean isImage(String fieldValue){
		return fieldValue==null||fieldValue.trim().length()==0?false:fieldValue.startsWith("&p");
	}
    /**
     * 为PDF文件生成水印图片
     * @param inputFile  源文件路径
     * @param outputFile 目标文件路径
     * @param waterMarkName  水印文字内容
     * @param imageFilePath  水印图片的路径
     */
	public static void waterMark(String inputFile, OutputStream outputStream, String waterMarkName,String imageFilePath ) {  
        try {  
            PdfReader reader = new PdfReader(inputFile);  
            PdfStamper stamper = new PdfStamper(reader, outputStream);  
            //
      	  	// 定义中文字体
	      	BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
	      	Font fontCN = new Font(bfChinese, 24,Font.NORMAL); 
            //new BaseFont();
            int total = reader.getNumberOfPages() + 1;  
            Image image = Image.getInstance(imageFilePath); 
            image.setAbsolutePosition(160, 230);
            //image.setAlignment(Element.ALIGN_CENTER);
            PdfContentByte under;  
//            int j = waterMarkName.length();  
            char c = 0;  
//            int rise = 0;  
            for (int i = 1; i < total; i++) {  
//                rise = 500;  
                under = stamper.getUnderContent(i); 
                PdfGState gs = new PdfGState();  
                gs.setFillOpacity(0.2f);//设置透明度为0.2  
                under.setGState(gs);  
                // 添加图片  
                under.addImage(image);  
                under.beginText(); 
//                //设置水印文字颜色
//                under.setColorFill(BaseColor.GRAY);  
//                under.setFontAndSize(bfChinese, 30);  
//                // 设置水印文字字体倾斜 开始  
//                if (j >= 15) {  
//                    under.setTextMatrix(200, 120);  
//                    for (int k = 0; k < j; k++) {  
//                        under.setTextRise(rise);  
//                        c = waterMarkName.charAt(k);  
//                        under.showText(c + "");  
//                        rise -= 20;  
//                    }  
//                } else {  
//                    under.setTextMatrix(180, 100);  
//                    for (int k = 0; k < j; k++) {  
//                        under.setTextRise(rise);  
//                        c = waterMarkName.charAt(k);  
//                        under.showText(c + "");  
//                        rise -= 18;  
//                    }  
//                }
//                // 字体设置结束  
                under.endText();  
            }  
            stamper.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
	
	public String getImagePath(){
		String imgPath = Struts2Utils.getSession().getServletContext().getRealPath("/");
		return imgPath;
	}
  
}
