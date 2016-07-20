    <form action="${taglibs.allctx}/system/setting/updateBusiness.htm" method="POST">
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
            <input type="hidden" name="sysSetting.settingCode" value="${(sysSetting.settingCode)!}"/>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Name:</label>
                        <div class="frm_controls">
                            <span class="frm_val">${(sysSetting.settingTitle)!}</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Value:</label>
                        <div class="frm_controls">
                            <#if sysSetting.settingType == "5">
								<@ctl.text name="sysSetting.settingValue" value="${(sysSetting.settingValue)!}" validate={"maxlength":"100"} required=true fieldName="${(sysSetting.settingTitle)!}"/>
                            <#elseif sysSetting.settingType == "1">
								<@ctl.text name="sysSetting.settingValue" value="${(sysSetting.settingValue)!}" counter="%" validate={"max":"100","integerOrFloat2":"true"} required=true fieldName="${(sysSetting.settingTitle)!}"/>
							<#elseif sysSetting.settingType == "2">
								<@ctl.text name="sysSetting.settingValue" value="${(sysSetting.settingValue)!}" counter="天" validate={"max":"10000","numericCharts":"true"} required=true fieldName="${(sysSetting.settingTitle)!}"/>
							<#elseif sysSetting.settingType == "3">
								<@ctl.text name="sysSetting.settingValue" value="${(sysSetting.settingValue)!}" counter="dollar" validate={"max":"100000","numericCharts":"true"} required=true fieldName="${(sysSetting.settingTitle)!}"/>
							<#elseif sysSetting.settingType == "4">
								<@ctl.text name="sysSetting.settingValue" value="${(sysSetting.settingValue)!}" validate={"max":"10000","numericCharts":"true"} required=true fieldName="${(sysSetting.settingTitle)!}"/>
							<#elseif sysSetting.settingType == "7">
								<@ctl.text name="sysSetting.settingValue" value="${(sysSetting.settingValue)!}"counter="hour" validate={"max":"59","integerOrFloat2":"true"} required=true fieldName="${(sysSetting.settingTitle)!}"/>
							</#if>
                        </div>
                    </td>
                </tr>
                 <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Remark:</label>
                        <div class="frm_controls">
                            <@ctl.text name="sysSetting.remark" value="${(sysSetting.remark)!}" validate={"maxlength":"100"}/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="form_btn" id="prepayment_apply">
            <@ctl.submit class="btn btn_primary" text="save"/>
        	<@ctl.button id="cancle" class="btn btn_primary" text="cancel"/>
        </div>
    </form>
<script type="text/javascript">
    $(function(){
        $("#cancle").on("click",function(){
           $.fancybox.close();
        });
    });
</script>