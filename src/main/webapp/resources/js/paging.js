///分页可提取 待处理
function LoadPaging(obj, current, length, totalCount) {
	if (obj) {
		if (totalCount == 0) {
			obj.html('');
			return;
		}
		var pageCount = totalCount % length == 0 ? parseInt(totalCount / length)
				: parseInt(totalCount / length + 1);
		if (current <= 1)
			current = 1;
		if (current >= pageCount)
			current = pageCount;
		if (current == 1 && pageCount == 1) {
			obj.html('');
			return;
		}

		var html = '';
		if (current > 1)
			html += '<li><a href="javascript:void(0);" onclick="gotopage(1)">首页</a></li><li><a href="javascript:void(0);" onclick="gotopage('
					+ (current - 1) + ')">上一页</a></li>';

		var number = '';
		for (m = current - 4; m <= current + 4; m++) {
			if (m > 0 && m <= pageCount)
				number += '<li' + (current == m ? ' class="page"' : '')
						+ '><a href="javascript:void(0);" onclick="gotopage('
						+ m + ')">' + m + '</a></li>';
		}
		html += number;

		if (current < pageCount)
			html += '<li><a href="javascript:void(0);" onclick="gotopage('
					+ (current + 1)
					+ ')">下一页</a></li><li><a href="javascript:void(0);" onclick="gotopage('
					+ pageCount + ')">尾页</a></li>';
		obj.html(html);

	}
}
function gotopage(p) {
	request.current = p;
	LoadList();
}