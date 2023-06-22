package kr.or.bit.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.EmpDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/EmpChart.do")
public class EmpChart extends HttpServlet {
       
    public EmpChart() {
        super();
    }
    
    private void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	EmpDao empDao = EmpDao.getInstance();
    	int empTotal = empDao.EmpTotal();
    	int empSalAvg = empDao.EmpSalAvg();
    	int deptTotal = empDao.DeptTotal();
    	int empJobTotal = empDao.EmpJobTotal();
    	
    	JSONArray jsonArr = new JSONArray();
    	
    	JSONObject obj = new JSONObject();
		obj.put("empTotal", empTotal);
		obj.put("empSalAvg", empSalAvg);
		obj.put("deptTotal", deptTotal);
		obj.put("empJobTotal", empJobTotal);
		
		jsonArr.add(obj);
				
    	response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jsonArr);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
