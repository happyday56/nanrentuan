$(function () {
    page.list();
});

var page = {};
page.list = function () {
    $.get("categorylist.do", "", function (data) {
        var html = "";
        for (var i = 0; i < data.length; i++) {
            var row = data[i];
            html += '<tr><td>' + row.id + '</td>';
            html += '<td><a href="' + row.url + '" target="_blank">' + row.name + '</a></td>';
            html += '<td>' + row.parentName + '</td>';
            html += '<td>' + row.path + '</td>';
            html += '<td>' + row.sort + '</td>';
            html += '<td><a href="categoryedit?oper=edit&id=' + row.id + '"  target="_blank">修改</a>';
            if (row.parentName == "") html += '<a href="categoryedit?oper=add&id=' + row.id + '"  target="_blank">新增子项</a>';
            html += '</td></tr>';
        }
        $("#list").find("tbody").html(html);
    });
}

