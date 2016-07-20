<#--
通过rstlist传值的时候，不处理多级的checkbox，因为rstlist只能是Map<String, String>类型的，无法传递分级信息
如果checkbox需要分级，只能通过listData传入
-->
<#if listData?? && listData?size gt 0>
<#list listData?keys as key1>
	<div class="purv_lev1 uniformjs">
    	<label class="frm_checkbox_label selected">
            <input type="checkbox"  id="" class="frm_checkbox frm_checkboxlist chk1" name="${(name)!}" value="${key1}" <#if disabled?? && disabled>disabled=disabled</#if> <#if listData[key1]["checked"]?? && listData[key1]["checked"]>checked="checked"</#if> />
            <span class="frm_val">${listData[key1]["name"]}</span>
        </label>
        <#if listData[key1]["children"]?? && listData[key1]["children"]?size gt 0>
        <#list listData[key1]["children"]?keys as key2>
        <div class="purv_lev2">
        	<span class="purv_lev2s">
                <label class="frm_checkbox_label checkbox">
                    <input type="checkbox"  id="" class="frm_checkbox frm_checkboxlist chk2" name="${(name)!}" value="${key2}" <#if disabled?? && disabled>disabled=disabled</#if> <#if listData[key1]["children"][key2]["checked"]?? && listData[key1]["children"][key2]["checked"]>checked="checked"</#if> />
                    <span class="frm_val">${listData[key1]["children"][key2]["name"]}</span>
                </label>
            </span>
            <#if listData[key1]["children"][key2]["children"]?? && listData[key1]["children"][key2]["children"]?size gt 0>
            <#list listData[key1]["children"][key2]["children"]?keys as key3>
			<span class="purv_lev3">
				<span class="purv-lev3s">
                    <label class="frm_checkbox_label checkbox">
                        <input type="checkbox"  id="" class="frm_checkbox frm_checkboxlist chk3" name="${(name)!}" value="${key3}" <#if disabled?? && disabled>disabled=disabled</#if> <#if listData[key1]["children"][key2]["children"][key3]["checked"]?? && listData[key1]["children"][key2]["children"][key3]["checked"]>checked="checked"</#if> />
                        <span class="frm_val">${listData[key1]["children"][key2]["children"][key3]["name"]}</span>
                    </label>
                </span>
			</span>
			</#list>
			</#if>
		</div>
		</#list>
		</#if>
	</div>
</#list>
<#elseif rstlist?? && rstlist?size gt 0>
<#list rstlist?keys as key>
	<span class=" check_content uniformjs">
    	<label class="frm_checkbox_label selected">
            <input  id="" name="${(name)!}" class="frm_checkbox" type="checkbox"  name="" value="${key}" <#if disabled?? && disabled>disabled=disabled</#if> <#if checkedList?seq_contains("${key}")>checked="checked"</#if> <#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl"> />${rstlist[key]}
        </label>
    </span>
</#list>
</#if>
<#include "${taglibs.ftlctx}/common/control/required_star.ftl">