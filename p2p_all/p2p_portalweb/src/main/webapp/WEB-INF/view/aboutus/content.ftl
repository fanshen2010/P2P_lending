<@cl.html>
        <section class="area">
            <div class="container">
                <div class="article-area">
                    <#include "${taglibs.ftlctx}/aboutus/menuitem.ftl"/>
                    <div class="main main-content">
                        <header class="main-content-hd"><span class="main-content-tit"><a href="index.htm?aboutUsDto.category.id=${(aboutUsDto.category.id)!}" title="">&lt; return</a></span></header>
                        <div class="main-content-bd">
                            <div class="main-content-info article-info">
                                <header class="post-header">
                                    <h1 class="article-title">${(aboutUsDto.article.title)!}</h1>
                                    <p class="article-dec">
                                        <span class="article-dec-from">Source:${(aboutUsDto.article.articleSource)!}</span>
                                        <span class="article-dec-date">Time:<@h.datef value=(aboutUsDto.article.postAt)! /></span>
                                    </p>
                                </header>
                                <div class="post-content">${(aboutUsDto.article.content)!}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>