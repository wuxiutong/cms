package wxt.business.servlet.validateTools;

import net.sf.json.JSONObject;
import wxt.dao.QueryData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2015/3/18.
 */
public class CheckExits extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/x-json");
        JSONObject json = new JSONObject();
        try {
        String filedName = request.getParameter("filedName").toString();//获取字段名称
        String filedValue = request.getParameter("filedValue").toString();//获取科目名称
        String entity = request.getParameter("entity").toString(); //获取类名
            List list = QueryData.getSomeEntityLike(entity, filedValue, filedName);
         //   System.out.println("------------------------"+filedName+".value:"+filedValue+"--------------------------");
            if (0 >= list.size() && null!=list.iterator() && list.iterator().hasNext()) {
                json.put("statusCode","200");
                json.put("message","数据库中不存在名称为'"+filedValue+"'的单位信息！");
            } else {
                json.put("statusCode","300");
                json.put("message","数据库中已经存在名称为'"+filedValue+"'的单位信息！");
            }
        } catch (Exception e) {
            json.put("statusCode","300");
            json.put("message","检测数据库中是否存在当前单位名称是出错！");
            e.printStackTrace();
        }
        finally {
            response.getWriter().print(json.toString());
        //    System.out.println("------------------------检验是否存在"+json.get("statusCode")+"！！！！！--------------------------");
        }

}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
        }
