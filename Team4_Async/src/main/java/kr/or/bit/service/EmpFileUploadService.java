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

public class EmpFileUploadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		int size = 1024*1024*10; //10M 네이버 계산기
		String filename="";
		try {
			MultipartRequest multi = new MultipartRequest(
					request, //기존에 있는  request 객체의 주소값 
					uploadpath, //실 저장 경로 (배포경로)
					size, //10M
					"UTF-8",
					new DefaultFileRenamePolicy() //파일 중복(upload 폴더 안에:a.jpg -> a_1.jpg(업로드 파일 변경) )
					);
			
			//파일 업로드 완료
			
			//여기까지 수행하면 upload 폴더에 파일이 저장
			
			Enumeration fileNames = multi.getFileNames(); //파일 이름 반환
			System.out.println("fileNames : " + fileNames);
			
			while(fileNames.hasMoreElements()){
				
			
			}

			String file = (String)fileNames.nextElement();
			System.out.println("file : " + file);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //파일 업로드 완료
		
		//여기까지 수행하면 upload 폴더에 파일이 저장
		
	ActionForward forward = new ActionForward();
	forward.setPath("/WEB-INF/views/updateView.jsp");

	return forward;
	}

}
