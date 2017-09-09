/**
 * Created by Administrator on 2016/9/14.
 */
function init() {
    $(".menu-title").attr("tag", "unselected");//全部反选；
    $(".menu-sub li").attr("sub_tag", "unselected");//全部反选；
    $(".menu-title").click(function () {
        $(".menu-title").attr("tag", "unselected");//全部反选；
        $(this).attr("tag", "selected")
    });
    $(".menu-sub li").click(function () {
        $(".menu-sub li").attr("sub_tag", "unselected");
        $(this).attr("sub_tag", "selected")
        $(".content-frame").attr("src", $(this).attr("content"));
        setiFrameHeight();
    });
}

function setiFrameHeight() {
    var ifm = document.getElementsByClassName("content-frame")[0];
    var subWeb = document.frames ? document.frames["content-frame"].document : ifm.contentDocument;
    if (ifm != null && subWeb != null) {
        ifm.height = 1000;
        ifm.height = subWeb.body.scrollHeight;
        console.info("自适应高度：" + subWeb.body.scrollHeight);
    }
}