package paymentDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import goodsDB.GoodsBean;



public class PaymentDAO {

	private Connection getConnection() throws Exception { // DB를 불러오는 메소드
		/*
		// DB 주소
		String dbUrl = "jdbc:mysql://localhost:3306/jspdb";
		// DB유저
		String dbUser = "jspMem_id";
		// DB비밀번호
		String dbMem_pass = "jspMem_pass";
		// DriverManager를 변수에 저장
		Connection con = null;
		// 1단계 드라이버 가져오기
		Class.forName("com.mysql.jdbc.Driver");
		// 2단계 DB 연결
		con = DriverManager.getConnection(dbUrl, dbUser, dbMem_pass);
		return con;
		*/
		
		// 커넥션 풀(Connection Pool)
		// : 데이터베이스와 연결된 Connection 객체를 미리 생성하여
		//	  풀(Pool)속에 저장해두고 필요할 때마다 풀에 접근하여 
		//   Connection 객체를 사용하고 작업이 끝나면 다시 반환하는 것
		//   프로그램 효율과 성능이 전체적으로 증가
		
		// 자카르타 DBCP API 이용 => 커넥션 풀 중 하나 사용		(아파치 8.0 버전 이상부터는 다운받지 않아도 된다.)
		// commons.apache.org	  => 	    DBCP		=>	  binary (zip 파일 다운로드)		=> 	 commons-dbcp2-2.2.0.jar 파일을 사용
		//									  => 	    Collections		=> 	 binary (zip 파일 다운로드)	=>	 commons-collections4-4.1.jar 파일을 사용
		//									  =>		Pool			=>	  binary (zip 파일 다운로드)		=> 	 commons-pool2-2.5.0.jar 파일을 사용
		
		// 1. WebContent\META-INF		context.xml 추가
		// 2. WebContent\WEB-INF		web.xml 수정
		// 3. MemberDB 사용
		
		Connection con = null;
		// context.xml 사용하기 위해서 객체 생성
		// import javax.naming.Context;
		// import javax.naming.InitialContext;
		Context init = new InitialContext();
		// 메소드 호출("자원위치:자원 이름")
		// javax.sql.DataSource
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		// con 변수에 DB 연결 정보를 저장한다.
		con = ds.getConnection();
		return con;
	}
	// DB 호출값 초기화
	Connection con = null;
	// pstmt 변수 초기화
	PreparedStatement pstmt = null;
	// sql 변수 초기화(sql 구문 초기화)
	String sql = "";
	// ResultSet 값을 초기화한다.
	ResultSet rs = null;
	
	
	
	
	//주문조회 리스트 출력
	public List<PaymentBean> getOrderList(String Mem_id,int startRow, int pageSize) {	// 주문목록 조회
		List<PaymentBean> List = new ArrayList<PaymentBean>();
		try {
			con=getConnection();
			//회원에 대한 주문조회
			sql = "select p.payment_id,p.order_date,g.goods_name,g.goods_image,p.mem_id,p.order_volume,p.payment_price,"
					+ "p.order_status,g.goods_platform,g.goods_type,p.receiver_name, p.receiver_post, p.receiver_address1,p.receiver_address2,g.goods_id"
					+" from goods g,payment p,member m where p.goods_id = g.goods_id and p.mem_id=m.mem_id and p.mem_id=? limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Mem_id);
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);
			rs=pstmt.executeQuery();
			
			//결과 값들을 반복으로 bean에 저장
			while (rs.next()) {
				PaymentBean ob = new PaymentBean();
				ob.setPayment_id(rs.getInt("p.payment_id"));
				ob.setOrder_date(rs.getTimestamp("p.order_date"));
				ob.setGoods_name(rs.getString("g.goods_name"));
				ob.setGoods_image(rs.getString("g.goods_image"));
				ob.setOrder_volume(rs.getInt("p.order_volume"));
				ob.setPayment_price(rs.getInt("p.payment_price"));
				ob.setOrder_status(rs.getInt("p.order_status"));
				ob.setPlatform(rs.getInt("g.goods_platform"));
				ob.setType(rs.getInt("g.goods_type"));
				ob.setReceiver_name(rs.getString("p.receiver_name"));
				ob.setReceiver_post(rs.getInt("p.receiver_post"));
				ob.setReceiver_address1(rs.getString("p.receiver_address1"));
				ob.setReceiver_address2(rs.getString("p.receiver_address2"));
				ob.setGoods_id(rs.getInt("g.goods_id"));
				List.add(ob);
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException ex) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
		}	
		return List;
	}	// 주문 조회 메소드의 끝
	
//주문된 상품의 개수
	public int getOrderCount(String Mem_id) {
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			//회원의 주문된 상품의 개수 조회
			sql = "select count(*) from payment where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Mem_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {

		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return count;
	}//개수끝
	
//결제기능	
	public void insertPayment(PaymentBean pb,String mem_id) {
		String sql = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs =null;
		int num=0;
		
		try {
			//시퀀스
			con = getConnection();
			sql="select max(payment_id) from payment";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}else{
				num=1;
			}
			//시퀀스끝
			
			sql = "insert into payment(payment_id,mem_id,goods_id,receiver_post,receiver_address1,receiver_address2,receiver_name"
					+ ",receiver_phone1,receiver_phone2,receiver_phone3,order_volume,payment_price,order_status,order_date) values(?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			//bean의 값들을 sql문에 저장
			pstmt.setInt(1, num);
			pstmt.setString(2, mem_id);
			pstmt.setInt(3, pb.getGoods_id());
			pstmt.setInt(4, pb.getReceiver_post());
			pstmt.setString(5, pb.getReceiver_address1());
			pstmt.setString(6, pb.getReceiver_address2());
			pstmt.setString(7, pb.getReceiver_name());
			pstmt.setString(8, pb.getReceiver_phone1());
			pstmt.setString(9, pb.getReceiver_phone2());
			pstmt.setString(10, pb.getReceiver_phone3());
			pstmt.setInt(11, pb.getOrder_volume());
			pstmt.setInt(12, pb.getPayment_price());
			pstmt.setInt(13, pb.getOrder_status());
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}

		}
	}//결제기능끝
	
	//구매확정시삭제
	public void deletePayment(int payment_id) {
		String sql = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs =null;
		try {
			con=getConnection();
	    	sql = "delete from payment where payment_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, payment_id);
			// delete 쿼리 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
        	// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
				e.printStackTrace();
        } finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException ex) {}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException ex) {}
			}
        }		
}//삭제
}
