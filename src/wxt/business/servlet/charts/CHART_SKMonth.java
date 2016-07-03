package wxt.business.servlet.charts;

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
 * Created by wuxiutong on 15/5/20.
 * 按月收款servlet
 */
public class CHART_SKMonth extends HttpServlet {
    JSONArray jsonArray = new JSONArray();

    private void getValueJSON(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONArray jsonArrayXM = new JSONArray();//条目项目如"预计收入"和"实际收入"则是对比项目；
        JSONArray jsonArrayY = new JSONArray();//Y轴项目；
        JSONArray jsonArrayData1 = new JSONArray();//内容项目；
        JSONArray jsonArrayData2 = new JSONArray();//内容项目；
        String head1 = "";
        String head3 = "";
        String head5 = "";
        String head6 = "";
        String head8 = "";
        String buttom1 = "";
        String jsonData = "";
        String kjnd = "";
        Session session=null;
        try {
             session = HibernateUtils.getSession();
            kjnd = request.getParameter("kjnd");
            final String kjndStr = kjnd;
            Transaction transaction = session.beginTransaction();
            session.doWork(
                    new Work() {
                        public void execute(Connection connection) throws SQLException {
                            Vector vv = new Vector();
                            PreparedStatement ps = connection.prepareStatement("{call ccc.cms_pro_queryMonth_bykjnd(?)}");
                            ps.setString(1, kjndStr);
                            ResultSet rs = ps.executeQuery();
                            try {
                                while (rs.next()) {
                                    final JSONObject jsonObject = new JSONObject();
                                    ResultSetMetaData metadata = rs.getMetaData();
                                    for (int i = 1; i <= metadata.getColumnCount(); i++) {
                                        System.out.println(i+":"+rs.getString(i)+":"+rs.getInt(i));
                                        vv.add(rs.getInt(i));
                                   }
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
            head1 = "{" +
                    "            \"tooltip\": {" +
                    "            \"trigger\": \"axis\"" +
                    "        }," +
                    "            \"legend\": {" +
                    "            \"data\": ";

            head3 = "}," +
                    "            \"toolbox\": {" +
                    "            \"show\": true," +
                    "                    \"feature\": {" +
                    "                \"mark\": {" +
                    "                    \"show\": true" +
                    "                }," +
                    "                \"dataView\": {" +
                    "                    \"show\": true," +
                    "                            \"readOnly\": false" +
                    "                }," +
                    "                \"magicType\": {" +
                    "                    \"show\": true," +
                    "                            \"type\": [" +
                    "                    \"line\"," +
                    "                            \"bar\"" +
                    "                    ]" +
                    "                }," +
                    "                \"restore\": {" +
                    "                    \"show\": true" +
                    "                }," +
                    "                \"saveAsImage\": {" +
                    "                    \"show\": true" +
                    "                }" +
                    "            }" +
                    "        }," +
                    "            \"calculable\": true," +
                    "                \"xAxis\": [" +
                    "            {" +
                    "                \"type\": \"category\"," +
                    "                    \"data\": ";
            head5 = "   " +
                    "            }" +
                    "            ]," +
                    "            \"yAxis\": [" +
                    "            {" +
                    "                \"type\": \"value\"," +
                    "                    \"splitArea\": {" +
                    "                \"show\": true" +
                    "            }" +
                    "            }" +
                    "            ]," +
                    "            \"series\": [";

            buttom1 = " }] }";

            jsonData += head1;

            jsonArrayXM.add(kjnd+"服务到期个数");//对应对比项目
//            jsonArrayXM.add("已收款");//对应对比项目

            jsonData += jsonArrayXM; //对比项
            jsonData += head3;

            jsonArrayY.add("5月");//Y轴项目；
            jsonArrayY.add("6月");//Y轴项目；
            jsonArrayY.add("7月");//Y轴项目；
            jsonArrayY.add("8月");//Y轴项目；
            jsonArrayY.add("9月");//Y轴项目；
            jsonArrayY.add("10月");//Y轴项目；
            jsonArrayY.add("11月");//Y轴项目；
            jsonArrayY.add("12月");//Y轴项目；
            jsonArrayY.add("1月");//Y轴项目；
            jsonArrayY.add("2月");//Y轴项目；
            jsonArrayY.add("3月");//Y轴项目；
            jsonArrayY.add("4月");//Y轴项目；

            jsonData += jsonArrayY; //Y轴项目
            jsonData += head5;
            head6 = "{" +
                    "                \"name\": \"" + jsonArrayXM.getString(0) + "\"," +
                    "                    \"type\": \"bar\"," +
                    "                    \"data\":";
            jsonData += head6;
//            jsonArrayData1.add(1);//内容1
//            jsonArrayData1.add(2);//内容1
//            jsonArrayData1.add(3);//内容1
//            jsonArrayData1.add(4);//内容1
//            jsonArrayData1.add(5);//内容1
//            jsonArrayData1.add(6);//内容1
//            jsonArrayData1.add(7);//内容1
//            jsonArrayData1.add(8);//内容1
//            jsonArrayData1.add(9);//内容1
//            jsonArrayData1.add(10);//内容1
//            jsonArrayData1.add(11);//内容1
//            jsonArrayData1.add(12);//内容1
            jsonData += this.jsonArray; //项目1
            head8 = "}," +
                    "            {" +
                    "                \"name\": \"" + jsonArrayXM.getString(0) + "\"," +
                    "                    \"type\": \"bar\"," +
                    "                    \"data\": ";
//            jsonData += head8;
            jsonArrayData2.add(10);//内容2
            jsonArrayData2.add(11);//内容2
            jsonArrayData2.add(12);//内容2
            jsonArrayData2.add(13);//内容2
            jsonArrayData2.add(14);//内容2
            jsonArrayData2.add(15);//内容2
            jsonArrayData2.add(16);//内容2
            jsonArrayData2.add(17);//内容2
            jsonArrayData2.add(18);//内容2
            jsonArrayData2.add(19);//内容2
            jsonArrayData2.add(20);//内容2
            jsonArrayData2.add(21);//内容2
            jsonArrayData2.add(22);//内容2
//            jsonData += jsonArrayData2;//项目2
            jsonData += buttom1;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session) {
                session.close();
            }
            System.out.print(jsonData);
            response.getWriter().print(jsonData);
        }
    }
}

