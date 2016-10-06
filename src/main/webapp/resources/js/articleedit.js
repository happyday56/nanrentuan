var id = J.getQueryString("id");
var oper = J.getQueryString("oper");
var model = {};

$(function () {
    $("#uploadTime").datetimepicker({
        changeMonth: true,
        changeYear: true,
        timeFormat: 'hh:mm:00',
        stepHour: 2,
        stepMinute: 10,
        stepSecond: 10,
        dayNamesMin: ["七", "一", "二", "三", "四", "五", "六"],
        monthNamesShort: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"]
    });

    if (oper == "edit") {
        J.AjaxJsonPost("articleedit.do?id=" + id, "",
            function (data) {
                $("#id").html(data.id);
                $("#title").val(data.title);
                $("#category").val(data.categoryId);
                editor.html(data.content);
                $("#summary").val(data.summary);
                $("#keywords").val(data.keywords);
                $("#description").val(data.description);
                $("#uploadTime").val(J.UtcToDateString(data.uploadTime));
                $("#views").val(data.views);
                $("#pictureUrl").val(data.pictureUrl);
                $("#imgPictureUrl").attr("src", data.pictureUrl);
                initCategory(data.categories, data.categoryId);
            });
    }
    else {
        J.AjaxJsonPost("articleedit.do", "",
            function (data) {
                initCategory(data, 0);
            });
    }

    $("#btnSave").click(function () {
        model.operType = oper;
        model.id = $("#id").html();
        if (model.id == "") model.id = "0";
        model.title = $("#title").val();
        model.categoryId = $("#category").val();
        model.content = $.trim(editor.html());
        model.summary = $("#summary").val();
        model.keywords = $("#keywords").val();
        model.description = $("#description").val();
        model.uploadTime = J.StringToDate($("#uploadTime").val());
        model.views = $("#views").val();
        model.pictureUrl = $("#pictureUrl").val();
        J.AjaxJsonPost("articleedit.save", JSON.stringify(model),
            function (data) {
                if (data == 1) {
                    window.location = "articlelist";
                } else {
                    alert(data);
                }
            });
    });
});

function initCategory(list, id) {
    var html = "";
    for (var i = 0; i < list.length; i++) {
        html += '<option value="' + list[i].id + '" ' + (id == list[i].id ? 'selected="selected"' : '') + '>' + list[i].name + '</option>';
    }
    $("#category").html(html);
}