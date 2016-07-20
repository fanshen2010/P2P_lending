<#macro fileUpload
	name="file" fileUploaded=""
>
<#if fileUploaded?? && fileUploaded!="">
<div class="frm_val">
    <a href="${fileUploaded.fileUrlBig!(fileUploaded.fileUrlOriginal)}" class="fancybox" title="${(fileUploaded.fileName)!}">${(fileUploaded.fileName)!}</a>
    <input type="hidden" name="${name}Id" value="${(fileUploaded.id)!}" />
    <a href="javascript:;" class="btn btn_primary dev_file_edit">modify</a>
</div>
</#if>

<div class="fileupload fileupload-new <#if fileUploaded?? && fileUploaded!="">dn</#if>" data-provides="fileupload">
    <div class="input-append">
        <div class="uneditable-input"> 
            <span class="fileupload-preview"></span>
        </div>
        <span class="btn btn-file">
            <span class="fileupload-new">选择文件</span>
            <span class="fileupload-exists">换一下</span>
            <input type="file" name="${name}">
        </span>
        <a href="javascript:;" class="btn fileupload-exists" data-dismiss="fileupload">delete</a>
    </div>
</div>
</#macro>