<#--
普通文本框
<@ctl.text id name class title tabindex value counter type required validate autocomplete placeholder hasTips tips />
-->

<#macro text
	id="" name="" class="input-text" title="" tabindex="" value="" counter="" type="text" placeholder="" readonly=false tips="" hasTips=false
	required=false validate={} autocomplete=true maxlength="" fieldName="" >
	<span class="frm_input_box <#if counter?? && counter!="">with_counter append</#if>">
        <input type="${type}" <#rt/>
        class="${class} <#if hasTips?? && hasTips>hasTips</#if>"<#rt/>
        <#if id?? && id!=""> id="${id}" </#if><#rt/>
        <#if name?? && name!=""> name="${name}" </#if><#rt/>
        <#if title?? && title!=""> title="${title}" </#if><#rt/>
        <#if tabindex?? && tabindex!=""> name="${tabindex}" </#if><#rt/>
        <#if value?? && value!=""> value="${value}" </#if><#rt/>
        <#if autocomplete?? && autocomplete><#else> autocomplete="off" </#if><#rt/>
        <#if placeholder?? && placeholder!="">placeholder="${placeholder}"</#if><#rt/>
        <#if counter?? && counter!=""> dir="rtl" </#if><#rt/>
        <#if readonly?? && readonly> readonly="true" </#if><#rt/>
        <#if maxlength?? && maxlength != ""> maxlength = "${maxlength}"<#rt/></#if>
        <#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl">
        <#if fieldName?? && fieldName!=""> data-field_name="${fieldName}" </#if>
        />
        <#if counter?? && counter!="">
        <em class="frm_input_append frm_counter">${counter}</em>
        </#if>
    </span>
    <#if hasTips?? && hasTips>
    <script type="text/javascript">
    $(".hasTips").tips(<#if tips?? && tips!="">{format:"${tips}"}</#if>);
    </script>
    </#if>
</#macro>
