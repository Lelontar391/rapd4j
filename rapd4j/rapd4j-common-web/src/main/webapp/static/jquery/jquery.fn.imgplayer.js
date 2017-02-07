/**
 * playImgs jQuery plugin
 *
 * Copyright (c) 2009 Xing,Xiudong
 *
 * @author Xing,Xiudong  xingxiudong@gmail.com | http://blog.csdn.net/xxd851116
 * @since: 2009-08-06
 * @version 1.0 2009-08-06
 * @deprecated 1.1 update a problem: the same index li element's show or hide is repeated when mouse over it at 2009-09-23
 * @deprecated 1.2 add row($title_bar.width($container.width() - 5 * 2);) to remove '$title_bar' left-padding and right-padding at 2010-02-24
 */
(function($){
	$.fn.playImgs = function(settings){
		var options = $.extend({
			imgCSS		: {}, // 用户自定义图片样式
			transition	: 0,  // 播放模式选项 1:溶解，2:挂历模式，3:滑动(从左到右),4:滑动(从上到下),5:滑动(从下到上),6:滑动(从右到左),23:随机
			width		: '', // 播放器div容器的宽度
			height		: '', // 播放器div容器的高度
			time		: 0,  // 图片播放间隙时间,单位：毫秒
			duration	: 500,// 图片播放时间,单位：毫秒
			onStart		: function(){}, // 开始播放时执行的函数
			onStop		: function(){}, // 停止播放时执行的函数
			onShow		: function($) {$.show();}, // 每页图片显示时执行的函数
			onHide		: function($) {$.hide();}  // 每页图片隐藏时执行的函数
		}, settings);

//		var bg = 'url(' + webContext + '/static/images/round.png) no-repeat -12px 0 transparent';
//		var bgsel = 'url(' + webContext + '/static/images/round.png) no-repeat 0 0 transparent';
		var __defaultCSSDiv = {'border':'1px solid #CCC','align':'center','position':'relative','overflow':'hidden','width':options.width,'height':options.height};
		var __defaultCSSImg = {'position':'absolute','z-index':'0',"border":"none"};
		var __defaultCSSUL  = {'margin':'0','padding':'0','fontSize':'12px','z-index':'2','position':'absolute','bottom':'2px','right':'2px','list-style-type':'none'};
		var __defaultCSSLI  = {'float':'left','height':'10px','width': '10px','border-radius':'5px','margin-left':'2px','cursor':'pointer','background': '#eee'};

		// 获得图片播放器容器
		var $container 	= this.hide().css(__defaultCSSDiv);
		// 获得图片数组
		var $images 	= $container.find("img").hide().css(options.imgCSS).css(__defaultCSSImg);
		// 最后一张（从左到右）图片的索引
		var lastIndex 	= $images.length - 1;
		// 最后一次访问图片的索引（从0开始）
		var prevIndex	= lastIndex;
		var index = 0;		// 记录当前索引值, 默认为第一张图片的索引：0
		var timer;			// 定时器

		// 生成索引序号
		var $indexGroups = $("<ul></ul>").css(__defaultCSSUL).fadeTo('fast', 0.9);
		for (var i = 0; i < $images.length; i++) {
			$indexGroups.append("<li index='" + i + "'></li>");
		}
		// 获取序号元素的jQuery对象数组
		var $sn = $indexGroups.appendTo($container).children("li");

		// 为每一个图片绑定hover事件
		$images.hover(function(){
			pause();
		},function(){
			timer = setTimeout(run, options.time);
		});

		// 为每一个li标签绑定hover事件
		$sn.css(__defaultCSSLI).hover(function() {

			// 计算当前图片的索引
			index = $(this).attr('index');

			if (prevIndex != index) {
				// 隐藏上一张图片
				hide(prevIndex);

				// 显示当前索引的图片
				show(index);
			}
			// 记住当前图片索引
			prevIndex = index;

			// 计时器停暂停运行
			pause();
		}, function() {
			prevIndex = $(this).attr('index');
			timer = setTimeout(run, options.time);
		});

		function run() {
			// 计算index值，如果指定显示正在显示的图片，则计时器将显示下一幅图片
			index = index == prevIndex ? index + 1 : index;
			// 计算index值，超过最后索引值则重置
			index = index > lastIndex ? 0 : index;

			if (options.transition == 23) {
				var random_num = parseInt(Math.random() * 5) + 1;
				$container.playAction(random_num);
			}

			hide(prevIndex);
			show(index);

			prevIndex = index;
			index++;

			timer = setTimeout(run, options.time);
		}

		function show(index) {
			options.onShow($images.eq(index));
			$sn.eq(index).css('background','#c44');
		}

		function hide(index) {
			options.onHide($images.eq(index));
			$sn.eq(index).css('background','#eee');
		}

		function pause() {
			options.onStop();
			clearTimeout(timer);
		}

		$container.start = function(){ options.onStart();run();return $container;}
		$container.stop  = function(){ options.onStop();pause();return $container;}

		// 定义动画
		$container.playAction = function(n) {
			switch(n) {
				case 1 :
					options.onShow = function($_) {$_.fadeIn(options.duration);};
					options.onHide = function($_) {$_.fadeOut(options.duration);};
					break;
				case 2 : // 日历模式
					options.onShow = function($_) {$_.slideDown(options.duration);};
					options.onHide = function($_) {$_.slideUp(options.duration);};
					break;
				case 3 : // →
					options.onShow = function($_){
						$_.css("left",  -$_.width() + 'px').show().animate({left: "0px"}, options.duration);
					};
					options.onHide = function($_){
						$_.css("left", '0px').animate({left: $_.width() + 'px'}, options.duration);
					};
					break;
				case 4 : // ↓
					options.onShow = function($_){
						$_.css("top", -$_.height() + 'px').show().animate({top: "0px"}, options.duration);
					};
					options.onHide = function($_){
						$_.css("top", '0px').animate({top: $_.height() + 'px'}, options.duration);
					};
					break;
				case 5 : //↑
					options.onShow = function($_){
						$_.css("bottom", -$_.height() + 'px').show().animate({bottom: "0px"}, options.duration);
					};
					options.onHide = function($_){
						$_.css("bottom", '0px').animate({bottom: $_.height() + 'px'}, options.duration);
					};
					break;
				case 6 : //←
					options.onShow = function($_){
						$_.css("left",  $_.width() + 'px').show().animate({left: "0px"}, options.duration);
					};
					options.onHide = function($_){
						$_.css("left", '0px').animate({left: -$_.width() + 'px'}, options.duration);
					};
					break;
				case 23 :
					var random = parseInt(Math.random() * 5) + 1;
					break;
			}
			return this;
		}

		return $container.playAction(options.transition).show();
	}
})(jQuery);