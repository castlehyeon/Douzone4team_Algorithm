package kr.or.bit.service;


import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;

public class EmpAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		String type = request.getParameter("type");
		
		
		int size = 1024*1024*10; //10M 네이버 계산기
		ActionForward forward = new ActionForward();
		String context = request.getContextPath();
		//output, input을 만들지 않아도됨, 좋음!!
		MultipartRequest multi = new MultipartRequest(
				request, //기존에 있는  request 객체의 주소값 
				uploadpath, //실 저장 경로 (배포경로)
				size, //10M
				"UTF-8",
				new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경) )
		);
		try {
			
		String empno = multi.getParameter("empno");
		String ename = multi.getParameter("ename");
		String job = multi.getParameter("job");
		String mgr = multi.getParameter("mgr");
		String hiredate = multi.getParameter("hiredate");
		String sal = multi.getParameter("sal").replaceAll(",", "");
		String comm = multi.getParameter("comm").replaceAll(",", "");;
		String deptno = multi.getParameter("deptno");
		
		Enumeration filenames = multi.getFileNames();
		
		String file = (String)filenames.nextElement();
		String filename = multi.getFilesystemName(file);
		
		if(filename == null) {
			filename = "emp.jpg";
		}
		//String orifilename = multi.getOriginalFileName(file);
		
		EmpDao dao = new EmpDao(); // POINT
		int result = 0;
		try {
			result = dao.insertEmp(Long.parseLong(empno), ename, job, Long.parseLong(mgr), hiredate,
					Long.parseLong(sal), Long.parseLong(comm), Long.parseLong(deptno), filename);
		} catch(Exception e) {
			e.printStackTrace();
			result = 0;
		}

		String msg = "";
		String url = "";
		if (result > 0) {
			msg = "사원 등록 성공";
			if(type.equals("dataTable")) {
				url = "dataTable.do";
			}else {
				url = "EmpTable.do";
			}
		} else {
			msg = "사원 등록 실패";
			url = "EmpWrite.do";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		List jobList = dao.jobList();
		
		forward.setPath("/WEB-INF/common/redirect.jsp");
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return forward;
		
	}

}
