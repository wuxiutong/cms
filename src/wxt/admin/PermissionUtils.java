package wxt.admin;

import org.junit.Test;
import wxt.dao.QueryData;
import wxt.exceptions.CustomException;
import wxt.model.User;

import java.util.Iterator;
import java.util.List;

/**
 * Created by wuxiutong on 2015/9/1.
 */
public class PermissionUtils {
    public static boolean checkPermission(String userID, String gnxm) throws CustomException{
        try {
            // Session session = HibernateUtils.getSession();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null == list || null == list.iterator() || !list.iterator().hasNext()) {
                throw new CustomException("未查找到当前传递的userID");
            }
            //根据用户代码获取所属角色
            User user = (User) list.iterator().next();
            String ssrole = user.getSsrole();
            if (null == ssrole || "".equals(ssrole.trim())) {
                throw new CustomException("当前用户无任何权限");
            }

            List roleList = QueryData.getSomeEntity("Role", userID, "id");
            //根据功能项目获取得到功能代码
            List gndmList = QueryData.getSomeEntityHQL("Select gndm from Authorization_items where gnxm like '%" + gnxm + "%'");
            if (null == gndmList || null == gndmList.iterator() || !gndmList.iterator().hasNext()) {
                throw new CustomException("当前请求的功能\""+gnxm+"\"未实现");
            }
            String gndm = (String) gndmList.iterator().next();
            //通过功能代码获取类角色代码
            List listRoles1 = QueryData.getSomeEntityHQL("Select id from Role where qx like '%" + gndm + ",%'");
            if (listRoles1 == null || listRoles1.isEmpty()) {
                throw new CustomException("当前请求的功能未授权给任何角色！");
            }

            String[] ssroles = ssrole.split(",");
            for (int i = 0; i < ssroles.length; i++) {

//                System.out.println("i:"+i+",value:" +ssroles[i]);
                if (null == ssroles[i] || "".equals(ssroles[i].trim())) {
                    continue;
                } else {
                    Iterator it = listRoles1.iterator();
                    while (it.hasNext()) {
                        String roleStr = (String) it.next();
//                        System.out.println(roleStr + ":_______"+ssroles.length+"_______:" + ssroles[i]);
                        if (roleStr != null && ssroles[i].trim().equals(roleStr.trim())) {
                            return true;
                        }
                    }
                }
            }
            return false;

        } catch (CustomException e) {
            throw new CustomException(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Test
    public void demo() {
        try{
            System.out.println(checkPermission("2", "RoleBaseServlet.getAll"));
        }catch (CustomException e){
            System.out.println(e.getMessage());
        }

    }
}
