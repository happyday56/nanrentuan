$.extend({

});

$.fn.extend({
	trim : function() {
		return $.trim(this.val());
	},
	lTrim : function() {
		return this.val().replace(/^\s+/, '');
	},
	rTrim : function() {
		return this.val().replace(/\s+$/, '');
	},

	setDisabled : function(disabled) {
		return this.each(function() {
			$(this).attr('disabled', disabled).css('opacity',
					disabled ? 0.5 : 1.0);
		});
	},
	setReadOnly : function(readonly) {
		return this.each(function() {
			$(this).attr('readonly', readonly).css('opacity',
					readonly ? 0.5 : 1.0);
		});
	},
	setChecked : function(checked, value) {
		return this.each(function() {
			if (value == undefined) {
				$(this).attr('checked', checked);
			} else if ($(this).val() == value.toString()) {
				$(this).attr('checked', checked);
			}
		});
	}
});

var J = J || {};

$
		.extend(
				J,
				{
					IsIE : $.browser.msie != undefined,
					IsIE6 : $.browser.msie && parseInt($.browser.version) === 6,

					CopyText : function(obj) {
						var str = J.IsElement(obj) ? obj.value
								: ($(obj).size() > 0 ? $(obj).val() : obj);
						if (window.clipboardData && clipboardData.setData
								&& window.clipboardData.setData("Text", str)) {
							return true;
						} else {
							if (J.IsElement(obj))
								o.select();
							return false;
						}
					},
					AddBookMark : function(url, title) {
						try {
							if (window.sidebar) {
								window.sidebar.addPanel(title, url, '');
							} else if (J.IsIE) {
								window.external.AddFavorite(url, title);
							} else if (window.opera && window.print) {
								return true;
							}
						} catch (e) {
							alert("Your browser does not support it.");
						}
					},
					SetHomePage : function(url) {
						try {
							document.body.style.behavior = 'url(#default#homepage)';
							document.body.setHomePage(url);
						} catch (e) {
							if (window.netscape) {
								try {
									netscape.security.PrivilegeManager
											.enablePrivilege("UniversalXPConnect");
								} catch (e) {
									alert("Your browser does not support it.");
								}
								var prefs = Components.classes['@mozilla.org/preferences-service;1']
										.getService(Components.interfaces.nsIPrefBranch);
								prefs.setCharPref('browser.startup.homepage',
										url);
							}
						}
					},

					GetCookie : function(name) {
						var r = new RegExp('(^|;|\\s+)' + name
								+ '=([^;]*)(;|$)');
						var m = document.cookie.match(r);
						return (!m ? '' : decodeURIComponent(m[2]));
					},
					SetCookie : function(name, value, expire, domain, path) {
						var s = name + '=' + encodeURIComponent(value);
						if (!J.IsUndefined(path))
							s = s + '; path=' + path;
						if (expire > 0) {
							var d = new Date();
							d.setTime(d.getTime() + expire * 1000);
							if (!J.IsUndefined(domain))
								s = s + '; domain=' + domain;
							s = s + '; expires=' + d.toGMTString();
						}
						document.cookie = s;
					},
					RemoveCookie : function(name, domain, path) {
						var s = name + '=';
						if (!J.IsUndefined(domain))
							s = s + '; domain=' + domain;
						if (!J.IsUndefined(path))
							s = s + '; path=' + path;
						s = s + '; expires=Fri, 02-Jan-1970 00:00:00 GMT';
						document.cookie = s;
					},

					IsUndefined : function(obj) {
						return typeof obj == 'undefined';
					},
					IsObject : function(obj) {
						return typeof obj == 'object';
					},
					IsNumber : function(obj) {
						return typeof obj == 'number';
					},
					IsString : function(obj) {
						return typeof obj == 'string';
					},
					IsElement : function(obj) {
						return obj && obj.nodeType == 1;
					},
					IsFunction : function(obj) {
						return typeof obj == 'function';
					},
					IsArray : function(obj) {
						return Object.prototype.toString.call(obj) === '[object Array]';
					},

					IsInt : function(str) {
						return /^-?\d+$/.test(str);
					},
					IsFloat : function(str) {
						return /^(-?\d+)(\.\d+)?$/.test(str);
					},
					IsIntPositive : function(str) {
						return /^[0-9]*[1-9][0-9]*$/.test(str);
					},
					IsFloatPositive : function(str) {
						return /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/
								.test(str);
					},
					IsLetter : function(str) {
						return /^[A-Za-z]+$/.test(str);
					},
					IsChinese : function(str) {
						return /^[\u0391-\uFFE5]+$/.test(str);
					},
					IsZipCode : function(str) {
						return /^[1-9]\d{5}$/.test(str);
					},
					IsEmail : function(str) {
						return /^[A-Z_a-z0-9-\.]+@([A-Z_a-z0-9-]+\.)+[a-z0-9A-Z]{2,4}$/
								.test(str);
					},
					IsMobile : function(str) {
						return /^(1)\d{10}$/.test(str);
					},
					IsTel : function(str) {
						return /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/
								.test(str);
					},
					IsUrl : function(str) {
						return /^(http:|ftp:)\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"])*$/
								.test(str);
					},
					IsIpAddress : function(str) {
						return /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/
								.test(str);
					},

					Encode : function(str) {
						return encodeURIComponent(str);
					},
					Decode : function(str) {
						return decodeURIComponent(str);
					},
					FormatString : function() {
						if (arguments.length == 0)
							return '';
						if (arguments.length == 1)
							return arguments[0];
						var args = J.CloneArray(arguments);
						args.splice(0, 1);
						return arguments[0].replace(/{(\d+)?}/g, function($0,
								$1) {
							return args[parseInt($1)];
						});
					},

					EscapeHtml : function(str) {
						return str.replace(/&/g, "&amp;").replace(/</g, "&lt;")
								.replace(/>/g, "&gt;");
					},
					UnEscapeHtml : function(str) {
						return str.replace(/&lt;/g, "<").replace(/&gt;/g, ">")
								.replace(/&nbsp;/g, " ").replace(/&quot;/g,
										"\"").replace(/&amp;/g, "&");
					},
					FilterHtml : function(str) {
						str = str.replace(/\<(.*?)\>/g, '', str);
						str = str.replace(/\<\/(.*?)\>/g, '', str);
						return str;
					},

					CloneArray : function(arr) {
						var cloned = [];
						for (var i = 0, j = arr.length; i < j; i++) {
							cloned[i] = arr[i];
						}
						return cloned;
					},
					open : function(str) {
						window.location.href = str;
					},
					GetKeyCode : function(e) {
						var evt = window.event || e;
						return evt.keyCode ? evt.keyCode
								: evt.which ? evt.which : evt.charCode;
					},
					CertainNumber : function(event) {
						var e = event ? event : (window.event ? window.event
								: null);
						if (e.keyCode == 13) {
							e.keyCode = 9;
						}
						if ((e.keyCode < 48 || e.keyCode > 57)) {
							if ((e.keyCode != 8)) {
								if ((e.keyCode < 96 || e.keyCode > 105)) {
									event.returnValue = false;
								}
							}
						}
					},
					EnterSubmit : function(e, v) {
						if (J.GetKeyCode(e) == 13) {
							if (J.IsFunction(v)) {
								v();
							} else if (J.IsString(v)) {
								$(v)[0].click();
							}
						}
					},
					CtrlEnterSubmit : function(e, v) {
						var evt = window.event || e;
						if (evt.ctrlKey && J.GetKeyCode(evt) == 13) {
							if (J.IsFunction(v)) {
								v();
							} else if (J.IsString(v)) {
								$(v)[0].click();
							}
						}
					},

					GetUrlQuery : function(key, Decode, url) {
						url = url || window.location.href;
						if (url.indexOf("#") !== -1)
							url = url.substring(0, url.indexOf("#"));
						var rts = [], rt;
						queryReg = new RegExp("(^|\\?|&)" + key
								+ "=([^&]*)(?=&|#|$)", "g");
						while ((rt = queryReg.exec(url)) != null) {
							if (Decode && Decode == true)
								rts.push(DecodeURIComponent(rt[2]));
							else
								rts.push(rt[2]);
						}
						return rts.length == 0 ? '' : (rts.length == 1 ? rts[0]
								: rts);
					},
					UtcToDateString : function(utcTime, format) {
						return new Date(utcTime).format(format);
					},
					UtcToDateString : function(utcTime) {
						return new Date(utcTime).format("yyyy-MM-dd hh:mm:ss");
					},
					StringToDate:function(time){
						return new Date(time.replace(/\-/g, "\/"));
					},
					AjaxJsonGet : function(url, data, success, error, option) {
						var op = {
							type : 'GET',
							url : url,
							data : data,
							dataType : 'json',
							cache : false,
							success : success,
							error : error
						};

						$.extend(op, option);
						$.ajax(op);
					},
					AjaxJsonPost : function(url, data, success, error, option) {
						var op = {
							type : 'POST',
							url : url,
							contentType:"application/json",
							data : data,
							dataType : 'json',
							cache : false,
							success : success,
							error : error
						};

						$.extend(op, option);
						$.ajax(op);
					},
					AjaxJsonPut : function(url, data, success, error, option) {
						var op = {
							type : 'POST',
							url : url,
							data : data,
							dataType : 'json',
							cache : false,
							success : success,
							error : error
						};

						$.extend(op, option);
						$.ajax(op);
					},
					AjaxJsonDelete : function(url, data, success, error, option) {
						var op = {
							type : 'POST',
							url : url,
							data : data,
							dataType : 'json',
							cache : false,
							success : success,
							error : error
						};

						$.extend(op, option);
						$.ajax(op);
					},
					AjaxJson : function(url, data, success, error, option) {
						var op = {
							type : 'POST',
							url : url,
							data : data,
							dataType : 'json',
							cache : false,
							success : success,
							error : error
						};

						$.extend(op, option);
						$.ajax(op);
					},
					AjaxText : function(url, data, success, error, option) {
						var op = {
							type : 'POST',
							url : url,
							data : data,
							dataType : 'text',
							cache : false,
							success : success,
							error : error
						};

						$.extend(op, option);
						$.ajax(op);
					},
					GetJson : function(url, data, success, error, option) {
						var op = {
							type : "get",
							async : false,
							url : url,
							data : data,
							dataType : "jsonp",
							jsonpCallback : "jsonpcallback",
							success : function(d, textStatus) {
								if (d == null || d == undefined) {
									if (typeof error == 'function')
										error();
								} else {
									if (typeof error == 'function')
										success(d, textStatus);
								}
							},
							error : error
						};
						$.extend(op, option);
						$.ajax(op);
					},
					GetAjax : function(url, data, success, error, type, option) {
						type = type == "post" ? type : "get";
						var op = {
							type : type,
							url : url,
							data : data,
							success : function(d, textStatus) {
								if (d == null || d == undefined) {
									if (typeof error == 'function')
										error();
								} else {
									if (typeof error == 'function')
										success(d, textStatus);
								}
							},
							error : error
						};
						$.extend(op, option);
						$.ajax(op);
					},

					PreloadImages : function() {
						for (var i = 0; i < arguments.length; i++) {
							var img = new Image();
							img.src = arguments[i];
						}
					},
					getQueryString : function(strName) {
						// /<summary>
						// /获取url 参数
						// /</summary>
						var strHref = window.document.location.href;
						var intPos = strHref.indexOf("?");
						var strRight = strHref.substr(intPos + 1);
						var arrTmp = strRight.split("&");
						for (var i = 0; i < arrTmp.length; i++) {
							var arrTemp = arrTmp[i].split("=");
							if (arrTemp[0].toUpperCase() == strName
									.toUpperCase())
								return arrTemp[1];
						}
						if (arguments.length == 1)
							return "";
						if (arguments.length == 2)
							return arguments[1];
					},
					PopupIFrame : function(url, PopupTitle, width, height,
							iframeID, buttons, iframeScrolling, bottomText,
							callback) {
						// /<summary>
						// /弹出层（iframe）,显示页面必须有submitMenu函数
						// /</summary>
						// /<param name="url" type="String">弹出层显示页面地址</param>
						// /<param name="PopupTitle" type="String">弹出层标题</param>
						// /<param name="width" type="Number">弹出层宽度</param>
						// /<param name="height" type="Number">弹出层高度</param>
						// /<param name="iframeID" type="String">弹出层ID</param>
						// /<param name="buttons" type="jQuery">弹出层显示页面地址,如：{
						// "确定": true, "关闭窗体": false }</param>
						// /<param name="iframeScrolling"
						// type="String">弹出层是否显示滚动条,如'auto','yes','no'</param>
						// /<param name="bottomText"
						// type="String">窗口的按钮左边的内容，当没有按钮时此设置无效</param>
						// /<param name="callback" type="jQuery">回调函数</param>
						$.jBox
								.open(
										"iframe:" + url,
										PopupTitle,
										width,
										height,
										{
											id : iframeID,
											buttons : buttons,
											top : "20px",
											bottomText : bottomText,
											showScrolling : false,
											iframeScrolling : iframeScrolling,
											closed : callback,
											submit : function(v, h, f) {
												if (v == true) {
													var result = $.jBox
															.getIframe(iframeID).contentWindow
															.submitMenu(); // 调用子窗口里的方法如：myFrame.window.functionName();
													if (result == -1)
														return false;
													if (callback) {
														callback(result);
													} else
														return false;
												}
												return true;
											}
										});
					}
				});

var cookie = cookie || {};
$
		.extend(
				cookie,
				{
					SetCookie : function(key, value, expires, path) {
						var domain = null;
						var url = 'http://' + document.domain;
						var doname = /http:\/\/(\w+\.){2,3}((com)|(net)|(org)|(gov\.cn)|(info)|(cc)|(com\.cn)|(net\.cn)|(org\.cn)|(name)|(biz)|(tv)|(cn)|(mobi)|(name)|(sh)|(ac)|   (io)|(tw)|(com\.tw)|(hk)|(com\.hk)|(ws)|(travel)|(us)|(tm)|(la)|(me\.uk)|(org\.uk)|(ltd\.uk)|(plc\.uk)|(in)|(eu)|(it)|(jp))/;
						var flag_domain = doname.test(url);
						if (flag_domain) {
							domain = cookie.getRealDomain(url);
						}
						expires = expires || 14;
						path = path || "/";
						$.cookie(key, value, {
							expires : expires,
							path : path,
							domain : domain
						});
					},
					getRealDomain : function(domains) {
						var redomain = '';
						var domainArray = new Array("com", "net", "org", "gov",
								"edu");
						var domains_array = domains.split('.');
						var domain_count = domains_array.length - 1;
						var flag = false;
						if (domains_array[domain_count] == 'cn') {
							for (i = 0; i < domainArray.length; i++) {
								if (domains_array[domain_count - 1] == domainArray[i]) {
									flag = true;
									break;
								}
							}
							if (flag == true) {
								redomain = domains_array[domain_count - 2]
										+ "." + domains_array[domain_count - 1]
										+ "." + domains_array[domain_count];
							} else {
								redomain = domains_array[domain_count - 1]
										+ "." + domains_array[domain_count];
							}
						} else {
							redomain = domains_array[domain_count - 1] + "."
									+ domains_array[domain_count];
						}
						return redomain;
					},
					GetCookie : function(name) {
						var r = new RegExp('(^|;|\\s+)' + name
								+ '=([^;]*)(;|$)');
						var m = document.cookie.match(r);
						var s = (!m ? '' : decodeURIComponent(m[2]));
						if (!cookie.IsUndefined(s) && s.trim())
							return s;
						if (arguments.length == 1)
							return "";
						if (arguments.length == 2)
							return arguments[1];
						return "";
					},
					RemoveCookie : function(name, path) {
						path = path || "/";
						var s = name + '=';
						if (!cookie.IsUndefined(path))
							s = s + '; path=' + path;
						s = s + '; expires=Fri, 02-Jan-1970 00:00:00 GMT';
						document.cookie = s;
					},
					IsUndefined : function(obj) {
						return typeof obj == 'undefined';
					}
				});

function setDailogCss() {
	$(".ui-widget-header").css("background", "none");
	$("#ui-dialog-title-addDailog").css("float", "none");
	$(".ui-state-default").css({
		"background" : "#3399FF",
		color : "#fff"
	}).addClass("button");
	$(".ui-widget").css("font-size", "1em");
}

Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};


String.prototype.replaceAll  = function(s1,s2){
	return this.replace(new RegExp(s1,"gm"),s2);
}
