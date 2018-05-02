package newsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class NewsDAO {

	// 필요한 변수들을 생성한다.
		// DB 호출변수 con 초기화
		Connection con = null;
		// pstmt 변수 초기화
		PreparedStatement pstmt = null;
		// sql 변수 초기화(sql 구문 초기화)
		String sql = "";
		// ResultSet 값을 초기화한다.
		ResultSet rs = null;
	
	//디비연결 메서드
	private Connection getConnection() throws Exception{
		Connection con=null;
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}
	//getNewsCount() 함수만들기
	public int getNewsCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql news모든 데이터 가져와서 개수 카운트
			sql="select count(*) from news";
			pstmt=con.prepareStatement(sql);
			//4 rs <= 실행
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면 count=가져온개수 저장
			if(rs.next()){
				count=rs.getInt(1); //1열 데이터 가져오기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	public int getNewsCount(String search){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql news모든 데이터 가져와서 개수 카운트   '%검색어%'
			sql="select count(*) from news where news_subject like ?";
			pstmt=con.prepareStatement(sql);
			// '%검색어%'
			pstmt.setString(1, "%"+search+"%");
			//4 rs <= 실행
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면 count=가져온개수 저장
			if(rs.next()){
				count=rs.getInt(1); //1열 데이터 가져오기
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return count;
	}
	
	public List getMainNewsList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List newsList=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			sql="select * from news order by news_number desc limit 0,3";
			pstmt=con.prepareStatement(sql);			
			//4 rs <= 실행결과저장
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면
			//  패키지 news 파일이름 NewsBean
			// 자바빈 객체 생성  NewsBean nb
			// 자바빈 멤버변수 <= rs 가져온 열 저장
			// newsList 배열 한칸에 저장
			while(rs.next()){
				NewsBean nb=new NewsBean();
				nb.setNews_image(rs.getString("news_image"));
				nb.setNews_number(rs.getInt("news_number"));
				newsList.add(nb);
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
		return newsList;
	}
	
	//getNewsList(시작행번호, 가져올글개수)
	public List getNewsList(int startRow, int pageSize){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		List newsList=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			sql="select * from news order by news_number desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4 rs <= 실행결과저장
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면
			//  패키지 news 파일이름 NewsBean
			// 자바빈 객체 생성  NewsBean nb
			// 자바빈 멤버변수 <= rs 가져온 열 저장
			// newsList 배열 한칸에 저장
			while(rs.next()){
				NewsBean nb=new NewsBean();
				nb.setNews_number(rs.getInt("news_number"));
				nb.setNews_subject(rs.getString("news_subject"));
				nb.setMem_id(rs.getString("mem_id"));
				nb.setNews_content(rs.getString("news_content"));				
				nb.setNews_reg_date(rs.getTimestamp("news_reg_date")); //nb.setNews_reg_date(rs.getTimestamp("news_reg_date"));
				nb.setNews_readcount(rs.getInt("news_readcount"));
				newsList.add(nb);
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
		return newsList;
	}
	
	//getNewsList(시작행번호, 가져올글개수)
		public List getNewsList(int startRow, int pageSize, String search){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="";
			List newsList=new ArrayList();
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql
				sql="select * from news where news_subject like ? order by news_number desc limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow-1);
				pstmt.setInt(3, pageSize);
				//4 rs <= 실행결과저장
				rs=pstmt.executeQuery();
				//5 rs 첫행이동 데이터 있으면
				//  패키지 news 파일이름 NewsBean
				// 자바빈 객체 생성  NewsBean nb
				// 자바빈 멤버변수 <= rs 가져온 열 저장
				// newsList 배열 한칸에 저장
				while(rs.next()){
					NewsBean nb=new NewsBean();
					nb.setNews_number(rs.getInt("news_number"));
					nb.setNews_subject(rs.getString("news_subject"));
					nb.setMem_id(rs.getString("mem_id"));
					nb.setNews_content(rs.getString("news_content"));					
					nb.setNews_reg_date(rs.getTimestamp("news_reg_date"));
					nb.setNews_readcount(rs.getInt("news_readcount"));
					newsList.add(nb);
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
			return newsList;
		}
		
		//getNews(num)
		public NewsBean getNews(int num){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql="";
			NewsBean nb=null;
			try {
				//1,2 디비연결
				con=getConnection();
				//3 sql news 모든 정보 가져오기 조건 num=?
				sql="select * from news where news_number=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//4 rs = 실행 저장
				rs=pstmt.executeQuery();
				//5  rs 첫행이동 데이터 있으면 저장된 내용을=> 자바빈 저장 
				//   자바빈 객체 생성
				//   자바빈 변수 <= rs 
				if(rs.next()){
					nb=new NewsBean();
					nb.setNews_number(rs.getInt("news_number"));
					nb.setNews_subject(rs.getString("news_subject"));
					nb.setMem_id(rs.getString("mem_id"));
					nb.setNews_content(rs.getString("news_content"));
					nb.setNews_image(rs.getString("news_image"));
					nb.setNews_reg_date(rs.getTimestamp("news_reg_date"));
					nb.setNews_readcount(rs.getInt("news_readcount"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null)
					try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)
					try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)
					try{con.close();}catch(SQLException ex){}
			}
			return nb;
		}
		
		//insertNews(자바빈 주소)
		public void insertNews(NewsBean nb){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			
			// 현재 게시물 번호를 위한 변수 생성
			int n=0;
			try {
				//1단계 드라이버로더
				//2단계 디비연결
				con=getConnection();
				//게시판 글번호 구하기  max(num)
				//3단계 sql  게시판 글중에 가장큰 게시판 번호구하기=가장 마지막 게시물의 번호를 구하기 위한 쿼리
				sql="select max(news_number) from news";
				pstmt=con.prepareStatement(sql);
				//4단계 결과 저장 <= 실행
				rs=pstmt.executeQuery();
				//5단계 첫행 이동 데이터 있으면  결과 +1
				if(rs.next()){
				   n=rs.getInt("max(news_number)")+1;
				} else {	// 게시물이 하나도 없을 때(게시물의 번호가 0이 되면 안되므로 1로 지정해준다.)
					n = 1;
				}
				//3단계 sql  insert 
				sql="insert into news(news_number,news_subject,mem_id,news_content,news_image,news_readcount,news_reg_date) values(?,?,?,?,?,?,now())";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, n);//글번호
				pstmt.setString(2, nb.getNews_subject()); //제목
				pstmt.setString(3, nb.getMem_id());//아이디
				pstmt.setString(4, nb.getNews_content());//내용
				pstmt.setString(5, nb.getNews_image());//이미지
				pstmt.setInt(6, 0); //조회수 0
				//4단계 실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//닫기
				if(rs!=null)
					try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)
					try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)
					try{con.close();}catch(SQLException ex){}
			}
		}
		
		
		//updateNews(nb)
		public void updateNews(NewsBean nb){
			Connection con=null;
			PreparedStatement pstmt=null;
			String sql="";
			ResultSet rs=null;
			try {
				//1,2 디비연결
				con=getConnection();
				//3  sql 게시판글 가져오기 조건 num=?
				sql="select * from news where news_number=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, nb.getNews_number());
				//4 rs = 실행해서 결과 저장
				rs=pstmt.executeQuery();
				//5 rs 첫행이동 데이터있으면 게시판 글있음 
				//    폼아이디  디비아이디 비교 틀리면 check=0
				//	        //폼아이디 admin 비교 맞으면 check=1
				//          	//3 sql update //4 실행
				//    비밀번호 틀리면 check=0
				if(rs.next()){
					if(nb.getMem_id().equals(rs.getString("mem_id"))){
						if(nb.getMem_id().equals("admin")){
							if(nb.getNews_image()==null){
								//3
								sql="update news set NEWS_SUBJECT=?,NEWS_CONTENT=?  where news_number=?";
								pstmt=con.prepareStatement(sql);
								pstmt.setString(1, nb.getNews_subject());
								pstmt.setString(2, nb.getNews_content());
								pstmt.setInt(3, nb.getNews_number());
								//4
								pstmt.executeUpdate();
							}else{
								//3
								sql="update news set NEWS_SUBJECT=?,NEWS_CONTENT=?,news_image=?  where news_number=?";
								pstmt=con.prepareStatement(sql);
								pstmt.setString(1, nb.getNews_subject());
								pstmt.setString(2, nb.getNews_content());
								pstmt.setString(3, nb.getNews_image());
								pstmt.setInt(4, nb.getNews_number());
								//4
								pstmt.executeUpdate();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//닫기
				if(rs!=null)
					try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)
					try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)
					try{con.close();}catch(SQLException ex){}
			}
		}
				
				//deleteNews(nb)
				public void deleteNews(int news_number) throws SQLException {
					Connection con=null;
					PreparedStatement pstmt=null;
					String sql="";
					ResultSet rs=null;
					try {
						// getConnection() 메소드 가져와서 con 변수에 넣기
						con=getConnection();
				    	sql = "delete from news where news_number=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, news_number);
						// delete 쿼리 실행
						pstmt.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						//닫기
						if(rs!=null)
							try{rs.close();}catch(SQLException ex){}
						if(pstmt!=null)
							try{pstmt.close();}catch(SQLException ex){}
						if(con!=null)
							try{con.close();}catch(SQLException ex){}
					}
				}
			
				
				public int getSearchCount(String search, int searchOption) {	// 게시물의 갯수를 반환해주는 메소드
					int count = 0;
					try {
						// getConnection() 메소드 가져와서 con 변수에 넣기
						con=getConnection();
						// searchOption이 0이면 제목
						if (searchOption==0){
							// 제목을 검색하여 출력되는 게시물의 개수를 조회한다.
							sql = "select count(*) from news where news_subject like ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+search+"%");
						}
						// searchOption이 1이면 내용
						if (searchOption==1){
							// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
							sql = "select count(*) from news where news_content like ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+search+"%");
						}
						// searchOption이 2이면 제목+내용
						if (searchOption==2){
							// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
							sql = "select count(*) from news where news_content like ? or news_subject like ?";
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, "%"+search+"%");
							pstmt.setString(2, "%"+search+"%");
						}
						// searchOption이 3이면 작성자
						if (searchOption==3){
							// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
							sql = "select count(*) from news where mem_id like ?";
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
				}	// getSearchCount() 메소드의 끝
			
				
				public List<NewsBean> getSearchList(int startRow, int pageSize, String search, int searchOption) {	// 검색된 게시물을 조회하는 메소드
					List<NewsBean> nList = new ArrayList<NewsBean>();
					// 자바 예외 처리 코드
					try {
						// 예외가 발생할 것 같은 코드 -> DB 연결
						// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
						con=getConnection();
						// 검색어에 필터링된 게시물들을 각 옵션에 따라 조회한다.			
						if (searchOption==0){		// searchOption이 0이면 제목
							//검색어 테이블 제어 
							// 검색값을 조회한다.
							sql = "select * from news where news_subject like ? order by news_number desc limit ?,?";
							pstmt = con.prepareStatement(sql);
							// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
							pstmt.setString(1, "%"+search+"%");
							pstmt.setInt(2,  startRow-1);
							pstmt.setInt(3,  pageSize);
						}
						// searchOption이 1이면 내용
						if (searchOption==1){
							//검색어 테이블 제어 
							sql = "select * from news where news_content like ? order by news_number desc limit ?,?";
							pstmt = con.prepareStatement(sql);
							// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
							pstmt.setString(1, "%"+search+"%");
							pstmt.setInt(2,  startRow-1);
							pstmt.setInt(3,  pageSize);
						}
						// searchOption이 2이면 제목+내용
						if (searchOption==2){
							// 검색어 테이블 제어 
							sql = "select * from news where news_subject like ? or news_content like ? order by news_number desc limit ?,?";
							pstmt = con.prepareStatement(sql);
							// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
							pstmt.setString(1, "%"+search+"%");
							pstmt.setString(2, "%"+search+"%");
							pstmt.setInt(3,  startRow-1);
							pstmt.setInt(4,  pageSize);
						}
						// searchOption이 3이면 작성자
						if (searchOption==3){
							// 검색어 테이블 제어 
							sql = "select * from news where mem_id like ? order by news_number desc limit ?,?";
							pstmt = con.prepareStatement(sql);
							// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
							pstmt.setString(1, "%"+search+"%");
							pstmt.setInt(2,  startRow-1);
							pstmt.setInt(3,  pageSize);
						}
						// 쿼리문의 결과를 rs에 저장
						rs=pstmt.executeQuery();
						while (rs.next()) {
							// 한 사람의 데이터 값을 저장할 MemberBean mb 객체 생성
							NewsBean nb = new NewsBean();
							// nb 데이터에 값을 저장
							nb.setNews_number(rs.getInt("news_number"));
							nb.setNews_subject(rs.getString("news_subject"));
							nb.setMem_id(rs.getString("mem_id"));
							nb.setNews_content(rs.getString("news_content"));
							nb.setNews_image(rs.getString("news_image"));
							nb.setNews_readcount(rs.getInt("news_readcount"));
							nb.setNews_reg_date(rs.getTimestamp("news_reg_date"));			
							// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
							nList.add(nb);
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
					return nList;
				}	// 검색된 게시물을 조회하는 메소드 끝
				
		
	public List<NewsBean> getList(int startRow, int pageSize) {	// 게시판 조회
		List<NewsBean> nList = new ArrayList<NewsBean>();
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 게시판 글을 조회하는 쿼리문
			// 게시물의 갯수가 startRow(제일 첫 글의 번호)보다 크거나 같고, 
			// pageSize(전체 글의 갯수)보다 작거나 같아야한다.
			sql = "select * from news order by news_number desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.			
			pstmt.setInt(1,  startRow-1);
			pstmt.setInt(2,  pageSize);
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				// 한 사람의 데이터 값을 저장할 NewsBean nb 객체 생성
				NewsBean nb = new NewsBean();
				// mb 데이터에 값을 저장
				nb.setNews_number(rs.getInt("news_number"));
				nb.setNews_subject(rs.getString("news_subject"));
				nb.setMem_id(rs.getString("mem_id"));
				nb.setNews_content(rs.getString("news_content"));
				nb.setNews_image(rs.getString("news_image"));
				nb.setNews_readcount(rs.getInt("news_readcount"));
				nb.setNews_reg_date(rs.getTimestamp("news_reg_date"));			
				// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				nList.add(nb);
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
		return nList;
	}	// 게시판 조회 메소드의 끝	
	
	public void setReadCount(int news_number) {	// 게시물의 갯수를 반환해주는 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 조회수를 1 증가한다.
			sql = "update news set news_readcount=news_readcount+1 where news_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  news_number);
			// 조회수 증가 쿼리문을 실행한다.
			pstmt.executeUpdate();			
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
	}	// setReadCount() 메소드의 끝
}
