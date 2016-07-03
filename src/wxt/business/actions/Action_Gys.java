package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.Gys;
import wxt.utils.CheckUtils;
import wxt.utils.HibernateUtils;
import wxt.utils.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuxiutong on 15/8/31.
 */
public class Action_Gys {
    //增加供应商
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_gys = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                js_gys.put(name, request.getParameter(name));
                System.out.println("name:" + name + ";value:" + request.getParameter(name));
            }
            Gys gys = (Gys) JSONObject.toBean(js_gys, Gys.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if (ValidateUtils.checkDMExist("Gys", gys.getGysdm())) {
                //  json.fromObject(request);
                json.put("statusCode", "300");
                json.put("message", "代码已经存在！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
                //如果该代码在数据库中存在上级！则可以增加
            } else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.save(gys);
                transaction.commit();
                json.put("gysdm", gys.getGysdm());
                json.put("gysmc", gys.getGysmc());
                //  json.fromObject(request);
                json.put("statusCode", "200");
                json.put("message", "增加成功！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            }
        } catch (Exception e122) {
            e122.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", e122.toString());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        } finally {
            if (null != session  ) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //修改供应商
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_gys = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                js_gys.put(name, request.getParameter(name));
            }
            Gys gys = (Gys) JSONObject.toBean(js_gys, Gys.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if (!ValidateUtils.checkDMExist("Gys", gys.getGysdm())) {
                //  json.fromObject(request);
                json.put("statusCode", "300");
                json.put("message", "代码不存在！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
                //如果该代码在数据库中存在上级！则可以增加
            } else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.update(gys);
                transaction.commit();
                json.put("gysdm", gys.getGysdm());
                json.put("gysmc", gys.getGysmc());
                //  json.fromObject(request);
                json.put("statusCode", "200");
                json.put("message", "修改成功！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            }
        } catch (Exception e122) {
            e122.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", e122.toString());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        } finally {
            if (null != session  ) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //删除供应商
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String gysdm = request.getParameter("gysdm").toString();
            if (CheckUtils.checkGysHasBeenUsed(gysdm)) {
                throw new Exception("当前供应商信息已经被使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete Gys d  where d.gysdm = :gysdm";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
            Query del = session.createQuery(hqlDel);
            del.setString("gysdm", gysdm);
            int result = del.executeUpdate();
            transaction.commit();
            json.put("result", result);
            json.put("statusCode", "200");
            json.put("message", "成功 " + result + "条记录！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("result", "");
            json.put("statusCode", "300");
            json.put("message", e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if (null != session ) {
                session.close();
            }
            pw.print(json);
        }
    }

    //获取所有的供应商
    public void getAllForTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        List allGys = null;
        Iterator it = null;
        StringBuffer sb = new StringBuffer();
        try {
            allGys = QueryData.getAllEntity("Gys");
            it = allGys.iterator();
            sb.append("[");
            while (it.hasNext()) {
                Gys gys = (Gys) it.next();
                sb.append("{\"id\":\"" + gys.getGysdm() + "\",\"name\":\"" + gys.getGysdm() + "-" + gys.getGysmc() + "\",\"pId\":\"0\"},");
            }
            System.out.println(sb.toString().substring(0, sb.length() - 1) + "]");
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取某个供应商的具体信息
    public void getOneGys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String gysdm = request.getParameter("gysDm").toString();
        List list = QueryData.getEntity("Gys", gysdm);
        System.out.println("传回：" + gysdm + "的地区信息！");
        if (0 >= list.size()) {
            System.out.println("数据库中不存在代码为：" + gysdm + "的地区信息！");
        } else {
            Iterator it = list.iterator();
            Gys gys = (Gys) it.next();
            json.put("gysdm", gys.getGysdm());
            json.put("gysmc", gys.getGysmc());
            json.put("address", gys.getAddress());
            json.put("ps", gys.getPs());
            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
        }
    }

    //获取某个所有的供应商组成代码+名称的json
    public void getAllForJson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        List allGys = null;
        Iterator it = null;
        StringBuffer sb = new StringBuffer();
        try {
            allGys = QueryData.getAllEntity("Gys");
            it = allGys.iterator();
            sb.append("[");
            while (it.hasNext()) {
                Gys gys = (Gys) it.next();
                sb.append("{\"gysDm\":\"" + gys.getGysdm() + "\",\"gysMc\":\"" + gys.getGysmc() + "\"},");
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
