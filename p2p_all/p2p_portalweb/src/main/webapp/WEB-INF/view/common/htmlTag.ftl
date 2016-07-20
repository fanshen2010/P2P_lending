<#-- 文本框 -->
<#macro textfield maxlength="" readonly="" value="" ck=""
label="" noHeight="false" required="false" colspan="" width="" help="" helpPosition="2" colon=":" hasColon="true"
id="" name="" class="def_input_text" style="" size="" title="" disabled="" tabindex="" accesskey=""
vld="" equalTo="" maxlength="" minlength="" max="" min="" rname="" rvalue=""
onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
must="" tz="" validate="">
    <#if pageMode=1>
    ${value!}
    <#else>
    <input type="text" <#if id!=""> id="${id}"</#if><#rt/>
        <#if maxlength!=""> maxlength="${maxlength}"</#if><#rt/>
        <#if max?string!=""> max="${max}"</#if><#rt/>
        <#if min?string!=""> min="${min}"</#if><#rt/>
        <#if readonly!=""> readonly="${readonly}"</#if><#rt/>
        <#if rname!=""> rname="${rname}"</#if><#rt/>
        <#if rvalue!=""> rvalue="${rvalue}"</#if><#rt/>
           <#if ck!="">ck="${ck}"</#if><#rt/>
           <#if validate!="">data-validation-engine="${validate}"</#if><#rt/>
        <#if tz=="Y">
            <#if value?? && value?string!=""> value="<@s.property value="${value}"/>"</#if><#rt/>
        <#else>
            <#if value?? && value?string!=""> value="${value}"</#if><#rt/>
        </#if>
        <#include "${taglibs.ftlctx}/common/common-attributes.ftl"/><#rt/>
        <#include "${taglibs.ftlctx}/common/scripting-events.ftl"/><#rt/>

            /><#rt/>
        <#if must=="true"><font style="color:red">*</font></#if><#rt/>
    </#if>
</#macro>

<#--隐藏域-->
<#macro hidden id="" name="" ck="" value="">
<input type="hidden"<#rt/>
    <#if id!=""> id="${id}"</#if><#rt/>
    <#if name!=""> name="${name}"</#if><#rt/>
    <#if value?string!=""> value="<@s.property value="'${value}'"/>"</#if><#rt/>
       <#if ck!="">ck="${ck}"</#if><#rt/>
        />
</#macro>

<#--radiobutton-->
<#macro radio
list="" dataType="" groupCode="" keyFlag="" value="" name="" disabled="" pageMode=""
label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon=":" hasColon="true"
id=""  class="" style="" size="" title="" tabindex="" accesskey=""
onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange="" >
    <@jj_radio dataType=dataType groupCode=groupCode keyFlag=keyFlag disabled=disabled value=value name=name id=id/>


</#macro>

<#macro select
list="" dataType="" groupCode="" pid="" id="" cid="" name="" text="" value="" pvalue="" parentCode=""
multiple="" headerKey="" headerValue="" keyFlag="" ck = "" disabled="" default=""  pdefault=""
class="" style="" size="" title="" disabled="" tabindex=""  width="160px" codeIn=""
onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
tz="" validate="">
    <#if tz=="Y">
        <#assign retvalue><@s.property value="${value}" /></#assign>
        <#assign retpvalue><@s.property value="${pvalue}" /></#assign>
    <#else>
        <#assign retvalue>${value}</#assign>
        <#assign retpvalue>${pvalue}</#assign>
    </#if>
    <@jj_dropdownlist dataType=dataType groupCode=groupCode parentCode=parentCode codeIn=codeIn width=width default=default pdefault=pdefault headerKey=headerKey disabled=disabled headerValue=headerValue pid=pid id=id cid=cid name=name text=text retvalue=retvalue retpvalue=retpvalue keyFlag=keyFlag onchange=onchange ck=ck title=title class=class validate=validate/>

</#macro>

<#--标准的select  传来的数据为list-->
<#macro select_arr
list value="" multiple="" headerKey="" headerValue="" listKey="" listValue="" listDeep="" headerButtom="false"
label="" noHeight="false" required="false" colspan="" help="" helpPosition="2" colon=":" hasColon="true"
id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey="" readonly=""
vld="" tz="" class="" validate=""
onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
>
<select<#rt/>
    <#if tz=="Y">
        <#assign tvalue><@s.property value="${value}" /></#assign>
    <#else>
        <#assign tvalue    >${value}</#assign>
    </#if>
    <#if id!=""> id="${id}"</#if><#rt/>
    <#if class!=""> class="${class}"</#if><#rt/>
    <#if validate!=""> data-validation-engine="${validate}"</#if><#rt/>
    <#if multiple!=""> multiple="${multiple}"</#if><#rt/>
    <#include "${taglibs.ftlctx}/common/common-attributes.ftl"/><#rt/>
    <#include "${taglibs.ftlctx}/common/scripting-events.ftl"/><#rt/>
        ><#rt/>
    <#if headerButtom=="false">
        <#if headerKey!="" || headerValue!="">
            <option value="${headerKey}"<#if headerKey==tvalue?string>
                    selected="selected"</#if>>${headerValue}</option><#t/>
        </#if>
    </#if>
    <#if list?is_sequence>
        <#if listKey!="" && listValue!="">
            <#if listDeep!="" && list?size gt 0><#local origDeep=list[0][listDeep]+1/></#if>
            <#list list as item>
                <option value="${item[listKey]}"<#if item[listKey]?string==tvalue?string>
                        selected="selected"</#if>><#if listDeep!="" && item[listDeep] gte origDeep><#list origDeep..item[listDeep] as i>
                    &nbsp;&nbsp;</#list>></#if>${item[listValue]!}</option><#t/>
            </#list>
        <#else>
            <#list list as item>
                <option value="${item}"<#if item==tvalue> selected="selected"</#if>>${item}</option><#t/>
            </#list>
        </#if>
    <#else>
        <#list list?keys as key>
            <option value="${key}"<#if key==tvalue?string>
                    selected="selected"</#if>><@s.mt code=list[key] text=list[key]/></option><#t/>
        </#list>
    </#if>
    <#if headerButtom!="false">
        <#if headerKey!="" || headerValue!="">
            <option value="${headerKey}"<#if headerKey==value>
                    selected="selected"</#if>><@s.mt code=headerValue text=headerValue/></option><#t/>
        </#if>
    </#if>
</select>
</#macro>




<#-- 日期控件 -->
<#macro date id="" name="" value="" size="" cssClass="" readonly="true" must="" tz="" ck="" title="" bjtitle="" style="">
    <#if pageMode=1>
        <#if value!="">
        ${value}
        <#--${value?string('yyyy-MM-dd')} -->
        </#if>
    <#--<@s.date name="${value}" format="yyyy-MM-dd" />-->
    <#else>

            <input <#if size!="">size='${size}'</#if><#t/>
                   <#if style!="">style='${style}'</#if><#t/>
                   <#if name!="">name='${name}'</#if><#t/>
                   <#if id!="">id='${id}'</#if><#t/>
                   <#if ck!="">ck='${ck}'</#if><#t/>
                   <#if title!="">title='${title}'</#if><#t/>
                   <#if bjtitle!="">bjtitle='${bjtitle}'</#if><#t/>
                   class="Wdate" onclick="WdatePicker({readOnly:${readonly}})" type="text" My97Mark="false" <#t/>
        <#if tz=="Y">
                   <#if value?? && value !="">value="<@s.property value="${value}"/>"</#if> /><#t/>
        <#else>
            <#if value?? && value !="">value=${value}</#if> /><#t/>
        </#if>

        <#if must=="true"><font style="color:red">*</font></#if>

    <#--
    EasyUi时间控件，会影响共通校验的实现方式（修改EasyUi源代码，添加日期控件的清除
    <input <#if size!="">size='${size}'</#if><#t/>
           <#if name!="">name='${name}'</#if><#t/>
           <#if id!="">id='${id}'</#if><#t/>
           <#if ck!="">ck='${ck}'</#if><#t/>
           <#if title!="">title='${title}'</#if><#t/>
           class="Jdate" <#t/>
           <#if tz=="Y">
                   <#if value?? && value !="">value="<@s.property value="${value}"/>"</#if> /><#t/>
               <#else>
                   <#if value?? && value !="">value=${value}</#if> /><#t/>
           </#if>

           <#if must=="true"><font style="color:red">*</font></#if>
    -->
    </#if>
</#macro>

<#-- 日期控件 -->
<#macro dateformat value format=""  >
    <#if value??>
    ${value?string('${format}')}
    </#if>
</#macro>


<#-- 打印按钮 -->
<#-- ty 打印类型:如:设备(SB)-->
<#-- param 参数:如:(SBID)-->
<#macro printButton ty="SB" param="">
<#--<a id="printView_id" data-options="iconCls:'icon-dyyl'" class="easyui-linkbutton" ty="${ty}" param="${param}">打印预览</a>-->
<a id="print_id" data-options="iconCls:'icon-print'" class="easyui-linkbutton" ty="${ty}" param="${param}">打印</a>
</#macro>

<#-- combobox控件 -->
<#macro combobox id="" name="" value="" dataType="" groupCode="" callback="" size="" ck="" must="" keyFlag="">
    <#if pageMode=1>
    ${value!}
    <#else>
        <@jj_combobox id=id name=name ck=ck value=value dataType=dataType groupCode=groupCode callback=callback keyFlag=keyFlag />
    </#if>
</#macro>

<#--checkbox-->
<#-- TODO checkboxs的指令对应的java代码未完成 -->
<#macro checkboxs
list dataType="" groupCode="" keyFlag="" value="" name=""
label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon=":" hasColon="true"
id=""  class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange="" >

    <#if pageMode=1>
    <script>
        var tt = $('#' + '${id}').children('option:selected').text();
        $('#' + '${id}').replaceWith(tt);
    </script>
    <#elseif pageMode=2 || pageMode=3>
        <#if list ??>
            <#list list?split("|")  as item>
                <#if value != "">
                <lable><input type="checkbox" name="${name}" value="${item!}"
                              <#if value == item>checked="checked"</#if>/>${item!}</lable>
                <#else>
                <lable><input type="checkbox" name="${name}" value="${item!}"
                              <#if item_index == 0>checked="checked"</#if>/>${item!}</lable>
                </#if>
            </#list>
        <#else>
        <#-- TODO checkboxs的指令对应的java代码未完成 -->
            <@jj_radio dataType=dataType groupCode=groupCode keyFlag=keyFlag name=name value=value onclick=onclick />
        </#if>
    <#-- <#elseif pageMode=3> -->
    </#if>
</#macro>

<#--richTextEditor富文本编辑器-->
<#-- TODO checkboxs的指令对应的java代码未完成 -->
<#--
<textarea name="textarea"></textarea>页面logo
-->
<#macro logo value="" click="" class=""
>
<a <#if click!=""> href="${click}" <#else> href="javascript:;"</#if><#rt/>
    <#if class!=""> class="${class}"</#if><#rt/>
    <#if title!=""> title="${title}"</#if><#rt/>
        ><span><#if value!=""> ${title}</#if></span>
</a>

</#macro>


<#--
分页标签
-->
<#macro page page formurl="">
<#-- 自定义的分页指令 
    属性:
   pageNo      当前页号(int类型)
   pageSize    每页要显示的记录数(int类型)
   toURL       点击分页标签时要跳转到的目标URL(string类型)
   recordCount 总记录数(int类型)
 -->
<#-- 定义局部变量pageCount保存总页数   -->
    <#assign pageSize=(page.pageSize)?int>
    <#assign recordCount=(page.totalCount)?int>
    <#assign  pageCount=((recordCount + pageSize - 1) / pageSize)?int>
    <#assign pageNo=page.pageNo?int>
    <#if recordCount gt 0>
    <div class="pagination pagination-right">
        <ul>
            <input type="hidden" id="pageNo" name="page.pageNo" value="${page.pageNo!1}"/>
            <input type="hidden" id="pageSize" name="page.pageSize" value="${page.pageSize!10}"/>
            <input type="hidden" id="pageOrderby" name="page.orderBy" value="${page.orderBy}"/>
            <input type="hidden" id="pageOrder" name="page.order" value="${page.order}"/>
            <#nested>

        <#-- 页号越界处理 -->
            <#if (pageNo gt pageCount)>
                <#assign pageNo=pageCount/>
            </#if>
            <#if (pageNo lt 1)>
                <#assign pageNo=1/>
            </#if>
        <#-- 输出分页表单 -->
        <#-- 把请求中的所有参数当作隐藏表单域(无法解决一个参数对应多个值的情况) -->
        <#-- 上一页处理 -->
            <#if (pageNo == 1)>
                <li class="disabled"><a>&nbsp;上一页</a></li>
            <#else>
                <li><a href="javascript:turnOverPage(${pageNo - 1})">&nbsp;上一页</a></li>
            </#if>
        <#-- 如果前面页数过多,显示... -->
            <#assign start=1/>
            <#if (pageNo gt 4)>
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
                        <li class="active"><a>${i}</a></li>
                    <#else>
                        <li><a href="javascript:turnOverPage(${i})">${i}</a></li>
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
                <li class="disabled"><a>下一页&nbsp;</a></li>
            <#else>
                <li><a href="javascript:turnOverPage(${pageNo + 1})">下一页&nbsp;</a></li>
            </#if>
        </ul>
    </div>
    <script language="javascript">
        function turnOverPage(no) {
            var qForm = document.forms[document.forms.length - 1];
            if (no >${pageCount}) {
                no =${pageCount};
            }
            if (no < 1) {
                no = 1;
            }
            document.getElementById("pageNo").value = no;
            //qForm.action="${url}"
            qForm.submit();
        }
    </script>
    </#if>

</#macro>


<#--
表格列标签:展示数据列。
	title:标题（列头）。直接显示字符串。默认""。
	code:标题（列头）。显示国际化信息。默认""。
	width:列宽。默认""。
	align:对齐方式。
	class:css样式class
	style:css样式style
-->
<#macro column id="" name="" title="" code="" width="" align="" class="" style="" onDblClick="">
    <#if title="" && code="">
    <td>title and code all not assign!</td><#return></#if>
    <#if i==-1>
    <th<#if width!=""> width="${width}"</#if><#if class!=""> class="${class}"</#if><#if style!="">
                       style="${style}"</#if>><#if title!="">${title}<#else><@s.mt code=code text=code/></#if></th><#rt/>
    <#else>
    <td  <#if id!=""> id="${id}"</#if> <#if name!=""> name="${name}"</#if> <#if align!="">
                      align="${align}"</#if><#if class!=""> class="${class}"</#if><#if style!="">
                      style="${style}"</#if> <#if onDblClick!=""> onDblClick="${onDblClick}"</#if>><#nested/></td><#rt/>
    </#if>
</#macro>



<#--
表格标签:用于显示列表数据。
	value:列表数据，可以是Pagination也可以是List。
	class:table的class样式。默认"pn-ltable"。
	sytle:table的style样式。默认""。
	width:表格的宽度。默认100%。
-->
<#macro table value listAction="v_list.do" searchAction="" class="pn-ltable" style="" theadClass="pn-lthead" tbodyClass="pn-ltbody" width="100%">
<table class="${class}" style="${style}" width="${width}" cellspacing="1" cellpadding="0" border="0">
    <#if value?is_sequence><#local pageList=value/><#else><#local pageList=value.list/></#if>
    <#list pageList as row>
        <#if row_index==0>
            <#assign i=-1/>
            <thead class="${theadClass}">
            <tr><#nested row,i,true/></tr>
            </thead>
        </#if>
        <#assign i=row_index has_next=row_has_next/>
        <#if row_index==0>
        <tbody class="${tbodyClass}">
        <tr onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'"><#else>
        <tr onmouseover="this.bgColor='#eeeeee'"
            onmouseout="this.bgColor='#ffffff'"></#if><#nested row,row_index,row_has_next/>
        <#if !row_has_next>
        </tr></tbody>
        <#else>
            </tr>
        </#if>
    </#list>
</table>
    <#if !value?is_sequence>
        <#include "${taglibs.ftlctx}/common/page.ftl"/>
    </#if>
</#macro>

<#--
<form></form>
-->
<#macro form
action="" method="post" target="" enctype="" acceptCharset=""
theme="jeesys" width="100%" tableClass="pn-ftable" labelWidth="20" required="false" colspan="1"
id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
onsubmit=""
>
<form<#rt/>
        method="${method}"<#rt/>
        action="${action}"<#rt/>
    <#if id!=""> id="${id}"</#if><#rt/>
    <#if target!=""> target="${target}"</#if><#rt/>
    <#if enctype!=""> enctype="${enctype}"</#if><#rt/>
    <#if onsubmit!=""> onsubmit="${onsubmit}"</#if><#rt/>
    <#if acceptCharset!=""> accept-charset="${acceptCharset}"</#if><#rt/>
    <#include "common-attributes.ftl"/><#rt/>
        >
    <#if theme!="simple">
        <#assign labelWidth=labelWidth/>
    <table class="${tableClass}">
    <tr>
    </#if>
    <#nested/><#rt/>
    <#if theme=="jeesys">
    </tr></table>
    </#if>
</form>
</#macro>

<#-- 
输出查询条件:将查询条件以隐藏域的形式输出
用于页面跳转保存查询状态。
 -->
<#macro searchCondation prefix="">
    <#if searchCondationMap??>
    <!-- **********************************************************-->
        <#list searchCondationMap?keys as mapKey>
            <#assign mapvalues = searchCondationMap[mapKey]>
            <#if mapKey?index_of("page.")!=0>
            <intpu type="hidden" name="${prefix}#${mapKey}" value="${mapvalues}"/>
            </#if>
        </#list>
    <!-- **********************************************************-->
    </#if>
<#--
<#if pg?exists>
    <@page pg/>
</#if>
-->
</#macro>
<#-- 
	输出校验错消息
 -->
<#macro error msg="">
<div class="form-prompt" id="errdiv">
    <#if msg?? && msg?size gt 0>
        <div class="form-prompt-txt error" style="width: 250px">
            <#list msg as ms>
                <p class="fpt-li">
                    <#assign msArray=ms.split(":")/>
                    <span class="fpt-label">${msArray[0]}</span>
                    <span class="fpt-val">${msArray[1]}</span>
                </p>
            </#list>
        </div>
    </#if>
</div>
</#macro>

<#macro stringLength value  len >
    <#if value?length lt len>
    ${value}<#rt>
    <#else>
    ${value[0..len-1]}...<#rt>
    </#if>
</#macro>

<#--日期格式化  yyyy-MM-dd  -->
<#macro datef value="" format="yyyy-MM-dd">
    <#if value?is_date>
    	${value?string(format)}<#rt>
    </#if>
</#macro>
<#--日期格式化  yyyy-MM-dd  -->
<#macro datetimef value="" format="yyyy-MM-dd HH:mm:ss">
    <#if value?is_date>
    	${value?string(format)}<#rt>
    </#if>
</#macro>

<#-- 数字格式化  带有两位小数-->
<#macro numf value="">
    <#if value=="">
    ${0?string(",##0.00")}<#rt>
    <#else>
    ${value?string(",##0.00")}<#rt>
    </#if>
</#macro>

<#-- 数字格式化  取整-->
<#macro numi value="">
    <#if value=="">
    ${0}<#rt>
    <#else>
    ${value?string(",##0")}<#rt>
    </#if>
</#macro>

<#macro urlEncode url="">
    <#lt>${encodeURL("${url}")}<#rt>
</#macro>
<#macro hiddenEncode value="">
    <#lt>${dataEncrypt("${value}")}<#rt>
</#macro>


<#macro subStr value="">
    <#if value?length gt 4>
    ********${value?substring(value?length-4)}	
	<#else>
    ********${value}
    </#if>
</#macro>


