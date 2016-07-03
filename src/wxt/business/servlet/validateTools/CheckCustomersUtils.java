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
public class CheckCustomersUtils {
    //检测当前用户是否被使用！
    public static boolean checkCustomersToBeUsed(String khdm) {
        List listCwBills = QueryData.getSomeEntity("CWBills",khdm,"khdm");
        if(listCwBills.isEmpty()){
            return false;
        }else {
            return true;
        }
    }

}
