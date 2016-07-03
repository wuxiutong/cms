package wxt.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import wxt.model.*;
import wxt.utils.HibernateUtils;

import java.util.List;

/**
 * Created by admin on 2015/3/12.
 */
public class QueryData {
    //获取到所有的地区信息列表
    public static List getAllEntity(String className) {
        Session session = null;
        Transaction transaction = null;
        Class clazz = null;
        String dmFiled = null;
        List allEntity = null;
        Criteria criteria = null;
        try {
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
            } else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
                dmFiled = "gysdm";
            }else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
                dmFiled = "verDm";
            }else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
                dmFiled = "modelDm";
            }else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
                dmFiled = "gsdm";
            }else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
                dmFiled = "lxdm";
            }else if (className.equals(CWBills.class.getSimpleName())) {
                clazz = CWBills.class;
                dmFiled = "id";
            }else if (className.equals(CMS_RPT_WHRQ.class.getSimpleName())) {
                clazz = CMS_RPT_WHRQ.class;
                dmFiled = "id";
            }else if (className.equals(Authorization_items.class.getSimpleName())) {
                clazz = Authorization_items.class;
                dmFiled = "gndm";
            }else if (className.equals(Role.class.getSimpleName())) {
                clazz = Role.class;
                dmFiled = "id";
            }else if (className.equals(User.class.getSimpleName())) {
                clazz = User.class;
                dmFiled = "userID";
            }else if (className.equals(CMS_RPT_DWYSK.class.getSimpleName())) {
                clazz = CMS_RPT_DWYSK.class;
                dmFiled = "khdm";
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria(clazz);
            allEntity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return allEntity;
        }
        // System.out.println("获取到的LIST大小为："+allDq.size());
    }

    public static List getEntity(String className, String dm) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
        Class clazz = null;
        String dmFiled = null;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
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
            } else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
                dmFiled = "gysdm";
            }else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
                dmFiled = "verDm";
            }else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
                dmFiled = "modelDm";
            }else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
                dmFiled = "gsdm";
            }else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
                dmFiled = "lxdm";
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq(dmFiled, dm));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!= session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
        }
        //System.out.println("获取到的LIST大小为：" + allDq.size());

    }

    ;

    //f返回特定字段相等的数据
    public static List getSomeEntity(String className, String dm, String field) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
        Class clazz = null;
        String dmFiled = field;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
            // 判断传递过来的类是哪个类
            if (className.equals(Dqxx.class.getSimpleName())) {
                clazz = Dqxx.class;
                // dmFiled = "dqdm";
            } else if (className.equals(Bmxx.class.getSimpleName())) {
                clazz = Bmxx.class;
                //  dmFiled = "bmdm";
            } else if (className.equals(Khxx.class.getSimpleName())) {
                clazz = Khxx.class;
                //  dmFiled = "khdm";
            } else if (className.equals(Zyxx.class.getSimpleName())) {
                clazz = Zyxx.class;
                //  dmFiled = "zydm";
            }else if (className.equals(Khxx_lxr.class.getSimpleName())) {
                clazz = Khxx_lxr.class;
            }else if (className.equals(Khxx_soft.class.getSimpleName())) {
                clazz = Khxx_soft.class;
            }else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
            }else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
            }else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
            }else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
            }else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
            }else if (className.equals(Whdj.class.getSimpleName())) {
                clazz = Whdj.class;
            }else if (className.equals(CMS_RPT_WHRQ.class.getSimpleName())) {
                clazz = CMS_RPT_WHRQ.class;
            }else if (className.equals(CMS_RPT_DWYSK.class.getSimpleName())) {
                clazz = CMS_RPT_DWYSK.class;
            }else if (className.equals(CWBills.class.getSimpleName())) {
                clazz = CWBills.class;
            }else if (className.equals(Role.class.getSimpleName())) {
                clazz = Role.class;
            }else if (className.equals(User.class.getSimpleName())) {
                clazz = User.class;
            }else if (className.equals(Authorization_items.class.getSimpleName())) {
                clazz = Authorization_items.class;
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq(dmFiled, dm));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session!=null) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
            //System.out.println("获取到的LIST大小为：" + allDq.size());
        }
    }
    //f返回特定字段相等的数据
    public static List getSomeEntity(String className, int dm, String field) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
        Class clazz = null;
        String dmFiled = field;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
            // 判断传递过来的类是哪个类
            if (className.equals(Dqxx.class.getSimpleName())) {
                clazz = Dqxx.class;
                // dmFiled = "dqdm";
            } else if (className.equals(Bmxx.class.getSimpleName())) {
                clazz = Bmxx.class;
                //  dmFiled = "bmdm";
            } else if (className.equals(Khxx.class.getSimpleName())) {
                clazz = Khxx.class;
                //  dmFiled = "khdm";
            } else if (className.equals(Zyxx.class.getSimpleName())) {
                clazz = Zyxx.class;
                //  dmFiled = "zydm";
            }else if (className.equals(Khxx_lxr.class.getSimpleName())) {
                clazz = Khxx_lxr.class;
            }else if (className.equals(Khxx_soft.class.getSimpleName())) {
                clazz = Khxx_soft.class;
            }else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
            }else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
            }else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
            }else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
            }else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
            }else if (className.equals(Whdj.class.getSimpleName())) {
                clazz = Whdj.class;
            }else if (className.equals(Tjb_whrq.class.getSimpleName())) {
                clazz = Tjb_whrq.class;
            }else if (className.equals(Cwkpd.class.getSimpleName())) {
                clazz = Cwkpd.class;
            }else if (className.equals(CWBills.class.getSimpleName())) {
                clazz = CWBills.class;
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq(dmFiled, dm));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
            //System.out.println("获取到的LIST大小为：" + allDq.size());
        }
    }

    //f返回特定字段相等的数据,传入的是类class
    public static List getSomeEntityLike(Class clazz, String dmStr, String fieldStr) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
      ///  Class clazz = null;
        String dmFiled = fieldStr;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.like(dmFiled, dmStr, MatchMode.START));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
        }
    }
    //f返回特定字段相等的数据
    public static List getSomeEntityLike(String className, String dm, String field) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
        Class clazz = null;
        String dmFiled = field;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
            // 判断传递过来的类是哪个类
            if (className.equals(Dqxx.class.getSimpleName())) {
                clazz = Dqxx.class;
                // dmFiled = "dqdm";
            } else if (className.equals(Bmxx.class.getSimpleName())) {
                clazz = Bmxx.class;
                //  dmFiled = "bmdm";
            } else if (className.equals(Khxx.class.getSimpleName())) {
                clazz = Khxx.class;
                //  dmFiled = "khdm";
            } else if (className.equals(Zyxx.class.getSimpleName())) {
                clazz = Zyxx.class;
                //  dmFiled = "zydm";
            }else if (className.equals(Khxx_lxr.class.getSimpleName())) {
                clazz = Khxx_lxr.class;
            }else if (className.equals(Khxx_soft.class.getSimpleName())) {
                clazz = Khxx_soft.class;
            }else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
            }else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
            }else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
            }else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
            }else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
            }else if (className.equals(Whdj.class.getSimpleName())) {
                clazz = Whdj.class;
            }else if (className.equals(Cwkpd.class.getSimpleName())) {
                clazz = Cwkpd.class;
            }else if (className.equals(CWBills.class.getSimpleName())) {
                clazz = CWBills.class;
            }else if (className.equals(CMS_RPT_DWYSK.class.getSimpleName())) {
                clazz = CMS_RPT_DWYSK.class;
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.like(dmFiled, dm, MatchMode.START));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
            //System.out.println("获取到的LIST大小为：" + allDq.size());
        }
    }
    //f返回特定字段相等的数据 多个参数,遵循先字段，后字段值的规则！
    public static List getSomeEntityLikeMutlParam(String className, String ...args) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
        Class clazz = null;
        String dmFiled = null;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
            // 判断传递过来的类是哪个类
            if (className.equals(Dqxx.class.getSimpleName())) {
                clazz = Dqxx.class;
                // dmFiled = "dqdm";
            } else if (className.equals(Bmxx.class.getSimpleName())) {
                clazz = Bmxx.class;
                //  dmFiled = "bmdm";
            } else if (className.equals(Khxx.class.getSimpleName())) {
                clazz = Khxx.class;
                //  dmFiled = "khdm";
            } else if (className.equals(Zyxx.class.getSimpleName())) {
                clazz = Zyxx.class;
                //  dmFiled = "zydm";
            }else if (className.equals(Khxx_lxr.class.getSimpleName())) {
                clazz = Khxx_lxr.class;
            }else if (className.equals(Khxx_soft.class.getSimpleName())) {
                clazz = Khxx_soft.class;
            }else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
            }else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
            }else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
            }else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
            }else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
            }else if (className.equals(Whdj.class.getSimpleName())) {
                clazz = Whdj.class;
            }else if (className.equals(Cwkpd.class.getSimpleName())) {
                clazz = Cwkpd.class;
            }
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(clazz);
            for(int index= 0 ;index<args.length; ){
                criteria.add(Restrictions.like(args[index++], args[index++], MatchMode.START));
            }
          //  criteria.add(Restrictions.like(dmFiled, dm));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
            //System.out.println("获取到的LIST大小为：" + allDq.size());
        }
    }
    //f返回特定字段相等的数据 条件为传入的HQL
    public static List getSomeEntityHQL(String HQL) {

        List list= null;
        Session session =null;
        Transaction transaction = null;
        try{
            session = HibernateUtils.getSession();
            Query query = session.createQuery(HQL);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null!=session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return list;
        }
    }

}
