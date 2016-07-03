package wxt.business.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.Enterprise;
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
import java.util.Vector;

/**
 * Created by wuxiutong on 15/8/31.
 */
public class Action_Bloc {
    //增加销售公司
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
                js_dq.put(name, request.getParameter(name));
            }
            Enterprise en = (Enterprise) JSONObject.toBean(js_dq, Enterprise.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(ValidateUtils.checkDMExist("Enterprise", en.getGsdm())){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","代码已经存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
                //如果该代码在数据库中存在上级！则可以增加
            }else if (1==1){
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.save(en);
                transaction.commit();
                json.put("gsdm",en.getGsdm());
                json.put("gsmc",en.getGsmc());
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
            json.put("forwardUrl", "");

        }finally {
            if(null!=session ){
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
    //删除销售公司
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String gsdm = request.getParameter("gsdm").toString();
            if(CheckUtils.checkEnterpriseHasBeenUsed(gsdm)){
                throw new Exception("当前公司资料已经使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            List list = QueryData.getSomeEntity("", "", "");
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete Enterprise d  where d.gsdm = :gsdm";
            Query del = session.createQuery(hqlDel);
            del.setString("gsdm", gsdm);
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
    //修改销售公司
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                js_dq.put(name, request.getParameter(name));
            }
            Enterprise en = (Enterprise) JSONObject.toBean(js_dq, Enterprise.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(!ValidateUtils.checkDMExist("Enterprise",en.getGsdm())){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","代码不存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
                //如果该代码在数据库中存在上级！则可以增加
            }else if (1==1){
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.update(en);
                transaction.commit();
                json.put("gsdm",en.getGsdm());
                json.put("gsmc",en.getGsmc());
                json.put("altered",true);
                json.put("statusCode","200");
                json.put("message","修改成功！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl", "");
            }
        }catch (Exception e122){
            e122.printStackTrace();
            json.put("statusCode", "300");
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
    //获取所有的销售公司信息
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json  = null;
        try {  //获取所有地区信息
            json = new JSONObject();
            StringBuffer sb = new StringBuffer();
            List allEn = QueryData.getAllEntity("Enterprise");
            if (null == allEn){
                return ;// throw new MyException("不存在数据");
            }
            Iterator it = allEn.iterator();
            //给添加
            sb.append("[");
//            sb.append("{\"id\":\"100\",\"name\":\"销售单管理\",\"pId\":\"0\"},");
            while (it.hasNext()) {
                Enterprise en = (Enterprise) it.next();
                if(1==1) {
                    sb.append("{\"id\":\"" +en.getGsdm() + "\",\"name\":\"" +en.getGsdm()+"-"+en.getGsmc() + "\",\"pId\":\"0\"},");
                }
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print("");
        }
    }
    //获取所有的销售公司给某个dialog表格
    public void getAllForDialog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json  = null;
        Vector vector = null;
        try {  //获取所有地区信息
            vector = new Vector();
            json = new JSONObject();
            StringBuffer sb = new StringBuffer();
            List allEn = QueryData.getAllEntity("Enterprise");
            if (null == allEn){
                return ;// throw new MyException("不存在数据");
            }
            Iterator it = allEn.iterator();
            while (it.hasNext()) {
                JSONObject json_data = new JSONObject();
                Enterprise en = (Enterprise) it.next();
                json_data.put("gsdm", en.getGsdm());
//                        json_data.put("zydmxm", zyxx.getZydm()+"["+zyxx.getZyxm()+"]");
                json_data.put("gsmc", en.getGsmc());
                json_data.put("workphone", en.getWorkphone());
                json_data.put("type_en", en.getType_en());
                json_data.put("ps", en.getPs());
                json_data.put("selectCell", "<a href=\"javascript:;\" data-toggle=\"lookupback\" data-args=\"{pid:'1', gsdm:'" + en.getGsdm() + "',gsmc:'" + en.getGsmc() + "',gsdmmc:'"+en.getGsdm()+"["+en.getGsmc()+"]'}\" class=\"btn btn-blue\" title=\"选择本项\" data-icon=\"check\">选择</a>");
                vector.add(json_data);
            }
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print("");
        }finally {
            response.getWriter().print(JSONArray.fromObject(vector));
        }
    }
    //获取某个销售公司的具体信息
    public void getOneBloc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String gsdm  = request.getParameter("gsdm").toString();
        List list = QueryData.getEntity("Enterprise", gsdm);
        if(0>=list.size() ){
            System.out.println("数据库中不存在代码为："+gsdm+"的地区信息！");
        }else {
            Iterator it = list.iterator();
            Enterprise en = (Enterprise)it.next();
            json.put("gsdm",en.getGsdm());
            json.put("gsmc",en.getGsmc());
            json.put("workphone",en.getWorkphone());
            json.put("type_en",en.getType_en());
            json.put("ps",en.getPs());
            PrintWriter pw  =  response.getWriter();
            pw.print(json.toString());
        }


    }

}
