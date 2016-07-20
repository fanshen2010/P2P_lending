package cn.com.p2p.security.control.back;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.domain.user.entity.PfmRoleMenu;
import cn.com.p2p.domain.user.repository.PfmMenuRepository;
import cn.com.p2p.domain.user.repository.PfmRoleMenuRepository;
import cn.com.p2p.framework.util.StringUtils;

public class MySecurityMetaDataSource implements
		FilterInvocationSecurityMetadataSource, MySecurityMetaDataSourceAccessHandler {

	private PathMatcher urlMatcher = new AntPathMatcher();
	
	@Autowired
	private PfmMenuRepository pfmMenuRepository;
	
	@Autowired
	private PfmRoleMenuRepository pfmRoleMenuRepository;	


	private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String,Collection<ConfigAttribute>>();

	private List<String> notCheckUrlList = null;

	public void setNotCheckUrlList(List<String> notCheckUrlList) {
		this.notCheckUrlList = notCheckUrlList;
	}

	private boolean notFindDenied = false;

	public void setNotFindDenied(boolean notFindDenied) {
		this.notFindDenied = notFindDenied;
	}

	private List<ConfigAttribute> deniedConfigAttribute = new ArrayList<ConfigAttribute>();
	private static final Logger logger = LoggerFactory
			.getLogger(MySecurityMetaDataSource.class);

	public MySecurityMetaDataSource() {

	}

	public void init() {
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		
		//menu 资源查询
		List<PfmMenu> listFmmenu = pfmMenuRepository.findAll();
		for(PfmMenu fm : listFmmenu){
			
			//url 不存在时继续处理
			String url = fm.getMenuUrl();
			if(url == null || "".equals(url)){
				continue;
			}
			
			//menu对应权限权限
			List<PfmRoleMenu> pfmRoleMenus = pfmRoleMenuRepository.findPfmRoleMenuByMenuId(fm.getMenuId());
			if(pfmRoleMenus.size() > 0){
				for(PfmRoleMenu frm : pfmRoleMenus){
					ConfigAttribute ca = null;
					if(StringUtils.isNotEmpty(frm.getRoleId())){
						ca = new SecurityConfig(frm.getRoleId());
					} else{
						ca = new SecurityConfig("0");
					}

					if(resourceMap.containsKey(url)){
						Collection<ConfigAttribute> value = resourceMap.get(url);
						value.add(ca);
						resourceMap.put(url,value);
					} else{
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
						atts.add(ca);
						resourceMap.put(url,atts);
					}
				}
			} else {

				// 有菜单，没有角色关联
				ConfigAttribute ca = null;
				ca = new SecurityConfig("0");
				if(resourceMap.containsKey(url)){
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url,value);
				} else{
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url,atts);
				}
			}
		}

		ConfigAttribute ca = new SecurityConfig("denied");
		deniedConfigAttribute.add(ca);
	}

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	public boolean supports(Class<?> arg0) {

		return true;
	}
	
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();

		return getAttributesSub(url);
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(String url) {

		return getAttributesSub(url);

	}

	private Collection<ConfigAttribute> getAttributesSub(String reqUrl) {
		logger.trace("[MyInvocationSecurityMetadataSource][getAttributes][url]"
				+ reqUrl);

		String url = reqUrl;

		// 忽略URL不要权限验证
		if (notCheckUrlList != null) {
			// 忽略验证
			for (String value : notCheckUrlList) {
				if (urlMatcher.match(url, value)) {
					return null;
				}
			}
		}
		
		// 
		int firstQuestionMarkIndex = url.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}

		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.match(url, resURL)) {
				return resourceMap.get(resURL);

			}
		}

		if (notFindDenied) {
			// 没有找到匹配的url 拒绝访问
			logger.warn("[MyInvocationSecurityMetadataSource][getAttributes][没有找到匹配的url,拒绝访问][url]"
					+ url);

			return deniedConfigAttribute;
		}

		return null;
	}



	@Override
	public void refresh() {
		loadResourceDefine();

	}
}