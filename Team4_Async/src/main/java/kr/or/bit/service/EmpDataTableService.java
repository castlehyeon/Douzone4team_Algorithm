package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

public class EmpDataTableService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			
			EmpDao empdao = new EmpDao();
			
			List<Emp> elist = empdao.dataTablelist();
			
			request.setAttribute("list", elist);
			
			
		ActionForward forward = new ActionForward();
		forward.setPath("/WEB-INF/views/dataTable.jsp");

		return forward;
		}
	}
	
