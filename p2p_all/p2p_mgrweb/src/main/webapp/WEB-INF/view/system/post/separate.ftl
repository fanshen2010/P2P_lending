<#if postDto.menuMap?if_exists>
    <@ctl.checkboxlist listData=postDto.menuMap disabled="true"/>
<#elseif postDto.pfmUserOfNoPosts?if_exists && postDto.pfmUserOfNoPosts?size != 0>
 <#-- 表单在这里 --><form id="search_form" action="addmember.htm" method="POST">
        <input type="hidden" name="postDto.post.postCd" value="${(postDto.post.postCd)!}">
        <input type="hidden" name="postDto.user.realName" id="keyword" value="${(postDto.user.realName)!}"/>
        <div class="role_name_area">
            <ul class="role_name_list">
               <#list postDto.pfmUserOfNoPosts as user>
                  <li>
                    <span class="uniformjs">
                       <span class=" check_content uniformjs">
                            <label class="frm_checkbox_label selected">
                                <input name="postDto.userIds" value="${(user.id)!}" class="frm_checkbox" type="checkbox">
                            </label>
                        </span>
                        <span class="name">${(user.realName)!}</span>
                    </span>
                  </li>
               </#list>
            </ul>
           <@ctl.submit title="add" text="add"/>
        </div>
  <#-- 表单在这里 --></form>
  <script type="text/javascript">
    // checkbox radio
    $('.uniformjs').find("input[type=checkbox],input[type=radio]").uniform();
  </script>
<#else>
	<div class="role_name_area">
        <ul class="role_name_list">
              <li>
                    <span class="name">No data</span>
              </li>
        </ul>
    </div>
</#if>

