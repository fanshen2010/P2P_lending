	<div id="person-base-info" class="form_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Enterprise name:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.enterpriseName)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Organization code:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.organizationCert)!}</span>
                        </div>
                    </td>
                    <td class="size1of2">
                        <label class="frm_label">Business license:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.businessIicense)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Enterprise type:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val"><@bizTag.enumValue enumName="CompanyTradesEnum" key="${(loanDto.enterpriseInfoDto.enterpriseType)!}" /></span>
                        </div>
                    </td>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Enterprise industry:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val"><@bizTag.enumValue enumName="IndustryEnum" key="${(loanDto.enterpriseInfoDto.industry)!}" /></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Enterprise scale:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val"><@bizTag.enumValue enumName="EnterpriseScaleEnum" key="${(loanDto.enterpriseInfoDto.enterpriseScale)!}" /></span>
                        </div>
                    </td>
                    <td class="size1of2">
                        <label class="frm_label">Registered capital:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.registeredCapital)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Setup year:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.createdYear)!}</span>
                        </div>
                    </td>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Annual earnings:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.annualEarnings)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" table_right_border size1of2">
                        <label class="frm_label">Contact name:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.legalRepresentative)!}</span>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Contact phone:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.contactPhone)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" table_right_border size1of2">
                        <label class="frm_label">Contact email:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.contactMail)!}</span>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Enterprise address:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.officeAddr)!}</span>
                        </div>
                    </td>
                </tr>
                <tr class="last">
                    <td class="border_bottom_none size1of1" colspan="2">
                        <label class="frm_label">Enterprise brief:</label>
                        <div class="frm_controls"> 
                            <span class="frm_val">${(loanDto.enterpriseInfoDto.brief)!}</span>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
<div class="popsize_l company_addInfo_pop fancybox_pop" id="msg_view">
</div>
<script type="text/javascript">
$(".view.fancybox").click(function(){
		var url=$(this).data("action");
		var param = {};
		$(this).parents("tr").find("input[type='hidden']").each(function(){
		param[$(this).attr("name").replace(/\w+s\[\d\]./,"")]=$(this).val();
		
		});
		$.ajax({
	            type: "POST",
	            url: url,
	            dataType: "json",
	            data:  param,
	            error: function (data, transport) {
	               $("#msg_view").html("服务器繁忙");
	            },
	            success: function (result) {
	                $("#msg_view").html(result.html);
	                $.fancybox.open($("#msg_view"),{"title":"view"});
	            }
	    });
	});
</script>