<@cl.html title="内容管理_栏目内容管理_文章管理">
<div class="col_main">
     <div class="main_hd" id="main_hd">
    	<div class="clearfix">
            <h2><@ctl.linkButton href="articleEdit.htm?categoryId=${categoryId}&articleLoadType=1" float="fr" text="add" />Article Manage</h2>
        </div> 
     </div>
    <div class="main_bd">
    <form id="searchForm" action="${taglibs.ctx}/content/category/articleIndex.htm" method="POST">
    	<div class="search_form">
        	<div class="search_list clearfix">
                        <div class="sl_li">
                           <label class="frm_label">Title:</label>
	                        <div class="frm_controls">
	                            <@ctl.text name="criteria.title" value=criteria.title />
	                        </div>
                        </div>
                        <div class="sl_li">
                            <label class="frm_label">Parent Category:</label>
                            <div class="frm_controls group belongto-part">
                                 <@ctl.dropdownlist id="firstCategory" name="criteria.parentCategory" value=criteria.parentCategory listData=firstCategoryMap hasAll=true />
                                 <span class="frm_val">/</span>
                                 <@ctl.dropdownlist id="secondCategory" name="criteria.category" value=criteria.category listData=secondCategoryMap hasAll=true />
                            </div>
                        </div>
                        <div class="sl_li sl_btn">
                            <@ctl.submit text="search" />
	                        <input type="hidden" name="categoryId" value="${categoryId}" />
                        </div>
            </div>
        </div>
        <div class="main_list">
            <div class="nowrap_table">
                <table class="table table-striped table-hover" id="tabArticleView" cellpadding="0" cellspacing="0">
                    <thead class="thead">
                        <tr>
                            <th class="td_checkbox tl">
                            	<div class="uniformjs">
                                	<label class="checkbox">
                                    	<input type="checkbox" class="checkbox" id="checkboxAll" /> All
                                    </label>
                                </div>
                            </th>
                            <th class="table_cell tl">Order</th>
                            <th class="table_cell tl">Title</th>
                            <th class="table_cell tl">Status</th>
                            <th class="table_cell tr">Operate</th>
                        </tr>
                    </thead>
                    <tbody class="tbody" id="">
                    	<#assign i=0 /><#-- 定义排序对象，用户将当前记录的ID和orderNum，赋值到同一个Article实体中 -->
                    	<#if articleList?has_content>
                    	<#list articleList as item>
                        <tr class="">
                            <td class=" table_cell">
                            	<div class="uniformjs">
                                	<label class="checkbox">
                                    	<input type="checkbox" class="checkbox" name="checkboxList" value="${item.id}" />
                                    </label>
                                </div>
                            </td>
                            <td class="table_cell">
                                <div class="orderby_input">
                               		<@ctl.text class="frm_input frm_input_num" name="articleList[${i}].orderNum" value="${item.orderNum}" />
                               		<input type="hidden" name="articleList[${i}].id" value="${item.id}" />
                                </div>
                            </td>
                            <td class=" table_cell">${item.title}</td>
                            <td class=" table_cell "><@ctl.statusButton status="${item.status}" isView=true /></td>
                            <td class=" table_cell tr">
                            	<input type="hidden" value="${item.id}" />
                            	<@ctl.operateButton href="articleEdit.htm?categoryId=${categoryId}&articleLoadType=2&articleId=${item.id}" title="modify" text="modify" />
                                <@ctl.delButton href="okSubmitArticleDelete.htm?articleId=${item.id}" title="delete" text="delete" />
                            </td>
                        </tr>
                        <#assign i=i+1 />
                        </#list>
                        <#else>
						  <tr><td colspan="5">No data</td></tr>
						</#if>
                    </tbody>
                </table>
            </div>
            
            <#if articleList?has_content>
            <div class="main_list_opera">
                <div class="form_btn">
	                <@ctl.linkButton class="btn" href="#delete_btn" text="delete" isFancybox=true />
	                <@ctl.button id="btnSaveOrderNum" class="btn" text="save order" />
                </div>
                <div class="pagination_wrp">
	                <@ctl.page page=criteria.page>
	                </@ctl.page>
                </div>
            </div>
            </#if>
        </div>
        </form>
    </div>
</div>
<!--- delete submit -->
<div class="confirm_pop_s fancybox_pop" id="delete_btn">
	<p class="tc ptb20">Comfirm delete？</p>
    <p class="form_btn">
    	<@ctl.button id="btnOkSubmitBatchDel" text="confirm" />
    	<@ctl.button id="btnCancel" class="btn" text="cancel" />
    </p>
</div>

<script type="text/javascript">
			//排序只能输入数字
			$(".frm_input_num").onlynum();
			
			//删除fancybox取消功能
			$("#btnCancel").click(function(){
				$.fancybox.close();
			});

			//批量删除方法
			$("#btnOkSubmitBatchDel").click(function(){
					$.ajax({
			            type: "POST",
			            url: "okSubmitArticleBatchDel.htm",
			            dataType: "json",
			            data:$("form#searchForm").serialize(),
			            error: function (data, transport) {
			               alert("删除失败");
			            },
			            success: function (result) {
				            alert("delete"+result.retMes+" data");
				            $("#searchForm").submit();
			            }
			        });
			        $.fancybox.close();
			});
			
			//全选/反选
			$("#checkboxAll").click(function(){ 
				if($(".checkbox div span").attr("class") == 'valid checked') {
					$("input[name='checkboxList']").prop("checked",false); 
					$(".checker span").removeAttr("class");
				} else {
					$("input[name='checkboxList']").prop("checked",true); 
					$(".checker span").attr("class","valid checked");
				}
			});
			
			//保存排序方法
			$("#btnSaveOrderNum").click(function(){
				$.ajax({
			            type: "POST",
			            url: "saveOrderNumArticle.htm",
			            dataType: "json",
			            data: $("form#searchForm").serialize(),
			            error: function (data, transport) {
			               alert("save error");
			            },
			            success: function (result) {
			            	if(result.retMes=="-1"){
			            		alert("排序为空，更新失败");
			            	}else if(result.retMes>0) {
			            		alert("save success");	
			            		$("#searchForm").submit();
			            	} else {
			            		alert("save error");
			            	}
			            }
			        });
			});
			
			//一级栏目级联 change方法
			$("#firstCategory").change(function(){
				var firSelected = $("#firstCategory  option:selected").val();
				
				 $("#secondCategory").empty();	//添加前清空select值
				 $("#secondCategory").append("<option value=''>All</option>");
				 $("#secondCategory").siblings(".bootstrap-select").find(".filter-option").text("All");
				
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
			          	 $.each(result, function(key,value){
			          	 		$("#secondCategory").append("<option value=" + key + ">" + value + "</option>");
							});
			            }
			        });
				}
			});
</script>
</@cl.html>