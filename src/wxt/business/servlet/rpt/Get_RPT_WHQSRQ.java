package wxt.business.servlet.rpt;

import net.sf.json.JSONObject;
import wxt.dao.QueryData;
import wxt.model.CMS_RPT_WHRQ;
import wxt.utils.DMMCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuxiutong on 2015/9/14.
 */
public class Get_RPT_WHQSRQ extends HttpServlet {
    //获取单位维护日期的起始日期
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String khdm = request.getParameter("khdmmc");
          khdm = DMMCUtils.getDM(khdm,"[");
        String jzrq = "1900-01-01";
        try{
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            List list = QueryData.getSomeEntity("CMS_RPT_WHRQ", khdm, "khdm");
            if(list !=null &&  list.iterator() != null && list.iterator().hasNext()){
                Iterator it = list.iterator();
                CMS_RPT_WHRQ whrq = (CMS_RPT_WHRQ)it.next();
                if(whrq.getJzrq() != null && !"".equals(whrq.getJzrq())){
                    jzrq = whrq.getJzrq();
                    json.put("statusCode","200");
                    json.put("message","isok");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            json.put("jzrq",jzrq);
            PrintWriter out = response.getWriter();
            out.print(json);
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

}
