package wxt.business.servlet.rpt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import wxt.dao.QueryData;
import wxt.model.*;
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
public class Get_RPT_YKPKHXX extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONArray json = new JSONArray();
        Vector vector = new Vector();
        String keywordStr="";
        try {  //获取所有地区信息
            StringBuffer sb = new StringBuffer();
            String djlx = request.getParameter("djlx");
            String dqxx = request.getParameter("dqxx");
            String khjl = request.getParameter("khjl");
            String qsrq = request.getParameter("qsrq");
            String jzrq = request.getParameter("jzrq");
            String keyword = request.getParameter("keyword");
            dqxx = dqxx.replaceAll(" ","");
            khjl = khjl.replaceAll(" ","");
            keyword = keyword.replaceAll(" ","");
            String conditionStr="";//条件
            //获取地区信息
            if(dqxx.length()>0){
                dqxx = dqxx.replaceAll(",","','");
                dqxx =" ssdq in ('"+dqxx+"')";
                conditionStr = " where "+dqxx;
            }
            //获取客户经理
            if(khjl.length()>0){
                khjl = khjl.replaceAll(",","','");
                khjl = "khjl in ('"+khjl+"')";
                if(conditionStr.length()>0){
                    conditionStr+= " and "+khjl;
                }else{
                    conditionStr = " where  "+khjl;
                }
            }
            if (keyword.length() > 0) {
                for(int i = 0 ;i<keyword.length();i++) {
                    keywordStr += "%" + keyword.charAt(i) ;
                }
                keywordStr = " khmc like '" +keywordStr+"%'";
                if(conditionStr.length()>0){
                    conditionStr+=" and  "+keywordStr;
                }else{
                    conditionStr =" where "+keywordStr;
                }

                System.out.println("获取到的客户信息关键字是："+keywordStr);
            }
            //截取客户代码
            List allKhxx = QueryData.getSomeEntityHQL("from Khxx "+conditionStr);
            //如果返回的结果集不为空则进行数据收集工作
            if (null != allKhxx) {
                Iterator itKhxx = allKhxx.iterator();
                vector.clear();
                while (itKhxx.hasNext()) {
                    JSONObject jsonObj = new JSONObject();
                    Khxx khxx = (Khxx) itKhxx.next();
                    //如果传递过来的状态不存在则直接返回所有单据
                    int countDJ = 0;
                    Double sumJe = 0.00;
                    //查询单位下面的财务开票单据
                    List listCWbill = QueryData.getSomeEntity("CWBills", khxx.getKhdm(), "khdm");
                    if (null != listCWbill && null != listCWbill.iterator() && listCWbill.iterator().hasNext()) {
                        Iterator itCWBill = listCWbill.iterator();
                        while (itCWBill.hasNext()) {
                            CWBills cwbill = (CWBills) itCWBill.next();
                            if (djlx != null && !djlx.equals("") && !cwbill.getDjlx().equals(djlx)) {
                                continue;
                            }
                            if (qsrq != null && !qsrq.equals("") && cwbill.getKprq().compareTo(qsrq) < 0) {
                                continue;
                            }
                            if (jzrq != null && !jzrq.equals("") && cwbill.getKprq().compareTo(jzrq) > 0) {
                                continue;
                            }
                            sumJe += cwbill.getJe();
                            countDJ += 1;
                        }
                    }
                    JSONObject jsonBean = new JSONObject();
                    List listDwlx = QueryData.getSomeEntity("Dwlx", khxx.getKhlx(), "lxdm");
                    if(null == listDwlx || null == listDwlx.iterator() || !listDwlx.iterator().hasNext()){
                        jsonBean.put("khlx","获取客户类型失败！");
                    }else{
                        Dwlx dwlx = (Dwlx)listDwlx.iterator().next();
                        if(dwlx == null){
                            jsonBean.put("khlx","获取客户类型失败！");
                        }else{
                            jsonBean.put("khlx", khxx.getKhlx()+"["+dwlx.getLxmc()+"]");
                        }
                    };
                    List listDqxx = QueryData.getSomeEntity("Dqxx", khxx.getSsdq(), "dqdm");
                    if(null == listDqxx || null == listDqxx.iterator() || !listDqxx.iterator().hasNext()){
                        jsonBean.put("ssdq","获取所属地区失败！");
                    }else{
                        Dqxx dqxx1 = (Dqxx)listDqxx.iterator().next();
                        if(dqxx == null){
                            jsonBean.put("ssdq","获取客户类型失败！");
                        }else{
                            jsonBean.put("ssdq", khxx.getSsdq()+"["+dqxx1.getDqmc()+"]");
                        };
                    }
                    List listkhjl = QueryData.getSomeEntity("Zyxx", khxx.getKhjl(), "zydm");
                    if(null == listkhjl || null == listkhjl.iterator() || !listkhjl.iterator().hasNext()){
                        jsonBean.put("khjl","获取所属地区失败！");
                    }else{
                        Zyxx zyxx = (Zyxx)listkhjl.iterator().next();
                        if(zyxx == null){
                            jsonBean.put("khjl","获取客户类型失败！");
                        }else{
                            jsonBean.put("khjl",khxx.getKhjl()+"["+zyxx.getZyxm()+"]");
                        };
                    };
                    jsonBean.put("khdmmc", khxx.getKhdm() + "[" + khxx.getKhmc() + "]");
                    jsonBean.put("kpsl", countDJ);
                    jsonBean.put("je", sumJe);
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
        this.doPost(request, response);
    }
}
