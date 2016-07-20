package cn.com.p2p.domain.user.query;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.user.entity.WebUser;

/**
 * WebUserQuery定义.
 * <p>
 * 数据访问层<前台用户>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 *
 */
public interface WebUserQuery {

	/**
	 * 按登录名进行查询
	 * @param login  登录名
	 * @return
	 */
	public WebUser findByLogin(@Param("login") String login);
}
