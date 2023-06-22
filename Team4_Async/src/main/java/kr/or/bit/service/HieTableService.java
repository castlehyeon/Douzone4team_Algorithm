package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Emp;

public class HieTableService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			
		BoardDao dao = new BoardDao();
		int totalboardcount = dao.totalBoardCount();

		String ps = request.getParameter("ps"); // pagesize
		System.out.println("ps : " + ps);
		String cp = request.getParameter("cp"); // current page

		if (ps == null || ps.trim().equals("")) {
			ps = "10"; 
		}

		if (cp == null || cp.trim().equals("")) {
			cp = "1"; 
		}
		
		int pagesize = Integer.parseInt(ps);
		int cpage = Integer.parseInt(cp); 
		int pagecount = 0; 

		if (totalboardcount % pagesize == 0) {
			pagecount = totalboardcount / pagesize; 
		} else {
			pagecount = (totalboardcount / pagesize) + 1;  
		}

		List<Board> list = dao.list(cpage, pagesize);

		request.setAttribute("list", list);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("cpage", cpage);
		request.setAttribute("pagecount", pagecount);
			
			
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/HieEmpTable.jsp");

		return forward;
		}
	}
	
