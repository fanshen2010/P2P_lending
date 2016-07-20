<#--标签使用整理demo,方便拷贝-->

<#--枚举数据的下拉列表-->
<@ctl.dropdownlist name="paymentUsage"  hasChoice="select"  groupCode="InvestTypeEnmu"  dataType="Enum" value="${(paymentUsage)!}" />

<#--枚举类值code转name-->
<@bizTag.enumValue enumName="InvestTypeEnmu" key="${(paymentLog.paymentUsage)!}" ></@bizTag.enumValue>

<#--日期格式-->
<@h.datef value="${(createTime)!}" />

 <#--数字金额格式-->
<@h.numf value=loanAmount />
