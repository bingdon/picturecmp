import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvReleaseImage;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_COMP_CORREL;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_HIST_ARRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCalcHist;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCompareHist;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvNormalizeHist;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.googlecode.javacpp.Pointer;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_imgproc.CvHistogram;


public class bnbn {

	private static long bingtime=0;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		bingtime=System.currentTimeMillis();
		ExecutorService pool=Executors.newCachedThreadPool();
//		ExecutorService pool=Executors.newFixedThreadPool(2);
//		ExecutorService pool=Executors.newSingleThreadExecutor();
		
		
		Thread aThread=new Thread(bing);
		Thread bThread=new Thread(cing);
		Thread cThread=new Thread(ling);
		
		pool.execute(aThread);
		pool.execute(bThread);
		pool.execute(cThread);
		
		pool.shutdown();
		
	}

	
	
static Runnable bing=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			bnbn li=new bnbn();
			for (int i = 0; i < 16; i++) {
				 li.CmpPic("f:\\img0.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img0.jpg");
				 
			}
			
			
			li=null;
		}
	};
	
	
 static Runnable ling=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			bnbn li=new bnbn();
			for (int i = 0; i < 17; i++) {
				 li.CmpPic("f:\\img0.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img0.jpg");
			}
			
			li=null;
		}
	};
	
	
static Runnable cing=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			bnbn li=new bnbn();
			for (int i = 0; i < 17; i++) {
				 li.CmpPic("f:\\img0.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img2.jpg");
				 li.CmpPic("f:\\img0.jpg");
			}
			
			li=null;
		}
	};
	
	
public double CmpPic(String path) {  
		
//		MsIng.readImg(path);
//		MsIng.readImg("f:\\img1.jpg");
//		System.out.println("开始计时");
		long time=System.currentTimeMillis();
        int l_bins = 20;  
        int hist_size[] = { l_bins };  
        float v_ranges[] = { 0, 100 };  
        float ranges[][] = { v_ranges };  
//        System.out.println("加载图片");

        IplImage Image10 = IplImage.createFrom(MsIng.readImg("f:\\img1.jpg"));

        IplImage Image1=cvCreateImage(cvGetSize(Image10), Image10.depth(), 1);
        cvCvtColor(Image10, Image1, CV_BGR2GRAY);
        
        IplImage Image20 = IplImage.createFrom(MsIng.readImg(path));
        IplImage Image2=cvCreateImage(cvGetSize(Image20), Image20.depth(), 1);
        cvCvtColor(Image20, Image2, CV_BGR2GRAY);
        
        
        
        IplImage imageArr1[] = { Image1 };  
        IplImage imageArr2[] = { Image2 };  
        CvHistogram Histogram1 = CvHistogram.create(1, hist_size,  
                CV_HIST_ARRAY, ranges, 1);  
        CvHistogram Histogram2 = CvHistogram.create(1, hist_size,  
                CV_HIST_ARRAY, ranges, 1);  
        cvCalcHist(imageArr1, Histogram1, 0, null);  
        cvCalcHist(imageArr2, Histogram2, 0, null);  
        
        cvNormalizeHist(Histogram1, 1.0);  
        cvNormalizeHist(Histogram2, 1.0);  
        
        
        
        double ccc0=cvCompareHist(Histogram1, Histogram2, CV_COMP_CORREL);  
        double ccc1=cvCompareHist(Histogram1, Histogram2, 1);  
        double ccc2=cvCompareHist(Histogram1, Histogram2, 2);  
        double ccc3=cvCompareHist(Histogram1, Histogram2, 3);  
//        System.out.print("检测结果:"+ccc0+"::"
//        		+ccc1+"::"+ccc2+"::"+ccc3+"\n");
        cvReleaseImage(Image1);
        cvReleaseImage(Image2);
//        cvReleaseImage(Image10);
//        cvReleaseImage(Image20);
        
        double bing=((1-ccc1)+(1-ccc3)+ccc0+ccc2)/4;
        time=System.currentTimeMillis()-time;
        long m=System.currentTimeMillis()-bingtime;
////        System.out.println("最终结果:"+bing*100
////        		+"\n"
////        		+"耗时:"+time);
        System.out.println("计算时间:"+m/1000+"秒");
        
        
        return bing;  
        
    }  
	
}
