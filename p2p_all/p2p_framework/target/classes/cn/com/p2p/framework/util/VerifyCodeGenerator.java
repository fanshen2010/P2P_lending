package cn.com.p2p.framework.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆验证码生成器
 */
public class VerifyCodeGenerator {

	static Random r = new Random();
	static String ssource = "1234567890";
	static char[] src = ssource.toCharArray();

	// 根据长度获取随机字符串
	private static String randString(int length) {
		char[] buf = new char[length];
		int rnd;
		for (int i = 0; i < length; i++) {
			rnd = Math.abs(r.nextInt()) % src.length;
			buf[i] = src[rnd];
		}
		return new String(buf);
	}

	// 根据指定的长度，获取指定长度的随机字符串
	public static String getVerifyCode(int length) {
		String VerifyCode = randString(length);
		return VerifyCode;
	}

	// 获取随机颜色
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	// 根据输入的字符串，生成图片
	private static BufferedImage createImage(String sCode) {
		try {
			Random random = new Random();
			Font CodeFont = new Font("Arial", Font.ITALIC,
					random.nextInt(8) + 8);
			int iLength = sCode.length();
			int width = 12 * iLength, height = 20;
			int CharWidth = (int) (width - 1) / iLength;
			int CharHeight = 16;

			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(getRandColor(200, 240));
			g.fillRect(0, 0, width, height);
			g.setFont(CodeFont);
			g.setColor(getRandColor(10, 50));
			g.drawRect(0, 0, width - 1, height - 1);

			g.setColor(getRandColor(160, 200));
			// 绘制噪点线
			// for (int i = 0; i < 155; i++) {
			// int x = random.nextInt(width);
			// int y = random.nextInt(height);
			// int xl = random.nextInt(12);
			// int yl = random.nextInt(12);
			// g.drawLine(x, y, x + xl, y + yl);
			// }
			// 绘制干扰线
			int lins = random.nextInt(5);
			for (int i = 0; i <= lins; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height / 2);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				int red = random.nextInt(255);
				int green = random.nextInt(255);
				int blue = random.nextInt(255);
				g.setColor(new Color(red, green, blue));
				g.drawLine(x, y, x + xl, y + yl);
			}
			shear(g, random.nextInt(CharWidth + width),
					random.nextInt(CharHeight + height), getRandColor(128, 255));// 使图片扭曲
			// 绘制验证码
			for (int i = 0; i < iLength; i++) {
				String rand = sCode.substring(i, i + 1);
				AffineTransform trans = new AffineTransform();

				// trans.rotate(random.nextInt(10) * i, 3, 5);

				// float scaleSize = random.nextFloat() * 0.5f;
				//
				// if (scaleSize < 0.8 || scaleSize > 1.1f) {
				//
				// scaleSize = 1f;
				//
				// }
				//
				// trans.scale(random.nextInt(1) + 1, random.nextInt(1) + 1);
				// g.setTransform(trans);

				g.setColor(new Color(20 + random.nextInt(60), 20 + random
						.nextInt(120), 20 + random.nextInt(180)));
				g.setFont(getRandomFont());// 随机字体
				g.drawString(rand, CharWidth * i + random.nextInt(3),
						random.nextInt(CharHeight - 8) + 12);
			}

			g.dispose();

			return image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String flushVerifyCode(HttpServletResponse response,
			String sCode) {
		try {
			BufferedImage image = createImage(sCode);
			OutputStream out = response.getOutputStream();
			ImageIO.write(image, "JPEG", out);
			// out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Font getRandomFont() {
		String[] fonts = { "Georgia", "Verdana", "Arial", "Tahoma",
				"Time News Roman", "Courier New", "华文新魏", "华文隶书", "方正舒体",
				"方正姚体", "华文行楷", "Arial Black", "Quantzite" };
		int fontIndex = r.nextInt(fonts.length - 1);
		int fontSize = (int) Math.round(Math.random() * 4 + 16);
		return new Font(fonts[fontIndex], Font.ITALIC, fontSize);
	}

	private static void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private static void shearX(Graphics g, int w1, int h1, Color color) {

		int period = r.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = r.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}

	}

	private static void shearY(Graphics g, int w1, int h1, Color color) {

		int period = r.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}

		}

	}

}