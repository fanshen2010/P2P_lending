
     <div class="col_side">
        <div class="menu_box" id="menuBar">
                <dl class="menu no_extra">
                    <dt class="menu_title home">
                    	<i class="icon_menu"></i>
                        <a href="${taglibs.ctx}/index.htm" title="">index</a>
                    </dt>
                </dl>
                <#assign actionUrl=request.requestURI>
                <@fm_menu_list roleIdList=loginuser.roleNameList requestURI=actionUrl  contextPath=request.contextPath>
                <#list fmMenus as fmMenu>
                <#if fmMenu.status !=0>
		           <dl class="menu ">
	                    <dt class="menu_title ${(fmMenu.menuClass)!}">
                            <i class="icon_menu"></i>
                            <span>${(fmMenu.menuName)!}</span>
                            <i class="icon_arrow"></i>
                        </dt>
                        <#list fmMenu.childs as child>
                        	<#if child.status !=0>
                        	    <dd class="menu_item ${(child.menuClass)!}"><a href="${taglibs.ctx}${child.menuUrl}" title="">${child.menuName}</a></dd>
                        	</#if>
                        </#list>
		            </dl>
                 </#if>
                 </#list>
                 </@fm_menu_list>
        </div>
     </div>
            