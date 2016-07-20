$(function(){
	$("#imgFile").uploadify({
	 	swf: webName + '/uploadify/uploadify.swf',
	    uploader: webName + '/upload.htm',
	    fileObjName: 'uploadify',
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
	    	addButton($li);
	    }
	});
});