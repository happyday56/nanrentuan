var request = {
    type: 0,
    current: 1,
    length: 5,
    key: "",
    isContentNull: 0
};

$(function () {
    LoadList();

    //搜索
    $("#btnSearch").click(function () {
        var searchContent = $("#key").val();
        request.key = searchContent;
        request.isContentNull = $("#isContentNull").attr("checked") ? 1 : 0;
        LoadList();
    });

});

function LoadList() {
    J.AjaxJsonPost("articlelist.do", JSON.stringify(request),
        function (data) {
            var list = data.list;
            var page = data.page;
            var html = "";
            for (var i = 0; i < list.length; i++) {
                var row = list[i];
                html += '<tr><td>' + row.id + '</td>'
                    + '<td><a href="../v/' + row.id + '" target="_blank">' + row.title + '</a></td>'
                    + '<td>' + row.categoryTitle + '</td>'
                    + '<td>' + (row.pictureUrl != "" ? "有图" : "") + '</td>'
                    + '<td>' + row.keywords + '</td>'
                    + '<td>' + row.description + '</td>'
                    + '<td>' + J.UtcToDateString(row.uploadTime) + '</td>'
                    + '<td>' + row.views + '</td>'
                    + '<td><a href="articleedit?id=' + row.id + '&oper=edit"  target="_blank">修改</a></td></tr>';
            }

            $("#list").find("tbody").html(html);

            LoadPaging($(".pagination"), page.current, page.length, page.count);
        });
}
