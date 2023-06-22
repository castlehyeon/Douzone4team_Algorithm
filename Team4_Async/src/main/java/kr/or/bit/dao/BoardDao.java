package kr.or.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;


public class BoardDao {
	DataSource ds = null;

	public BoardDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");// java:comp/env/ + name
		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	public int writeok(Board boarddata) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer)"
					+ "values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boarddata.getWriter());
			pstmt.setString(2, boarddata.getPwd());
			pstmt.setString(3, boarddata.getSubject());
			pstmt.setString(4, boarddata.getContent());
			pstmt.setString(5, boarddata.getEmail());
			pstmt.setString(6, boarddata.getHomepage());
			pstmt.setString(7, boarddata.getWriter());

			// refer
			int refermax = getMaxRefer();
			int refer = refermax + 1;

			pstmt.setInt(8, refer);

			row = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			try {
				pstmt.close();
				conn.close(); // 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}


	public int getMaxRefer() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int refer_max = 0;
		try {
			conn = ds.getConnection(); // 
			String sql = "select nvl(max(refer),0) from jspboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				refer_max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); // �
			} catch (Exception e) {

			}
		}
		return refer_max;

	}

	// �۾���(���)

	// �Խù� ��Ϻ���
	public List<Board> list(int cpage, int pagesize) {


		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = null;

		try {
			conn = ds.getConnection();
			String sql = "select * from  "
					+ "(select rownum rn , idx ,writer , email, homepage, pwd , subject , content, writedate, readnum "
					+ "	                 , filename, filesize , refer , depth , step "
					+ "             from ( SELECT * FROM jspboard ORDER BY refer DESC , step ASC ) " + // 
																										// 
					"             where rownum <= ?" + // end row
					") where rn >= ?"; // start row
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize - 1); // 1*5 -(5-1) = 1
			int end = cpage * pagesize; // 1 * 5 = 5

			pstmt.setInt(1, end);
			pstmt.setInt(2, start);

			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while (rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getDate("writedate"));
				board.setReadnum(rs.getInt("readnum"));
				board.setRefer(rs.getInt("refer"));
				board.setStep(rs.getInt("step"));
				board.setDepth(rs.getInt("depth"));

				list.add(board); 
				System.out.println(list);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); // 
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		}
		return list;

	}

	public int totalBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null; //
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from jspboard"; //
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalcount = rs.getInt("cnt"); //
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); // 
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		return totalcount;
	}

	public Board getContent(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;

		try {
			conn = ds.getConnection();
			String sql = "select * from jspboard where idx=?"; // 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString("writer");
				String email = rs.getString("email");
				String homepage = rs.getString("homepage");
				String pwd = rs.getString("pwd");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String filename = rs.getString("filename");

				System.out.println("writer : " + writer);
				System.out.println("email : " + email);
				System.out.println("homepage : " + homepage);
				System.out.println("pwd : " + pwd);
				System.out.println("subject : " + subject);
				System.out.println("content : " + content);
				System.out.println("filename : " + filename);

				java.sql.Date writedate = rs.getDate("writedate");
				int readnum = rs.getInt("readnum");
				int filesize = rs.getInt("filesize");

				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");

				board = new Board(idx, writer, pwd, subject, content, writedate, readnum, filename, filesize, homepage,
						email, refer, depth, step);
			}

		} catch (Exception e) {
			System.out.println("content: " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 
			} catch (Exception e2) {

			}
		}

		return board;
	}

	public boolean getReadNum(String idx) {
		// update jspboard set readnum = readnum + 1 where idx=?
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = ds.getConnection();
			String sql = "update jspboard set readnum = readnum + 1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);

			int row = pstmt.executeUpdate();
			if (row > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();// 
			} catch (Exception e) {

			}
		}
		return result;
	}

	public int deleteOk(String idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from jspboard where idx=?";
		int row = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			row = pstmt.executeUpdate();
			System.out.println("row : " + row);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}

	public int replywrite(int idx_fk, String writer, String userid, String content, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into reply(no,writer,userid,content,pwd,idx_fk) "
					+ " values(reply_no.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, userid);
			pstmt.setString(3, content);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, idx_fk);

			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {

			}
		}

		return row;
	}

	public List<Reply> replylist(String idx_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ArrayList<Reply> list = null;

		System.out.println("idx_fk " + idx_fk);
		
		try {
			conn = ds.getConnection();
			String reply_sql = "select * from reply where idx_fk=? order by no desc";
		
			pstmt = conn.prepareStatement(reply_sql);
			pstmt.setString(1, idx_fk);
			rs = pstmt.executeQuery();
			//pstmt.close();
			String sql2 = "select to_char(writedate, 'yyyy-MM-dd') as cDate from reply where idx_fk=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, idx_fk);
			rs2 = pstmt.executeQuery();

			list = new ArrayList<>();
			while (rs.next() && rs2.next()) {
				int no = Integer.parseInt(rs.getString("no"));
				String writer = rs.getString("writer");
				String userid = rs.getString("userid");
				String pwd = rs.getString("pwd");
				String content = rs.getString("content");
				//java.sql.Date writedate = rs.getDate("writedate");
				String date = rs2.getString("cDate");
				int idx = Integer.parseInt(rs.getString("idx_fk"));

				Reply replydto = new Reply(no, writer, userid, pwd, content, date, idx);
				list.add(replydto);
			}
			System.out.println("리스트DAO");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				rs2.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	// ��� �����ϱ�
	public int replyDelete(String no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			String replydelete = "delete from reply where no=?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(replydelete);
			pstmt.setString(1, no);
			row = pstmt.executeUpdate();
			System.out.println("삭제DAO");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();// 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return row;
	}
	
	public int replyUpdate(int idx_fk, int no, String content) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;

		try {
			conn = ds.getConnection();
			String sql = "update reply set content = ? where no = ? and idx_fk = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, content);
				pstmt.setInt(2, no);
				pstmt.setInt(3, idx_fk);
				row = pstmt.executeUpdate();
				System.out.println("댓글수정..row : " + row);
			} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}
	
	

	public int reWriteOk(Board boardata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ds.getConnection();

			int idx = boardata.getIdx(); 

			String writer = boardata.getWriter();
			String email = boardata.getEmail();
			String homepage = boardata.getHomepage();
			String pwd = boardata.getPwd();
			String subject = boardata.getSubject();
			String content = boardata.getContent();
			String filename = boardata.getFilename();
			int filesize = 0;

			String refer_depth_step_sal = "select refer , depth , step from jspboard where idx=?";

			String step_update_sql = "update jspboard set step= step+1 where step  > ? and refer =? ";

			String rewrite_sql = "insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer,depth,step)"
					+ " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?,?,?)";

			pstmt = conn.prepareStatement(refer_depth_step_sal);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");

				pstmt = conn.prepareStatement(step_update_sql);
				pstmt.setInt(1, step);
				pstmt.setInt(2, refer);
				pstmt.executeUpdate();

				// filename,filesize,refer,depth,step
				pstmt = conn.prepareStatement(rewrite_sql); // 
				pstmt.setString(1, writer);
				pstmt.setString(2, pwd);
				pstmt.setString(3, subject);
				pstmt.setString(4, content);
				pstmt.setString(5, email);
				pstmt.setString(6, homepage);
				pstmt.setString(7, filename);

				// �亯
				pstmt.setInt(8, refer);
				pstmt.setInt(9, depth + 1); //
				pstmt.setInt(10, step + 1); //

				int row = pstmt.executeUpdate();
				if (row > 0) {
					result = row;
				} else {
					result = -1;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 
			} catch (Exception e) {

			}
		}

		return result;
	}

	public Board getEditContent(String idx) {
		return this.getContent(Integer.parseInt(idx));
	}

	public int boardEdit(Board board) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;

		try {
			conn = ds.getConnection();
			String sql = "update jspboard set writer=? , email=? , homepage=? ,"
					+ " subject=? , content=? , filename=? where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, board.getWriter());
				pstmt.setString(2, board.getEmail());
				pstmt.setString(3, board.getHomepage());
				pstmt.setString(4, board.getSubject());
				pstmt.setString(5, board.getContent());
				pstmt.setString(6, board.getFilename());
				pstmt.setInt(7, board.getIdx());
				row = pstmt.executeUpdate();
				 System.out.println("수정..row : " + row);
			} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}

		return row;
	}
	
	
	public List<Board> searchHie(String sen, String select) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<Board> list = new ArrayList<Board>();

		try {
			conn = ds.getConnection();
			if(select.equals("title")) {
				System.out.println("sen : " + sen);
			 	System.out.println("일로와라.222.");
				String sql = "select idx, subject, writer, writedate, readnum from jspboard where subject like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sen + "%");
				rs = pstmt.executeQuery();
			}else {
				String sql = "select idx, subject, writer, writedate, readnum from jspboard where writer like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sen + "%");
				rs = pstmt.executeQuery();
			}
	
		String sql2 = "select to_char(writedate, 'yyyy-MM-dd') as cDate from jspboard where subject like ?";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, "%" + sen + "%");
		rs2 = pstmt.executeQuery();
		
			/*
			 * while () {
			 * 
			 * System.out.println("ww " + rs2.getString("cDate")); }
			 */
		
			while (rs.next() && rs2.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt(1));
				board.setSubject(rs.getString(2));
				board.setWriter(rs.getString(3));
				/*
				 * java.sql.Date writedate = rs.getDate("writedate");
				 * System.out.println("writedate : " + writedate);
				 * board.setWritedate(writedate);
				 */
				board.setWritedateStr(rs2.getString("cDate"));
				board.setReadnum(rs.getInt(5));				
				list.add(board);
			}
			System.out.println("dao list : " + list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	

}
