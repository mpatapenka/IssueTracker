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
        responseElement = $('<div class="activity-item"><strong>' + list['name']
            + '</strong> ' + list['comment'] + '<br><span class="label label-default">' + list['date'] + '</span> - '
            + '<span class="label label-success">' + list['duration'] + ' min</span>')
            .insertBefore(element);
    } else {
        responseElement.replaceWith('<span id="' + responseElementId + '" class="'
            + type + '" style="display:none">' + list + '</span>');
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
        error: function () {
            location.reload();
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
            }
        },
        error: function () {
            location.reload();
        }
    });
    return false;
});

function memberAdd(id) {
    $.ajax({
        url: "/projects?id=" + id,
        data: $('#memberForm').serialize(),
        type: "POST",
        success: function (result) {
            if (result != "") {
                var element = $('.insertBefore');
                var strDiv = '<div id="insertedError" class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button>' + result + '</div>';
                var insElem = $(strDiv);
                var parent = document.getElementById("memberForm");
                var test = document.getElementById("insertedError");
                if (test != null) {
                    parent.removeChild(test);
                }
                insElem.insertBefore(element);
            } else {
                location.reload();
            }
        },
        error: function () {
            location.reload();
        }
    });
}

function projectAdd() {
    $.ajax({
        url: "/projects?new",
        data: $('#newProjectForm').serialize(),
        type: "POST",
        success: function (result) {
            if (result != "") {
                result = result.split('\n').join('<br>');
                var element = $('.insertBefore');
                var strDiv = '<div id="insertedError" class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button>' + result + '</div>';
                var insElem = $(strDiv);
                var parent = document.getElementById("newProjectForm");
                var test = document.getElementById("insertedError");
                if (test != null) {
                    parent.removeChild(test);
                }
                insElem.insertBefore(element);
            } else {
                location.reload();
            }
        },
        error: function () {
            location.reload();
        }
    });
}

function reportIssue(id) {
    $.ajax({
        url: "/issues?id=" + id + "&report",
        data: $('#reportForm').serialize(),
        type: "POST",
        success: function (result) {
            if (result != "") {
                result = result.split('\n').join('<br>');
                var element = $('.insertBeforeReportForm');
                var strDiv = '<div id="insertedErrorReport" class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button>' + result + '</div>';
                var insElem = $(strDiv);
                var parent = document.getElementById("reportForm");
                var test = document.getElementById("insertedErrorReport");
                if (test != null) {
                    parent.removeChild(test);
                }
                insElem.insertBefore(element);
            } else {
                location.reload();
            }
        },
        error: function () {
            location.reload();
        }
    });
}

function reassignIssue(id) {
    $.ajax({
        url: "/issues?id=" + id + "&reassign",
        data: $('#reassignForm').serialize(),
        type: "POST",
        success: function (result) {
            if (result != "") {
                result = result.split('\n').join('<br>');
                var element = $('.insertBeforeReassignForm');
                var strDiv = '<div id="insertedErrorReassign" class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button>' + result + '</div>';
                var insElem = $(strDiv);
                var parent = document.getElementById("reassignForm");
                var test = document.getElementById("insertedErrorReassign");
                if (test != null) {
                    parent.removeChild(test);
                }
                insElem.insertBefore(element);
            } else {
                location.reload();
            }
        },
        error: function () {
            location.reload();
        }
    });
}

function transferIssueStatus(id, param) {
    $.ajax({
        url: "/issues?id=" + id + "&to=" + param,
        type: "POST",
        success: function (result) {
            if (result != "") {
                alert(result);
            } else {
                location.reload();
            }
        },
        error: function () {
            location.reload();
        }
    });
}

function attachFile(id) {
    var data = new FormData($('#attachForm')[0]);
    $.ajax({
        url: "/issues?id=" + id + "&attach",
        data: data,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        type: "POST",
        success: function (result) {
            if (result != "") {
                result = result.split('\n').join('<br>');
                var element = $('.insertBeforeAttachForm');
                var strDiv = '<div id="insertedErrorAttach" class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button>' + result + '</div>';
                var insElem = $(strDiv);
                var parent = document.getElementById("attachForm");
                var test = document.getElementById("insertedErrorAttach");
                if (test != null) {
                    parent.removeChild(test);
                }
                insElem.insertBefore(element);
            } else {
                location.reload();
            }
        },
        error: function (xhr) {
            alert(xhr);
        }
    });
}

function createIssue() {
    $.ajax({
        url: "/issues?create",
        data: $('#issueForm').serialize(),
        type: "POST",
        success: function (result) {
            if (result != "") {
                result = result.split('\n').join('<br>');
                var element = $('.insertBeforeIssueForm');
                var strDiv = '<div id="insertedErrorIssue" class="alert alert-danger alert-dismissible alert-fix alert-danger-fix" role="alert">' +
                    '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
                    '<span aria-hidden="true">&times;</span></button>' + result + '</div>';
                var insElem = $(strDiv);
                var parent = document.getElementById("issueForm");
                var test = document.getElementById("insertedErrorIssue");
                if (test != null) {
                    parent.removeChild(test);
                }
                insElem.insertBefore(element);
            } else {
                location.reload();
            }
        },
        error: function () {
            location.reload();
        }
    });
}

function loadEmployees(element) {
    var id = element.options[element.selectedIndex].value;
    if (id == -1) {
        return;
    }
    $.ajax({
        url: "/employees?project=" + id,
        contentType: "application/json",
        dataType: "json",
        type: "POST",
        success: function (json) {
            $('#projMembers').find('option').remove().end();

            var str = '<option value="-1">Assignee</option>';
            if (json.length == 0) {
                str = '<option value="-2">Unassigned</option>';
            }
            for (var i = 0; i < json.length; i++) {
                str += '<option value="' + json[i]['id'] + '">' + json[i]['name'] + '</option>';
            }
            $('#projMembers').append(str);
        },
        error: function () {
            location.reload();
        }
    });
}

$(function () {
    $('.datepicker').datepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true
    });
});
