package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.exceptions.CustomException;
import wxt.model.Authorization_items;
import wxt.model.Role;
import wxt.utils.GetMD5;
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
public class Action_Role {
    //增加
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            Role role = new Role();
            String id = request.getParameter("id");
            if (null != id && !"".equals(id.trim())) {
                role.setId(id);
            } else {
                role.setId(GetMD5.getMD5(request.getParameter("mc")));
            }
            role.setMc(request.getParameter("mc"));
            role.setQx(request.getParameter("qx"));
            role.setPs(request.getParameter("ps"));
            session.saveOrUpdate(role);
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "增加成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", e.getMessage());
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

    //修改
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            String id = request.getParameter("id");
            String mc = request.getParameter("mc");
            String ps = request.getParameter("ps");
            String hql = "update Role t set t.mc = :mc,t.ps = :ps where t.id = :id";
            Query query = session.createQuery(hql);
            query.setString("id", id);
            query.setString("mc", mc);
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
            json.put("message", e.getMessage());
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

    //删除
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String userID = request.getSession().getAttribute("userid").toString();
            String id = request.getParameter("dm");
            String checkSql = "select userID from User where ssrole like '%"+id+",%' and userID = "+userID;
            Query queryUser  = session.createQuery(checkSql);
            List list  = queryUser.list();
            //如果检测当前修改的角色下有当前登录的用户则禁止修改！
            if(null!=list && !list.isEmpty()){
                throw  new CustomException("禁止删除您所属角色！");
            }
            String hql = "delete Role t   where t.id = :id";
            Query query = session.createQuery(hql);
            query.setString("id", id);
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
            json.put("message", e.getMessage());
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

    //获取所有角色
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
            List list = QueryData.getAllEntity("Role");
            if (null == list) {
                throw new CustomException("获取角色错误！");
            }
            Iterator it = list.iterator();
            sb.append("[");
            while (it.hasNext()) {
                Role role = (Role) it.next();
                sb.append("{\"id\":\"" + role.getId() + "\",\"name\":\"" + role.getMc() + "\",\"pId\":\"0\"},");
            }
            result = sb.toString().substring(0, sb.length() - 1) + "]";
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        } finally {
            System.out.println("++++返回角色：" + result);
            if (null != session) {
                session.close();
            }
            response.getWriter().print(result);
        }
    }

    //获取某个角色
    public void getOneRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String result = "";
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            String id = request.getParameter("dm");
            if (null == id || "".equals(id.trim())) {
                throw new CustomException("传递角色ID为空，请检查！");
            }
            List list = QueryData.getSomeEntity("Role", id, "id");
            if (null == list || list.iterator() == null || !list.iterator().hasNext()) {
                throw new CustomException("无法从数据库中查找到当前角色！");
            }
            Role role = (Role) list.iterator().next();
            json.put("statusCode", "200");
            json.put("message", "查找成功！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
            json.put("id", role.getId());
            json.put("mc", role.getMc());
            json.put("ps", role.getPs());

        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "查找角色信息错误！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            json.put("closeCurrent", true);
        } finally {
            if (null != session) {
                session.close();
            }
            response.getWriter().print(json);
        }
    }

    //获取角色所有权限
    public void getOneRoleAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String result = "";
        String roleDM = "";
        String[] qxArray = null;
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            List list = QueryData.getAllEntity("Authorization_items");
            roleDM = request.getParameter("dm");
            if (null == roleDM || "".equals(roleDM.trim())) {
                throw new CustomException("传递的role代码有错！");
            }
            //获取角色的权限
            List qxList = QueryData.getSomeEntity("Role", roleDM, "id");
            if (qxList == null) {
                // throw new CustomException("无法查询到当前role");
            }
            String qx = null;
            if (!qxList.iterator().hasNext()) {
                // throw new CustomException("无法查询到当前role");
            }
            qx = ((Role) qxList.iterator().next()).getQx();
            if (null == qx || "".equals(qx.trim())) {
                // throw new CustomException("无法查询到当前role");
            } else {
                qxArray = qx.split(",");
            }
            Iterator it = list.iterator();
            sb.append("[");
            while (it.hasNext()) {
                Boolean checked = false;
                Authorization_items author = (Authorization_items) it.next();
                String parentGndm = author.getParentGndm();
                if (null != qxArray) {
                    for (int i = 0; i < qxArray.length; i++) {
                        if (qxArray[i].trim().startsWith(author.getGndm())) {
                            checked = true;
                            break;
                        }
                    }
                }
                if (null == parentGndm || "".equals(parentGndm.trim()))
                    sb.append("{\"id\":\"" + author.getGndm() + "\",\"open\":true,\"checked\":" + checked + ",\"chkDisabled\":true,\"name\":\"" + author.getGnmc() + "\",\"pId\":\"0\"},");
                else {
                    sb.append("{\"id\":\"" + author.getGndm() + "\",\"checked\":" + checked + ",\"chkDisabled\":true,\"name\":\"" + author.getGnmc() + "\",\"pId\":\"" + parentGndm + "\"},");
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

    //修改角色权限
    public void updateRoleAuthor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {  //获取所有地区信息
            String userID = request.getSession().getAttribute("userid").toString();
            String id = request.getParameter("dm");
            String checkSql = "select userID from User where ssrole like '%"+id+",%' and userID = "+userID;
            Query queryUser  = session.createQuery(checkSql);
            List list  = queryUser.list();
            //如果检测当前修改的角色下有当前登录的用户则禁止修改！
            if(null!=list && !list.isEmpty()){
                throw  new CustomException("禁止修改您所属角色权限！");
            }
            String qx = request.getParameter("qx");
            String hql = "update Role t set t.qx = :qx where t.id = :id";
            Query query = session.createQuery(hql);
            query.setString("id", id);
            query.setString("qx", qx);
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
            json.put("message", e.getMessage());
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
    //

}
