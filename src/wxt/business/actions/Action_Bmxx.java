package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.exceptions.MyException;
import wxt.model.Bmxx;
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
 * Created by wuxiutong on 15/8/31.
 */
public class Action_Bmxx {
    //增加部门信息
    public void add(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
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
                if(name.equalsIgnoreCase("bmfzr.zydm")) {
                    js_dq.put("fzr", request.getParameter(name));
                } else if(name.equalsIgnoreCase("bmfzr.zyxm")){
                    continue;
                } else {
                    js_dq.put(name, request.getParameter(name));
                }
                // System.out.println("paranames:"+name +";value:"+request.getParameter(name));
            }
            Bmxx bmxx = (Bmxx) JSONObject.toBean(js_dq, Bmxx.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(!ValidateUtils.isLevOk(bmxx.getBmdm(), "bmfj")){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","不符合分级方案！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
            }else if((null==ValidateUtils.hasParent("Bmxx",bmxx.getBmdm(),"bmfj"))){
                //  json.fromObject(request);
                json.put("statusCode","300");
                json.put("message","上级代码不存在！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl","");
            }else if(ValidateUtils.checkDMExist("Bmxx",bmxx.getBmdm())){
                {
                    //  json.fromObject(request);
                    json.put("statusCode","300");
                    json.put("message","代码重复！");
                    json.put("navTabId","");
                    json.put("rel","");
                    json.put("callbackType","");
                    json.put("forwardUrl","");
                }
            }else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.save(bmxx);
                transaction.commit();
                json.put("dm", bmxx.getBmdm());
                json.put("mc",bmxx.getBmmc());
                json.put("parentDm", ValidateUtils.hasParent("Bmxx",bmxx.getBmdm(),"bmfj"));
                //  json.fromObject(request);
                json.put("statusCode","200");
                json.put("message","增加成功！");
                json.put("navTabId","");
                json.put("rel","");
                json.put("callbackType","");
                json.put("forwardUrl", "");
            }
        }catch (MyException e122){
            e122.printStackTrace();
            //  json.fromObject(request);
            json.put("statusCode","300");
            json.put("message",e122.getMessage());
            json.put("navTabId","");
            json.put("rel","");
            json.put("callbackType","");
            json.put("forwardUrl", "");

        }finally {
            if(null!=session  ){
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
    //修改部门信息
    public void alter(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_dq = new JSONObject();
        JSONObject json  = new JSONObject();
        Session session = null;
        try {
            while (enum1.hasMoreElements()){
                String  name = enum1.nextElement();
                if(name.equalsIgnoreCase("bmfzr.zydm")) {
                    js_dq.put("fzr", request.getParameter(name));
                } else if(name.equalsIgnoreCase("bmfzr.zyxm")){
                    continue;
                } else {
                    js_dq.put(name, request.getParameter(name));
                }
            }
            Bmxx bmxx = (Bmxx) JSONObject.toBean(js_dq, Bmxx.class);
            String bmdm = bmxx.getBmdm();
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if(1==1){
                session = HibernateUtils.getSession();
                Transaction transaction = session.beginTransaction();
                session.update(bmxx);
                transaction.commit();
                json.put("bmdm",bmxx.getBmdm());
                json.put("bmmc",bmxx.getBmmc());
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

    //删除部门信息
    public void del(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        Transaction transaction = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String bmdm = request.getParameter("bmdm").toString();

            if(CheckUtils.checkBmxxHasBeenUsed(bmdm)){
                throw new Exception("当前部门资料已经使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            String hqlDel = "delete Bmxx b  where b.bmdm = :bmdm";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
            Query del = session.createQuery(hqlDel);
            del.setString("bmdm", bmdm);
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
            if(null!=session  ){
                session.clear();
                session.close();
            }
            pw.print(json);
        }
    }

    //获取所有部门信息
    public void getAll(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = null;
        try {  //获取所有地区信息
            json = new JSONObject();
            StringBuffer sb = new StringBuffer();
            List allBm = QueryData.getAllEntity("Bmxx");
            Iterator itBm = allBm.iterator();
            sb.append("[");
            while (itBm.hasNext()) {
                Bmxx bmxx = (Bmxx) itBm.next();
                if (ValidateUtils.isLevOk(bmxx.getBmdm(), "bmfj")) { //符合编码规则的才执行
                    String parentDm = ValidateUtils.hasParent("Bmxx", bmxx.getBmdm(), "bmfj"); //获取上级代码
                    if ("zoot".equalsIgnoreCase(parentDm))
                        sb.append("{\"id\":\"" + bmxx.getBmdm() + "\",\"name\":\"" + bmxx.getBmdm() + "-" + bmxx.getBmmc() + "\",\"pId\":\"0\"},");
                    else
                        sb.append("{\"id\":\"" + bmxx.getBmdm() + "\",\"name\":\"" + bmxx.getBmdm() + "-" + bmxx.getBmmc() + "\",\"pId\":\"" + parentDm + "\"},");
                }
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取一个部门详细信息
    public void getOneBmxx(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        String dm = request.getParameter("bmdm").toString();
        JSONObject json = new JSONObject();
        Iterator it = null;
        List list = null;
        try {
            list = QueryData.getEntity("Bmxx", dm);
            it = list.iterator();
            Bmxx xx = (Bmxx) it.next();
            List fzrList = QueryData.getEntity("Zyxx", xx.getFzr());
            Iterator itfzr = fzrList.iterator();
            if (itfzr.hasNext()) {
                Zyxx fzrxx = (Zyxx) itfzr.next();
                json.put("fzrxm", fzrxx.getZyxm());
            } ;
            json.put("bmdm", xx.getBmdm());
            json.put("bmmc", xx.getBmmc());
            json.put("fzr", xx.getFzr());
            json.put("ps", xx.getPs());
            if (0 >= list.size()) {
                System.out.println("数据库中不存在代码为：的地区信息！");
            } else {
                PrintWriter pw = response.getWriter();
                pw.print(json.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取部门职员信息
    public void getBmZy(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
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

    //检索部门职员
    public void getOneBmOrZy(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
//        System.out.println("传递过来的ID");
        String dm  = request.getParameter("dm").toString();
        String mark =request.getParameter("mark").toString();
        JSONObject json = new JSONObject();
        Iterator it = null;
//        System.out.println("传递过来的ID为："+dqdm);
        //如果获取到的是职员信息
        List list = null;
        if(dm.indexOf(mark)>=0){
            System.out.println("查询职员信息");
            list = QueryData.getEntity("Zyxx", dm.substring(mark.length()));
            it = list.iterator();
            Zyxx xx = (Zyxx)it.next();
            //所属部门
            List ssbmList = QueryData.getEntity("Bmxx",xx.getSsbm());
            Iterator ssbmIt = ssbmList.iterator();
            json.put("zydm", xx.getZydm());
            json.put("zyxm",xx.getZyxm());
            json.put("zy",xx.getZy());
            json.put("ps",xx.getPs());
            json.put("birthday",xx.getBirthday());
            json.put("byxx",xx.getByxx());
            json.put("jtzz",xx.getJtzz());
            json.put("lxdh",xx.getLxdh());
            json.put("mz",xx.getMz());
            json.put("rzrq",xx.getRzrq());
            json.put("sex",xx.getSex());
            json.put("sfzh",xx.getSfzh());
            if(ssbmIt.hasNext()){
                Bmxx ssbmxx = (Bmxx)ssbmIt.next();
                json.put("ssbmMc",ssbmxx.getBmmc());
            };
            json.put("ssbm",xx.getSsbm());
            json.put("xjdz",xx.getXjdz());
            json.put("xl", xx.getXl());
        }else{
            System.out.println("查询部门信息");
            list = QueryData.getEntity("Bmxx",dm);
            it = list.iterator();
            Bmxx xx = (Bmxx)it.next();
            List fzrList = QueryData.getEntity("Zyxx",xx.getFzr());
            Iterator itfzr = fzrList.iterator();
            if(itfzr.hasNext()){
                Zyxx fzrxx = (Zyxx)itfzr.next();
                json.put("fzrxm",fzrxx.getZyxm());
            };
            json.put("bmdm", xx.getBmdm());
            json.put("bmmc",xx.getBmmc());
            json.put("fzr",xx.getFzr());
            json.put("ps", xx.getPs());
            System.out.println("查询职员信息：部门名称：" + xx.getBmmc());
        }
        if(0>=list.size() ){
            System.out.println("数据库中不存在代码为：的地区信息！");
        }else {
            PrintWriter pw  =  response.getWriter();
            pw.print(json.toString());
        }


    }
}
