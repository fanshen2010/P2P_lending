 <script type="text/javascript">
			$(function(){
				$("#searchCloseBt").click(function(){
					$("#keyInput").val('');
				})
				$("#search_btn").click(function(){
						msg("customerMsg.htm");
				})
				 $("#keyInput").keypress(function (e) {  
			            var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;  
			            if (keyCode == 13) {  
			                for (var i = 0; i < this.form.elements.length; i++) {  
			                    if (this == this.form.elements[i]) break;  
			                }  
			                i = (i + 1) % this.form.elements.length;  
			                this.form.elements[i].focus(); 
			                msg("customerMsg.htm"); 
			                return false;  
			            } else {  
			                return true;  
			            }  
			        }); 
			});
			var msg=function(url){
				var inputStr=$("#keyInput").val();
				var params={"value":inputStr};
				$.ajax({
		            type: "POST",
		            url: url,
		            dataType: "json",
		            async:false,
		            data: $("form#loanfrm").serialize(),
		            error: function (data, transport) {
		               $("#notice_y").html("服务器繁忙");
		                $("#notice_y").removeClass("hide").slideDown();
		            },
		            success: function (result) {
		                $("#notice_y").html(result.html);
		                $("#notice_y").removeClass("hide").slideDown();
		            }
		        });
			}
		</script>