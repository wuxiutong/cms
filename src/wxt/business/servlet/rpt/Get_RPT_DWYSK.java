package wxt.business.servlet.rpt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.utils.DMMCUtils;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by wuxiutong on 15/9/4.
 */
//单位应收款，预计收款和已经收款
public class Get_RPT_DWYSK extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONArray json = new JSONArray();
        Vector vector = new Vector();
        String dqxx = request.getParameter("dqxx").toString();
        dqxx = dqxx.replaceAll(" ", "");
        String keyword = request.getParameter("keyword").toString();
        keyword = keyword.replaceAll(" ", "");
        String keywordStr="";
        String kjnd = request.getParameter("kjnd").toString();
        kjnd = DMMCUtils.getDM(kjnd,"[");
        String khjl = request.getParameter("khjl").toString();
        khjl = khjl.replaceAll(" ", "");
        Transaction transaction = session.beginTransaction();
        try {
            String sql = "select  CONCAT(khxx.khdm ,'[',khxx.khmc,']') as khdm,rpt.yjsk,rpt.ysk,rpt.kjnd,concat(khxx.ssdq,'[',dqxx.dqmc,']') as ssdq" +
                    " ,concat(khxx.khlx,'[',dwlx.lxmc,']') as khlx,concat(khxx.khjl,'[',zyxx.zyxm,']') as khjl from  CMS_RPT_DWYSK  as rpt , Khxx  as khxx" +
                    ",Dqxx as dqxx,Zyxx as zyxx,Dwlx as dwlx  where  rpt.khdm = khxx.khdm and  " +
                    " (dqxx.dqdm  = substring( khxx.ssdq ,1,locate('[',khxx.ssdq)-1)  or dqxx.dqdm  =   khxx.ssdq) and " +
                    " (dwlx.lxdm  = substring( khxx.khlx ,1,locate('[',khxx.khlx)-1)  or dwlx.lxdm =   khxx.khlx) and " +
                    " (zyxx.zydm  = substring( khxx.khjl ,1,locate('[',khxx.khjl)-1)  or zyxx.zydm =   khxx.khjl) ";
            if (dqxx.length()>0) {
                sql += " and (khxx.ssdq in ('"+dqxx.replaceAll(",","','")+"'))" ;
            }
             if (khjl.length()>0) {
                sql += " and (khxx.khjl in('"+khjl.replaceAll(",","','")+"'))"  ;
            }
            if (null != kjnd && !"".equals(kjnd.trim())) {
                sql += " and rpt.kjnd = '"+kjnd +"'";
            }
            if (keyword.length() > 0) {
                for(int i = 0 ;i<keyword.length();i++) {
                    keywordStr += "%" + keyword.charAt(i) ;
                }
                keywordStr = " khxx.khmc like '" +keywordStr+"%'";
                sql+=" and "+keywordStr;
            }
            Query query = session.createQuery(sql);
            List list = query.list();
            if (null != list && list.iterator().hasNext()) {
                Iterator iterator = list.iterator();
                //循环获取
                while (iterator.hasNext()) {
                    JSONObject jsonObject = new JSONObject();
                    Object[] map = (Object[]) iterator.next();
                    jsonObject.put("khdmmc",map[0].toString());
                    jsonObject.put("yjsk",map[1].toString());
                    jsonObject.put("ysk",map[2].toString());
                    jsonObject.put("kjnd",map[3].toString());
                    jsonObject.put("ssdq",map[4].toString());
                    jsonObject.put("khlx",map[5].toString());
                    jsonObject.put("khjl",map[6].toString());
                    vector.add(jsonObject);
                }
                json = JSONArray.fromObject(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session ) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
