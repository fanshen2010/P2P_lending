<@cl.html title="前台栏目管理">
<div class="col_main">
	<div class="main_hd" id="main_hd">
		<div class="clearfix">
	        <h2><@ctl.linkButton href="categoryEdit.htm?categoryLoadType=1" title="add" text="add" />Category Manage</h2>
	    </div> 
	</div>
	<div class="main_bd">
		<div class="main_list">
			<form id="searchForm" method="POST">
	    	<div class="nowrap_table">
	        	<table id="tabList" class="table">
	            	<thead class="thead">
	                    <tr>
	                    	<th class="table_cell tl">Order</th>
	                        <th class="table_cell tl">Category name</th>
	                        <th class="table_cell tl">Category type</th>
	                        <th class="table_cell tl">Category code</th>
	                        <th class="table_cell tl">Status</th>
	                        <th class="table_cell tr">Operate</th>
	                    </tr>
	                </thead>
	                <tbody class="tbody" id="">
	                <#assign i=0 /><#-- 定义排序对象，用户将当前记录的ID和orderNum，赋值到同一个Article实体中 -->
	                <#list categoryList as item>
	                <#if !item.parentCategory?? || item.parentCategory=="">
	                <tr class="level_first" id="level${item.id}" data-status="open">
	                	<td class="table_cell">
	                    	<div class="orderby_input">
	                            <@ctl.text class="frm_input" name="categoryList[${i}].orderNum" value="${item.orderNum}" />
	                             <input type="hidden" name="categoryList[${i}].id" value="${item.id}" />
	                        </div>
	                    </td>
	                    <td class=" table_cell">
	                    	<div class="menu_tit">
	                            <span class="menu_toggle open"></span>
	                            <span class="menu_txt">${item.title}</span>
	                            <a class="menu_add" href="categoryEdit.htm?categoryLoadType=1&parentId=${item.id}&parentName=${item.title}" title="添加栏目"></a>
	                            <input type="hidden" value="${item.title}" />
	                        </div>
	                    </td>
	                    <td class=" table_cell"><@bizTag.pageType type="${item.categoryType}" /></td>	
	                    <td class=" table_cell">${item.categoryCode}</td>
	                    <td class=" table_cell"><@ctl.statusButton status="${item.status}" isView=true /></td>
	                    <td class=" table_cell tr">
	                        <@ctl.operateButton href="categoryEdit.htm?categoryLoadType=2&categoryId=${item.id}" text="modify" />
	                        <@ctl.delButton href="okSubmitcategoryDelete.htm?categoryId=${item.id}" title="delete" text="delete" />
	                    </td>
	                </tr>
	                <#else>
	                	<tr class="level_second" id="level${item.parentCategory}_level${item.id}" data-status="open">
		                	<td class="table_cell">
		                    	<div class="orderby_input">
	                                <@ctl.text class="frm_input" name="categoryList[${i}].orderNum" value="${item.orderNum}" />
	                                <input type="hidden" name="categoryList[${i}].id" value="${item.id}" />
		                        </div>
		                    </td>
		                    <td class=" table_cell"><div class="menu_tit"><span class="menu_txt">${item.title}</span></div></td>
		                    <td class=" table_cell"><@bizTag.pageType type="${item.categoryType}" /></td>	
		                    <td class=" table_cell">${item.categoryCode}</td>
		                    <td class=" table_cell"><@ctl.statusButton status="${item.status}" isView=true /></td>
		                    <td class=" table_cell tr">
		                    	<@ctl.operateButton href="categoryEdit.htm?categoryLoadType=2&categoryId=${item.id}&parentId=${item.parentCategory}" text="modify" />
                                <@ctl.delButton href="okSubmitcategoryDelete.htm?categoryId=${item.id}" title="delete" text="delete" />
		                    </td>
		                </tr>
	                </#if>
	                <#assign i=i+1 />
	                </#list>
	            	</tbody>
	         	</table>
	        </div>
	        <div class="main_list_opera">
	            <div class="form_btn">
	            	<@ctl.button id="btnSaveOrderNum" class="btn" text="save order" />
	            </div>
	        </div>
	        </form>
	    </div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		// 一级菜单点击展开伸缩 菜单添加
		$(".level_first .menu_toggle").click(function(){
			var parents = $(this).parents(".level_first");
			var num = parents.attr("id");
			var data = parents.data("status");
			if(data=="open"){
				$("[id^=" +num+ "_level]").fadeOut();
				parents.data("status","close");	
				$(this).removeClass("open");	
			}else if(data=="close"){
				$("[id^=" +num+ "_level].level_second").each(function(){
					var secondLevel = $(this).attr("id")
					$(this).fadeIn();
					if($(this).data("status") == "open"){
						$("[id^=" + secondLevel + "_level]").fadeIn();
					}	
				});
				parents.data("status","open");		
				$(this).addClass("open");	
			}
		});
	});
	//保存排序提交方法	
	$("#btnSaveOrderNum").click(function(){
		$.ajax({
	            type: "POST",
	            url: "saveOrderNumCategory.htm",
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
	            		location.replace(location);
	            	} else {
	            		alert("save error");
	            	}
	          	 	
	            }
	        });
	});
	
	//设置排序只可输入数字
	$(".frm_input").onlynum();
	
</script>
</@cl.html>