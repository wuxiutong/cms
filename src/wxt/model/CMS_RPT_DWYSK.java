package wxt.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by wuxiutong on 2015/9/6.
 */
public class CMS_RPT_DWYSK implements Serializable {
    String khdm; //客户代码
    String kjnd; //会计年度
    Double yjsk; //预计收款
    Double ysk; //已收款

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CMS_RPT_DWYSK dwysk = (CMS_RPT_DWYSK) o;
        return Objects.equals(khdm, dwysk.khdm) &&
                Objects.equals(kjnd, dwysk.kjnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(khdm, khdm);
    }

    public String getKhdm() {
        return khdm;
    }

    public void setKhdm(String khdm) {
        this.khdm = khdm;
    }

    public String getKjnd() {
        return kjnd;
    }

    public void setKjnd(String kjnd) {
        this.kjnd = kjnd;
    }

    public Double getYjsk() {
        return yjsk;
    }

    public Double getYsk() {
        return ysk;
    }

    public void setYsk(Double ysk) {
        this.ysk = ysk;
    }

    public void setYjsk(Double yjsk) {
        this.yjsk = yjsk;

    }
}
