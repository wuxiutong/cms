package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.Gys;
import wxt.model.SoftVer;
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
public class Action_SoftVer {
    //增加版本信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_ver = new JSONObject();
        JSONObject json  = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()){
                String  name = enum1.nextElement();
                if(name.startsWith("gys.")) {
                    js_ver.put(name.substring(4), request.getParameter(name));
                }else{
                    js_ver.put(name, request.getParameter(name));
                }
                // System.out.println("name:"+name+";value:"+request.getParameter(name));
            }
            SoftVer ver = (SoftVer) JSONObject.toBean(js_ver, SoftVer.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(ValidateUtils.checkDMExist("SoftVer", ver.getVerDm())){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","代码已经存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
                //如果该代码在数据库中存在上级！则可以增加
            }else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.save(ver);
                transaction.commit();
                json.put("gysdm", ver.getGysDm());
                json.put("verdm", ver.getVerDm());
                json.put("vermc",ver.getVerMc());
                //  json.fromObject(request);
                json.put("statusCode","200");
                json.put("message","增加成功！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl", "");
            }
        }catch (Exception e122){
            e122.printStackTrace();
            json.put("statusCode","300");
            json.put("message",e122.toString());
            json.put("navTabId","");
            json.put("rel","");
            json.put("callbackType","");
            json.put("forwardUrl","");
            PrintWriter out = response.getWriter();
            out.print(json);
        }finally {
            if(null!=session ){
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
    //修改版本信息
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_ver = new JSONObject();
        JSONObject json  = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()){
                String  name = enum1.nextElement();
                if(name.startsWith("gys.")) {
                    js_ver.put(name.substring(4), request.getParameter(name));
                }else{
                    js_ver.put(name, request.getParameter(name));
                }
                // System.out.println("name:"+name+";value:"+request.getParameter(name));
            }
            SoftVer ver = (SoftVer) JSONObject.toBean(js_ver, SoftVer.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(!ValidateUtils.checkDMExist("SoftVer",ver.getVerDm())){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","代码不存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
                //如果该代码在数据库中存在上级！则可以增加
            }else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.update(ver);
                transaction.commit();
                json.put("gysdm", ver.getGysDm());
                json.put("verdm", ver.getVerDm());
                json.put("vermc",ver.getVerMc());
                //  json.fromObject(request);
                json.put("statusCode","200");
                json.put("message","修改成功！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl", "");
            }
        }catch (Exception e122){
            e122.printStackTrace();
            json.put("statusCode","300");
            json.put("message",e122.toString());
            json.put("navTabId","");
            json.put("rel","");
            json.put("callbackType","");
            json.put("forwardUrl","");
            PrintWriter out = response.getWriter();
            out.print(json);
        }finally {
            if(null!=session  ){
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
    //删除版本信息
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String verDM = request.getParameter("verDm").toString();
            if(CheckUtils.checkVerHasBeenUsed(verDM)){
                throw new Exception("当前软件版本信息已经被使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete SoftVer d  where d.verDm = :verDm";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
            Query del = session.createQuery(hqlDel);
            del.setString("verDm", verDM);
            int result = del.executeUpdate();
            transaction.commit();
            json.put("result", result);
            json.put("statusCode", "200");
            json.put("message", "成功 " + result + "条记录！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        }catch (Exception e){
            e.printStackTrace();
            json.put("result", "");
            json.put("statusCode", "300");
            json.put("message", e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        }finally {
            if (null!=session  ){
                session.close();
            }
            pw.print(json);
        }
    }
    //获取所有版本信息
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        List allVer = null;
        List allGys = null;
        Iterator itVer = null;
        Iterator itGys = null;
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("[");
            allVer = QueryData.getAllEntity("SoftVer");
            allGys = QueryData.getAllEntity("Gys");
            if(null!=allGys) {
                itGys = allGys.iterator();
                while(itGys.hasNext()){
                    Gys gys = (Gys)itGys.next();
                    sb.append("{\"id\":\"" + "gys_"+gys.getGysdm() + "\",\"name\":\"" + gys.getGysdm()+"-"+gys.getGysmc() + "\",\"pId\":\"0\"},");
                }
            }
            if(null != allVer) {
                itVer = allVer.iterator();
                while(itVer.hasNext()){
                    SoftVer ver = (SoftVer)itVer.next();
                    sb.append("{\"id\":\"" +"ver_"+ ver.getVerDm() + "\",\"name\":\"" + ver.getVerDm()+"-"+ver.getVerMc() + "\",\"pId\":\""+"gys_"+ver.getGysDm()+"\"},");
                }
            }
            response.getWriter().print(sb.toString().substring(0,sb.length()-1)+"]");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("{\"id\":\"" + "\",\"name\":\"" + "\",\"pId\":\"" + "\"},");
        }finally {
            // response.getWriter().print(sb.toString().substring(0,sb.length()-1)+"]");
        }
    }
    //获取某个版本信息明细
    public void getOneSoftVer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String verDm = null;
        List list = null;
        PrintWriter pw = response.getWriter();
        try {
            verDm = request.getParameter("verDm").toString();
            list = QueryData.getEntity("SoftVer", verDm);
            if (0 >= list.size()) {
                System.out.println("数据库中不存在代码为：" + verDm + "的地区信息！");
            } else {
                Iterator it = list.iterator();
                SoftVer ver = (SoftVer) it.next();
                json.put("verdm", ver.getVerDm());
                json.put("vermc", ver.getVerMc());
                json.put("gysdm", ver.getGysDm());
                List gys = QueryData.getEntity("Gys", ver.getGysDm());
                json.put("gysmc", ((Gys) (gys.iterator().next())).getGysmc());
                json.put("ps", ver.getPs());
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.print("error");
        } finally {
            pw.print(json.toString());
        }

    }
    //获取某个版本和供应
    protected void getOneGysVerByVer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        List allVer = null;
        Iterator it = null;
        StringBuffer sb = new StringBuffer();
        try {
            allVer = QueryData.getAllEntity("SoftVer");
            it = allVer.iterator();
            sb.append("[");
            while(it.hasNext()){
                SoftVer ver = (SoftVer)it.next();
                String gysmc = ((Gys)(QueryData.getSomeEntity("Gys",ver.getGysDm(),"gysdm").iterator().next())).getGysmc();
                sb.append("{\"verDm\":\"" + ver.getVerDm() + "\",\"verMc\":\"" +  ver.getVerMc()  + "\",\"gysDm\":\"" +  ver.getGysDm()  + "\",\"gysMc\":\"" + gysmc + "\"},");
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
