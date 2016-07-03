package wxt.business.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.business.servlet.AddKhxxOldInfo;
import wxt.dao.QueryData;
import wxt.model.*;
import wxt.utils.CheckUtils;
import wxt.utils.DMMCUtils;
import wxt.utils.GetMD5;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by admin on 2015/3/12.
 */
public class Action_Khxx {
    //增加客户信息操作
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject json_khxx = new JSONObject();
        String khdm = request.getParameter("khdm");
        JSONObject json = new JSONObject();  //发返回信息
        //定义客户维护日期
        CMS_RPT_WHRQ rpt_whrq = new CMS_RPT_WHRQ();
        Map map = new HashMap<String, JSONObject>();
        Map map_model = new HashMap<String, JSONObject>();
        Session session = HibernateUtils.getSession();
        String newKhdm = "";
        Transaction transaction = session.beginTransaction();
        try {
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            //首先获取地区和客户类型
            String dqdmmc1 = request.getParameter("ssdq.dqdmmc");
            dqdmmc1 = DMMCUtils.getDM(dqdmmc1, "[");
            String lxdmmc1 = request.getParameter("khlx.lxdmmc");
            lxdmmc1 = DMMCUtils.getDM(lxdmmc1, "[");
            String tempStr = dqdmmc1.trim() + "-" + lxdmmc1.trim() + "-";
            int length_str = tempStr.length() + 1;
            List list = QueryData.getSomeEntityHQL("select max(cast(substring(khdm," + length_str + ") as int)) as khdm from Khxx where khdm like '" + tempStr + "%'");
            if (null != list && null != list.iterator() && list.iterator().hasNext()) { //返回的最大khdm存在的情况下在其基础上+1即可
                Object obj = list.iterator().next();
                if (obj != null) {
                    int maxKhdm = (int) list.iterator().next();
                    newKhdm = tempStr + "" + (maxKhdm + 1);
                } else {
                    newKhdm = tempStr + "1";
                }
            } else { //否则直接赋予最小客户代码！
                newKhdm = tempStr + "1";
            }

            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement().trim();
                //分别输入客户代码到客户信息和客户联系人
                if (name.equalsIgnoreCase("khjl.zydmxm")) {
                    String zydmxm = request.getParameter(name);
                    json_khxx.put("khjl", DMMCUtils.getDM(zydmxm.trim(), "["));
                } else if (name.equalsIgnoreCase("ssdq.dqdmmc")) {
                    String dqdmmc = request.getParameter(name);
                    json_khxx.put("ssdq", DMMCUtils.getDM(dqdmmc.trim(), "["));
                } else if (name.equalsIgnoreCase("xsgs.gsdmmc")) {
                    String gsdmmc = request.getParameter(name);
                    json_khxx.put("xsgs", DMMCUtils.getDM(gsdmmc.trim(), "["));
                } else if (name.equalsIgnoreCase("khlx.lxdmmc")) {
                    String lxdmmc = request.getParameter(name);
                    json_khxx.put("khlx", DMMCUtils.getDM(lxdmmc.trim(), "["));
                } else if (name.equalsIgnoreCase("whrq")) { //获取维护日期
                    String whrq = request.getParameter(name);
                    json_khxx.put("whrq", whrq);
                }else if (name.equalsIgnoreCase("lxr")) { //联系人
                    String lxr1 = request.getParameter(name);
                    if (null != lxr1 && !"".equalsIgnoreCase(lxr1.trim())) { //
                        JSONArray lxrArray = JSONArray.fromObject(lxr1.trim());
                        Khxx_lxr[] lxrs = (Khxx_lxr[]) JSONArray.toArray(lxrArray, Khxx_lxr.class);
                        String lxrbh = null;
//1、持久化客户联系人信息
                        for (Khxx_lxr lxr : lxrs) {
                            if (null != lxr.getLxrxm() && !"".equalsIgnoreCase(lxr.getLxrxm())) {//如果联系人的姓名为空则直接屏蔽跳过，判定未非法联系人
                                //联系人编号为客户代码加联系人姓名加联系人性别+手机号码
                                lxrbh = GetMD5.getMD5(newKhdm + "" + lxr.getLxrxm() + "" + lxr.getSex() + lxr.getCellphone());
                                lxr.setLxrbh(String.valueOf(lxrbh));
                                lxr.setKhdm(newKhdm);
                                session.save(lxr);
                            }
                        }
                    }
                } else if (name.equalsIgnoreCase("soft")) { //使用软件
                    String str = request.getParameter(name);
                    if (null != str && !"".equalsIgnoreCase(str.trim())) { //只有客户代码不为空的时候才允许其他操作！
                        JSONArray array = JSONArray.fromObject(str.trim());
                        Khxx_soft[] bean = (Khxx_soft[]) JSONArray.toArray(array, Khxx_soft.class);
//2、持久化客户使用软件信息
                        for (Khxx_soft soft : bean) {
                            rpt_whrq.setQsrq(soft.getGmrq());
                            if (null != soft.getModelDm() && !"".equalsIgnoreCase(soft.getModelDm())) {//如果联系人的姓名为空则直接屏蔽跳过，判定未非法联系人
                                soft.setKhdm(newKhdm);
                                soft.setKhmc(request.getParameter("khmc"));
                                session.save(soft);
                            }
                        }
                    }
                } else if (name.equalsIgnoreCase("datagrid.checkbox")) {
                    //如果传递过来的时一个datagrid.checkbox! 则跳过
                    continue;
                } else { //否则直接存入客户信息表
                    json_khxx.put(name, request.getParameter(name));
                }
            }
            //3、持久化客户信息
            Khxx khxx = (Khxx) JSONObject.toBean(json_khxx, Khxx.class);
            khxx.setKhdm(newKhdm); //设置客户代码
            json.put("khdm", newKhdm);
            session.save(khxx);

            //如果新增客户信息成功则更新客户维护日期信息
            rpt_whrq.setJzrq(khxx.getWhrq());
            rpt_whrq.setKhdm(khxx.getKhdm());
            rpt_whrq.setKhmc(khxx.getKhmc());
            rpt_whrq.setUpdateDjh("新增客户默认！");
            rpt_whrq.setUpdateUser(request.getSession().getAttribute("userid")+"["+request.getSession().getAttribute("username")+"]");
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = format.format(calendar.getTime());
            rpt_whrq.setLastUpdate(time);
            session.save(rpt_whrq);

//最后提交持久化信息
            transaction.commit();
            json.put("message", "操作成功！");
            json.put("statusCode", "200");
        } catch (Exception e) {
            json.put("statusCode", "300");
            if (!json.has("message")) {
                json.put("message", "提交错误,请于软件供应商联系！");
            }
            //如果报错，直接持久化回退！
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
            //  System.out.println("-----------------测试报错！！！！！！！！！------------------");
        }
    }

    //删除客户操作
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        Transaction transaction = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        int result = 0;
        int sumKhdm = 0;
        String deletedRows = "";
        try {
            String khdms = request.getParameter("delKhdms");
            String khdmA[] = khdms.split(",");
            session = HibernateUtils.getSession();
            {
                sumKhdm = khdmA.length; //赋值客户数量！
                //循环删除选中客户信息
                for (String khdm : khdmA) {
                    khdm=DMMCUtils.getDM(khdm,"[");
                    //如果是传递过来的一个undefined则跳过
                    if ("undefined".equalsIgnoreCase(khdm)) continue;
                    if (null == khdm || "".equalsIgnoreCase(khdm.trim())) {
                        continue;
                    }
                    if (null == transaction || !transaction.isActive()) {
                        transaction = session.beginTransaction();
                    }

                    //判断当前客户信息是否被使用，如果已经被使用则跳出循环！
                    if (CheckUtils.checkKhxxHasBeenUsed(khdm)) {
                        throw new Exception("客户 \"" + khdm + "\" 已经被使用，请检查！");
                    }
                    String hqlDel = "delete Khxx d  where d.khdm = :khdm";
                    String hqlDel_lxr = "delete Khxx_lxr l where l.khdm = :khdm";
                    String hqlDel_model = "delete Khxx_soft m where m.khdm = :khdm";
                    String hqlDel_rpt = "delete CMS_RPT_WHRQ m where m.khdm = :khdm";
                    Query del = session.createQuery(hqlDel);
                    Query del_lxr = session.createQuery(hqlDel_lxr);
                    Query del_model = session.createQuery(hqlDel_model);
                    Query del_rpt = session.createQuery(hqlDel_rpt);
                    del.setString("khdm", khdm);
                    del_lxr.setString("khdm", khdm);
                    del_model.setString("khdm", khdm);
                    del_rpt.setString("khdm", khdm);
                    del.executeUpdate();
                    int result_lxr = del_lxr.executeUpdate();
                    int result_model = del_model.executeUpdate();
                    int result_rpt = del_rpt.executeUpdate();
                    result++;
                }
                transaction.commit();
                json.put("statusCode", "200");
                json.put("message", "成功删除 " + result + "条记录！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
                result = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
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
            pw.print(json.toString());
        }
    }

    //更新客户信息
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject json_khxx = new JSONObject();
        JSONObject json = new JSONObject();  //发返回信息
        Map map = new HashMap<String, JSONObject>();
        Map map_model = new HashMap<String, JSONObject>();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        StringBuffer sb = new StringBuffer();
        JSONArray jsonArrayLxr = new JSONArray();
        JSONArray jsonArraySoft = new JSONArray();
        try {
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                sb.append("name:" + name + " ; value:" + request.getParameter(name) + "\n");
                //分别输入客户代码到客户信息和客户联系人
                if (name.equalsIgnoreCase("khdm")) {
                    json_khxx.put(name, request.getParameter(name).trim());
                } else if (name.equalsIgnoreCase("khmc")) {
                    json_khxx.put(name, request.getParameter(name).trim());
                } else if (name.equalsIgnoreCase("gzdz")) {
                    json_khxx.put(name, request.getParameter(name).trim());
                } else if (name.equalsIgnoreCase("bgdh")) {
                    json_khxx.put(name, request.getParameter(name).trim());
                } else if (name.equalsIgnoreCase("whrq")) { //获取维护日期
                    String whrq = request.getParameter(name);
                    json_khxx.put("whrq", whrq);
                }else if (name.equalsIgnoreCase("ps")) {
                    json_khxx.put(name, request.getParameter(name).trim());
                } else if (name.equalsIgnoreCase("khjl.zydmxm")) {
                    json_khxx.put("khjl", DMMCUtils.getDM(request.getParameter(name).trim(), "[")); //截取客户经理代码
                } else if (name.equalsIgnoreCase("khjl.zyxm")) { //跳过客户经理姓名
                    continue;
                } else if (name.equalsIgnoreCase("ssdq.dqdmmc")) {
                    json_khxx.put("ssdq", DMMCUtils.getDM(request.getParameter(name).trim(), "[")); //截取所属地区代码
                } else if (name.equalsIgnoreCase("xsgs.gsdmmc")) {
                    json_khxx.put("xsgs", DMMCUtils.getDM(request.getParameter(name).trim(), "[")); //截取销售公司代码
                } else if (name.equalsIgnoreCase("ssdq.dqmc")) { //跳过客户经理姓名
                    continue;
                } else if (name.equalsIgnoreCase("khlx.lxdmmc")) {
                    json_khxx.put("khlx", DMMCUtils.getDM(request.getParameter(name).trim(), "[")); //截取客户类型代码
                } else if (name.equalsIgnoreCase("khlx.lxmc")) { //跳过客户经理姓名
                    continue;
                } else if (name.equalsIgnoreCase("xgyy")) { //跳过修改原因
                    continue;
                } else if (name.startsWith("datagrid")) {
                    continue;
                } else if (name.startsWith("lxr")) { //如果获取到的字段是联系人字段直接则调用存储到联系人
                    String temp = request.getParameter(name).toString();
                    if (temp.startsWith("[{") && temp.endsWith("}]")) {
                        jsonArrayLxr = JSONArray.fromObject(temp);
                    } else {
                        jsonArrayLxr = null;
                    }
                } else if (name.startsWith("soft")) {//如果获取到的时使用软件模块信息则调用存储到联系人
                    String temp = request.getParameter(name).toString();
                    if (temp.startsWith("[{") && temp.endsWith("}]")) {
                        jsonArraySoft = JSONArray.fromObject(request.getParameter(name).toString());
                    } else {
                        jsonArraySoft = null;
                    }
                } else { //否则直接存入客户信息表
                    continue;
                }
            }
            //获取当前录入日期
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = format.format(calendar.getTime());
            Khxx khxx = (Khxx) JSONObject.toBean(json_khxx, Khxx.class);
            //增加备份修改前的信息
            new AddKhxxOldInfo().add(khxx.getKhdm(), time, request.getParameter("xgyy")
                    , request.getSession().getAttribute("userid") + "[" + request.getSession().getAttribute("username") + "]"
                    , session);
            String hqlDel_lxr = "delete Khxx_lxr l where l.khdm = :khdm";
            Query del_lxr = session.createQuery(hqlDel_lxr);
            del_lxr.setString("khdm", khxx.getKhdm());
            int lxrDelOk = del_lxr.executeUpdate();

            //如果删除返回结果没报错且lxr存在内容则执行插入操作
            if (lxrDelOk >= 0 && null != jsonArrayLxr) {
                for (Object object : jsonArrayLxr) {
                    Khxx_lxr khxx_lxr = (Khxx_lxr) (JSONObject.toBean((JSONObject) object, Khxx_lxr.class));
                    khxx_lxr.setKhdm(khxx.getKhdm());
                    khxx_lxr.setLxrbh(GetMD5.getMD5(khxx.getKhdm() + "" + khxx_lxr.getLxrxm() + "" + khxx_lxr.getSex() + khxx_lxr.getCellphone()));
                    //再增加联系人
                    session.saveOrUpdate(khxx_lxr);
                }
            }
            //保存使用软件模块
            //先删除该用户的所有模块
            String hqlDel_model = "delete Khxx_soft m where m.khdm = :khdm";
            Query del_model = session.createQuery(hqlDel_model);
            del_model.setString("khdm", khxx.getKhdm());
            int softDelOk = del_model.executeUpdate();
            if (softDelOk >= 0 && null != jsonArraySoft) {
                for (Object object : jsonArraySoft) {
                    Khxx_soft khxx_soft = (Khxx_soft) (JSONObject.toBean((JSONObject) object, Khxx_soft.class));
                    khxx_soft.setKhdm(khxx.getKhdm());
                    khxx_soft.setKhmc(khxx.getKhmc());
                    //在增加使用模块
                    session.saveOrUpdate(khxx_soft);
                }
            }
            //保存客户信息
            session.saveOrUpdate(khxx);
//修改维护日期
            List cms_rpt_whrq  = QueryData.getSomeEntity("CMS_RPT_WHRQ",khxx.getKhdm(),"khdm");
            if(null!=cms_rpt_whrq){
                Iterator it_whrq =  cms_rpt_whrq.iterator();
                if (it_whrq.hasNext()){
                    CMS_RPT_WHRQ rpt_whrq = (CMS_RPT_WHRQ) it_whrq.next();
                    if(!rpt_whrq.getJzrq().equals(khxx.getWhrq())){
                        rpt_whrq.setUpdateDjh("修改客户信息");
                        rpt_whrq.setUpdateUser(request.getSession().getAttribute("userid")+"["+request.getSession().getAttribute("username")+"]");

                        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        String datenow = formatDate.format(calendar.getTime());
                        rpt_whrq.setLastUpdate(datenow);
                        rpt_whrq.setJzrq(khxx.getWhrq());
                        session.saveOrUpdate(rpt_whrq);
                    }
                }
            }
            if (transaction.isActive()) {
                transaction.commit();
            }
            if (session != null) {
                session.clear();
            }
            json.put("statusCode", "200");
            json.put("message", "修改成功！");
        } catch (Exception e) {
            json.put("statusCode", "300");
            if (!json.has("message")) {
                json.put("message", "提交错误！");
            }
            if (null != transaction && transaction.isActive()) {
                transaction.rollback();
            }
            if (null != session) {
                session.clear();
            }
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //获取所有客户信息，有过滤功能
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String khjl = "";
        String khlx ="";
        String xsgs = "";
        String softModel ="";
        String dqxx ="";
        String keyword ="";
        String mykhxx ="";
        String conditionStr="";
        try {  //

            //获取参数信息
            keyword = request.getParameter("keyword");
            khjl = request.getParameter("khjl");
            if (khjl.trim().length() > 0)
                khjl="'"+khjl.replaceAll(",", "','")+"'";
            khlx = request.getParameter("khlx");
            if (khlx.trim().length() > 0)
                khlx="'"+khlx.replaceAll(",", "','")+"'";
            xsgs = request.getParameter("xsgs");
            if (xsgs.trim().length() > 0)
                xsgs="'"+xsgs.replaceAll(",", "','")+"'";
            softModel = request.getParameter("softModel");
            if (softModel.trim().length() > 0)
                softModel="'"+softModel.replaceAll(",", "','")+"'";
            mykhxx = request.getParameter("mykhxx");
            dqxx = request.getParameter("dqxx");
            if (dqxx.trim().length() > 0) {
                dqxx = "'" + dqxx.replaceAll(",", "','") + "'";
            }
            if(dqxx.length()>0){
                conditionStr =" ssdq in("+dqxx+")";
            }
            //判断软件模块
            if(softModel.length()>0){
                if(conditionStr.length()>0){
                    conditionStr+=" and khdm in ("+"select khdm from Khxx_soft where modelDM in ("+softModel+"))";
                }else{
                    conditionStr=" khdm in ("+"select khdm from Khxx_soft where modelDM in ("+softModel+"))";

                }
            }
            //判断销售公司
            if(xsgs.length()>0){
                if(conditionStr.length()>0){
                    conditionStr+=" and xsgs in ("+xsgs+")";
                }else{
                    conditionStr=" xsgs in ("+xsgs+")";

                }
            }
            //判断客户类型
            if(khlx.length()>0){
                if(conditionStr.length()>0){
                    conditionStr+=" and khlx in ("+khlx+")";
                }else{
                    conditionStr=" khlx in ("+khlx+")";

                }
            }
            //判断客户类经理
            if(khjl.length()>0){
                if(conditionStr.length()>0){
                    conditionStr+=" and khjl in ("+khjl+")";
                }else{
                    conditionStr=" khjl in ("+khjl+")";

                }
            }
            //获取like字符串
            keyword = keyword.replace(" ", "");//替换空格
            String keywordStr = "";
            //如果存在关键字的话则生成关键字字段。
            if (keyword.length() > 0) {
                for(int i = 0 ;i<keyword.length();i++) {
                    keywordStr += "%" + keyword.charAt(i) ;
//                    keyword = keyword.substring(1,keyword.length());
                }
                keywordStr = " khmc like '" +keywordStr+"%'";
//                keywordStr = keywordStr.substring(0, keywordStr.lastIndexOf("or"));
            }

            //判断关键字
            if(keywordStr.length()>0){
                if(conditionStr.length()>0){
                    conditionStr+=" and "+keywordStr;
                }else{
                    conditionStr=keywordStr;

                }
            }

            //最后授予条件where
            if(conditionStr.length()>0){
                conditionStr=" where "+conditionStr;
            }else{
                conditionStr="";

            }

            vector = new Vector();
            json = new JSONObject();
            List  allKh = QueryData.getSomeEntityHQL("from Khxx  " + conditionStr);
            if (null != allKh) {
                Iterator itKh = allKh.iterator();
                //给添加
//            sb.append("{\"id\":\"100\",\"name\":\"销售单管理\",\"pId\":\"0\"},");
                while (itKh.hasNext()) {
                    JSONObject json_data = new JSONObject();
                    Khxx khxx = (Khxx) itKh.next();
                    json_data.put("khdmmc", khxx.getKhdm() + "[" + khxx.getKhmc() + "]");
                    json_data.put("bgdh", khxx.getBgdh());
                    json_data.put("gzdz", khxx.getGzdz());
                    List listDwlx = QueryData.getSomeEntity("Dwlx", khxx.getKhlx(), "lxdm");
                    if (null == listDwlx || null == listDwlx.iterator() || !listDwlx.iterator().hasNext()) {
                        json_data.put("khlx", "获取客户类型失败！");
                    } else {
                        Dwlx dwlx = (Dwlx) listDwlx.iterator().next();
                        if (dwlx == null) {
                            json_data.put("khlx", "获取客户类型失败！");
                        } else {
                            json_data.put("khlx", khxx.getKhlx() + "[" + dwlx.getLxmc() + "]");
                        }
                    }
                    ;
                    List listDqxx = QueryData.getSomeEntity("Dqxx", khxx.getSsdq(), "dqdm");
                    if (null == listDqxx || null == listDqxx.iterator() || !listDqxx.iterator().hasNext()) {
                        json_data.put("ssdq", "获取所属地区失败！");
                    } else {
                        Dqxx dqxx1 = (Dqxx) listDqxx.iterator().next();
                        if (dqxx == null) {
                            json_data.put("ssdq", "获取客户类型失败！");
                        } else {
                            json_data.put("ssdq", khxx.getSsdq() + "[" + dqxx1.getDqmc() + "]");
                        }
                        ;
                    }
                    List listkhjl = QueryData.getSomeEntity("Zyxx", khxx.getKhjl(), "zydm");
                    if (null == listkhjl || null == listkhjl.iterator() || !listkhjl.iterator().hasNext()) {
                        json_data.put("khjl", "获取所属地区失败！");
                    } else {
                        Zyxx zyxx = (Zyxx) listkhjl.iterator().next();
                        if (zyxx == null) {
                            json_data.put("khjl", "获取客户类型失败！");
                        } else {
                            json_data.put("khjl", khxx.getKhjl() + "[" + zyxx.getZyxm() + "]");
                        }
                        ;
                    }
                    ;

                    //获取销售公司信息
                    List listXsgs = QueryData.getSomeEntity("Enterprise", khxx.getXsgs(), "gsdm");
                    if (null != listXsgs && listXsgs.iterator().hasNext()) {
                        Enterprise en = (Enterprise) listXsgs.iterator().next();
                        json_data.put("xsgs", khxx.getXsgs() + "[" + en.getGsmc() + "]");
                    } else {
                        json_data.put("xsgs", khxx.getXsgs());
                    }

                    json_data.put("btn", " <button  onclick = 'getDetiles($(this))' class='getDetail'>+</button>");
                    vector.add(json_data);
                }
                json.put("data", JSONArray.fromObject(vector));
            } else {
                json.put("data", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().print(JSONArray.fromObject(vector));
        }
    }
    //获取某一个客户信息

    public void getOneKhxx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        //JSONObject json  = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        String khdm = "";
        String whrq = "";
        try {
            //获取传递过来的参数
            khdm = request.getParameter("khdmmc");
            khdm = DMMCUtils.getDM(khdm, "[");
            //获取匹配的单位信息
            List allKh = QueryData.getSomeEntity("Khxx", khdm, "khdm");
            //获取单位对应的单位的维护截止日期
            List cms_rpt_whrq = QueryData.getSomeEntity("CMS_RPT_WHRQ",khdm,"khdm");
            if(null!=cms_rpt_whrq){
                 Iterator itWhrq  = cms_rpt_whrq.iterator();
                while(itWhrq.hasNext()){
                    CMS_RPT_WHRQ rpt_whrq = (CMS_RPT_WHRQ) itWhrq.next();
                    whrq =  rpt_whrq.getJzrq().toString();
                }
            }
//            遍历客户信息
            if (null != allKh) {
                Iterator itKh = allKh.iterator();
                while (itKh.hasNext()) {
                    JSONObject json_data = new JSONObject();
                    Khxx khxx = (Khxx) itKh.next();
                    json_data.put("khdm", khxx.getKhdm());
                    json_data.put("ps", khxx.getPs());
                    json_data.put("khmc", khxx.getKhmc());
                    json_data.put("bgdh", khxx.getBgdh());
                    json_data.put("gzdz", khxx.getGzdz());;
                    json_data.put("whrq", ((null==khxx.getWhrq())?whrq:(khxx.getWhrq().compareToIgnoreCase(whrq))>0?khxx.getWhrq():whrq));
                    //获取客户性质（类型）
                    List listdwlx = QueryData.getSomeEntity("Dwlx", khxx.getKhlx(), "lxdm");
                    if (null != listdwlx && listdwlx.iterator().hasNext()) {
                        Dwlx dwlx = (Dwlx) listdwlx.iterator().next();
                        json_data.put("khlx", khxx.getKhlx() + "[" + dwlx.getLxmc() + "]");
                    } else {
                        json_data.put("khlx", khxx.getKhlx());
                    }
                    //获取所述地区
                    List listdqxx = QueryData.getSomeEntity("Dqxx", khxx.getSsdq(), "dqdm");
                    if (null != listdqxx && listdqxx.iterator().hasNext()) {
                        Dqxx dqxx = (Dqxx) listdqxx.iterator().next();
                        json_data.put("ssdq", khxx.getSsdq() + "[" + dqxx.getDqmc() + "]");
                    } else {
                        json_data.put("ssdq", khxx.getSsdq());
                    }
//                    //获取客户经理信息
                    List listzyxx = QueryData.getSomeEntity("Zyxx", khxx.getKhjl(), "zydm");
                    if (null != listzyxx && listzyxx.iterator().hasNext()) {
                        Zyxx zyxx = (Zyxx) listzyxx.iterator().next();
                        json_data.put("khjl", khxx.getKhjl() + "[" + zyxx.getZyxm() + "]");
                    } else {
                        json_data.put("khjl", khxx.getKhjl());
                    }
//                    //获取销售公司信息
                    List listXsgs = QueryData.getSomeEntity("Enterprise", khxx.getXsgs(), "gsdm");
                    if (null != listXsgs && listXsgs.iterator().hasNext()) {
                        Enterprise en = (Enterprise) listXsgs.iterator().next();
                        json_data.put("xsgs", khxx.getXsgs() + "[" + en.getGsmc() + "]");
                    } else {
                        json_data.put("xsgs", khxx.getXsgs());
                    }
                    //  json_data.put("btn"," <button  onclick = 'kkk($(this))' class='getDetail'>+</button>");
                    //把匹配的单位信息存入一个json
                    jsonObject.put("khxx", json_data);
                }
            }
            //获取联系人信息
            List lxrList = QueryData.getSomeEntity("Khxx_lxr", khdm, "khdm");
            vector.clear();
            if (null != lxrList) {
                Iterator lxrIt = lxrList.iterator();
                while (lxrIt.hasNext()) {
                    Khxx_lxr lxr = (Khxx_lxr) (lxrIt.next());
                    JSONObject json = new JSONObject();
                    json.put("age", lxr.getAge());
                    json.put("cellphone", lxr.getCellphone());
                    json.put("lxrbh", lxr.getLxrbh());
                    json.put("lxrxm", lxr.getLxrxm());
                    json.put("qq", lxr.getQq());
                    json.put("ps", lxr.getPs());
                    json.put("qtlxfs", lxr.getQtlxfs());
                    json.put("sex", lxr.getSex());
                    json.put("tel", lxr.getTel());
                    json.put("zw", lxr.getZw());
                    json.put("email", lxr.getEmail());
                    vector.add(json);
                }
            }
            //将客户联系人信息添加到json
            jsonObject.put("khxx_lxr", JSONArray.fromObject(vector));

            //获取软件信息
            List listSoft = QueryData.getSomeEntity("Khxx_soft", khdm, "khdm");
            vector.clear();
            if (null != listSoft) {
                Iterator softIt = listSoft.iterator();
                while (softIt.hasNext()) {
                    JSONObject json = new JSONObject();
                    Khxx_soft soft = (Khxx_soft) softIt.next();
                    // json.clear();
                    json.put("softID", soft.getSoftID());
                    json.put("gmrq", soft.getGmrq());
                    //供应商代码名称
                    List softList = QueryData.getSomeEntity("Gys", soft.getGysDm(), "gysdm");
                    if (null != softList && softList.iterator().hasNext()) {
                        Gys gys = (Gys) softList.iterator().next();
                        json.put("gysDmMc", gys.getGysdm() + "[" + gys.getGysmc() + "]");
                        json.put("gysDm", gys.getGysdm());
                        json.put("gysMc", gys.getGysmc());
                    }
                    //软件代码名称
                    List verList = QueryData.getSomeEntity("SoftVer", soft.getVerDm(), "verDm");
                    if (null != verList && verList.iterator().hasNext()) {
                        SoftVer ver = (SoftVer) verList.iterator().next();
                        json.put("verDmMc", ver.getVerDm() + "[" + ver.getVerMc() + "]");
                        json.put("verDm", ver.getVerDm());
                        json.put("verMc", ver.getVerMc());
                    }
                    //软件模块代码名称
                    List modelList = QueryData.getSomeEntity("SoftModel", soft.getModelDm(), "modelDm");
                    if (null != modelList && modelList.iterator().hasNext()) {
                        SoftModel model = (SoftModel) modelList.iterator().next();
                        json.put("modelDmMc", model.getModelDm() + "[" + model.getModelMc() + "]");
                        json.put("modelDm", model.getModelDm());
                        json.put("modelMc", model.getModelMc());
                    }
                    json.put("yhs", soft.getYhs());
                    json.put("ps", soft.getPs());
                    vector.add(json);
                }
                jsonObject.put("khxx_soft", JSONArray.fromObject(vector));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // System.out.println("json+data:"+JSONArray.fromObject(vector));
            response.getWriter().print(jsonObject);
            System.out.println("客户信息：" + jsonObject.toString());
//            response.getWriter().print(json.toString());
        }
    }

    //获取某个客户的联系人
    public void getLxrAndSoft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Vector vector = new Vector();
        StringBuffer sb = new StringBuffer();
        StringBuffer sbsoft = new StringBuffer();
        String khdm = request.getParameter("khdm");
        try {
            //获取联系人信息
            List allLxr = QueryData.getSomeEntity("Khxx_lxr", khdm, "khdm");
            if (null != allLxr) {
                Iterator itLxr = allLxr.iterator();
                //给添加
                sb.append("<div class=\"tableContent\"><table class=\"table table-bordered table-hover table-striped table-top\" >");
                sb.append("<thead><tr><th align=\"center\"  align=\"center\">姓名</th><th align=\"center\" >性别</th><th align=\"center\"  >年龄</th><th align=\"center\"  >" +
                        "职务</th><th align=\"center\" >座机</th><th align=\"center\"  >手机</th><th align=\"center\"  >QQ</th><th align=\"center\" >其他联系方式</th><th align=\"center\" >备注</th></tr>");
                while (itLxr.hasNext()) {
                    JSONObject json_data = new JSONObject();
                    Khxx_lxr khxx_lxr = (Khxx_lxr) itLxr.next();
                    String lxrxx = "<tr><td align=\"center\"><p>" + khxx_lxr.getLxrxm() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getSex() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getAge() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getZw() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getTel() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getCellphone()
                            + "</p></td><td align=\"center\"><p>" + khxx_lxr.getQq() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getQtlxfs() + "</p></td><td align=\"center\"><p>" + khxx_lxr.getPs() + "</p></td></tr>";
                    sb.append(lxrxx);
                }
                sb.append("<table></div>");
                json.put("lxr", sb.toString());
            } else {
                json.put("lxr", "联系人信息不存在");
            }
            //获取使用软件信息
            sbsoft.append("<div class=\"tableContent\"><table class=\"table table-bordered table-hover table-striped table-top\" >");
            sbsoft.append("<thead><tr><th align=\"center\"  align=\"center\">软件ID</th><th align=\"center\" >软件供应商</th><th align=\"center\"  >软件版本</th><th align=\"center\"  >" +
                    "软件模块</th><th align=\"center\" >用户数</th><th align=\"center\"  >购买日期</th><th align=\"center\"  >备注</th><th align=\"center\" >其他信息1</th><th align=\"center\" >其他信息2</th></tr>");
            List listSoft = QueryData.getSomeEntity("Khxx_soft", khdm, "khdm");
            if (null != listSoft) {
                Iterator softIt = listSoft.iterator();
                while (softIt.hasNext()) {
                    JSONObject json_data = new JSONObject();
                    Khxx_soft khxx_soft = (Khxx_soft) softIt.next();
                    String softxx = "<tr><td align=\"center\"><p>" + khxx_soft.getSoftID() + "</p></td><td align=\"center\"><p>" + khxx_soft.getGysDm() + "[" + khxx_soft.getGysMc() + "]" + "</p></td><td align=\"center\"><p>" + khxx_soft.getVerDm() + "[" + khxx_soft.getVerMc() + "]" + "</p></td><td align=\"center\"><p>" + khxx_soft.getModelDm() + "[" + khxx_soft.getModelMc() + "]" + "</p></td><td align=\"center\"><p>" + khxx_soft.getYhs() + "</p></td><td align=\"center\"><p>" + khxx_soft.getGmrq()
                            + "</p></td><td align=\"center\"><p>" + khxx_soft.getPs() + "</p></td><td align=\"center\"><p>" + "</p></td><td align=\"center\"><p>" + "</p></td></tr>";
                    sbsoft.append(softxx);
                }
                sbsoft.append("<table></div>");
                json.put("soft", sbsoft.toString());
            } else {
                json.put("soft", "软件信息不存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // System.out.println("json+data:"+JSONArray.fromObject(vector));
            response.getWriter().print(json.toString());
//            response.getWriter().print(json.toString());
        }

    }

    //获取所有客户信息给dialog
    public void getAllForDialog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-json");
        String dqdmmc = "";
        String keyword = "";
        JSONObject json = null;
        Vector vector = null;
        try {
            //获取参数信息
            keyword = request.getParameter("keyword");
            dqdmmc = request.getParameter("dqdmmc");
            String dqdmStr = "";//where条件中需要使用的khdm
            String[] dqdmmcArray = dqdmmc.split(",");
            //循环获地区代码
            for (int i = 0; i < dqdmmcArray.length; i++) {
                if (dqdmmcArray[i].replaceAll(" ", "").length() <= 0) continue;
                if (dqdmmcArray[i].contains("-")) {
                    dqdmStr += "'" + dqdmmcArray[i].substring(0, dqdmmcArray[i].indexOf("-")) + "',";
                } else {
                    dqdmStr += "'" + dqdmmcArray[i] + "',";
                }
            }
            if (dqdmStr.trim().length() > 0) {
                dqdmStr = dqdmStr.substring(0, dqdmStr.lastIndexOf(","));
            }
            //获取like字符串
            keyword = keyword.replace(" ", "");//替换空格
            String keywordStr = "";
            //如果存在关键字的话则生成关键字字段。
            if (keyword.length() > 0) {
                for(int i = 0 ;i<keyword.length();i++) {
                    keywordStr += "%" + keyword.charAt(i) ;
//                    keyword = keyword.substring(1,keyword.length());
                }
                keywordStr = " khmc like '" +keywordStr+"%'";
//                keywordStr = keywordStr.substring(0, keywordStr.lastIndexOf("or"));
            }

            vector = new Vector();
            json = new JSONObject();
            StringBuffer sb = new StringBuffer();
            List allEn = null;
            String extendsStr = "";
            //如果存在地区代码则
            if (dqdmStr.trim().length() > 0) {
                extendsStr = " ssdq in (" + dqdmStr + ") ";
            }
            //如果存在关键字的话则关键字条件
            if (keywordStr.length() > 0) {
                if (extendsStr.length() > 0) {
                    extendsStr += " and " + keywordStr;
                } else {
                    extendsStr += keywordStr;
                }
            }
            //判断是否存在条件。
            if (extendsStr.trim().length() > 0) {
                extendsStr = " where " + extendsStr;
            }
            allEn = QueryData.getSomeEntityHQL("from Khxx  " + extendsStr);
            if (null == allEn) {
                return;// throw new MyException("不存在数据");
            }
            Iterator it = allEn.iterator();
            while (it.hasNext()) {
                JSONObject json_data = new JSONObject();
                Khxx khxx = (Khxx) it.next();
                json_data.put("khdmmc", khxx.getKhdm() + "[" + khxx.getKhmc() + "]");
                json_data.put("gzdz", khxx.getGzdz());
                json_data.put("selectCell", "<a href=\"javascript:;\" data-toggle=\"lookupback\" data-args=\"{pid:'1', khdm:'" + khxx.getKhdm() + "',khmc:'" + khxx.getKhmc() + "',khdmmc:'" + khxx.getKhdm() + "[" + khxx.getKhmc() + "]'}\" class=\"btn btn-blue\" title=\"选择本项\" data-icon=\"check\">选择</a>");
                List listDwlx = QueryData.getSomeEntity("Dwlx", khxx.getKhlx(), "lxdm");
                if (null == listDwlx || null == listDwlx.iterator() || !listDwlx.iterator().hasNext()) {
                    json_data.put("khlx", "获取客户类型失败！");
                } else {
                    Dwlx dwlx = (Dwlx) listDwlx.iterator().next();
                    if (dwlx == null) {
                        json_data.put("khlx", "获取客户类型失败！");
                    } else {
                        json_data.put("khlx", khxx.getKhlx() + "[" + dwlx.getLxmc() + "]");
                    }
                }
                ;
                List listDqxx = QueryData.getSomeEntity("Dqxx", khxx.getSsdq(), "dqdm");
                if (null == listDqxx || null == listDqxx.iterator() || !listDqxx.iterator().hasNext()) {
                    json_data.put("ssdq", "获取所属地区失败！");
                } else {
                    Dqxx dqxx = (Dqxx) listDqxx.iterator().next();
                    if (dqxx == null) {
                        json_data.put("ssdq", "获取客户类型失败！");
                    } else {
                        json_data.put("ssdq", khxx.getSsdq() + "[" + dqxx.getDqmc() + "]");
                    }
                    ;
                }
                List listkhjl = QueryData.getSomeEntity("Zyxx", khxx.getKhjl(), "zydm");
                if (null == listkhjl || null == listkhjl.iterator() || !listkhjl.iterator().hasNext()) {
                    json_data.put("khjl", "获取所属地区失败！");
                } else {
                    Zyxx zyxx = (Zyxx) listkhjl.iterator().next();
                    if (zyxx == null) {
                        json_data.put("khjl", "获取客户类型失败！");
                    } else {
                        json_data.put("khjl", khxx.getKhjl() + "[" + zyxx.getZyxm() + "]");
                    }
                    ;
                }
                ;

                //获取销售公司信息
                List listXsgs = QueryData.getSomeEntity("Enterprise", khxx.getXsgs(), "gsdm");
                if (null != listXsgs && listXsgs.iterator().hasNext()) {
                    Enterprise en = (Enterprise) listXsgs.iterator().next();
                    json_data.put("xsgs", khxx.getXsgs() + "[" + en.getGsmc() + "]");
                } else {
                    json_data.put("xsgs", khxx.getXsgs());
                }

                json_data.put("btn", " <button  onclick = 'getDetiles($(this))' class='getDetail'>+</button>");
                vector.add(json_data);
            }
//            while (it.hasNext()) {
//                JSONObject json_data = new JSONObject();
//                Khxx en = (Khxx) it.next();
//                json_data.put("khdmmc", en.getKhdm()+"["+en.getKhmc()+"]");
//                json_data.put("ssdq", en.getSsdq());
//                json_data.put("gzdz", en.getGzdz());
//                json_data.put("khlx", en.getKhlx());
//                json_data.put("khjl", en.getKhjl());
//                json_data.put("selectCell", "<a href=\"javascript:;\" data-toggle=\"lookupback\" data-args=\"{pid:'1', khdm:'" + en.getKhdm() + "',khmc:'" + en.getKhmc() + "',khdmmc:'"+en.getKhdm()+"["+en.getKhmc()+"]'}\" class=\"btn btn-blue\" title=\"选择本项\" data-icon=\"check\">选择</a>");
//                vector.add(json_data);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("");
        } finally {
            response.getWriter().print(JSONArray.fromObject(vector));
        }
    }
}
