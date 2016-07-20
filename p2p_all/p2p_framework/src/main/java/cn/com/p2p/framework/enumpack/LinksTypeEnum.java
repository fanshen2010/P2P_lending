package cn.com.p2p.framework.enumpack;

import cn.com.p2p.framework.code.EnumCodeList.CodeListItem;

/**
 * 友情链接枚举类
 * @author wangxiangshun
 *
 */
public enum LinksTypeEnum implements CodeListItem{
	/**
	 * 合作网站
	 */
	LINKS_TYPE_01("1", "coop website"),
	/**
	 * 内部链接
	 */
	LINKS_TYPE_02("2", "internal link"),
	/**
	 * 外部链接
	 */
	LINKS_TYPE_03("3", "external link");

	private String value = null;
	private String code = null;
	
	private LinksTypeEnum(String _code, String _value){
		this.value = _value;
		this.code = _code;
	}
	
	public static LinksTypeEnum getEnumByKey(String key) {
        for (LinksTypeEnum e : LinksTypeEnum.values()) {
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
