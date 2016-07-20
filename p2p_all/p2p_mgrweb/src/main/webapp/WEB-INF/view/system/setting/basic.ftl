<div class="tab-bd-con active">
<form action="${taglibs.allctx}/system/setting/updateBasic.htm" method="POST" enctype="multipart/form-data" id="searchForm">
	<div class="form_wrp">
        <table class="table">
            <tbody>
        		<tr>
                    <td class="size1of1" colspan="2">
                    <#list sysSettingDto.proLogoUploadFile as file>
	                    <label class="frm_label">${sysSettingDto.picSettingName[file_index]}:</label>
	                    <div class="frm_controls">
	                        <input type="hidden" name="picSettingCodes" value="${sysSettingDto.picSettingCode[file_index]}"/>
	                        <@ctl.fileUpload name="fileDtos[${file_index}].file" fileUploaded=file validate={"imageAccept":"[.bmp,.png,.jpeg, .jpg,.gif]"}/>
	                    </div>
                    </#list>
                    </td>
                </tr>
                <#list sysSettingDto.basicSetting as basic>
	                <tr>
	                    <td class="size1of1" colspan="2">
	                        <label class="frm_label">${basic.settingTitle}:</label>
	                        <div class="frm_controls">
	                            <span class="frm_input_box medium_xxl">
	                            	<input type="hidden" name="settingCode" value="${basic.settingCode}"/>
	                                <input type="text" name="settingValue" value="${basic.settingValue}" class="long frm_input" data-rule-maxlength="100">
	                            </span>
	                            <span class="frm_input_prompt">${basic.settingCode}</span>
	                        </div>
	                    </td>
	                </tr>
                </#list>
            </tbody>
        </table>
        <div class="form_btn">
            <@ctl.submit class="btn btn_primary" text="save"/>
        </div>
    </div>
</form>    
</div>