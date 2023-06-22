package kr.or.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.AjaxTableListService;
import kr.or.bit.service.EmpAddService;
import kr.or.bit.service.EmpChartService;
import kr.or.bit.service.EmpDataTableService;
import kr.or.bit.service.EmpDeleteService;
import kr.or.bit.service.EmpDeptNoListService;
import kr.or.bit.service.EmpDetailService;
import kr.or.bit.service.EmpEditOkService;
import kr.or.bit.service.EmpEditService;
import kr.or.bit.service.EmpFileUploadService;
import kr.or.bit.service.HieTableService;
import kr.or.bit.service.HieWriteService;
import kr.or.bit.service.HieSearchService;
import kr.or.bit.service.EmpJobListService;
import kr.or.bit.service.EmpListService;
import kr.or.bit.service.EmpLoginService;
import kr.or.bit.service.EmpLogoutService;
import kr.or.bit.service.EmpSearchService;
import kr.or.bit.service.HieDeleteService;
import kr.or.bit.service.HieDetailService;
import kr.or.bit.service.HieEditService;
import kr.or.bit.service.HieEditServiceok;
import kr.or.bit.service.HieReWriteService;
import kr.or.bit.service.HieReWriteServiceok;
import kr.or.bit.service.HieReplyAddService;
import kr.or.bit.service.HieReplyDeleteService;
import kr.or.bit.service.HieReplyUpdateService;

@WebServlet("*.do")
public class FrontEmpController extends HttpServlet {

	public FrontEmpController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url_Command = requestURI.substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		if (url_Command.equals("/Main.do")) { // 
			forward = new ActionForward();
			forward.setPath("/index.jsp");
			
		} else if (url_Command.equals("/login.do")) { // 
			action = new EmpLoginService();
			forward = action.execute(request, response);
			
		} else if(url_Command.equals("/logout.do")) { // 
			action = new EmpLogoutService();
			forward = action.execute(request, response);
			
		} else if(url_Command.equals("/EmpTable.do")) { //
			action = new EmpListService();
			forward = action.execute(request, response);
			
		} else if(url_Command.equals("/search.do")) { 
			action = new EmpSearchService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/EmpWrite.do")) { //
			forward = new ActionForward();
			String type = request.getParameter("type");
			request.setAttribute("type", type);
			forward.setPath("/WEB-INF/views/EmpWrite.jsp");
			
		}else if(url_Command.equals("/EmpWriteok.do")) { //
			action = new EmpAddService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/delete.do")) { 
			action = new EmpDeleteService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/detailView.do")) {
			action = new EmpDetailService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/update.do")) { //
			action = new EmpEditService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/updateok.do")) { //
			action = new EmpEditOkService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/chartView.do")) { //
			forward = new ActionForward();
			forward.setPath("/WEB-INF/views/chartView.jsp");
			
		}else if(url_Command.equals("/chartViewok.do")) { //
			action = new EmpChartService();
			forward = action.execute(request, response);
			
		}else if(url_Command.equals("/upload.do")) { //
			action = new EmpFileUploadService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/jobList.do")) { //
			action = new EmpJobListService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/deptNoList.do")) { //
			action = new EmpDeptNoListService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/dataTable.do")) { //화면 + 로직 )
			action = new EmpDataTableService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpTable.do")) { //화면 + 로직 )
			action = new HieTableService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpWrite.do")) { //화면 )
			forward = new ActionForward();
			String type = request.getParameter("type");
			request.setAttribute("type", type);
			forward.setPath("/WEB-INF/views/HieEmpWrite.jsp");
		}else if(url_Command.equals("/HieEmpWriteok.do")) { //
			action = new HieWriteService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpDetail.do")) { //
			action = new HieDetailService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpDelete.do")) { //
			action = new HieDeleteService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpEdit.do")) { //
			action = new HieEditService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpEditok.do")) { //
			action = new HieEditServiceok();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpReWrite.do")) { //
			action = new HieReWriteService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieEmpReWriteok.do")) { //
			action = new HieReWriteServiceok();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/HieSearch.do")) { //
			action = new HieSearchService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/replyDelete.do")) { //
			action = new HieReplyDeleteService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/replyAdd.do")) { //
			action = new HieReplyAddService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/replyUpdate.do")) { //
			action = new HieReplyUpdateService();
			forward = action.execute(request, response);
		}else if(url_Command.equals("/ajaxPaging.do")) { //화면 )
			forward = new ActionForward();
			forward.setPath("/WEB-INF/views/ajaxPaging.jsp");
		}else if(url_Command.equals("/ajaxTable.do")) { //화면 )
			action = new AjaxTableListService();
			forward = action.execute(request, response);
		}
		
		
	
	
		
		if (forward != null) {
			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
