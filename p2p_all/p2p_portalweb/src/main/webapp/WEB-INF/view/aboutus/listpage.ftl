<@cl.html >
        <section class="area">
            <div class="container">
                <div class="article-area">
                    <#include "${taglibs.ftlctx}/aboutus/menuitem.ftl"/>
                    <div class="main main-content">
                        <header class="main-content-hd"><span class="main-content-tit">${(aboutUsDto.category.title)!}</span></header>
                        <div class="main-content-bd">
                        <form action="index.htm" id="searchForm" method="POST"/>
                        <input type="hidden" name="aboutUsDto.category.id" value="${(aboutUsDto.category.id)!}">
                            <div class="main-content-info article-list">
                                <ul class="list">
                                    <#list aboutUsDto.articles as article>
                                        <li>
                                            <span class="time"></span>
                                            <a href="view.htm?aboutUsDto.article.id=${(article.id)}&aboutUsDto.category.id=${(aboutUsDto.category.id)!}" <#if aboutUsDto.category.target=="0"> target="_blank" </#if> >${(article.title)!}</a>
                                        </li>
                                    </#list>
                                </ul>
                                <@ctl.page page=aboutUsDto.criteria.page />
                            </div>
                        </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>