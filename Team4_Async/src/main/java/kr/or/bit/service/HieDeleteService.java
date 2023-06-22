package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class HieDeleteService implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		String idx = request.getParameter("idx");
		
		BoardDao board = new BoardDao();
		int result = board.deleteOk(idx);
	
		
		forward.setPath("HieEmpTable.do");

		return forward;
		}
	}
	
