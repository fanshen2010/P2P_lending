	<div id="base_info" class="tab-bd-con active">
        <div class="form_wrp">
            <table class="table" cellpadding="0" cellspacing="0">
                <tbody class="tbody" >
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Customer name:</label>
                            <div class="frm_controls">
                                <#if export?? &&export =="1">
	                             	<span class="frm_val">${(loanPersonalBasicDto.name)!}</span>
	                          		<input type="hidden" name="loanPersonalBasicDto.name" value="${loanPersonalBasicDto.name}"/>
	                            <#elseif updateDistinguish?? &&updateDistinguish =="1" >
	                            	<span class="frm_val">${(loanPersonalBasicDto.name)!}</span>
	                          		<input type="hidden" name="loanPersonalBasicDto.name" value="${loanPersonalBasicDto.name}"/>
	                            <#else>
		                            <@ctl.text name="loanPersonalBasicDto.name" required=true  validate={"maxlength":"32"} fieldName="Customer name" value="${loanPersonalBasicDto.name}"/>
	                            </#if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">ID number:</label>
                            <div class="frm_controls">
	                        	 <#if export?? &&export =="1">
	                             	<span class="frm_val">${loanPersonalBasicDto.identity}</span>
	                           		<input type="hidden" name="loanPersonalBasicDto.identity" value="${loanPersonalBasicDto.identity}"/>
	                            <#elseif updateDistinguish?? &&updateDistinguish =="1" >
	                            	<span class="frm_val">${loanPersonalBasicDto.identity}</span>
	                           		<input type="hidden" name="loanPersonalBasicDto.identity" value="${loanPersonalBasicDto.identity}"/>
	                            <#else>
		                            <@ctl.text id="identity" name="loanPersonalBasicDto.identity" required=true  validate={"chinaId":"true"} fieldName="ID number" value="${loanPersonalBasicDto.identity}"/>
	                            </#if>
                            </div>
                        </td>
                        <td class=" size1of2">
                            <label class="frm_label">Gender:</label>
                            <div class="frm_controls">
                            	<#if export?? &&export =="1">
	                             	<span class="frm_val"><@bizTag.enumValue enumName="SexEnum" key="${(loanPersonalBasicDto.gender)!1}" /></span>
	                             	<input type="hidden" name="loanPersonalBasicDto.gender" value="${(loanPersonalBasicDto.gender)!1}"/>
	                            <#elseif updateDistinguish?? &&updateDistinguish =="1">
	                            	<span class="frm_val"><@bizTag.enumValue enumName="SexEnum" key="${(loanPersonalBasicDto.gender)!1}" /></span>
	                             	<input type="hidden" name="loanPersonalBasicDto.gender" value="${(loanPersonalBasicDto.gender)!1}"/>
	                            <#else>
		                            <@ctl.radiobuttonlist  name="loanPersonalBasicDto.gender" groupCode="SexEnum" value="${(loanPersonalBasicDto.gender)!1}" dataType="Enum"  class="frm_val frm_radio radio"/>
	                            </#if>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Birthday:</label>
                            <div class="frm_controls">
	                            <#if export?? &&export =="1">
	                             	<span class="frm_val">${(loanPersonalBasicDto.birthdayDate)?string("yyyy-MM-dd")}</span>
	                             	<input type="hidden" name="loanPersonalBasicDto.birthdayDate" value="${(loanPersonalBasicDto.birthdayDate)?string("yyyy-MM-dd")}"/>
	                            <#elseif updateDistinguish?? &&updateDistinguish =="1">
	                            	<span class="frm_val">${(loanPersonalBasicDto.birthdayDate)?string("yyyy-MM-dd")}</span>
	                             	<input type="hidden" name="loanPersonalBasicDto.birthdayDate" value="${(loanPersonalBasicDto.birthdayDate)?string("yyyy-MM-dd")}"/>
	                            <#else>
		                            <@ctl.datepicker id="birthdayDate" name="loanPersonalBasicDto.birthdayDate"  required=true max="now" validate={"dateFormat":"true"} fieldName="Birthday" value=loanPersonalBasicDto.birthdayDate/>
	                            </#if>
                            </div>
                        </td>
                        <td class=" size1of2">
                            <label class="frm_label">Birthplace:</label>
                            <div class="frm_controls">
                                <@ctl.text  name="loanPersonalBasicDto.birthplace"    value="${loanPersonalBasicDto.birthplace}" validate={"maxlength":"100"} />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Phone:</label>
                            <div class="frm_controls">
                                <@ctl.text id="cellphone" name="loanPersonalBasicDto.cellphone"   class="frm_input required"    required=true  validate={"cellphone":"true"} value="${loanPersonalBasicDto.cellphone}" fieldName="Phone"/>
                            </div>
                        </td>
                        <td class=" size1of2">
                            <label class="frm_label">Email:</label>
                            <div class="frm_controls">
                                <@ctl.text  name="loanPersonalBasicDto.email"  value="${loanPersonalBasicDto.email}" validate={"maxlength":"64","email":"true"}/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class=" size1of1" colspan="2">
                            <label class="frm_label">Occupation:</label>
                            <div class="frm_controls">
                                <@ctl.text  name="loanPersonalBasicDto.occupation"  required=true   value="${loanPersonalBasicDto.occupation}" validate={"maxlength":"32"} fieldName="Occupation"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class=" size1of1" colspan="2">
                            <label class="frm_label">address:</label>
                            <div class="frm_controls frm_address">
                            <@ctl.text  name="loanPersonalBasicDto.address" class="frm_input street" spanClass="medium_l street"  required=true  validate={"maxlength":"100"} value="${(loanPersonalBasicDto.address)!}" fieldName=""/>
                            </div> 
                        </td>
                    </tr>
                    
                    
                </tbody>
            </table>
        </div>
    </div>
    <script type="text/javascript">
    $(function(){
	    $("#identity").blur(function(){
	            	var identity=$(this).val();
	         	if(identity&&identity!=""){
	         		//获取出生日期 
					var birthDay=identity.substring(6, 10) + "-" + identity.substring(10, 12) + "-" + identity.substring(12, 14); 
					$("#birthdayDate").val(birthDay);
					$("input:radio").each(function(){
						$(this).prop("checked",false);
						$(this).parent().removeClass("checked");
					});
					//获取性别 
					if (parseInt(identity.substr(16, 1)) % 2 == 1) { 
					//男 
						$("input:radio[value='1']").prop("checked", "checked");
						$("input:radio[value='1']").parent().addClass("checked");
					} else { 
					//女 
						$("input:radio[value='0']").prop("checked", "checked");
						$("input:radio[value='0']").parent().addClass("checked");
					} 
	         	}
	    
	    });
     });
    </script>