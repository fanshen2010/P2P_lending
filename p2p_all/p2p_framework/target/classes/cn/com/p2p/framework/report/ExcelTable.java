package cn.com.p2p.framework.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import cn.com.p2p.framework.report.tool.NumberUtils;
import cn.com.p2p.framework.report.tool.ParserRule;
import cn.com.p2p.framework.report.tool.PinyinUtils;

/***
 * 将Excel中的数据转换成表格数据，以便输出Html和report报表
 * @author 张旭
 *
 */
public class ExcelTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -884781673704569107L;
	public final static int X_SKIP = 10;//x轴偏移量
	public final static int Y_SKIP = 10;//y轴偏移量
	public final static float SF_SCALE = 0.9f;//Excel列的缩放比例默认：90%;
	//表格数据数组
	private int row_count = 0;
	private int col_count = 0;
	
	//每一列的具体宽度
	private int[] colWidth = null;
	private int twidth = 0;//表格宽度
	private int theight = 0 ;//表格高度
	//合并的数据区域
	private List<ExRegion> regList = new ArrayList<ExRegion>();
	//单元格的数据信息(key:0_0,0_1)
	private List<Map<DataEnum,String>> valListMap = new ArrayList<Map<DataEnum,String>>();
	private Map<String,Map<DataEnum,String>> tmap = new ConcurrentHashMap<String, Map<DataEnum,String>>();
	//==============================================
	private String sheetName = null;
	private String actionName = null;
	//==============================================
	//获取的数据库字段及Bean属性区域
	private List<String> field = null;
	//==============================================
	/***
	 * 默认构造函数
	 */
	public ExcelTable(){
	}
	/**
	 * 构造函数
	 * @param rowCount
	 * @param colCount
	 */
	public ExcelTable(int rowCount,int colCount){
		row_count = rowCount;
		col_count = colCount;
		colWidth = new int[colCount];
	}
	
	/***
	 * Excel Sheet页初始化表格数据（一个Sheet页面对应一个表格）
	 * @param sheet
	 */
	public void initTableInfo(Sheet sheet){
		//初始化所有数据列对象
		initCellObject(sheet);
System.out.printf("表格的大小为width=%s,hight=%s\r\n",twidth,theight);		
		//初始化表格对象
		initRegions(sheet);
		//打印读取数据的结果
		//print();
		
	}
	
	/**
	 * 从XML生成的ExcelTable对象，需要调用此方法初始化部分数据。
	 */
	public void initForXML(){
		colWidth = new int[col_count];
		//actionName
		this.actionName =  PinyinUtils.getFirstSpell(this.sheetName);
		//=========================================================
		//初始化表格的宽度和高度
		this.initPageSize();
		//=========================================================
	}
	
	/***
	 * 根据Sheet对象初始化表格中的数据列
	 * @param sheet
	 */
	private void initCellObject(Sheet sheet){
		int b_row = sheet.getFirstRowNum();
		int l_row = sheet.getLastRowNum();
		//=======================================
		//sheetName 
		this.sheetName = sheet.getSheetName();
		//actionName
		this.actionName =  PinyinUtils.getFirstSpell(this.sheetName);
		//=======================================
		row_count = l_row;
		col_count = sheet.getRow(0).getLastCellNum();
		colWidth = new int[col_count];
System.out.println("Sheet Name = "+sheetName+",row_count="+row_count+",col_count="+col_count);		
		for(int i = b_row; i <= l_row; i++){
			Row row = sheet.getRow(i);
			int b_col = row.getFirstCellNum();
			int l_col = row.getLastCellNum();
			for(int j = 0; j< l_col; j++){
				
                Cell fCell = j>=b_col? row.getCell(j):null;
                String key = String.format("%s_%s", i,j);
                //==============================================================单元格数据Map
                Map<DataEnum,String> map = new ConcurrentHashMap<DataEnum, String>();
                //----------------------------------------------------------------------------------
                //获得单元格样式
                short bottomSytle=fCell==null?0:fCell.getCellStyle().getBorderBottom();
                map.put(DataEnum.bottom_style,String.valueOf(bottomSytle));
                short leftSytle=fCell==null?0:fCell.getCellStyle().getBorderLeft();
                map.put(DataEnum.left_style,String.valueOf(leftSytle));
                short rightSytle=fCell==null?0:fCell.getCellStyle().getBorderRight();
                map.put(DataEnum.right_style,String.valueOf(rightSytle));
                short topSytle=fCell==null?0:fCell.getCellStyle().getBorderTop();
                map.put(DataEnum.top_style,String.valueOf(topSytle));
                //对齐方式
                short align=fCell==null?0:fCell.getCellStyle().getAlignment();//ALIGN_CENTER:2,ALIGN_LEFT:1,ALIGN_RIGHT:3
                map.put(DataEnum.align_style,String.valueOf(align));
                short valign=fCell==null?-1:fCell.getCellStyle().getVerticalAlignment();//VERTICAL_CENTER:1,VERTICAL_BOTTOM:2,VERTICAL_TOP:0
                map.put(DataEnum.valign_style,String.valueOf(valign));
                short font_index=fCell==null?-1:fCell.getCellStyle().getFontIndex();
                short font_size=font_index==-1?0:sheet.getWorkbook().getFontAt(font_index).getFontHeightInPoints();
                map.put(DataEnum.font_size,String.valueOf(font_size));
//System.out.printf("cel=%s,bottom=%s,left=%s,right=%s,top=%s,align=%s,valign=%s,font_size=%s\r\n",
//					fCell==null?"null":fCell.toString(),bottomSytle,leftSytle,rightSytle,topSytle,align,valign,font_size);                
                //----------------------------------------------------------------------------------
                
                String cvalue = this.getCellValue(fCell);
                //index,value,width,height,x,y,style;
                // index
                map.put(DataEnum.index, key);
                //值(value)
                map.put(DataEnum.value, cvalue);
                //宽度（width）
                int width = sheet.getColumnWidth(j)/32;
                map.put(DataEnum.width, String.valueOf(width));
                //高度(height)
                int height = NumberUtils.getPxFromPt(sheet.getRow(i).getHeightInPoints());
                map.put(DataEnum.height, String.valueOf(height));
                //X
                if(j <=0 ){
                	map.put(DataEnum.x, String.valueOf(X_SKIP));
                }else{
                	//左侧单元格的X+左侧单元格的宽度
                	int kx = j-1;
                	String p_key = i+"_"+kx;
                	int px = Integer.parseInt(tmap.get(p_key).get(DataEnum.x));
                	int p_width = Integer.parseInt(tmap.get(p_key).get(DataEnum.width));
                	px = px + p_width;
                	map.put(DataEnum.x, String.valueOf(px));
                }
                //Y
                if(i <= 0 ){
                	map.put(DataEnum.y, String.valueOf(Y_SKIP));
                }else{
                	String p_key=null;
                	try{
                	//上边单元格的x+ 上边单元格的高度
                	int ky = i-1;
                	p_key = ky+"_"+j;
                	String val_key_y = tmap.get(p_key).get(DataEnum.y);
                	val_key_y = val_key_y==null?"0":val_key_y;
                	int py = Integer.parseInt(val_key_y);
                	String val_key_h = tmap.get(p_key).get(DataEnum.height);
                	val_key_h = val_key_h==null?"0":val_key_h;
                	int p_hight = Integer.parseInt(val_key_h);
                	py = py + p_hight;
                	map.put(DataEnum.y, String.valueOf(py));
                	}catch(Exception xe){
                		xe.printStackTrace();
                		System.err.printf("SheetName = %s,key=%s\r\n",sheetName,p_key);
                	}                	
                }
                //style
                //========================================================================
                valListMap.add(map);
//System.out.println("key="+key);
                tmap.put(key, map);
			}
		}

		//=========================================================
		//初始化表格的宽度和高度
		this.initPageSize();
		//=========================================================
	}
	/**
	 * 初始化Sheet页中的Region区域
	 * @param sheet
	 */
	private void initRegions(Sheet sheet){
		int sheetMergeCount = sheet.getNumMergedRegions(); 
		for(int i = 0 ; i < sheetMergeCount ; i++){ 
			CellRangeAddress ca = sheet.getMergedRegion(i); 
			ExRegion region = new ExRegion(sheet,ca,i);
			regList.add(region);
		}
	}
	
	public void print(){
		for(Map<DataEnum,String> item:valListMap){
			ExRegion region = this.getRegion(item.get(DataEnum.index));
			System.out.printf("%s=%s(%s),width=%s,height=%s,X=%s,Y=%s\r\n",item.get(DataEnum.index),
					item.get(DataEnum.value),region==null?"false":"true colspan="+region.getColspan()+" rowspan="+region.getRowspan(),
							item.get(DataEnum.width),item.get(DataEnum.height),item.get(DataEnum.x),item.get(DataEnum.y));
		}

	}
	
	/***
	 * 初始化表格的宽度和高度
	 */
	private void initPageSize(){
		//=========================================================
		//获得page页的宽度和高度
		//高度
		for(int i = 0 ;i< this.row_count;i++){
			String key = String.format("%s_%s", i,0);
			//System.out.printf("key=%s,height=%s\r\n",key,"NaN");	
			int ih =Integer.parseInt(tmap.get(key).get(DataEnum.height));
				
			this.theight += ih;
		}
		//宽度
		for(int j = 0 ;j< this.col_count;j++){
			String key = String.format("%s_%s", 0,j);
			int ih =Integer.parseInt(tmap.get(key).get(DataEnum.width));
			//System.out.printf("key=%s,width=%s\r\n",key,ih);
			this.twidth += ih;
			colWidth[j]=(int) ((int)ih*SF_SCALE);
		}
		//=========================================================
	}
	
	/***
	 * 将表格转换成FTL（HTML）格式输出
	 * @return
	 */
	public Map<String,?> getHtmlData(){
		Map<Integer,ExRegion> out_region_map = new ConcurrentHashMap<Integer, ExRegion>();
		Map<String,Object> result_map = new ConcurrentHashMap<String,Object>();
		//===========================================================================
		result_map.put("actionName", this.actionName);
		result_map.put("sheetName", this.sheetName);
		//列数
		result_map.put("cell_count", String.valueOf(this.col_count));
		result_map.put("celw_arry", Arrays.toString(colWidth));
		//===========================================================================
		//初始化表格列的宽度，以便固定表格的样式。2014-6-19 张旭添加
		List<String> td_width_list = new ArrayList<String>();
		for(int cell_i = 0;cell_i <this.col_count;cell_i++){
			String key = String.format("%s_%s", 0,cell_i);
//System.out.print("key ="+key);			
			Map<DataEnum,String> data = this.tmap.get(key);
			String width = data.get(DataEnum.width);
//System.out.println("\t\twidth ="+width);				
			td_width_list.add(width);
		}
		result_map.put("td_widths", td_width_list);
		//---------------------------------------------------------------------------
		List<List<Map<String,Object>>> html_tabl_list = new ArrayList<List<Map<String,Object>>>();
		for(int row_i =0; row_i<=this.row_count;row_i++){
			List<Map<String,Object>> row_list = new ArrayList<Map<String,Object>>();//行数据
			for(int cell_j = 0;cell_j <=this.col_count;cell_j++){
				String key = String.format("%s_%s", row_i,cell_j);
				Map<String,Object> html_cell = new ConcurrentHashMap<String, Object>();
				ExRegion region = this.getRegion(key);//获得表格区域，null时是表格的单元格
				if(region == null ){//单元格（非合并）
					Map<DataEnum,String> data = this.tmap.get(key);
					if(data == null) continue;
					int width = Integer.parseInt(data.get(DataEnum.width));
					String value = data.get(DataEnum.value);
					List<Map<String,Object>> map = ParserRule.getFiledInfo(value);//解析Excel中字符值，生成Map对象。
					initMaxlength(map);
//System.out.println("************************************************value= "+value);
					html_cell.put(HtmlCellEnum.ftl_section_map.getName(), map);
					html_cell.put(HtmlCellEnum.h_data_field.getName(), value);
					html_cell.put(HtmlCellEnum.h_colspan.getName(), "0");
					html_cell.put(HtmlCellEnum.h_rowspan.getName(), "0");
					html_cell.put(HtmlCellEnum.h_width.getName(),String.format("%s", width));
					html_cell.put(HtmlCellEnum.h_height.getName(),data.get(DataEnum.height));
					//-----------------------------------------------------------------
					//样式
					html_cell.put(HtmlCellEnum.bottom_style.getName(), data.get(DataEnum.bottom_style));
					html_cell.put(HtmlCellEnum.top_style.getName(), data.get(DataEnum.top_style));
					html_cell.put(HtmlCellEnum.left_style.getName(), data.get(DataEnum.left_style));
					html_cell.put(HtmlCellEnum.right_style.getName(), data.get(DataEnum.right_style));
					//对齐
					html_cell.put(HtmlCellEnum.align_style.getName(), data.get(DataEnum.align_style));
					html_cell.put(HtmlCellEnum.valign_style.getName(), data.get(DataEnum.valign_style));
					html_cell.put(HtmlCellEnum.font_size.getName(), data.get(DataEnum.font_size));
//System.out.printf("value=%s,bottom=%s,left=%s,right=%s,top=%s\r\n",value,data.get(DataEnum.bottom_style),data.get(DataEnum.top_style)
//								,data.get(DataEnum.left_style),data.get(DataEnum.right_style)); 					
					//-----------------------------------------------------------------
				}else{
					if(out_region_map.containsKey(region.index)) continue;
					cell_j += region.getColspan()-1;//跳过合并的列
					int width = region.getWidth();
					String value = region.getCvalue();
					List<Map<String,Object>> map = ParserRule.getFiledInfo(value);//解析Excel中字符值，生成Map对象。
					initMaxlength(map);
//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ value= "+value);	
					html_cell.put(HtmlCellEnum.ftl_section_map.getName(), map);
					html_cell.put(HtmlCellEnum.h_data_field.getName(), value);
					html_cell.put(HtmlCellEnum.h_colspan.getName(), String.format("%s", region.colspan));
					html_cell.put(HtmlCellEnum.h_rowspan.getName(), String.format("%s", region.getRowspan()));
					html_cell.put(HtmlCellEnum.h_width.getName(),String.format("%s", width));
					html_cell.put(HtmlCellEnum.h_height.getName(),String.format("%s", region.getHeight()));
					//-----------------------------------------------------------------
					//样式
					html_cell.put(HtmlCellEnum.bottom_style.getName(), String.valueOf(region.getBottomStyle()));
					html_cell.put(HtmlCellEnum.top_style.getName(), String.valueOf(region.getTopStyle()));
					html_cell.put(HtmlCellEnum.left_style.getName(), String.valueOf(region.getLeftStyle()));
					html_cell.put(HtmlCellEnum.right_style.getName(), String.valueOf(region.getRightStyle()));
					//对齐
					html_cell.put(HtmlCellEnum.align_style.getName(), String.valueOf(region.getAlign()));
					html_cell.put(HtmlCellEnum.valign_style.getName(), String.valueOf(region.getValign()));
					html_cell.put(HtmlCellEnum.font_size.getName(), String.valueOf(region.getFontSize()));
//System.out.printf("Region=%s,bottom=%s,left=%s,right=%s,top=%s\r\n",value,region.getBottomStyle(),region.getTopStyle()
//								,region.getLeftStyle(),region.getRightStyle()); 					
					//-----------------------------------------------------------------					
					out_region_map.put(region.getIndex(), region);//合并区域已经输出，下次循环忽略掉
				}
				row_list.add(html_cell);
			}
			html_tabl_list.add(row_list);
		}

		result_map.put("tdata", html_tabl_list);
		return result_map;
	}
	
	/**
	 * 初始化控件的最大长度
	 * @param map
	 */
	private void initMaxlength(List<Map<String,Object>> maplist){
//		if(maplist ==null ||maplist.size()==0)return;
//		for(int i =0;i<maplist.size();i++){
//			Map<String,Object> map = maplist.get(i);
//		//-----------------------------------------------------
//		if(map.containsKey("dblength")){
//			String dblength = (String)map.get("dblength");
//			if(StringUtils.isNotEmpty(dblength)){
//				dblength = dblength.replaceAll("_[0-9]+", "");
//			}
//			if(StringUtils.isNotEmpty(dblength)){
//				int lg = Integer.parseInt(dblength);
//				map.put("mlength", lg>50?String.valueOf((int)lg/4):lg);
//			}
//		}
//		//-----------------------------------------------------
//		}
	}
	/***
	 * 根据索引获得Regin对象
	 * @param key 0_0
	 * @return
	 */
	public ExRegion getRegion(String key){
		ExRegion region = null;
		int row_index = Integer.parseInt(key.replaceAll("^([0-9]+)_([0-9]+)$", "$1"));
		int col_index = Integer.parseInt(key.replaceAll("^([0-9]+)_([0-9]+)$", "$2"));
		for(ExRegion reg:this.regList){
			if(reg.inRegion(row_index, col_index)){
				region = reg;
				return region;
			}
		}
		return null;
	}
	
	/***
	 * 实例化ExRegion对象
	 * @return
	 */
	public ExRegion newRegion(){
		return new ExRegion();
	}
	//==========================================================================
	//合并区域数据
	public class ExRegion implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7781201127270554250L;
		private int firstColumnIndex;
		private int lastColumnIndex;
		private int firstRowIndex;
		private int lastRowIndex;
		//区域索引
		private int index = 0;
		//合并的行数
		private int colspan;
		private int rowspan;
		//格式数据
		private int width;
		private int height;
		private int x;
		private int y;
		//======================================================================
		//表框样式
		private int bottom_style = 0;
		private int top_style = 0;
		private int left_style = 0;
		private int right_style = 0;
		
		private int align = 0;//
		private int valign = 0;
		private int font_size = 0;
		
		//======================================================================
		//数据
		private String cvalue = null;
		/**
		 * 默认构造函数
		 */
		public ExRegion(){
			
		}
		public ExRegion(int firstRowIndex,int lastRowIndex,int firstColIndex,int lastColIndex){
			this.firstColumnIndex = firstColIndex;
			this.lastColumnIndex  = lastColIndex;
			this.firstRowIndex = firstRowIndex;
			this.lastRowIndex = lastRowIndex;
			
			
			int bottomeRow = 0;
			int bottomeCol = 0;
			// 判断当前单元格是否是合并过的
			bottomeRow = lastRowIndex;
			bottomeCol = lastColIndex;
			rowspan = bottomeRow - firstRowIndex + 1;
			colspan = bottomeCol - firstColIndex + 1;
		}
		/**
		 * 根据Excel的区域初始化对象
		 * @param ca
		 */
		public ExRegion(Sheet sheet,CellRangeAddress ca,int index){
			if(ca==null) return;
			
			firstColumnIndex = ca.getFirstColumn();  
			lastColumnIndex = ca.getLastColumn();  
			firstRowIndex = ca.getFirstRow();  
			lastRowIndex = ca.getLastRow(); 
//System.out.printf("row=%s,col=%s,e_row =%s,e_col=%s\r\n",firstRowIndex,	firstColumnIndex,	lastRowIndex,	lastColumnIndex);
			//单元格样式,合并单元格的第一个Cell的样式就是整个合并区域的样式
			Cell areaCell = CellUtil.getCell(sheet.getRow(ca.getFirstRow()), firstColumnIndex);
			Cell rightCell = CellUtil.getCell(sheet.getRow(ca.getLastRow()), lastColumnIndex);
			if(areaCell != null ){
				this.bottom_style = areaCell.getCellStyle().getBorderBottom();
				this.top_style = areaCell.getCellStyle().getBorderTop();
				this.left_style = rightCell.getCellStyle().getBorderLeft();
				
                //对齐方式
                align=areaCell==null?0:areaCell.getCellStyle().getAlignment();//ALIGN_CENTER:2,ALIGN_LEFT:1,ALIGN_RIGHT:3
                valign=areaCell==null?-1:areaCell.getCellStyle().getVerticalAlignment();//VERTICAL_CENTER:1,VERTICAL_BOTTOM:2,VERTICAL_TOP:0
                short font_index=areaCell==null?-1:areaCell.getCellStyle().getFontIndex();
                font_size=font_index==-1?0:sheet.getWorkbook().getFontAt(font_index).getFontHeightInPoints();
			}
			//单元格样式,合并单元格的第一个Cell的样式就是整个合并区域的样式
			if(rightCell != null ){
				this.right_style = rightCell.getCellStyle().getBorderRight();
			}
			//===============================================
			this.index = index;
			//===============================================
			// 合并的行列数据
			int bottomeRow = 0;
			int bottomeCol = 0;
			// 判断当前单元格是否是合并过的
			bottomeRow = lastRowIndex;
			bottomeCol = lastColumnIndex;
			rowspan = bottomeRow - firstRowIndex + 1;
			colspan = bottomeCol - firstColumnIndex + 1;
			//==============================================
			//格式数据 width,height
			for (int j = firstColumnIndex; j <= lastColumnIndex; j++) {
				width = width + sheet.getColumnWidth(j);
			}
			width = width/32;//获取绝对宽度
			for (int j = firstRowIndex; j <= lastRowIndex; j++) {
				height = height + NumberUtils.getPxFromPt(sheet.getRow(j).getHeightInPoints());
			}
			//==============================================
			cvalue = getMergedRegionValue(sheet,firstRowIndex,firstColumnIndex);
			//==============================================
			//x,y坐标单独获取。
			initX();
			initY();
			//==============================================
		}
		/**
		 * 初始化X坐标（报表使用）
		 */
		public void initX(){
			this.x = 0;
			String key =String.format("%s_%s",firstRowIndex,firstColumnIndex);
			Map<DataEnum,String> data = tmap.get(key);
			if(data == null){
				System.out.println("null data = "+key);
			}
			this.x = Integer.parseInt(data.get(DataEnum.x));
		}
		/***
		 * 初始化Y坐标（报表使用）
		 */
		public void initY(){
			this.y = 0;
			String key =String.format("%s_%s",firstRowIndex,firstColumnIndex);
			Map<DataEnum,String> data = tmap.get(key);
			if(data == null){
				System.out.println("null data = "+key);
			}
			this.y = Integer.parseInt(data.get(DataEnum.y));
		}
		
		public boolean inRegion(int rowindex,int colIndex){
			if(rowindex >= firstRowIndex && rowindex <= lastRowIndex){ 
				if(colIndex >= firstColumnIndex && colIndex <= lastColumnIndex){ 
					return true; 
				} 
			} 
			return false;
		}

		public int getColspan() {
			return colspan;
		}

		public void setColspan(int colspan) {
			this.colspan = colspan;
		}

		public int getRowspan() {
			return rowspan;
		}

		public void setRowspan(int rowspan) {
			this.rowspan = rowspan;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public String getCvalue() {
			return cvalue;
		}

		public void setCvalue(String cvalue) {
			this.cvalue = cvalue;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
		public int getBottomStyle() {
			return bottom_style;
		}
		public void setBottomStyle(int bottom_style) {
			this.bottom_style = bottom_style;
		}
		public int getTopStyle() {
			return top_style;
		}
		public void setTopStyle(int top_style) {
			this.top_style = top_style;
		}
		public int getLeftStyle() {
			return left_style;
		}
		public void setLeftStyle(int left_style) {
			this.left_style = left_style;
		}
		public int getRightStyle() {
			return right_style;
		}
		public void setRightStyle(int right_style) {
			this.right_style = right_style;
		}
		public int getAlign() {
			return align;
		}
		public void setAlign(int align) {
			this.align = align;
		}
		public int getValign() {
			return valign;
		}
		public void setValign(int valign) {
			this.valign = valign;
		}
		public int getFontSize() {
			return font_size;
		}
		public void setFontSize(int font_size) {
			this.font_size = font_size;
		}
		
		public String[] propertiesNames(){
			return new String[]{"firstColumnIndex","lastColumnIndex","firstRowIndex","lastRowIndex","index","colspan","rowspan","width","height","x","y"
								,"bottomStyle","topStyle","leftStyle","rightStyle","align","valign","fontSize","cvalue"};
		}
		public int getFirstColumnIndex() {
			return firstColumnIndex;
		}
		public void setFirstColumnIndex(int firstColumnIndex) {
			this.firstColumnIndex = firstColumnIndex;
		}
		public int getLastColumnIndex() {
			return lastColumnIndex;
		}
		public void setLastColumnIndex(int lastColumnIndex) {
			this.lastColumnIndex = lastColumnIndex;
		}
		public int getFirstRowIndex() {
			return firstRowIndex;
		}
		public void setFirstRowIndex(int firstRowIndex) {
			this.firstRowIndex = firstRowIndex;
		}
		public int getLastRowIndex() {
			return lastRowIndex;
		}
		public void setLastRowIndex(int lastRowIndex) {
			this.lastRowIndex = lastRowIndex;
		}
	}
	
	/**
	 * 获取单元格的数据
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell){
		if(cell == null) return "";  
	    if(cell.getCellType() == Cell.CELL_TYPE_STRING){  
	        return cell.getStringCellValue();  
	    }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){  
	        return String.valueOf(cell.getBooleanCellValue());  
	    }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){  
	        return cell.getCellFormula() ;  
	    }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
	        return String.valueOf(cell.getNumericCellValue());  
	    }  
	    return "";  
	}
	
	public static Date getDateCellValue(Cell cell){
	    return  cell.getDateCellValue();  
	}
	/***
	 * 获取合并单元格的数据
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public String getMergedRegionValue(Sheet sheet ,int row , int column){  
	    int sheetMergeCount = sheet.getNumMergedRegions();  
	    for(int i = 0 ; i < sheetMergeCount ; i++){  
	        CellRangeAddress ca = sheet.getMergedRegion(i);  
	        int firstColumn = ca.getFirstColumn();  
	        int lastColumn = ca.getLastColumn();  
	        int firstRow = ca.getFirstRow();  
	        int lastRow = ca.getLastRow();  
	        if(row >= firstRow && row <= lastRow){  
	            if(column >= firstColumn && column <= lastColumn){  
	                Row fRow = sheet.getRow(firstRow);  
	                Cell fCell = fRow.getCell(firstColumn);  
	                return getCellValue(fCell) ;  
	            }  
	        }  
	    }  
	    return null ;  
	}  	
	
	//==============================================================
	//数据枚举
	public enum DataEnum{
		index,value,width,height,x,y,align_style,valign_style,font_size,bottom_style,top_style,left_style,right_style;
		
		public String getName(){
			String name = "";
			switch(this){
			case index:
				name = "index";
				break;
			case value:
				name = "value";
				break;
			case width:
				name = "width";
				break;
			case height:
				name = "height";
				break;
			case x:
				name = "x";
				break;
			case y:
				name = "y";
				break;
			case align_style:
				name = "align_style";
				break;
			case valign_style:
				name = "valign_style";
				break;
			case font_size:
				name = "font_size";
				break;
			case bottom_style:
				name = "bottom_style";
				break;
			case top_style:
				name = "top_style";
				break;
			case left_style:
				name = "left_style";
				break;
			case right_style:
				name = "right_style";
				break;				
			default :
				break;
			}
			return name;
			
		}
	}
	//HTML枚举(用于FTL页面输出)
	public enum HtmlCellEnum{
		h_data_field,h_width,h_height,h_colspan,h_rowspan,h_style,h_script,ftl_section_map,
		bottom_style,top_style,left_style,right_style,align_style,valign_style,font_size;
		
		public String getName(){
			String name = "";
			switch(this){
			case h_data_field:
				name = "field";
				break;
			case h_width:
				name = "width";
				break;
			case h_height:
				name = "height";
				break;
			case h_colspan:
				name = "colspan";
				break;
			case h_rowspan:
				name = "rowspan";
				break;
			case h_style:
				name = "style";
				break;
			case h_script:
				name = "script";
				break;
			case bottom_style:
				name = "bottom_sytle";
				break;
			case top_style:
				name = "top_style";
				break;
			case left_style:
				name = "left_style";
				break;
			case right_style:
				name = "right_style";
				break;
			case align_style:
				name = "align_style";
				break;
			case valign_style:
				name = "valign_style";
				break;
			case font_size:
				name = "font_size";
				break;	
			case ftl_section_map:
				name = "ftlmap";
				break;
			default :
				break;
			}
			return name;
		}
	}	
	//==============================================================

	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public int getTwidth() {
		return twidth;
	}
	public void setTwidth(int twidth) {
		this.twidth = twidth;
	}
	public int getTheight() {
		return theight;
	}
	public void setTheight(int theight) {
		this.theight = theight;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	//
	public List<ExRegion> getRegList() {
		return regList;
	}
	public void setRegList(List<ExRegion> regList) {
		this.regList = regList;
	}
	public List<Map<DataEnum, String>> getValListMap() {
		return valListMap;
	}
	public void setValListMap(List<Map<DataEnum, String>> valListMap) {
		this.valListMap = valListMap;
	}
	public Map<String, Map<DataEnum, String>> getTmap() {
		return tmap;
	}
	public void setTmap(Map<String, Map<DataEnum, String>> tmap) {
		this.tmap = tmap;
	}
	public int getRow_count() {
		return row_count;
	}
	public void setRow_count(int row_count) {
		this.row_count = row_count;
	}
	public int getCol_count() {
		return col_count;
	}
	public void setCol_count(int col_count) {
		this.col_count = col_count;
	}

	
}
