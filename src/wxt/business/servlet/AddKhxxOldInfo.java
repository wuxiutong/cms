package wxt.business.servlet;

import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by wuxiutong on 15/9/5.
 */
public class AddKhxxOldInfo {
    public void add(String khdm, String updateTime, String updateType,String updateuser,Session session) throws ServletException, IOException {
         String newKhdm = "";
        try {
            //客户资料表
            String khxxSql = "insert into Khxx_oldInfo (updateTime,updateType,updateUser,khdm,khmc,gzdz,bgdh,ps,khjl,ssdq,khlx,xsgs,zt)" +
                    " select '" + updateTime + "' as updateTime,'" + updateType + "' as updateType,'"+updateuser+"' as updateUser,khxx.khdm,khxx.khmc,khxx.gzdz,khxx.bgdh,khxx.ps,concat(khxx.khjl,'[',zyxx.zyxm,']'),concat( khxx.ssdq,'[',dqxx.dqmc,']')," +
                    "concat(khxx.khlx,'[',dwlx.lxmc,']'),concat(khxx.xsgs,'[',en.gsmc,']'),khxx.zt from" +
                    " Khxx as  khxx,Dqxx as dqxx,Dwlx  as dwlx ,Enterprise as en,Zyxx as zyxx  where khdm = '" + khdm + "' " +
                    " and en.gsdm = khxx.xsgs and dqxx.dqdm = khxx.ssdq and dwlx.lxdm = khxx.khlx and zyxx.zydm = khxx.khjl" ;
            Query queryKhxx = session.createQuery(khxxSql);
            //客户联系人表
            String khxx_lxrSql = "insert into Khxx_lxr_oldInfo ( updateTime,lxrbh,khdm,lxrxm,sex,age,zw,tel,cellphone,email,qq,qtlxfs,ps)" +
                    " select '" + updateTime + "' as updateTime,lxrbh,khdm,lxrxm,sex,age,zw,tel,cellphone,email,qq,qtlxfs,ps from Khxx_lxr where khdm = '" + khdm + "'";
            Query queryKhxx_lxr = session.createQuery(khxx_lxrSql);
            //客户联系人表
            String khxx_softSql = "insert into Khxx_soft_oldInfo ( updateTime,softID,khdm,gysDm,verDm,modelDm,modelMc,khmc,gysMc,verMc,yhs,gmrq,ps)" +
                    " select '" + updateTime + "' as updateTime,softID,khdm,gysDm,verDm,modelDm,modelMc,khmc,gysMc,verMc,yhs,gmrq,ps from Khxx_soft where khdm = '" + khdm + "'";
            Query queryKhxx_soft = session.createQuery(khxx_softSql);

            queryKhxx.executeUpdate();
            queryKhxx_lxr.executeUpdate();
            queryKhxx_soft.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
