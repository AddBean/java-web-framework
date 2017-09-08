/**
 * Created by Administrator on 2016/10/25.
 */
function getDialogView(qrcode, index) {

    var html = "<div class='qr-dialog' id='qr-dialog-id-" + index + "' tag='selected'> " +
        "<div class='qr-header'> " +
        "<h5 class='label-header'>安全支付</h5> " +
        "</div> " +
        "<div class='qr-body'> " +
        "<div class='qr-box'>" +
        " <span class='qr-box-num' id='qr-box-num-id-" + index + "'></span>" +
        "<div class='qr-box-barcode' id='qr-box-barcode-id-" + index + "'></div> " +
        "<div class='qr-box-code' >" + "<div class='qr-box-code-content' id='qr-box-code-id-" + index + "'> </div></div>" +
        "<div class='qr-box-bank'><span class='qr-box-label-bank' id='qr-box-label-bank-id-"+index+"'>银行信息</span></div>" +
        "</div>" +
        " </div> " +
        "<div class='qr-footer'>" +
        "</div> " +
        "</div>";
    return html;
}