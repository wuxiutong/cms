package wxt.business.actions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.dao.UpdateData;
import wxt.model.CWBills;
import wxt.utils.DMMCUtils;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wuxiutong on 2015/9/1.
 */
public class Action_CWBill {
    //新增财务单据信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement().toString().trim();
                String value = request.getParameter(name).toString().trim();
                //特殊处理
                if (name.equalsIgnoreCase("khxx.khdmmc")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("khdm不存在！");
                    } else {
                        jsonBean.put("khdm", DMMCUtils.getDM(value, "["));
                        jsonBean.put("khmc", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("xsgs.gsdmmc")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("gsdm不存在！");
                    } else {
                        jsonBean.put("gsdm", DMMCUtils.getDM(value, "["));
                        jsonBean.put("gsmc", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("khjl.zydmxm")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("kpr代码不存在！");
                    } else {
                        jsonBean.put("kpr", DMMCUtils.getDM(value, "["));
                        jsonBean.put("kprxm", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("khjl.zydmxm")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("kpr代码不存在！");
                    } else {
                        jsonBean.put("kpr", DMMCUtils.getDM(value, "["));
                        jsonBean.put("kprxm", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("lrrdmmc")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("lrr代码不存在！");
                    } else {
                        jsonBean.put("lrr", request.getSession().getAttribute("userid"));
                        jsonBean.put("lrrxm", request.getSession().getAttribute("username"));
                    }
                } else  //如果是直接可以存入，而不用改变代码的
                    if (name.equalsIgnoreCase("id") || name.equalsIgnoreCase("kprq") || name.equalsIgnoreCase("djlx")
                            || name.equalsIgnoreCase("fylx") || name.equalsIgnoreCase("nx") || name.equalsIgnoreCase("qsrq")
                            || name.equalsIgnoreCase("jzrq") || name.equalsIgnoreCase("je") || name.equalsIgnoreCase("ps")
                            || name.equalsIgnoreCase("fph") || name.equalsIgnoreCase("kjnd")) {
                        jsonBean.put(name, request.getParameter(name.trim()));
                    } else if (name.equalsIgnoreCase("shrdmmc")) { //如果是审核人则特殊处理
                        jsonBean.put("shr", "");
                        jsonBean.put("shrxm", "");
                    } else if (name.equalsIgnoreCase("skrdmmc")) {//如果是收款人也特殊处理
                        jsonBean.put("skr", "");
                        jsonBean.put("skrxm", "");
                    } else {
                        continue;
                    }
            }
            //获取最大单据号
            List list = QueryData.getSomeEntityHQL("select max(id) from CWBills");
            if (null != list && list.iterator().hasNext() && null != list.iterator().next()) {
                Iterator it = list.iterator();
                int i = (int) it.next();
                i++;
                json.put("id", String.valueOf(i));
                jsonBean.put("id", String.valueOf(i));
            } else {
                //如果没有获取到当年最大的单据号，则直接从当年单据0001号编码
                json.put("id", Calendar.getInstance().get(Calendar.YEAR) + "" + "0001");
                jsonBean.put("id", Calendar.getInstance().get(Calendar.YEAR) + "" + "0001");

            }
            //获取当前录入日期
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = format.format(calendar.getTime());
            jsonBean.put("lrrq", time);
            //转换成bean
            CWBills bills = (CWBills) JSONObject.toBean(jsonBean, CWBills.class);
            bills.setZt("0");
            session.save(bills);
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e);
            json.put("id", "");
        } finally {
            if (null != session ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }

    }

    //修改财务单据
    public void alter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement().toString().trim();
                String value = request.getParameter(name).toString().trim();
                //特殊处理
                if (name.equalsIgnoreCase("khxx.khdmmc")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("khdm不存在！");
                    } else {
                        jsonBean.put("khdm", DMMCUtils.getDM(value, "["));
                        jsonBean.put("khmc", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("xsgs.gsdmmc")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("gsdm不存在！");
                    } else {
                        jsonBean.put("gsdm", DMMCUtils.getDM(value, "["));
                        jsonBean.put("gsmc", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("khjl.zydmxm")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("kpr代码不存在！");
                    } else {
                        jsonBean.put("kpr", DMMCUtils.getDM(value, "["));
                        jsonBean.put("kprxm", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("khjl.zydmxm")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("kpr代码不存在！");
                    } else {
                        jsonBean.put("kpr", DMMCUtils.getDM(value, "["));
                        jsonBean.put("kprxm", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else if (name.equalsIgnoreCase("lrrdmmc")) {
                    if (null == DMMCUtils.getDM(value, "[")) {
                        throw new Exception("lrr代码不存在！");
                    } else {
                        jsonBean.put("lrr", DMMCUtils.getDM(value, "["));
                        jsonBean.put("lrrxm", DMMCUtils.getFirst2LastMC(value, "[", "]"));
                    }
                } else  //如果是直接可以存入，而不用改变代码的
                    if (name.equalsIgnoreCase("id") || name.equalsIgnoreCase("kprq") || name.equalsIgnoreCase("djlx")
                            || name.equalsIgnoreCase("fylx") || name.equalsIgnoreCase("nx") || name.equalsIgnoreCase("qsrq")
                            || name.equalsIgnoreCase("jzrq") || name.equalsIgnoreCase("je") || name.equalsIgnoreCase("ps")
                            || name.equalsIgnoreCase("fph") || name.equalsIgnoreCase("kjnd")) {
                        jsonBean.put(name, request.getParameter(name.trim()));
                    } else if (name.equalsIgnoreCase("shrdmmc")) { //如果是审核人则特殊处理
                        jsonBean.put("shr", "");
                        jsonBean.put("shrxm", "");
                    } else if (name.equalsIgnoreCase("skrdmmc")) {//如果是收款人也特殊处理
                        jsonBean.put("skr", "");
                        jsonBean.put("skrxm", "");
                    } else {
                        continue;
                    }
            }
            //检查是否存在单据号码
            List list = QueryData.getSomeEntity("CWBills", Integer.valueOf(jsonBean.get("id").toString()), "id");
            if (null == list && list.isEmpty()) {
                throw new Exception("当前单据不存在，请检查！");
            }
            //获取当前录入日期
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = format.format(calendar.getTime());
            jsonBean.put("lrrq", time);
            //转换成bean
            CWBills bills = (CWBills) JSONObject.toBean(jsonBean, CWBills.class);
            bills.setZt("0");
            session.update(bills);
            transaction.commit();
            json.put("statusCode", "200");
            json.put("message", "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e);
            json.put("id", "");
        } finally {
            if (null != session  ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }

    }

    //删除财务单据信息
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_SC(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "删除失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "删除失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "删除 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //查询某个单据具体信息
    public void getOneCWBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject jsonObject = new JSONObject();
        String id = "";
        try {
            //获取传递过来的参数
            id = request.getParameter("id");
            //获取匹配的单位信息
            List listCWBill = QueryData.getSomeEntity("CWBills", Integer.valueOf(id), "id");
            if (null != listCWBill && listCWBill.size() > 0) {
                Iterator itBill = listCWBill.iterator();
                if (itBill.hasNext()) {
                    CWBills bill = (CWBills) itBill.next();
                    jsonObject = JSONObject.fromObject(bill);
                } else {
                    jsonObject.put("errorMessage", "未查找到单据！");
                }
            } else {
                jsonObject.put("errorMessage", "未查找到单据！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("errorMessage", e.getMessage());
        } finally {
            response.getWriter().print(jsonObject);
        }
    }

    //获取所有财务单据
    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONArray json = new JSONArray();
        Vector vector = new Vector();
        try {  //获取所有地区信息
            StringBuffer sb = new StringBuffer();
            List allBills = QueryData.getAllEntity("CWBills");
            //如果返回的结果集不为空则进行数据收集工作
            if (null != allBills) {
                Iterator itBills = allBills.iterator();
                vector.clear();
                while (itBills.hasNext()) {
                    CWBills cwBills = (CWBills) itBills.next();
                    JSONObject jsonBean = new JSONObject();
                    jsonBean = JSONObject.fromObject(cwBills);
                    jsonBean.put("khdmmc", cwBills.getKhdm() + "[" + cwBills.getKhmc() + "]");
                    jsonBean.put("gsdmmc", cwBills.getGsdm() + "[" + cwBills.getGsmc() + "]");
                    jsonBean.put("kpr", cwBills.getKpr() + "[" + cwBills.getKprxm() + "]");
                    jsonBean.put("shr", cwBills.getShr() + "[" + cwBills.getShrxm() + "]");
                    jsonBean.put("skr", cwBills.getSkr() + "[" + cwBills.getSkrxm() + "]");
                    jsonBean.put("lrr", cwBills.getLrr() + "[" + cwBills.getLrrxm() + "]");
                    if (cwBills.getDjlx().equalsIgnoreCase("xsd")) {
                        jsonBean.put("djlx", "销售单");
                    } else {
                        jsonBean.put("djlx", "维护单");
                    }
                    ;
                    switch ((null != cwBills.getFylx()) ? cwBills.getFylx().trim() : "") {
                        case "once":
                            jsonBean.put("fylx", "按次未维护费");
                            break;
                        case "year":
                            jsonBean.put("fylx", "年度维护费");
                            break;
                        case "jzf":
                            jsonBean.put("fylx", "建账费");
                            break;
                        case "other":
                            jsonBean.put("fylx", "其他");
                            break;
                        default:
                            jsonBean.put("fylx", cwBills.getFylx());
                    }
                    jsonBean.put("lrr", cwBills.getLrr() + "[" + cwBills.getLrrxm() + "]");
                    vector.add(jsonBean);
                }
                json = JSONArray.fromObject(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().print(json);
        }
    }

    //获取某个状态的单据
    public void getSomeZTCWBill(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONArray json = new JSONArray();
        Vector vector = new Vector();
        try {  //获取所有地区信息
            StringBuffer sb = new StringBuffer();
            String zt = request.getParameter("zt");
            List allBills = QueryData.getSomeEntityLike("CWBills", zt, "zt");
            //如果返回的结果集不为空则进行数据收集工作
            if (null != allBills) {
                Iterator itBills = allBills.iterator();
                vector.clear();
                while (itBills.hasNext()) {
                    CWBills cwBills = (CWBills) itBills.next();
                    JSONObject jsonBean = new JSONObject();
                    jsonBean = JSONObject.fromObject(cwBills);
                    jsonBean.put("khdmmc", cwBills.getKhdm() + "[" + cwBills.getKhmc() + "]");
                    jsonBean.put("gsdmmc", cwBills.getGsdm() + "[" + cwBills.getGsmc() + "]");
                    jsonBean.put("kpr", cwBills.getKpr() + "[" + cwBills.getKprxm() + "]");
                    jsonBean.put("shr", cwBills.getShr() + "[" + cwBills.getShrxm() + "]");
                    jsonBean.put("skr", cwBills.getSkr() + "[" + cwBills.getSkrxm() + "]");
                    jsonBean.put("lrr", cwBills.getLrr() + "[" + cwBills.getLrrxm() + "]");
                    if (cwBills.getDjlx().equalsIgnoreCase("xsd")) {
                        jsonBean.put("djlx", "销售单");
                    } else {
                        jsonBean.put("djlx", "维护单");
                    }
                    ;
                    switch ((null != cwBills.getFylx()) ? cwBills.getFylx().trim() : "") {
                        case "once":
                            jsonBean.put("fylx", "按次未维护费");
                            break;
                        case "year":
                            jsonBean.put("fylx", "年度维护费");
                            break;
                        case "jzf":
                            jsonBean.put("fylx", "建账费");
                            break;
                        case "other":
                            jsonBean.put("fylx", "其他");
                            break;
                        default:
                            jsonBean.put("fylx", cwBills.getFylx());
                    }
                    jsonBean.put("lrr", cwBills.getLrr() + "[" + cwBills.getLrrxm() + "]");
                    vector.add(jsonBean);
                }
                json = JSONArray.fromObject(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.getWriter().print(json);
        }
    }

    //财务单据审核
    public void sh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_SH(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "审核失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "审核失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "成功审核 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }

    }

    //财务单据确认款项到账
    public void sk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_SK(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "确认款项到账失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "确认款项到账失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "成功确认款项到账 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session  ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //单据销审
    public void xs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_XS(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "销审失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "销审失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "成功销审 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //单据取消款项到账
    public void tk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_TK(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "取消到账失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "取消到账失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "取消到账 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session  ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //单据作废
    public void zf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_ZF(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "作废失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "作废失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "作废 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session  ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //财务单据恢复
    public void hf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONObject json = new JSONObject();
        JSONObject jsonBean = new JSONObject();
        Transaction transaction = session.beginTransaction();
        try {
            String ids = request.getParameter("ids");
            String userID = request.getSession().getAttribute("userid").toString();
            if (userID == null || "".equals(userID.trim())) {
                throw new Exception("无法获取当前登陆的用户！");
            }
            String[] ids_array = ids.split(";");
            int result = UpdateData.CWBills_HF(ids_array, userID);
            if (result <= 0) { //小于0则代表更新失败！
                json.put("statusCode", "300");
                json.put("message", "恢复失败，请重试！");
            } else if (-789 == result) {//如果返回的是-789则代表当前库中不存在当前userID
                json.put("statusCode", "300");
                json.put("message", "恢复失败，当前库中为存在ID为：" + userID + "的用户，请重新登陆！");
            } else {
                json.put("statusCode", "200");
                json.put("message", "恢复 " + result + " 条记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", "错误信息：" + e.getMessage());
        } finally {
            if (null != session ) {
                session.close();
            }
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

}
