package wxt.business.servlet.users;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by wuxiutong on 2015/8/20.
 */

public class GetUserSession extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        try {
            String userid = request.getSession().getAttribute("userid").toString();
            String username =  request.getSession().getAttribute("username").toString();
            if(null != userid && !"".equals(userid.trim())){
                json.put("statusCode",200);
                json.put("userid",userid);
                json.put("username",username);
            } else {
                json.put("statusCode", 300);
                json.put("userid","");
                json.put("username","");
            }
        } catch (Exception e) {
            json.put("statusCode", 300);
            json.put("userid","");
            json.put("username","");
            e.printStackTrace();
        } finally {
            response.getWriter().print(json);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
