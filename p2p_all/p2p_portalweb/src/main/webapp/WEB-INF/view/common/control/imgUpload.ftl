<#macro imgUpload
	returnTarget=""
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
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${taglibs.ctx}/style/js/img_upload.js"></script>
</#macro>