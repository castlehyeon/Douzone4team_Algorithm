package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class HieReWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String subject = request.getParameter("subject");
		
		
		Board board = new Board();
		board.setSubject(subject);
		board.setIdx(Integer.parseInt(idx));
		
		request.setAttribute("board", board);
			
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/HieEmpReWrite.jsp");

		return forward;
		}
	}
	
