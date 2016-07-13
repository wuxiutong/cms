package wxt.utils;

import org.hibernate.Session;
import wxt.dao.QueryData;

import java.util.List;

/**
 * Created by admin on 2015/3/21.
 */
public class CheckUtils {

    //检查公司代码是否已经被使用
    public static boolean checkEnterpriseHasBeenUsed(String gsdm) {
        Session session = null;
        Boolean exist = false;
        try {
            //1、检测财务单据是否使用了公司代码
            List CWBillList = QueryData.getSomeEntity("CWBills", gsdm, "gsdm");
            //1、检测客户信息是否使用了公司代码
            List KhxxList = QueryData.getSomeEntity("Khxx", gsdm, "gsdm");
            //如果如果存在数据则返回true,否则返回false
            if ((null!=CWBillList && !CWBillList.isEmpty())  ||   ( null!=KhxxList  &&  !KhxxList.isEmpty())) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist = true;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查地区信息是否已经被使用
    public static boolean checkRegionHasBeenUsed(String dqdm) {
        Session session = null;
        Boolean exist = false;
        try {
            //1、检测客户是否使用了地区信息
            List CWBillList = QueryData.getSomeEntityLike("Khxx", dqdm, "ssdq");
            //如果如果存在数据则返回true,否则返回false
            if (!CWBillList.isEmpty()) {
                 exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist = true;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查客户类型是否已经被使用
    public static boolean checkDwlxHasBeenUsed(String lxdm) {
        Session session = null;
        Boolean exist = false;
        try {
            //1、检测客户是否使用了客户类型
            List khxxList = QueryData.getSomeEntityLike("Khxx", lxdm, "khlx");
            //如果如果存在数据则返回true,否则返回false
            if (!khxxList.isEmpty()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist = true;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist ;
        }
    }

    //检查职员信息是否已经被使用
    public static boolean checkZyxxHasBeenUsed(String zydm) {
        Session session = null;
        Boolean exist = false;
        try {
            //1、检测客户是否使用了客户类型
            List khxxList = QueryData.getSomeEntityLike("Khxx", zydm, "khjl");
            List bmxxList = QueryData.getSomeEntityLike("Bmxx", zydm, "fzr");
            //如果如果存在数据则返回true,否则返回false
            if (!khxxList.isEmpty() || !bmxxList.isEmpty()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist = true;
    } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查部门信息是否已经被使用
    public static boolean checkBmxxHasBeenUsed(String bmdm) {
        Session session = null;
        Boolean exist = false;
        try {
            //1、检测职员信息是否使用了部门类型
            List bmxxList = QueryData.getSomeEntityLike("Zyxx", bmdm, "ssbm");
            //如果如果存在数据则返回true,否则返回false
            if (!bmxxList.isEmpty()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
           exist = true;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查供应商信息是否已经被使用
    public static boolean checkGysHasBeenUsed(String gysdm) {
        Session session = null;
        boolean exist = false;
        try {
            //1、检测供应商是否被版本信息使用
            List verList = QueryData.getSomeEntity("SoftVer", gysdm, "gysDm");
            //2、检测供应商是否被模块信息使用
            List modelList = QueryData.getSomeEntity("SoftModel", gysdm, "gysDm");
            //如果如果存在数据则返回true,否则返回false
            if (!verList.isEmpty() || !modelList.isEmpty()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist = false;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查版本信息是否已经被使用
    public static boolean checkVerHasBeenUsed(String verDm) {
        Session session = null;
        boolean exist = false;
        try {
            //1、检测版本是否被模块信息使用
            List modelList = QueryData.getSomeEntity("SoftModel", verDm, "verDm");
            //如果如果存在数据则返回true,否则返回false
            if (!modelList.isEmpty()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist =  false;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查版本信息是否已经被使用
    public static boolean checkModelHasBeenUsed(String modelDm) {
        Session session = null;
        boolean exist = false;
        try {
            //1、检测版本是否被模块信息使用
            List khxxList = QueryData.getSomeEntity("Khxx_soft", modelDm, "modelDm");
            //如果如果存在数据则返回true,否则返回false
            if (!khxxList.isEmpty()) {
                exist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist =  false;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }

    //检查客户信息是否已经被使用
    public static boolean checkKhxxHasBeenUsed(String khdm) {
        Session session = null;
        boolean exist = false;
        try {
            //1、检测是否被模块信息使用
            List cwbillList = QueryData.getSomeEntity("CWBills", khdm, "khdm");
            //如果如果存在数据则返回true,否则返回false
            if (!cwbillList.isEmpty()) {
               exist  = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exist =  false;
        } finally {
            if (null != session) {
                session.close();
            }
            return exist;
        }
    }
}

