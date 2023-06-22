package kr.or.bit.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;
import kr.or.bit.dto.Reply;
import net.sf.json.JSONArray;

public class HieReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();	
		BoardDao dao = new BoardDao();
		Board board = new Board();
		
		String idx = request.getParameter("idx");
		String comment = request.getParameter("comment");
		
		
		int result = dao.replywrite(Integer.parseInt(idx), "admin", "admin", comment, "admin"); //데이터 등록
		List<Reply> replyList = dao.replylist(idx); 	//데이터 리스트 불러오는
		

		JSONArray jsonArr = JSONArray.fromObject(replyList); 
		
		
		try {
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonArr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		}
	}
	