var request = {
    key: "",
    pageNo: 1,
    pageSize: 10

};

$(function () {
    page.list();

    $("#key").keypress(function (event) {
        if (event.which == 13) page.search();
    });
});

var page = {};

page.list = function () {
    $.post("articlelist.do", request, function (data) {
        var html = "";
        for (var i = 0; i < data.list.length; i++) {
            var row = data.list[i];
            html += '<tr><td>' + row.id + '</td>'
                + '<td><a href="../v/' + row.id + '">' + row.title + '</a></td>'
                + '<td>' + row.categoryTitle + '</td>'
                + '<td>' + (row.pictureUrl != "" ? "有图" : "") + '</td>'
                + '<td>' + row.keywords + '</td>'
                + '<td>' + row.description + '</td>'
                + '<td>' + J.UtcToDateString(row.uploadTime) + '</td>'
                + '<td>' + row.views + '</td>'
                + '<td><a href="articleedit?id=' + row.id + '&oper=edit">修改</a> ' +
                '&nbsp;&nbsp;<a href="/' + row.id + '.html" target="_blank">查看</a></td></tr>';
        }

        $("#list").find("tbody").html(html);
        page.paging(data.page.pageNumber, data.page.pageSize, data.page.totalCount);
    });
}

page.paging = function (pageNo, pageSize, recordCount) {
    var pagebar = new hots.paging($(".pagination"), pageNo, pageSize, recordCount);
    pagebar.init(function (pageNo) {
        request.pageNo = pageNo;
        page.list();
    });
}

page.search = function () {
    var searchContent = $("#key").val();
    request.key = searchContent;
    page.list();
}