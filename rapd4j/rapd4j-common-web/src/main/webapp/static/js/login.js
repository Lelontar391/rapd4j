$(function() {
	if($("#isshow").val()=="1"){
		$(".meter-login-valid").show();
	}
	$('#kaptcha').click(
			function() {
				$(this).hide()
						.attr(
								'src',
								'static/images/kaptcha.jpg?'
										+ Math.floor(Math.random() * 100))
						.fadeIn();
			});
	$('#kaptchaa').click(
			function() {
				$('#kaptcha')
						.attr(
								'src',
								'images/kaptcha.jpg?'
										+ Math.floor(Math.random() * 100));
			});
});
function beforeSubmit(){
	var username=$("#username").val();
	if(username==""){
		$(".meter-error").html("请输入用户名！");
		return false;
	}
	var password=$("#password").val();
	if(password==""){
		$(".meter-error").html("请输入密码！");
		return false;
	}
	var isshow=$("#isshow").val();
	var flag=$(".meter-login-valid").is(':visible');
	if(isshow=="1"&&flag){
		var code=$("#code").val();
		if(code==""){
			$(".meter-error").html("请输入验证码！");
			return false;
		}
	}
	
	
}