package kr.or.bit.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

public class EmpEditOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		EmpDao dao = new EmpDao(); // POINT
		
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
			
			String type = multi.getParameter("type");
			
			String empno = multi.getParameter("empno");
			String ename = multi.getParameter("ename");
			String job = multi.getParameter("job");
			String mgr = multi.getParameter("mgr");
			String hiredate = multi.getParameter("hiredate");
			String sal = multi.getParameter("sal");
			String comm = multi.getParameter("comm");
			String deptno = multi.getParameter("deptno");
			
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			String filename = multi.getFilesystemName(file);
			
			String tempFileName = dao.empFilename(Long.parseLong(empno));
			System.out.println("Long.parseLong(empno) " + Long.parseLong(empno));
			System.out.println("이름 " + tempFileName);
			
		
			if(filename == null) {
				filename = tempFileName;
			}
		
			String orifilename = multi.getOriginalFileName(file);
		
		int result = 0;
		try {
			result = dao.updateOkEmp(Long.parseLong(empno), ename, job, Long.parseLong(mgr), hiredate,
					Long.parseLong(sal), Long.parseLong(comm), Long.parseLong(deptno), filename);
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		String msg = "";
		String url = "";
		
		System.out.println("result : " + result);
		if (result > 0) {
			msg = "수정 성공";
			if(type.equals("dataTable")) {
				url = "dataTable.do";
			}else {
				url = "EmpTable.do";
			}
			
		} else {
			msg = "수정 실패";
			url = "update.do?empno=" + empno;
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		request.setAttribute("ename", ename);
		request.setAttribute("job", job);
		request.setAttribute("mgr", mgr);
		request.setAttribute("hiredate", hiredate);
		request.setAttribute("sal", sal);
		request.setAttribute("comm", comm);
		request.setAttribute("deptno", deptno);
		request.setAttribute("empno", empno);

		forward.setPath("/WEB-INF/common/redirect.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return forward;
	}

}
