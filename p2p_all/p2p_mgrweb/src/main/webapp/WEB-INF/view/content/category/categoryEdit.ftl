<@cl.html title="前台栏目管理">
<div class="col_main">
    <div class="main_hd">
    	<div class="clearfix">
            <h2><a class="fr btn btn_primary" href="categoryIndex.htm" title="">return</a> Add Category</h2>
        </div> 
     </div>
    <div class="main_bd">
        <div class="form_wrp">
			<form class="validate_form" id="categoryForm" action="okSubmitCategoryEdit.htm" method="post">
            <table class="table" cellpadding="0" cellspacing="0">
                <tbody class="tbody" id="">
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label for="title" class="frm_label">Category name:</label>
                            <div class="frm_controls">
                               <@ctl.text name="category.title" value="${(category.title)!}" required=true validate={"maxlength":"16"} placeholder="max length is 16" fieldName="Category name"/>
                               <input type="hidden" name="categoryLoadType" value="${categoryLoadType}" />
                               <input type="hidden" name="category.id" value="${(category.id)!}" />
                            </div>	
                        </td>
                    </tr>
                	<tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label" for="current">Category code:</label>
                            <div class="frm_controls"> 
                                <@ctl.text name="category.categoryCode" value="${(category.categoryCode)!}" required=true validate={"remote":"categoryCodeCheck.htm?category.id=${(category.id)!}"} fieldName="Category code"/>
                            </div>	
                        </td>
                    </tr>
                	<tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label" for="current">Parent category:</label>
                            <div class="frm_controls"> 
                            	<#if categoryLoadType=2 && parentId??>
                            		<@ctl.dropdownlist listData=firstCategoryMap name="category.parentCategory" value="${category.parentCategory}" />
                            	<#else>
                            		<span class="menu_txt">${parentName}</span>
	                                <input type="hidden" name="category.parentCategory" value="${parentId}" />
                            	</#if>
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label for="keyword" class="frm_label">Seo keywords:</label>
                            <div class="frm_controls">
                               <@ctl.text name="category.seoKeywords" value="${(category.seoKeywords)!}" validate={"maxlength":"32"} placeholder="max length is 32" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label for="description" class="frm_label">Seo description:</label>
                            <div class="frm_controls">
                               <@ctl.text name="category.seoDescription" value="${(category.seoDescription)!}" validate={"maxlength":"64"} placeholder="max length is 64" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Status:</label>
                            <div class="frm_controls">
                                <@ctl.radiobuttonlist spanClass="frm_radio_box uniformjs supplement_info_radio" groupCode="status" dataType="BizCode" name="category.status" value="${category.status}" />
                            </div>	
                        </td>
                    </tr>
                    <tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Order:</label>
                            <div class="frm_controls">
                                <@ctl.text name="category.orderNum" value="${(category.orderNum)!}" required=true validate={"positiveInteger":"true","max","999"} placeholder="Please fill in 0~1000 integer" />
                                <#--<span class="frm_input_prompt">默认99，如需置顶请填写1~98数字</span>-->
                            </div>	
                        </td>
                    </tr>
                	<tr>
                        <td class="size1of1" colspan="2">
                            <label class="frm_label" for="current">Category type:</label>
                            <div class="frm_controls"> 
                                 <@ctl.dropdownlist id="model_style" name="category.categoryType" value="${(category.categoryType)!}" required=true hasChoice=true groupCode="pageType" dataType="BizCode" fieldName="Category type"/>
                            </div>	
                        </td>
                    </tr>
                    <tr class="pagestyle_link_adr hide">
                        <td class="size1of1" colspan="2">
                            <label class="frm_label" for="current">Target url:</label>
                            <div class="frm_controls"> 
                            	<@ctl.text name="category.targetUrl" value="${(category.targetUrl)!}" required=true fieldName="Target url"/>
                            </div>	
                        </td>
                    </tr>
                	<tr class="pagestyle_link_style hide">
                        <td class="size1of1" colspan="2">
                            <label class="frm_label" for="current">Target way:</label>
                            <div class="frm_controls"> 
                            <@ctl.dropdownlist name="category.target" value="${(category.target)!}" required=true hasChoice=true groupCode="windows" dataType="BizCode" fieldName="Target way"/>
                            </div>	
                        </td>
                    </tr>
                    <tr  class="pagestyle_cont hide">
                        <td class="size1of1" colspan="2">
                            <label class="frm_label">Content:</label>
                            <div class="frm_controls">
                          		<@ctl.editor id="content" name="category.content" value="${(category.content)!}" />
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
	$("#model_style").change(function(){
				var selecttext = $(this).find("option:selected").val();
				if(selecttext=="0"){
					$(".pagestyle_link_style").removeClass("hide").show();
					$(".pagestyle_cont").removeClass("hide").show();
					$(".pagestyle_link_adr").hide();	
				}else if(selecttext=="1"){
					$(".pagestyle_link_style").removeClass("hide").show();
					$(".pagestyle_cont").hide();
					$(".pagestyle_link_adr").hide();
				}else if(selecttext=="2"){
					$(".pagestyle_link_style").hide();
					$(".pagestyle_cont").hide();
					$(".pagestyle_link_adr").removeClass("hide").show();	
				}
			});
			
	$(document).ready(function(){ 
	　　$("#model_style").change();
	});
</script>
</@cl.html>