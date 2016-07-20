<#--
下拉列表框
<@ctl.dropdownlist listData id name hasAll hasChoice textChoice hasNone groupCode value dataType data class spanClass />
-->

<#macro dropdownlist 
	id="" name="" hasAll=false hasChoice=false textChoice="select" hasNone=false groupCode="" value="" dataType="" data="" class="selectpicker" spanClass="frm_select_picker" listData={} required=false fieldName=""
	>
	<@dropdownList groupCode=groupCode id=id name=name hasAll=hasAll hasChoice=hasChoice hasNone=hasNone commonResultPath="${commonResultPath}" dataType=dataType data=data class=class spanClass=spanClass listData=listData value=value fieldName=fieldName />
</#macro>
