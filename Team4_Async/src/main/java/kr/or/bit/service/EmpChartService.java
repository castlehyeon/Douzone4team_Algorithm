package kr.or.bit.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpChartService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		EmpDao empdao = new EmpDao();
		List<Emp> list = empdao.chartList();
		
		request.setAttribute("list", list);
		
		JSONArray jsonArr = JSONArray.fromObject(list); 
		
		
//		request.setAttribute("jsonArr", jsonArr);
		
		try {
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonArr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * ActionForward forward = new ActionForward();
		 * forward.setPath("/WEB-INF/views/chartView.jsp");
		 */

	return null;
	}
}
