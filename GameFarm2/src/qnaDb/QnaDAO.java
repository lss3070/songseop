package qnaDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import communityDB.CommunityBean;
import communityDB.HotkeyBean;

public class QnaDAO {
	String sql = "";
	PreparedStatement pstmt = null;
	Connection con = null;
	ResultSet rs = null;
	
	private Connection getConnection() throws Exception{//디비연결 메서드
		Connection con=null;
		//context.xml 사용하기위해객체생성	
		//import javax.naming.Context;
		//import javax.naming.InitiolContext;
		Context init=new InitialContext();
		//메서드 호출 자원찾기(자원위치/자원이름)
		//javax.sql.DataSource
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		con=ds.getConnection();
		return con;
	}//getConnection()메소드 끝
	
	public int getQnaCount(){//getqnaCount() 함수만들기		
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql qna모든 데이터 가져와서 개수 카운트
			sql="select count(*) from qna";
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
	}//getQnaCount() 메소드끝
	
	public int getQnaSearchCount(String search){//검색시사용되는 검색어메서드
		int count=0;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql qna모든 데이터 가져와서 개수 카운트   '%검색어%'
			sql="select count(*) from qna where subject like ?";
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
	}//getQnaSearchCount 메소드끝	
	
	public List getQnaList(int startRow, int pageSize){//List출력시 사용되는 메서드 getqnaList(시작행번호, 가져올글개수)
		List QnaList=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			sql="select * from qna order by qna_re_ref desc,qna_re_lev asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			//4 rs <= 실행결과저장
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면
			//  패키지 qna 파일이름 QnaBean
			// 자바빈 객체 생성  QnaBean qb
			// 자바빈 멤버변수 <= rs 가져온 열 저장
			// qnaList 배열 한칸에 저장
			while(rs.next()){
				QnaBean qb=new QnaBean();
				qb.setQna_number(rs.getInt("qna_number"));
				qb.setQna_content(rs.getString("qna_content"));
				qb.setQna_reg_date(rs.getTimestamp("qna_reg_date"));
				qb.setQna_re_lev(rs.getInt("qna_re_lev"));
				qb.setQna_re_ref(rs.getInt("qna_re_ref"));
				qb.setQna_re_seq(rs.getInt("qna_re_seq"));
				qb.setQna_readcount(rs.getInt("qna_readcount"));
				qb.setMem_id(rs.getString("mem_id"));
				qb.setQna_subject(rs.getString("qna_subject"));				
				QnaList.add(qb);
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
		return QnaList;
	}//getQnaList 메소드끝 	
	
	public List getqnaList(int startRow, int pageSize, String search){//검색시 사용되는 메서드			
		List qnaList=new ArrayList();
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql
			sql="select * from qna where qna_subject like ? order by qna_re_ref desc,qna_re_lev asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setInt(2, startRow-1);
			pstmt.setInt(3, pageSize);
			//4 rs <= 실행결과저장
			rs=pstmt.executeQuery();
			//5 rs 첫행이동 데이터 있으면
			//  패키지 qna 파일이름 QnaBean
			// 자바빈 객체 생성  QnaBean qb
			// 자바빈 멤버변수 <= rs 가져온 열 저장
			// qnaList 배열 한칸에 저장
			while(rs.next()){
				QnaBean qb=new QnaBean();
				qb.setQna_number(rs.getInt("qna_numberber"));
				qb.setQna_content(rs.getString("qna_content"));
				qb.setQna_reg_date(rs.getTimestamp("qna_reg_date"));					
				qb.setQna_re_lev(rs.getInt("qna_re_lev"));
				qb.setQna_re_ref(rs.getInt("qna_re_ref"));
				qb.setQna_re_seq(rs.getInt("qna_re_seq"));
				qb.setQna_readcount(rs.getInt("qna_readcount"));
				qb.setQna_subject(rs.getString("qna_subject"));
				qb.setMem_id(rs.getString("mem_id"));
				qnaList.add(qb);
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
		return qnaList;
	}//검색시 사용되는 getqnaList 메소드 끝 
	
	public QnaBean getqna(int qna_number){	// 고객센터 게시물을 보기
		//QnaBean변수선언
		QnaBean qb = null;						
		try{
			//db연결후 sql qna 모든정보 가져오기 조건 qna_number=?
			//rs = 실행저장   rs첫행이동후 데이터 있으면 저장된내용을 자바빈에 저장
			//자바빈 객체생성  자바빈변수=rs
			con=getConnection();
			sql="select * from qna where qna_number=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna_number);
			//rs실행 저장
			rs=pstmt.executeQuery();
			//sql실행결과가 정확할때 해당값을 set으로 지정 
			if(rs.next()){
				qb= new QnaBean();
				qb.setQna_number(rs.getInt("qna_qna_numberber"));
				qb.setQna_content(rs.getString("qna_content"));
				qb.setQna_reg_date(rs.getTimestamp("qna_reg_date"));					
				qb.setQna_re_lev(rs.getInt("qna_re_lev"));
				qb.setQna_re_ref(rs.getInt("qna_re_ref"));
				qb.setQna_re_seq(rs.getInt("qna_re_seq"));
				qb.setQna_readcount(rs.getInt("qna_readcount"));
				qb.setQna_subject(rs.getString("qna_subject"));
				qb.setMem_id(rs.getString("mem_id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}return qb;
	}//getqna 메소드끝
	
	public void InsertQna(QnaBean qb){//글입력시 사용되는 메서드		
		int n =0;			
		try{
			//글번호 최대값을 찾아서 그다음 번호로 글이작성되게함
			con=getConnection();
			sql = "select max(qna_number) from qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				n = rs.getInt(1) + 1;				
			}				
			sql="update qna set qna_re_seq=qna_re_seq+1, qna_re_ref=? where qna_re_seq>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qb.getQna_number());
			pstmt.setInt(2, qb.getQna_re_seq());
			pstmt.executeUpdate();
			//value값을 insert문사용해서 db저장				
			sql="insert into qna(qna_number,qna_subject,qna_content,qna_readcount,qna_re_ref,qna_re_seq,qna_re_lev,mem_id,qna_reg_date)values(?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, n);				
			pstmt.setString(2, qb.getQna_subject());
			pstmt.setString(3, qb.getQna_content());
			pstmt.setInt(4, 0);//조회수 0
			pstmt.setInt(5, n);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setString(8, qb.getMem_id());								
			//rs실행 저장
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}		
	}
		
	public void ReInsertQna(QnaBean qb){//답글입력메서드			
		// 현재 게시물 번호를 위한 변수 생성
		int nextnum = 0;		
		// 자바 예외 처리 코드
		try {				
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 가장 마지막 게시물의 번호를 구하기 위한 쿼리
			sql = "select max(qna_number) from qna";
			pstmt=con.prepareStatement(sql);
			// Resultset에 쿼리의 결과를 대입한다. 결과값: 가장 마지막 게시물의 번호
			rs=pstmt.executeQuery();
			if(rs.next()) {		// 가장 마지막 게시물의 번호가 존재할 때(최소한 1개의 게시물이라도 있으면 조건이 부합한다)
				// 현재 게시물의 번호는 가장 마지막 게시물 번호에서 1을 더한 값
				nextnum = rs.getInt(1)+1;
			} else {	// 게시물이 하나도 없을 때(게시물의 번호가 0이 되면 안되므로 1로 지정해준다.)
				nextnum = 1;
			}
			// insert 만들어서 실행할 내장 객체
			sql = "insert into qna values(?,?,?,?,?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			// ? 값을 채우기
			pstmt.setInt(1, nextnum);	// nextnum은 현재 게시물의 번호
			pstmt.setString(2, qb.getMem_id());
			pstmt.setString(3, qb.getQna_subject());				
			pstmt.setString(4, qb.getQna_content());				
			pstmt.setInt(5, qb.getQna_re_ref());	// 답글 그룹(현재 게시물과 같은 그룹이므로 번호와 같은 값을 넣는다.)
			pstmt.setInt(6, qb.getQna_re_lev()+1);	// 답글 들여쓰기(답글의 답글을 달 때마다 이 값이 1씩 증가한다.)
			pstmt.setInt(7, qb.getQna_re_seq()+1);	// 답글 순서(답글의 답글을 달 때마다 이 값이 1씩 증가한다.)
			pstmt.setInt(8, 0);
			// SQL 실행(executeUpdate() 메소드는 자체적으로 commit도 실행한다.)
			pstmt.executeUpdate();				
		} catch (Exception e) {
			// Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 예외 발생 관계 유무하고 마무리 작업 수행
			// 객체 생성 -> 종료 -> 닫기 기억장소 정리
			if(rs!=null){
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
	}//ReInsertQna 메소드 끝
		
	public QnaBean getContent(int qna_number){//글내용가져오기 메소드
		QnaBean qb = null;			
		try{
			con=getConnection();
			qb = new QnaBean();
			//글번호를 지정해서 해당글번호의 내용을 가져옴
			sql="select * from qna where qna_number=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna_number);							
			//rs실행 저장
			rs = pstmt.executeQuery();
			if (rs.next()) {// qb객체 생성					
				qb.setQna_number(qna_number);
				qb.setQna_content(rs.getString("qna_content"));
				qb.setQna_reg_date(rs.getTimestamp("qna_reg_date"));
				qb.setQna_subject(rs.getString("qna_subject"));
				qb.setQna_readcount(rs.getInt("qna_readcount"));
				qb.setMem_id(rs.getString("mem_id"));
				qb.setQna_re_ref(rs.getInt("qna_re_ref"));
				qb.setQna_re_lev(rs.getInt("qna_re_lev"));
				qb.setQna_re_seq(rs.getInt("qna_re_seq"));					
			}
			// 자바빈 멤버변수 <-rs 열내용
			return qb;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
		return null;			
	}//getContent메소드 끝
		
	public void updateQna(QnaBean qb){//글수정시 사용되는메서드		
		try{
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			//글번호를 지정해서 글제목과 내용을 수정
			sql = "update qna set qna_subject=?, qna_content=? where qna_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qb.getQna_subject());			
			pstmt.setString(2, qb.getQna_content());
			pstmt.setInt(3, qb.getQna_number());
			// update 쿼리 실행
			pstmt.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null)
				try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)
				try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)
				try{con.close();}catch(SQLException ex){}
		}
	}//updateQna메소드 끝
	
	public void deleteQna(int qna_number) throws SQLException {//글삭제시 사용되는 메소드
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			//해당 글번호의 내용을 삭제
	    	sql = "delete from qna where qna_number=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_number);
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
		
	public int getSearchCount(String search, int searchOption) {//게시물의 갯수를 반환해주는 메소드	
		int count = 0;
		int check = 0;			
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// searchOption이 0이면 제목
			if (searchOption==0){
				// 제목을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from qna where qna_subject like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}
			// searchOption이 1이면 내용
			if (searchOption==1){
				// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from qna where qna_content like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
			}
			// searchOption이 2이면 제목+내용
			if (searchOption==2){
				// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from qna where qna_content like ? or qna_subject like ? and mem_id != 'admin'";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
			}
			// searchOption이 3이면 작성자
			if (searchOption==3){
				// 내용을 검색하여 출력되는 게시물의 개수를 조회한다.
				sql = "select count(*) from qna where mem_id like ? and mem_id != 'admin'";
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
		
	public List<QnaBean> getSearchList(int startRow, int pageSize, String search, int searchOption) {//검색된 게시물을 조회하는 메소드
		List<QnaBean> cList = new ArrayList<QnaBean>();			
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와 DB에 연결한 다음 con 변수에 지정하기
			con=getConnection();
			// 검색어에 필터링된 게시물들을 각 옵션에 따라 조회한다.			
			if (searchOption==0){		// searchOption이 0이면 제목					
				// 검색값을 조회한다.
				sql = "select * from qna where qna_subject like ? and mem_id != 'admin' order by qna_number desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2,  startRow-1);
				pstmt.setInt(3,  pageSize);
			}
			// searchOption이 1이면 내용
			if (searchOption==1){					
				sql = "select * from qna where qna_content like ? and mem_id != 'admin' order by qna_number desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2,  startRow-1);
				pstmt.setInt(3,  pageSize);
			}
			// searchOption이 2이면 제목+내용
			if (searchOption==2){					
				sql = "select * from qna where qna_subject like ? or qna_content like ? and mem_id != 'admin' order by qna_number desc limit ?,?";
				pstmt = con.prepareStatement(sql);
				// 받아넘겨온 정수 값들을 sql의 ?부분에 지정해준다.
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				pstmt.setInt(3,  startRow-1);
				pstmt.setInt(4,  pageSize);
			}
			// searchOption이 3이면 작성자
			if (searchOption==3){					
				sql = "select * from qna where mem_id like ? and mem_id != 'admin' order by qna_number desc limit ?,?";
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
				QnaBean qb = new QnaBean();
				// mb 데이터에 값을 저장
				qb.setQna_number(rs.getInt("qna_number"));
				qb.setQna_subject(rs.getString("qna_subject"));
				qb.setMem_id(rs.getString("mem_id"));
				qb.setQna_content(rs.getString("qna_content"));					
				qb.setQna_readcount(rs.getInt("qna_readcount"));
				qb.setQna_reg_date(rs.getTimestamp("qna_reg_date"));			
				// 글 하나에 대한 데이터 값을 배열 bList 한 칸에 추가로 저장
				cList.add(qb);
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
		
	public void setReadCount(int comm_number) {	// 게시물의 갯수를 반환해주는 메소드			
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con=getConnection();
			// 조회수를 1 증가한다.
			sql = "update qna set qna_readcount=qna_readcount+1 where qna_number=?";
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
	
}//클래스
