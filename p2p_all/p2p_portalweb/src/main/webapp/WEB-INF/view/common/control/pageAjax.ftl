<#--
ajax分页标签
-->
<#macro pageAjax page url="" css="pagination-centered" elemId="investRecord" hideElemPre="criteria" formId="">
<#-- 自定义的分页指令 
    属性:
   pageNo      当前页号(int类型)
   pageSize    每页要显示的记录数(int类型)
   toURL       点击分页标签时要跳转到的目标URL(string类型)
   recordCount 总记录数(int类型)
 -->
<#-- 定义局部变量pageCount保存总页数   -->
    <#assign  pageCount=(page.totalPages)?int>
    <#assign pageNo=page.currentPage?int>
    <#if pageCount gt 0>
    <div class="pagination ${css}">
    	<input type="hidden" id="currentPage" name="${hideElemPre}.page.currentPage" value="${page.pageNo!1}"/>
        <ul>
            		<#-- 页号越界处理 -->
			<#if (pageNo gt pageCount)>
			    <#assign pageNo=pageCount/>
			</#if>
			<#if (pageNo lt 1)>
			    <#assign pageNo=1/>
			</#if>
			<#-- 上一页处理 -->
			<#if (pageNo == 1)>
				<li class="disabled"><a>&lt;</a></li>
			<#else>
				<li><a href="javascript:turnOverPage(${pageNo - 1})">&lt;</a></li>
			  </#if>
			<#-- 如果前面页数过多,显示... -->
			<#assign start=1/>
			<#if (pageNo gt 3)>
			    <#assign start=(pageNo - 1)/>
			    <li><a href="javascript:turnOverPage(1)">1</a></li>
				<li><a href="javascript:turnOverPage(2)">2</a></li>
				<li><span>&hellip;</span></li>
			</#if>
			<#-- 显示当前页号和它附近的页号 -->
			<#assign end=(pageNo + 1)>
			<#if (end > pageCount)>
				<#assign end=pageCount>
			</#if>
			<#if recordCount==0>
			 	<li class="active"><a>1</a></li>
			<#else>
				<#list start..end as i>
				    <#if (pageNo==i)>
						<li class="active"><a >${i}</a></li>
				  	<#else>
						<li><a href="javascript:turnOverPage(${i})">${i}</a> </li>     
				    </#if>
				</#list>
			</#if>
			<#-- 如果后面页数过多,显示... -->
			<#if (end lt pageCount - 2)>
				<li><span>&hellip;</span></li>
			</#if>
			<#if (end lt pageCount - 1)>
				<li><a href="javascript:turnOverPage(${pageCount - 1})">${pageCount-1}</a></li>
			</#if>
			<#if (end lt pageCount)>
				<li><a href="javascript:turnOverPage(${pageCount})">${pageCount}</a></li>
			</#if>
			<#-- 下一页处理 -->
			<#if (pageNo == pageCount)||(pageCount==0)>
				<li class="disabled"><a>&gt;</a></li>
			<#else>
				<li><a href="javascript:turnOverPage(${pageNo + 1})">&gt;</a></li>
			</#if>
        </ul>
    </div>
    </#if>
	<script language="javascript">
	var turnOverPage=function(pageNo) {
		$("#currentPage").val(pageNo);
		$.ajax({
			type : "POST",
			url : "${url}",
			dataType : "json",
			data : $("#${formId}").serialize(),
			success : function(json) {
				$("#${elemId}").replaceWith(json.html);
			}
		});
	}
	</script>
</#macro>