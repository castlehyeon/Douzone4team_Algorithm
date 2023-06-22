package kr.or.bit.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

public class EmpListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			
			EmpDao empdao = new EmpDao();
		
			String ps = request.getParameter("ps"); //pagesize
			String cp = request.getParameter("cp");
			
			
			if (cp == null || cp.trim().equals("")) {
				cp = "1";
			}
			if (ps == null || ps.trim().equals("")) {
				ps = "5";
			}
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int totalcount = empdao.totallistCount();
			int pagecount = 0;
			
			
			if (totalcount % pagesize == 0) { 
				pagecount = totalcount / pagesize;
			} else {
				pagecount = (totalcount / pagesize) + 1;
			}
			List<Emp> elist = empdao.list(cpage, pagesize);
			
			request.setAttribute("list", elist);
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("cpage", cpage);
			
			
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/EmpTable.jsp");

		return forward;
		}
	}
	
