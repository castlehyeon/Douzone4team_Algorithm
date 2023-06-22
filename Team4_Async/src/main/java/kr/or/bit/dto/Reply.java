package kr.or.bit.dto;

import java.util.Date;


public class Reply {
	private int no;
	private String writer;
	private String userid;
	private String pwd;
	private String content;
	private Date writedate;
	private String writedateStr;
	private int idx_fk;

	public String getWritedateStr() {
		return writedateStr;
	}

	public void setWritedateStr(String writedateStr) {
		this.writedateStr = writedateStr;
	}

	public Reply() {}

	public Reply(int no, String writer, String userid, String pwd, String content, String writedateStr, int idx_fk) {
		super();
		this.no = no;
		this.writer = writer;
		this.userid = userid;
		this.pwd = pwd;
		this.content = content;
		this.writedateStr = writedateStr;
		this.idx_fk = idx_fk;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public int getIdx_fk() {
		return idx_fk;
	}

	public void setIdx_fk(int idx_fk) {
		this.idx_fk = idx_fk;
	}
	

	@Override
	public String toString() {
		return "reply [no=" + no + ", writer=" + writer + ", userid=" + userid + ", pwd=" + pwd + ", content=" + content
				+ ", writedate=" + writedate + ", idx_fk=" + idx_fk + "]";
	}
	
	
	
}
