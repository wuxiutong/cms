package wxt.business.servlet.users;

import net.sf.json.JSONObject;
import wxt.dao.UserUtils;
import wxt.exceptions.CustomException;
import wxt.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by wuxiutong on 2015/8/20.
 */

public class UserLogin {
    public void execute(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = UserUtils.Login(username, password);
            if(null == username || "".equals(username.trim())){
                throw new CustomException("请使用户ID或者用户名登录！");
            }
            if (null != user) {
                json.put("userid", user.getUserID());
                json.put("username", user.getUserName());
                json.put("email", user.getEmail());
                json.put("ps", user.getPs());
                request.getSession().setAttribute("userid", user.getUserID());
                request.getSession().setAttribute("ps", user.getPs());
                request.getSession().setAttribute("username", user.getUserName());
                request.getSession().setAttribute("email",user.getEmail());
                response.sendRedirect("/index.jsp");
            } else {
                request.getSession().setAttribute("message","登陆失败,请检查用户名和密码！");
                response.sendRedirect("/login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", e.getMessage());
            response.sendRedirect("/login.jsp");
        } finally {
          //  response.getWriter().print(json);
        }
    }

}
