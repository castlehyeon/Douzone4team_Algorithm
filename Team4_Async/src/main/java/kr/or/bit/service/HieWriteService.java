package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class HieWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String type = request.getParameter("type");
		
		System.out.println("type33 : " + type);
		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		BoardDao dao = new BoardDao();
		Board board = new Board();
		board.setSubject(subject);
		board.setWriter(writer);
		board.setEmail(email);
		board.setHomepage(homepage);
		board.setContent(content);
		board.setPwd(pwd);
		
		int writeResult = dao.writeok(board);
		
		String msg = "";
		String url = "";
		if (writeResult > 0) {
			msg = "계층형 등록 성공";
			url = "HieEmpTable.do";
		} else {
			msg = "계층형 등록 실패";
			url = "history.back()";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
			
			
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/common/redirect.jsp");

		return forward;
		}
	}
	
