package wxt.business.servlet;

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
public class BlocBaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        try {
            Action_Bloc action = new Action_Bloc();
            Class clazz = action.getClass();
            String requestServlet = request.getParameter("requestServlet");
            String url = request.getRequestURL().toString();
            String servlet = request.getServletPath();
            //获取初始化的请求参数
            String actionStr = requestServlet.substring(requestServlet.indexOf(".") + 1);
            System.out.println("方法名："+actionStr+" ");
            //按照传递的名称获取方法
            Method method = clazz.getMethod(actionStr, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.invoke(action, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
