package com.nln;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateCode {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Qrcode qrcode = new Qrcode();
		String content = "BEGIN:VCARD\r\n" + 
						   "FN:姓名:聂力娜\r\n"+
						   "ORG:班级:科技1603\r\n"+
						   "TITLE:学生\r\n" +
						   "TEL;CELL;VOICE:15533516801\r\n"+
						   "EMAIL;HOME:2843753691@qq.com\r\n" + 
						   "END:VCARD"; 

		boolean[][]calQrcode=qrcode.calQrcode(content.getBytes("utf-8"));
		int version=qrcode.getQrcodeVersion();
		int imgSize=67+(version-1)*12;
		BufferedImage bufferedimage= new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_BGR);
		Graphics2D gs= bufferedimage.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, imgSize, imgSize);
		 content ="123";
		int  pixoff=2;
		
		for(int i=0;i<calQrcode.length;i++){
			for(int j=0;j<calQrcode.length;j++){
				if(calQrcode[i][j]){
					int StartR=255;
					int StartG=0;
					int StartB=0;
					
					int EndR=0;
					int EndG=255;
					int EndB=0;
					
					int r=StartR+(EndR-StartR)*(j+1)/calQrcode.length;
					int g=StartG+(EndG-StartG)*(j+1)/calQrcode.length;
					int b=StartB+(EndB-StartB)*(j+1)/calQrcode.length;
					if(r>255)
						r=255;
					if(g>255)
						g=255;
					if(b>255)
						b=255;
					Color color= new Color(r,g,b);
					gs.setColor(color);
					gs.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
				}
			}
		}

		
		
		BufferedImage logo=ImageIO.read(new File("D:/1.jpg"));
		int logoSize=imgSize/3;
		int o=(imgSize-logoSize)/2;
		gs.drawImage(logo,o,o,logoSize,logoSize,null);
		bufferedimage.flush();
		gs.dispose();
		
	
	
	try{
		ImageIO.write(bufferedimage,"png",new File ("D:/qr1.png"));
	}catch(IOException e){
		e.printStackTrace();
	}
	bufferedimage.flush();
	System.out.println("OK");
	}
}

