       	<script type="text/javascript">
			$(function(){
				<#--画面必填项校验-->
			$("body").on("blur","input:text,select",function(){
				var passFlag=true;
				$(this).parents("div.tab-bd-con").find("input:text,select").each(function(){
					var thisVal=$(this).val();
					if( $(this).type === "text" ){
					}else if ($(this).hasClass("selectpicker")){
						thisVal=$(this).find("option:selected").val();
					}
					
					if(($(this).parent().next().hasClass("required-star") && thisVal=="") || !$(this).valid()){
						passFlag=false;
					}
				});
				if(passFlag){
					$("#"+$(this).parents("div.tab-bd-con").attr("id")+"i").removeClass().addClass("tab_pass");	
				}else{
					$("#"+$(this).parents("div.tab-bd-con").attr("id")+"i").removeClass().addClass("tab_wrong");
				}
			});
			
			
			<#--补充信息添加-->
				$("body").on("click","a.item_hd_oper.fancybox",function(){
					var url=$(this).data("action");
			        	var param = {"count":$("#"+$(this).data("flag")+"_count").val()};
			        	$.ajaxLoadFtl({
					            type: "POST",
					            url: url,
					            dataType: "json",
					            data:  param,
					            error: function (data, transport) {
					               $("#msg_view").html("服务器繁忙");
					            },
					            success: function (result) {
					                $("#msg_view").html(result.html);
					                $.fancybox.open($("#msg_view"),{"title":"add"});
					            }
					      });
			    	});
			
					<#--补充信息删除-->
					$("a.del.fancybox").unbind("click");
					$("body").on("click","a.del.fancybox",function(){
			        	var param = {"dataid":$(this).data("id"),"flag":$(this).data("flag")};
			        	$.ajaxLoadFtl({
					            type: "POST",
					            url: "${taglibs.ctx}/loan/apply/delete.htm",
					            dataType: "json",
					            data:  param,
					            error: function (data, transport) {
					               $("#delete_btn").html("服务器繁忙");
					            },
					            success: function (result) {
					                $("#delete_btn").html(result.html);
					                $.fancybox.open($("#delete_btn"),{"title":"delete"});
					            }
					      });
			    	});
					<#--补充信息查看-->
					$("body").on("click","a.view.fancybox",function(){
						var url=$(this).data("action");
						var param = {};
						$(this).parents("tr").find("input[type='hidden']").each(function(){
						param[$(this).attr("name").replace(/\w+s\[\d\]./,"")]=$(this).val();
						
						});
						$.ajaxLoadFtl({
					            url: url,
					            data:  param,
					            error: function (data, transport) {
					               $("#msg_view").html("服务器繁忙");
					            },
					            success: function (result) {
					                $("#msg_view").html(result.html);
					                $.fancybox.open($("#msg_view"),{"title":"view"});
					            }
					    });
					});
				<#--补充信息修改-->
					$("body").on("click","a.edit.fancybox",function(){
						var url=$(this).data("action");
						var param = {};
						$(this).parents("tr").find("input[type='hidden']").each(function(){
						param[$(this).attr("name").replace(/\w+s\[\d\]./,"")]=$(this).val();
						});
						
						param["index"]=$(this).data("index");
						$.ajaxLoadFtl({
				            url: url,
				            data:  param,
				            error: function (data, transport) {
				               $("#msg_view").html("服务器繁忙");
				            },
				            success: function (result) {
				                $("#msg_view").html(result.html);
				                $.fancybox.open($("#msg_view"),{"title":"modify"});
				            }
				        });
					});
					
					$("body").on("click",".btncancel",function(){
						$.fancybox.close();
					});
					<#--删除确定-->
					$("body").on("click","#del_submit.btn_primary",function(){
						var elementId=$("#delEmId").val();
						var elementParent=$("#"+elementId).parent();
						$("#"+elementId).remove();
						elementParent.find("tr").each(function(){
						
						var index=$(this).index();
							$(this).find("a").each(function(){
								$(this).removeAttr("data-index").attr("data-index",index);
							});
							$(this).find("input:hidden").each(function(){
								var namestr=$(this).attr("name");
								var reg = /\d/g;
								$(this).attr("name",namestr.replace(reg,index));
							});
						});
						
						$("#"+$("#flag:hidden").val()+"_count").val(elementParent.find("tr").length);
						$.fancybox.close();
					});
					
					
					
					$("body").on("click","#del_cancel",function(){
						$.fancybox.close();
					});
					
					
					$("#form_submit").click(function(){
						if($("#loanFrm").validate().form()==true){
							$.fancybox.open({
								'href' : '#financ_apl_submit',
								'title' : 'submit'
								});	
						}else{
							$("label.error:first").parent().find("input:text:first").focus();
						}
					});
				// previous
				$("#returnButton").fancybox({
					'href' : '#financ_return_button',
					'title' : 'previous'
				});	
				//新版上传图片
				 $("#file_upload").uploadify({
				 	'swf'      		 : '../../uploadify/uploadify.swf',
	                'uploader'       : '${taglibs.allctx}/upload.htm',
	                fileObjName: 'uploadify'
	            });
				
				
				//个人工作信息是否有补充资料js
				$("input[id='supplement_info']").click(function(){
					if($(this).val()=="1"){
						$("#supplement_info_table").removeClass("hide").slideDown();
					}else {
						$("#supplement_info_table").addClass("hide").slideDown();
						$("#supplement_info_table").removeAttr("style");
					}
				});
					
				$("a#a_cancel").click(function(){
					$.fancybox.close();
				});
				$("#return_cancel").click(function(){
					$.fancybox.close();
				});
				
				$(':file').change(function(){
				    //your validation
				    var formData = new FormData($('form')[0]);
				    $.ajax({
				        url : webName + '/fileUpload.htm',  //server script to process data
				        type: 'POST',
				        // Form数据
				        data: formData,
				         dataType : 'json',
				        /*xhr: function() {  // custom xhr
				            myXhr = $.ajaxSettings.xhr();
				            if(myXhr.upload){ // check if upload property exists
				                myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // for handling the progress of the upload
				            }
				            return myXhr;
				        },
				        //Ajax事件
				        beforeSend: beforeSendHandler,
				        */
				        success: function(result){
				        	$("#electronicSealfileName").val(result.fileName);
				        	$("#electronicSealurl").val(result.fileUrlOriginal);
				        },
				        error: function(e){
				        },
				        //Options to tell JQuery not to process data or worry about content-type
				        cache: false,
				        contentType: false,
				        processData: false
				    });
				    
				    
				});
				
		$("body").on("change","#carBuyWay",function(){
			var select = $("#carBuyWay").find("option:selected").val();
			var param ={"buyWay":select};
				$.ajaxLoadFtl({
		            type: "POST",
		            url: "${taglibs.ctx}/loan/apply/car_supplement.htm",
		            dataType: "json",
		            data:  param,
		            error: function (data, transport) {
		            },
		            success: function (result) {
			            if(result.result){
			            	$("table tbody#carTbody tr:gt(1)").remove();
			            	$("#carTbody").append(result.html);
			            }else{
			            	$("table tbody#carTbody tr:gt(1)").remove();
			            }
		            }
		      });
		});
		$("body").on("change","#houseBuyType",function(){
			var select = $("#houseBuyType").find("option:selected").val();
			var param ={"buyType":select,"buyAmount":$("#buyAmount").val()};
				$.ajaxLoadFtl({
		            type: "POST",
		            url: "${taglibs.ctx}/loan/apply/house_supplement.htm",
		            dataType: "json",
		            data:  param,
		            error: function (data, transport) {
		            },
		            success: function (result) {
		            	$("table#houseTable tbody tr:gt(2)").remove();
		            	$("table#houseTable tbody").append(result.html);
		            }
		      });
		});
		$("input:text,select").each(function(){
			var thisVal=$(this).val();
			if( $(this).type === "text" ){
			}else if ($(this).hasClass("selectpicker")){
				thisVal=$(this).find("option:selected").val();
			}
			if(($(this).parent().next().hasClass("required-star") && thisVal=="") || !$(this).valid()){
				$("#"+$(this).parents("div.tab-bd-con").attr("id")+"i").removeClass().addClass("tab_pass");	
			}else{
				$("#"+$(this).parents("div.tab-bd-con").attr("id")+"i").removeClass().addClass("tab_wrong");	
			}
		});		
		
		
		$("div.tab-bd-con").each(function(){
			var passFlag=true;
			$(this).find("input:text,select").each(function(){
					var thisVal=$(this).val();
					if( $(this).type === "text" ){
					}else if ($(this).hasClass("selectpicker")){
						thisVal=$(this).find("option:selected").val();
					}
					if(($(this).parent().next().hasClass("required-star") && thisVal=="")){
						passFlag=false;
					}
			});
			if(passFlag){
					$("#"+$(this).attr("id")+"i").removeClass().addClass("tab_pass");	
				}else{
					$("#"+$(this).attr("id")+"i").removeClass().addClass("tab_wrong");
			}
		});	
	});
</script>