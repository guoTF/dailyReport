package com.seda.dailyReport.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateCodeUtils {

	/*
	 * 随机数
	 */
	private static Random random = new Random();

	/*
	 * 获取随机数颜色
	 */
	private static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
	}

	public static String getValidateCode(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("image/jpeg");

		int width = 120;
		int height = 30;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();

		Color[] color={Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK};

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

		Color lineColor = getRandomColor();
		g.setColor(lineColor);
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(1200);
			int yl = random.nextInt(1200);
			g.drawLine(x, y, x + xl, y + yl);
		}

		int num1 = random.nextInt(9) + 1;
		int num2 = random.nextInt(9) + 1;
		int funNo = random.nextInt(3);
		String funMethod = "";
		int result = 0;
		switch (funNo) {
		case 0:
			funMethod = "+";
			result = num1 + num2;
			break;
		case 1:
			funMethod = "-";
			result = (num1 - num2) > 0 ? (num1 - num2) : (num2 - num1);
			break;
		case 2:
			funMethod = "×";
			result = num1 * num2;
			break;
		}
		//随机产生两个非计算的数字
		int num3=random.nextInt(9)+1;
		int num4=random.nextInt(9)+1;
		
		String calc =num1+" "+num3+" "+funMethod+" "+num2+" "+num4+" =";
		
		FontRenderContext context = g.getFontRenderContext();
		Rectangle2D bounds = (new Font(Font.SANS_SERIF, Font.BOLD, 16)).getStringBounds(calc, context);
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		
		//获取非计算数字的位置
		int num3Loc=random.nextInt(2);
		int num4Loc=random.nextInt(2);
		
		if(funNo==1){
			//减法
			if(num1-num2>0){
				if(num3Loc==0&&num4Loc==0){
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)x, (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)(x+=15), (int) baseY);
					
				}else if(num3Loc==0&&num4Loc==1){
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)x, (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
					
				}else if(num3Loc==1&&num4Loc==0){
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)x, (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)(x+=15), (int) baseY);
					
				}else{
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)x, (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
				}
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString("= ", (int)(x+=15), (int) baseY);
			}else{
				if(num3Loc==0&&num4Loc==0){
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)x, (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)(x+=15), (int) baseY);
					
				}else if(num3Loc==0&&num4Loc==1){
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)x, (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
					
				}else if(num3Loc==1&&num4Loc==0){
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)x, (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)(x+=15), (int) baseY);
					
				}else{
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num2+" ", (int)x, (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num3+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString("- ", (int)(x+=15), (int) baseY);
					
					g.setColor(color[random.nextInt(color.length)]);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num1+" ", (int)(x+=15), (int) baseY);
					
					g.setColor(Color.BLACK);
					g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
					g.drawString(num4+" ", (int)(x+=15), (int) baseY);
				}
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString("= ", (int)(x+=15), (int) baseY);
			}
		}else{
			//非减法
			if(num3Loc==0&&num4Loc==0){
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num3+" ", (int)x, (int) baseY);
				
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num1+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(funMethod+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num4+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num2+" ", (int)(x+=15), (int) baseY);
				
			}else if(num3Loc==0&&num4Loc==1){
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num3+" ", (int)x, (int) baseY);
				
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num1+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(funMethod+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num2+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num4+" ", (int)(x+=15), (int) baseY);
				
			}else if(num3Loc==1&&num4Loc==0){
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num1+" ", (int)x, (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num3+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(funMethod+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num4+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num2+" ", (int)(x+=15), (int) baseY);
				
			}else{
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num1+" ", (int)x, (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num3+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(funMethod+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(color[random.nextInt(color.length)]);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num2+" ", (int)(x+=15), (int) baseY);
				
				g.setColor(Color.BLACK);
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.drawString(num4+" ", (int)(x+=15), (int) baseY);
			}
			g.setColor(Color.BLACK);
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawString("= ", (int)(x+=15), (int) baseY);
		}
		
		HttpSession session = request.getSession();
		String imgCode = String.valueOf(result);
		session.setAttribute("imgCode", imgCode);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return imgCode;
	}
}
