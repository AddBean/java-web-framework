/**
 * Created by Administrator on 2016/9/14.
 */

function initPagination(start, end, callback) {
    setPagination(start, end);
    $(".pagination-index").attr("tag", "unselected");//全部反选；
    $(".pagination-index").click(function () {
        $(".pagination-index").attr("tag", "unselected");//全部反选；
        $(this).attr("tag", "selected")
        if ($(this).attr("index") == -1) {
            if (start > 0)
                initPagination(--start, --end, callback);
        } else if ($(this).attr("index") == -2) {
            initPagination(++start, ++end, callback);
        } else {
            callback($(this).attr("index"));
        }
    });
}

function setPaginationIndex(index) {
    $(".pagination-index").attr("tag", "unselected");//全部反选；
    $('.pagination-index').each(function (i, domEle) {
        try {
            if (domEle.getAttribute("index") == index) {
                domEle.setAttribute("tag", "selected");
            }
        } catch (e) {
        }
    });
}

function setPagination(start, end) {
    var content = document.getElementsByClassName("pagination-content")[0];
    var html = "<li class='pagination-index' index='-1'><<</li>";
    for (var i = start; i < end; i++) {
        html += "<li class='pagination-index' tag='unselected' index='" + i + "'>" + i + "</li>";
    }
    html += " <li class='pagination-index' index='-2'>>></li>";
    content.innerHTML = html;
}
function initTable() {
    var trs = document.getElementsByClassName("table-content")[0].getElementsByTagName("tr");
    for (var i = 0; i < trs.length; i++) {
        if (i % 2 == 0) {
            trs[i].style.backgroundColor = "#f9f9f9";
        } else {
            trs[i].style.backgroundColor = "#ffffff";
        }
    }
}