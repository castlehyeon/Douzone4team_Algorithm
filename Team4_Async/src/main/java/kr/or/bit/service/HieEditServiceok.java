package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class HieEditServiceok implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();	
		BoardDao dao = new BoardDao();
		Board board = new Board();
		
		String idx = request.getParameter("idx");
		String pwd = request.getParameter("pwd");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");
		
		board.setIdx(Integer.parseInt(idx));
		board.setPwd(pwd);
		board.setWriter(writer);
		board.setEmail(email);
		board.setHomepage(homepage);
		board.setSubject(subject);
		board.setContent(content);
		board.setFilename(filename);

		int result = dao.boardEdit(board);
	
		request.setAttribute("board", board);			
	
		forward.setPath("/WEB-INF/views/HieEmpDetail.jsp");

		return forward;
		}
	}
	
