package wxt.dao;

import org.hibernate.Session;
import wxt.utils.FjgzUtils;
import wxt.utils.HibernateUtils;

/**
 * Created by wuxiutong on 15/5/23.
 */
public class demode {
    public static void main(String args []){
       String dqfj = FjgzUtils.getFjfa("dqfj");
        System.out.println("dqfj:"+ dqfj);
    }
}
