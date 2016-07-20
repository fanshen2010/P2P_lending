<%@ page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>登录超时</title>
		<link href="${allctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${allctx}/css/content.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/js/jquery/jquery.js" type="text/javascript"></script>
		
		<style type="text/css">
			body{
				font-size: 12px;
				margin:0px auto;
			}
		</style>
		<script type="text/javascript">
			if ($(self.parent.document).find("#tab-loading-mask")) {
	 			$(self.parent.document).find("#tab-loading-mask").fadeOut();
	 		}
	 	</script>
	</head>
	<body>
		<div class="page-error">
			<div class="page-img img500"></div>
			<div class="page-solve">
				<span class="p-s-t500">500!服务器出现异常了</span>
				<span >试试 以下的几种方法吧：</span>
				<span >1.检查网址是否正确。</span>
				<span >2.<a href = "${ctx}/j_admin_logout" >重新登录</a>。</span>
			</div>
			<div class="clear"></div>
		</div>
	</body>
</html>