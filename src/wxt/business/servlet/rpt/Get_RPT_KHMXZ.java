package wxt.business.servlet.rpt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wxt.dao.QueryData;
import wxt.model.CWBills;
import wxt.utils.DMMCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by wuxiutong on 15/9/4.
 */
public class Get_RPT_KHMXZ  extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONArray json = new JSONArray();
        Vector vector = new Vector();
        try {  //获取所有地区信息
            StringBuffer sb = new StringBuffer();
            String djzt = request.getParameter("djzt");
            String khdm = request.getParameter("khdm");
            String qsrq = request.getParameter("qsrq");
            String jzrq = request.getParameter("jzrq");
            //截取客户代码
            khdm=DMMCUtils.getDM(khdm,"[");
            List allBills = QueryData.getSomeEntity("CWBills", khdm,"khdm");
            //如果返回的结果集不为空则进行数据收集工作
            if (null != allBills) {
                Iterator itBills = allBills.iterator();
                vector.clear();
                while (itBills.hasNext()) {
                    CWBills cwBills = (CWBills) itBills.next();
                    //如果传递过来的状态不存在则直接返回所有单据
                    if(djzt!=null && !djzt.equals("") && !cwBills.getZt().equals(djzt)){
                        continue;
                    } if(qsrq!=null && !qsrq.equals("") && cwBills.getKprq().compareTo(qsrq)<0){
//                        System.out.println("kprq:"+cwBills.getKprq()+";小于");
                        continue;
                    } if(jzrq!=null && !jzrq.equals("") && cwBills.getKprq().compareTo(jzrq)>0){
//                        System.out.println("kprq:"+cwBills.getKprq()+";大于");
                        continue;
                    }
                   System.out.println(cwBills.getKprq().compareTo(qsrq)+"：kprq:"+cwBills.getKprq()+";小于");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
