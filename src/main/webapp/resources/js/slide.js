$(function () {
    var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
    var len = $("#focus ul li").length; //获取焦点图个数
    var index = 0;
    var picTimer;

    //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
    var btn = "<div class='btnBg'></div><div class='btn'>";
    for (var i = 0; i < len; i++) {
        btn += "<span></span>";
    }
    $("#focus").append(btn);
    $("#focus .btnBg").css("opacity", 0.5);

    //为小按钮添加鼠标滑入事件，以显示相应的内容
    $("#focus .btn span").css("opacity", 0.4).mouseenter(function () {
        index = $("#focus .btn span").index(this);
        showPics(index);
    }).eq(0).trigger("mouseenter");

    //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
    $("#focus ul").css("width", sWidth * (len));

    //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
    $("#focus").hover(function () {
        clearInterval(picTimer);
    }, function () {
        picTimer = setInterval(function () {
            showPics(index);
            index++;
            if (index == len) { index = 0; }
        }, 5000); //此4000代表自动播放的间隔，单位：毫秒
    }).trigger("mouseleave");

    //显示图片函数，根据接收的index值显示相应的内容
    function showPics(index) { //普通切换
        var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
        $("#focus ul").stop(true, false).animate({ "left": nowLeft }, 300); //通过animate()调整ul元素滚动到计算出的position
        $("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
        $("#focus .btn span").stop(true, false).animate({ "opacity": "0.4" }, 300).eq(index).stop(true, false).animate({ "opacity": "1" }, 300); //为当前的按钮切换到选中的效果
    }
});





//document.write('<div id="qqcalling" style="width:110px">');
//document.write('<div style="text-align:right;padding-right:5px;cursor: pointer;font-size:12px;color:#000067;" onclick="document.getElementById(\'qqcalling\').style.display=\'none\'">关闭</div>');
//document.write('<div><a href="" target="_blank"><img src="http://g.100xuexi.com/CssModel/XXMain/Images/jianzhi.gif" width="110" height="90"  /></a></div>');
//document.write('</div>');
var Class = {
   create: function() {
     return function() {
       this.initialize.apply(this, arguments);
     }
   }
}
Function.prototype.bind = function() {
   var __method = this, args = $A(arguments), object = args.shift();
   return function() {
     return __method.apply(object, args.concat($A(arguments)));
   }
}
var $A = Array.from = function(iterable) {
   if (!iterable) return [];
   if (iterable.toArray) {
     return iterable.toArray();
   } else {
     var results = [];
     for (var i = 0; i < iterable.length; i++)
       results.push(iterable[i]);
     return results;
   }
}
var Float = Class.create();
Float.prototype = {
    initialize: function (elem, options) {
        try {
            this.toDo = options.toDo || function () { },
            this.bodyScrollTop = document.documentElement.scrollTop || document.body.scrollTop,
            this.bodyScrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft,
            this.element = document.getElementById(elem);
            this.dely = options.dely || 500;
            this.top = options.top || 0;
            this.left = options.left || 0;
        } catch (e) { }
},
start:function(){
   if(!this.element){
    ('please set a element first!');
    return false;
   }
   this.element.style.position = 'absolute';
   this.toDo();
   setInterval(this.toDo.bind(this),this.dely)
}
}
var f = new Float('qqcalling',{dely:100,
toDo:function(){
var isIE = document.all && window.external;
this.bodyScrollTop = document.documentElement.scrollTop || document.body.scrollTop;
this.bodyScrollLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
if(isIE){
   this.docWidth = document.documentElement.clientWidth || document.body.clientWidth;
   this.docHeight = document.documentElement.clientHeight || document.body.clientHeight;
}else{
   this.docWidth = (document.body.clientWidth > document.documentElement.clientWidth)?document.documentElement.clientWidth:document.body.clientWidth;
   this.docHeight = (document.body.clientHeight > document.documentElement.clientHeight)?document.documentElement.clientHeight:document.body.clientHeight;
}
this.element.style.top = 300 + parseInt(this.bodyScrollTop, 10) + 'px';
this.element.style.left = (this.docWidth - parseInt(this.element.offsetWidth,10)) + parseInt(this.bodyScrollLeft, 10) + 'px';
}
});
f.start();