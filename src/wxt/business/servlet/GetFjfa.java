package wxt.business.servlet;

import net.sf.json.JSONObject;
import wxt.utils.FjgzUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2015/3/16.
 */
@WebServlet(name = "GetFjfa")
public class GetFjfa extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-json");
            json = new JSONObject();
            json.put("fjfa", FjgzUtils.getFjfa(request.getParameter("dm")));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.getWriter().print(json.toString());
            System.out.println(json.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
