package wxt.utils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import wxt.model.Fjgz;

import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2015/3/16.
 */
public class FjgzUtils {
    public static String getFjfa(String fjlx){
        Session session  =null;
        Criteria ct = null;
        List list = null;
        Fjgz fjgz = null;
        String fjfa = null;
        try {
            session = HibernateUtils.getSession();
            ct = session.createCriteria(Fjgz.class);
            ct.add(Restrictions.eq("dm", fjlx));
            list = ct.list();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                 fjgz = (Fjgz) it.next();
                fjfa = fjgz.getFjgz();
              //  System.out.println("fjfa:"+fjfa);
            }
        }catch (Exception e){
           // System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            if(null!= session  ){
                session.close();
            };
            return fjfa;
        }
    }
}
