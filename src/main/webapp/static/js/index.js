Cufon.replace('a.logo', {fontFamily: 'Bebas'});
Cufon.replace('a.logo span', {fontFamily: 'Bell Gothic Std'});
function loadAjaxWindow(target) {
    $.ajax({
        url: target,
        type: "POST",
        data: null,
        success: function () {
            window.location = target;
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}

function saveFeedback() {
    if ($("#feedback").val() !== "Leave a comment to KCEP officials") {
        $.ajax({
            url: "saveFeedback",
            type: "POST",
            data: "feedback=" + $("#feedback").val(),
            success: function () {
                $("#feedback").val("Feedback has been received. Thank you!");
            },
            error: function (response) {
                showError("error_label", response.responseText);
            },
            dataType: "HTML"
        });
    } else
        return;
}

function showError(title, message) {
    $("#message").html(String(message));
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: title,
        resizable: false,
        modal: false,
        context: $(this),
        buttons: {
            "Ok": function () {
                $(this).dialog("close");
            }
        }
    });
}

//<editor-fold defaultstate="collapsed" desc="Windows">
function loadPreviousWindow() {
    parent.history.back();
    return false;
}
//</editor-fold>
