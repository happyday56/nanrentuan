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
                this.obj.html('<label class="msg" style="margin-left: 10px;">共<span>' + this.recordCount + '</span>条记录' +
                    '，当前第<span>' + (this.pageNo) + '</span>/共<span>' + pageCount + '</span>页' +
                    '，每页<span>' + this.pageSize + '</span>条记录</label>');
                if (this.pageNo > 1) this.obj.append('<a class="button-white" href="javascript:goTo(1,' + callback + ')"><span>首页</span></a>' +
                    '<a   href="javascript:goTo(' + (this.pageNo - 1) + ',' + callback + ')"><span>上一页</span></a>');
                if (this.pageNo != pageCount) this.obj.append('<a  href="javascript:goTo(' + (this.pageNo + 1) + ',' + callback + ')"><span>下一页</span></a>' +
                    '<a  href="javascript:goTo(' + pageCount + ',' + callback + ')"><span>尾页</span></a>');

                this.obj.append('第<input class="txt" id="currentPageNo"style="text-align:center;" value="' + this.pageNo + '" type="text">页' +
                    '<input type="button"  onclick="javascript:goToNo(' + callback + ');" class="button1" value="跳转">');
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