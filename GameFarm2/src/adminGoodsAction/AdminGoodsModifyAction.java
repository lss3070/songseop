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

import adminGoodsDB.AdminGoodsDAO;
import goodsDB.GoodsBean;

public class AdminGoodsModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdGoodsModifyAction excute()");
		//한글 출력이 가능하도록!
		request.setCharacterEncoding("utf-8");
		// 파일이 있는 게시판이므로 multi 객체를 가져올 준비를 한다.
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("업로드폴더:"+realPath);
		// 파일 크기 제한: 30메가
		int maxSize=30*1024*1024;
		// MultipartRequest multi = new MultipartRequest(request, 파일을 업로드할 물리적 경로 폴더, 파일 크기, 한글 처리, 업로드하는 파일 이름이 동일할 경우 이름 변경 파일 생성);
		MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());		
		// AdminGoodsDAO 객체생성
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		// 입력한 상품 정보를 불러오기 위해 GoodsBean 객체를 생성한다.
		GoodsBean gb = new GoodsBean();
		
		//유튜브영상올리는것
		String goods_video = multi.getParameter("goods_video");
		int idx = goods_video.indexOf("be/");
		goods_video = goods_video.substring(idx+3);
		
		String goods_image = multi.getFilesystemName("goods_image");
		String goods_image2 = multi.getFilesystemName("goods_image2");
		String goods_image3 = multi.getFilesystemName("goods_image3");
		String goods_image4 = multi.getFilesystemName("goods_image4");
		String goods_content = multi.getFilesystemName("goods_content");
		
		//ParameterBlock클래스에 변환할 이미지를 담고 
		//RenderedOp로 저장된 이미지를 불러옴
	
		//100*100으로 지정  bi로 저장
		//여기서 크기변경
		//불러온 이미지를 bi로 생성한 bufferedimage에 담는다
		//정해진 버퍼사이즈에 맞춰서 드로우
		//이미지를 넣어서 드로우		 
		//일반 이미지저장
		
		if(goods_image!=null){
			ParameterBlock pb=new ParameterBlock();
			pb.add(realPath+"/"+goods_image);
			RenderedOp rOp=JAI.create("fileload",pb);
			BufferedImage bi= rOp.getAsBufferedImage();
			File image = new File(realPath+"/"+goods_image);
			ImageIO.write(bi,"jpg",image);
			gb.setGoods_image(multi.getFilesystemName(goods_image));
		}else {
			goods_image = multi.getParameter("goods_image_1");
			gb.setGoods_image(goods_image);
		}		
		
		if(goods_image2!=null){
			ParameterBlock pb2=new ParameterBlock();
			pb2.add(realPath+"/"+goods_image2);
			RenderedOp rOp2=JAI.create("fileload",pb2);
			BufferedImage bi2= rOp2.getAsBufferedImage();
			BufferedImage thumb2=new BufferedImage(720,400,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2=thumb2.createGraphics();
			g2.drawImage(bi2,0,0,720,400,null);
			File image2 = new File(realPath+"/"+goods_image2);
			ImageIO.write(thumb2,"jpg",image2);
			gb.setGoods_image2(multi.getFilesystemName(goods_image2));
		}else{
			goods_image2 = multi.getParameter("goods_image2_1");
			gb.setGoods_image2(goods_image2);
		}
	
		if(goods_image3!=null){
			ParameterBlock pb3=new ParameterBlock();
			pb3.add(realPath+"/"+goods_image3);
			RenderedOp rOp3=JAI.create("fileload",pb3);
			BufferedImage bi3= rOp3.getAsBufferedImage();
			BufferedImage thumb3=new BufferedImage(720,400,BufferedImage.TYPE_INT_RGB);
			Graphics2D g3=thumb3.createGraphics(); 
			g3.drawImage(bi3,0,0,720,400,null); 
			File image3 = new File(realPath+"/"+goods_image3);
			ImageIO.write(thumb3,"jpg",image3); 
			gb.setGoods_image3(multi.getFilesystemName(goods_image3));
		}else{
			goods_image3 = multi.getParameter("goods_image3_1");
			gb.setGoods_image3(goods_image3);
		}
		
		
		if(goods_image4!=null){
			ParameterBlock pb4=new ParameterBlock();
			pb4.add(realPath+"/"+goods_image4);
			RenderedOp rOp4=JAI.create("fileload",pb4);
			BufferedImage bi4= rOp4.getAsBufferedImage();
			BufferedImage thumb4=new BufferedImage(720,400,BufferedImage.TYPE_INT_RGB);
			Graphics2D g4=thumb4.createGraphics(); 
			g4.drawImage(bi4,0,0,720,400,null);
			File image4 = new File(realPath+"/"+goods_image4);
			ImageIO.write(thumb4,"jpg",image4); 
			gb.setGoods_image4(multi.getFilesystemName(goods_image4));
		}else{
			goods_image4 = multi.getParameter("goods_image4_1");
			gb.setGoods_image4(goods_image4);
		}

		
		if(goods_content!=null){
			ParameterBlock pbc=new ParameterBlock();
			pbc.add(realPath+"/"+ goods_content);
			RenderedOp rOpc=JAI.create("fileload",pbc);
			BufferedImage bic= rOpc.getAsBufferedImage();
			File imagec = new File(realPath+"/"+goods_content);
			ImageIO.write(bic,"jpg",imagec);
			gb.setGoods_content(multi.getFilesystemName(goods_content));
		}else{
			goods_content = multi.getParameter("goods_content_1");
			gb.setGoods_content(goods_content);
			
		}

		
		
		
		//goods_id 파라미터 가져오기
		int goods_id = Integer.parseInt(multi.getParameter("goods_id"));
		String goods_type = multi.getParameter("goods_type");
		String goods_name = multi.getParameter("goods_name");
		String goods_platform = multi.getParameter("goods_platform");
		String goods_developer = multi.getParameter("goods_developer");
		String goods_publisher = multi.getParameter("goods_publisher");
		int goods_price = Integer.parseInt(multi.getParameter("goods_price"));
		int goods_voice = Integer.parseInt(multi.getParameter("goods_voice"));
		int goods_lang = Integer.parseInt(multi.getParameter("goods_lang"));
		int goods_stock = Integer.parseInt(multi.getParameter("goods_stock"));
		int goods_cost = Integer.parseInt(multi.getParameter("goods_cost"));
		
		
		
		
		gb.setGoods_id(goods_id);
		gb.setGoods_type(goods_type);
		gb.setGoods_name(goods_name);
		gb.setGoods_platform(goods_platform);
		gb.setGoods_developer(goods_developer);
		gb.setGoods_publisher(goods_publisher);
		gb.setGoods_price(goods_price);
		gb.setGoods_voice(goods_voice);
		gb.setGoods_lang(goods_lang);		
		gb.setGoods_image(goods_image);
		gb.setGoods_image2(goods_image2);
		gb.setGoods_image3(goods_image3);
		gb.setGoods_image4(goods_image4);
		gb.setGoods_content(goods_content);
		gb.setGoods_stock(goods_stock);
		gb.setGoods_cost(goods_cost);
		gb.setGoods_video(goods_video);
		//updateMember(자바빈 주소값) 실행 -> 입력받은 상품 값들을 DB에 update한다.				
		agdao.updateGoods(gb);
		
		// 자바 스크립트 사용
		// 자바 -> text/html 변경
		response.setContentType("text/html; charset=UTF-8");
		// 스크립트를 출력할 객체 생성
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('상품정보를 수정하였습니다.');");
		out.println("location.href='./AdminGoodsList.ag'");
		out.println("</script>");
		out.close();		
		return null;
		
	}
	
}
