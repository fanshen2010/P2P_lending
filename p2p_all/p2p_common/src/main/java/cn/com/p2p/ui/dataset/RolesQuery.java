package cn.com.p2p.ui.dataset;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.repository.PfmRoleRepository;
import cn.com.p2p.framework.web.ui.UiListDataQuery;

/**
 * <p>查询所有的角色列表，并组装城Map对象</p>
 * @author  
 * @date 2015-10-02 15:42
 */
@Service(value = "RolesQuery")
public class RolesQuery implements UiListDataQuery {
    
    /** 角色管理接口 */
    @Autowired
    private PfmRoleRepository pfmRoleRepository;;

	@Override
	public Map<String, String> dataQuery() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		// 查询所有的角色列表，并组装城Map对象
		List<PfmRole> roles = pfmRoleRepository.findAll();
		for(PfmRole role : roles){
			result.put(role.getRoleId(), role.getRoleName());
		}
		return result;
	}

}
