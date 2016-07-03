package wxt.business.servlet;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.User;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by wuxiutong on 2015/9/2.
 */
public class ChangePWD extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        String userid = request.getSession().getAttribute("userid").toString();
        String oldPWD = request.getParameter("oldPWD");
        String newPWD = request.getParameter("newPWD");
        Session session = null;
        Transaction transaction = null;
        JSONObject json = new JSONObject();
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userid, "userID");
            if (null != list && list.iterator() != null && list.iterator().hasNext()) {
                User user = (User) list.iterator().next();
                if (oldPWD.equals(user.getPassword())) {
                    Query query = session.createQuery("update User  set password = :newPWD where userID = :userid");
                    query.setString("newPWD", newPWD);
                    query.setString("userid", userid);
                    if (query.executeUpdate() > 0) {
                        json.put("statusCode", "200");
                        json.put("message", "修改成功，请记住新密码！");
                        json.put("navTabId", "");
                        json.put("rel", "");
                        json.put("callbackType", "");
                        json.put("forwardUrl", "");
                        transaction.commit();
                    }
                }else {
                    json.put("statusCode", "300");
                    json.put("message", "原始密码错误！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");
                }
            }else{
                json.put("statusCode", "300");
                json.put("message", "查找用户时失败，请重新登陆修改！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "修改密码时发生错误，请重新登陆尝试");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if (null != session) {
                session.close();
            }
            response.getWriter().print(json);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
