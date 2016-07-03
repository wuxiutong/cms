package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.exceptions.MyException;
import wxt.model.Dqxx;
import wxt.model.Zyxx;
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
 * Created by wuxiutong on 2015/8/31.
 */
public class Action_Region {
    //增加地区信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_dq = new JSONObject();
        JSONObject json  = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()){
                String  name = enum1.nextElement();
                if(name.equalsIgnoreCase("dqfzr.zydm")) {
                    js_dq.put("fzr", request.getParameter(name));
                } else if(name.equalsIgnoreCase("dqfzr.zyxm")){
                    continue;
                } else {
                    js_dq.put(name, request.getParameter(name));
                }
            }
            Dqxx dqxx = (Dqxx) JSONObject.toBean(js_dq, Dqxx.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(ValidateUtils.checkDMExist("Dqxx", dqxx.getDqdm())){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","代码已经存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
                //如果该代码在数据库中存在上级！则可以增加
            }else if (null != ValidateUtils.hasParent("Dqxx",dqxx.getDqdm(),"dqfj")){
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.save(dqxx);
                transaction.commit();
                json.put("dqdm",dqxx.getDqdm());
                json.put("dqmc",dqxx.getDqmc());
                json.put("parentDm", ValidateUtils.hasParent("Dqxx",dqxx.getDqdm(),"dqfj"));
                //  json.fromObject(request);
                json.put("statusCode","200");
                json.put("message","增加成功！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl", "");
            }else  {
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","上级不存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
            }
        }catch (MyException e122){
            e122.printStackTrace();
            json.put("statusCode","300");
            json.put("message",e122.toString());
            json.put("navTabId","");
            json.put("rel","");
            json.put("callbackType","");
            json.put("forwardUrl", "");

        }finally {
            if(null!=session ){
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
    //删除地区信息
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        int result=0;
        JSONObject json = new JSONObject();
        try {
            String dqdm = request.getParameter("dqdm").toString();
            if(CheckUtils.checkRegionHasBeenUsed(dqdm)){
                throw new Exception("当前地区信息已经被使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete Dqxx d  where d.dqdm like :dqdm";
            Query del = session.createQuery(hqlDel);
            del.setString("dqdm", dqdm+"%");
            result = del.executeUpdate();
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
            json.put("message",e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        }finally {
            if (null!=session  ){
                session.close();
            }
            response.getWriter().print(json);
        }
    }
    //修改地区信息
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_dq = new JSONObject();
        JSONObject json  = new JSONObject();
        Session session = null;
        try {
            while (enum1.hasMoreElements()){
                String  name = enum1.nextElement();
                if(name.equalsIgnoreCase("dqfzr.zydm")) {
                    js_dq.put("fzr", request.getParameter(name));
                } else if(name.equalsIgnoreCase("dqfzr.zyxm")){
                    continue;
                } else {
                    js_dq.put(name, request.getParameter(name));
                }
            }
            Dqxx dqxx = (Dqxx) JSONObject.toBean(js_dq, Dqxx.class);
            String dqdm = dqxx.getDqdm();
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(1==1){
                session = HibernateUtils.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(dqxx);
                transaction.commit();
                json.put("dqdm",dqxx.getDqdm());
                json.put("dqmc",dqxx.getDqmc());
                json.put("statusCode","200");
                json.put("message","修改成功！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl", "");
                json.put("altered","ok");
            }else  {
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","该记录在数据库中不存在，无法更新！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
            }
        }catch (Exception e122){
            e122.printStackTrace();
            //  json.fromObject(request);
            json.put("statusCode","300");
            json.put("message",e122.getMessage());
            json.put("navTabId","");
            json.put("rel","");
            json.put("callbackType","");
            json.put("forwardUrl","");
            PrintWriter out = response.getWriter();
            out.print(json);
        }finally {
            if (null!=session ){
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
    //获取所有的地区信息
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json  = new JSONObject();
        try {  //获取所有地区信息
            StringBuffer sb = new StringBuffer();
            List allDq = QueryData.getAllEntity("Dqxx");
            if (null == allDq){
                return ;// throw new MyException("不存在数据");
            }
            Iterator it = allDq.iterator();
            sb.append("[");
            while (it.hasNext()) {
                Dqxx dqxx = (Dqxx) it.next();
                if(ValidateUtils.isLevOk(dqxx.getDqdm(),"dqfj")) { //符合编码规则的才执行
                    String parentDm = ValidateUtils.hasParent("Dqxx",dqxx.getDqdm(),"dqfj");
                    if("0".equalsIgnoreCase(parentDm))
                        sb.append("{\"id\":\"" + dqxx.getDqdm() + "\",\"name\":\"" +dqxx.getDqdm()+"-"+dqxx.getDqmc() + "\",\"pId\":\"0\"},");
                    else
                        sb.append("{\"id\":\"" + dqxx.getDqdm() + "\",\"name\":\"" +dqxx.getDqdm()+"-"+ dqxx.getDqmc() + "\",\"pId\":\""+parentDm+"\"},");
                }
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print("");
        }
    }
    //获取某个地区信息
    public void getOneDqxx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String dqdm  = request.getParameter("dqdm").toString();
        List list = QueryData.getEntity("Dqxx", dqdm);
        if(0>=list.size() ){
            System.out.println("数据库中不存在代码为："+dqdm+"的地区信息！");
        }else {
            Iterator it = list.iterator();
            Dqxx dqxx1 = (Dqxx)it.next();
            List fzrList = QueryData.getEntity("Zyxx",dqxx1.getFzr());
            Iterator fzrIt = fzrList.iterator();
            if(fzrIt.hasNext()){
                Zyxx fzrxx = (Zyxx)fzrIt.next();
                json.put("fzrXm",fzrxx.getZyxm());
            };
            json.put("dqdm",dqxx1.getDqdm());
            json.put("dqmc",dqxx1.getDqmc());
            json.put("fzr",dqxx1.getFzr());
            json.put("postcode",dqxx1.getPostcode());
            json.put("ps",dqxx1.getPs());
            PrintWriter pw  =  response.getWriter();
            pw.print(json.toString());
        }
    }
}
