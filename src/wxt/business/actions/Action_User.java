package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.exceptions.CustomException;
import wxt.model.Role;
import wxt.model.User;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by wuxiutong on 2015/9/2.
 */
public class Action_User {
    //增加user
    public  void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            String userID = request.getParameter("userID");
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String ps = request.getParameter("ps");
            User user  = new User();
            user.setUserID(userID);
            user.setUserName(userName);
            user.setPassword(password);
            user.setPs(ps);
            session.save(user);
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "保存成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message",e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if(null != session){
                session.close();
            }
            response.getWriter().print(json);
        }
    }
    //修改user
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            String userID = request.getParameter("userID");
            String userName = request.getParameter("userName");
            String ps = request.getParameter("ps");
            String hql = "update User t set t.userName = :userName,t.ps=:ps where t.userID = :userID";
            Query query = session.createQuery(hql);
            query.setString("userID", userID);
            query.setString("userName", userName);
            query.setString("ps", ps);
            query.executeUpdate();
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "修改成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message",e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if(null != session){
                session.close();
            }
            response.getWriter().print(json);
        }
    }
    //删除user
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            //当前session中userid
            String userid = request.getSession().getAttribute("userid").toString();
            //前台传递过来的userID
            String userID = request.getParameter("userID").trim();
           //如果检测是否是当前登录的用户，如果是相同则禁止修改
            if(userID.equals(userid)){
                throw  new CustomException("禁止删除自己");
            }
            String hql = "delete User t  where t.userID = :userID";
            Query query = session.createQuery(hql);
            query.setString("userID",userID);
            query.executeUpdate();
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "删除成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message",e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if(null != session){
                session.close();
            }
            response.getWriter().print(json);
        }
    }
    //获取一个user
    public void getOneUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String result = "";
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            String userID = request.getParameter("userID");
            if(null==userID || "".equals(userID.trim())){
                throw new CustomException("传递角色ID为空，请检查！");
            }
            List list = QueryData.getSomeEntity("User",userID,"userID");
            if(null==list || list.iterator()==null || !list.iterator().hasNext()){
                throw new CustomException("无法从数据库中查找到当前用户！");
            }
            User user = (User)list.iterator().next();
            json.put("statusCode", "200");
            json.put("message", "查找成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
            json.put("userID",user.getUserID());
            json.put("userName",user.getUserName());
            json.put("ps",user.getPs());

        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "查找用户信息错误！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } finally {
            if(null != session){
                session.close();
            }
            response.getWriter().print(json);
        }
    }
    //获取所有user
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String result = "";
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            List list = QueryData.getAllEntity("User");
            if(null==list){
                throw new CustomException("获取用户错误！");
            }
            Iterator it = list.iterator();
            sb.append("[");
            while (it.hasNext()) {
                User user = (User) it.next();
                if(user.getUserID().equals("1")){
                    continue;
                }
                sb.append("{\"id\":\"" + user.getUserID() + "\",\"name\":\"" +user.getUserName()+ "\",\"pId\":\"0\"},");
            }
            result= sb.toString().substring(0,sb.length()-1)+"]";
        } catch (Exception e) {
            e.printStackTrace();
            result ="";
        } finally {
            System.out.println("++++返回用户："+result);
            if(null != session){
                session.close();
            }
            response.getWriter().print(result);
        }
    }
    //获取user所属的角色
    public void getUserRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String result = "";
        String userDM = "";
        String[] roleArray = null;
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            userDM = request.getParameter("dm");
            if (null == userDM || "".equals(userDM.trim())) {
                throw new CustomException("传递的userDM代码有错！");
            }
            //获取角色的权限
            List userList = QueryData.getSomeEntity("User", userDM, "userID");
            if (userList == null) {
                // throw new CustomException("无法查询到当前role");
            }
            String ssrole = null;
            if (!userList.iterator().hasNext()) {
                // throw new CustomException("无法查询到当前role");
            }
            ssrole = ((User) userList.iterator().next()).getSsrole();
            if (null == ssrole || "".equals(ssrole.trim())) {
                throw new CustomException("无法用户所属角色");
            } else {
                roleArray = ssrole.split(",");
            }

            sb.append("[");
            //循环获取roleID
            for (int i = 0; i < roleArray.length; i++) {
                if (null == roleArray[i] || "".equals(roleArray[i].trim())) {
                    continue;
                } else {
                    //获取角色信息
                    List list = QueryData.getSomeEntity("Role", roleArray[i], "id");
                    if (null == list || null == list.iterator() || !list.iterator().hasNext()) {
                        continue;
                    } else {
                        Role role = (Role) list.iterator().next();
                        if (null == role) {
                            continue;
                        } else {
                            sb.append("{\"id\":\"" + role.getId() + "\",\"open\":true,\"name\":\"" + role.getMc() + "\",\"pId\":\"0\"},");
                        }
                    }
                }
            }
            result = sb.toString().substring(0, sb.length() - 1) + "]";
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        } finally {
            if (null != session) {
                session.close();
            }
            response.getWriter().print(result);
        }
    }
    //获取user不属角色
    public  void getOtherRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String result = "";
        String userDM = "";
        String[] roleArray = null;
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            userDM = request.getParameter("dm");
            if (null == userDM || "".equals(userDM.trim())) {
                throw new CustomException("传递的userDM代码有错！");
            }
            //获取角色的权限
            List userList = QueryData.getSomeEntity("User", userDM, "userID");
            if (userList == null) {
                // throw new CustomException("无法查询到当前role");
            }
            String ssrole = null;
            if (!userList.iterator().hasNext()) {
                // throw new CustomException("无法查询到当前role");
            }
            ssrole = ((User) userList.iterator().next()).getSsrole();
            if (null == ssrole || "".equals(ssrole.trim())) {

            } else {
                roleArray = ssrole.split(",");
            }
            //获取所有的角色信息
            List allRole = QueryData.getAllEntity("Role");
            if (null == allRole || null == allRole.iterator() || !allRole.iterator().hasNext()) {

            } else {
                sb.append("[");
                Iterator it = allRole.iterator();
                while (it.hasNext()) {
                    Boolean isContains = false;
                    Role role = (Role) it.next();
                    if (null == role) {
                        continue;
                    } else if (roleArray != null && roleArray.length > 0) {
                        //循环获取roleID
                        for (int i = 0; i < roleArray.length; i++) {
                            if (null == roleArray[i] || "".equals(roleArray[i].trim())) {
                                continue;
                            } else {
                                if (roleArray[i].equals(role.getId().trim())) {
                                    isContains = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!isContains) {
                        sb.append("{\"id\":\"" + role.getId() + "\",\"open\":true,\"name\":\"" + role.getMc() + "\",\"pId\":\"0\"},");
                    }
                }
                result = sb.toString().substring(0, sb.length() - 1) + "]";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        } finally {
            if (null != session) {
                session.close();
            }
            response.getWriter().print(result);
        }
    }
    //更新user角色权限
    public  void updateUserRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            //当前session中userid
            String userid = request.getSession().getAttribute("userid").toString();
            //前台传递过来的userID
            String userID = request.getParameter("userID").trim();
            //如果检测是否是当前登录的用户，如果是相同则禁止修改
            if(userID.equals(userid)){
                throw  new CustomException("禁止修改自己的权限，请登录其他操作员修改！");
            }
            String ssrole = request.getParameter("ssrole");
            System.out.println(userID+":"+ssrole);
            String hql = "update User t set t.ssrole = :ssrole where t.userID = :userID";
            Query query = session.createQuery(hql);
            query.setString("userID",userID);
            query.setString("ssrole",ssrole);
            query.executeUpdate();
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "修改成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message",e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if(null != session){
                session.close();
            }
            response.getWriter().print(json);
        }
    }
}
