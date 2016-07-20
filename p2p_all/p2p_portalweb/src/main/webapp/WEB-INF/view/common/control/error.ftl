<#macro error>
<#if Request["common_error_msg_key"]?exists>
<#assign msgs = Request["common_error_msg_key"]>
    <#if msgs?? && msgs?size gt 0>
    	<div id="verify_msg" class="verify_msg hide">
            <#list msgs as ms>
                <p>
                    ${ms}
                </p>
            </#list>
        </div>
        <script type="text/javascript">
        $(function(){
        	$("#verify_msg").show();
			setTimeout(function(){
				$("#verify_msg").hide();
				},2000);
        });
        
        </script>
    </#if>

</#if>
</#macro>
