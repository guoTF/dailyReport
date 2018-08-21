//主页面操作子界面及格式化类别
pageSet = {
    GetQueryString: function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
};

function IFrameResize() {
    $("#ifpage", parent.document).height(200);
    var iframeHeight = document.body.scrollHeight;
    $("#ifpage", parent.document).height(iframeHeight);
    // console.log(iframeHeight)
}

function openIframeByJS(strurl, args, callback) {
    $("#ifpage").attr("src", strurl);
}

function iFrameHeight() {
    $("#ifpage").height(200);
    var iframeHeight = $("#ifpage").contents().find("body").outerHeight(true);
    $("#ifpage").height(iframeHeight);
    // console.log(iframeHeight)


}
