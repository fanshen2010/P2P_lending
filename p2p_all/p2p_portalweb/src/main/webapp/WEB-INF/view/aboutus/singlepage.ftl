<@cl.html>
        <section class="area">
            <div class="container">
                <div class="about-area">
                    <#include "${taglibs.ftlctx}/aboutus/menuitem.ftl"/>
                    <div class="main main-content">
                        <header class="main-content-hd"><span class="main-content-tit">${(aboutUsDto.category.title)!}</span></header>
                        <div class="main-content-bd">
                            <div class="main-content-info about-info">
                                <div class="post-content">
                                    ${(aboutUsDto.category.content)!}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>