MvcUtil = {};
MvcUtil.showSuccessResponse = function (text, element) {
    MvcUtil.showResponse("success", text, element);
};
MvcUtil.showErrorResponse = function showErrorResponse(text, element) {
    MvcUtil.showResponse("error", text, element);
};
MvcUtil.showResponse = function (type, list, element) {
    var responseElementId = element.attr("id") + "Response";
    var responseElement = $("#" + responseElementId);
    if (responseElement.length == 0) {
        responseElement = $('<div class="activity-item"><strong>' + list['name'] + '</strong> ' + list['comment'] + '<br>' + list['date'] + '</div>')
            .insertBefore(element);
    } else {
        responseElement.replaceWith('<span id="' + responseElementId + '" class="' + type + '" style="display:none">' + list + '</span>');
        responseElement = $("#" + responseElementId);
    }
    responseElement.fadeIn("slow");
};

var offset = 1;
var panel = "#activity-panel";
var showMoreBtn = "show-more-btn";
$("a.show-more-btn").click(function () {
    var link = $(this);
    $.ajax({
        url: this.href + "?offset=" + offset,
        contentType: "application/json",
        dataType: "json",
        beforeSend: function (req) {
            req.setRequestHeader("Accept", "application/json");
        },
        success: function (json) {
            var strJson = JSON.stringify(json);
            var list = JSON.parse(strJson);
            for (var i = 0; i < list.length; i++) {
                MvcUtil.showSuccessResponse(list[i], link);
            }
            offset += 1;
        },
        error: function (xhr) {
            MvcUtil.showErrorResponse(xhr.responseText, link);
        }
    });
    $.ajax({
        url: this.href + "?offset=" + (offset + 1),
        contentType: "application/json",
        dataType: "json",
        beforeSend: function (req) {
            req.setRequestHeader("Accept", "application/json");
        },
        success: function (json) {
            if (json == "") {
                document.getElementById(showMoreBtn).style.display = "none";
                return;
            }
        },
        error: function (xhr) {
            MvcUtil.showErrorResponse(xhr.responseText, link);
        }
    });
    return false;
});