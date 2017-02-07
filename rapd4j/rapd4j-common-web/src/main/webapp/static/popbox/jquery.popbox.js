;( function( $ ) {
	var ie6 = ( jQuery.browser.msie && parseInt( jQuery.browser.version, 10 ) < 7 && parseInt( jQuery.browser.version, 10 ) > 4 );
    if ($.proxy === undefined) {
        $.extend({
            proxy: function(fn, thisObject) {
                if (fn) {
                    proxy = function() {
                        return fn.apply(thisObject || this, arguments);
                    };
                }
                return proxy;
            }
        });
    }
    $.extend(jQuery.easing, {
        easeOutBack: function(x, t, b, c, d, s) {
            if (s == undefined)
                s = 1.70158;
            return c * ((t = t / d - 1) * t * ((s + 1) * t + s) + 1) + b;
        }
    });
    $.extend($.expr[':'], {
        value: function(a) {
            return $(a).val();
        }
    });

    function random() {
    	return new Date().getTime() + Math.ceil( Math.random() * 10000 );
    }
    
	$.fn.extend({
		popbox: function( opts, value ) {

			var options = {};
			if ( "object" === typeof opts ) {

				if ( this[0].registedPopbox ) {
					$[ this[0].popboxName ].poper.adjust( opts );
					return this;
				}

				var tempName = "popbox" + random();
				options = $.extend({
					title: null,
					name: tempName,
					mode: true,
					close: function() {
					},
					width: 420,
					height: "auto",
					draggable: true,
					autoOpen: true,
					background: "#fff",
					zIndex: 10000,
					animate: true,
					buttons: {}
				}, opts || {} );
				this[0].popboxName = tempName;
			} else if ( "string" === typeof opts && 1 === arguments.length ) {
				if ( this[0].registedPopbox ) {
					var param = {};
					param[opts] = true;
					$[ this[0].popboxName ].poper.adjust( param );
				}
				return this;
			} else if ( "string" === typeof opts && 2 === arguments.length ) {
				if ( this[0].registedPopbox ) {
					var param = {};
					if ( "open" === opts ) {
						param[opts] = true;
					} else {
						param[opts] = value;
					}
					$[ this[0].popboxName ].poper.adjust( param );
				}
				return this;
			} else {
				return this;
			}
			
            options.shake =  {
                distance: 10,
                duration: 100,
                transition: 'easeOutBack',
                loops: 2
            };
            var $obj = this;
			$[ this[0].popboxName ] = {};
			$[ this[0].popboxName ].poper = {
				options: options,
				$obj: $obj,
				esqueleto: {
	                msgbox: [],
	                titler: [],
	                wrapper: [],
	                buttoner: [],
	                buttons: [],
	                replacer: [],
	                offset: {},
	                closer: []
            	},
            	visible: false,
	            i: 0,
	            animation: false,
				overlay: {
	                create: function() {
	                    this.options = options;
	                    this.options.showDuration = 200;
                		this.options.closeDuration = 100;
                		this.options.hideOnClick = !options.mode;
	                    this.element = $('<div id="' + new Date().getTime() + '"></div>');
	                    this.element.css({
	                        'position': 'fixed',
	                        'top': 0,
	                        'left': 0,
	                        'display': 'none',
	                        'z-index': this.options.zIndex,
	                        'background-color': '#888',
                    		'opacity': 0.5
	                    });
	                    this.element.click($.proxy(function(event) {
	                        if (this.options.hideOnClick) {
	                            if (!this.options.callback === undefined) {
	                                this.options.callback();
	                            } else {
	                                this.hide();
	                            }
	                        }
	                        event.preventDefault();
	                    }, 
	                    this));
	                    this.hidden = true;
	                    this.inject();
	                    return this;
	                },
	                inject: function() { // 注入modal
	                    this.target = $(document.body);
	                    this.target.append(this.element);
	                    if (ie6) {
	                        this.element.css({
	                            'position': 'absolute'
	                        });
	                        var zIndex = parseInt(this.element.css('zIndex'));
	                        if (!zIndex) {
	                            zIndex = 1;
	                            var pos = this.element.css('position');
	                            if (pos == 'static' || !pos) {
	                                this.element.css({
	                                    'position': 'relative'
	                                });
	                            }
	                            this.element.css({
	                                'zIndex': zIndex
	                            });
	                        }
	                        zIndex = (!!(this.options.zIndex || this.options.zIndex === 0) && zIndex > this.options.zIndex) ? this.options.zIndex : zIndex - 1;
	                        if (zIndex < 0) {
	                            zIndex = 1;
	                        }
	                        this.shim = $('<iframe id="IF_' + new Date().getTime() + '" scrolling="no" frameborder=0 src=""></div>');
	                        this.shim.css({
	                            zIndex: zIndex,
	                            position: 'absolute',
	                            top: 0,
	                            left: 0,
	                            border: 'none',
	                            width: 0,
	                            height: 0,
	                            opacity: 0
	                        });
	                        this.shim.insertAfter(this.element);
	                        $('html, body').css({
	                            'height': '100%',
	                            'width': '100%',
	                            'margin-left': 0,
	                            'margin-right': 0
	                        });
	                    }
	                },
	                resize: function(x, y) {
	                    this.element.css({
	                        'height': 0,
	                        'width': 0
	                    });
	                    if (this.shim)
	                        this.shim.css({
	                            'height': 0,
	                            'width': 0
	                        });
	                    var win = {
	                        x: $(document).width(),
	                        y: $(document).height()
	                    };
	                    this.element.css({
	                        'width': '100%',
	                        'height': y ? y : win.y
	                    });
	                    if (this.shim) {
	                        this.shim.css({
	                            'height': 0,
	                            'width': 0
	                        });
	                        this.shim.css({
	                            'position': 'absolute',
	                            'left': 0,
	                            'top': 0,
	                            'width': this.element.width(),
	                            'height': y ? y : win.y
	                        });
	                    }
	                    return this;
	                },
	                show: function() {
	                    if (!this.hidden)
	                        return this;
	                    if (this.transition)
	                        this.transition.stop();
	                    this.target.bind('resize', $.proxy(this.resize, this));
	                    this.resize();
	                    if (this.shim)
	                        this.shim.css({
	                            'display': 'block'
	                        });
	                    this.hidden = false;
	                    this.transition = this.element.fadeIn(this.options.showDuration, $.proxy(function() {
	                        this.element.trigger('show');
	                    }, 
	                    this));
	                    return this;
	                },
	                hide: function() {
	                    if (this.hidden)
	                        return this;
	                    if (this.transition)
	                        this.transition.stop();
	                    this.target.unbind('resize');
	                    if (this.shim)
	                        this.shim.css({
	                            'display': 'none'
	                        });
	                    this.hidden = true;
	                    this.transition = this.element.fadeOut(this.options.closeDuration, $.proxy(function() {
	                        this.element.trigger('hide');
	                        this.element.css({
	                            'height': 0,
	                            'width': 0
	                        });
	                    }, 
	                    this));
	                    return this;
	                }
	            },

	            create: function() {
	            	this.overlay.create();
	                this.esqueleto.msgbox = $('<div class="' + this.options.name + '"></div>');
	                this.esqueleto.msgbox.css({
	                    display: 'none',
	                    position: 'absolute',
	                    top: 0,
	                    left: 0,
	                    width: this.options.width,
	                    // height: this.options.height,
	                    border: '1px solid #bababa',
	                    // overflow: 'auto',
	                    'z-index': this.options.zIndex,
	                    'word-wrap': 'break-word',
	                    '-moz-box-shadow': '0 0 15px rgba(0, 0, 0, 0.5)',
	                    '-webkit-box-shadow': '0 0 15px rgba(0, 0, 0, 0.5)',
	                    'box-shadow': '0 0 85px rgba(0, 0, 0, 0.5)',
	                    '-moz-border-radius': '6px',
	                    '-webkit-border-radius': '6px',
	                    'border-radius': '6px',
	                    'background-color': this.options.background
	                });

	                if ( this.options.title ) {
						this.esqueleto.titler = $('<div class="popbox-titler"></div>');
						this.esqueleto.msgbox.append(this.esqueleto.titler);

						this.esqueleto.titler.append('<span class="popbox-titler-text">' + this.options.title + '</span>');
						this.esqueleto.closer = $('<span class="popbox-titler-close" title="关闭">&times;</span>');
						this.esqueleto.titler.append( this.esqueleto.closer );
						this.esqueleto.titler.append( '<div style="clear:both"></div>' );
	                }

	                this.esqueleto.wrapper = $('<div class="popbox-wrapper"></div>');
	                this.esqueleto.msgbox.append(this.esqueleto.wrapper);
	                this.esqueleto.wrapper.css({
	                    height: (ie6 ? 80 : this.options.height),
	                    'min-height': 80,
	                    'zoom': 1
	                });

	                if ( this.options.buttons && !$.isEmptyObject( this.options.buttons ) ) {
	                	var buttons = [], poper = this;
	                	this.esqueleto.buttoner = $('<div class="popbox-buttoner"></div>');
	                	this.esqueleto.msgbox.append( this.esqueleto.buttoner );
	                	$.each( this.options.buttons, function( value, fn ) {
	                		buttons[ buttons.length ] = $('<input type="button" value="' + value + '" />');
	                		poper.esqueleto.buttoner.append( buttons[ buttons.length - 1 ] );
	                		buttons[ buttons.length - 1 ].bind( "click", fn);
	                	});
	                }

	                $('body').append(this.esqueleto.msgbox);
	                this.addevents();
	                return this.esqueleto.msgbox;
	            },

	            adjust: function( param ) {
					if ( param.hasOwnProperty( "width" ) ) {
						this.options.width = param.width;
						this.esqueleto.msgbox.css("width", param.width);
					}
					if ( param.hasOwnProperty( "height" ) ) {
						this.options.height = param.height;
						this.esqueleto.wrapper.css("height", (ie6 ? 80 : param.height));
					}
					if ( param.hasOwnProperty( "title" ) ) {
						if ( !this.options.title && param.title ) {
							this.esqueleto.titler = $('<div class="popbox-titler"></div>');
							this.esqueleto.msgbox.prepend(this.esqueleto.titler);

							this.esqueleto.titler.append('<span class="popbox-titler-text">' + param.title + '</span>');
							this.esqueleto.closer = $('<span class="popbox-titler-close" title="关闭">x</span>');
							this.esqueleto.titler.append( this.esqueleto.closer );
							this.esqueleto.titler.append( '<div style="clear:both"></div>' );

							this.options.title = param.title;
						}
						if ( this.options.title ) {
							if ( param.title ) {
								this.esqueleto.titler.find( 'span.popbox-titler-text' ).text( param.title );
							} else {
								this.esqueleto.titler.remove();
							}
							this.options.title = param.title;
						}
					}
					this.moveBox();
					var dom = this.$obj[0];
					if ( param.open && !dom.opening ) {
						this.show();
					}
					if ( param.close && dom.opening ) {
						this.close();
					}
					if ( param.destroy ) {
						this.destroy();
					}

	            },

	            addevents: function() {
	                $(window).bind('resize', $.proxy(function() {
	                    if (this.visible) {
	                        this.overlay.resize();
	                        this.moveBox();
	                    }
	                }, 
	                this));
	                $(window).bind('scroll', $.proxy(function() {
	                    this.moveBox();
	                }, 
	                this));
	                this.addTitleEvents();
	                this.overlay.element.bind('show', $.proxy(function() {
	                    $(this).triggerHandler('show');
	                }, 
	                this));
	                this.overlay.element.bind('hide', $.proxy(function() {
	                    $(this).triggerHandler('close');
	                }, 
	                this));
	            },
	            addTitleEvents: function() {
	            	if ( this.esqueleto.titler.length ) {
		                this.esqueleto.closer.bind('click', $.proxy(function(event) {
		                	this.close();
		                }, this));

		                if ( !this.options.draggable ) return false;
		                this.esqueleto.titler.bind('mousedown', $.proxy(function(event) {
							if (document.selection && document.selection.empty) document.selection.empty(); 
							else if (window.getSelection) window.getSelection().removeAllRanges();

							this.esqueleto.offset = {
								x: event.pageX,
								y: event.pageY
							};

							$(document).bind('mousemove', $.proxy(function(event) {
								var diff = {
									x: event.pageX - this.esqueleto.offset.x,
									y: event.pageY - this.esqueleto.offset.y
								};
								var offset = this.esqueleto.msgbox.offset();
								this.esqueleto.msgbox.css({
									"left": ( offset.left + diff.x ) + "px",
									"top": ( offset.top + diff.y ) + "px"
								});
								this.esqueleto.offset = {
									x: event.pageX,
									y: event.pageY
								};
								return false;
			                }, this));
			                $(document).bind('mouseup', function() {
			                	$(document).unbind('mousemove');
			                	$(document).unbind('mouseup');
			                });
							return false;
		                }, this));
	                }
	            },
	            show: function() {
	            	this.esqueleto.replacer = $("<input type='hidden'/>");
	            	this.$obj.after(this.esqueleto.replacer);

	                this.esqueleto.msgbox.queue(this.options.name, $.proxy(function(next) {
	                    this.esqueleto.wrapper.append( this.$obj );
	                    this.moveBox();
	                    this.visible = true;

	                    if ( this.options.mode ) {
		                    this.overlay.show();
	                    }

	                    this.esqueleto.msgbox.css({
	                        display: 'block',
	                        left: (($(document).width() - this.options.width) / 2)
	                    });
	                    this.$obj.show();
	                    this.moveBox();
	                }, 
	                this));
	                this.i++;
	                if (this.i == 1) {
	                    this.esqueleto.msgbox.dequeue(this.options.name);
	                }
	                this.$obj[0].opening = true;
	                return this;
	            },
	            toArguments: function(array) {
	                return $.map(array, 
	                function(a) {
	                    return $(a).val();
	                });
	            },
	            moveBox: function() {
	                var size = {
	                    x: $(window).width(),
	                    y: $(window).height()
	                };
	                var scroll = {
	                    x: $(window).scrollLeft(),
	                    y: $(window).scrollTop()
	                };
	                var height = this.esqueleto.msgbox.outerHeight();
	                var y = 0;
	                var x = 0;
	                y = scroll.x + ((size.x - this.options.width) / 2);
	                if (this.options.emergefrom == "bottom") {
	                    x = (scroll.y + size.y + 80);
	                } else {
	                    x = (scroll.y - height) - 80;
	                }
	                if (this.visible) {

	                	if ( this.options.animate ) {
	                		if (this.animation) {
		                        this.animation.stop;
		                    }
		                    
		                    this.animation = this.esqueleto.msgbox.animate({
		                        left: y,
		                        top: scroll.y + ((size.y - height) / 2)
		                    }, 
		                    {
		                        duration: this.options.moveDuration,
		                        queue: false,
		                        easing: 'easeOutBack'
		                    });
	                	} else {
	                		this.esqueleto.msgbox.css({
		                    	left: y,
		                        top: scroll.y + ((size.y - height) / 2)
		                    });
	                	}
	                    
	                } else {
	                    this.esqueleto.msgbox.css({
	                        top: x,
	                        left: y
	                    });
	                }
	            },
	            close: function(param) {
	                this.esqueleto.msgbox.css({
	                    display: 'none',
	                    top: 0
	                });
	                this.visible = false;
	                if ($.isFunction(this.callback)) {
	                    this.callback.apply(this, $.makeArray(param));
	                }
	                setTimeout($.proxy(function() {
	                    this.i--;
	                    this.esqueleto.msgbox.dequeue(this.options.name);
	                }, 
	                this), this.options.closeDuration);
	                if (this.i == 1) {
	                    this.overlay.hide();
	                }
	                this.moveBox();

	                this.options.close();
	                this.$obj[0].opening = false;
	                return this;
	            },
	            destroy: function() {
	            	this.esqueleto.replacer.before( this.$obj );
	            	this.esqueleto.replacer.remove();
	            	this.$obj.hide();
	            	this.$obj[0].registedPopbox = false;
	            	this.$obj[0].opening = false;
	            	this.overlay.element.remove();
	            	this.esqueleto.msgbox.remove();
	            },
	            shake: function() {
	                var x = this.options.shake.distance;
	                var d = this.options.shake.duration;
	                var t = this.options.shake.transition;
	                var o = this.options.shake.loops;
	                var l = this.esqueleto.msgbox.position().left;
	                var e = this.esqueleto.msgbox;
	                for (i = 0; i < o; i++) {
	                    e.animate({
	                        left: l + x
	                    }, 
	                    d, t);
	                    e.animate({
	                        left: l - x
	                    }, 
	                    d, t);
	                }
	                e.animate({
	                    left: l + x
	                }, 
	                d, t);
	                e.animate({
	                    left: l
	                }, 
	                d, t);
	            }
			};

			if ( !this[0].registedPopbox ) {
				$[ this[0].popboxName ].poper.create();
				this[0].registedPopbox = true;
				this[ 0 ].opening = false;
			}

			if ( options.autoOpen ) {
				$[ this[0].popboxName ].poper.show();
				this[ 0 ].opening = true;
			}
		}
	});

	$.extend({
        MsgBoxConfig: function(options) {
            var defaults = {
                name: 'jquery-msgbox',
                formaction: '#',
                zIndex: 10000,
                width: 420,
                height: 'auto',
                background: '#FFFFFF',
                modal: true,
                overlay: {
                    'background-color': '#000000',
                    'opacity': 0.5
                },
                showDuration: 200,
                closeDuration: 100,
                moveDuration: 500,
                emergefrom: 'top',
                shake: {
                    distance: 10,
                    duration: 100,
                    transition: 'easeOutBack',
                    loops: 2
                }
            };
            if ($.aerOptions === undefined) {
                return $.aerOptions = $.extend(true, defaults, options);
            } else {
                return $.aerOptions = $.extend(true, $.aerOptions, options);
            }
        },
        MsgBoxObject: {
            options: {},
            esqueleto: {
                msgbox: [],
                wrapper: [],
                form: [],
                buttons: [],
                inputs: []
            },
            visible: false,
            i: 0,
            animation: false,
            overlay: {
                create: function(options) {
                    this.options = options;
                    this.element = $('<div id="' + new Date().getTime() + '"></div>');
                    this.element.css($.extend({}, 
                    {
                        'position': 'fixed',
                        'top': 0,
                        'left': 0,
                        'opacity': 0,
                        'display': 'none',
                        'z-index': this.options.zIndex
                    }, 
                    this.options.style));
                    this.element.click($.proxy(function(event) {
                        if (this.options.hideOnClick) {
                            if (!this.options.callback === undefined) {
                                this.options.callback();
                            } else {
                                this.hide();
                            }
                        }
                        event.preventDefault();
                    }, 
                    this));
                    this.hidden = true;
                    this.inject();
                    return this;
                },
                inject: function() {
                    this.target = $(document.body);
                    this.target.append(this.element);
                    if (ie6) {
                        this.element.css({
                            'position': 'absolute'
                        });
                        var zIndex = parseInt(this.element.css('zIndex'));
                        if (!zIndex) {
                            zIndex = 1;
                            var pos = this.element.css('position');
                            if (pos == 'static' || !pos) {
                                this.element.css({
                                    'position': 'relative'
                                });
                            }
                            this.element.css({
                                'zIndex': zIndex
                            });
                        }
                        zIndex = (!!(this.options.zIndex || this.options.zIndex === 0) && zIndex > this.options.zIndex) ? this.options.zIndex : zIndex - 1;
                        if (zIndex < 0) {
                            zIndex = 1;
                        }
                        this.shim = $('<iframe id="IF_' + new Date().getTime() + '" scrolling="no" frameborder=0 src=""></div>');
                        this.shim.css({
                            zIndex: zIndex,
                            position: 'absolute',
                            top: 0,
                            left: 0,
                            border: 'none',
                            width: 0,
                            height: 0,
                            opacity: 0
                        });
                        this.shim.insertAfter(this.element);
                        $('html, body').css({
                            'height': '100%',
                            'width': '100%',
                            'margin-left': 0,
                            'margin-right': 0
                        });
                    }
                },
                resize: function(x, y) {
                    this.element.css({
                        'height': 0,
                        'width': 0
                    });
                    if (this.shim)
                        this.shim.css({
                            'height': 0,
                            'width': 0
                        });
                    var win = {
                        x: $(document).width(),
                        y: $(document).height()
                    };
                    this.element.css({
                        'width': '100%',
                        'height': y ? y : win.y
                    });
                    if (this.shim) {
                        this.shim.css({
                            'height': 0,
                            'width': 0
                        });
                        this.shim.css({
                            'position': 'absolute',
                            'left': 0,
                            'top': 0,
                            'width': this.element.width(),
                            'height': y ? y : win.y
                        });
                    }
                    return this;
                },
                show: function() {
                    if (!this.hidden)
                        return this;
                    if (this.transition)
                        this.transition.stop();
                    this.target.bind('resize', $.proxy(this.resize, this));
                    this.resize();
                    if (this.shim)
                        this.shim.css({
                            'display': 'block'
                        });
                    this.hidden = false;
                    this.transition = this.element.fadeIn(this.options.showDuration, $.proxy(function() {
                        this.element.trigger('show');
                    }, 
                    this));
                    return this;
                },
                hide: function() {
                    if (this.hidden)
                        return this;
                    if (this.transition)
                        this.transition.stop();
                    this.target.unbind('resize');
                    if (this.shim)
                        this.shim.css({
                            'display': 'none'
                        });
                    this.hidden = true;
                    this.transition = this.element.fadeOut(this.options.closeDuration, $.proxy(function() {
                        this.element.trigger('hide');
                        this.element.css({
                            'height': 0,
                            'width': 0
                        });
                    }, 
                    this));
                    return this;
                }
            },
            create: function() {
                this.options = $.MsgBoxConfig();
                this.overlay.create({
                    style: this.options.overlay,
                    hideOnClick: !this.options.modal,
                    zIndex: this.options.zIndex - 1,
                    showDuration: this.options.showDuration,
                    closeDuration: this.options.closeDuration
                });
                this.esqueleto.msgbox = $('<div class="' + this.options.name + '"></div>');
                this.esqueleto.msgbox.css({
                    display: 'none',
                    position: 'absolute',
                    top: 0,
                    left: 0,
                    width: this.options.width,
                    height: this.options.height,
                    'z-index': this.options.zIndex,
                    'word-wrap': 'break-word',
                    '-moz-box-shadow': '0 0 15px rgba(0, 0, 0, 0.5)',
                    '-webkit-box-shadow': '0 0 15px rgba(0, 0, 0, 0.5)',
                    'box-shadow': '0 0 15px rgba(0, 0, 0, 0.5)',
                    '-moz-border-radius': '6px',
                    '-webkit-border-radius': '6px',
                    'border-radius': '6px',
                    'background-color': this.options.background
                });
                this.esqueleto.wrapper = $('<div class="' + this.options.name + '-wrapper"></div>');
                this.esqueleto.msgbox.append(this.esqueleto.wrapper);
                this.esqueleto.form = $('<form action="' + this.options.formaction + '" method="post"></form>');
                this.esqueleto.wrapper.append(this.esqueleto.form);
                this.esqueleto.wrapper.css({
                    height: (ie6 ? 80 : 'auto'),
                    'min-height': 80,
                    'zoom': 1
                });
                $('body').append(this.esqueleto.msgbox);
                this.addevents();
                return this.esqueleto.msgbox;
            },
            addevents: function() {
                $(window).bind('resize', $.proxy(function() {
                    if (this.visible) {
                        this.overlay.resize();
                        this.moveBox();
                    }
                }, 
                this));
                $(window).bind('scroll', $.proxy(function() {
                    this.moveBox();
                }, 
                this));
                this.esqueleto.msgbox.bind('keydown', $.proxy(function(event) {
                    if (event.keyCode == 27) {
                        this.close(false);
                    }
                }, 
                this));
                this.esqueleto.form.bind('submit', $.proxy(function(event) {
                    event.preventDefault();
                }, 
                this));
                this.overlay.element.bind('show', $.proxy(function() {
                    $(this).triggerHandler('show');
                }, 
                this));
                this.overlay.element.bind('hide', $.proxy(function() {
                    $(this).triggerHandler('close');
                }, 
                this));
            },
            show: function(txt, options, callback) {
                var types = ['alert', 'info', 'error', 'prompt', 'confirm'];
                this.esqueleto.msgbox.queue(this.options.name, $.proxy(function(next) {
                    options = $.extend(true, {
                        type: 'alert'
                    }, 
                    options || {});
                    if (options.buttons === undefined) {
                        if (options.type == 'confirm' || options.type == 'prompt') {
                            var buttons = [{
                                    type: 'submit',
                                    value: '确定'
                                }, 
                                {
                                    type: 'cancel',
                                    value: '取消'
                                }];
                        } else {
                            var buttons = [{
                                    type: 'submit',
                                    value: '确定'
                                }];
                        }
                    } else {
                        var buttons = options.buttons;
                    }
                    if (options.inputs === undefined && options.type == 'prompt') {
                        var inputs = [{
                                type: 'text',
                                name: 'prompt',
                                value: ''
                            }];
                    } else {
                        var inputs = options.inputs;
                    }
                    this.callback = $.isFunction(callback) ? callback : function(e) {};
                    if (inputs !== undefined) {
                        this.esqueleto.inputs = $('<div class="' + this.options.name + '-inputs"></div>');
                        this.esqueleto.form.append(this.esqueleto.inputs);
                        $.each(inputs, $.proxy(function(i, input) {
                            if (input.type == 'text' || input.type == 'password') {
                                iLabel = input.label ? '<label class="' + this.options.name + '-label">' + input.label : '';
                                fLabel = input.label ? '</label>' : '';
                                input.value = input.value === undefined ? '' : input.value;
                                iRequired = input.required === undefined || input.required == false ? '' : 'required="true"';
                                this.esqueleto.inputs.append($(iLabel + '<input type="' + input.type + '" name="' + this.options.name + '-label-' + i + '" value="' + input.value + '" autocomplete="off" ' + iRequired + '/>' + fLabel));
                            } else if (input.type == 'checkbox') {
                                iLabel = input.label ? '<label class="' + this.options.name + '-label">' : '';
                                fLabel = input.label ? input.label + '</label>' : '';
                                input.value = input.value === undefined ? '1' : input.value;
                                this.esqueleto.inputs.append($(iLabel + '<input type="' + input.type + '" style="display:inline; width:auto;" name="' + this.options.name + '-label-' + i + '" value="' + input.value + '" autocomplete="off"/> ' + fLabel));
                            }
                        }, 
                        this));
                    }
                    this.esqueleto.buttons = $('<div class="' + this.options.name + '-buttons"></div>');
                    this.esqueleto.form.append(this.esqueleto.buttons);
                    if (options.type == 'alert' || options.type == 'info' || options.type == 'error' || options.type == 'confirm') {
                        $.each(buttons, $.proxy(function(i, button) {
                            
                            if (button.type == 'submit') {
                                this.esqueleto.buttons.append($('<button type="submit">' + button.value + '</button>').bind('click', $.proxy(function(e) {
                                    this.close(button.value);
                                    e.preventDefault();
                                }, 
                                this)));
                            } else if (button.type == 'cancel') {
                                this.esqueleto.buttons.append($('<button type="button">' + button.value + '</button>').bind('click', $.proxy(function(e) {
                                    this.close(false);
                                    e.preventDefault();
                                }, 
                                this)));
                            }
                        }, 
                        this));
                    } else if (options.type == 'prompt') {
                        $.each(buttons, $.proxy(function(i, button) {
                            if (button.type == 'submit') {
                                this.esqueleto.buttons.append($('<button type="submit">' + button.value + '</button>').bind('click', $.proxy(function(e) {
                                    if ($('input[required="true"]:not(:value)').length > 0) {
                                        $('input[required="true"]:not(:value):first').focus();
                                        this.shake();
                                    } else {
                                        this.close(this.toArguments($('input', this.esqueleto.inputs)));
                                    }
                                    e.preventDefault();
                                }, 
                                this)));
                            } else if (button.type == 'cancel') {
                                this.esqueleto.buttons.append($('<button type="button">' + button.value + '</button>').bind('click', $.proxy(function(e) {
                                    this.close(false);
                                    e.preventDefault();
                                }, 
                                this)));
                            }
                        }, 
                        this));
                    }
                    this.esqueleto.form.prepend(txt);
                    $.each(types, $.proxy(function(i, e) {
                        this.esqueleto.wrapper.removeClass(this.options.name + '-' + e);
                    }, 
                    this));
                    this.esqueleto.wrapper.addClass(this.options.name + '-' + options.type);
                    this.moveBox();
                    this.visible = true;
                    this.overlay.show();
                    this.esqueleto.msgbox.css({
                        display: 'block',
                        left: (($(document).width() - this.options.width) / 2)
                    });
                    this.moveBox();
                    setTimeout($.proxy(function() {
                        var b = $('input, button', this.esqueleto.msgbox);
                        if (b.length) {
                            b.get(0).focus();
                        }
                    }, 
                    this), this.options.moveDuration);
                }, 
                this));
                this.i++;
                if (this.i == 1) {
                    this.esqueleto.msgbox.dequeue(this.options.name);
                }
            },
            toArguments: function(array) {
                return $.map(array, 
                function(a) {
                    return $(a).val();
                });
            },
            moveBox: function() {
                var size = {
                    x: $(window).width(),
                    y: $(window).height()
                };
                var scroll = {
                    x: $(window).scrollLeft(),
                    y: $(window).scrollTop()
                };
                var height = this.esqueleto.msgbox.outerHeight();
                var y = 0;
                var x = 0;
                y = scroll.x + ((size.x - this.options.width) / 2);
                if (this.options.emergefrom == "bottom") {
                    x = (scroll.y + size.y + 80);
                } else {
                    x = (scroll.y - height) - 80;
                }
                if (this.visible) {
                    if (this.animation) {
                        this.animation.stop;
                    }
                    this.animation = this.esqueleto.msgbox.animate({
                        left: y,
                        top: scroll.y + ((size.y - height) / 2)
                    }, 
                    {
                        duration: this.options.moveDuration,
                        queue: false,
                        easing: 'easeOutBack'
                    });
                } else {
                    this.esqueleto.msgbox.css({
                        top: x,
                        left: y
                    });
                }
            },
            close: function(param) {
                this.esqueleto.msgbox.css({
                    display: 'none',
                    top: 0
                });
                this.visible = false;
                if ($.isFunction(this.callback)) {
                    this.callback.apply(this, $.makeArray(param));
                }
                setTimeout($.proxy(function() {
                    this.i--;
                    this.esqueleto.msgbox.dequeue(this.options.name);
                }, 
                this), this.options.closeDuration);
                if (this.i == 1) {
                    this.overlay.hide();
                }
                this.moveBox();
                this.esqueleto.form.empty();
            },
            shake: function() {
                var x = this.options.shake.distance;
                var d = this.options.shake.duration;
                var t = this.options.shake.transition;
                var o = this.options.shake.loops;
                var l = this.esqueleto.msgbox.position().left;
                var e = this.esqueleto.msgbox;
                for (i = 0; i < o; i++) {
                    e.animate({
                        left: l + x
                    }, 
                    d, t);
                    e.animate({
                        left: l - x
                    }, 
                    d, t);
                }
                ;
                e.animate({
                    left: l + x
                }, 
                d, t);
                e.animate({
                    left: l
                }, 
                d, t);
            }
        },
        msgbox: function(txt, options, callback) {
            return $.MsgBoxObject.show(txt, options, callback);
        }
    });
    $(function() {
        $.MsgBoxObject.create();
    });
})( jQuery );