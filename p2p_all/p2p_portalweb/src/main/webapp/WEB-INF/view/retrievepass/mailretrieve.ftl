<@cl.html>
        <section class="area">
            <div class="container">
                <div class="regin change-way-mailbackbox">
                    <h2 class="regin-hd"><strong class="regin-tit">Retrieve Your Password By Email</strong></h2>
                    <div class="regin-bd">
                        <form action="mailupdate.htm" class="fpw-phone">
                        <input class="input-text" name="webUser.id" type="hidden" value="${(webUser.id)!}"/>
                            <div class="form-group">
                                <span class="frm-label">New Password:</span>
                                <span class="frm-controls">
                                    <input class="input-text" type="password" name="password" id="password" data-rule-rangelength="6,18" data-rule-required="true" data-field_name="New Password"/>
                                </span>
                            </div>
                            <div class="form-group">
                                <span class="frm-label">Confirm Password:</span>
                                <span class="frm-controls">
                                    <input class="input-text" type="password" name="webUser.password" data-rule-rangelength="6,18" data-rule-equalTo="#password" data-rule-required="true" data-field_name="Confirm Password"/>
                                </span>
                            </div>
                            <div class="form-group">
                                <span class="frm-label">&nbsp;</span>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
 </@cl.html>