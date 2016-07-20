package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

public enum InvestStatusEnmu implements CodeListItem {

    /**
     * 00:取消，01:待付款，02:待生效，03:超募退款中，04:流标退款中，05:超募退回，06:流标退回，07:还款中，08:提前还款处理中，
     * 09:已完成,10:转让申请中,11:转让中,12:转让完成）
     */

    /**
     * 取消
     */
//    INVEST_STATUS_00("00", "取消"),
    /**
     * 待付款
     */
    INVEST_STATUS_01("01", "investing"),
    /**
     * 待生效
     */
    INVEST_STATUS_02("02", "auditting"),
    /**
     * 超募退款中
     */
//    INVEST_STATUS_03("03", "超募退款中"),
    /**
     * 流标退款中
     */
//    INVEST_STATUS_04("04", "流标退款中"),
    /**
     * 超募退回
     */
    INVEST_STATUS_05("05", "超募已退款"),
    /**
     * 流标退回
     */
    INVEST_STATUS_06("06", "failed"),
    /**
     * 还款中
     */
    INVEST_STATUS_07("07", "repaying"),
    /**
     * 提前还款处理中
     */
//    INVEST_STATUS_08("08", "提前还款处理中"),
    /**
     * 已完成
     */
    INVEST_STATUS_09("09", "repaid"),
    /**
     * 转让申请中
     */
//TODO 暂时注释 此版本不要该功能    INVEST_STATUS_10("10", "转让申请中"),
    /**
     * 转让中
     */
//    INVEST_STATUS_11("11", "转出中"),
    /**
     * 转让完成
     */
//TODO 暂时注释 此版本不要该功能    INVEST_STATUS_12("12", "已转出"),
//    /**
//     * 提前还款完成
//     */
//    INVEST_STATUS_13("13", "提前还款完成"),
    /**
     * 超募
     */
    INVEST_STATUS_14("14", "超募");
    

    private String value = null;
    private String code = null;

    private InvestStatusEnmu(String _code, String _value) {
        this.value = _value;
        this.code = _code;
    }

    public static InvestStatusEnmu getEnumByKey(String key) {
        for (InvestStatusEnmu e : InvestStatusEnmu.values()) {
            if (e.getCode().equals(key)) {
                return e;
            }
        }
        return null;
    }

    /** 获取value */
    public String getValue() {
        return value;
    }

    /** 获取code */
    public String getCode() {
        return code;
    }
}