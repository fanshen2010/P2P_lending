<@cl.html title="内容管理_栏目内容管理_文章管理_编辑文章">
<div class="col_main">
    <div class="main_hd">
    	<div class="clearfix">
            <h2><a class="fr btn btn_primary" href="articleIndex.htm" title="">return</a> ${articleEditMenu}</h2>
        </div> 
     </div>
    <div class="main_bd">
        <div class="form_wrp">
			<form class="validate_form" id="crticleForm" action="okSubmitArticleEdit.htm" enctype="multipart/form-data" method="POST">
            <table class="table" cellpadding="0" cellspacing="0">
                <tbody class="tbody" id="">
                	<tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label" for="current">Parent Category:</label>
                            <div class="frm_controls"> 
                                 <@ctl.dropdownlist id="firstCategory" listData=firstCategoryMap hasChoice=true value="${parentId}" />
                                 <span class="frm_val">/</span>
                                 <@ctl.dropdownlist id="secondCategory" listData=secondCategoryMap name="article.category" value="${article.category}" hasChoice=true required=true fieldName="Parent Category"/>
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label for="title" class="frm_label">Title:</label>
                            <div class="frm_controls">
                                <@ctl.text name="article.title" value="${(article.title)!}" required=true validate={"maxlength":"32"} placeholder="max length is 32" fieldName="Title"/>
                                <input type="hidden" name="articleLoadType" value="${(articleLoadType)!}" />
                                <input type="hidden" name="article.id" value="${(article.id)!}" />
                            </div>	
                        </td>
                    </tr>
                     <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Article source:</label>
                            <div class="frm_controls">
                                <@ctl.text name="article.articleSource" value="${(article.articleSource)!}" validate={"maxlength":"30"} placeholder="max length is 30" />
                            </div>	
                        </td>
                    </tr>
                    <#--
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Title Pic:</label>
                            <div class="frm_controls">
                                <@ctl.fileUpload fileUploaded=defultUf />
                            </div>	
                        </td>
                    </tr>
                    -->
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label for="keyword" class="frm_label">Seo keywords:</label>
                            <div class="frm_controls">
                                <@ctl.text name="article.seoKeywords" value="${(article.seoKeywords)!}" validate={"maxlength":"32"} placeholder="max length is 32" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label for="description" class="frm_label">Seo description:</label>
                            <div class="frm_controls">
                                <@ctl.text name="article.seoDescription" value="${(article.seoDescription)!}" validate={"maxlength":"64"} placeholder="max length is 64" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Article status:</label>
                            <div class="frm_controls">
                            	<@ctl.radiobuttonlist spanClass="frm_radio_box uniformjs supplement_info_radio" groupCode="status" dataType="BizCode"  name="article.status" value="${(article.status)!}" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Recommend:</label>
                            <div class="frm_controls">
                                <@ctl.radiobuttonlist spanClass="frm_radio_box uniformjs supplement_info_radio" groupCode="ifExport" dataType="BizCode" name="article.recommendFlag" value="${(article.recommendFlag)!}" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Post Time:</label>
                            <div class="frm_controls">
                            	<@ctl.datetimepicker name="article.postAt"  value=(article.postAt)! required=true validate={"dateTimeFormat24":"true"} />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Order:</label>
                            <div class="frm_controls">
                                <@ctl.text name="article.orderNum" value="${(article.orderNum)!}" validate={"positiveInteger":"true","max","999"} placeholder="Please fill in 0~1000 integer" />
                                <#--<span class="frm_input_prompt">默认99，如需置顶请填写1~98数字</span>-->
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Content:</label>
                            <div class="frm_controls">
                            	<@ctl.editor id="content" name="article.content" value="${(article.content)!}" />
                            </div>	
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="form_btn">
            	<@ctl.submit id="btnOkSubmit" text="save" />
            </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
	//一级栏目级联change事件 
	$("#firstCategory").change(function(){
		var firSelected = $("#firstCategory  option:selected").val();
		
		$("#secondCategory").empty();	//添加前清空select值
			 $("#secondCategory").append("<option value=''>select</option>");
			 $("#secondCategory").siblings(".bootstrap-select").find(".filter-option").text("select");
		
		if(firSelected!=""){
			 $.ajax({
	            type: "POST",
	            url: "loadSecondCategoryMap.htm",
	            dataType: "json",
	            data: {parentId:firSelected},
	            error: function (data, transport) {
	            	alert("error");
	            },
	            success: function (result) {
	            $("#secondCategory").empty();	//添加前清空select值
	            $("#secondCategory").append("<option value=''>select</option>");
	          	 $.each(result, function(key,value){
	          	 		$("#secondCategory").append("<option value=" + key + ">" + value + "</option>");
					});
	            }
	        });
		}
	});
</script>
</@cl.html>