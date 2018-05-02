package goodsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GoodsDAO {
	private Connection getConnection() throws Exception {
		Connection con = null;
		// context.xml 사용하기 위해서 객체 생성
		// import javax.naming.Context;
		// import javax.naming.InitialContext;
		Context init = new InitialContext();
		// 메소드 호출("자원위치:자원 이름")
		// javax.sql.DataSource
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/Mysql");
		// con 변수에 DB 연결 정보를 저장한다.
		con = ds.getConnection();
		return con;
	}

	// 상품리스트의 개수를 들고온다.(관리자 리스트에서 출력되는것)
	public int getGoodsCount() {

		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			// 상품의 개수를 구한다
			sql = "select count(*) from goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// count함수에 결과값을 담는다
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {

		} finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		// 개수반환
		return count;
	}// 상품개수끝

	// 상품리스트의 개수를 가져온다.(상품리스트에 출력되는것)
	public int getGoodsCount(int platform, int type) {

		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			// 상품리스트의 개수를 출력
			sql = "select count(*) from goods where goods_platform=" + platform + " and goods_type=" + type;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// count함수에 결과값을 담는다
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {

		} finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return count;
	}// 상품개수끝
	
	public int getGoodsCount(int platform, int type,String search) {

		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			// 상품리스트의 개수를 출력
			sql = "select count(*) from goods where goods_platform=" + platform + " and goods_type=" + type+" and goods_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			// count함수에 결과값을 담는다
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {

		} finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return count;
	}// 상품개수끝
	

	
	// 상품리스트를 출력
	public List getGoodsList(int startRow, int pageSize, int platform, int type, String sort, String search,
			int searchOption) {
		List GoodsList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();

			if (search == null) {// 검색어가 없을때는 platform,type,sort만을 이용해서 리스트 출력
				sql = "select * from goods where goods_platform=" + platform + " and goods_type=" + type + " order by "
						+ sort + " limit ?,?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, startRow - 1);
				pstmt.setInt(2, pageSize);
			} else {// 검색어가 있을때
				if (searchOption == 0) {// 검색어의 분류가 0경우에는 상품이름의 검색어 출력
					sql = "select * from goods where goods_platform=" + platform + " and goods_type=" + type
							+ " and goods_name like ? order by " + sort + " limit ?,?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%" + search + "%");
					pstmt.setInt(2, startRow - 1);
					pstmt.setInt(3, pageSize);
				} else if (searchOption == 1) {// 검색어의 분류가 1경우에는 개발사의 검색어 출력
					sql = "select * from goods where goods_platform=" + platform + " and goods_type=" + type
							+ " and goods_developer like ? order by " + sort + " limit ?,?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%" + search + "%");
					pstmt.setInt(1, startRow - 1);
					pstmt.setInt(2, pageSize);
				}

			}
			rs = pstmt.executeQuery();
			while (rs.next()) {// sql결과값을 bean에 담는다.
				GoodsBean gb = new GoodsBean();
				gb.setGoods_id(rs.getInt("goods_id"));
				gb.setGoods_name(rs.getString("goods_name"));
				gb.setGoods_price(rs.getInt("goods_price"));
				gb.setGoods_stock(rs.getInt("goods_stock"));
				gb.setGoods_image(rs.getString("goods_image"));
				gb.setGoods_reg_date(rs.getDate("goods_reg_date"));
				GoodsList.add(gb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
						// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}

		}
		return GoodsList;
	}// 상품리스트 끝

	// 상품정보를 받는다
	public GoodsBean getGoods(int id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		GoodsBean gb = null;
		try {
			con = getConnection();
			//상품정보를 조회한다.
			sql = "select * from goods where goods_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			// 결과
			if (rs.next()) {
				// sql결과값을 담는다.
				gb = new GoodsBean();
				gb.setGoods_id(rs.getInt("goods_id"));
				gb.setGoods_type(rs.getString("goods_type"));
				gb.setGoods_name(rs.getString("goods_name"));
				gb.setGoods_platform(rs.getString("goods_platform"));
				gb.setGoods_developer(rs.getString("goods_developer"));
				gb.setGoods_publisher(rs.getString("goods_publisher"));
				gb.setGoods_price(rs.getInt("goods_price"));
				gb.setGoods_voice(rs.getInt("goods_voice"));
				gb.setGoods_lang(rs.getInt("goods_lang"));
				gb.setGoods_image(rs.getString("goods_image"));
				gb.setGoods_image2(rs.getString("goods_image2"));
				gb.setGoods_image3(rs.getString("goods_image3"));
				gb.setGoods_image3(rs.getString("goods_image3"));
				gb.setGoods_image4(rs.getString("goods_image4"));
				gb.setGoods_video(rs.getString("goods_video"));
				gb.setGoods_reg_date(rs.getDate("goods_reg_date"));
				gb.setGoods_stock(rs.getInt("goods_stock"));
				gb.setGoods_content(rs.getString("goods_content"));
				gb.setGoods_cost(rs.getInt("goods_cost"));

			}
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
		return gb;
	}// 상품정보끝

	// 상품등록
	public void insertGoods(GoodsBean gb) {
		String sql = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = getConnection();
			// 시퀀스 부분
			sql = "select max(goods_id) from goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}
			// 시퀀스 부분

			// bean에 저장된 값들을 sql문에 넣어준다.상품등록
			sql = "insert into goods(goods_id,goods_type,goods_name,goods_platform,goods_developer"
					+ ",goods_publisher,goods_price,goods_image,goods_image2,goods_image3,goods_image4,goods_video"
					+ ",goods_reg_date,goods_content,goods_voice,goods_lang,goods_cost,goods_stock) values(?,?,?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, gb.getGoods_type());
			pstmt.setString(3, gb.getGoods_name());
			pstmt.setString(4, gb.getGoods_platform());
			pstmt.setString(5, gb.getGoods_developer());
			pstmt.setString(6, gb.getGoods_publisher());
			pstmt.setInt(7, gb.getGoods_price());
			pstmt.setString(8, gb.getGoods_image());
			pstmt.setString(9, gb.getGoods_image2());
			pstmt.setString(10, gb.getGoods_image3());
			pstmt.setString(11, gb.getGoods_image4());
			pstmt.setString(12, gb.getGoods_video());
			pstmt.setString(13, gb.getGoods_content());
			pstmt.setInt(14, gb.getGoods_voice());
			pstmt.setInt(15, gb.getGoods_lang());
			pstmt.setInt(16, gb.getGoods_cost());
			pstmt.setInt(17, gb.getGoods_stock());
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
	}// 상품등록 끝

	// ---------------------------------------------------리뷰영역-------------------------------------------------------//

	// 리뷰넣기
	public void insertReview(ReviewBean rb) {
		String sql = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		int num = 0;
		try {
			// 시퀀스부분
			con = getConnection();
			//리뷰의 최대값을 출력
			sql = "select max(review_number) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				//최대값에서 1을더한다
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}
			// 시퀀스부분

			//리뷰등록
			sql = "insert into review(review_number,mem_id,goods_id,review_content,review_score"
					+ ",review_reg_date) values(?,?,?,?,?,now())";
			// bean에 저장된 값들을 sql문에 넣어 db에 자료를 올린다.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, rb.getMem_id());
			pstmt.setInt(3, rb.getGoods_id());
			pstmt.setString(4, rb.getReview_content());
			pstmt.setInt(5, rb.getReview_score());
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
	}// 리뷰넣기 끝

	// 리뷰리스트 출력
	public List getReviewList(int goods_id, int startRow, int pageSize) {
		List ReviewList = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();
			//상품id에 맞는 리뷰리스트를 출력한다
			sql = "select * from review where goods_id=? order by review_number desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {// bean에 sql문의 결과값들을 담는다.
				ReviewBean rb = new ReviewBean();
				rb.setGoods_id(rs.getInt("goods_id"));
				rb.setReview_number(rs.getInt("review_number"));
				rb.setMem_id(rs.getString("mem_id"));
				rb.setReview_score(rs.getInt("review_score"));
				rb.setReview_content(rs.getString("review_content"));
				rb.setReview_reg_date(rs.getDate("review_reg_date"));
				ReviewList.add(rb);
			}
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
		return ReviewList;
	}// 리뷰리스트끝
	
	// 리뷰글개수
	public int getReviewCount(int goods_id) {
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			//리뷰개수를 출력한다
			sql = "select count(*) from review where goods_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_id);
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
	}// 리뷰글개수끝
	
	// 리뷰삭제
	public void deleteReview(int review_number) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();
			//리뷰번호에 맞는 값을 삭제한다
			sql = "delete from review where review_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review_number);
			// delete 쿼리 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
				}
			}
		}
	} // 게시물 삭제 메소드 끝
}
