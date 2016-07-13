package wxt.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import wxt.model.User;
import wxt.utils.HibernateUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by wuxiutong on 2015/8/25.
 */
public class UserUtils {
    public static User Login(String username, String password) {
//        System.out.println("username:"+username+"_______password:"+password);
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("password", password));
            List entity = criteria.list();
            if (null != entity && !entity.isEmpty()) {
                Iterator it = entity.iterator();
                if (it.hasNext()) {
                    while (it.hasNext()) {
                        User user = (User) it.next();
                        if (user.getUserID().equals(username)) {
                            return user;
                        } else {
                            continue;
                        }
                    }
                    //重新循环
                    Iterator itId = entity.iterator();
                    while (itId.hasNext()) {
                        User user = (User) itId.next();
//                        System.out.println("传入的username:"+username+"数据库中username:"+user.getUserName());
                        if (user.getUserName().equals(username)) {
                            return user;
                        } else {
                            if (itId.hasNext()) {
                                continue;
                            } else return null;
                        }
                    }
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(null!=session ){
                session.close();
            }
        }
    }
}
