package wxt.utils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import wxt.exceptions.MyException;
import wxt.model.*;

/**
 * Created by admin on 2015/3/21.
 */
public class ValidateUtils {
    public static boolean isLevOk(String dm, String fjlx) throws MyException { //如果符合分级方案则返回TRUE
        boolean isok = true;
        //分级方案
        String fjfa = FjgzUtils.getFjfa(fjlx);
        //分级方案
        char[] fjfa1 = fjfa.replaceAll("-", "").toCharArray();
        //地区代码的长度
        int tempLen = 0;
        //如果分级方案大于的错误则直接抛出异常
        if (fjfa1.length <= 0) {
            isok = false;
            throw new MyException("分级方案：" + fjfa + "不正确，请纠正！");
        } else {
            for (int i = 0; i <= fjfa1.length; i++) {
                //如果地区代码的长度能递减到0则代表该代码分级方案正确
                if (tempLen == dm.length()) {
                    break;
                } else if ((tempLen < dm.length()) && (i < fjfa1.length)) { //如果分级方案长度还大于当时递加的tempLen，则继续
                    tempLen = tempLen + Integer.valueOf(String.valueOf(fjfa1[i]));
                } else { //如果tempLen长度大于代码长度则表示该代码不合法
                    isok = false;
                    throw new MyException("该代码（" + dm + "）不符合编码分级方案:" + fjfa);
                }
            }
        }
        return isok;
    }

    //2、检测数据库中是否存在该地区代码的上级 如果返回null则代表无上级，返回zoot则表示是第一级
    public static String hasParent(String className, String dm, String fjlx) throws MyException {

        Session session = null;
        try {
            Class clazz = null;
            String dmFiled = null;
            //返回的字符
            String parentDm = null;
            //地区代码长度
            int dmlen = dm.length();
            //分级方案
            String fjfa = FjgzUtils.getFjfa(fjlx);
            //分级方案的数组
            char[] fjfa1 = fjfa.replaceAll("-", "").toCharArray();

            //如果符合节点分级要求
            if (isLevOk(dm, fjlx)) {
                //返回如果检测到时一级代码则返回一级标志zoot
                if (Integer.parseInt(fjfa1[0] + "") == dm.length()) {
                    return "zoot";
                }
                //如果不是一级节点则执行下面的查询节点数据！
                else {
                    int tempLen = 0;
                    for (int i = 0; tempLen < dmlen; i++) {
                        if (tempLen + Integer.valueOf(String.valueOf(fjfa1[i])) == dmlen) {
                            break;
                        } else {
                            tempLen = tempLen + Integer.valueOf(String.valueOf(fjfa1[i]));
                        }
                    }
                    //上级代码
                    parentDm = dm.substring(0, tempLen);
                    //判断传递过来的类是哪个类
                    if (className.equals(Dqxx.class.getSimpleName())) {
                        clazz = Dqxx.class;
                        dmFiled = "dqdm";
                    } else if (className.equals(Bmxx.class.getSimpleName())) {
                        clazz = Bmxx.class;
                        dmFiled = "bmdm";
                    } else if (className.equals(Khxx.class.getSimpleName())) {
                        clazz = Khxx.class;
                        dmFiled = "khdm";
                    } else if (className.equals(Zyxx.class.getSimpleName())) {
                        clazz = Zyxx.class;
                        dmFiled = "zydm";
                    } else if (className.equals(Dwlx.class.getSimpleName())) {
                        clazz = Dwlx.class;
                        dmFiled = "lxdm";
                    }
                     session = HibernateUtils.getSession();
                    Criteria ct = session.createCriteria(clazz);
                    ct.add(Restrictions.eq(dmFiled, parentDm));
                    if (0 != ct.list().size()) {
                        return parentDm;
                    } else   //如果没有在数据库中查找到上级代码则跑出异常提示上级代码不存在！
                        throw new MyException("上级代码不存在！");
                }
            }
            return parentDm;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(null != session ){
                session.close();
            }
        }
    }

    //检查该代码在数据库中是否存在 如果存在返回TRUE 如果不存在返回false
    public static boolean checkDMExist(String className, String dm) {
        try {
            Class clazz = null;
            String dmFiled = null;
            //返回的字符
            Session session = HibernateUtils.getSession();
            //Transaction transaction = session.beginTransaction();
            // 判断传递过来的类是哪个类
            if (className.equals(Dqxx.class.getSimpleName())) {
                clazz = Dqxx.class;
                dmFiled = "dqdm";
            } else if (className.equals(Bmxx.class.getSimpleName())) {
                clazz = Bmxx.class;
                dmFiled = "bmdm";
            } else if (className.equals(Khxx.class.getSimpleName())) {
                clazz = Khxx.class;
                dmFiled = "khdm";
            } else if (className.equals(Zyxx.class.getSimpleName())) {
                clazz = Zyxx.class;
                dmFiled = "zydm";
            } else if (className.equals(Khxx_lxr.class.getSimpleName())) {
                clazz = Khxx_lxr.class;
                dmFiled = "lxrbh";
            } else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
                dmFiled = "gysdm";
            } else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
                dmFiled = "verDm";
            } else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
                dmFiled = "modelDm";
            } else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
                dmFiled = "gsdm";
            } else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
                dmFiled = "lxdm";
            } else if (className.equals(Khxx_soft.class.getSimpleName())) {
                clazz = Khxx_soft.class;
                dmFiled = "softID";
            } else if (className.equals(User.class.getSimpleName())) {
                clazz = User.class;
                dmFiled = "userID";
            }
            Criteria ct = session.createCriteria(clazz);
            ct.add(Restrictions.eq(dmFiled, dm));
            int lsize = ct.list().size();
            if (lsize > 0) {
                session.clear();
                return true;
            } else {
                session.clear();
                return false;
            }
        } catch (Exception e) {
            System.out.println("ValidateUtils_checkDMExite 报错！");
            e.printStackTrace();
            return false;
        }
    }

    ;

}
