package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;

public class HieDetailService  implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		ActionForward forward = new ActionForward();	
		BoardDao dao = new BoardDao();
		Board board = new Board();
		
		String idx = request.getParameter("idx").trim();
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
	
		if (cpage == null || cpage.trim().equals("")) {
			//default 값 설정
			cpage = "1"; //1번째 페이지를 보겠다.
		}

		if (pagesize == null || pagesize.trim().equals("")) {
			//default 값 설정
			pagesize = "5"; //5개씩 묶음을 잡겠다
		}
		
		dao.getReadNum(idx);
		board = dao.getContent(Integer.parseInt(idx));
		
		List<Reply> replyList = dao.replylist(idx);
		
		request.setAttribute("board", board);		
		request.setAttribute("idx", idx);	
		request.setAttribute("replyList", replyList);	
		
	
		forward.setPath("/WEB-INF/views/HieEmpDetail.jsp");

		return forward;
		}
	}
	
