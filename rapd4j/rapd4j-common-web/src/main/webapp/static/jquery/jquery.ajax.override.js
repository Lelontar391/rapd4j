(function($) {
	// 备份jquery的ajax方法
	var _ajax = $.ajax;

	// 重写jquery的ajax方法
	$.ajax = function(opt) {
		// 备份opt中error和success方法
		var fn = {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			},
			success : function(data, textStatus) {
			}
		};
		if (opt.error) {
			fn.error = opt.error;
		}
		if (opt.success) {
			fn.success = opt.success;
		}
		// 扩展增强处理
		var _opt = $.extend(opt, {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// 错误方法增强处理

				fn.error(XMLHttpRequest, textStatus, errorThrown);
			},
			success : function(data, textStatus) {
				// 成功回调方法增强处理
				if (!data.success && data.message === "login") {
					showLoginWindow();
				} else {
					fn.success(data, textStatus);
				}

			}
		});
		return _ajax(_opt);
	};
})(jQuery);

(function($) {
	$._ajax = function(opt) {
		// 备份opt中error和success方法
		var fn = {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			},
			success : function(data, textStatus) {
			}
		};
		if (opt.error) {
			fn.error = opt.error;
		}
		if (opt.success) {
			fn.success = opt.success;
		}
		// 扩展增强处理
		var _opt = $.extend(opt, {
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// 错误方法增强处理
				loading();
				fn.error(XMLHttpRequest, textStatus, errorThrown);
			},
			success : function(data, textStatus) {
				loading();
				// 成功回调方法增强处理
				if (!data.success && data.message === "login") {
					showLoginWindow();
				} else {
					fn.success(data, textStatus);
				}
			}
		});
		
		loading(1);
		return jQuery.ajax(_opt);
	};
	
	$.each( [ '_get', '_post' ], function( i, method ) {
		jQuery[ method ] = function( url, data, callback, type ) {
			if ( jQuery.isFunction( data ) ) {
				type = type || callback;
				callback = data;
				data = undefined;
			}
			
			return jQuery._ajax({
				type: method.slice(1),
				url: url,
				data: data,
				success: callback,
				dataType: type
			});
		};
	});
})(jQuery);

function getPreUrl() {
	var urls = window.location.href.split( webContext );
	return urls[ urls.length - 1 ];
}

function showLoginWindow() {
	
	location.href = webContext + "/user/toLogin?url=";
}