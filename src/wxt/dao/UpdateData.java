package wxt.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import wxt.model.*;
import wxt.utils.DMMCUtils;
import wxt.utils.HibernateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2015/3/12.
 */
public class UpdateData {
    //f返回特定字段相等的数据
    public static List updateSomeEntity(String className, String dm, String field) {
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
            } else if (className.equals(Khxx_lxr.class.getSimpleName())) {
                clazz = Khxx_lxr.class;
            } else if (className.equals(Khxx_soft.class.getSimpleName())) {
                clazz = Khxx_soft.class;
            } else if (className.equals(SoftVer.class.getSimpleName())) {
                clazz = SoftVer.class;
            } else if (className.equals(Gys.class.getSimpleName())) {
                clazz = Gys.class;
            } else if (className.equals(SoftModel.class.getSimpleName())) {
                clazz = SoftModel.class;
            } else if (className.equals(Enterprise.class.getSimpleName())) {
                clazz = Enterprise.class;
            } else if (className.equals(Dwlx.class.getSimpleName())) {
                clazz = Dwlx.class;
            } else if (className.equals(Whdj.class.getSimpleName())) {
                clazz = Whdj.class;
            } else if (className.equals(Tjb_whrq.class.getSimpleName())) {
                clazz = Tjb_whrq.class;
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
        }
    }

    //f返回特定字段相等的数据
    public static List updateSomeEntityLike(String className, String dm, String field) {
        //  System.out.println("传递queryData过来的ID为："+dqdm);
        Class clazz = null;
        String dmFiled = field;
        Session session = null;
        Transaction transaction = null;
        List entity = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            //  String HQL = "update Tjb_whrq t set t.jzrq = :jzrq where gsdm  = :gsdm";
            //Query query = session.createQuery(HQL);
            // query.setString("")
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.like(dmFiled, dm));
            entity = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return entity;
            //System.out.println("获取到的LIST大小为：" + allDq.size());
        }
    }//f返回特定字段相等的数据

    public static int updateDateHqlStr(String hql, String... params) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            System.out.println("hsql:" + query.getQueryString());
            result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    public static int updateDateHqlInt(String hql, int... params) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i, params[i]);
            }
            System.out.println("hsql:" + query.getQueryString());
            result = query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //注意这个id处于hql中最后！
    public static int updateDateHqlIntStr(String hql, int lastParamID, String... params) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setInteger(params.length, lastParamID);
            for (int i = 0; i < params.length; i++) {
                query.setString(i, params[i]);
            }
            result = query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //注意这个id数组是INT处于hql中最后！
    public static int updateHqlStr(String hql, String[] lastParamIDs, String... params) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                Query query = session.createQuery(hql);
                query.setParameter(params.length, lastParamIDs[idI]);
                for (int i = 0; i < params.length; i++) {
                    query.setString(i, params[i]);
                }
                result = result + query.executeUpdate();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //注意这个id数组是INT处于hql中最后！
    public static int updateHqlIntArStr(String hql, int[] lastParamIDs, String... params) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                Query query = session.createQuery(hql);
                query.setParameter(params.length, lastParamIDs[idI]);
                for (int i = 0; i < params.length; i++) {
                    query.setString(i, params[i]);
                }
                result = result + query.executeUpdate();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            result = 0;
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //注意这个id数组是INT处于hql中最后！
    public static int deleteHqlIntArStr(String hql, int[] lastParamIDs, String... params) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                Query query = session.createQuery(hql);
                query.setParameter(params.length, lastParamIDs[idI]);
                for (int i = 0; i < params.length; i++) {
                    query.setString(i, params[i]);
                }
                result = result + query.executeUpdate();
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null)
                transaction.rollback();
            result = 0;
            throw new RuntimeException(e.getMessage());
        } finally {
            if (null != session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //审核财务单据！
    public static int CWBills_SH(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    List listCWBills = QueryData.getSomeEntity("CWBills", Integer.valueOf(lastParamIDs[idI]), "id");
                    //如果没有查询到单据则跳过到条信息
                    if (null != listCWBills && listCWBills.iterator().hasNext()) {
                        CWBills cwBills = (CWBills) listCWBills.iterator().next();
                        cwBills.setZt("1");
                        cwBills.setShr(userID);
                        cwBills.setShrxm(userXM);
                        List listKhxx = QueryData.getSomeEntity("Khxx", cwBills.getKhdm(), "khdm");
                        //如果未查询到用户则直接跳过
                        if (null != listKhxx && listKhxx.iterator().hasNext()) {
                            Khxx khxx = (Khxx) listKhxx.iterator().next();
                            //如果更新记录返回信息为-1则代表更新出错！
                            if (cwBills.getDjlx().equals("whd")) {
                                if (CMS_RPT_WHRQ_Update(khxx, true, cwBills.getQsrq(), cwBills.getJzrq(), String.valueOf(cwBills.getId()), userID + "[" + userXM + "]", "") >= 0
                                        && updateDWYSK(khxx.getKhdm(), cwBills.getJe(), cwBills.getKjnd(), "+", session)) {
                                    session.saveOrUpdate(cwBills);
                                    result++;
                                } else {
                                    continue;
                                }
                            } else {
                                session.saveOrUpdate(cwBills);
                                result++;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }

                }
            }
            if (result > 0) {
                if (null != transaction)
                    transaction.commit();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
            result = 0;
        } finally {
            if (null != session) {
                session.close();
            }
            return result;
        }
    }

    //销审财务单据！
    public static int CWBills_XS(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null == transaction || !transaction.isActive())
                    transaction = session.beginTransaction();
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    List listCWBills = QueryData.getSomeEntity("CWBills", Integer.valueOf(lastParamIDs[idI]), "id");
                    //如果没有查询到单据则跳过到条信息
                    if (null != listCWBills && listCWBills.iterator().hasNext()) {
                        CWBills cwBills = (CWBills) listCWBills.iterator().next();
                        cwBills.setZt("0");
                        cwBills.setShr("");
                        cwBills.setShrxm("");
                        List listKhxx = QueryData.getSomeEntity("Khxx", cwBills.getKhdm(), "khdm");
                        //如果未查询到用户则直接跳过
                        if (null != listKhxx && listKhxx.iterator().hasNext()) {
                            Khxx khxx = (Khxx) listKhxx.iterator().next();
                            //如果更新记录返回信息为-1则代表更新出错！
                            if (cwBills.getDjlx().equals("whd")) {
                                if (CMS_RPT_WHRQ_Update(khxx, false, cwBills.getQsrq(), cwBills.getJzrq(), String.valueOf(cwBills.getId()), userID + "[" + userXM + "]", "") >= 0
                                        && updateDWYSK(khxx.getKhdm(), cwBills.getJe(), cwBills.getKjnd(), "-", session)) {
                                    session.saveOrUpdate(cwBills);
                                    result++;
                                } else {
                                    continue;
                                }
                            } else {
                                session.saveOrUpdate(cwBills);
                                result++;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            if (result > 0) {
                if (null != transaction)
                    transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
            result = 0;
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //确认款项到账财务单据！

    public static int CWBills_SK(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            String hsql = "update CWBills set zt = '2',skr='" + userID + "',skrxm = '" + userXM + "' where zt = '1' and  ";
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    switch (idI) {
                        case 0:
                            hsql += " ( id = '" + lastParamIDs[idI] + "'";
                            break;
                        default:
                            hsql += " or id = '" + lastParamIDs[idI] + "'";
                            break;
                    }
                }
            }
            hsql += " )";

            Query query = session.createQuery(hsql);
            result = query.executeUpdate();
            if (result >= 0) {
                if (null != transaction)
                    transaction.commit();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //退款财务单据！
    public static int CWBills_TK(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            String hsql = "update CWBills set zt = '1',skr='',skrxm = '' where zt = '2' and  ";
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    switch (idI) {
                        case 0:
                            hsql += " ( id = '" + lastParamIDs[idI] + "'";
                            break;
                        default:
                            hsql += " or id = '" + lastParamIDs[idI] + "'";
                            break;
                    }
                }
            }
            hsql += " )";

            Query query = session.createQuery(hsql);
            result = query.executeUpdate();
            if (result >= 0) {
                if (null != transaction)
                    transaction.commit();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //作废财务单据！
    public static int CWBills_ZF(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            String hsql = "update CWBills set zt = '-1'  where zt = '0' and  ";
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    switch (idI) {
                        case 0:
                            hsql += " ( id = '" + lastParamIDs[idI] + "'";
                            break;
                        default:
                            hsql += " or id = '" + lastParamIDs[idI] + "'";
                            break;
                    }
                }
            }
            hsql += " )";

            Query query = session.createQuery(hsql);
            result = query.executeUpdate();
            if (result >= 0) {
                if (null != transaction)
                    transaction.commit();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //恢复财务单据！
    public static int CWBills_HF(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            String hsql = "update CWBills set zt = '0'  where zt = '-1' and  ";
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    switch (idI) {
                        case 0:
                            hsql += " ( id = '" + lastParamIDs[idI] + "'";
                            break;
                        default:
                            hsql += " or id = '" + lastParamIDs[idI] + "'";
                            break;
                    }
                }
            }
            hsql += " )";

            Query query = session.createQuery(hsql);
            result = query.executeUpdate();
            if (result >= 0) {
                if (null != transaction)
                    transaction.commit();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //删除财务单据！
    public static int CWBills_SC(String[] lastParamIDs, String userID) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("User", userID, "userID");
            if (null != list && list.iterator().hasNext()) {
                userXM = ((User) list.iterator().next()).getUserName();  //获取操作员姓名
            } else {
                return -789;//如果当前库里面不存在传递过来的用户则返回-789
            }
            String hsql = "delete CWBills   where zt = '-1' and  ( ";
            for (int idI = 0; idI < lastParamIDs.length; idI++) { //循环读取IDS
                if (null != lastParamIDs[idI] && !lastParamIDs[idI].trim().equalsIgnoreCase("")) {
                    switch (idI) {
                        case 0:
                            hsql += " id = '" + lastParamIDs[idI] + "'";
                            break;
                        default:
                            hsql += " or id = '" + lastParamIDs[idI] + "'";
                            break;
                    }
                }
            }
            hsql += " )";

            Query query = session.createQuery(hsql);
            result = query.executeUpdate();
            if (result >= 0) {
                if (null != transaction)
                    transaction.commit();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
        } finally {
            if (null != session ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //更新客户维护日期
    public static int CMS_RPT_WHRQ_Update(Khxx khxx, boolean updateFlag, String qsrq, String jzrq, String updateDjh, String updateUserDMMC, String lastUpdate) {
        /*
        规则：1、如果检查传递过来的截至日期缺失则不更新，直接返回0.
            2、如果报错错误将返回-1
            3、如果没有任何的错误则直接反返回成功标志1
         */
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        String whjzrq = null;
        String userXM = "";
        String khdm = null;
        String khmc = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        CMS_RPT_WHRQ whrq = null;

        try {
            khdm = khxx.getKhdm();
            khmc = khxx.getKhmc();
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            List list = QueryData.getSomeEntity("CMS_RPT_WHRQ", khdm, "khdm");
            //如果返回的khdm在维护日期统计表格中不存在则直接插入操作 ，否则执行更行操作
            if (null == list || !list.iterator().hasNext()) {
                whrq = new CMS_RPT_WHRQ();
                whrq.setKhdm(khdm);
                whrq.setKhmc(khmc);
                whrq.setQsrq(qsrq);
                whrq.setJzrq(jzrq);
                whrq.setUpdateDjh(updateDjh);
                whrq.setUpdateUser(updateUserDMMC);
                whrq.setLastUpdate(sdf.format(Calendar.getInstance().getTime()));
            } else {
                //如果是正常的更新数据
                if (updateFlag) {
                    //如果传递过来的截至日期空则直接反回0
                    if (null == jzrq || jzrq.equalsIgnoreCase("")) {
                        result = 0;
                        return result;
                    }

                    whrq = (CMS_RPT_WHRQ) list.iterator().next();
                    //如果传递过来的起始日期不为空（包含""）,并且whrq.qsrq为空包含""）
                    if (null != qsrq && !qsrq.equalsIgnoreCase("") && (whrq.getQsrq() == null || whrq.getQsrq().trim().equalsIgnoreCase(""))) {
                        whrq.setQsrq(qsrq);
                    }
                    //如果传递的截至日期大于当前数据库里面的截至日期则更新数据，否则不更新数据
                    if (null == whrq.getJzrq() || whrq.getJzrq().trim().equalsIgnoreCase("") || (null != jzrq && !jzrq.equalsIgnoreCase("") && dateFormat.parse(whrq.getJzrq()).before(dateFormat.parse(jzrq)))) {
                        whrq.setJzrq(jzrq);
                        whrq.setLastUpdate(sdf.format(Calendar.getInstance().getTime()));
                        whrq.setUpdateDjh(updateDjh);
                        whrq.setUpdateUser(updateUserDMMC);
                    } else {
                        result = 0;
                    }
                }
                //如果是减少维护日期
                else if (!updateFlag) {
                    whrq = (CMS_RPT_WHRQ) list.iterator().next();
                    // System.out.println("updateFlag:"+updateDjh+";whrq.updateDjh:"+whrq.getUpdateDjh());
                    if (1 == 1) {
                        //查询当前数据中同单位的最大截至日期
                        List listMax = QueryData.getSomeEntityHQL("select max(jzrq) as jzrq,id from CWBills t where t.khdm = '" + khdm + "' and zt >='1' and id <> '" + updateDjh + "'");
                        if (null != listMax && listMax.iterator().hasNext()) {
                            Object[] str = (Object[]) listMax.iterator().next();
                            if (null != str[0] && !"".equalsIgnoreCase(str[0].toString().trim())) {
                                jzrq = str[0].toString().trim();
                            } else {
                                jzrq = qsrq; //维护日期的截至日期为单据的起始日起
                            }
                            if (null != str[1] && !"".equalsIgnoreCase(str[1].toString().trim())) {
                                updateDjh = str[1].toString().trim();
                            } else {
                                updateDjh = "取消审核更新";
                            }

                        }
                        System.out.println("++++++++++++++++++++" + jzrq + ":" + updateDjh + "++++++++++++++");
                        whrq.setJzrq(jzrq);
                        whrq.setLastUpdate(sdf.format(Calendar.getInstance().getTime()));
                        whrq.setUpdateDjh(updateDjh);
                        whrq.setUpdateUser(updateUserDMMC);
                    }
                }
            }
            if (null != whrq) {
                session.saveOrUpdate(whrq);
                transaction.commit();
            }
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
            if (null != transaction)
                transaction.rollback();
            result = -1;
        } finally {
            if (null != session  ) {
                session.clear();
                session.flush();
                session.close();
            }
            return result;
        }
    }

    //写入应收款统计表
    public static boolean updateDWYSK(String khdm, double je, String kjnd, String operator, Session session) {
        Boolean hasDwysk = false;
        try {
            List list = QueryData.getSomeEntityLike("CMS_RPT_DWYSK", DMMCUtils.getDM(khdm, "[") , "khdm");
            if (null != list && null != list.iterator() && list.iterator().hasNext()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    CMS_RPT_DWYSK dwysk = (CMS_RPT_DWYSK) it.next();
                    //如果获取到当前年度未收款则更新
                    if (dwysk.getKjnd().equals(kjnd)) {
                        hasDwysk = true;
                        Query query = null;
                        if (operator.equals("+")) {
                            query = session.createQuery("update CMS_RPT_DWYSK  set ysk = :ysk + ysk where khdm like :khdm and kjnd = :kjnd ");
                        } else if (operator.equals("-")) {
                            query = session.createQuery("update CMS_RPT_DWYSK  set ysk = ysk - :ysk where khdm like :khdm and kjnd = :kjnd ");
                        }
                        query.setString("khdm", DMMCUtils.getDM(khdm, "["));
                        query.setString("kjnd", kjnd);
                        query.setDouble("ysk", je);
                        query.executeUpdate();
                        break;
                    } else continue;
                }
            }
            //如果显示没有单位应收款则执行插入操作
            if (!hasDwysk) {
                CMS_RPT_DWYSK newDwysk = new CMS_RPT_DWYSK();
                newDwysk.setKhdm(khdm);
                newDwysk.setKjnd(kjnd);
                newDwysk.setYjsk(0.00);
                newDwysk.setYsk(je);
                session.save(newDwysk);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
