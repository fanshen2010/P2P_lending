            <aside class="side side-menu">
                <dl class="about-menu">
                    <dt class="side-menu-tit"><span>FAQ</span></dt>
                    <dd class="side-menu-list">
                        <#list aboutUsDto.categorys as category>
                        	<#if category.categoryType=="2">
                        		<a class="news-notice" href="${(category.targetUrl)!}"><i class="icon"></i><span>${(category.title)!}</span></a>
                        	<#else>
                        		<a class="news-notice" id="${(category.id)!}" href="index.htm?aboutUsDto.category.id=${(category.id)!}"><i class="icon"></i><span>${(category.title)!}</span></a>
                        	</#if>
                        </#list>
                    </dd>
                </dl>
            </aside>
        <script type="text/javascript">
            $(document).ready(function(){
                 $("#${(aboutUsDto.category.id)!}").addClass("active").siblings("a").removeClass("active");
            });
        </script>
