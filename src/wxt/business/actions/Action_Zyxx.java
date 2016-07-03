package wxt.business.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.Bmxx;
import wxt.model.Dqxx;
import wxt.model.User;
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
import java.util.Vector;

/**
 * Created by wuxiutong on 15/8/31.
 */
public class Action_Zyxx {
    //增加职员信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        PrintWriter pw = response.getWriter();
        JSONObject js_dq = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        User user = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                if (name.equalsIgnoreCase("ssbm.bmdm")) {
                    js_dq.put("ssbm", request.getParameter(name));
                } else if (name.equalsIgnoreCase("ssbm.bmmc")) {
                    continue;
                } else {
                    js_dq.put(name, request.getParameter(name));
                }
            }
            Zyxx zyxx = (Zyxx) JSONObject.toBean(js_dq, Zyxx.class);
            user = new User();
            user.setUserID(zyxx.getZydm());
            user.setUserName(zyxx.getZyxm());
            user.setEmail(zyxx.getLxdh());
            user.setPassword("1");

            if (ValidateUtils.checkDMExist("Zyxx", zyxx.getZydm())) {
                json.clear();
                json.put("statusCode", "300");
                json.put("message", "该职员信息已经存在，请核对！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            } else {
                session = HibernateUtils.getSession();
                if(!ValidateUtils.checkDMExist("User",user.getUserID())){
                    session.save(user);
                }else{

                }
                transaction = session.beginTransaction();
                session.save(zyxx);
                transaction.commit();
                json.clear();
                json.put("zydm", zyxx.getZydm());
                json.put("zyxm", zyxx.getZyxm());
                json.put("parentDm", zyxx.getSsbm());
                json.put("statusCode", "200");
                json.put("message", "增加成功！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            }
        }catch (Exception e){
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        }finally {
            if (null!=session ){
                session.close();
            }
            pw.print(json.toString());
        }
    }

    //修改职员信息
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
                if (name.equalsIgnoreCase("ssbm.bmdm")) {
                    js_dq.put("ssbm", request.getParameter(name));
                } else if (name.equalsIgnoreCase("ssbm.bmmc")) {
                    continue;
                } else {
                    js_dq.put(name, request.getParameter(name));
                }
                //  System.out.println("name:"+name+"value:"+request.getParameter(name));
            }
            Zyxx zyxx = (Zyxx) JSONObject.toBean(js_dq,Zyxx.class);
            String zydm = zyxx.getZydm();
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(1==1){
                session = HibernateUtils.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(zyxx);
                transaction.commit();
                System.out.println("zydm:"+zyxx.getZydm()+" ;pid:"+zyxx.getSsbm());
                json.put("zydm", zyxx.getZydm());
                json.put("zyxm",zyxx.getZyxm());
                json.put("pid",zyxx.getSsbm());
                json.put("parentDm", zyxx.getSsbm());
                json.put("statusCode", "200");
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

    //删除职员信息
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String zydm = request.getParameter("zydm").toString();
            if(CheckUtils.checkZyxxHasBeenUsed(zydm)){
                throw new Exception("当前职员资料已经使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete Zyxx b  where b.zydm = :zydm";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
            Query del = session.createQuery(hqlDel);
            del.setString("zydm", zydm);
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
        }
        finally {
            if (null!=session ){
                session.close();
            }
            pw.print(json);
        }
    }

    //获取所有职员信息forZtree ,暂时弃用20150917
    public void getAllForZtree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json  = null;
        try {  //获取所有地区信息
            json = new JSONObject();
            StringBuffer sb = new StringBuffer();
            List allBm = QueryData.getAllEntity("Bmxx");
            Iterator itBm = allBm.iterator();
            sb.append("[");
            while (itBm.hasNext()) {
                Bmxx bmxx = (Bmxx) itBm.next();
                if(ValidateUtils.isLevOk(bmxx.getBmdm(), "bmfj")) { //符合编码规则的才执行
                    String parentDm = ValidateUtils.hasParent("Bmxx", bmxx.getBmdm(), "bmfj"); //获取上级代码
                    List zyxx = QueryData.getSomeEntity("Zyxx",bmxx.getBmdm(),"ssbm"); //获取该部门下的所有职员
                    Iterator itZy = zyxx.iterator();
                    if("zoot".equalsIgnoreCase(parentDm))
                        sb.append("{\"id\":\"" + bmxx.getBmdm() + "\",\"name\":\"" +bmxx.getBmdm()+"-"+bmxx.getBmmc() + "\",\"pId\":\"0\"},");
                    else
                        sb.append("{\"id\":\"" + bmxx.getBmdm()  + "\",\"name\":\"" +bmxx.getBmdm()+"-"+ bmxx.getBmmc() + "\",\"pId\":\""+parentDm+"\"},");
                    //在该部门节点下增加职员信息！
                    while(itZy.hasNext()){
                        Zyxx zyxx_p =(Zyxx)itZy.next();
                        sb.append("{\"id\":\"" + "zy_"+zyxx_p.getZydm() + "\",\"name\":\"" +zyxx_p.getZydm()+"-"+ zyxx_p.getZyxm() + "\",\"pId\":\""+bmxx.getBmdm()+"\"},");
                    }

                }
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //获取所有职员信息
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        String flag = request.getParameter("flag");
        List allZy = null;
        Vector vector = null;
        try {
            allZy = QueryData.getAllEntity("Zyxx");
            vector = new Vector();
            if (null != allZy) {
                Iterator itZy = allZy.iterator();
                if(null==flag) { //普通
                    while (itZy.hasNext()) {
                        JSONObject json_data = new JSONObject();
                        Zyxx zyxx = (Zyxx) itZy.next();
                        json_data.put("zydm", zyxx.getZydm());
//                        json_data.put("zydmxm", zyxx.getZydm()+"["+zyxx.getZyxm()+"]");
                        json_data.put("zyxm", zyxx.getZyxm());
                        json_data.put("sex", zyxx.getSex());
                        json_data.put("ssbm", zyxx.getSsbm() + "[" + ((Bmxx) QueryData.getSomeEntity("Bmxx", zyxx.getSsbm(), "bmdm").iterator().next()).getBmmc()+"]");
//                    json_data.put("checkCell", "<input type=\"checkbox\" name=\"ids\" data-toggle=\"icheck\" value=\"{pid:'1', zydm:'" + zyxx.getZydm() + "',zyxm:'" + zyxx.getZyxm() + "'}\">");
                        json_data.put("selectCell", "<a href=\"javascript:;\" data-toggle=\"lookupback\" data-args=\"{pid:'1', zydm:'" + zyxx.getZydm() + "',zyxm:'" + zyxx.getZyxm() + "',zydmxm:'"+zyxx.getZydm()+"["+zyxx.getZyxm()+"]'}\" class=\"btn btn-blue\" title=\"选择本项\" data-icon=\"check\">选择</a>");
                        vector.add(json_data);
                    }
                }
                //
                else if (1==2 && "customer_add".equalsIgnoreCase(flag)){
                    while (itZy.hasNext()) {
                        JSONObject json_data = new JSONObject();
                        Zyxx zyxx = (Zyxx) itZy.next();
                        json_data.put("zydm", zyxx.getZydm());
                        json_data.put("zyxm", zyxx.getZyxm());
                        json_data.put("sex", zyxx.getSex());
                        json_data.put("ssbm", zyxx.getSsbm() + " " + ((Bmxx) QueryData.getSomeEntity("Bmxx", zyxx.getSsbm(), "bmdm").iterator().next()).getBmmc());
//                    json_data.put("checkCell", "<input type=\"checkbox\" name=\"ids\" data-toggle=\"icheck\" value=\"{pid:'1', zydm:'" + zyxx.getZydm() + "',zyxm:'" + zyxx.getZyxm() + "'}\">");
                        json_data.put("selectCell", "<a href=\"javascript:;\" data-toggle=\"lookupback\" data-args=\"{pid:'1', zydm:'" + zyxx.getZydm() + "',zyxm:'" + zyxx.getZyxm() + "'}\" class=\"btn btn-blue\" title=\"选择本项\" data-icon=\"check\">选择</a>");
                        vector.add(json_data);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.getWriter().print(JSONArray.fromObject(vector));
        }

    }
}
