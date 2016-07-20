<#--
下拉列表框
<@ctl.dropdownlist listData id name groupCode value dataType data class spanClass labelClass />
-->

<#macro radiobuttonlist 
	id="" name="" groupCode="" value="" dataType="" data="" class="frm_radio radio" labelClass="frm_radio_label" spanClass="uniformjs" listData={}
	>
	<@radioButtonList groupCode=groupCode id=id name=name commonResultPath="${commonResultPath}" dataType=dataType data=data class=class labelClass=labelClass spanClass=spanClass listData=listData value=value />
</#macro>
