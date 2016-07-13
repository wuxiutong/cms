package wxt.business.servlet.rpt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
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
 * Created by wuxiutong on 15/8/16.
 */
public class Get_RPT_WHRQ extends HttpServlet {
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
        String dqxx ="";
        String khjl ="";
        String keyword="";
        String keywordStr="";
        String conditionStr="";
        Transaction transaction = session.beginTransaction();
        try {
            khjl = request.getParameter("khjl");
            keyword = request.getParameter("keyword");
            if (khjl.trim().length() > 0) {
                khjl = "'" + khjl.replaceAll(",", "','") + "'";
            }
            dqxx = request.getParameter("dqxx");
            if (dqxx.trim().length() > 0) {
                dqxx = "'" + dqxx.replaceAll(",", "','") + "'";
            }
            //如果存在关键字的话则生成关键字字段。
            if (null!=keyword && keyword.length() > 0) {
                for(int i = 0 ;i<keyword.length();i++) {
                    keywordStr += "%" + keyword.charAt(i) ;
                }
                keywordStr = " '" +keywordStr+"%'";
            }
            //获取参数信息
            keyword = request.getParameter("keyword");
            final  String ssdq = dqxx;
            final  String zydm = khjl;
            final  String keyword1 = keywordStr;
            session.doWork(
//定义一个匿名类，实现了Work接口
                    new Work() {
                        public void execute(Connection connection) throws SQLException {
//经由过程JDBC API执行SQL语句
                            Vector vv = new Vector();
                            PreparedStatement ps = connection.prepareStatement("{call cms_pro_querywhrq(?,?,?)}");
                            ps.setString(1, ssdq);
                            ps.setString(2, zydm);
                            ps.setString(3, keyword1);
                            ResultSet rs = ps.executeQuery();
                            try {
                                while (rs.next()) {
                                    final JSONObject jsonObject = new JSONObject();
                                    ResultSetMetaData metadata = rs.getMetaData();
                                    for (int i = 1; i <= metadata.getColumnCount(); i++) {
                                        jsonObject.put(metadata.getColumnLabel(i), rs.getString(i));
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
