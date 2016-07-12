package wxt.business.actions;

import net.sf.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import wxt.dao.QueryData;
import wxt.model.Gys;
import wxt.model.SoftModel;
import wxt.model.SoftVer;
import wxt.utils.CheckUtils;
import wxt.utils.HibernateUtils;
import wxt.utils.ValidateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuxiutong on 15/8/31.
 */
public class Action_SoftModel {
    //增加一个软件模块
    public void add(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_ver = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                if (name.startsWith("model.")) {
                    js_ver.put(name.substring("model.".length()), request.getParameter(name));
                } else {
                    js_ver.put(name, request.getParameter(name));
                }
                System.out.println("name:" + name + ";value:" + request.getParameter(name));
            }
            SoftModel model = (SoftModel) JSONObject.toBean(js_ver, SoftModel.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if (ValidateUtils.checkDMExist("SoftModel", model.getModelDm())) {
                //  json.fromObject(request);
                System.out.println("代码已经存在！");
                json.put("statusCode", "300");
                json.put("message", "代码已经存在！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
                //如果该代码在数据库中存在上级！则可以增加
            } else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.save(model);
                transaction.commit();
                json.put("modelDm", model.getModelDm());
                json.put("modelMc", model.getModelMc());
                json.put("verDm", model.getVerDm());
                //  json.fromObject(request);
                json.put("statusCode", "200");
                json.put("message", "增加成功！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            }
        } catch (Exception e122) {
            e122.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", e122.toString());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        } finally {
            if (null != session ) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
            System.out.println("运行到最后");
        }
    }

    //修改一个软件模块
    public void alter(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Enumeration<String> enum1 = request.getParameterNames();
        JSONObject js_ver = new JSONObject();
        JSONObject json = new JSONObject();
        Session session = null;
        Transaction transaction = null;
        try {
            while (enum1.hasMoreElements()) {
                String name = enum1.nextElement();
                if (name.startsWith("model.")) {
                    js_ver.put(name.substring("model.".length()), request.getParameter(name));
                } else {
                    js_ver.put(name, request.getParameter(name));
                }
                System.out.println("name:" + name + ";value:" + request.getParameter(name));
            }
            SoftModel model = (SoftModel) JSONObject.toBean(js_ver, SoftModel.class);
            //检查代码是否有上级，是否符合编码规则，是否已经存在！
            if (!ValidateUtils.checkDMExist("SoftModel", model.getModelDm())) {
                //  json.fromObject(request);
                System.out.println("代码已经存在！");
                json.put("statusCode", "300");
                json.put("message", "代码不存在！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
                //如果该代码在数据库中存在上级！则可以增加
            } else {
                session = HibernateUtils.getSession();
                transaction = session.beginTransaction();
                session.update(model);
                transaction.commit();
                json.put("modeldm", model.getModelDm());
                json.put("modelmc", model.getModelMc());
                json.put("verdm", model.getVerDm());
                //  json.fromObject(request);
                json.put("statusCode", "200");
                json.put("message", "修改成功！");
                json.put("navTabId", "");
                json.put("rel", "");
                json.put("callbackType", "");
                json.put("forwardUrl", "");
            }
        } catch (Exception e122) {
            e122.printStackTrace();
            json.put("statusCode", "300");
            json.put("message", e122.toString());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
            PrintWriter out = response.getWriter();
            out.print(json);
        } finally {
            if (null != session ) {
                session.close();
            }
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }

    //删除一个软件模块
    public void del(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        Session session = null;
        PrintWriter pw = response.getWriter();
        JSONObject json = new JSONObject();
        try {
            String modeldm = request.getParameter("modeldm").toString();
            if (CheckUtils.checkModelHasBeenUsed(modeldm)) {
                throw new Exception("当前软件模块信息已经被使用，无法删除！");
            }
            session = HibernateUtils.getSession();
            Transaction transaction = session.beginTransaction();
            String hqlDel = "delete SoftModel d  where d.modelDm = :modeldm";
// or String hqlUpdate = "update Customer set name = :newName where name = :oldName";
            Query del = session.createQuery(hqlDel);
            del.setString("modeldm", modeldm);
            int result = del.executeUpdate();
            transaction.commit();
            json.put("result", result);
            json.put("statusCode", "200");
            json.put("message", "成功 " + result + "条记录！");
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("result", "");
            json.put("statusCode", "300");
            json.put("message", e.getMessage());
            json.put("navTabId", "");
            json.put("rel", "");
            json.put("callbackType", "");
            json.put("forwardUrl", "");
        } finally {
            if (null != session  ) {
                session.close();
            }
            pw.print(json);
        }
    }

    //获取所有供应商 版本 和模块
    public void getAllGysVerModelForTree(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        Iterator itGys = null;
        ;
        try {  //获取所有供应商信息
            StringBuffer sb = new StringBuffer();
            List allGys = QueryData.getAllEntity("Gys");
            if (null != allGys) {
                itGys = allGys.iterator();
                //给添加
                sb.append("[");
                while (itGys.hasNext()) {
                    Gys gys = (Gys) itGys.next();
                    if (1 == 1) { //符合编码规则的才执行
                        sb.append("{\"id\":\"gys_" + gys.getGysdm() + "\",\"name\":\"" + gys.getGysdm() + "-" + gys.getGysmc() + "\",\"pId\":\"0\"},");
                        List allVer = QueryData.getSomeEntity("SoftVer", gys.getGysdm(), "gysDm");
                        if (null != allVer) {
                            Iterator itver = allVer.iterator();
                            while (itver.hasNext()) {
                                SoftVer ver = (SoftVer) itver.next();
                                sb.append("{\"id\":\"ver_" + ver.getVerDm() + "\",\"name\":\"" + ver.getVerDm() + "-" + ver.getVerMc() + "\",\"pId\":\"" + "gys_" + ver.getGysDm() + "\"},");
                                List listModel = QueryData.getSomeEntity("SoftModel", ver.getVerDm(), "verDm");
                                if (null != listModel) {
                                    Iterator itModel = listModel.iterator();
                                    while (null != itModel && itModel.hasNext()) {
                                        SoftModel model = (SoftModel) itModel.next();
                                        sb.append("{\"id\":\"model_" + model.getModelDm() + "\",\"name\":\"" + model.getModelDm() + "-" + model.getModelMc() + "\",\"pId\":\"" + "ver_" + model.getVerDm() + "\"},");
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println(sb.toString().substring(0, sb.length() - 1) + "]");
                response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取某个模块包含版本和供应返回jsonArray格式
    public void getOneGysVerModelForJsonArrayByModel(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        List modelList = null;
        Iterator it = null;
        StringBuffer sb = new StringBuffer();
        String modelDm = request.getParameter("modelDm");
        try {
            modelList = QueryData.getSomeEntity("SoftModel", modelDm, "modelDm");
            it = modelList.iterator();
            sb.append("[");
            while (it.hasNext()) {
                SoftModel model = (SoftModel) it.next();
                List listVer = QueryData.getSomeEntity("SoftVer", model.getVerDm(), "verDm");
                String verMc = ((SoftVer) listVer.iterator().next()).getVerMc();
                List listGys = QueryData.getSomeEntity("Gys", model.getGysDm(), "gysdm");
                String gysmc = ((Gys) listGys.iterator().next()).getGysmc();
                sb.append("{\"gysDm\":\"" + model.getGysDm() + "\",\"gysMc\":\"" + gysmc +
                        "\",\"verDm\":\"" + model.getVerDm() + "\",\"verMc\":\"" + verMc +
                        "\",\"modelDm\":\"" + model.getModelDm() + "\",\"modelMc\":\"" + model.getModelMc() + "\"},");
            }
            response.getWriter().print(sb.toString().substring(0, sb.length() - 1) + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取一个模块的供应商和版本返回forjson
    public void getOneGysVerModeForJsonByModel(Object requestObj, Object responseObj) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)requestObj;
        HttpServletResponse response = (HttpServletResponse)responseObj;response.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        String modelDm = request.getParameter("modelDm").toString();
        List list = QueryData.getEntity("SoftModel", modelDm);
        System.out.println("传回：" + modelDm + "版本信息！");
        try {
            if (0 >= list.size()) {
                System.out.println("数据库中不存在代码为：" + modelDm + "的地区信息！");
            } else {
                Iterator it = list.iterator();
                SoftModel model = (SoftModel) it.next();
                json.put("modeldm", model.getModelDm());
                json.put("modelmc", model.getModelMc());
                json.put("gysdm", model.getGysDm());
                List listGys = QueryData.getSomeEntity("Gys", model.getGysDm(), "gysdm");
                if (null != listGys && listGys.iterator().hasNext()) {
                    json.put("gysmc", ((Gys) (listGys.iterator().next())).getGysmc());
                }
                json.put("verdm", model.getVerDm());
                List listVer = QueryData.getEntity("SoftVer", model.getVerDm());
                if (null != listVer && listVer.iterator().hasNext()) {
                    json.put("vermc", ((SoftVer) (listVer.iterator().next())).getVerMc());
                }
                json.put("ps", model.getPs());
                PrintWriter pw = response.getWriter();
                pw.print(json.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter pw = response.getWriter();
            json.put("statuscode", 200);
            pw.print(json.toString());
        }

    }


}
