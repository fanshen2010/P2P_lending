package cn.com.p2p.framework.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.IOUtils;

/**
 * 文件上传工具
 * 
 * @author pub
 *
 */
public class FileUpload implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static Map<String, String> imgUpload(File file, String fileName)
            throws Exception {
        Properties settings = SpringContextUtils.getBean("settings");
        // 获取文件上传的物理路径
        String imgPath = settings.getProperty(
                "imgPath", "/www/uploadfile/images/");
        String originalPath = imgPath + "/original/";
        String bigPath = imgPath + "/big/";
        String thumbPath = imgPath + "/thumb/";

        String suffix = "";
        String current = DateUtils.formatCurrentDateTime("yyyyMMddHHmmss")
                + System.currentTimeMillis();

        File originalDirectory = new File(originalPath);
        File bigDirectory = new File(bigPath);
        File thumbDirectory = new File(thumbPath);

        if (!originalDirectory.exists()) {
            originalDirectory.mkdir();
        }

        if (!bigDirectory.exists()) {
            bigDirectory.mkdir();
        }

        if (!thumbDirectory.exists()) {
            thumbDirectory.mkdir();
        }

        if ((fileName != null) && (fileName.length() > 0)) {
            // 获取后缀名.的索引位置
            int dot = fileName.lastIndexOf(".");
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                // 获取文件后缀名，并转换成小写
                suffix = fileName.substring(dot + 1).toLowerCase();
            }
        }
        String newName = current + "." + suffix;
        String imgUrl = settings.getProperty("imagesServer", "/fileServer/");
        String originalUrl = imgUrl + "/original/" + newName;
        String bigUrl = imgUrl + "/big/" + newName;
        String thumbUrl = imgUrl + "/thumb/" + newName;
        // TODO: 此处应判断上传文件类型是否允许，如果不允许，应返回异常信息
        FileInputStream fis = new FileInputStream(file);
        FileInputStream fisThumb = new FileInputStream(file);
        FileInputStream fisBig = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(originalPath + newName);
        FileOutputStream fosThumb = new FileOutputStream(thumbPath + newName);
        FileOutputStream fosBig = new FileOutputStream(bigPath + newName);
        Thumbnails.of(fisBig).size(670, 670).toOutputStream(fosBig);

        // 如果图片小于50Kb不压缩
        if (file.length() < 50 * 1024) {
            Thumbnails.of(fisThumb).size(200, 200).toOutputStream(fosThumb);
            fosThumb.flush();
            fosThumb.close();
        } else {

            BufferedImage src = null;
            ImageWriter imgWrier;
            ImageWriteParam imgWriteParams;

            // 指定写图片的方式为 jpg
            imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
            imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                    null);
            // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
            imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
            // 这里指定压缩的程度，参数qality是取值0~1范围内，
            imgWriteParams.setCompressionQuality((float) 0.1);
            imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
            ColorModel colorModel = ColorModel.getRGBdefault();
            // 指定压缩时使用的色彩模式
            imgWriteParams
                    .setDestinationType(new javax.imageio.ImageTypeSpecifier(
                            colorModel, colorModel.createCompatibleSampleModel(
                                    16, 16)));
            src = ImageIO.read(fisThumb);
            // out =fosThumb;

            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(fosThumb));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            fosThumb.flush();
            fosThumb.close();

        }
        IOUtils.copy(fis, fos);
        fos.flush();
        // fosThumb.flush();
        fosBig.flush();

        // fosThumb.close();
        fosBig.close();
        fos.close();
        fis.close();
        Map<String, String> map = new HashMap<String, String>();
        map.put("fileUrlOriginal", originalUrl);
        map.put("fileUrlThumb", thumbUrl);
        map.put("fileUrlBig", bigUrl);
        return map;
    }

    public static Map<String, String> fileUpload(File file, String fileName)
            throws IOException {
        Properties settings = SpringContextUtils.getBean("settings");
        // 获取文件上传的物理路径
        String filePath = settings.getProperty("filePath", "/www/uploadfile/files");

        String suffix = "";
        String current = DateUtils.formatCurrentDateTime("yyyyMMddHHmmss")
                + System.currentTimeMillis();

        String originalPath = filePath + "/original/";
        String bigPath = filePath + "/big/";
        String thumbPath = filePath + "/thumb/";

        File originalDirectory = new File(originalPath);
        File bigDirectory = new File(bigPath);
        File thumbDirectory = new File(thumbPath);

        if (!originalDirectory.exists()) {
            originalDirectory.mkdir();
        }

        if (!bigDirectory.exists()) {
            bigDirectory.mkdir();
        }

        if (!thumbDirectory.exists()) {
            thumbDirectory.mkdir();
        }

        if ((fileName != null) && (fileName.length() > 0)) {
            // 获取后缀名.的索引位置
            int dot = fileName.lastIndexOf(".");
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                // 获取文件后缀名，并转换成小写
                suffix = fileName.substring(dot + 1).toLowerCase();
            }
        }
        String newName = current + "." + suffix;
        String fileUrl = settings.getProperty("filesServer",
                "/www/uploadfile/files/");

        // TODO: 此处应判断上传文件类型是否允许，如果不允许，应返回异常信息
        FileInputStream fis = new FileInputStream(file);

        FileOutputStream fos = new FileOutputStream(originalPath + File.separator + newName);
        String originalUrl = fileUrl + "/original/" + newName;
        String bigUrl = fileUrl + "/big/" + newName;
        String thumbUrl = fileUrl + "/thumb/" + newName;
        // 当前上传文件类型为图片 则进行压缩
        if (StringUtils.equals(suffix, "bmp") || StringUtils.equals(suffix, "png")
                || StringUtils.equals(suffix, "jpeg") || StringUtils.equals(suffix, "jpg")
                || StringUtils.equals(suffix, "gif"))
        {
            // TODO: 此处应判断上传文件类型是否允许，如果不允许，应返回异常信息
            FileInputStream fisThumb = new FileInputStream(file);
            FileInputStream fisBig = new FileInputStream(file);
            FileOutputStream fosThumb = new FileOutputStream(thumbPath + newName);
            FileOutputStream fosBig = new FileOutputStream(bigPath + newName);
            Thumbnails.of(fisBig).size(670, 670).toOutputStream(fosBig);

            // 如果图片小于50Kb不压缩
            if (file.length() < 50 * 1024) {
                Thumbnails.of(fisThumb).size(200, 200).toOutputStream(fosThumb);
                fosThumb.flush();
                fosThumb.close();
            } else {

                BufferedImage src = null;
                ImageWriter imgWrier;
                ImageWriteParam imgWriteParams;

                // 指定写图片的方式为 jpg
                imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
                imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(
                        null);
                // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
                imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
                // 这里指定压缩的程度，参数qality是取值0~1范围内，
                imgWriteParams.setCompressionQuality((float) 0.1);
                imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
                ColorModel colorModel = ColorModel.getRGBdefault();
                // 指定压缩时使用的色彩模式
                imgWriteParams
                        .setDestinationType(new javax.imageio.ImageTypeSpecifier(
                                colorModel, colorModel.createCompatibleSampleModel(
                                        16, 16)));
                src = ImageIO.read(fisThumb);
                // out =fosThumb;

                imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
                // OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(fosThumb));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
                fosThumb.flush();
                fosThumb.close();

            }
            // fosThumb.flush();
            fosBig.flush();

            // fosThumb.close();
            fosBig.close();
        }

        IOUtils.copy(fis, fos);
        fos.flush();

        fos.close();
        fis.close();
        Map<String, String> map = new HashMap<String, String>();
        map.put("fileUrlOriginal", originalUrl);
        map.put("fileName", fileName);
        map.put("fileUrlThumb", thumbUrl);
        map.put("fileUrlBig", bigUrl);
        return map;
    }

    // 图片处理
    public String compressPic() {
        try {
            // // Image img = ImageIO.read(fos);
            // // 判断图片格式是否正确
            // if (img.getWidth(null) == -1) {
            // System.out.println(" can't read,retry!" + "<BR>");
            // return "no";
            // } else {
            // int newWidth; int newHeight;
            // // 判断是否是等比缩放
            // // 为等比缩放计算输出的图片宽度及高度
            // double rate1 = ((double) img.getWidth(null)) / (double) 200 +
            // 0.1;
            // double rate2 = ((double) img.getHeight(null)) / (double) 200 +
            // 0.1;
            // // 根据缩放比率大的进行缩放控制
            // double rate = rate1 > rate2 ? rate1 : rate2;
            // newWidth = (int) (((double) img.getWidth(null)) / rate);
            // newHeight = (int) (((double) img.getHeight(null)) / rate);
            // BufferedImage tag = new BufferedImage((int) newWidth, (int)
            // newHeight, BufferedImage.TYPE_INT_RGB);
            //
            // /*
            // * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
            // * 优先级比速度高 生成的图片质量比较好 但速度慢
            // */
            // tag.getGraphics().drawImage(img.getScaledInstance(newWidth,
            // newHeight, Image.SCALE_SMOOTH), 0, 0, null);
            // // FileOutputStream out = new FileOutputStream(outputDir +
            // outputFileName);
            // // JPEGImageEncoder可适用于其他图片类型的转换
            // // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // // encoder.encode(tag);
            // // out.close();
            // }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "ok";
    }

}
