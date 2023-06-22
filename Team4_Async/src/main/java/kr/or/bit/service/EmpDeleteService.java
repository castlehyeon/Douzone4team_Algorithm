package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;

public class EmpDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
			String empno = request.getParameter("empno");
			String table = request.getParameter("table");
	
		   EmpDao dao = new EmpDao(); // POINT
			int result = 0;
			try {
				result = dao.deleteEmp(Long.parseLong(empno));
			} catch(Exception e) {
				e.printStackTrace();
				result = 0;
			}
			
			System.out.println(result);
			String msg = "";
			String url = "";
			if (result > 0) {
				msg = "삭제 성공";
				if(table != null) {
					url = "dataTable.do";
				}else {
					url = "EmpTable.do";
				}
				
			} else {
				msg = "삭제 실패";
				if(table != null) {
					url = "dataTable.do";
				}else {
					url = "EmpTable.do";
				}
			}

			request.setAttribute("msg", msg);
			request.setAttribute("url", url);

			ActionForward forward = new ActionForward();
			forward.setPath("/WEB-INF/common/redirect.jsp");

			return forward;
		}
}
