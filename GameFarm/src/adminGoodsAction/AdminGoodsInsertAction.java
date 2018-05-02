package adminGoodsAction;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import goodsDB.GoodsBean;
import goodsDB.GoodsDAO;

public class AdminGoodsInsertAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminGoodsInsertAction execute()");
		//한글 설정
		request.setCharacterEncoding("utf-8");
		
		//자료받을 경로를 설정
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		
		//이미지 용량설정
		int maxSize=30*1024*1024;	
		MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		//유튜브영상을 받아와 필요한 부분만 자름
		String video = multi.getParameter("goods_video");
		int idx = video.indexOf("be/");
		String video1 = video.substring(idx+3);
		
		//변수에 이미지 이름들을 저장
		String filename1 = multi.getFilesystemName("goods_image");
		String filename2 = multi.getFilesystemName("goods_image2");
		String filename3 = multi.getFilesystemName("goods_image3");
		String filename4 = multi.getFilesystemName("goods_image4");
		String content = multi.getFilesystemName("goods_content");
		
		//lib에jai_codec.jar,jai_core.jar추가
		//ParameterBlock클래스에 변환할 이미지를 경로와 같이 담고 
		ParameterBlock pb=new ParameterBlock();
		pb.add(realPath+"/"+filename1);
		ParameterBlock pb2=new ParameterBlock();
		pb2.add(realPath+"/"+filename2);
		ParameterBlock pb3=new ParameterBlock();
		pb3.add(realPath+"/"+filename3);
		ParameterBlock pb4=new ParameterBlock();
		pb4.add(realPath+"/"+filename4);
		ParameterBlock pbc=new ParameterBlock();
		pbc.add(realPath+"/"+ content);
		
		// RenderedOp로 pb에 저장된 이미지를 불러옴
		RenderedOp rOp=JAI.create("fileload",pb);
		RenderedOp rOp2=JAI.create("fileload",pb2);
		RenderedOp rOp3=JAI.create("fileload",pb3);
		RenderedOp rOp4=JAI.create("fileload",pb4);
		RenderedOp rOpc=JAI.create("fileload",pbc);
		
		//불러온 이미지를 bi로 생성한 bufferedimage에 담는다
		BufferedImage bi= rOp.getAsBufferedImage();
		BufferedImage bi2= rOp2.getAsBufferedImage();
		BufferedImage bi3= rOp3.getAsBufferedImage();
		BufferedImage bi4= rOp4.getAsBufferedImage();
		BufferedImage bic= rOpc.getAsBufferedImage();
		
		// 썸네일 이미지로 크기 변경
		BufferedImage thumb2=new BufferedImage(720,400,BufferedImage.TYPE_INT_RGB);
		BufferedImage thumb3=new BufferedImage(720,400,BufferedImage.TYPE_INT_RGB);
		BufferedImage thumb4=new BufferedImage(720,400,BufferedImage.TYPE_INT_RGB);
		
		//정해진 버퍼사이즈에 맞춰서 드로우
		//이미지를 넣어서 크기에 맞게 그린다		 
		Graphics2D g2=thumb2.createGraphics(); 
		g2.drawImage(bi2,0,0,720,400,null); 
		Graphics2D g3=thumb3.createGraphics(); 
		g3.drawImage(bi3,0,0,720,400,null); 
		Graphics2D g4=thumb4.createGraphics(); 
		g4.drawImage(bi4,0,0,720,400,null); 
		
		//이미지저장
		File image = new File(realPath+"/"+filename1);
		ImageIO.write(bi,"jpg",image); 
		File image2 = new File(realPath+"/"+filename2);
		ImageIO.write(thumb2,"jpg",image2); 
		File image3 = new File(realPath+"/"+filename3);
		ImageIO.write(thumb3,"jpg",image3); 
		File image4 = new File(realPath+"/"+filename4);
		ImageIO.write(thumb4,"jpg",image4); 
		File imagec = new File(realPath+"/"+content);
		ImageIO.write(bic,"jpg",imagec);
		
		//객체 생성
		GoodsBean gb = new GoodsBean();
		
		//bean에 값들을 저장시킨다
		gb.setGoods_type(multi.getParameter("goods_type"));
		gb.setGoods_name(multi.getParameter("goods_name"));
		gb.setGoods_platform(multi.getParameter("goods_platform"));
		gb.setGoods_developer(multi.getParameter("goods_developer"));
		gb.setGoods_publisher(multi.getParameter("goods_publisher"));
		gb.setGoods_price(Integer.parseInt(multi.getParameter("goods_price")));
		gb.setGoods_stock(Integer.parseInt(multi.getParameter("goods_stock")));
		gb.setGoods_cost(Integer.parseInt(multi.getParameter("goods_cost")));
		gb.setGoods_voice(Integer.parseInt(multi.getParameter("goods_voice")));
		gb.setGoods_lang(Integer.parseInt(multi.getParameter("goods_lang")));
		gb.setGoods_video(video1);
		gb.setGoods_content(content);
		gb.setGoods_image(filename1);
		gb.setGoods_image2(filename2);
		gb.setGoods_image3(filename3);
		gb.setGoods_image4(filename4);
		GoodsDAO gdao = new GoodsDAO();
		//bean에 값들을 db에 저장
		gdao.insertGoods(gb);
		
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('상품등록이 완료되었습니다.');");
		out.println("location.href='./AdminGoodsList.ag'");
		out.println("</script>");
		out.close();	
		 return null;
	}
}
