<#macro fileUpload
	name="file" fileUploaded="" required=false validate={} fieldName="" isfancybox=false formId=""
>
<#if fileUploaded?? && fileUploaded!="" && isfancybox==false>
<div class="frm_val">
    <a href="${(fileUploaded.fileUrlOriginal)!}" class="fancybox" title="${(fileUploaded.fileName)!}">${(fileUploaded.fileName)!}</a>
    <input type="hidden" name="${name}Id" value="${(fileUploaded.id)!}" />
    <a href="javascript:;" class="btn btn_primary dev_file_edit">modify</a>
</div>
<#elseif fileUploaded?? && fileUploaded!="" && isfancybox==true>
<div class="frm_val">
    <span class="fileupload-preview">${(fileUploaded.fileName)!}</span>
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
            <input type="file" name="${name}" data-name="${name}"<#rt>
            <#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl"><#rt>
            />
        </span>
        <a href="javascript:;" class="btn fileupload-exists" data-dismiss="fileupload" title="deletePic">delete</a>
    </div>
</div>
<#include "${taglibs.ftlctx}/common/control/required_star.ftl">
 <#if isfancybox==true>
		<div class="filecallback-img divFileImg" style="<#if fileUploaded?? && fileUploaded!=""><#else>display:none</#if>">
			<img class="link_img" height="120" width="120" src="<#if fileUploaded?? && fileUploaded!="">${fileUploaded.fileUrlThumb}</#if>" />
		</div>
		<script type="text/javascript">
			$(".fileupload-exists").click(function(){
				if($(this).attr("title")=="deletePic"){
					$(".divFileImg").hide();
				}
			});
			$(':file').change(function(){
					    //your validation
					    var formData = new FormData(document.getElementById("${formId}"));
					    $.ajax({
					        url : webName + '/fileUpload.htm',  //server script to process data
					        type: 'POST',
					        // Form数据
					        data: formData,
					        dataType : 'json',
					        success: function(result){
					     	   $(".link_img").attr("src",result.fileUrlThumb);
					     	   $(".divFileImg").show();
					        },
					        error: function(e){
					        },
					        //Options to tell JQuery not to process data or worry about content-type
					        cache: false,
					        contentType: false,
					        processData: false
					    });
					});
		</script>
	</#if>
</#macro>