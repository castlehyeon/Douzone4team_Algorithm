package kr.or.bit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpSearchService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			EmpDao empDao = EmpDao.getInstance();
			List<Emp> list = new ArrayList<Emp>();
			String empno = request.getParameter("empno");
			String ename = request.getParameter("ename");
			String keyword = request.getParameter("keyword");

			if (empno != null && ename == null) {
				list = empDao.searchEmpno(empno);
			} else {
				list = empDao.searchEname(ename);
			}
			

			JSONArray jsonArr = JSONArray.fromObject(list); 
		

	    	response.setContentType("application/x-json; charset=UTF-8");
			try {
				response.getWriter().print(jsonArr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		

	}

}