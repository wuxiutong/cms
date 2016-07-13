package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.exceptions.CustomException;
import wxt.model.CMS_RPT_DWYSK;
import wxt.model.CMS_RPT_WHRQ;
import wxt.utils.DMMCUtils;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wuxiutong on 2015/9/7.
 */
public class Action_InitRpt extends HttpServlet {
    //更新单位应收款
    public void updateDWYSK(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        //返回值json
        JSONObject jsonObject = new JSONObject();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String values = request.getParameter("json");
            // System.out.println("++++++++++++++++++++++++++++++++++json:"+request.getParameter("json"));
            // throw new CustomException("");
//            JSONArray jsonArray = JSONArray.fromObject(values);
//            Iterator iterator = jsonArray.iterator();
//            while (iterator.hasNext()) {
            JSONObject json = JSONObject.fromObject(values);
            String khdmmc = json.getString("khdmmc");
            String khdm = DMMCUtils.getDM(khdmmc, "[");
            String kjnd = json.getString("kjnd");
            String yjskStr = json.getString("yjsk");
            if (yjskStr == null || "".equals(yjskStr.trim())) {
                yjskStr = "0.00";
            }
            double yjsk = Double.valueOf(yjskStr);
            if (khdm == null || khdm.equals("")) {
                throw new CustomException("客户代码和年度不正确！");
            }
            String cxSql = "select 1 from CMS_RPT_DWYSK  as t where t.kjnd = :kjnd and t.khdm=:khdm";
            Query query = session.createQuery(cxSql);
            query.setString("kjnd", kjnd);
            query.setString("khdm", khdm);
            List list = query.list();
            if (list != null && list.size() > 0) {
                String updateSql = "update CMS_RPT_DWYSK set yjsk = :yjsk where khdm = :khdm and kjnd = :kjnd";
                Query updateQuery = session.createQuery(updateSql);
                updateQuery.setString("khdm", khdm);
                updateQuery.setString("kjnd", kjnd);
                updateQuery.setDouble("yjsk", yjsk);
                updateQuery.executeUpdate();
            } else {
                CMS_RPT_DWYSK dwysk = new CMS_RPT_DWYSK();
                dwysk.setKhdm(khdm);
                dwysk.setYsk(0.00);
                dwysk.setYjsk(yjsk);
                dwysk.setKjnd(kjnd);
                session.save(dwysk);
            }
//            }
            transaction.commit();
            jsonObject.put("statusCode", "200");
            jsonObject.put("message", "成功！");
            jsonObject.put("navTabId", "");
            jsonObject.put("rel", "");
            jsonObject.put("callbackType", "");
        } catch (Exception e) {
            transaction.rollback();
            jsonObject.put("statusCode", "300");
            jsonObject.put("message", "保存错误！");
            jsonObject.put("navTabId", "");
            jsonObject.put("rel", "");
            jsonObject.put("callbackType", "");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            response.getWriter().print(jsonObject);
        }
    }

    //更新客户的维护日期
    public void updateWHRQ(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        //返回值json
        JSONObject jsonObject = new JSONObject();
        Session session = HibernateUtils.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            String values = request.getParameter("json");
            JSONObject json = JSONObject.fromObject(values);
            String khdmmc = json.getString("khdmmc");
            String khdm = DMMCUtils.getDM(khdmmc, "[");
            String qsrq ="";
            if(json.has("qsrq")) {
                qsrq = json.getString("qsrq");
            }
            String jzrq = json.getString("jzrq");
            String cxSql = "select 1 from CMS_RPT_WHRQ  as t where  t.khdm=:khdm";
            Query query = session.createQuery(cxSql);
            query.setString("khdm", khdm);
            List list = query.list();
            if (list != null && list.size() > 0) {
                String updateSql = "update CMS_RPT_WHRQ set qsrq = :qsrq,jzrq=:jzrq,updateDjh=:updateDjh,updateUser=:updateUser,lastUpdate=:lastUpdate where khdm = :khdm ";
                Query updateQuery = session.createQuery(updateSql);
                updateQuery.setString("khdm", khdm);
                updateQuery.setString("qsrq", qsrq);
                updateQuery.setString("jzrq", jzrq);
                updateQuery.setString("updateDjh", "初始化、更新");
                updateQuery.setString("updateUser", request.getSession().getAttribute("userid")+"["+request.getSession().getAttribute("username")+"]");
                //获取当前录入日期
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String time = format.format(calendar.getTime());
                updateQuery.setString("lastUpdate", time);
                updateQuery.executeUpdate();
            } else {
                CMS_RPT_WHRQ whrq = new CMS_RPT_WHRQ();
                whrq.setKhdm(khdm);
                whrq.setKhmc(DMMCUtils.getDesignMC(khdmmc, "[", "]", 1));
                whrq.setQsrq(qsrq);
                whrq.setJzrq(jzrq);
                whrq.setUpdateDjh("初始化、更新");
                whrq.setUpdateUser(request.getSession().getAttribute("userid")+"["+request.getSession().getAttribute("username")+"]");
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String time = format.format(calendar.getTime());
                whrq.setLastUpdate(time);
                session.save(whrq);
            }
            transaction.commit();
            jsonObject.put("statusCode", "200");
            jsonObject.put("message", "成功！");
            jsonObject.put("navTabId", "");
            jsonObject.put("rel", "");
            jsonObject.put("callbackType", "");
        } catch (Exception e) {
            transaction.rollback();
            jsonObject.put("statusCode", "300");
            jsonObject.put("message", "保存错误！");
            jsonObject.put("navTabId", "");
            jsonObject.put("rel", "");
            jsonObject.put("callbackType", "");
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            response.getWriter().print(jsonObject);
        }
    }
}
