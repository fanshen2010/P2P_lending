<div class="tab-bd-con hide">
<#list sysSettingDto.settingLists as settings>
    <div class="item">
        <div class="item_hd">
            <strong class="item_hd_title">${sysSettingDto.settingListName[settings_index]}</strong>
        </div>
        <div class="item_bd ">
            <div class="form_wrp">
                <table id="tabPartner_info_cost" class="table form_wrp_padding  table-hover" cellpadding="0" cellspacing="0">
                    <thead class="thead">
                        <tr>
                            <th class="table_cell tl">No</th>
                            <th class="table_cell tl size1of3">Name</th>
                            <th class="table_cell tl">Value</th>
                            <th class="table_cell tl">Remark</th>
                            <th class="table_cell tr">Operate</th>
                        </tr>
                    </thead>
                    <tbody class="tbody" id="">
                    	<#list settings as setting>
	                        <tr>
	                            <td class="table_cell">${setting_index+1}</td>
	                            <td id="title" class="table_cell">${setting.settingTitle}</td>
	                            <td class="table_cell">${setting.settingValue}
		                            <#if setting.settingType == "1">%
		                            <#elseif setting.settingType == "2">天
									<#elseif setting.settingType == "3">dollar
									<#elseif setting.settingType == "7">hour
									<#else>
									</#if>
								</td>
	                            <td class="table_cell tl">${setting.remark}</td>
	                            <td class="table_cell tr">
	                            	<a name="edit" href="javascript:void(0);" data-code="${setting.settingCode}" title="modify" class="operate_icon edit fancybox">modify</a>
	                            </td>
	                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
        </#list> 
		<div class="item instruction_configuration">
            <div class="item_hd">
                <strong class="fl item_hd_title">项目说明书配置</strong>
                <a class="fr fancybox" title="项目说明书配置修改" href="#project_info_update">modify</a>
            </div>
            <div class="item_bd ">
                <p class="text">${(sysSettingDto.sysSettingClob.settingValue)!}</p>
            </div>
        </div>
    </div>
<!-- 项目说明书预设 -->
<div class="popsize_l fancybox_pop " id="project_info_update">
    <form action="${taglibs.allctx}/system/setting/updateManual.htm" method="POST">
        <input type="hidden" name="settingClob.id" value="${(sysSettingDto.sysSettingClob.id)!}"/>
        <table class="table" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Name:</label>
                        <div class="frm_controls">
                            <span class="frm_val">项目说明书预设</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="size1of1" colspan="2">
                        <label class="frm_label">Value</label>
                        <div class="frm_controls">
                            <@ctl.textarea name="settingClob.settingValue" value="${(sysSettingDto.sysSettingClob.settingValue)!}" validate={"maxlength":"1000"}/>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="form_btn" id="prepayment_apply">
            <@ctl.submit title="save" text="save"/>
            <@ctl.linkButton href="javascript:;" id="btnCancel" title="cancel" text="cancel"/>
        </div>
    </form>
</div>
<div class="popsize_l fancybox_pop " id="editct"></div>
<script type="text/javascript">
$(document).ready(function(){
	$("a[name='edit']").on("click",function(){
           var title = $(this).parent("td").siblings("td#title").text() + 'modify';
           $.ajaxLoadFtl({
                type: "POST",
                dataType: "html",
                url: "${taglibs.allctx}/system/setting/editBusiness.htm",
                data:{
                    "sysSetting.settingCode":$(this).data("code"),
                 },
                error: function (data, transport) {
                },
                success: function (response) {
                   $("#editct").html(response);
                   $.fancybox.open($("#editct"),{"title":title});
                }
            });
         });
	$("#btnCancel").click(function(){
		$.fancybox.close();
	});
});
</script>
