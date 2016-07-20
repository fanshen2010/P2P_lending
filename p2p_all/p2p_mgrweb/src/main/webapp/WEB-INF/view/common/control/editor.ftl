<#--
富文本编辑器
<@ctl.editor id name class title tabindex value required validate />
-->

<#macro editor
	id name="" class="frm_textarea" title="" tabindex="" value="" required=false validate={} fieldName="" >
	<@ctl.textarea id=id name=name class=class title=title tabindex=tabindex value=value required=required validate=validate idEditor=true fieldName=fieldName />
	<script type="text/javascript">CKEDITOR.replace("${id!}");</script>
</#macro>
