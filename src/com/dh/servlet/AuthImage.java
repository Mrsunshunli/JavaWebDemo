package com.dh.servlet;

import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;

import java.awt.image.BufferedImage;

import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


public class AuthImage extends HttpServlet {

	private static final long serialVersionUID = 8165458985542870320L;
	// ����ͼ����֤���е��ַ���������Ĵ�С
	private Font mFont = new Font("Arial Black", Font.PLAIN, 16);
	/**
	 * 
	 * ���������ɫ
	 * 
	 * @param fc
	 * 
	 * @param bc
	 * 
	 * @return
	 */
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);

	}



	public void doGet(HttpServletRequest request, HttpServletResponse response)

	throws ServletException, IOException {

		// ��ֹ���ɵ�ҳ�����ݱ����棬��֤ÿ���������������֤��
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// ָ��ͼ����֤��ͼƬ�Ĵ�С��
		int width = 80;// ���
		int height = 20;// �߶�
		BufferedImage image = new BufferedImage(width, height,
		BufferedImage.TYPE_INT_RGB);

		// ׼����ͼƬ�л�������

		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		g.drawRect(0, 0, width - 1, height - 1);
		g.setFont(mFont);
		g.setColor(getRandColor(160, 200));

		// �����������

		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g.drawLine(x, y, x + xl, y + yl);
		}

		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}
		String sRand = "";
		// ����������ַ��������뵽ͼƬ��
		int LEN = 4; // ���������ĳ���
		for (int i = 0; i < LEN; i++) {

			String tmp = getRandomChar();
			sRand += tmp;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
			.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(tmp, 15 * i + 10, 15);
		}
		HttpSession session = request.getSession(true);
		// �����Զ�ת��ΪСд��Ҳ����˵�û���������֤���ʱ�򣬲���Ҫ���ִ�Сд���������롣
		session.setAttribute("randomImageStr", sRand.toLowerCase());
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());

	}

	/**
	 * 
	 * ��������ַ���
	 * 
	 * @return
	 */

	private String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);

		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}

	}

}