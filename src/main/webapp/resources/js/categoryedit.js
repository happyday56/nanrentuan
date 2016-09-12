$(function () {
    var href = window.location.href;
    if (href.indexOf("edit")) {

    }
    else {

    }
});


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
                alert("保存成功");
                window.location = "articlelist";
            } else {
                alert(data);
            }
        });
});
