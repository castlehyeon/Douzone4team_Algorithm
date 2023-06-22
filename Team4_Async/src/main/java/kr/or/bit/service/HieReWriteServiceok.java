package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class HieReWriteServiceok implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardDao dao = new BoardDao();
		Board board = new Board();
		board.setIdx(Integer.parseInt(idx));
		board.setSubject(subject);
		board.setWriter(writer);
		board.setEmail(email);
		board.setHomepage(homepage);
		board.setContent(content);
		board.setPwd(pwd);
		
		int reWriteResult = dao.reWriteOk(board);
		
		String msg = "";
		String url = "";
		if (reWriteResult > 0) {
			msg = "계층형 답변 성공";
			url = "HieEmpTable.do";
		} else {
			msg = "계층형 답변 실패";
			url = "HieEmpTable.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
			
			
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/common/redirect.jsp");

		return forward;
		}
	}
	
