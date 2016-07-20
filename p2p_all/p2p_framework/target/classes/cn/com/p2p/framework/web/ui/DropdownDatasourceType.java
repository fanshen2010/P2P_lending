package cn.com.p2p.framework.web.ui;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/***
 * 详细营销方案执行者定义
 * @author 
 *
 */
public enum DropdownDatasourceType implements CodeListItem {
    
    /**
     * 营销枚举类
     */
    MARKET_PATH("market", "cn.com.p2p.marketing.constants");
    
    /**
     * 流程枚举类
     */
    //WORKFLOW_PATH("workflow", "cn.com.p2p.workflow.constants");

    
    private String value = null;
    private String code = null;
    
    private DropdownDatasourceType(String _code, String _value){
        this.value = _value;
        this.code = _code;
    }
    
    public static DropdownDatasourceType getType(String key) {
        for (DropdownDatasourceType e : DropdownDatasourceType.values()) {
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
