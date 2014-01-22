import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class MsIng {

	public synchronized static  BufferedImage readImg(String path){
		Image image=Toolkit.getDefaultToolkit().getImage("f:\\img0.jpg");
		BufferedImage bufferedImage = null;
		try {
//			InputStream is = new FileInputStream(new File(path));  
			bufferedImage=ImageIO.read(new File(path));
//			bufferedImage=ImageIO.read(new FileInputStream(path));
//			bufferedImage=ImageIO.read(is);
//			is.close();
//			System.out.println("图像:"+bufferedImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bufferedImage;
		
	}
	
}
