
package cn.com.p2p.framework.code;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.Assert;


public class BizCodeList extends AbstractBizCodeList {

  
    Map<String, Map<String, String>> codeListTable = new LinkedHashMap<String, Map<String, String>>();

    @Override
    public Map<String, String> getMap(String bizType) {
        Assert.notNull(bizType, "locale is null");
        return codeListTable.get(bizType);
    }


    public void setRows(Map<String, Map<String, String>> rows) {

        for (Map.Entry<String, Map<String, String>> e : rows.entrySet()) {
            Map<String, String> row = e.getValue();
            codeListTable.put(e.getKey(), row);
        }

    }



	@Override
	public Map<String, String> toMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
