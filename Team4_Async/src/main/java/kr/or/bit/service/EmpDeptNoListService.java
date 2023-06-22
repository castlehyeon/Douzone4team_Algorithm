package kr.or.bit.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import net.sf.json.JSONArray;

public class EmpDeptNoListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		try {
			
			EmpDao dao = new EmpDao(); 
			List deptNoList = dao.deptNoList();
		
			JSONArray jsonArr = JSONArray.fromObject(deptNoList); 
			
	    	response.setContentType("application/x-json; charset=UTF-8");
			try {
				response.getWriter().print(jsonArr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
		
	}

}
