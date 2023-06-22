package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class HieEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();	
		BoardDao dao = new BoardDao();
		Board board = new Board();
		
		String idx = request.getParameter("idx");
		
		board = dao.getEditContent(idx);
	
		request.setAttribute("board", board);			
	
		forward.setPath("/WEB-INF/views/HieEmpEdit.jsp");

		return forward;
		}
	}