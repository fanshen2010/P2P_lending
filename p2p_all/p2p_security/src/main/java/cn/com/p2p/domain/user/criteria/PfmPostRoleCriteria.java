/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmPostRoleCriteria.java
 * Description:       查询条件PfmPostRoleCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;


import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_POST_ROLE")
public class PfmPostRoleCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**角色ID*/
    @Column(name="ROLE_ID")
    private String roleId;

    /**职位ID*/
    @Column(name="POST_ID")
    private String postId;

         
    public PfmPostRoleCriteria() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId, Operator ... oper) {
        this.roleId = roleId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("roleId", param);
            }
        }
    }
    /**获取职位ID*/
    public String getPostId() {
        return postId;
    }

    /**设置职位ID*/
    public void setPostId(String postId, Operator ... oper) {
        this.postId = postId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("postId", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        roleId("ROLE_ID"),
        postId("POST_ID");

    	private String value = "";
    	private OrderField(String value){
    		this.value = value;
    	}

		@Override
		public String getValue() {
			return value;
		}
    }
    public String toString() {
        return "PfmPostRole ["
        + ", id=" + id
        + ", roleId=" + roleId
        + ", postId=" + postId
        + "]";
    }

}
