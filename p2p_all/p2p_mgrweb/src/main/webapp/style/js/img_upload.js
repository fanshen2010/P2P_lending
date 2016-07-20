$(function(){
	$("#imgFile").uploadify({
	 	swf: webName + '/uploadify/uploadify.swf',
	    uploader: webName + '/upload.htm',
	    fileObjName: 'uploadify',
	    fileSizeLimit: "2MB",
	    fileTypeExts: "*.bmp; *.png; *.jpeg; *.jpg; *.gif",
	    fileTypeDesc: "图片文件",
	    onUploadSuccess: function(file, data, response){
	    	var result = $.parseJSON(data);
	    	if($("#js_imglist").find("ul.group").length > 0){
	    	
	    	} else {
	    		var $ul = $(document.createElement("ul"));
	    		$ul.addClass("group").appendTo($("#js_imglist"));
	    	}
	    	var $li = $(document.createElement("li"));
	    	$li.addClass("img_item js_imgitem");
	    	$("#js_imglist").find("ul.group").append($li);
	    	addImg($li, result, file.name);
	    	addButton($li,file);
	    }
	});
		
	
	var removeImg = function(){
		var files = $('#imgFile').data('uploadify').queueData.files = [];
    	for (var member in files) {
    		if (member.id==$(this).data("id"))
    			delete files[member];
    	}
		$(this).parents("li.img_item").remove();
		resetName();
	};

	var addImg = function($li, data, fileName){
		var index = $("#js_imglist").find("ul.group li.img_item").index($li);
		var $img = $(document.createElement("img"));
		$img.addClass("pic wxmImg Zoomin").attr("src", data.fileUrlThumb);
		$img.fancybox({"href": data.fileUrlBig, "title": fileName});
		
		var input = document.createElement("input");
		$(input).attr("type", "text").addClass("frm_input").val(fileName).attr("name", "uploadFiles[" + index + "].fileName");
		
		var $thumbUrl = $(document.createElement("input"));
		$thumbUrl.attr("type", "hidden").val(data.fileUrlThumb).attr("name", "uploadFiles[" + index + "].fileUrlThumb");
		
		var $bigUrl = $(document.createElement("input"));
		$bigUrl.attr("type", "hidden").val(data.fileUrlBig).attr("name", "uploadFiles[" + index + "].fileUrlBig");
		
		var $originalUrl = $(document.createElement("input"));
		$originalUrl.attr("type", "hidden").val(data.fileUrlOriginal).attr("name", "uploadFiles[" + index + "].fileUrlOriginal");
		
		var spanInput = document.createElement("span");
		$(spanInput).addClass("frm_input_box").append(input).append($originalUrl).append($bigUrl).append($thumbUrl);
		
		var spanName = document.createElement("span");
		$(spanName).addClass("name_text").append(spanInput);
		
		var imgItem = document.createElement("div");
		$(imgItem).addClass("img_item_bd").append($img).append(spanName);
		
		$li.append(imgItem);
	}

	var addButton = function($li,file){
		var i = document.createElement("i");
		$(i).addClass("icon18_common del_gray").text("删除");
		var span = document.createElement("span");
		$(span).addClass("msg_card_opr_item_inner").append(i);
		var a = document.createElement("a");
		$(a).addClass("js_del js_tooltip js_popover").attr("href", "javascript:;").attr("data-id",file.id).append(span);
		$(a).on("click", removeImg);
		var li = document.createElement("li");
		$(li).addClass("grid_item size1of1 msg_card_opr_item clearfix").append(a);
		var ul = document.createElement("ul");
		$(ul).addClass("grid_line msg_card_opr_list").append(li);
		var div = document.createElement("div");
		$(div).addClass("msg_card_ft").append(ul);
		
		$li.append(div);
	}
	
	var resetName = function(){
		$("ul.group span.name_text span.frm_input_box").each(function(){
			var index = $("ul.group span.name_text span.frm_input_box").index($(this));
			$(this).find("input").each(function(){
				var oldName = $(this).attr("name");
				var newName = oldName.replace(/(uploadFiles\[)(\d+)(\]\.)(\w+)/g,"$1"+index+"$3" + "$4");
				$(this).attr("name", newName);
			});
		});
	}
	$("body").on("click","a.js_del.js_tooltip.js_popover",function(){
		$(this).parents("li.img_item").remove();
		resetName();
	});
});