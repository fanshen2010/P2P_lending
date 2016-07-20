<#--
普通文本框
<@ctl.text id name class title tabindex value counter type required validate autocomplete placeholder hasTips tips />
-->

<#macro text
	id="" name="" class="frm_input" title="" tabindex="" value="" counter="" type="text" placeholder="" readonly=false tips="" hasTips=false
	required=false validate={} autocomplete=true maxlength="" fieldName="" spanClass="" disabled=false>
	<span class="frm_input_box <#if counter?? && counter!="">with_counter append</#if> <#if spanClass?? && spanClass!="">${spanClass}</#if>">
        <input type="${type}" <#rt/>
        class="${class} <#if hasTips?? && hasTips>hasTips</#if> <#if counter?? && counter!=""> tr </#if><#rt/>"<#rt/>
        <#if id?? && id!=""> id="${id}" </#if><#rt/>
        <#if name?? && name!=""> name="${name}" </#if><#rt/>
        <#if title?? && title!=""> title="${title}" </#if><#rt/>
        <#if tabindex?? && tabindex!=""> name="${tabindex}" </#if><#rt/>
        <#if value?? && value!=""> value="${value}" </#if><#rt/>
        <#if autocomplete?? && autocomplete><#else> autocomplete="off" </#if><#rt/>
        <#if placeholder?? && placeholder!="">placeholder="${placeholder}"</#if><#rt/>
        <#if readonly?? && readonly> readonly="true" </#if><#rt/>
         <#if disabled?? && disabled> disabled="true" </#if><#rt/>
        <#if maxlength?? && maxlength != ""> maxlength = "${maxlength}"<#rt/></#if>
        <#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl">
        <#if fieldName?? && fieldName!=""> data-field_name="${fieldName}" </#if>
        />
        <#if counter?? && counter!="">
        <em class="frm_input_append frm_counter">${counter}</em>
        </#if>
    </span>
    <#include "${taglibs.ftlctx}/common/control/required_star.ftl">
    <#if hasTips?? && hasTips>
    <script type="text/javascript">
    $(".hasTips").tips(<#if tips?? && tips!="">{format:"${tips}"}</#if>);
    </script>
    </#if>
</#macro>
