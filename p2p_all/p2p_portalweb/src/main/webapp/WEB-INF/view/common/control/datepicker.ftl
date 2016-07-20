<#--
datepicker文本框
<@ctl.datepicker id name class title tabindex value required validate />
-->

<#macro datepicker
	id="" name="" class="frm_input datepicker" title="" tabindex="" value="" required=false validate={} max="" min="" fieldName="" >
	<#if value?is_date>
    	<#local value = value?string("yyyy-MM-dd")>
    </#if>
	<@text id=id name=name class=class title=title tabindex=tabindex value=value required=required validate=validate readonly=true fieldName=fieldName />
	<script type="text/javascript">
	$(".datepicker").datepicker({
		<#if max?? && max=="now">
		maxDate:"${.now}",
		<#elseif max?? && max!="">
		maxDate:"${max}",
		</#if>
		<#if min?? && min=="now">
		minDate:"${.now}",
		<#elseif min?? && min!="">
		minDate:"${min}",
		</#if>
		yearRange:'c-50:c+5'
	});
	</script>
</#macro>

<#macro datetimepicker
	id="" name="" class="frm_input datepicker_time" title="" tabindex="" value="" required=false validate={} max="" min="" fieldName="" >
	<#if value?is_date>
    	<#local value = value?string("yyyy-MM-dd HH:mm:ss")>
    </#if>
	<@text id=id name=name class=class title=title tabindex=tabindex value=value required=required validate=validate readonly=true fieldName=fieldName />
	<script type="text/javascript">
	$(".datepicker_time").datetimepicker({
		<#if max?? && max=="now">
		maxDateTime:new Date("${.now}"),
		<#elseif max?? && max!="">
		maxDateTime:new Date("${max}"),
		</#if>
		<#if min?? && min=="now">
		minDateTime:new Date("${.now}"),
		<#elseif min?? && min!="">
		minDateTime:new Date("${min}"),
		</#if>
		showSecond: true,
		showMillisec: true,
		timeFormat: 'HH:mm:ss'
	});
	</script>
</#macro>
