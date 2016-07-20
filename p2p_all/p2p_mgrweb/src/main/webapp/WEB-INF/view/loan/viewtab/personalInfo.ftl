    <div class="form_wrp">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Customer name:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalBasicDto.name)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of2 table_right_border">
                        <label class="frm_label">ID number:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalBasicDto.identity)!}</span>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Gender:</label>
                        <div class="frm_controls">
                        	<!--性别的显示共通目前还没有完成-->
                            <span class="frm_val"><@bizTag.enumValue enumName="SexEnum" key="${(loanDto.personalBasicDto.gender)!}" /></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Birthday:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalBasicDto.birthdayDate?date)!}</span>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Birthplace:</label>
                        <div class="frm_controls">
                        	<!--地址的显示共通目前还没有完成-->
                            <span class="frm_val">${(loanDto.personalBasicDto.birthplace)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Phone:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalBasicDto.cellphone)!}</span>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Email:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalBasicDto.email)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Occupation:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalBasicDto.occupation)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" size1of1" colspan="2">
                        <label class="frm_label">address:</label>
                        <div class="frm_controls frm_address">
                        	<!--地址的显示共通目前还没有完成-->
                            <span class="frm_val">${(loanDto.personalBasicDto.address)!}</span>
                        </div> 
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="form_wrp">
        <h3 class="h3">Personal Job Info</h3>
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company name:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalJobDto.companyName)!}</span>
                        </div>
                    </td>
                    <td class=" size1of2">
                         <label class="frm_label">Department:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalJobDto.department)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                         <label class="frm_label">Position:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalJobDto.workPosition)!}</span>
                        </div>
                    </td>
                    <td class="size1of2">
                        <label class="frm_label">Salary:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalJobDto.monthlyIncome)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company type:</label>
                        <div class="frm_controls">
                            <span class="frm_val"><@bizTag.enumValue enumName="CompanyTypeEnum" key="${(loanDto.personalJobDto.companyCategory)!}" /></span>
                        </div>
                    </td>
                    <td class=" size1of2">
                        <label class="frm_label">Company industry:</label>
                        <div class="frm_controls">
                            <span class="frm_val"><@bizTag.enumValue enumName="CompanyTradesEnum" key="${(loanDto.personalJobDto.companyIndustry)!}" /></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company scale:</label>
                        <div class="frm_controls">
                            <span class="frm_val"><@bizTag.enumValue enumName="CompanySizeEnum" key="${(loanDto.personalJobDto.companySize)!}" /></span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="table_right_border size1of2">
                        <label class="frm_label">Company phone:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(loanDto.personalJobDto.companyTel)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class=" size1of2" colspan="2">
                        <label class="frm_label">Company address:</label>
                        <div class="frm_controls frm_address">
                        	<!--地址的显示共通目前还没有完成-->
                            <span class="frm_val"><span class="frm_val">${(loanDto.personalJobDto.address)!}</span>
                        </div> 
                    </td>
                </tr>
                <tr class="last">
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