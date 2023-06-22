package kr.or.bit.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionForward  {
	private String path = null; 

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
