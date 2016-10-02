var request = {
    title: "",
    keywords: "",
    description: "",
    top: ""
};

$(function () {

    $("#btnSave").click(function () {
        page.save();
    });
})

var page = {};
page.save = function () {
    request.title = $("#title").val();
    request.keywords = $("#keywords").val();
    request.description = $("#description").val();
    request.top = editor.html();

    $.post("configEdit.save", request, function (data) {
        if (data == 1) {
            window.location = "configEdit";
        } else {
            alert(data);
        }
    });
}
