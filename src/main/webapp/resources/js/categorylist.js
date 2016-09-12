$(function () {
    page.list();
});

var page = {};
page.list = function () {
    $.get("category/list", "", function (data) {
        var list = data.list;
        var html = "";
        for (var i = 0; i < list.length; i++) {
            var row = list[i];
            html += '<tr><td>' + row.id + '</td>';
            html += '<td><a href="' + row.url + '" target="_blank">' + row.name + '</a></td>';
            html += '<td>' + row.parentName + '</td>';
            html += '<td>' + row.path + '</td>';
            html += '<td>' + row.sort + '</td>';
            html += '<td><a href="category/edit/' + row.id + '"  target="_blank">修改</a>';
            if (row.parentName == "") html += '<a href="category/add/' + row.id + '"  target="_blank">新增子项</a>';
            html += '</td></tr>';
        }
        $("#list").find("tbody").html(html);
    });
}
