/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmPostRole.java
 * Description:       实体PfmPostRole类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.entity;

import java.io.Serializable;


public class PfmPostRole implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**角色ID*/
    private String roleId;

    /**职位ID*/
    private String postId;

         
    public PfmPostRole() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    /**获取职位ID*/
    public String getPostId() {
        return postId;
    }

    /**设置职位ID*/
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String toString() {
        return "PfmPostRole ["
        + ", id=" + id
        + ", roleId=" + roleId
        + ", postId=" + postId
        + "]";
    }

}
