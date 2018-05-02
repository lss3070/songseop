package memberDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
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
	// DB 호출값 초기화
	Connection con = null;
	// pstmt 변수 초기화
	PreparedStatement pstmt = null;
	// sql 변수 초기화(sql 구문 초기화)
	String sql = "";
	// ResultSet 값을 초기화한다.
	ResultSet rs = null;

	public void insertMember(MemberBean mb) {	// 회원 가입
		// 자바 예외 처리 코드
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			String member = "mem_id, mem_pass, mem_name, mem_gender, mem_age, "
					+ "mem_email1, mem_email2, mem_post, mem_address1, mem_address2, mem_phone1, "
					+ "mem_phone2, mem_phone3, mem_point, mem_reg_date";
			// insert 만들어서 실행할 내장 객체
			sql = "insert into member("+member+") "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// ? 값을 채우기
			// Mem_id 값
			pstmt.setString(1, mb.getMem_id());
			// Mem_pass 값
			pstmt.setString(2, mb.getMem_pass());
			// name 값
			pstmt.setString(3, mb.getMem_name());
			// gender
			pstmt.setString(4, mb.getMem_gender());
			// age
			pstmt.setString(5, mb.getMem_age());
			// email1
			pstmt.setString(6, mb.getMem_email1());
			// email2
			pstmt.setString(7, mb.getMem_email2());
			// post 값
			pstmt.setString(8, mb.getMem_post());
			// 도로명 주소
			pstmt.setString(9, mb.getMem_address1());
			// 상세 주소
			pstmt.setString(10, mb.getMem_address2());
			// phone1 값
			pstmt.setString(11, mb.getMem_phone1());
			// phone2 값
			pstmt.setString(12, mb.getMem_phone2());
			// phone3 값
			pstmt.setString(13, mb.getMem_phone3());
			// 결제를 위한 포인트 값. 결제를 위해 100만 포인트를 부여한다.
			pstmt.setInt(14, 1000000);			
			// 가입일자 값
			pstmt.setTimestamp(15,  mb.getMem_reg_date());
			pstmt.executeUpdate();
			// insert 쿼리를 실행하고 DB를 commit 해준다.(따로 commit() 메소드를 실행하여 DB를			
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
	}	// 회원 가입 메소드 끝

	public void updateMember(MemberBean mb) throws SQLException {		// 회원 정보 수정
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			sql = "update member set mem_pass=?, mem_name=?, mem_gender=?, mem_age=?, "
					+ "mem_email1=?, mem_email2=?, mem_post=?, mem_address1=?, mem_address2=?,"
					+ "mem_phone1=?, mem_phone2=?, mem_phone3=? where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getMem_pass());
			pstmt.setString(2, mb.getMem_name());
			pstmt.setString(3, mb.getMem_gender());
			pstmt.setString(4, mb.getMem_age());
			pstmt.setString(5, mb.getMem_email1());
			pstmt.setString(6, mb.getMem_email2());
			pstmt.setString(7, mb.getMem_post());
			pstmt.setString(8, mb.getMem_address1());
			pstmt.setString(9, mb.getMem_address2());
			pstmt.setString(10, mb.getMem_phone1());
			pstmt.setString(11, mb.getMem_phone2());
			pstmt.setString(12, mb.getMem_phone3());			
			pstmt.setString(13, mb.getMem_id());			
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
	}	// 회원 정보 수정 메소드 끝

	public void deleteMember(String mem_id) {		// 회원 삭제 처리
		try {
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			sql = "delete from member where mem_id=?";
			pstmt = con.prepareStatement(sql);
			/*pstmt.setString(1, mb.getMem_id());*/
			pstmt.setString(1, mem_id);
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
	}	// 회원 삭제 처리 메소드 끝

	public MemberBean getMember(String mem_id) throws Exception { // 해당 mem_id를 가진 member의 정보 가져오기
		MemberBean mb = null;		
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			// getMember(String Mem_id)에서 입력받아온 Mem_id 값에 대한 다른 열 값들을 조회하기 위해 select 문을
			// sql 변수에 대입한다.
			sql = "select * from member where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			// sql 변수에 입력받은 select 문을 실행한다.
			rs = pstmt.executeQuery();
			if (rs.next()) {
				// MemberBean()
				mb = new MemberBean();
				mb.setMem_id(rs.getString("mem_id"));
				mb.setMem_pass(rs.getString("mem_pass"));
				mb.setMem_name(rs.getString("mem_name"));		
				mb.setMem_gender(rs.getString("mem_gender"));
				mb.setMem_age(rs.getString("mem_age"));				
				mb.setMem_email1(rs.getString("mem_email1"));
				mb.setMem_email2(rs.getString("mem_email2"));
				mb.setMem_post(rs.getString("mem_post"));
				mb.setMem_address1(rs.getString("mem_address1"));
				mb.setMem_address2(rs.getString("mem_address2"));
				mb.setMem_phone1(rs.getString("mem_phone1"));
				mb.setMem_phone2(rs.getString("mem_phone2"));
				mb.setMem_phone3(rs.getString("mem_phone3"));				
				mb.setMem_point(rs.getInt("mem_point"));
				mb.setMem_reg_date(rs.getTimestamp("mem_reg_date"));
			}
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
		return mb;
	}// 회원 정보 메소드 끝

	public int userCheck(String mem_id, String mem_pass) throws Exception { // 아이디와 비밀번호를 비교하는 부분
		// check 값의 기본값은 -1로 지정해준다.
		int check = -1;
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			sql = "select * from member where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 아이디가 존재함
				if (mem_pass.equals(rs.getString("mem_pass"))) { // 비밀번호에 대한 조건식
					// 비밀번호 일치시
					check = 1;
				} else { // 비밀번호 틀릴시
					check = 0;
				}
			} else { // 아이디가 틀림
				check = -1;
			}
		} catch (Exception e) { // Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 기억장소 회수
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
		return check;
	} // 아이디와 비밀번호를 비교하는 메소드 끝

	public int idCheck(String Mem_id) {	 // 아이디 중복 확인
		// check 값의 기본값은 0로 지정해준다.
		int check=0;
		try {
			// 예외가 발생할 것 같은 코드 -> DB 연결
			// getConnection() 메소드 가져와서 con 변수에 넣기
			con = getConnection();
			sql = "select Mem_id from member where Mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Mem_id);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 아이디가 존재함
				check=1;
			} else { // 아이디가 없음
				check=0;
			}
		} catch (Exception e) { // Exception이 예외를 잡아서 처리 -> 예외 발생 메시지 출력
			e.printStackTrace();
		} finally {
			// 기억장소 회수
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
		return check;
	}	 // 아이디 중복 확인 메소드 끝	
} // 클래스의 끝
