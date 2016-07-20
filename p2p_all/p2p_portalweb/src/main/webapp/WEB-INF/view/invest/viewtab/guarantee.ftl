<#if loanDto.projectInfoDto.guaranteeComCode && loanDto.projectInfoDto.guaranteeComCode!="">
<div class="invest-group-li">
	<div class="guarantee">loan is guaranteed by<a href="guaranteeCompProfile.htm?departmentCd=${loanDto.projectInfoDto.guaranteeComCode}" target="_blank"><@bizTag.guaranteeComName guaranteeComCode=loanDto.projectInfoDto.guaranteeComCode  /></a> </div>
</div>
</#if>
