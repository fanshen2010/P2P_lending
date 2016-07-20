<footer class="footer">
	<div class="footer-info">
    	<div class="container">
        	<dl class="about-us">
            	<dt>FAQ</dt>
            	<dd>
            	<@aboutCategorys >
            		<#list aboutCategorys as item>
            			<a href="${taglibs.allctx}/aboutus/index.htm?aboutUsDto.category.id=${item.id}" title="">${item.title}</a>
            		</#list>
            	</@aboutCategorys>
                </dd>
            </dl>
            <dl class="srv-online">
            	<dt>客服在线</dt>
            	<dd>
                	<p class="so-mail">${getSettingValue('service_email')}</p>
                    <p class="so-phone">${getSettingValue('service_tell')}</p>
                    <p class="so-qrcode"><img src="" alt="" /></p>
                </dd>
            </dl>
        </div>
    </div>
    <div class="foot-copyright">
    	<div class="container">
        	<div class="copyright">
            	<p class="copyright-cpy">${getSettingValue('copyright')}</p>
                <p class="copyright-record">${getSettingValue('site_beian')}</p>
            </div>
            <div class="letter">
            	<a href="javascript:;" title=""><img src="/style/images/sym.png" alt="" /></a>
                <a href="javascript:;" title=""><img src="/style/images/foot_itrust.png" alt="" /></a>
            </div>
        </div>
    </div>
</footer>