package communityDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommunityDAO {
	// 필요한 변수들을 생성한다.
	// DB 호출변수 con 초기화
	Connection con = null;
	// pstmt 변수 초기화
	PreparedStatement pstmt = null;
	// sql 변수 초기화(sql 구문 초기화)
	String sql = "";
	// ResultSet 값을 초기화한다.
	ResultSet rs = null;
	
	private Connection getConnection() throws Exception { // DB를 불러오는 메소드		
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
	
	public int getCommunityCount() {	// 게시물의 갯수를 반환해주는 메소드
		int count = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 게시판 글 갯수를 조회한다.
			sql = "select count(*) from community where mem_id != 'admin'";
			pstmt = con.prepareStatement(sql);
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
	}	// 게시물의 갯수를 반환해주는 메소드
		
	public int getAdminCount() {	// 공지사항의 갯수를 반환해주는 메소드
		int admincount = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 게시판 글 갯수를 조회한다.
			sql = "select count(*) from community where mem_id='admin'";
			pstmt = con.prepareStatement(sql);			
			// Resultset으로 쿼리를 실행한다.
			rs=pstmt.executeQuery();
			// 첫번째 열의 값이 게시판 글 개수이다.
			rs.next();
			// 조회된 개수를 count 변수에 저장한다.
			admincount = rs.getInt("count(*)");
			// count 값을 반환한다.
			return admincount;
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
		return admincount;		
	}	// 공지사항의 갯수를 반환해주는 메소드
	
	public int getSearchCount(String search, int searchOption) {	// 게시물의 갯수를 반환해주는 메소드
		int count = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// searchOption이 0이면 제목
			if (searchOption==0){
				// 제목을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from community where comm_subject like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}
			// searchOption이 1이면 내용
			if (searchOption==1){
				// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from community where comm_content like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}
			// searchOption이 2이면 제목+내용
			if (searchOption==2){
				// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from community where comm_content like ? or comm_subject like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
			}
			// searchOption이 3이면 작성자
			if (searchOption==3){
				// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from community where mem_id like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search);
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
	
	public void setReadCount(int comm_number) {	// 게시물의 갯수를 반환해주는 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 조회수를 1 증가한다.
			sql = "update community set comm_readcount=comm_readcount+1 where comm_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  comm_number);
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

	public List<CommunityBean> getList(int startRow, int pageSize) {	// 게시판 조회
		List<CommunityBean> cList = new ArrayList<CommunityBean>();
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 게시판 글을 조회하는 쿼리문
			// 게시물의 갯수가 startRow(제일 첫 글의 번호)보다 크거나 같고, 
			// pageSize(전체 글의 갯수)보다 작거나 같아야한다.
			sql = "select * from community where mem_id != 'admin' order by comm_number desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.			
			pstmt.setInt(1,  startRow-1);
			pstmt.setInt(2,  pageSize);
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				// 한 사람의 데이터 값을 저장할 CommunityBean mb 객체 생성
				CommunityBean cb = new CommunityBean();
				// mb 데이터에 값을 저장
				cb.setComm_number(rs.getInt("comm_number"));
				cb.setComm_subject(rs.getString("comm_subject"));
				cb.setMem_id(rs.getString("mem_id"));
				cb.setComm_content(rs.getString("comm_content"));
				cb.setComm_image(rs.getString("comm_image"));
				cb.setComm_readcount(rs.getInt("comm_readcount"));
				cb.setComm_reg_date(rs.getTimestamp("comm_reg_date"));				
					// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				cList.add(cb);
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
		return cList;
	}	// 게시판 조회 메소드의 끝

	public List<CommunityBean> getSearchList(int startRow, int pageSize, String search, int searchOption) {	// 검색된 게시물을 조회하는 메소드
		List<CommunityBean> cList = new ArrayList<CommunityBean>();
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 검색어에 필터링된 게시물들을 각 옵션에 따라 조회한다.			
			if (searchOption==0){		// searchOption이 0이면 제목
				//검색어 테이블 제어 
				// 검색어테이블에 검색어값이 존재하는지 확인한다.
				sql = "select keyword from hotkey where keyword=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search);
				rs = pstmt.executeQuery();
				if(rs.next()) {	// 검색어가 존재하면 업데이트문으로 searchcount값만 +1해준다.
					sql = "update hotkey set searchcount=searchcount+1 where keyword=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.executeUpdate();
				} else {	// 검색어가 존재하지 않는다면 삽입문으로 검색값을 tavernsearch테이블에 추가한다.					
					sql = "insert into hotkey values(?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, 1);				
					pstmt.executeUpdate();
				}
				//검색어 테이블 제어 
				// 검색값을 조회한다.
				sql = "select * from community where comm_subject like ? and mem_id != 'admin' order by comm_number desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2,  startRow-1);
				pstmt.setInt(3,  pageSize);
			}
			// searchOption이 1이면 내용
			if (searchOption==1){
				//검색어 테이블 제어 
				// 검색어테이블에 검색어값이 존재하는지 확인한다.
				sql = "select keyword from hotkey where keyword=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search);
				rs = pstmt.executeQuery();
				if(rs.next()) {	// 검색어가 존재하면 업데이트문으로 searchcount값만 +1해준다.
					sql = "update hotkey set searchcount=searchcount+1 where keyword=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.executeUpdate();
				} else {	// 검색어가 존재하지 않는다면 삽입문으로 검색값을 tavernsearch테이블에 추가한다.
					sql = "insert into hotkey values(?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, 1);				
					pstmt.executeUpdate();
				}
				 //검색어 테이블 제어 
				sql = "select * from community where comm_content like ? and mem_id != 'admin' order by comm_number desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2,  startRow-1);
				pstmt.setInt(3,  pageSize);
			}
			// searchOption이 2이면 제목+내용
			if (searchOption==2){
				// 검색어 테이블 제어 
				// 검색어테이블에 검색어값이 존재하는지 확인한다.
				sql = "select keyword from hotkey where keyword=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search);
				rs = pstmt.executeQuery();
				if(rs.next()) {	// 검색어가 존재하면 업데이트문으로 searchcount값만 +1해준다.
					sql = "update hotkey set searchcount=searchcount+1 where keyword=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.executeUpdate();
				} else {	// 검색어가 존재하지 않는다면 삽입문으로 검색값을 tavernsearch테이블에 추가한다.
					sql = "insert into hotkey values(?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, 1);				
					pstmt.executeUpdate();
				}
				 // 검색어 테이블 제어 
				sql = "select * from community where comm_subject like ? or comm_content like ? and mem_id != 'admin' order by comm_number desc limit ?,?";
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
				// 검색어테이블에 검색어값이 존재하는지 확인한다.
				sql = "select keyword from hotkey where keyword=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, search);
				rs = pstmt.executeQuery();
				if(rs.next()) {	// 검색어가 존재하면 업데이트문으로 searchcount값만 +1해준다.
					sql = "update hotkey set searchcount=searchcount+1 where keyword=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.executeUpdate();
				} else {	// 검색어가 존재하지 않는다면 삽입문으로 검색값을 tavernsearch테이블에 추가한다.
					sql = "insert into hotkey values(?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, search);
					pstmt.setInt(2, 1);				
					pstmt.executeUpdate();
				}
				// 검색어 테이블 제어 
				sql = "select * from community where mem_id like ? and mem_id != 'admin' order by comm_number desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, search);
				pstmt.setInt(2,  startRow-1);
				pstmt.setInt(3,  pageSize);
			}
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				// 한 사람의 데이터 값을 저장할 MemberBean mb 객체 생성
				CommunityBean cb = new CommunityBean();
				// mb 데이터에 값을 저장
				cb.setComm_number(rs.getInt("comm_number"));
				cb.setComm_subject(rs.getString("comm_subject"));
				cb.setMem_id(rs.getString("mem_id"));
				cb.setComm_content(rs.getString("comm_content"));
				cb.setComm_image(rs.getString("comm_image"));
				cb.setComm_readcount(rs.getInt("comm_readcount"));
				cb.setComm_reg_date(rs.getTimestamp("comm_reg_date"));			
				// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				cList.add(cb);
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
		return cList;
	}	// 검색된 게시물을 조회하는 메소드 끝
	
	public List<CommunityBean> getAdminList() {	// 관리자 게시글들을 조회하는 메소드 생성
		List<CommunityBean> aList = new ArrayList<CommunityBean>();
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 게시판 글을 조회하는 쿼리문
			// 게시물의 갯수가 startRow(제일 첫 글의 번호)보다 크거나 같고, 
			// pageSize(전체 글의 갯수)보다 작거나 같아야한다.
			sql = "select * from community where mem_id='admin' order by comm_number desc limit 0, 3";
			pstmt = con.prepareStatement(sql);			
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				// 한 사람의 데이터 값을 저장할 MemberBean mb 객체 생성
				CommunityBean cb = new CommunityBean();
				// mb 데이터에 값을 저장
				cb.setComm_number(rs.getInt("comm_number"));				
				cb.setComm_subject(rs.getString("comm_subject"));
				cb.setMem_id(rs.getString("mem_id"));
				cb.setComm_content(rs.getString("comm_content"));
				cb.setComm_image(rs.getString("comm_image"));
				cb.setComm_readcount(rs.getInt("comm_readcount"));
				cb.setComm_reg_date(rs.getTimestamp("comm_reg_date"));
				// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				aList.add(cb);
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
		return aList;
	}	// 관리자 게시물 조회 메소드의 끝
	
	
	public CommunityBean getDetail(int comm_number) {	// 글 내용을 보기 위한 메소드
			try {
				// getConnection() 메소드 가져와서 con 변수에 넣기
				con=getConnection();
				// BoardBean 객체를 생성
				CommunityBean cb = new CommunityBean();
				// 받아온 number값과 일치하는 게시물을 가져온다.
				sql = "select * from community where comm_number = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,  comm_number);
				rs = pstmt.executeQuery();
				// 조회한 결과를 set함수로 bb 변수에 담는다.
				if (rs.next()) {
					cb.setComm_number(rs.getInt("comm_number"));
					cb.setComm_subject(rs.getString("comm_subject"));
					cb.setMem_id(rs.getString("mem_id"));
					cb.setComm_content(rs.getString("comm_content"));
					cb.setComm_image(rs.getString("comm_image"));
					cb.setComm_readcount(rs.getInt("comm_readcount"));
					cb.setComm_reg_date(rs.getTimestamp("comm_reg_date"));					
				}
				// 게시물의 내용을 담은 cb 값을 리턴한다.
				return cb;
			} catch (Exception e) {
				System.out.println("getDetail 에러: "+ e);
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
			// 게시물이 없으면 아무 값도 반환하지 않는다.
			return null;
	}	// 글 내용을 보기 위한 메소드 끝

		
	public void insertCommunity(CommunityBean cb)  { 	// 글 작성을 위한 메소드
		// 현재 게시물 번호를 위한 변수 생성
		int nextnum = 0;		
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 가장 마지막 게시물의 번호를 구하기 위한 쿼리
			sql = "select max(comm_number) from community";
			pstmt=con.prepareStatement(sql);
			// Resultset에 쿼리의 결과를 대입한다. 결과값: 가장 마지막 게시물의 번호
			rs=pstmt.executeQuery();
			if(rs.next()) {		// 가장 마지막 게시물의 번호가 존재할 때(최소한 1개의 게시물이라도 있으면 조건이 부합한다)
				// 현재 게시물의 번호는 가장 마지막 게시물 번호에서 1을 더한 값
				nextnum = rs.getInt("max(comm_number)")+1;
			} else {	// 게시물이 하나도 없을 때(게시물의 번호가 0이 되면 안되므로 1로 지정해준다.)
				nextnum = 1;
			}			
			// insert 만들어서 실행할 내장 객체
			sql = "insert into community values(?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			// ? 값을 채우기
			pstmt.setInt(1, nextnum);	// nextnum은 현재 게시물의 번호
			pstmt.setString(2, cb.getMem_id());
			pstmt.setString(3, cb.getComm_subject());			
			pstmt.setString(4, cb.getComm_content());
			pstmt.setString(5, cb.getComm_image());			
			pstmt.setInt(6, 0);
			// SQL 실행(executeUpdate() 메소드는 자체적으로 commit도 실행한다.)
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
	} 	// 글 작성을 위한 메소드 끝
	
	public void updateCommunity(CommunityBean cb) throws SQLException {		// 글 수정 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			if (cb.getComm_image()==null) {	// 입력받은 파일이 비어있다면 파일은 업데이트하지 않는다.
				sql = "update community set comm_subject=?, comm_content=? where comm_number=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cb.getComm_subject());
				pstmt.setString(2, cb.getComm_content());
				pstmt.setInt(3, cb.getComm_number());
				// update 쿼리 실행
				pstmt.executeUpdate();
			} else {	// 파일을 입력받았다면 파일도 업데이트한다.
		    	sql = "update community set comm_subject=?, comm_content=?, comm_image=? where comm_number=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, cb.getComm_subject());
				pstmt.setString(2, cb.getComm_content());
				pstmt.setString(3, cb.getComm_image());
				pstmt.setInt(4, cb.getComm_number());
				// update 쿼리 실행
				pstmt.executeUpdate();
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
	}		// 글 수정 메소드 끝
	
	public void imageDelete(int comm_number) throws SQLException {	 // 기존 파일 삭제 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
	    	sql = "update community set comm_image=null where comm_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comm_number);
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
	} // 기존 파일 삭제 메소드 끝
	
	public void deleteCommunity(int comm_number) throws SQLException {	 // 게시물 삭제 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
	    	sql = "delete from community where comm_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comm_number);
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
	} // 게시물 삭제 메소드 끝
	
	public int getCommentCount(int comm_number) {	// 해당 게시물에 있는 댓글의 갯수를 반환해주는 메소드
		int count = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 게시판 글 갯수를 조회한다.
			sql = "select count(*) from comment where comm_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  comm_number);
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
	}	// 해당 게시물에 있는 댓글의 갯수를 반환해주는 메소드 끝
	
	public List<CommentBean> getCommentList(int commentStartRow, int commentPageSize, int comm_number) {	 // 댓글 목록 조회 메소드
		List<CommentBean> cList = new ArrayList<CommentBean>();
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 게시판 글을 조회하는 쿼리문
			// 게시물의 갯수가 startRow(제일 첫 글의 번호)보다 크거나 같고, 
			// pageSize(전체 글의 갯수)보다 작거나 같아야한다.
			sql = "select * from comment where comm_number=? order by comment_number desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
			pstmt.setInt(1,  comm_number);
			pstmt.setInt(2,  commentStartRow-1);
			pstmt.setInt(3,  commentPageSize);
			// 쿼리문의 결과를 rs에 저장
			rs=pstmt.executeQuery();
			while (rs.next()) {
				// 한 사람의 데이터 값을 저장할 CommunityBean mb 객체 생성
				CommentBean cb = new CommentBean();
				// mb 데이터에 값을 저장
				cb.setComment_number(rs.getInt("comment_number"));
				cb.setComm_number(rs.getInt("comm_number"));
				cb.setMem_id(rs.getString("mem_id"));
				cb.setComment_content(rs.getString("comment_content"));				
				cb.setComment_reg_date(rs.getTimestamp("comment_reg_date"));				
					// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				cList.add(cb);
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
		return cList;
	}	 // 댓글 목록 조회 메소드의 끝
	
	public CommentBean getCommentDetail(int comment_number) {	// 해당 댓글의 내용을 보기 위한 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// BoardBean 객체를 생성
			CommentBean cob = new CommentBean();
			// 받아온 number값과 일치하는 게시물을 가져온다.
			sql = "select * from comment where comment_number = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  comment_number);
			rs = pstmt.executeQuery();
			// 조회한 결과를 set함수로 bb 변수에 담는다.
			if (rs.next()) {
				cob.setComment_number(rs.getInt("comment_number"));
				cob.setComm_number(rs.getInt("comment_number"));
				cob.setMem_id(rs.getString("mem_id"));
				cob.setComment_content(rs.getString("comment_content"));				
				cob.setComment_reg_date(rs.getTimestamp("comment_reg_date"));				
			}
			// 게시물의 내용을 담은 cob 값을 리턴한다.
			System.out.println("댓글 내용"+cob.getComment_content());
			return cob;
		} catch (Exception e) {
			System.out.println("getCommentDetail 에러: "+ e);
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
		// 게시물이 없으면 아무 값도 반환하지 않는다.
		return null;
}	// 해당 댓글의 내용 보기 메소드의 끝

	public void insertComment(CommentBean cob, int comm_number, String mem_id)  {	// 댓글 작성을 위한 메소드
		// 현재 게시물 번호를 위한 변수 생성
		int nextnum = 0;		
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 가장 마지막 게시물의 번호를 구하기 위한 쿼리
			sql = "select max(comment_number) from comment";
			pstmt=con.prepareStatement(sql);
			// Resultset에 쿼리의 결과를 대입한다. 결과값: 가장 마지막 게시물의 번호
			rs=pstmt.executeQuery();
			if(rs.next()) {		// 가장 마지막 게시물의 번호가 존재할 때(최소한 1개의 게시물이라도 있으면 조건이 부합한다)
				// 현재 게시물의 번호는 가장 마지막 게시물 번호에서 1을 더한 값
				nextnum = rs.getInt("max(comment_number)")+1;
			} else {	// 게시물이 하나도 없을 때(게시물의 번호가 0이 되면 안되므로 1로 지정해준다.)
				nextnum = 1;
			}			
			// insert 만들어서 실행할 내장 객체
			sql = "insert into comment values(?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			// ? 값을 채우기
			pstmt.setInt(1, nextnum);	// nextnum은 현재 게시물의 번호
			pstmt.setInt(2, comm_number);
			pstmt.setString(3, mem_id);			
			pstmt.setString(4, cob.getComment_content());
			// SQL 실행(executeUpdate() 메소드는 자체적으로 commit도 실행한다.)
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
	}	// 댓글 작성을 위한 메소드 끝
	
	public void deleteComment(int comment_number) throws SQLException {	 // 댓글 삭제 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
	    	sql = "delete from comment where comment_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, comment_number);
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
	}	 // 댓글 삭제 메소드 끝
	
	public void updateComment(CommentBean cob) throws SQLException {	// 댓글 수정 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			sql = "update comment set comment_content=?, comment_reg_date=now() where comment_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cob.getComment_content());			
			pstmt.setInt(2, cob.getComment_number());
			// update 쿼리 실행
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
	}	// 댓글 수정 메소드의 끝
	
	public List<HotkeyBean> hotKeywordList() {	 // 인기 검색어의 목록을 조회하는 메소드
		List<HotkeyBean>	hotList = new ArrayList<HotkeyBean>();
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 검색어 조회횟수(searchcount)를 내림차순으로 정렬한 검색어 값을 최대 6개까지만 조회한다.
			sql = "select * from hotkey order by 2 desc";
			pstmt = con.prepareStatement(sql);				
			rs=pstmt.executeQuery();
			while(rs.next()) {
				// 조회한 데이터를 저장할 핫키 자바빈 객체 생성
				communityDB.HotkeyBean hb = new communityDB.HotkeyBean();	
				// hb에 조회한 값들을 저장한다.
				hb.setKeyword(rs.getString("keyword"));
				hb.setSearchcount(rs.getInt("searchcount"));
				hotList.add(hb);
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
		return hotList;
	}	 // 인기 검색어의 목록을 조회하는 메소드
	
	public int getHotkeyCount() { 	// 검색어의 검색횟수를 반환해주는 메소드
		int hotkeyCount = 0;
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 게시판 글 갯수를 조회한다.
			sql = "select count(*) from hotkey";
			pstmt = con.prepareStatement(sql);
			// Resultset으로 쿼리를 실행한다.
			rs=pstmt.executeQuery();
			// 첫번째 열의 값이 게시판 글 개수이다.
			rs.next();
			// 조회된 개수를 count 변수에 저장한다.
			hotkeyCount = rs.getInt("count(*)");
			// count 값을 반환한다.
			return hotkeyCount;
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
		return hotkeyCount;		
	} 	// 검색어의 검색횟수를 반환해주는 메소드
}
