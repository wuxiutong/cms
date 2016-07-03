package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.Dwlx;
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
public class Action_Dwlx {
    //增加单位类型
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_dq = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        String pearentDm = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                js_dq.put(name, request.getParameter(name));
            }
            Dwlx dwlx = (Dwlx) JSONObject.toBean(js_dq, Dwlx.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if (ValidateUtils.isLevOk(dwlx.getLxdm(), "dwlxfj")) {//如果单位分级符合规则才执行
                pearentDm = ValidateUtils.hasParent("Dwlx", dwlx.getLxdm(), "dwlxfj");
                if (ValidateUtils.checkDMExist("Dwlx", dwlx.getLxdm())) {
                    //  json.fromObject(request);
                    json.put("statusCode", "300");
                    json.put("message", "代码已经存在！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");
                    //如果该代码在数据库中存在上级！则可以增加
                } else if (null != pearentDm) {
                    session = HibernateUtils.getSession();
                    transaction = session.beginTransaction();
                    session.save(dwlx);
                    transaction.commit();
                    json.put("lxdm", dwlx.getLxdm());
                    json.put("lxmc", dwlx.getLxmc());
                    json.put("pearentDm", pearentDm);
                    //  json.fromObject(request);
                    json.put("statusCode", "200");
                    json.put("message", "增加成功！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");

                } else {
                    //  json.fromObject(request);
                    json.put("statusCode", "300");
                    json.put("message", "上级不存在！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");

                }
            } else {
                json.put("statusCode", "300");
                json.put("message", "不符合分级方案！");
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
            if (session != null ) {
                session.close();
            }
            ;
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //修改单位类型
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_dq = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        String pearentDm = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                js_dq.put(name, request.getParameter(name));
            }
            Dwlx dwlx = (Dwlx) JSONObject.toBean(js_dq, Dwlx.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if (ValidateUtils.isLevOk(dwlx.getLxdm(), "dwlxfj")) {//如果单位分级符合规则才执行
                pearentDm = ValidateUtils.hasParent("Dwlx", dwlx.getLxdm(), "dwlxfj");
                if (!ValidateUtils.checkDMExist("Dwlx", dwlx.getLxdm())) {
                    //  json.fromObject(request);
                    json.put("statusCode", "300");
                    json.put("message", "代码不存在！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");
                    //如果该代码在数据库中存在上级！则可以增加
                } else if (null != pearentDm) {
                    session = HibernateUtils.getSession();
                    transaction = session.beginTransaction();
                    session.update(dwlx);
                    transaction.commit();
                    json.put("lxdm", dwlx.getLxdm());
                    json.put("lxmc", dwlx.getLxmc());
                    json.put("pearentDm", pearentDm);
                    json.put("altered", true);
                    //  json.fromObject(request);
                    json.put("statusCode", "200");
                    json.put("message", "增加成功！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");

                } else {
                    //  json.fromObject(request);
                    json.put("statusCode", "300");
                    json.put("message", "上级不存在！");
                    json.put("navTabId", "");
                    json.put("rel", "");
                    json.put("callbackType", "");
                    json.put("forwardUrl", "");

                }
            } else {
                //  json.fromObject(request);
                json.put("statusCode", "300");
                json.put("message", "不符合分级方案！");
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
            if (session != null  ) {
                session.close();
            }
            ;
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //删除单位类型
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String lxdm = request.getParameter("lxdm").toString();
            if (CheckUtils.checkDwlxHasBeenUsed(lxdm)) {
                throw new Exception("当前类型已经被使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete Dwlx d  where d.lxdm like :lxdm";
            Query del = session.createQuery(hqlDel);
            del.setString("lxdm", lxdm + "%");
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

    //获取所有单据类型
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        ;
        try {  //获取所有地区信息
            StringBuffer sb = new StringBuffer();
            List allDw = QueryData.getAllEntity("Dwlx");
            if (null == allDw) {
                return;// throw new MyException("不存在数据");
            }
            Iterator it = allDw.iterator();
            //给添加
            sb.append("[");
//            sb.append("{\"id\":\"100\",\"name\":\"销售单管理\",\"pId\":\"0\"},");
            while (it.hasNext()) {
                Dwlx dwlx = (Dwlx) it.next();
                if (ValidateUtils.isLevOk(dwlx.getLxdm(), "dwlxfj")) { //符合编码规则的才执行
                    String parentDm = ValidateUtils.hasParent("Dwlx", dwlx.getLxdm(), "dwlxfj");
                    if ("zoot".equalsIgnoreCase(parentDm))
                        sb.append("{\"id\":\"" + dwlx.getLxdm() + "\",\"name\":\"" + dwlx.getLxdm() + "-" + dwlx.getLxmc() + "\",\"pId\":\"0\"},");
                    else if (null != parentDm) {
                        sb.append("{\"id\":\"" + dwlx.getLxdm() + "\",\"name\":\"" + dwlx.getLxdm() + "-" + dwlx.getLxmc() + "\",\"pId\":\"" + parentDm + "\"},");
                    }
                }
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("");
        }
    }

    //获取某个单位信息
    public void getOneDwlx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String lxdm = request.getParameter("lxdm").toString();
        List list = QueryData.getEntity("Dwlx", lxdm);
        if (0 >= list.size()) {
            System.out.println("数据库中不存在代码为：" + lxdm + "的地区信息！");
        } else {
            Iterator it = list.iterator();
            Dwlx dwlx = (Dwlx) it.next();
            json.put("lxdm", dwlx.getLxdm());
            json.put("lxmc", dwlx.getLxmc());
            json.put("ps", dwlx.getPs());
            PrintWriter pw = response.getWriter();
            pw.print(json.toString());
        }


    }


}
