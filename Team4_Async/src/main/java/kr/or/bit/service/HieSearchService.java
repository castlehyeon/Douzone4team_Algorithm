package kr.or.bit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Emp;
import net.sf.json.JSONArray;

public class HieSearchService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
		BoardDao boardDao = new BoardDao();
		List<Board> list = new ArrayList<Board>();
		String title = request.getParameter("something[title]");
		String writer = request.getParameter("something[writer]");
		
		
		  	if (title != null && writer == null) { 
		  		System.out.println("일로와라..");
			  	list = boardDao.searchHie(title, "title"); 
			  } else { 
				  list = boardDao.searchHie(writer, "writer"); 
			  }

		
		JSONArray jsonArr = JSONArray.fromObject(list); 
		
		response.setContentType("application/x-json; charset=UTF-8");
		try {
			response.getWriter().print(jsonArr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println("jsonArr : " + jsonArr);

    	response.setContentType("application/x-json; charset=UTF-8");
		
		
		
		return null;
		
	}

}