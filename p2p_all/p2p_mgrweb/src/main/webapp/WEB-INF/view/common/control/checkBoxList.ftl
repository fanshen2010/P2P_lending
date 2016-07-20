<#--
<@ctl.checkboxlist listData id name groupCode value dataType data class spanClass labelClass />
-->

<#macro checkboxlist 
	id="" name="" groupCode="" value="" dataType="" data="" listData={} disabled=false checkedList=[] required=false 
	>
	<@checkBoxList groupCode=groupCode id=id name=name commonResultPath="${commonResultPath}" dataType=dataType data=data listData=listData value=value enable=enable checkedList=checkedList />
</#macro>
