package wxt.business.servlet;

import net.sf.json.JSONObject;
import wxt.business.actions.Action_Bloc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by admin on 2015/3/12.
 */
//无权权限或者报错的跳转servlet
public class NoPermissionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        //获取attribute
        json.put("statusCode", request.getSession().getAttribute("PermisiionStatusCode"));
        json.put("message", request.getSession().getAttribute("PermisiionMessge"));
        //清空attribue
        request.getSession().removeAttribute("PermisiionStatusCode");
        request.getSession().removeAttribute("PermisiionMessge");
        json.put("navTabId", "");
        json.put("rel", "");
        json.put("callbackType", "");
        json.put("forwardUrl", "");
        response.getWriter().print(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
