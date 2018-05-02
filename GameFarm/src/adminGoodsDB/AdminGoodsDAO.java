package adminGoodsDB;

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
import goodsDB.GoodsBean;

public class AdminGoodsDAO {
	private Connection getConnection() throws Exception {
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

	public List<GoodsBean> getNewGoodsList(int platform) {	// 메인 화면에 띄워줄 신상품 3개 조회
		// 변수 지정
		List<GoodsBean> newGoodsList = new ArrayList<GoodsBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		try {
			con = getConnection();
			sql = "select * from goods where goods_platform=? order by goods_id desc limit 0,6";
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1, platform);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GoodsBean gb = new GoodsBean();	
				gb.setGoods_id(rs.getInt("goods_id"));
				gb.setGoods_platform(rs.getString("goods_platform"));
				gb.setGoods_image(rs.getString("goods_image"));
				gb.setGoods_name(rs.getString("goods_name"));
				gb.setGoods_price(rs.getInt("goods_price"));
				gb.setGoods_reg_date(rs.getDate("goods_reg_date"));
				gb.setGoods_developer(rs.getString("goods_developer"));
				gb.setGoods_publisher(rs.getString("goods_publisher"));
				gb.setGoods_type(rs.getString("goods_type"));
				newGoodsList.add(gb);
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
		return newGoodsList;
	}	// 메인 화면에 띄워줄 신상품 3개 조회
	
	public List getAdminGoodsList(int startRow, int pageSize){ 	// 관리자가 보는 상품 목록
		 List gList = new ArrayList();
		 Connection con = null;
		 PreparedStatement pstmt = null;
	     String sql = "";
		 ResultSet rs = null;
		 try {
			 	con=getConnection();				
				sql="select * from goods order by goods_id desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,  startRow-1);
				pstmt.setInt(2,  pageSize);				
				rs=pstmt.executeQuery();				
				while(rs.next()){
					GoodsBean gb=new GoodsBean();
					gb.setGoods_id(rs.getInt("goods_id"));
					gb.setGoods_type(rs.getString("goods_type"));
					gb.setGoods_image(rs.getString("goods_image"));
					gb.setGoods_name(rs.getString("goods_name"));
					gb.setGoods_cost(rs.getInt("goods_cost"));
					gb.setGoods_price(rs.getInt("goods_price"));
					gb.setGoods_stock(rs.getInt("goods_stock"));
					gb.setGoods_platform(rs.getString("goods_platform"));
					gb.setGoods_reg_date(rs.getDate("goods_reg_date"));
					gList.add(gb);
				}				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		 return gList;
	} 	// 관리자가 보는 상품 목록
	
	public int getSearchCount(String search) {	//		관리자가 보는 상품 목록의 개수를 조회 
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		int count = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();			
			// 상품명을 검색하여 출력되는 게시물의 개수를 조회한다.
			sql = "select count(*) from goods where goods_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
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
	}	//		관리자가 보는 상품 목록의 개수를 조회
	
	public List<GoodsBean> getSearchList(int startRow, int pageSize, String search) {	//	 검색된 상품 목록을 조회하는 메소드
		List<GoodsBean> gList = new ArrayList<GoodsBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 검색어에 필터링된 게시물들을 각 옵션에 따라 조회한다.								
		    //검색어 테이블 제어 
			// 검색값을 조회한다.
			sql = "select * from goods where goods_name like ? order by goods_id desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2,  startRow-1);
			pstmt.setInt(3,  pageSize);					
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				GoodsBean gb = new GoodsBean();
				// mb 데이터에 값을 저장
				gb.setGoods_id(rs.getInt("goods_id"));
				gb.setGoods_type(rs.getString("goods_type"));
				gb.setGoods_image(rs.getString("goods_image"));
				gb.setGoods_name(rs.getString("goods_name"));
				gb.setGoods_cost(rs.getInt("goods_cost"));
				gb.setGoods_price(rs.getInt("goods_price"));
				gb.setGoods_stock(rs.getInt("goods_stock"));
				gb.setGoods_platform(rs.getString("goods_platform"));
				gb.setGoods_reg_date(rs.getDate("goods_reg_date"));
				// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				gList.add(gb);
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
				return gList;
	}	//	 검색된 상품 목록을 조회하는 메소드
	
	public void updateGoods(GoodsBean gb) throws SQLException {	// update(GoodsBean gb) 메소드 //0420박강원
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			sql = "update goods set goods_name=?, goods_type=?, goods_platform=?, goods_lang=?, "
					+ "goods_voice=?, goods_developer=?, goods_publisher=?, goods_price=?, "
					+ "goods_video=?, goods_image=?, goods_image2=?, goods_image3=?, "
					+ "goods_image4=?, goods_content=?, goods_stock=?, goods_cost=? where goods_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gb.getGoods_name());
			pstmt.setString(2, gb.getGoods_type());
			pstmt.setString(3, gb.getGoods_platform());
			pstmt.setInt(4, gb.getGoods_lang());
			pstmt.setInt(5, gb.getGoods_voice());
			pstmt.setString(6, gb.getGoods_developer());
			pstmt.setString(7, gb.getGoods_publisher());
			pstmt.setInt(8, gb.getGoods_price());
			pstmt.setString(9, gb.getGoods_video());
			pstmt.setString(10, gb.getGoods_image());
			pstmt.setString(11, gb.getGoods_image2());
			pstmt.setString(12, gb.getGoods_image3());
			pstmt.setString(13, gb.getGoods_image4());
			pstmt.setString(14, gb.getGoods_content());
			pstmt.setInt(15, gb.getGoods_stock());
			pstmt.setInt(16, gb.getGoods_cost());
			pstmt.setInt(17, gb.getGoods_id());			
			// update 쿼리 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
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
	} // updateGoods 메소드의 끝

	public void deleteGoods(int goods_id) { // delete(int goods_id) 메소드 //0420박강원
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			sql = "delete from goods where goods_id=?";
			pstmt = con.prepareStatement(sql);
			/*pstmt.setString(1, mb.getMem_id());*/
			pstmt.setInt(1, goods_id);
			// delete 쿼리 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
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
	} // deleteGoods 메소드의 끝
	
	
	
	public void updateStock(GoodsBean gb){	// 결제 후 재고수 감소
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			// 주문한 goods_id별 재고수 감소
			sql = "update goods set goods_stock=? where goods_id=?";			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gb.getGoods_stock());	
			pstmt.setInt(2, gb.getGoods_id());
			// update 쿼리 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
		
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
	}
	public int selectStock(int goods_id){
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		int stock=0;
		try{
			con = getConnection();
			// 주문한 goods_id별 재고수 감소
			sql = "select goods_stock from goods where goods_id=?";			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_id);	
	
			rs = pstmt.executeQuery();

			if (rs.next()) {
				stock = rs.getInt(1);
			}
		}catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
		
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
		return stock;
	}
	public void updateStock(int goods_stock,int goods_id){	// 결제 후 재고수 감소
		Connection con = null;
		PreparedStatement pstmt = null;
	    String sql = "";
		ResultSet rs = null;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			// 주문한 goods_id별 재고수 감소
			sql = "update goods set goods_stock=? where goods_id=?";			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goods_stock);	
			pstmt.setInt(2, goods_id);
			// update 쿼리 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
		
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
	}
}
