<#--
password文本框
<@ctl.password id name class title tabindex value required validate />
-->

<#macro password
	id="" name="" class="frm_input" title="" tabindex="" value="" required=false validate={} fieldName="" >
	<@text id=id name=name class=class title=title tabindex=tabindex value=value type="password" required=required validate=validate autocomplete=false fieldName=fieldName />
</#macro>
