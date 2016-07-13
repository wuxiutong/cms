package wxt.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by wuxiutong on 2015-03-30.
 */
public class Khxx_lxr implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Khxx_lxr khxx_lxr = (Khxx_lxr) o;
        return Objects.equals(khdm, khxx_lxr.khdm) &&
                Objects.equals(lxrbh, khxx_lxr.lxrbh) &&
                Objects.equals(lxrxm, khxx_lxr.lxrxm) &&
                Objects.equals(sex, khxx_lxr.sex) &&
                Objects.equals(age, khxx_lxr.age) &&
                Objects.equals(zw, khxx_lxr.zw) &&
                Objects.equals(tel, khxx_lxr.tel) &&
                Objects.equals(cellphone, khxx_lxr.cellphone) &&
                Objects.equals(qq, khxx_lxr.qq) &&
                Objects.equals(qtlxfs, khxx_lxr.qtlxfs) &&
                Objects.equals(ps, khxx_lxr.ps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(khdm, lxrbh, lxrxm, sex, age, zw, tel, cellphone, qq, qtlxfs, ps);
    }

    private String khdm; // 客户代码
    private String lxrbh;//联系人编号
    private String lxrxm;// 联系人姓名
    private String sex;// 性别
    private String age;// 年龄
    private String zw;// 职务
    private String tel;// 座机
    private String email;// 手机
    private String cellphone;// 手机
    private String qq;//qq
    private String qtlxfs;//其他联系方式
    private String ps;//备注

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getKhdm() {
        return khdm;
    }

    public void setKhdm(String khdm) {
        this.khdm = khdm;
    }

    public String getLxrbh() {
        return lxrbh;
    }

    public void setLxrbh(String lxrbh) {
        this.lxrbh = lxrbh;
    }

    public String getLxrxm() {
        return lxrxm;
    }

    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQtlxfs() {
        return qtlxfs;
    }

    public void setQtlxfs(String qtlxfs) {
        this.qtlxfs = qtlxfs;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
