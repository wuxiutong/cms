package wxt.business.servlet.rpt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import wxt.utils.DMMCUtils;
import wxt.utils.HibernateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by wuxiutong on 15/9/4.
 */
//单位应收款，预计收款和已经收款
public class Get_RPT_KhxxForInitDwysk extends HttpServlet {
    JSONArray jsonArray = new JSONArray();

    private void getValueJSON(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        Session session = HibernateUtils.getSession();
        JSONArray json = new JSONArray();
        final Vector vector = new Vector();
        String dqxx = request.getParameter("dqxx");
        String keyword = request.getParameter("keyword");
        String kjnd = request.getParameter("kjnd");
        String khjl = request.getParameter("khjl");
        if (kjnd.trim().length()<=0) {
            kjnd = "000000000";
        }
        final String kjndStr ="'"+kjnd+"'";
        Transaction transaction = session.beginTransaction();
        try {
            dqxx = dqxx.trim();
            if(dqxx.length()>0){
                dqxx = "'"+dqxx.replaceAll(",","','")+"'";
            }
            khjl = khjl.trim();
            if(khjl.length()>0){
                khjl = "'"+khjl.replaceAll(",","','")+"'";
            }
            String temp="";
            if (null!=keyword && keyword.length() > 0) {
                for(int i = 0 ;i<keyword.length();i++) {
                    temp += "%" + keyword.charAt(i) ;
                }
                temp = " '" +temp+"%'";
            }
            final String keywordStr =temp;
            final String ssdq = dqxx;
            final String zydm = khjl;
            session.doWork(
//定义一个匿名类，实现了Work接口
                    new Work() {
                        public void execute(Connection connection) throws SQLException {
//经由过程JDBC API执行SQL语句
                            Vector vv = new Vector();
                            PreparedStatement ps = connection.prepareStatement("{call cms_pro_querydwysk(?,?,?,?)}");
                            ps.setString(1, kjndStr);
                            ps.setString(2, ssdq);
                            ps.setString(3, keywordStr);
                            ps.setString(4, zydm);
                            ResultSet rs = ps.executeQuery();
                            try {
                                while (rs.next()) {
                                    final JSONObject jsonObject = new JSONObject();
                                    ResultSetMetaData metadata = rs.getMetaData();
                                    for (int i = 1; i <= metadata.getColumnCount(); i++) {
                                        jsonObject.put(metadata.getColumnLabel(i), rs.getString(i));
                                        System.out.println(i+"++++++++++"+metadata.getColumnLabel(i)+":"+rs.getString(i)+";");
                                    }
                                    vv.add(jsonObject);
                                }
                                getValueJSON(JSONArray.fromObject(vv));
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (null != ps) {
                                    ps.close();
                                }
                                if (null != rs) {
                                    rs.close();
                                }
                            }
                        }
                    }
            );
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session  ) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(this.jsonArray);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
