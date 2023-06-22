package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;


public class EmpDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		/*
		 * String idx = request.getParameter("idx");
		 * 
		 * if (idx == null || idx.trim().equals("")) {
		 * response.sendRedirect("EmpTable.jsp");
		 * 
		 * } idx = idx.trim();
		 */
		
		String type = request.getParameter("type");
		
		String empnoStr = request.getParameter("empno");
		Long empno = Long.parseLong(empnoStr.trim());
		
		EmpDao empdao = new EmpDao();
		Emp emp = empdao.detailList(empno);
		
		
		request.setAttribute("emp", emp);
		request.setAttribute("type", type);
		
		/*
		 * EmpDao empdao = new EmpDao(); List<Emp> delist = empdao.detailList(elist);
		 * System.out.println(elist); request.setAttribute("list", elist);
		 */
		
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/detailView.jsp");
	

	return forward;
	}

}
