<#--
融资状态标签
<@bizTag.loanStatus statusCode loanAmount loanEndTime />
-->
<#macro loanStatusButton
    statusCode loanAmount loanEndTime investedAmount=0
>
    <#if statusCode == "05">
    <#--下线状态，显示启用按钮-->
    <div class="fl">
        <@ctl.button id="start_btn" text="Enable"/>
     </div>
     <#elseif statusCode == "04">
         <#if loanAmount == investedAmount>
         <#--已满额-->
         <div class="fl">    
            <@ctl.button id="flow_btn"   text="Fail" float="fl"/>
        </div>
        <div class="fr">
            <#--<span class="notice_text">（今日为节假日，由于银行转账的延时的关系建议不要进行放款操作！）</span>-->
            <@ctl.button id="loan_btn" text="Effect" />
        </div>
         <#elseif loanAmount lt investedAmount>
         <#--超募-->
         <div class="fl">    
            <@ctl.button id="flow_btn"   text="Fail" float="fl"/>
        </div>
        <div class="fr">
            <@ctl.button id="loan_btn" text="Effect" />
            <#--<@ctl.button id="ultra_btn" text="超募处理" />-->
        </div>
        <#elseif loanEndTime?datetime gt .now>
        <#--招募中-->
        <div class="fl">    
            <#--<@ctl.button id="delay_btn" text="延时" float="fl"/>-->
            <@ctl.button id="forbidden_btn" text="Disable" />
        </div>
        <#else>
        <#--到期未满标-->
        <div class="fl">    
            <#--<@ctl.button id="delay_btn" text="延时" float="fl"/>-->
            <@ctl.button id="flow_btn"   text="Fail" float="fl"/>
        </div>
            <#if investedAmount?? && investedAmount gt  0>
                <div class="fr">
                    <@ctl.button id="loan_btn" text="effect" />、
                </div>
            </#if>
         </#if>
    </#if>
</#macro>