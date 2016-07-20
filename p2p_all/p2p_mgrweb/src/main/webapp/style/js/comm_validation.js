$(document).ready(
		function() {
			jQuery("form").each(function() {
				$(this).validationEngine();
			});

			$(".collapsenext").click(
					function() {
						$(this).parent().nextAll("div.nowrap_table").eq(0)
								.children(".table").toggle();
					});
			$(".collapsenext").click(function() {
				$(this).parent().next("table.table").toggle();
			});
			$(".collapsenext").click(function() {
				$(this).parent().nextAll("div.zhedie").eq(0).toggle();
			});
			
			// 获取默认状态下radio的value值
			var curvalue = $("input.radio[checked]").val();
			$("input.radio").parents(".uniformjs").append("<input class='curvalue' type='hidden' value='' />");
			$("input.curvalue").val(curvalue);	
			
			$(".reset").click(function() {
				$("div.formError").remove();
				$("a.fileupload-exists").click();
				$(".selectpicker").each(function(){ // 重置原生select的值
			    	$(this).find("option").attr("selected", false);  
			    	$(this).find("option:first").attr("selected", true);
			    	if($("#actionId").val() != "edit"){
			    		$(this).next(".bootstrap-select").find(".filter-option").text($(this).find("option").first().text());
			    	}else{
			    		$(this).next(".bootstrap-select").find(".filter-option").text($(this).siblings("input").val());
			    	}
			    });
				// 重置radio
				$("input.radio").each(function(){
					$(this).removeAttr("checked");
					$(this).parent().removeClass("checked");
					var afterval = $("input.curvalue").val();
					if($(this).val()==afterval){
						$(this).attr("checked");
						$(this).parent().addClass("checked");
					}else{
						$(this).removeAttr("checked");
						$(this).parent().removeClass("checked");
					}
				});
			});

			if ($("#errdiv").html() != "") {
				setTimeout(function() {
					$("#errdiv").fadeOut("slow");
				}, 3000);
			}

		});
