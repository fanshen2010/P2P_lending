<#--
ajax分页标签
-->
<#macro pageAjax page url="" css="" elemId="">
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
    <div class="main_list_opera">
        <div class="pagination_wrp">
            <input type="hidden" id="currentPage" name="criteria.page.currentPage" value="${page.pageNo!1}"/>
            
            <#nested>

            <#-- 页号越界处理 -->
            <#if (pageNo gt pageCount)>
                <#assign pageNo=pageCount/>
            </#if>
            <#if (pageNo lt 1)>
                <#assign pageNo=1/>
            </#if>
            
            <div class="pagination">
                <span class="page_nav_area">
		        <#-- 输出分页表单 -->
		        <#-- 把请求中的所有参数当作隐藏表单域(无法解决一个参数对应多个值的情况) -->
		        <#-- 上一页处理 -->
		            <#if (pageNo == 1)>
		               <a href="" class="btn page_prev" style="display:none"><i class="arrow"></i></a>
		            <#else>
		                <a href="javascript:turnOverPage(${pageNo - 1})" class="btn page_prev"><i class="arrow"></i></a>
		            </#if>
                    
                    <span class="page_num">
                        <label>${pageNo}</label>
                        <span class="num_gap">/</span>
                        <label>${pageCount}</label>
                    </span>
		            <#-- 下一页处理 -->
		            <#if (pageNo == pageCount)||(pageCount==0)>
		                <a href="" class="btn page_next" style="display:none"><i class="arrow"></i></a>
		            <#else>
		                <a href="javascript:turnOverPage(${pageNo + 1})" class="btn page_next"><i class="arrow"></i></a>
		            </#if>            
                </span>
                <span class="goto_area">
                    <input type="text" id="goPage" value="" />
                    <a href="javascript:turnOverPage($('#goPage').val());" class="btn page_go">go</a>
                </span>
            </div>
        </div>
     </div>
    </#if>
<script language="javascript">
var turnOverPage=function(pageNo) {
	var pageTotal = $(".page_num label:eq(1)").text();
	if(pageTotal < pageNo){
		pageNo = pageTotal;
	}
	$("#currentPage").val(pageNo);
	var formData=$("form").serialize();
	$.ajaxLoadFtl({
			type : "POST",
			url : "${url}",
			dataType : "json",
			data : formData,
			success : function(json) {
				$("#${elemId}").html(json.html);
			}
		});
}
</script>
</#macro>