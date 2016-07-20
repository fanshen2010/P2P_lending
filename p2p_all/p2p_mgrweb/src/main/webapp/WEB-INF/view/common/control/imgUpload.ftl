<#macro imgUpload
	uploadFiles=[]
>
<div class="img_pick_panel">
    <div class="inner_container_box side_r cell_layout">
        <div class="inner_main">
            <div class="bd">
                <div class="media_list">
                    <div class="global_mod float_layout">
                        <div class="global_extra">
                            <div class="bubble_tips bubble_right warn">
                                <div class="bubble_tips_inner">大小: 不超过2M,&nbsp;&nbsp;&nbsp;&nbsp;格式: bmp, png, jpeg, jpg, gif</div>
                                <i class="bubble_tips_arrow out"></i>
                                <i class="bubble_tips_arrow in"></i>
                            </div>
                            <div class="upload_box align_right">
							    <span class="upload_area webuploader-container" style="position:relative">
									<input id="imgFile" class="plugins_img_upload" type="file">
							    </span>
							</div>
                            <span class="global_extra_notice">(请上传遮盖后的资质证照图片，该图片会在线上融资过程中向投资者展示。)</span>
                        </div>
                    </div>
                    <div class="group img_pick" id="js_imglist">
                        <#if uploadFiles?exists && uploadFiles?is_sequence && uploadFiles?size gt 0>
                        <ul class="group">
                        	<#list uploadFiles as uploadFile>
                        	<li class="img_item js_imgitem">
                        		<div class="img_item_bd">
                        			<img class="pic wxmImg Zoomin" src="${(uploadFile.fileUrlThumb)!}">
                        			<span class="name_text">
                        				<span class="frm_input_box">
                        					<input type="text" class="frm_input" name="uploadFiles[${uploadFile_index}].fileName" value="${(uploadFile.fileName)!}">
                        					<input type="hidden" value="${(uploadFile.fileUrlOriginal)!}" name="uploadFiles[${uploadFile_index}].fileUrlOriginal">
                        					<input type="hidden" value="${(uploadFile.fileUrlBig)!}" name="uploadFiles[${uploadFile_index}].fileUrlBig">
                        					<input type="hidden" value="${(uploadFile.fileUrlThumb)!}" name="uploadFiles[${uploadFile_index}].fileUrlThumb">
                    					</span>
                					</span>
            					</div>
            					<div class="msg_card_ft">
            						<ul class="grid_line msg_card_opr_list">
            							<li class="grid_item size1of1 msg_card_opr_item clearfix">
            								<a class="js_del js_tooltip js_popover" href="javascript:;">
            									<span class="msg_card_opr_item_inner">
            										<i class="icon18_common del_gray">delete</i>
        										</span>
    										</a>
										</li>
									</ul>
								</div>
							</li>
							</#list>
                        </ul>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${taglibs.ctx}/style/js/img_upload.js"></script>
</#macro>