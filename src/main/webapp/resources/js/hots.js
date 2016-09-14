var hots = {
    /**
     * 分页扩展
     * @param loadObj dom对象
     * @param pageNo 页码 从1开始
     * @param pageSize 每页数量
     * @param recordCount 总条数
     */
    paging: function (loadObj, pageNo, pageSize, recordCount) {
        this.obj = loadObj;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.recordCount = recordCount;
        this.init = function (callback) {
            if (this.obj) {
                var pageCount = this.recordCount % this.pageSize == 0 ? parseInt(this.recordCount / this.pageSize) : parseInt(this.recordCount / this.pageSize + 1);
                this.obj.html('<div class="msg" style="margin-left: 10px;">共<span>' + this.recordCount + '</span>条记录' +
                    '，当前第<span>' + (this.pageNo) + '</span>/共<span>' + pageCount + '</span>页' +
                    '，每页<span>' + this.pageSize + '</span>条记录</div>');
                if (this.pageNo > 1) this.obj.append('<a class="button-white" href="javascript:goTo(1,' + callback + ')"><span>首页</span></a>' +
                    '<a class="button-white" href="javascript:goTo(' + (this.pageNo - 1) + ',' + callback + ')"><span>上一页</span></a>');
                if (this.pageNo != pageCount) this.obj.append('<a class="button-white" href="javascript:goTo(' + (this.pageNo + 1) + ',' + callback + ')"><span>下一页</span></a>' +
                    '<a class="button-white" href="javascript:goTo(' + pageCount + ',' + callback + ')"><span>尾页</span></a>');

                this.obj.append('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第<input id="currentPageNo" class="input-small" style="text-align:center;" value="' + this.pageNo + '" type="text">页' +
                    '&nbsp;&nbsp;&nbsp;&nbsp;<a class="button-white" href="javascript:goToNo(' + callback + ');"><span>跳转</span></a> ');
            }
        };
    }
};

function goTo(pageNo, callback) {
    callback(pageNo);
}

function goToNo(callback) {
    var pageNo = document.getElementById("currentPageNo").value;
    goTo(pageNo, callback);
}