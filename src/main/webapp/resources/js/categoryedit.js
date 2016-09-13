$(function () {
    $("#btnSave").click(function () {
        var model = {};
        var href = window.location.href;
        if (href.indexOf("edit")) {
            model.oper = "edit";
        }
        else {
            model.oper = "add";
        }

        model.id = $("#id").val();
        model.name = $("#name").val();
        model.categoryId = $("#category").val();
        model.path = $("#path").val();
        model.title = $("#title").val();
        model.keywords = $("#keywords").val();
        model.description = $("#description").val();
        model.sort = $("#sort").val();
        J.AjaxJsonPost("categoryedit.save", model,
            function (data) {
                if (data == 1) {
                    alert("保存成功");
                    window.location = "categorylist";
                } else {
                    alert(data);
                }
            });
    });


});

