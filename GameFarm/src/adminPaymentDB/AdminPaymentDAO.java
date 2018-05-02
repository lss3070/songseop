package adminPaymentDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import memberDB.MemberBean;
import paymentDB.PaymentBean;






public class AdminPaymentDAO {

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
	
		
	public List<PaymentBean> getAdminOrderList(int startRow, int pageSize) {	// 관리자용 주문목록 조회
		List<PaymentBean> List = new ArrayList<PaymentBean>();
		// 자바 예외 처리 코드
		try {

			con=getConnection();

			sql = "select p.payment_id,p.order_date,g.goods_name,g.goods_image,p.mem_id,p.order_volume,p.payment_price,p.order_status,p.order_waybill"
					+" from goods g,payment p,member m where p.goods_id = g.goods_id and p.mem_id=m.mem_id order by p.payment_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow - 1);
			pstmt.setInt(2, pageSize);
	
			rs=pstmt.executeQuery();
			
			while (rs.next()) {
				PaymentBean ob = new PaymentBean();
				
				ob.setMem_id(rs.getString("p.mem_id"));
				ob.setPayment_id(rs.getInt("p.payment_id"));
				ob.setOrder_date(rs.getTimestamp("p.order_date"));
				ob.setGoods_name(rs.getString("g.goods_name"));
				ob.setGoods_image(rs.getString("g.goods_image"));
				ob.setOrder_volume(rs.getInt("p.order_volume"));
				ob.setPayment_price(rs.getInt("p.payment_price"));
				ob.setOrder_status(rs.getInt("p.order_status"));
				ob.setOrder_waybill(rs.getString("p.order_waybill"));
//				cb.set//상품이미지
//				cb.set//상품개수
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
	}	// 관리자용 주문게시판 조회 메소드의 끝
	
	public int getOrderCount() {
		
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = getConnection();
			sql = "select count(*) from payment";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			count = rs.getInt("count(*)");
			
			return count;
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
		return count;	
	}//관리자용 주문게시판 조회 글수 메소드
	
	
	public List<PaymentBean> getSearchList(int startRow, int pageSize, String search, int searchOption) {	// 검색된 회원 목록을 조회하는 메소드
		List<PaymentBean> pList = new ArrayList<PaymentBean>();
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 검색어에 필터링된 게시물들을 각 옵션에 따라 조회한다.			
			if (searchOption==0){		// searchOption이 0이면 상품명
			// 검색값을 조회한다.
			sql = "select p.payment_id,p.order_date,g.goods_name,g.goods_image,p.mem_id,p.order_volume,p.payment_price,p.order_status,p.order_waybill"
					+" from goods g,payment p,member m where p.goods_id = g.goods_id and p.mem_id=m.mem_id and g.goods_name like ? "
					+ "order by p.payment_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2,  startRow-1);
			pstmt.setInt(3,  pageSize);
			}			
			if (searchOption==1){	// searchOption이 1이면 회원아이디
				//검색어 테이블 제어 
				sql = "select p.payment_id,p.order_date,g.goods_name,g.goods_image,p.mem_id,p.order_volume,p.payment_price,p.order_status,p.order_waybill"
						+" from goods g,payment p,member m where p.goods_id = g.goods_id and p.mem_id=m.mem_id and p.mem_id like ? "
						+ "order by p.payment_id desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2,  startRow-1);
				pstmt.setInt(3,  pageSize);
			}							
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				PaymentBean ob = new PaymentBean();
				
				ob.setMem_id(rs.getString("p.mem_id"));
				ob.setPayment_id(rs.getInt("p.payment_id"));
				ob.setOrder_date(rs.getTimestamp("p.order_date"));
				ob.setGoods_name(rs.getString("g.goods_name"));
				ob.setGoods_image(rs.getString("g.goods_image"));
				ob.setOrder_volume(rs.getInt("p.order_volume"));
				ob.setPayment_price(rs.getInt("p.payment_price"));
				ob.setOrder_status(rs.getInt("p.order_status"));
				ob.setOrder_waybill(rs.getString("p.order_waybill"));
//				cb.set//상품이미지
//				cb.set//상품개수
				pList.add(ob);
			}
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
		return pList;
	}	// 검색된 회원 목록을 조회하는 메소드 끝

	public int getSearchCount(String search, int searchOption) {	// 검색된 회원의 수를 구하는 메소드
		int count = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// searchOption이 0이면 제목
			if (searchOption==0){
				// 상품명을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from goods g,payment p,member m "
						+ "where p.goods_id = g.goods_id and p.mem_id=m.mem_id and g.goods_name like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}
			// searchOption이 1이면 내용
			if (searchOption==1){
				// 회원아이디를 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from goods g,payment p,member m "
						+ "where p.goods_id = g.goods_id and p.mem_id=m.mem_id and p.mem_id like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}

			// Resultset으로 쿼리를 실행한다.
			rs=pstmt.executeQuery();
			// 첫번째 열의 값이 게시판 글 개수이다.
			rs.next();
			// 조회된 개수를 count 변수에 저장한다.
			count = rs.getInt("count(*)");
			// count 값을 반환한다.
			return count;
		} catch (Exception e) {
			// TODO: handle exception
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
		return count;		
	}	// 검색된 회원의 수를 구하는 메소드 끝
	
	public void UpdatePost(int order_status, String order_waybill,int payment_id) {
		try {
			con=getConnection();
			// searchOption이 0이면 제목
				sql = "update payment set order_status=?,order_waybill=? where payment_id=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, order_status);
				pstmt.setString(2, order_waybill);
				pstmt.setInt(3, payment_id);

				pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
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
	
	}	
}
