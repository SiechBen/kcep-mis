var language = "en";
//<editor-fold defaultstate="collapsed" desc="Metis menu">
$(function () {
    $('#side-menu').metisMenu();
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sidebar">
/* Loads the correct sidebar on window load,
 * collapses the sidebar on window resize.
 * Sets the min-height of #page-wrapper to window size */
$(function () {
    $(window).bind("load resize", function () {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1)
            height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });
    var url = window.location;
    var element = $('ul.nav a').filter(function () {
        return this.href === url || url.href.indexOf(this.href) === 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Remember user">
$(function () {

    var username = $.jStorage.get("username");
    var password = $.jStorage.get("password");
    if (username && password) {
        $("#username").val(username);
        $("#password").val(password);
        $("#login-button").focus();
    } else if (username) {
        $("#username").val(username);
        $("#password").val("").focus();
    } else if (password) {
        $("#username").val("").focus();
        $("#password").val(password);
    }

});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Tootlips">
$('[data-toggle="tooltip"]').tooltip(); //initialize all tooltips in the document
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Datepicker">
$(function () {
    if (!Modernizr.inputtypes.date) {
        $(".datefield").datepicker({dateFormat: 'yy-mm-dd'});
    }
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Year picker">
$(function () {
    var yearNow = new Date().getFullYear();
    var yearInTheFuture = yearNow + 5;
    var startYear = 2015;
    for (yearOption = startYear; yearOption <= yearInTheFuture; yearOption++) {
        if (yearOption === yearNow) {
            $(".yearfield").append($("<option/>").val(yearOption).attr("selected", "selected").html(yearOption));
        } else {
            $(".yearfield").append($("<option/>").val(yearOption).html(yearOption));
        }
    }
});
$(function () {
    var yearNow = new Date().getFullYear();
    var twoCenturiesAgo = yearNow - 200;
    for (yearOption = yearNow; yearOption >= twoCenturiesAgo; yearOption--) {
        if (yearOption === yearNow) {
            $("#year-of-birth").append($("<option/>").val(yearOption).attr("selected", "selected").html(yearOption));
        } else {
            $("#year-of-birth").append($("<option/>").val(yearOption).html(yearOption));
        }
    }
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cursor">
$(function () {
    $(".pencil").awesomeCursor("pencil");
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="DataTable">
$(function () {
    $("#wards-table").DataTable({
        responsive: true,
        dom: "Blftip",
        "bLengthChange": false,
        buttons: [
            'excel'
        ]
    });
});
$(function () {
    $(".data-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add',
                action: function () {
                    loadAjaxWindow($("#add-label").text());
                }
            },
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }]
    });
});
$(function () {
    $('.reports-table').DataTable({
        "bLengthChange": false,
        "scrollCollapse": true,
        "searching": false,
        "bPaginate": false,
        responsive: true,
        "bFilter": false,
        "scrollX": true,
        "bSort": false,
        "bInfo": false,
        dom: "Blftip",
        buttons: ['excel', 'print',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }]
    });
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Application attributes">
//$.ajax({
//    url: "load",
//    type: "POST",
//    data: "",
//    error: function (response) {
//        showError("error_label", response.responseText);return;
//    },
//    dataType: "HTML"
//});
function loadApplicationAttributes() {
    $.ajax({
        url: "load",
        type: "POST",
        data: "",
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Calculation">
$("#actual-outcome-value").on("input", function () {
    calculateOutcomeRatio();
});
$("#awpb-outcome-target").on("input", function () {
    calculateOutcomeRatio();
});
function calculateOutcomeRatio(id) {

    var actualValue = parseFloat($("#actual-outcome-value-" + id + "").val()) || 0.0;
    var expectedValue = parseFloat($("#awpb-outcome-target-" + id + "").val()) || 0.0;
    $("#outcome-ratio-" + id + "").html((actualValue / expectedValue * 100).toFixed(2) + "%");
}

function calculateRatio() {

    var actualValue = parseFloat($("#actual-value").val()) || 0.0;
    var expectedValue = parseFloat($("#expected-value").val()) || 0.0;
    $("#ratio").val((actualValue / expectedValue * 100).toFixed(2));
}

$("#actual-value").on("input", function () {
    calculateRatio();
});
$("#expected-value").on("input", function () {
    calculateRatio();
});
$("#appraisal-target").on("input", function () {
    var actualValue = parseFloat($("#actual-value").val()) || 0.0;
    var expectedValue = parseFloat($("#appraisal-target").val()) || 0.0;
    $("#ratio").val((actualValue / expectedValue * 100).toFixed(2));
});
function calculateAWPBTotals() {

    var unitCost = $("#unit-cost").val();
    var awpbTarget = $("#awpb-target").val();
    if (unitCost.trim() === "") {
        unitCost = 0;
    }
    if (awpbTarget.trim() === "") {
        awpbTarget = 0;
    }

    $("#totals").val((parseFloat(unitCost) * parseFloat(awpbTarget)).toFixed(2));
}

$("#unit-cost").on("input", function () {
    calculateAWPBTotals();
});
$("#awpb-target").on("input", function () {
    calculateAWPBTotals();
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Savings update calculation">
$("#change").on("change", function () {
    var savings = $("#savings").val();
    var change = $("#change").val();
    if (savings.trim() === "") {
        savings = 0;
    }
    if (change.trim() === "") {
        change = 0;
    }
    $("#savings").val((parseFloat(change) + parseFloat(savings)).toFixed(2));
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Post-harvest loss calculation">
$("#quantity-harvested").on("change", function () {
    calculatePostHarvestLoss();
});
$("#quantity-sold").on("change", function () {
    calculatePostHarvestLoss();
});
$("#family-consumption").on("change", function () {
    calculatePostHarvestLoss();
});
function calculatePostHarvestLoss() {
    var quantityHarvested = parseFloat($("#quantity-harvested").val()) || 0.0;
    var familyConsumption = parseFloat($("#family-consumption").val()) || 0.0;
    var quantitySold = parseFloat($("#quantity-sold").val()) || 0.0;
    $("#post-harvest-loss").val((quantityHarvested - (familyConsumption + quantitySold)).toFixed(2));
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sliding form">
$(function () {
    /*
     number of fieldsets
     */
    var fieldsetCount = $('.sliding-form').children().length;
    /*
     current position of fieldset / navigation link
     */
    var current = 1;
    /*
     sum and save the widths of each one of the fieldsets
     set the final sum as the total width of the steps element
     */
    var stepsWidth = 0;
    var widths = new Array();
    $('#steps .step').each(function (i) {
        var $step = $(this);
        widths[i] = stepsWidth;
        stepsWidth += $step.width();
    });
    $('#steps').width(stepsWidth);
    /*
     to avoid problems in IE, focus the first input of the form
     */
    $('.sliding-form').children(':first').find(':input:first').focus();
    /*
     show the navigation bar
     */
    $('#slider-navigation').show();
    /*
     when clicking on a navigation link
     the form slides to the corresponding fieldset
     */
    $('#slider-navigation a').bind('click', function (e) {
        var $this = $(this);
        var prev = current;
        $this.closest('ul').find('li').removeClass('selected');
        $this.parent().addClass('selected');
        /*
         we store the position of the link
         in the current variable
         */
        current = $this.parent().index() + 1;
        /*
         animate / slide to the next or to the corresponding
         fieldset. The order of the links in the navigation
         is the order of the fieldsets.
         Also, after sliding, we trigger the focus on the first
         input element of the new fieldset
         If we clicked on the last link (confirmation), then we validate
         all the fieldsets, otherwise we validate the previous one
         before the form slided
         */
        $('#steps').stop().animate({
            marginLeft: '-' + widths[current - 1] + 'px'
        }, 500, function () {
            if (current === fieldsetCount)
                validateSteps();
            else
                validateStep(prev);
            $('.sliding-form').children(':nth-child(' + parseInt(current) + ')').find(':input:first').focus();
        });
        e.preventDefault();
    });
    /*
     clicking on the tab (on the last input of each fieldset), makes the form
     slide to the next step
     */
    $('.sliding-form > fieldset').each(function () {
        var $fieldset = $(this);
        $fieldset.children(':last').find(':input').keydown(function (e) {
            if (e.which === 9) {
                $('#slider-navigation li:nth-child(' + (parseInt(current) + 1) + ') a').click();
                /* force the blur for validation */
                $(this).blur();
                e.preventDefault();
            }
        });
    });
    /*
     validates errors on all the fieldsets
     records if the Form has errors in $('.sliding-form').data()
     */
    function validateSteps() {
        var FormErrors = false;
        for (var i = 1; i < fieldsetCount; ++i) {
            var error = validateStep(i);
            if (error === -1)
                FormErrors = true;
        }
        $('.sliding-form').data('errors', FormErrors);
    }

    /*
     validates one fieldset
     and returns -1 if errors found, or 1 if not
     */
    function validateStep(step) {
        if (step === fieldsetCount)
            return;
        var error = 1;
        var hasError = false;
        $('.sliding-form')
                .children(':nth-child(' + parseInt(step) + ')')
                .find(':input:not(button)')
                .each(function () {
                    var $this = $(this);
                    if ($this.is($("#admission-year"))) {
                        $.ajax({
                            url: "checkFacultyMemberRole",
                            type: "POST",
                            data: "memberRole=" + $("#faculty-member-role").val(),
                            success: function (response) {
                                if (response !== "") {
                                    if ($("#admission-year").val().trim().length === 0) {
                                        hasError = true;
                                        $("#admission-year").css('background-color', '#FFEDEF');
                                        var $link = $('#slider-navigation li:nth-child(' + parseInt(step) + ') a');
                                        $link.parent().find('.error,.checked').remove();
                                        var valclass = 'checked';
                                        if (hasError) {
                                            error = -1;
                                            valclass = 'error';
                                        }
                                        $('<span class="' + valclass + '"></span>').insertAfter($link);
                                        return error;
                                    } else {
                                        $("#admission-year").css('background-color', '#FFFFFF');
                                    }
                                } else {
                                    $("#admission-year").css('background-color', '#FFFFFF');
                                }
                            }
                        });
                    } else if ($this.prop('required')) {
                        if ($this.val().trim().length === 0) {
                            hasError = true;
                            $this.css('background-color', '#FFEDEF');
                        } else {
                            $this.css('background-color', '#FFFFFF');
                        }
                    }
                });
        var $link = $('#slider-navigation li:nth-child(' + parseInt(step) + ') a');
        $link.parent().find('.error,.checked').remove();
        var valclass = 'checked';
        if (hasError) {
            error = -1;
            valclass = 'error';
        }
        $('<span class="' + valclass + '"></span>').insertAfter($link);
        return error;
    }

    /*
     if there are errors don't allow the user to submit
     */
    $('.slider-submit-button').bind('click', function () {
        if ($('.sliding-form').data('errors')) {
            showError("error_label", 'Please correct the errors in the Form');
            return false;
        }
    });
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Tables">

function setTableIcons() {

    $(".editButton").button({
        text: false,
        icons: {
            primary: "ui-icon-pencil"
        }
    });
    $(".deleteButton").button({
        text: false,
        icons: {
            primary: "ui-icon-trash"
        }
    });
    $(".openButton").button({
        text: false,
        icons: {
            primary: "ui-icon-extlink"
        }
    });
    $(".downloadButton").button({
        text: false,
        icons: {
            primary: "ui-icon-arrowthick-1-s"
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Forms">
function submitForm(form) {
    $("#" + form).submit();
}

function validateform(form) {
    var flag = true;
    $("form#" + form + "[required]").each(function () {
        if ($(this).val() === null || $(this).val().trim() === "") {
            showMessage("Error", $(this).attr("name") + " is required");
            flag = false;
        }
    });
    return flag;
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Messages">

(function ($) {
    $.fn.flash_message = function (options) {

        options = $.extend({
            text: "Done",
            time: 2000,
            how: "before",
            class_name: ""
        }, options);
        return $(this).each(function () {
            if ($(this).parent().find(".flash_message").get(0))
                return;
            var message = $("<span />", {
                "class": "flash_message " + options.class_name,
                text: options.text
            }).hide().fadeIn("fast");
            $(this)[options.how](message);
            message.delay(options.time).fadeOut("normal", function () {
                $(this).remove();
            });
        });
    };
})(jQuery);
$(".add-item").click(function () {
    $("#status-area").flash_message({
        text: "Added to cart!",
        how: "append"
    });
});
function showMessage(title, message) {

    //Set the message
    $("#message").text(message);
    //Pop the message dialog
    $("#message-dialog").dialog({
        resizable: false,
        height: 160,
        modal: true,
        title: title,
        buttons: {
            "OK": function () {
                $(this).dialog("close");
            }
        }
    });
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

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Windows">
function loadWindow(target) {
    $(".loader").show();
    $.isLoading();
    window.location = target;
}

function loadAjaxWindow(target) {
    $(".loader").show();
    $.isLoading();
    $.ajax({
        url: target,
        type: "POST",
        data: null,
        success: function () {
            window.location = target;
            $.isLoading("hide");
            $(".loader").hide();
            return;
        },
        error: function (response) {
            $.isLoading("hide");
            $(".loader").hide();
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function loadPreviousWindow() {
    parent.history.back();
    return false;
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Account">
function editAccount(accountNumber, eblBranch, solId, savings) {
    $("#account-number").val(accountNumber);
    $("#ebl-branch option[value=" + eblBranch + "]").attr('selected', 'selected');
    $("#sol-id").val(solId);
    $("#change").val(0);
    $("#savings").val(savings);
    $("#account-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_account_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditAccount",
                    type: "POST",
                    data: "accountNumber=" + $("#account-number").val() + "&eblBranch=" + $("#ebl-branch").val()
                            + "&solId=" + $("#sol-id").val() + "&savings=" + $("#savings").val(),
                    success: function (response) {
                        $("#account-number").val("");
                        $("#ebl-branch").val("");
                        $("#sol-id").val("");
                        $("#change").val("");
                        $("#savings").val("");
                        $("#account").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#account-number").val("");
            $("#ebl-branch").val("");
            $("#sol-id").val("");
            $("#change").val("");
            $("#savings").val("");
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Activity progress report">
$(function () {
    $("#activity-report-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            },
            {
                text: 'Choose reference code',
                action: function () {
                    $("#awpb-reference-code-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "pick_awpb_reference_code",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Retrieve": function () {
                                $.ajax({
                                    url: "getActivityProgress",
                                    type: "POST",
                                    data: "awpbReferenceCode=" + $("#awpb-reference-code").val(),
                                    success: function () {
                                        loadAjaxWindow("activity_report");
                                    },
                                    error: function (response) {
                                        showError("error_label", response.responseText);
                                        return;
                                    },
                                    dataType: "HTML"
                                });
                                $(this).dialog("close");
                            }
                        }
                    });
                }
            }
        ],
        columnDefs: [{
                targets: [1, 2],
                render: function (data, type) {
                    return type === "display" && data.length > 30 ? data.substr(0, 27) + "..." : data;
                }
            }]
    });
});

function changeQuarter() {
    $.ajax({
        url: "changeQuarter",
        type: "POST",
        data: "quarter=" + $("#quarter").val(),
        success: function () {
            loadAjaxWindow("activity_report");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function changeFinancialYear() {
    $.ajax({
        url: "changeFinancialYear",
        type: "POST",
        data: "financialYear=" + $("#financial-year").val(),
        success: function () {
            loadAjaxWindow("activity_report");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editActivityProgress(cell, activityProgressId, valueType, quarter) {

    var cellIndex = cell.cellIndex + 1;
    var initialActivityProgressValue = parseFloat($('td:nth-child(' + cellIndex + ')', $(cell).parents('tr')).text()) || 0.0;
    $("#activity-progress-value").val(initialActivityProgressValue);
    $("#activity-progress-dialog").dialog({
        width: 495,
        height: "auto",
        title: quarter !== undefined ? "" : quarter + " " + valueType.toLowerCase() + " for ref code " + $('td:nth-child(' + 1 + ')', $(cell).parents('tr')).text(),
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {

                var newActivityProgressValue = parseFloat($("#activity-progress-value").val()) || 0.0;
                var deltaActivityProgressValue = newActivityProgressValue - initialActivityProgressValue;
                if (cellIndex < Indices.CUMMULATIVE_TARGET) {

                    if (valueType === "Target") {
                        var cumulativeTarget = parseFloat($('td:nth-child(' + Indices.CUMMULATIVE_TARGET + ')', $(cell).parents('tr')).text()) || 0.0;
                        var appraisalTarget = parseFloat($('td:nth-child(' + (Indices.CUMMULATIVE_TARGET - 1) + ')', $(cell).parents('tr')).text()) || 0.0;
                        if ((cumulativeTarget + deltaActivityProgressValue) > appraisalTarget) {
                            showError("error_label", "Cumulative target[" + (cumulativeTarget + deltaActivityProgressValue) + "] has exceeded the appraisal target[" + appraisalTarget + "].");
                            return;
                        }
                    }

                } else {

                    if (valueType === "Budget") {
                        var cumulativeBudget = parseFloat($('td:nth-child(' + Indices.CUMMULATIVE_BUDGET + ')', $(cell).parents('tr')).text()) || 0.0;
                        var appraisalTarget = parseFloat($('td:nth-child(' + (Indices.CUMMULATIVE_BUDGET - 1) + ')', $(cell).parents('tr')).text()) || 0.0;
                        if ((cumulativeBudget + deltaActivityProgressValue) > appraisalTarget) {
                            showError("error_label", "Cumulative planned budget[" + (cumulativeBudget + deltaActivityProgressValue) + "] has exceeded the appraisal target[" + appraisalTarget + "].");
                            return;
                        }
                    }

                }

                $.ajax({
                    url: "doEditActivityProgress",
                    type: "POST",
                    data: "id=" + activityProgressId + "&valueType=" + valueType +
                            "&activityProgressValue=" + newActivityProgressValue,
                    success: function () {

                        $('td:nth-child(' + cellIndex + ')', $(cell).parents('tr')).text(newActivityProgressValue);
                        var deltaActivityProgressValue = newActivityProgressValue - initialActivityProgressValue;
                        if (cellIndex < Indices.CUMMULATIVE_TARGET) {

                            if (valueType === "Target") {
                                var cumulativeTarget = parseFloat($('td:nth-child(' + Indices.CUMMULATIVE_TARGET + ')', $(cell).parents('tr')).text()) || 0.0;
                                $('td:nth-child(' + Indices.CUMMULATIVE_TARGET + ')', $(cell).parents('tr')).text((cumulativeTarget + deltaActivityProgressValue));
                            } else if (valueType === "Value achieved") {
                                var cumulativeValueAchieved = parseFloat($('td:nth-child(' + Indices.CUMMULATIVE_VALUE_ACHIEVED + ')', $(cell).parents('tr')).text()) || 0.0;
                                $('td:nth-child(' + Indices.CUMMULATIVE_VALUE_ACHIEVED + ')', $(cell).parents('tr')).text((cumulativeValueAchieved + deltaActivityProgressValue));
                            }

                        } else {

                            if (valueType === "Budget") {
                                var cumulativeBudget = parseFloat($('td:nth-child(' + Indices.CUMMULATIVE_BUDGET + ')', $(cell).parents('tr')).text()) || 0.0;
                                $('td:nth-child(' + Indices.CUMMULATIVE_BUDGET + ')', $(cell).parents('tr')).text((cumulativeBudget + deltaActivityProgressValue));
                            } else if (valueType === "Expense") {
                                var cumulativeExpense = parseFloat($('td:nth-child(' + Indices.CUMMULATIVE_EXPENSE + ')', $(cell).parents('tr')).text()) || 0.0;
                                $('td:nth-child(' + Indices.CUMMULATIVE_EXPENSE + ')', $(cell).parents('tr')).text((cumulativeExpense + deltaActivityProgressValue));
                            }

                        }
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function editActivityProgressComment(cell, activityProgressCommentId, comment) {
    var cellIndex = cell.cellIndex + 1;
    $("#activity-progress-comment").val(comment);
    $("#activity-progress-comment-dialog").dialog({
        width: 495,
        height: "auto",
        title: "Comments for reference code " + $('td:nth-child(' + 1 + ')', $(cell).parents('tr')).text(),
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {

                comment = $("#activity-progress-comment").val();
                $.ajax({
                    url: "doEditActivityProgressComment",
                    type: "POST",
                    data: "id=" + activityProgressCommentId + "&comment=" + comment,
                    success: function () {

                        $('td:nth-child(' + cellIndex + ')', $(cell).parents('tr')).text(comment);
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

var Indices = {
    CUMMULATIVE_TARGET: 7,
    CUMMULATIVE_VALUE_ACHIEVED: 8,
    CUMMULATIVE_BUDGET: 13,
    CUMMULATIVE_EXPENSE: 14
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="E-voucher">
$("#e-voucher-form").ajaxForm({
    success: function () {
        $("#e-voucher-amount").val("");
        $("#e-voucher-input-type").val("");
        $("#e-voucher-person").val("");
        $("#date-redeemed").val("");
        $("#inputs-loogbook-page").val("");
        loadAjaxWindow('EVouchers');
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
        return;
    }
});
function editEVoucher(id, amount, inputType, person, dateRedeemed) {
    $("#e-voucher-input-type").val(inputType);
    $("#date-redeemed").val(dateRedeemed);
    $("#e-voucher-input-type option[value=" + inputType + "]").attr('selected', 'selected');
    $("#e-voucher-person option[value=" + person + "]").attr('selected', 'selected');
    $("#e-voucher-amount").val(amount);
    $("#evoucher-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_evouchers_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditEVoucher",
                    type: "POST",
                    data: "id=" + id +
                            "&amount=" + $("#e-voucher-amount").val() +
                            "&person=" + $("#e-voucher-person").val() +
                            "&dateRedeemed=" + $("#date-redeemed").val() +
                            "&inputType=" + $("#e-voucher-input-type").val(),
                    success: function (response) {
                        $("#e-voucher-input-type").val("");
                        $("#e-voucher-amount").val("");
                        $("#e-voucher-person").val("");
                        $("#date-redeemed").val("");
//                        loadAjaxWindow("eVouchers");
                        $("table#e-voucher-table tbody").html(response);
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#e-voucher-amount").val("");
            $("#e-voucher-input-type").val("");
            $("#e-voucher-person").val("");
            $("#date-redeemed").val("");
        }
    });
}

function deletEVoucher(id) {
    $("#message").text("Are you sure you want to remove this e-voucher?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_e_voucher",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteEVoucher",
                    type: "POST",
                    data: "id=" + id,
                    success: function (response) {
//                        loadAjaxWindow("eVouchers");
                        $("table#e-voucher-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Equipment">

function loadEquimentWindow(warehouseId) {

    $(".loader").show();
    $.isLoading();
    var target = "equipment";
    $.ajax({
        url: target,
        type: "POST",
        data: "warehouseId=" + warehouseId,
        success: function () {
            window.location = target;
            return;
        },
        error: function (response) {

            $.isLoading("hide");
            $(".loader").hide();
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function addEquipment(warehouseId) {
    $.ajax({
        url: "doAddEquipment",
        type: "POST",
        data: "warehouseId=" + warehouseId +
                "&equipmentType=" + $("#equipment-type").val() +
                "&serialNumber=" + $("#serial-number").val() +
                "&equipmentStatus=" + $("#equipment-status").val() +
                "&equipmentTotalCount=" + $("#equipment-total-count").val(),
        success: function () {
            $("#serial-number").val("");
            $("#equipment-total-count").val("");
            $("#equipment-status").val("");
            $("#equipment-type").val("");
            loadEquimentWindow(warehouseId);
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editEquipment(id, warehouseId, type, serialNumber, count, status) {
    $("#equipment-total-count").val(count);
    $("#serial-number").val(serialNumber);
    $("#equipment-status").val(status);
    $("#equipment-type").val(type);
    $("#equipment-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_equipment_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditEquipment",
                    type: "POST",
                    data: "id=" + id +
                            "&warehouseId=" + warehouseId +
                            "&serialNumber=" + $("#serial-number").val() +
                            "&equipmentType=" + $("#equipment-type").val() +
                            "&equipmentStatus=" + $("#equipment-status").val() +
                            "&equipmentTotalCount=" + $("#equipment-total-count").val(),
                    success: function () {
                        $("#equipment-total-count").val("");
                        $("#equipment-status").val("");
                        $("#equipment-type").val("");
                        $("#serial-number").val("");
                        loadEquimentWindow(warehouseId);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#equipment-total-count").val("");
            $("#equipment-status").val("");
            $("#equipment-type").val("");
        }
    });
}

function deleteEquipment(id, warehouseId) {
    $("#message").text("Are you sure you want to remove this equipment?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_equipment_label",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteEquipment",
                    type: "POST",
                    data: "id=" + id +
                            "&warehouseId=" + warehouseId,
                    success: function () {
                        loadEquimentWindow(warehouseId);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Farm">
$(function () {
    $(".farm-data-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }]
    });
});
function loadFarmWindow(farmerId) {

    $(".loader").show();
    $.isLoading();
    var target = "farm";
    $.ajax({
        url: target,
        type: "POST",
        data: "farmerId=" + farmerId,
        success: function () {
            window.location = target;
            return;
        },
        error: function (response) {

            $.isLoading("hide");
            $(".loader").hide();
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editFarm(farmerId, plotSize, locationId, county, subCounty, ward, divisionalLocation, village, latitude, longitude) {
    $("#plot-size").val(plotSize);
    if (county !== "")
        $("#county option[value=" + county + "]").attr('selected', 'selected');
    if (subCounty !== "")
        $("#sub-county option[value=" + subCounty + "]").attr('selected', 'selected');
    if (ward !== "")
        $("#ward option[value=" + ward + "]").attr('selected', 'selected');
    $("#divisional-location").val(divisionalLocation);
    $("#village").val(village);
    $("#latitude").val(latitude);
    $("#longitude").val(longitude);
    $("#farm-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_farm_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {

                latitude = $("#latitude").val();
                longitude = $("#longitude").val();
                //Ascertain validity of latitude
                try {
                    if (latitude.length >= 1) {
                        latitude = parseFloat(latitude);
                        if (latitude > 90 || latitude < -90) {
                            showError("error_label", "Latitude value is out of the range (-90 to 90)");
                            return;
                        }
                    }
                } catch (err) {
                    showError("error_label", "Latitude format is invalid");
                    return;
                }

                try {
                    if (longitude.length >= 1) {
                        longitude = parseFloat(longitude);
                        if (longitude > 180 || longitude < -180) {
                            showError("error_label", "Longitude value is out of the range (-180 to 180)");
                            return;
                        }
                    }
                } catch (err) {
                    showError("error_label", "Longitude format is invalid");
                    return;
                }

                $.ajax({
                    url: "doEditFarm",
                    type: "POST",
                    data: "farmerId=" + farmerId +
                            "&plotSize=" + $("#plot-size").val() +
                            "&locationId=" + locationId +
                            "&county=" + $("#county").val() +
                            "&subCounty=" + $("#sub-county").val() +
                            "&ward=" + $("#ward").val() +
                            "&divisionalLocation=" + $("#divisional-location").val() +
                            "&village=" + $("#village").val() +
                            "&latitude=" + $("#latitude").val() +
                            "&longitude=" + $("#longitude").val(),
                    success: function (response) {
                        $("#farm").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function toggleDivisionalLocationInput() {
    $(".shown-dl").hide();
    $("#other-divisional-location").show().focus();
    return;
}

function addDivisionalLocation() {
    $.ajax({
        url: "doAddDivisionalLocation",
        type: "POST",
        data: "name=" + $("#other-divisional-location").val(),
        success: function (response) {
            $("#divisional-location").append($('<option>', {value: response, text: $("#other-divisional-location").val()}));
            $("#divisional-location option[value=" + response + "]").attr("selected", "selected");
            $(".shown-dl").show();
            $("#other-divisional-location").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleVillageInput() {
    $(".shown-v").hide();
    $("#other-village").show().focus();
    return;
}

function addVillage() {
    $.ajax({
        url: "doAddVillage",
        type: "POST",
        data: "name=" + $("#other-village").val(),
        success: function (response) {
            $("#village").append($('<option>', {value: response, text: $("#other-village").val()}));
            $("#village option[value=" + response + "]").attr("selected", "selected");
            $(".shown-v").show();
            $("#other-village").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleExpectedOutcomeInput() {
    $(".shown-eo").hide();
    $("#other-expected-outcome").show().focus();
    return;
}

function addExpectedOutcome(phenomenonTypeId) {
    $.ajax({
        url: "doAddPhenomenon",
        type: "POST",
        data: "name=" + $("#other-expected-outcome").val() + "&phenomenonTypeId=" + phenomenonTypeId + "&phenomenonType=Expected outcome",
        success: function (response) {
            $("#expected-outcome").append($('<option>', {value: response, text: $("#other-expected-outcome").val()}));
            $("#expected-outcome option[value=" + response + "]").attr("selected", "selected");
            $(".shown-eo").show();
            $("#other-expected-outcome").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleComponentInput() {
    $(".shown-c").hide();
    $("#other-component").show().focus();
    return;
}

function addComponent(phenomenonTypeId) {
    $.ajax({
        url: "doAddPhenomenon",
        type: "POST",
        data: "name=" + $("#other-component").val() + "&phenomenonTypeId=" + phenomenonTypeId + "&phenomenonType=Component",
        success: function (response) {
            $("#component").append($('<option>', {value: response, text: $("#other-component").val()}));
            $("#component option[value=" + response + "]").attr("selected", "selected");
            $(".shown-c").show();
            $("#other-component").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleSubComponentInput() {
    $(".shown-sc").hide();
    $("#other-sub-component").show().focus();
    return;
}

function addSubComponent(phenomenonTypeId, relativeId) {
    $.ajax({
        url: "doAddPhenomenon",
        type: "POST",
        data: "name=" + $("#other-sub-component").val() + "&phenomenonTypeId=" + phenomenonTypeId + "&relativeId=" + $("#component").val() + "&phenomenonType=Sub-component",
        success: function (response) {
            $("#sub-component").append($('<option>', {value: response, text: $("#other-sub-component").val()}));
            $("#sub-component option[value=" + response + "]").attr("selected", "selected");
            $(".shown-sc").show();
            $("#other-sub-component").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleAnnualIndicatorInput() {
    $(".shown-ai").hide();
    $("#other-annual-indicator").show().focus();
    return;
}

function addAnnualIndicator(phenomenonTypeId) {
    $.ajax({
        url: "doAddPhenomenon",
        type: "POST",
        data: "name=" + $("#other-annual-indicator").val() + "&phenomenonTypeId=" + phenomenonTypeId + "&phenomenonType=Annual indicator",
        success: function (response) {
            $("#annual-indicator").append($('<option>', {value: response, text: $("#other-annual-indicator").val()}));
            $("#annual-indicator option[value=" + response + "]").attr("selected", "selected");
            $(".shown-ai").show();
            $("#other-annual-indicator").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleActivityNameInput() {
    $(".shown-an").hide();
    $("#other-activity-name").show().focus();
    return;
}

function flyAddActivityName() {
    $.ajax({
        url: "flyAddActivityName",
        type: "POST",
        data: "name=" + $("#other-activity-name").val(),
        success: function (response) {
            $("#activity-name").append($('<option>', {value: response, text: $("#other-activity-name").val()}));
            $("#activity-name option[value=" + response + "]").attr("selected", "selected");
            $(".shown-an").show();
            $("#other-activity-name").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function toggleSubActivityNameInput() {
    $(".shown-san").hide();
    $("#other-sub-activity-name").show().focus();
    return;
}

function flyAddSubActivityName() {
    $.ajax({
        url: "flyAddSubActivityName",
        type: "POST",
        data: "name=" + $("#other-sub-activity-name").val() + "&activityNameId=" + $("#activity-name").val(),
        success: function (response) {
            $("#sub-activity-name").append($('<option>', {value: response, text: $("#other-sub-activity-name").val()}));
            $("#sub-activity-name option[value=" + response + "]").attr("selected", "selected");
            $(".shown-san").show();
            $("#other-sub-activity-name").hide();
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Farm activity">
function addFarmActivity() {
    $("#farm-activity-dialog").dialog({
        width: 495,
        height: "auto",
        title: "add_farm_activity_label",
        modal: true,
        resizable: false,
        buttons: {
            "Add": function () {
                $.ajax({
                    url: "doAddFarmActivity",
                    type: "POST",
                    data: "yield=" + $("#yield").val() +
                            "&quantitySold=" + $("#quantity-sold").val() +
                            "&farmActivityDate=" + $("#farm-activity-date").val() +
                            "&postHarvestLoss=" + $("#post-harvest-loss").val() +
                            "&farmActivityName=" + $("#farm-activity-name").val() +
                            "&quantityHarvested=" + $("#quantity-harvested").val() +
                            "&familyConsumption=" + $("#family-consumption").val() +
                            "&averageSellingPrice=" + $("#average-selling-price").val(),
                    success: function (response) {
                        $("table#farm-activity-table tbody").html(response);
                        $("#yield").val("");
                        $("#quantity-sold").val("");
                        $("#farm-activity-date").val("");
                        $("#post-harvest-loss").val("");
                        $("#farm-activity-name").val("");
                        $("#quantity-harvested").val("");
                        $("#family-consumption").val("");
                        $("#average-selling-price").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#yield").val("");
            $("#quantity-sold").val("");
            $("#farm-activity-date").val("");
            $("#post-harvest-loss").val("");
            $("#farm-activity-name").val("");
            $("#quantity-harvested").val("");
            $("#family-consumption").val("");
            $("#average-selling-price").val("");
        }
    });
}

function editFarmActivity(id, quantityHarvested, familyConsumption, quantitySold,
        postHarvestLoss, yield, farmActivityDate, farmActivityName, averageSellingPrice) {

    $("#yield").val(yield);
    $("#quantity-sold").val(quantitySold);
    $("#farm-activity-date").val(farmActivityDate);
    $("#post-harvest-loss").val(postHarvestLoss);
    $("#farm-activity-name").val(farmActivityName);
    $("#quantity-harvested").val(quantityHarvested);
    $("#quantity-harvested").val(quantityHarvested);
    $("#family-consumption").val(familyConsumption);
    $("#average-selling-price").val(averageSellingPrice);
    $("#farm-activity-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_farm_activity",
        modal: true,
        resizable: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditFarmActivity",
                    type: "POST",
                    data: "yield=" + $("#yield").val() +
                            "&quantitySold=" + $("#quantity-sold").val() +
                            "&farmActivityDate=" + $("#farm-activity-date").val() +
                            "&postHarvestLoss=" + $("#post-harvest-loss").val() +
                            "&farmActivityName=" + $("#farm-activity-name").val() +
                            "&quantityHarvested=" + $("#quantity-harvested").val() +
                            "&familyConsumption=" + $("#family-consumption").val() +
                            "&averageSellingPrice=" + $("#average-selling-price").val() +
                            "&id=" + id,
                    success: function (response) {
                        $("table#farm-activity-table tbody").html(response);
                        $("#yield").val("");
                        $("#quantity-sold").val("");
                        $("#farm-activity-date").val("");
                        $("#post-harvest-loss").val("");
                        $("#farm-activity-name").val("");
                        $("#quantity-harvested").val("");
                        $("#family-consumption").val("");
                        $("#average-selling-price").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#yield").val("");
            $("#quantity-sold").val("");
            $("#farm-activity-date").val("");
            $("#post-harvest-loss").val("");
            $("#farm-activity-name").val("");
            $("#quantity-harvested").val("");
            $("#family-consumption").val("");
            $("#average-selling-price").val("");
        }
    });
}
//
function deleteFarmActivity(id) {
    $("#message").text("Are you sure you want to remove this farm activity?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_farm_activity",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteFarmActivity",
                    type: "POST",
                    data: "id=" + id,
                    success: function (response) {
                        $("table#farm-activity-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Feedback">

$("#feedback-form").ajaxForm({
    success: function () {
        $("#feedback").val("");
        loadAjaxWindow("home");
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
        return;
    }
});
function saveFeedback() {
    $.ajax({
        url: "saveFeedback",
        type: "POST",
        data: "feedback=" + $("#feedback").val(),
        success: function () {
            $("#feedback").val("");
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Financial year">

function addFinancialYear() {
    $.ajax({
        url: "doAddFinancialYear",
        type: "POST",
        data: "financialYear=" + $("#financial-year").val() + "&current=" + $("#current").val(),
        success: function () {
            $("#financial-year").val("");
            $("#current").val("");
            loadAjaxWindow("financial_years");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}
function deleteFinancialYear(id) {
    $("#message").text("Are you sure you want to remove this financial year?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_financial_yaer",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteFinancialYear",
                    type: "POST",
                    data: "id=" + id,
                    success: function (response) {
                        $("table#financial-yaer-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Inputs collection">
function addInputsCollection() {
    $("#inputs-collection-dialog").dialog({
        width: 495,
        height: "auto",
        title: "add_inputs_collection_label",
        modal: true,
        resizable: false,
        buttons: {
            "Add": function () {
                $.ajax({
                    url: "doAddInputsCollection",
                    type: "POST",
                    data: "dateCollected=" + $("#date-collected").val() +
                            "&inputVarietyId=" + $("#input-variety").val() +
                            "&agroDealerId=" + $("#agro-dealer").val() +
                            "&staticInputId=" + $("#static-input").val() +
                            "&inputTypeId=" + $("#input-type").val() +
                            "&quantity=" + $("#quantity").val(),
                    success: function (response) {
                        $("table#inputs-collection-table tbody").html(response);
                        $("#date-collected").val("");
                        $("#input-variety").val("");
                        $("#input-type").val("");
                        $("#quantity").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#date-collected").val("");
            $("#input-variety").val("");
            $("#input-type").val("");
            $("#quantity").val("");
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Loan">
function addLoan() {
    $("#loan-dialog").dialog({
        width: 495,
        height: "auto",
        title: "add_loan_label",
        modal: true,
        resizable: false,
        buttons: {
            "Add": function () {
                $.ajax({
                    url: "doAddLoan",
                    type: "POST",
                    data: "amount=" + $("#loan-amount").val() +
                            "&type=" + $("#loan-type").val() +
                            "&issuingBank=" + $("#issuing-bank").val(),
                    success: function (response) {
                        $("table#loan-table tbody").html(response);
                        $("#loan-amount").val("");
                        $("#loan-type").val("");
                        $("#issuing-bank").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#loan-amount").val("");
            $("#loan-type").val("");
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Login user">
function loginUser() {
    var username = $("#username").val();
    var password = $("#password").val();
    if (username.trim() !== "" || password !== "") {
        if (username !== null || password !== null) {

            if ($('#remember').prop('checked')) {
                $.jStorage.set('username', username);
                $.jStorage.set('password', password);
            } else {
                $.jStorage.deleteKey(username);
                $.jStorage.deleteKey(password);
            }

            $.ajax({
                url: "login",
                type: "POST",
                data: "username=" + username + "&password=" + password,
                success: function () {
                    loadAjaxWindow('home');
                },
                error: function (response) {
                    showError("error_label", response.responseText);
                    return;
                },
                dataType: "HTML"
            });
        }
    }
}


//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Indicator reports">

$(function () {
    $(".indicator-report-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns",
                className: "stretch-display hidden",
                action: function (e, dt, node, config) {
                    alert("sadasds");
//                    var default_action = data_table.button(2).action();
//
//                    data_table.button(2).action(function (e, dt, button, config) {
//
//                        default_action(e, dt, button, config);
//                    });
                }
            }],
        columnDefs: [{
                targets: [1, 2],
                render: function (data, type) {
                    return type === "display" && data.length > 75 ? data.substr(0, 75) + "..." : data;
                }
            }]
    });
});
//<editor-fold defaultstate="collapsed" desc="Outcome level reports">
function setOutcomeAppraisalTarget(id, appraisalTarget, description) {

    appraisalTarget = parseFloat(appraisalTarget) || 0;
    if (appraisalTarget !== "")
        $("#appraisal-target").val(appraisalTarget);
    $("#appraisal-target-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                appraisalTarget = parseFloat($("#appraisal-target").val()) || 0.0;
                $.ajax({
                    url: "setAppraisalTarget",
                    type: "POST",
                    data: "id=" + id +
                            "&appraisalTarget=" + appraisalTarget,
                    success: function () {
                        if (appraisalTarget !== 0.0)
                            $("#appraisal-target-" + id).html(appraisalTarget);
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function changeOutcomeReport() {
    $.ajax({
        type: "POST",
        url: "outcomeLevelReports",
        data: "projectYear=" + $("#project-year").val(),
        success: function () {
            loadAjaxWindow("outcomeLevelReports?projectYear=" + $("#project-year").val());
            return;
        },
        dataType: "HTML"
    });
}

function editOutcomeValue(id, actualValue, expectedValue, description) {
    actualValue = parseFloat(actualValue) || 0;
    expectedValue = parseFloat(expectedValue) || 0;
    if (expectedValue !== 0)
        $("#ratio").val(((actualValue / expectedValue) * 100).toFixed(2) + "%");
    if (expectedValue !== "")
        $("#expected-value option[value=" + parseInt(expectedValue) + "]").attr("selected", "selected");
    if (actualValue !== "")
        $("#actual-value option[value=" + parseInt(actualValue) + "]").attr("selected", "selected");
    $("#outcome-report-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                actualValue = parseFloat($("#actual-value").val()) || 0;
                expectedValue = parseFloat($("#expected-value").val()) || 0.0;
                $.ajax({
                    url: "updateOutcomeValues",
                    type: "POST",
                    data: "id=" + id +
                            "&projectYear=" + $("#project-year").val() +
                            "&actualValue=" + actualValue +
                            "&expectedValue=" + expectedValue,
                    success: function () {
                        if (expectedValue !== 0.0)
                            $("#outcome-ratio-" + id).html(((actualValue / expectedValue) * 100).toFixed(2) + "%");
                        $("#expected-value-" + id).html(expectedValue);
                        $("#actual-value-" + id).html(actualValue);
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Output level reports">
function setOutputAppraisalTarget(id, actualValue, appraisalTarget, description) {

    actualValue = parseFloat(actualValue) || 0;
    appraisalTarget = parseFloat(appraisalTarget) || 0;
    if (appraisalTarget !== 0.0)
        $("#ratio").val(((actualValue / appraisalTarget) * 100).toFixed(2) + "%");
    if (appraisalTarget !== "")
        $("#appraisal-target").val(appraisalTarget);
    if (actualValue !== "")
        $("#actual-value").val(actualValue);
    $("#output-report-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                actualValue = parseFloat($("#actual-value").val()) || 0;
                appraisalTarget = parseFloat($("#appraisal-target").val()) || 0.0;
                $.ajax({
                    url: "setAppraisalTarget",
                    type: "POST",
                    data: "id=" + id +
                            "&appraisalTarget=" + appraisalTarget,
                    success: function () {
                        if (appraisalTarget !== 0.0)
                            $("#output-ratio-" + id).html(((actualValue / appraisalTarget) * 100).toFixed(2) + "%");
                        $("#appraisal-target-" + id).html(appraisalTarget);
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}
//</editor-fold>

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Performance Indicator">

//<editor-fold defaultstate="collapsed" desc="Performance Indicator Datatable">
$(function () {
    $("#performance-indicator-table").removeAttr('width').DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "autoWidth": false,
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add project year',
                action: function () {
                    $("#project-year-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "add_project_year_label",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Save": function () {
                                $("#message").text("Are you sure you want to add this project year?\nThis action is irreversible.");
                                $("#message-dialog").dialog({
                                    width: 495,
                                    height: "auto",
                                    title: "add_project_year",
                                    modal: true,
                                    resizable: false,
                                    buttons: {
                                        "Yes": function () {
                                            $.ajax({
                                                url: "addProjectYear",
                                                type: "POST",
                                                data: "projectYear=" + $("#project-year").val(),
                                                success: function () {
                                                    $("#name").val("");
                                                    loadAjaxWindow("performance_indicators");
                                                    return;
                                                },
                                                error: function (response) {
                                                    showError("error_label", response.responseText);
                                                    return;
                                                },
                                                dataType: "HTML"
                                            });
                                            $(this).dialog("close");
                                        },
                                        "No": function () {
                                            $(this).dialog("close");
                                        }
                                    },
                                    close: function () {
                                    }
                                });
                            }
                        },
                        close: function () {
                            $("#project-year").val("");
                        }
                    });
                }
            },
            'excel',
            {
                text: "Output level report",
                action: function () {
                    loadAjaxWindow("outputLevelReports");
                }
            },
            {
                text: "Outcome level report",
                action: function () {
                    loadAjaxWindow("outcomeLevelReports");
                }
            },
            {
                text: "Goals report",
                action: function () {
                    loadAjaxWindow("goalLevelReports");
                }
            }],
        columnDefs: [{
                "width": "50%", "targets": 3
            }],
        fixedColumns: true
    });
});
//</editor-fold>

$('#performance-indicator-table').each(function () {
    /* Target column to be grouped */
    var columnNumber = 3;

    /* previousCell holds the first instance of same td. Initially first TD=null */
    var previousCell = null;
    var i = 1, j = 1;
    $("tbody", this).find('tr').each(function () {
        /* find the correct td of the correct column */
        /* we are considering the table column 1, You can apply on any table column */
        var currentCell = $(this).find('td:nth-child(' + columnNumber + ')');

        if (j === 11) {
            i = 1;
            j = 1;
            previousCell = null;
        }
        j++;

        if (previousCell === null) {
            /* for first row */
            previousCell = currentCell;
            i = 1;
        } else if (currentCell.text() === previousCell.text()) {
            /* the current td is identical to the previous row td */
            /* remove the current td */
            currentCell.hide();
            /* increment the rowspan attribute of the first row td instance */
            previousCell.attr('rowspan', i + 1);
            i = i + 1;
        } else {
            /* means new value found in current td. So initialize counter variable i */
            previousCell = currentCell;
            i = 1;
        }
    });
});

$('#awpb-table').each(function () {
    /* Target column to be grouped */
    var columnNumber = [4, 5, 6, 7];

    /* previousCell holds the first instance of same td. Initially first TD=null */
    var previousCell = null;
    var i = 1, j = 1, k;
    $("tbody", this).find('tr').each(function () {
        var currentCell;

        for (k = 0; k <= 3; k++) {
            /* find the correct td of the correct column */
            /* we are considering the table column 1, You can apply on any table column */
            currentCell = $(this).find('td:nth-child(' + columnNumber[k] + ')');

            if (j === 11) {
                i = 1;
                j = 1;
                previousCell = null;
            }

            if (previousCell === null) {
                /* for first row */
                previousCell = currentCell;
                i = 1;
            } else if (currentCell.text() === previousCell.text()) {
                /* the current td is identical to the previous row td */
                /* remove the current td */
                currentCell.hide();
                /* increment the rowspan attribute of the first row td instance */
                previousCell.attr('rowspan', i + 1);
                i = i + 1;
            } else {
                /* means new value found in current td. So initialize counter variable i */
                previousCell = currentCell;
                i = 1;
            }
        }
        j++;
    });
});


$("#performance-indicator-table tbody").find('tr').each(function () {
    var colIndex = 4;
    var currentCell = $(this).find('td:nth-child(' + colIndex + ')');
    var data = currentCell.text();
    currentCell.html(data.length > 31 ? data.substr(0, 31) + "..." : data);
});

function addPerformanceIndicator() {
    $.ajax({
        url: "doAddPerformanceIndicator",
        type: "POST",
        data: "performanceIndicatorType=" + $("#performance-indicator-type").val() +
                "&resultHierarchy=" + $("#result-hierarchy").val() +
                "&expectedValue=" + $("#expected-value").val() +
                "&baselineValue=" + $("#baseline-value").val() +
                "&baselineDate=" + $("#baseline-date").val() +
                "&actualValue=" + $("#actual-value").val() +
                "&description=" + $("#description").val() +
                "&projectYear=" + $("#project-year").val() +
                "&ratio=" + $("#ratio").val(),
        success: function () {
            clearPerformanceIndicatorFields();
            loadAjaxWindow("performance_indicators");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function clearPerformanceIndicatorFields() {
    $("#performance-indicator-type").val("");
    $("#result-hierarchy").val("");
    $("#description").val("");
    $("#baseline-date").val("");
    $("#baseline-value").val("");
    $("#project-year").val("");
    $("#actual-value").val("");
    $("#expected-value").val("");
    $("#ratio").val("");
}

function editPerformanceIndicator(id, type, resultHierarchyDescription, description,
        baselineDate, baselineValue, projectYear, actualValue, expectedValue, ratio) {
    if (type !== "")
        $("#performance-indicator-type option[value=" + type + "]").attr('selected', 'selected');
    if (resultHierarchyDescription !== "")
        $("#result-hierarchy option[value=" + resultHierarchyDescription + "]").attr('selected', 'selected');
    $("#description").val(description);
    $("#baseline-date").val(baselineDate);
    $("#baseline-value").val(baselineValue);
    $("#project-year").val(projectYear);
    $("#actual-value").val(actualValue);
    $("#expected-value").val(expectedValue);
    $("#ratio").val(ratio);
    $("#performance-indicators-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_perfomance_indicator_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditPerformanceIndicator",
                    type: "POST",
                    data: "id=" + id +
                            "&performanceIndicatorType=" + $("#performance-indicator-type").val() +
                            "&resultHierarchy=" + $("#result-hierarchy").val() +
                            "&expectedValue=" + $("#expected-value").val() +
                            "&baselineValue=" + $("#baseline-value").val() +
                            "&baselineDate=" + $("#baseline-date").val() +
                            "&actualValue=" + $("#actual-value").val() +
                            "&description=" + $("#description").val() +
                            "&projectYear=" + $("#project-year").val() +
                            "&ratio=" + $("#ratio").val(),
                    success: function () {
                        clearPerformanceIndicatorFields();
                        loadAjaxWindow("performance_indicators");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            clearPerformanceIndicatorFields();
        }
    });
}

function editPerformanceIndicatorValues(id, expectedValue, actualValue, ratio, description) {
    $("#expected-value").val(expectedValue);
    $("#actual-value").val(actualValue);
    $("#ratio").val(ratio);
    $("#performance-indicators-values-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditPerformanceIndicatorValues",
                    type: "POST",
                    data: "id=" + id +
                            "&expectedValue=" + $("#expected-value").val(),
                    success: function () {
                        $("#expected-value").val(0);
                        $("#actual-value").val(0);
                        $("#ratio").val(0.0);
                        loadAjaxWindow("performance_indicators");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#expected-value").val(0);
            $("#actual-value").val(0);
            $("#ratio").val(0.0);
        }
    });
}

function editBaselineDate(cell, id, baselineDate, description) {

    /* get the cell index */
    var cellIndex = cell.cellIndex + 1;
    $("#baseline-date").val(baselineDate);
    $("#baseline-date-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditBaselineDate",
                    type: "POST",
                    data: "id=" + id +
                            "&baselineDate=" + $("#baseline-date").val(),
                    success: function () {
                        var date = new Date($("#baseline-date").val());
                        console.log(getShortYear(date.getFullYear()) + "-" + getMonth(date.getMonth()) + "-" + date.getDate());
                        $('td:nth-child(' + cellIndex + ')', $(cell).parents('tr')).text(getShortYear(date.getFullYear()) + "-" + getMonth(date.getMonth()) + "-" + date.getDate());
                        $("#baseline-date").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        }
    });
}

function editBaselineValue(cell, id, baselineValue, description) {

    var cellIndex = cell.cellIndex + 1;
    $("#baseline-value").val(baselineValue);
    $("#baseline-value-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditBaselineValue",
                    type: "POST",
                    data: "id=" + id +
                            "&baselineValue=" + $("#baseline-value").val(),
                    success: function () {
                        $('td:nth-child(' + cellIndex + ')', $(cell).parents('tr')).text($("#baseline-value").val());
                        $("#baseline-value").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        }
    });
}

function editMeasurementUnit(cell, id, measurementUnitId, description) {

    var cellIndex = cell.cellIndex + 1;
    if (measurementUnitId !== "")
        $("#measurement-unit option[value=" + measurementUnitId + "]").attr("selected", "selected");
    $("#measurement-unit-dialog").dialog({
        width: 495,
        height: "auto",
        title: description,
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditMeasurementUnit",
                    type: "POST",
                    data: "id=" + id +
                            "&measurementUnit=" + $("#measurement-unit").val(),
                    success: function () {
                        $('td:nth-child(' + cellIndex + ')', $(cell).parents('tr')).text($("#measurement-unit option[value=" + $("#measurement-unit").val() + "]").text());
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        }
    });
}

function deletePerformanceIndicator(id) {
    $("#message").text("Are you sure you want to remove this performance indicator?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_performance_indicator",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeletePerformanceIndicator",
                    type: "POST",
                    data: "id=" + id,
                    success: function () {
                        loadAjaxWindow("performance_indicators");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function getShortYear(year) {
    /* convert to string */
    var yearString = year.toString();
    yearString = yearString.substring(2);
    return yearString;
}

function getMonth(num) {
    switch (num) {
        case 0:
            return  Months.JAN;
            break;
        case 1:
            return  Months.FEB;
            break;
        case 2:
            return  Months.MAR;
            break;
        case 3:
            return  Months.APR;
            break;
        case 4:
            return  Months.MAY;
            break;
        case 5:
            return  Months.JUN;
            break;
        case 6:
            return  Months.JUL;
            break;
        case 7:
            return  Months.AUG;
            break;
        case 8:
            return  Months.SEP;
            break;
        case 9:
            return  Months.OCT;
            break;
        case 10:
            return  Months.NOV;
            break;
        case 11:
            return  Months.DEC;
            break;
        default:
            break;
    }
}

var Months = {
    JAN: "Jan",
    FEB: "Feb",
    MAR: "Mar",
    APR: "Apr",
    MAY: "May",
    JUN: "Jun",
    JUL: "Jul",
    AUG: "Aug",
    SEP: "Sep",
    OCT: "Oct",
    NOV: "Nov",
    DEC: "Dec"
};
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Person">

//<editor-fold defaultstate="collapsed" desc="Person Datatable">
$(function () {
    $("#farmers-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        "footerCallback": function () {
            var api = this.api();

            // Total over all pages
            var totalPeople = api
                    .column(2)
                    .data()
                    .reduce(function (a) {
                        return a + 1;
                    }, 0);

            var femaleYouth = api
                    .column(2, {page: 'current'})
                    .data()
                    .reduce(function (a, b) {
                        var array = b.split(", ");
                        var sex = array[0];
                        var age = array[1];
                        if (parseInt(age) <= 35 && sex === "Female") {
                            return a + 1;
                        } else
                            return a;
                    }, 0);

            var femaleElderly = api
                    .column(2, {page: 'current'})
                    .data()
                    .reduce(function (a, b) {
                        var array = b.split(", ");
                        var sex = array[0];
                        var age = array[1];
                        if (parseInt(age) > 35 && sex === "Female") {
                            return a + 1;
                        } else
                            return a;
                    }, 0);

            var maleYouth = api
                    .column(2, {page: 'current'})
                    .data()
                    .reduce(function (a, b) {
                        var array = b.split(", ");
                        var sex = array[0];
                        var age = array[1];
                        if (parseInt(age) <= 35 && sex === "Male") {
                            return a + 1;
                        } else
                            return a;
                    }, 0);

            var maleElderly = api
                    .column(2, {page: 'current'})
                    .data()
                    .reduce(function (a, b) {
                        var array = b.split(", ");
                        var sex = array[0];
                        var age = array[1];
                        if (parseInt(age) > 35 && sex === "Male") {
                            return a + 1;
                        } else
                            return a;
                    }, 0);

            // Total over this page
            var pageTotal = api
                    .column(2, {page: 'current'})
                    .data()
                    .reduce(function (a) {
                        return a + 1;
                    }, 0);

            /* update people-count-table */
            var rows = $("table#people-count-table").find("tbody").find("tr");
            $(rows[2]).find("td:eq(0)").html(femaleYouth);
            $(rows[2]).find("td:eq(1)").html(femaleElderly);
            $(rows[2]).find("td:eq(2)").html(femaleYouth + femaleElderly);
            $(rows[2]).find("td:eq(3)").html(maleYouth);
            $(rows[2]).find("td:eq(4)").html(maleElderly);
            $(rows[2]).find("td:eq(5)").html(maleYouth + maleElderly);
            $(rows[2]).find("td:eq(6)").html(pageTotal);
        },
        buttons: [
            {
                text: 'Add',
                action: function () {
                    loadAjaxWindow($("#add-label").text());
                }
            },
            {
                text: "Upload excel",
                action: function () {
                    loadAjaxWindow("uploadPeople");
                }
            },
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            },
            {
                text: 'Search person',
                action: function () {
                    $("#search-person-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "search_criteria_label",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Search": function () {
                                $.ajax({
                                    url: "searchFarmer",
                                    type: "POST",
                                    data: "nationalId=" + $("#search-national-id").val() +
                                            "&name=" + $("#search-name").val(),
                                    success: function () {
                                        $("#search-national-id").val("");
                                        $("#search-name").val("");
                                        loadAjaxWindow("farmers");
                                    },
                                    error: function (response) {
                                        showError("error_label", response.responseText);
                                        return;
                                    },
                                    dataType: "HTML"
                                });
                                $(this).dialog("close");
                            }
                        },
                        close: function () {
                            $("#search-national-id").val("");
                            $("#search-name").val("");
                        }
                    });
                }
            }]
    });
});
$(function () {
    $("#agro-dealers-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add',
                action: function () {
                    loadAjaxWindow($("#add-label").text());
                }
            },
            {
                text: "Upload excel",
                action: function () {
                    loadAjaxWindow("uploadPeople");
                }
            },
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            },
            {
                text: 'Search person',
                action: function () {
                    $("#search-person-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "search_criteria_label",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Search": function () {
                                $.ajax({
                                    url: "searchAgroDealer",
                                    type: "POST",
                                    data: "nationalId=" + $("#search-national-id").val() +
                                            "&name=" + $("#search-name").val(),
                                    success: function () {
                                        $("#search-national-id").val("");
                                        $("#search-name").val("");
                                        loadAjaxWindow("agroDealers");
                                    },
                                    error: function (response) {
                                        showError("error_label", response.responseText);
                                        return;
                                    },
                                    dataType: "HTML"
                                });
                                $(this).dialog("close");
                            }
                        },
                        close: function () {
                            $("#search-national-id").val("");
                            $("#search-name").val("");
                        }
                    });
                }
            }]
    });
});
$(function () {
    $("#partner-farmers-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            },
            {
                text: 'Search person',
                action: function () {
                    $("#search-person-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "search_criteria_label",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Search": function () {
                                $.ajax({
                                    url: "searchFarmer",
                                    type: "POST",
                                    data: "nationalId=" + $("#search-national-id").val() +
                                            "&name=" + $("#search-name").val(),
                                    success: function () {
                                        $("#search-national-id").val("");
                                        $("#search-name").val("");
                                        loadAjaxWindow("farmers");
                                    },
                                    error: function (response) {
                                        showError("error_label", response.responseText);
                                        return;
                                    },
                                    dataType: "HTML"
                                });
                                $(this).dialog("close");
                            }
                        },
                        close: function () {
                            $("#search-national-id").val("");
                            $("#search-name").val("");
                        }
                    });
                }
            }]
    });
});
$(function () {
    $("#partner-agro-dealers-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                extend: 'colvis',
                text: "Hide / show columns"
            },
            {
                text: 'Search person',
                action: function () {
                    $("#search-person-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "search_criteria_label",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Search": function () {
                                $.ajax({
                                    url: "searchAgroDealer",
                                    type: "POST",
                                    data: "nationalId=" + $("#search-national-id").val() +
                                            "&name=" + $("#search-name").val(),
                                    success: function () {
                                        $("#search-national-id").val("");
                                        $("#search-name").val("");
                                        loadAjaxWindow("agroDealers");
                                    },
                                    error: function (response) {
                                        showError("error_label", response.responseText);
                                        return;
                                    },
                                    dataType: "HTML"
                                });
                                $(this).dialog("close");
                            }
                        },
                        close: function () {
                            $("#search-national-id").val("");
                            $("#search-name").val("");
                        }
                    });
                }
            }]
    });
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="mapping">
function initMap() {

    var locations = [
        [
            '<strong>Java House</strong><br>Moktar Daddah St<br>',
            -1.282641,
            36.8174466
        ],
        [
            '<strong>Chipotle on Belmont</strong><br>1025 W Belmont Ave<br> Chicago, IL 60657',
            -1.2827956,
            36.8290233
        ],
        [
            '<strong>Chipotle on Sheridan</strong><br>6600 N Sheridan Rd<br> Chicago, IL 60626<br>',
            -1.2827956,
            36.824365
        ]
    ];
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 8,
        center: new google.maps.LatLng(locations[0][1], locations[0][2]),
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    var infowindow = new google.maps.InfoWindow({});
    var marker, i;
    for (i = 0; i < locations.length; i++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(locations[i][1], locations[i][2]),
            map: map
        });
        google.maps.event.addListener(marker, 'click', (function (marker, i) {
            return function () {
                infowindow.setContent(locations[i][0]);
                infowindow.open(map, marker);
            };
        })(marker, i));
    }
}
//</editor-fold>

$(function () {
    $("#people-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add',
                action: function () {
                    loadAjaxWindow($("#add-label").text());
                }
            },
            {
                text: "Upload excel",
                action: function () {
                    loadAjaxWindow("uploadPeople");
                }
            },
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }]
    });
});
function addFarmer() {
    $.ajax({
        url: "doAddPerson",
        type: "POST",
        data: "name=" + $("#person-name").val() + "&nationalId=" + $("#national-id").val() +
                "&sex=" + $("#sex").val() + "&farmerGroup=" + $("#farmer-group").val() +
                "&phoneNumber=" + $("#phone").val() + "&businessName=" + $("#business-name").val() +
                "&county=" + $("#county").val() + "&subCounty=" + $("#sub-county").val() +
                "&personRole=" + $("#person-role").val() + "&ward=" + $("#ward").val() +
                "&farmerSubGroup=" + $("#farmer-sub-group").val() + "&postalAddress=" + $("#postal-address").val() +
                "&yearOfBirth=" + $("#year-of-birth").val(),
        success: function () {
            clearPersonFields();
            loadAjaxWindow('farmers');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function addPerson() {
    $.ajax({
        url: "doAddPerson",
        type: "POST",
        data: "name=" + $("#person-name").val() + "&nationalId=" + $("#national-id").val() +
                "&businessName=" + $("#business-name").val() + "&sex=" + $("#sex").val() +
                "&phoneNumber=" + $("#phone").val() + "&email=" + $("#email").val() +
                "&businessName=" + $("#business-name").val() + "&county=" + $("#county").val() +
                "&subCounty=" + $("#sub-county").val() + "&personRole=" + $("#person-role").val() +
                "&ward=" + $("#ward").val() + "&postalAddress=" + $("#postal-address").val() +
                "&yearOfBirth=" + $("#year-of-birth").val(),
        success: function () {
            clearPersonFields();
            loadAjaxWindow('people');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function updateCounts() {
    $.ajax({
        url: "changeCounter",
        type: "POST",
        data: "counter=" + $("#counter").val(),
        success: function (response) {
            $("tr#people-summary").html(response);
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
        , dataType: 'HTML'
    });
}

function updateTraineeCounts() {
    $.ajax({
        url: "changeTraineeCounter",
        type: "POST",
        data: "counter=" + $("#counter").val(),
        success: function (response) {
            $("tr#people-summary").html(response);
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
        , dataType: 'HTML'
    });
}

function editPerson(id, name, sex, personRole, nationalId, yearOfBirth, businessName,
        farmerGroup, farmerSubGroup, location, county, subCounty, ward, contactId, phone, email) {
    $("#person-name").val(name);
    if (sex !== "")
        $("#sex option[value=" + sex + "]").attr('selected', 'selected');
    if (personRole !== "")
        $("#person-role option[value=" + personRole + "]").attr('selected', 'selected');
    $("#national-id").val(nationalId);
    $("#year-of-birth").val(yearOfBirth);
    $("#business-name").val(businessName);
    if (farmerGroup !== "")
        $("#farmer-group option[value=" + farmerGroup + "]").attr('selected', 'selected');
    if (farmerSubGroup !== "")
        $("#farmer-sub-group option[value=" + farmerSubGroup + "]").attr('selected', 'selected');
    if (county !== "")
        $("#county option[value=" + county + "]").attr('selected', 'selected');
    if (subCounty !== "")
        $("#sub-county option[value=" + subCounty + "]").attr('selected', 'selected');
    if (ward !== "")
        $("#ward option[value=" + ward + "]").attr('selected', 'selected');
    $("#phone").val(phone);
    $("#email").val(email);
    $("#person-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_person_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditPerson",
                    type: "POST",
                    data: "id=" + id +
                            "&contactId=" + contactId +
                            "&name=" + $("#person-name").val() +
                            "&nationalId=" + $("#national-id").val() +
                            "&businessName=" + $("#business-name").val() +
                            "&sex=" + $("#sex").val() +
                            "&personRoleId=" + $("#person-role").val() +
                            "&farmerGroup=" + $("#farmer-group").val() +
                            "&phoneNumber=" + $("#phone").val() +
                            "&locationId=" + location +
                            "&email=" + $("#email").val() +
                            "&county=" + $("#county").val() +
                            "&subCounty=" + $("#sub-county").val() +
                            "&personRole=" + $("#person-role").val() +
                            "&ward=" + $("#ward").val() +
                            "&farmerSubGroup=" + $("#farmer-sub-group").val() +
                            "&postalAddress=" + $("#postal-address").val() +
                            "&yearOfBirth=" + $("#year-of-birth").val(),
                    success: function () {
                        clearPersonFields();
                        loadAjaxWindow('people');
                        return;
                    }, error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "Exit": function () {
                clearProcurementPlanFields();
                $(this).dialog("close");
            }
        },
        close: function () {
            clearPersonFields();
        }
    });
}

function deletePerson(id) {
    $("#message").text("Are you sure you want to remove this person?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_training",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
//                $.ajax({
//                    url: "doDeletePerson",
//                    type: "POST",
//                    data: "id=" + id,
//                    success: function () {
//                        loadAjaxWindow("people");
//                    },
//                    error: function (response) {
//                        showError("error_label", response.responseText);return;
//                    },
//                    dataType: "HTML"
//                });
                loadAjaxWindow("people");
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearPersonFields() {
    $("#sex").val("");
    $("#email").val("");
    $("#phone").val("");
    $("#national-id").val("");
    $("#year-of-birth").val("");
    $("#person-name").val("");
    $("#farmer-group").val("");
    $("#postal-address").val("");
    $("#business-name").val("");
    $("#farmer-sub-group").val("");
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Planning">
function addActivityPlanning() {

    $.ajax({
        url: "doAddPlanning",
        type: "POST",
        data: "performanceIndicator=" + $("#performance-indicator").val() + "&implementingPartner=" +
                $("#implementing-partner").val() + "&endPeriod=" + $("#end-period").val() +
                "&allocatedBudget=" + $("#allocated-budget").val() + "&awpbTarget=" +
                $("#awpb-target").val() + "&programmeTarget=" + $("#programme-target").val() +
                "&valueAchieved=" + $("#value-achieved").val() + "&startPeriod=" + $("#start-period").val() +
                "&component=" + $("#component").val() + "&subComponent=" + $("#sub-component").val() +
                "&measurementUnit=" + $("#measurement-unit").val() + "&activity=" + $("#activity").val() +
                "&annualWorkplanReferenceCode=" + $("#annual-workplan-reference-code").val() +
                "&category=" + $("#category").val() + "&procurementPlan=" + $("#procurement-plan").val(),
        success: function () {
            $("#measurement-unit").val("");
            $("#activity").val("");
            $("#category").val("");
            $("#awpb-target").val("");
            $("#component").val("");
            $("#end-period").val("");
            $("#start-period").val("");
            $("#value-achieved").val("");
            $("#sub-component").val("");
            $("#allocated-budget").val("");
            $("#programme-target").val("");
            $("#procurement-plan").val("");
            $("#implementing-partner").val("");
            $("#annual-workplan-reference-code").val("");
            loadAjaxWindow('planning');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Procurement">
$("#procurement-form").ajaxForm({
    success: function () {
        clearProcurementFields();
        loadAjaxWindow('procurements');
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
        return;
    }
});
function editProcurement(id, item, cost, date, serial, description, office, county, subcounty, lpoNumber) {
//    $("#item option[value=" + item + "]").attr('selected', 'selected');
    $("#item").val(item);
    $("#cost").val(cost);
    $("#date-purchased").val(date);
    $("#serial-number").val(serial);
    $("#description").val(description);
    $("#target-office").val(office);
    $("#county option[value=" + county + "]").attr('selected', 'selected');
    $("#sub-county option[value=" + subcounty + "]").attr('selected', 'selected');
    $("#lpo-number").val(lpoNumber);
    $("#procurements-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_procurement_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditProcurement",
                    type: "POST",
                    data: "id=" + id +
                            "&item=" + $("#item").val() +
                            "&cost=" + $("#cost").val() +
                            "&date-purchased=" + $("#date-purchased").val() +
                            "&serial-number=" + $("#serial-number").val() +
                            "&description=" + $("#description").val() +
                            "&target-office=" + $("#target-office").val() +
                            "&county=" + $("#county").val() +
                            "&sub-county=" + $("#sub-county").val() +
                            "&lpo-number=" + $("#lpo-number").val(),
                    success: function () {
                        clearProcurementFields();
                        loadAjaxWindow('procurements');
                        return;
                    }, error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            clearProcurementFields();
        }
    });
}

function deleteProcuremenet(id) {
    $("#message").text("Are you sure you want to remove this procurement?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_procurement",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteProcurement",
                    type: "POST",
                    data: "&id=" + id,
                    success: function (response) {
                        loadAjaxWindow('procurements');
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearProcurementFields() {
    $("#item").val("");
    $("#cost").val("");
    $("#date-purchased").val("");
    $("#serial-number").val("");
    $("#description").val("");
    $("#target-office").val("");
    $("#county").val("");
    $("#sub-county").val("");
    $("#lpo-number").val("");
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Procurement plan">
function addProcurementPlan() {

    $.ajax({
        url: "doAddProcurementPlan",
        type: "POST",
        data: "procurementPlanType=" + $("#procurement-plan-type").val() +
                "&description=" + $("#description").val() +
                "&ifadPriorReview=" + $("#ifad-prior-review").val() +
                "&planVsActual=" + $("#plan-vs-actual").val() +
                "&cost=" + $("#cost").val() +
                "&procurementMethod=" + $("#procurement-method").val() +
                "&completeBd=" + $("#complete-bd").val() +
                "&approvalByIfad1=" + $("#approval-by-ifad1").val() +
                "&approvalByIfad2=" + $("#approval-by-ifad2").val() +
                "&approvalBySda=" + $("#approval-by-sda").val() +
                "&approvalBySdaOrAg=" + $("#approval-by-sda-or-ag").val() +
                "&issueBd=" + $("#issue-bd").val() +
                "&receiveBids=" + $("#receive-bids").val() +
                "&evaluateBids=" + $("#evaluate-bids").val() +
                "&award=" + $("#award").val() +
                "&signContract=" + $("#sign-contract").val() +
                "&commenceContract=" + $("#commence-contract").val(),
        success: function () {
            $("#procurement-plan-type").val("");
            $("#description").val("");
            $("#ifad-prior-review").val("");
            $("#plan-vs-actual").val("");
            $("#cost").val("");
            $("#procurement-method").val("");
            $("#complete-bd").val("");
            $("#approval-by-ifad1").val("");
            $("#approval-by-ifad2").val("");
            $("#approval-by-sda").val("");
            $("#approval-by-sda-or-ag").val("");
            $("#issue-bd").val("");
            $("#receive-bids").val("");
            $("#evaluate-bids").val("");
            $("#award").val("");
            $("#sign-contract").val("");
            $("#commence-contract").val("");
            loadAjaxWindow("procurement_plans");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
        , dataType: "HTML"
    });
}

function editProcurementPlan(id, procurementPlanType, description, ifadPriorReviewchoice, planVsActualchoice,
        cost, procurementMethod, completeBd, approvalByIfad1, approvalBySda, issueBd, receiveBids
        , evaluateBids, approvalByIfad2, award, approvalBySdaOrAg, signContract, commenceContract) {
    if (procurementPlanType !== "")
        $("#procurement-plan-type option[value=" + procurementPlanType + "]").attr('selected', 'selected');
    $("#description").val(description);
    if (ifadPriorReviewchoice !== "")
        $("#ifad-prior-review option[value=" + ifadPriorReviewchoice + "]").attr('selected', 'selected');
    if (planVsActualchoice !== "")
        $("#plan-vs-actual option[value=" + planVsActualchoice + "]").attr('selected', 'selected');
    $("#cost").val(cost);
    if (procurementMethod !== "")
        $("#procurement-method option[value=" + procurementMethod + "]").attr('selected', 'selected');
    $("#complete-bd").val(completeBd);
    $("#approval-by-ifad1").val(approvalByIfad1);
    $("#approval-by-ifad2").val(approvalByIfad2);
    $("#approval-by-sda").val(approvalBySda);
    $("#approval-by-sda-or-ag").val(approvalBySdaOrAg);
    $("#issue-bd").val(issueBd);
    $("#receive-bids").val(receiveBids);
    $("#evaluate-bids").val(evaluateBids);
    $("#award").val(award);
    $("#sign-contract").val(signContract);
    $("#commence-contract").val(commenceContract);
    $("#procurement-plans-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_procurement_plan_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditProcurementPlan",
                    type: "POST",
                    data:
                            "id=" + id +
                            "&procurementPlanType=" + $("#procurement-plan-type").val() +
                            "&description=" + $("#description").val() +
                            "&ifadPriorReview=" + $("#ifad-prior-review").val() +
                            "&planVsActual=" + $("#plan-vs-actual").val() +
                            "&cost=" + $("#cost").val() +
                            "&procurementMethod=" + $("#procurement-method").val() +
                            "&completeBd=" + $("#complete-bd").val() +
                            "&approvalByIfad1=" + $("#approval-by-ifad1").val() +
                            "&approvalByIfad2=" + $("#approval-by-ifad2").val() +
                            "&approvalBySda=" + $("#approval-by-sda").val() +
                            "&approvalBySdaOrAg=" + $("#approval-by-sda-or-ag").val() +
                            "&issueBd=" + $("#issue-bd").val() +
                            "&receiveBids=" + $("#receive-bids").val() +
                            "&evaluateBids=" + $("#evaluate-bids").val() +
                            "&award=" + $("#award").val() +
                            "&signContract=" + $("#sign-contract").val() +
                            "&commenceContract=" + $("#commence-contract").val(),
                    success: function () {
                        clearProcurementPlanFields();
                        loadAjaxWindow("procurement_plans");
                        return;
                    }, error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "Exit": function () {
                clearProcurementPlanFields();
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearProcurementPlanFields() {
    $("#procurement-plan-type").val("");
    $("#description").val("");
    $("#ifad-prior-review").val("");
    $("#plan-vs-actual").val("");
    $("#cost").val("");
    $("#procurement-method").val("");
    $("#complete-bd").val("");
    $("#approval-by-ifad1").val("");
    $("#approval-by-ifad2").val("");
    $("#approval-by-sda").val("");
    $("#approval-by-sda-or-ag").val("");
    $("#issue-bd").val("");
    $("#receive-bids").val("");
    $("#evaluate-bids").val("");
    $("#award").val("");
    $("#sign-contract").val("");
    $("#commence-contract").val("");
}

function deleteProcurementPlan(id) {
    $("#message").text("Are you sure you want to remove this procurement plan - ncs?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_procurement_plan",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteProcurementPlan",
                    type: "POST",
                    data: "id=" + id,
                    success: function () {
                        loadAjaxWindow("procurement_plans");
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Procurement plan -cs">
function addProcurementPlanCs() {

    $.ajax({
        url: "doAddProcurementPlanCs",
        type: "POST",
        data: "description=" + $("#description").val() +
                "&ifadPriorReview=" + $("#ifad-prior-review").val() +
                "&planVsActual=" + $("#plan-vs-actual").val() +
                "&cost=" + $("#cost").val() +
                "&procurementMethod=" + $("#procurement-method").val() +
                "&submitTor=" + $("#submit-tor").val() +
                "&completeReoi=" + $("#complete-reoi").val() +
                "&completeBd=" + $("#complete-bd").val() +
                "&approvalByIfad1=" + $("#approval-by-ifad1").val() +
                "&approvalByIfad2=" + $("#approval-by-ifad2").val() +
                "&approvalByIfad3=" + $("#approval-by-ifad3").val() +
                "&approvalByIfad4=" + $("#approval-by-ifad4").val() +
                "&approvalBySda=" + $("#approval-by-sda").val() +
                "&approvalBySdaOrAg=" + $("#approval-by-sda-or-ag").val() +
                "&issueReoi=" + $("#issue-reoi").val() +
                "&receiveEois=" + $("#receive-eois").val() +
                "&establishShortList=" + $("#establish-short-list").val() +
                "&completeRfp=" + $("#complete-rfp").val() +
                "&issueRfp=" + $("#issue-rfp").val() +
                "&receiveProposals=" + $("#receive-proposals").val() +
                "&evaluateTechnicalProposals=" + $("#evaluate-technical-proposals").val() +
                "&negotiate=" + $("#negotiate").val() +
                "&award=" + $("#award").val() +
                "&signContract=" + $("#sign-contract").val() +
                "&commenceContract=" + $("#commence-contract").val(),
        success: function () {
            $("#procurement-plan-type").val("");
            $("#description").val("");
            $("#ifad-prior-review").val("");
            $("#plan-vs-actual").val("");
            $("#cost").val("");
            $("#procurement-method").val("");
            $("#submit-tor").val("");
            $("#complete-reoi").val("");
            $("#complete-bd").val("");
            $("#approval-by-ifad1").val("");
            $("#approval-by-ifad2").val("");
            $("#approval-by-ifad3").val("");
            $("#approval-by-ifad4").val("");
            $("#approval-by-sda").val("");
            $("#approval-by-sda-or-ag").val("");
            $("#issue-reoi").val("");
            $("#receive-eois").val("");
            $("#establish-short-list").val("");
            $("#complete-rfp").val("");
            $("#issue-rfp").val("");
            $("#receive-proposals").val("");
            $("#evaluate-technical-proposals").val("");
            $("#negotiate").val("");
            $("#award").val("");
            $("#sign-contract").val("");
            $("#commence-contract").val("");
            loadAjaxWindow("procurement_plans_cs");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
        , dataType: "HTML"
    });
}
function deleteProcurementPlanCs(id) {
    $("#message").text("Are you sure you want to remove this procurement plan cs?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_procurement_plan_cs",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteProcurementPlanCs",
                    type: "POST",
                    data: "id=" + id,
                    success: function (response) {
                        $("table#procurement-plan-cs-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function editProcurementPlansCs(id, type, description,
        planVsActual, cost, method, submitTor, completeReoi, completeBd,
        approvalByIfad1, approvalBySda, issueReoi, receiveEois, establishShortList,
        completeRfp, approvalByIfad2, issueRfp, receiveProposals, evaluateProposals,
        approvalByIfad3, negotiate, approvalByIfad4,
        award, approvalBySdaOrAg, signContract, commenceContract) {

    $("#procurement-plan-type option[value=" + type + "]").attr('selected', 'selected');
    $("#description").val(description);
    $("#plan-vs-actual option[value=" + planVsActual + "]").attr('selected', 'selected');
    $("#cost").val(cost);
    $("#procurement-method option[value=" + method + "]").attr('selected', 'selected');
    $("#submit-tor").val(submitTor);
    $("#complete-reoi").val(completeReoi);
    $("#complete-bd").val(completeBd);
    $("#approval-by-ifad1").val(approvalByIfad1);
    $("#approval-by-ifad2").val(approvalByIfad2);
    $("#approval-by-ifad3").val(approvalByIfad3);
    $("#approval-by-ifad4").val(approvalByIfad4);
    $("#approval-by-sda").val(approvalBySda);
    $("#approval-by-sda-or-ag").val(approvalBySdaOrAg);
    $("#issue-reoi").val(issueReoi);
    $("#receive-eois").val(receiveEois);
    $("#establish-short-list").val(establishShortList);
    $("#complete-rfp").val(completeRfp);
    $("#issue-rfp").val(issueRfp);
    $("#receive-proposals").val(receiveProposals);
    $("#evaluate-technical-proposals").val(evaluateProposals);
    $("#negotiate").val(negotiate);
    $("#award").val(award);
    $("#sign-contract").val(signContract);
    $("#commence-contract").val(commenceContract);
    $("#procurement-plans-cs-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_procurements_plans_cs_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditProcurementPlanCs",
                    type: "POST",
                    data: "id=" + id +
                            "&procurementPlanType=" + $("#procurement-plan-type").val() +
                            "&description=" + $("#description").val() +
                            "&ifadPriorReview=" + $("#ifad-prior-review").val() +
                            "&planVsActual=" + $("#plan-vs-actual").val() +
                            "&cost=" + $("#cost").val() +
                            "&procurementMethod=" + $("#procurement-method").val() +
                            "&submitTor=" + $("#submit-tor").val() +
                            "&completeReoi=" + $("#complete-reoi").val() +
                            "&completeBd=" + $("#complete-bd").val() +
                            "&approvalByIfad1=" + $("#approval-by-ifad1").val() +
                            "&approvalByIfad2=" + $("#approval-by-ifad2").val() +
                            "&approvalByIfad3=" + $("#approval-by-ifad3").val() +
                            "&approvalByIfad4=" + $("#approval-by-ifad4").val() +
                            "&approvalBySda=" + $("#approval-by-sda").val() +
                            "&approvalBySdaOrAg=" + $("#approval-by-sda-or-ag").val() +
                            "&issueReoi=" + $("#issue-reoi").val() +
                            "&receiveEois=" + $("#receive-eois").val() +
                            "&establishShortList=" + $("#establish-short-list").val() +
                            "&completeRfp=" + $("#complete-rfp").val() +
                            "&issueRfp=" + $("#issue-rfp").val() +
                            "&receiveProposals=" + $("#receive-proposals").val() +
                            "&evaluateTechnicalProposals=" + $("#evaluate-technical-proposals").val() +
                            "&negotiate=" + $("#negotiate").val() +
                            "&award=" + $("#award").val() +
                            "&signContract=" + $("#sign-contract").val() +
                            "&commenceContract=" + $("#commence-contract").val(),
                    success: function () {
                        $("#procurement-plan-type").val("");
                        $("#description").val("");
                        $("#ifad-prior-review").val("");
                        $("#plan-vs-actual").val("");
                        $("#cost").val("");
                        $("#procurement-method").val("");
                        $("#submit-tor").val("");
                        $("#complete-reoi").val("");
                        $("#complete-bd").val("");
                        $("#approval-by-ifad1").val("");
                        $("#approval-by-ifad2").val("");
                        $("#approval-by-ifad3").val("");
                        $("#approval-by-ifad4").val("");
                        $("#approval-by-sda").val("");
                        $("#approval-by-sda-or-ag").val("");
                        $("#issue-reoi").val("");
                        $("#receive-eois").val("");
                        $("#establish-short-list").val("");
                        $("#complete-rfp").val("");
                        $("#issue-rfp").val("");
                        $("#receive-proposals").val("");
                        $("#evaluate-technical-proposals").val("");
                        $("#negotiate").val("");
                        $("#award").val("");
                        $("#sign-contract").val("");
                        $("#commence-contract").val("");
                        loadAjaxWindow("procurement_plans_cs");
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#procurement-plan-type").val("");
            $("#description").val("");
            $("#ifad-prior-review").val("");
            $("#plan-vs-actual").val("");
            $("#cost").val("");
            $("#procurement-method").val("");
            $("#submit-tor").val("");
            $("#complete-reoi").val("");
            $("#complete-bd").val("");
            $("#approval-by-ifad1").val("");
            $("#approval-by-ifad2").val("");
            $("#approval-by-ifad3").val("");
            $("#approval-by-ifad4").val("");
            $("#approval-by-sda").val("");
            $("#approval-by-sda-or-ag").val("");
            $("#issue-reoi").val("");
            $("#receive-eois").val("");
            $("#establish-short-list").val("");
            $("#complete-rfp").val("");
            $("#issue-rfp").val("");
            $("#receive-proposals").val("");
            $("#evaluate-technical-proposals").val("");
            $("#negotiate").val("");
            $("#award").val("");
            $("#sign-contract").val("");
            $("#commence-contract").val("");
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="AWPB">
$(function () {
    $("#awpb-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add',
                action: function () {
                    loadAjaxWindow("addSubActivity");
                }
            },
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"},
            {
                text: "Financial reports",
                action: function () {
                    loadAjaxWindow("reports");
                }
            }
        ],
        columnDefs: [{
                targets: [3, 4, 5, 6, 7, 8],
                render: function (data, type) {
                    return type === "display" && data.length > 20 ? data.substr(0, 20) + "..." : data;
                }
            }]
    });
});
$("[id$='-percentage']").each(function () {
    $(this).on("change", function () {
        if ($(this).val() > 100 || $(this).val() < 0) {
            $(this).addClass("red");
            $(this).attr('title', "Percentage value lies between 0 and 100");
        } else {
            $(this).removeClass("red");
            $(this).attr('title', "");
        }
    });
});
function addSubActivity() {

    var offRange = false;
    var sum = 0;
    $("[id$='-percentage']").each(function () {
        if ($(this).val() > 100 || $(this).val() < 0) {
            $(this).addClass("red");
            offRange = true;
            $(this).attr('title', "This percentage value should lie between 0 and 100");
        } else {
            $(this).removeClass("red");
            $(this).attr('title', "");
        }
        if ($(this).val() !== "")
            sum = sum + parseInt($(this).val());
    });
    if (offRange)
        return;
    if (sum !== 100) {
        showError("error_label", "Sum of all percentages should be 100. Current sum is " + sum);
        return;
    }

    $.ajax({
        url: "doAddSubActivity",
        type: "POST",
        data: "financialYear=" + $("#financial-year").val() +
                "&annualWorkplanReferenceCode=" + $("#annual-workplan-reference-code").val() +
                "&gfssCode=" + $("#gfss-code").val() +
                "&expectedOutcome=" + $("#expected-outcome").val() +
                "&component=" + $("#component").val() +
                "&subComponent=" + $("#sub-component").val() +
                "&annualIndicatorIds=" + $("#annual-indicator-ids").val() +
                "&activityName=" + $("#activity-name").val() +
                "&subActivityName=" + $("#sub-activity-name").val() +
                "&startDate=" + $("#start-date").val() +
                "&endDate=" + $("#end-date").val() +
                "&measurementUnit=" + $("#measurement-unit").val() +
                "&unitCost=" + $("#unit-cost").val() +
                "&awpbTarget=" + $("#awpb-target").val() +
                "&programmeTarget=" + $("#programme-target").val() +
                "&totals=" + $("#totals").val() +
                "&responsePcu=" + $("#response-pcu").val() +
                "&implementingPartner=" + $("#implementing-partner").val() +
                "&procurementPlan=" + $("#procurement-plan").val() +
                "&description=" + $("#description").val() +
                "&valueAchieved=" + $("#value-achieved").val() +
                "&allocatedBudget=" + $("#allocated-budget").val() +
                "&expenditureCategory=" + $("#expected-category").val() +
                "&gokPercentage=" + $("#gok-percentage").val() +
                "&ifadLoanPercentage=" + $("#ifad-loan-percentage").val() +
                "&ifadGrantPercentage=" + $("#ifad-grant-percentage").val() +
                "&beneficiariesPercentage=" + $("#beneficiaries-percentage").val() +
                "&euPercentage=" + $("#eu-percentage").val() +
                "&financialInstitutionPercentage=" + $("#financial-institution-percentage").val(),
        success: function () {
            clearSubActivityFields();
            loadAjaxWindow("sub_activities");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editSubActivity(id, financialYear, annualWorkplanReferenceCode, gfssCode, expectedOutcome, component, subComponent,
        activityName, subActvityName, startDate, endDate, measurementUnit, unitCost, awpbTarget,
        programmeTarget, totals, responsePcu, implementingPartner, procurementPlan, description,
        valueAchieved, allocatedBudget, expenditureCategory, gokPercentage,
        ifadLoanPercentage, ifadGrantPercentage, beneficiariesPercentage, euPercentage, financialInstitutionPercentage) {
    if (financialYear !== "")
        $("#financial-year option[value=" + financialYear + "]").attr('selected', 'selected');
    if (expectedOutcome !== "")
        $("#expected-outcome option[value=" + expectedOutcome + "]").attr('selected', 'selected');
    if (gfssCode !== "")
        $("#gfss-code option[value=" + gfssCode + "]").attr('selected', 'selected');
    if (measurementUnit !== "")
        $("#measurement-unit option[value=" + measurementUnit + "]").attr('selected', 'selected');
    if (subComponent !== "")
        $("#sub-component option[value=" + subComponent + "]").attr('selected', 'selected');
    if (activityName !== "")
        $("#activity-name option[value=" + activityName + "]").attr('selected', 'selected');
    if (subActvityName !== "")
        $("#sub-activity-name option[value=" + subActvityName + "]").attr('selected', 'selected');
    if (responsePcu !== "")
        $("#response-pcu option[value=" + responsePcu + "]").attr('selected', 'selected');
    if (implementingPartner !== "")
        $("#implementing-partner option[value=" + implementingPartner + "]").attr('selected', 'selected');
    if (expenditureCategory !== "")
        $("#expected-category option[value=" + expenditureCategory + "]").attr('selected', 'selected');
    $("#annual-workplan-reference-code").val(annualWorkplanReferenceCode);
    $("#expected-outcome").val(expectedOutcome);
    $("#component option[value=" + component + "]").attr('selected', 'selected');
    $("#start-date").val(startDate);
    $("#end-date").val(endDate);
    $("#unit-cost").val(unitCost);
    $("#awpb-target").val(awpbTarget);
    $("#programme-target").val(programmeTarget);
    $("#totals").val(totals);
    $("#procurement-plan").val(procurementPlan);
    $("#description").val(description);
    $("#value-achieved").val(valueAchieved);
    $("#allocated-budget").val(allocatedBudget);
    $("#gok-percentage").val(gokPercentage);
    $("#ifad-loan-percentage").val(ifadLoanPercentage);
    $("#ifad-grant-percentage").val(ifadGrantPercentage);
    $("#beneficiaries-percentage").val(beneficiariesPercentage);
    $("#eu-percentage").val(euPercentage);
    $("#financial-institution-percentage").val(financialInstitutionPercentage);
    $("#sub-activity-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_sub_activity",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                var offRange = false;
                var sum = 0;
                $("[id$='-percentage']").each(function () {
                    if ($(this).val() > 100 || $(this).val() < 0) {
                        $(this).addClass("red");
                        offRange = true;
                        $(this).attr('title', "This percentage value should lie between 0 and 100");
                    } else {
                        $(this).removeClass("red");
                        $(this).attr('title', "");
                    }
                    if ($(this).val() !== "")
                        sum = sum + parseInt($(this).val());
                });
                if (offRange)
                    return;
                if (sum !== 100) {
                    showError("error_label", "Sum of all percentages should be 100. Current sum is " + sum);
                    return;
                }

                $.ajax({
                    url: "doEditSubActivity",
                    type: "POST",
                    data: "id=" + id +
                            "&financialYear=" + $("#financial-year").val() +
                            "&annualWorkplanReferenceCode=" + $("#annual-workplan-reference-code").val() +
                            "&gfssCode=" + $("#gfss-code").val() +
                            "&expectedOutcome=" + $("#expected-outcome").val() +
                            "&component=" + $("#component").val() +
                            "&subComponent=" + $("#sub-component").val() +
                            "&annualIndicatorIds=" + $("#annual-indicator-ids").val() +
                            "&activityName=" + $("#activity-name").val() +
                            "&subActivityName=" + $("#sub-activity-name").val() +
                            "&startDate=" + $("#start-date").val() +
                            "&endDate=" + $("#end-date").val() +
                            "&measurementUnit=" + $("#measurement-unit").val() +
                            "&unitCost=" + $("#unit-cost").val() +
                            "&awpbTarget=" + $("#awpb-target").val() +
                            "&programmeTarget=" + $("#programme-target").val() +
                            "&totals=" + $("#totals").val() +
                            "&responsePcu=" + $("#response-pcu").val() +
                            "&implementingPartner=" + $("#implementing-partner").val() +
                            "&procurementPlan=" + $("#procurement-plan").val() +
                            "&description=" + $("#description").val() +
                            "&valueAchieved=" + $("#value-achieved").val() +
                            "&allocatedBudget=" + $("#allocated-budget").val() +
                            "&expenditureCategory=" + $("#expected-category").val() +
                            "&gokPercentage=" + $("#gok-percentage").val() +
                            "&ifadLoanPercentage=" + $("#ifad-loan-percentage").val() +
                            "&ifadGrantPercentage=" + $("#ifad-grant-percentage").val() +
                            "&beneficiariesPercentage=" + $("#beneficiaries-percentage").val() +
                            "&euPercentage=" + $("#eu-percentage").val() +
                            "&financialInstitutionPercentage=" + $("#financial-institution-percentage").val(),
                    success: function () {
                        clearSubActivityFields();
                        loadAjaxWindow("sub_activities");
                        return;
                    }, error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "Exit": function () {
                clearSubActivityFields();
                $(this).dialog("close");
            }
        },
        close: function () {
            clearSubActivityFields();
        }
    });
}

function deleteSubActivity(id) {
    $("#message").text("Are you sure you want to remove this sub-activity?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_sub_activity",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteSubActivity",
                    type: "POST",
                    data: "id=" + id,
                    success: function () {
                        loadAjaxWindow("sub_activities");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearSubActivityFields() {
    $("#financial-year").val("");
    $("#annual-workplan-reference-code").val("");
    $("#gfss-code").val("");
    $("#expected-outcome").val("");
    $("#component").val("");
    $("#sub-component").val("");
    $("#annual-indicator").val("");
    $("#annual-indicator-ids").val("");
    $("#activity-name").val("");
    $("#sub-activity-name").val("");
    $("#start-date").val("");
    $("#end-date").val("");
    $("#measurement-unit").val("");
    $("#unit-cost").val("");
    $("#awpb-target").val("");
    $("#programme-target").val("");
    $("#totals").val("");
    $("#response-pcu").val("");
    $("#implementing-partner").val("");
    $("#procurement-plan").val("");
    $("#description").val("");
    $("#value-achieved").val("");
    $("#allocated-budget").val("");
    $("#expected-category").val("");
    $("#gok-percentage").val("");
    $("#ifad-loan-percentage").val("");
    $("#ifad-grant-percentage").val("");
    $("#beneficiaries-percentage").val("");
    $("#eu-percentage").val("");
    $("#financial-institution-percentage").val("");
}

function loadSubActivitiesWindow(activityPlanningId) {

    $(".loader").show();
    $.isLoading();
    var target = "subActivities";
    $.ajax({
        url: target,
        type: "POST",
        data: "activityPlanningId=" + activityPlanningId,
        success: function () {
            window.location = target;
            return;
        },
        error: function (response) {

            $.isLoading("hide");
            $(".loader").hide();
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function addToAnnualIndicators() {
    $("#annual-indicator-ids").val($("#annual-indicator-ids").val() + "-" + $("#annual-indicator").val());
    if ($("#annual-indicator-descriptions").val() === "") {
        $("#annual-indicator-descriptions").val($("#annual-indicator option[value='" + $("#annual-indicator").val() + "']").text());
    } else {
        $("#annual-indicator-descriptions").val($("#annual-indicator-descriptions").val() + ", " + $("#annual-indicator option[value='" + $("#annual-indicator").val() + "']").text());
    }
}

//<editor-fold defaultstate="collapsed" desc="Activity name">
function addActivityName() {
    $.ajax({
        url: "doAddActivityName",
        type: "POST",
        data: "name=" + $("#name").val(),
        success: function () {
            $("#name").val("");
            loadAjaxWindow("activity_names");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editActivityName(id, name) {
    $("#name").val(name);
    $("#activity-name-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_activity_name_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditActivityName",
                    type: "POST",
                    data: "name=" + $("#name").val() + "&id=" + id,
                    success: function (response) {
                        $("#name").val("");
                        $("table#activity-name-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#name").val("");
        }
    });
}
function deleteActivityName(id) {
    $("#message").text("Are you sure you want to remove this activity name?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_activity_name",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteActivityName",
                    type: "POST",
                    data: "id=" + id,
                    success: function (response) {
                        $("table#activity-name-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sub-activity name">
function addSubActivityName() {
    $.ajax({
        url: "doAddSubActivityName",
        type: "POST",
        data: "name=" + $("#name").val() + "&activityNameId=" + $("#activity-name-id").val(),
        success: function () {
            $("#name").val("");
            loadAjaxWindow("sub_activity_names");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editSubActivityName(id, name, activityNameId) {
    $("#name").val(name);
    $("#sub-activity-name-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_activity_name_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditSubActivityName",
                    type: "POST",
                    data: "name=" + $("#name").val() + "&id=" + id + "&activityNameId=" + activityNameId,
                    success: function (response) {
                        $("#name").val("");
                        $("table#sub-activity-name-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#name").val("");
        }
    });
}

function deleteSubActivityName(id, activityNameId) {
    $("#message").text("Are you sure you want to remove this sub-activity name?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_activity_name",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteSubActivityName",
                    type: "POST",
                    data: "id=" + id + "&activityNameId=" + activityNameId,
                    success: function (response) {
                        $("table#sub-activity-name-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function loadSubActivityNamesWindow(activityNameId) {

    $(".loader").show();
    $.isLoading();
    var target = "sub_activity_names";
    $.ajax({
        url: target,
        type: "POST",
        data: "activityNameId=" + activityNameId,
        success: function () {
            window.location = target;
            return;
        },
        error: function (response) {

            $.isLoading("hide");
            $(".loader").hide();
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Training">
$(function () {
    $("#training-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add',
                action: function () {
                    loadAjaxWindow($("#add-label").text());
                }
            },
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }],
        columnDefs: [{
                targets: 3,
                render: function (data, type) {
                    return type === "display" && data.length > 20 ? data.substr(0, 20) + "..." : data;
                }
            }]
    });
});
$("#training").ajaxForm({
    success: function () {
        clearTrainingFields();
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
        return;
    }
});
function addToTrainers() {
    $("#trainer-ids").val($("#trainer-ids").val() + "-" + $("#trainer").val());
    if ($("#trainer-names").val() === "") {
        $("#trainer-names").val($("#trainer option[value='" + $("#trainer").val() + "']").text());
    } else {
        $("#trainer-names").val($("#trainer-names").val() + ", " + $("#trainer option[value='" + $("#trainer").val() + "']").text());
    }

    updateTrainingModules();
}

function addToTrainees() {
    $("#trainee-ids").val($("#trainee-ids").val() + "-" + $("#trainee").val());
    if ($("#trainee-names").val() === "") {
        $("#trainee-names").val($("#trainee option[value='" + $("#trainee").val() + "']").text());
    } else {
        $("#trainee-names").val($("#trainee-names").val() + ", " + $("#trainee option[value='" + $("#trainee").val() + "']").text());
    }
}

function showTrainees(trainingId) {
    $.ajax({
        url: "loadTrainees",
        type: "POST",
        data: "trainingId=" + trainingId,
        success: function () {
            loadAjaxWindow("trainees");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }, dataType: 'HTML'
    });
}

function editTraining(id, startDate, endDate, topic, venue, county, subCounty, ward, numberOfTrainees) {

    $("#start-date").val(startDate);
    $("#end-date").val(endDate);
    if (topic !== "")
        $("#topic option[value=" + topic + "]").attr('selected', 'selected');
    if (county !== "")
        $("#county option[value=" + county + "]").attr('selected', 'selected');
    if (subCounty !== "")
        $("#sub-county option[value=" + subCounty + "]").attr('selected', 'selected');
    if (ward !== "")
        $("#ward option[value=" + ward + "]").attr('selected', 'selected');
    $("#number-of-trainees").val(numberOfTrainees);
    $("#training-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_training_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditTraining",
                    type: "POST",
                    data: "id=" + id +
                            "&startDate=" + $("#start-date").val() +
                            "&endDate=" + $("#end-date").val() +
                            "&topic=" + $("#topic").val() +
                            "&county=" + $("#county").val() +
                            "&subCounty=" + $("#sub-county").val() +
                            "&venue=" + venue +
                            "&ward=" + $("#ward").val() +
                            "&numberOfTrainees=" + $("#number-of-trainees").val(),
                    success: function () {
                        clearTrainingFields();
                        loadAjaxWindow("training");
                    }, error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            clearTrainingFields();
        }
    });
}

function deleteTraining(id) {
    $("#message").text("Are you sure you want to remove this Training?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_training",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteTraining",
                    type: "POST",
                    data: "id=" + id,
                    success: function () {
                        loadAjaxWindow("training");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearTrainingFields() {
    $("#start-date").val("");
    $("#end-date").val("");
    $("#topic").val("");
    $("#county").val("");
    $("#sub-county").val("");
    $("#ward").val("");
    $("#number-of-trainees").val("");
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Update Selects">
function updateSubComponents() {
    $.ajax({
        type: "POST",
        url: "updateSubComponents",
        data: "componentId=" + $("#component").val(),
        success: function (response) {
            $("#sub-component").html(response);
        },
        dataType: "HTML"
    });
}

function updateTrainingTopics() {
    $.ajax({
        type: "POST",
        url: "updateTopics",
        data: "moduleId=" + $("#training-module").val(),
        success: function (response) {
            $("#topic").html(response);
        },
        dataType: "HTML"
    });
}

function updateTrainingModules() {
    $.ajax({
        type: "POST",
        url: "updateTrainingModules",
        data: "trainerId=" + $("#trainer").val(),
        success: function (response) {
            $("#training-module").html(response);
        },
        dataType: "HTML"
    });
}

function updateInputVarieties() {
    $.ajax({
        type: "POST",
        url: "updateInputVarieties",
        data: "staticInputId=" + $("#static-input").val(),
        success: function (response) {
            $("#input-variety").html(response);
        },
        dataType: "HTML"
    });
}

function updateStaticInputs() {
    $.ajax({
        type: "POST",
        url: "updateStaticInputs",
        data: "inputTypeId=" + $("#input-type").val(),
        success: function (response) {
            $("#static-input").html(response);
        },
        dataType: "HTML"
    });
}

function updateSubActivityNames() {
    $.ajax({
        type: "POST",
        url: "updateSubActivityNames",
        data: "activityNameId=" + $("#activity-name").val(),
        success: function (response) {
            $("#sub-activity-name").html(response);
        }
    });
}

/**
 * Update list of people as well
 */
function updateCounties() {
    $.ajax({
        type: "POST",
        url: "updateCounties",
        data: "regionId=" + $("#region").val(),
        success: function (response) {
            $("#county").html(response);
        },
        dataType: "HTML"
    });
}

function updateSubCounties() {
    $.ajax({
        type: "POST",
        url: "updateSubCounties",
        data: "countyId=" + $("#county").val(),
        success: function (response) {
            $("#sub-county").html(response);
        },
        dataType: "HTML"
    });
}

function updateWards() {
    $.ajax({
        type: "POST",
        url: "updateWards",
        data: "subCountyId=" + $("#sub-county").val(),
        success: function (response) {
            $("#ward").html(response);
        },
        dataType: "HTML"
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Warehouse">
$(function () {
    $("#warehouse-count-table").DataTable({
        responsive: true,
        dom: "Blftip",
//        "bLengthChange": false,
//        "searching": false,
        "scrollX": true,
        "scrollCollapse": true
    });
});
function updateProduceCounts(warehouseId) {
    $.ajax({
        url: "changeProduceCounter",
        type: "POST",
        data: "warehouseId=" + warehouseId + "&counter=" + $("#counter").val(),
        success: function (response) {
            $("tr#produce-summary").html(response);
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
        , dataType: 'HTML'
    });
}

function updateWarehouseCounts() {
    $.ajax({
        url: "changeWarehouseCounter",
        type: "POST",
        data: "counter=" + $("#counter").val(),
        success: function (response) {
            $("tr#warehouse-summary").html(response);
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
        , dataType: 'HTML'
    });
}

function addWarehouse() {
    $.ajax({
        url: "doAddWarehouse",
        type: "POST",
        data: "name=" + $("#warehouse-name").val() +
                "&warehouseType=" + $("#warehouse-type").val() +
                "&warehouseOperator=" + $("#warehouse-operator").val() +
                "&capacity=" + $("#capacity").val() +
                "&capacityUnits=" + $("#capacity-units").val() +
                "&offersWrs=" + $("#offers-wrs").val() +
                "&certified=" + $("#certified").val() +
                "&latitude=" + $("#warehouse-latitude").val() +
                "&longitude=" + $("#warehouse-longitude").val() +
                "&county=" + $("#county").val() +
                "&subCounty=" + $("#sub-county").val() +
                "&ward=" + $("#ward").val() +
                "&warehouseType=" + $("#warehouse-type").val(),
        success: function () {
            clearWarehouseFields();
            loadAjaxWindow('warehouses');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        },
        dataType: "HTML"
    });
}

function editWarehouse(id, name, warehouseType, capacity, units, offersWrs,
        certified, location, subCounty, county, ward, latitude, longitude, warehouseOperator) {
    $("#warehouse-name").val(name);
    $("#warehouse-type option[value=" + warehouseType + "]").attr('selected', 'selected');
    $("#warehouse-operator option[value=" + warehouseOperator + "]").attr('selected', 'selected');
    $("#capacity").val(capacity);
    if (units !== "")
        $("#capacity-units option[value=" + units + "]").attr('selected', 'selected');
    $("#offers-wrs").val(offersWrs);
    $("#certified").val(certified);
    $("#warehouse-latitude").val(latitude);
    $("#warehouse-longitude").val(longitude);
    if (county !== "")
        $("#county option[value=" + county + "]").attr('selected', 'selected');
    if (subCounty !== "")
        $("#sub-county option[value=" + subCounty + "]").attr('selected', 'selected');
    if (ward !== "")
        $("#ward option[value=" + ward + "]").attr('selected', 'selected');
    $("#warehouse-type option[value=" + warehouseOperator + "]").val(warehouseOperator);
    $("#warehouse-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_warehouse_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                $.ajax({
                    url: "doEditWarehouse",
                    type: "POST",
                    data: "id=" + id +
                            "&location=" + location +
                            "&name=" + $("#warehouse-name").val() +
                            "&warehouseOperator=" + $("#warehouse-operator").val() +
                            "&capacity=" + $("#capacity").val() +
                            "&capacityUnits=" + $("#capacity-units").val() +
                            "&offersWrs=" + $("#offers-wrs").val() +
                            "&certified=" + $("#certified").val() +
                            "&latitude=" + $("#warehouse-latitude").val() +
                            "&longitude=" + $("#warehouse-longitude").val() +
                            "&county=" + $("#county").val() +
                            "&subCounty=" + $("#sub-county").val() +
                            "&ward=" + $("#ward").val() +
                            "&warehouseType=" + $("#warehouse-type").val(),
                    success: function () {
                        clearWarehouseFields();
                        loadAjaxWindow("warehouses");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            clearWarehouseFields();
        }
    });
}

function deleteWarehouse(id) {
    $("#message").text("Are you sure you want to remove this warehouse?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_warehouse",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteWarehouse",
                    type: "POST",
                    data: "id=" + id,
                    success: function () {
                        loadAjaxWindow("warehouses");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearWarehouseFields() {
    $("#warehouse-name").val("");
    $("#warehouse-operator").val("");
    $("#capacity").val("");
    $("#capacity-units").val("");
    $("#offers-wrs").val("");
    $("#certified").val("");
    $("#warehouse-latitude").val("");
    $("#warehouse-longitude").val("");
    $("#county").val("");
    $("#sub-county").val("");
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Warehouse operation">
$(function () {
    $("#warehouse-operations-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            'excel',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }]
    });
});
function addWarehouseOperation(warehouseId) {
    $("#warehouse-operation-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_warehouse_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
                if ($("#selling-date").val().trim().length < 1) {
                    showError("error_label", "Kindly select the selling date");
                    return;
                }
                $.ajax({
                    url: "doAddWarehouseOperation",
                    type: "POST",
                    data: "warehouseId=" + warehouseId +
                            "&quantityBrought=" + $("#quantity-brought").val() +
                            "&produceTypeBrought=" + $("#produce-type-brought").val() +
                            "&quantitySold=" + $("#warehouse-operation-quantity-sold").val() +
                            "&produceTypeSold=" + $("#produce-type-sold").val() +
                            "&sellingDate=" + $("#selling-date").val() +
                            "&sellingPrice=" + $("#selling-price").val() +
                            "&buyer=" + $("#buyer").val(),
                    success: function () {
                        clearWarehouseOperationFields();
                        loadEquimentWindow(warehouseId);
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            clearWarehouseOperationFields();
        }
    });
}

function editWarehouseOperation(id, warehouseId, quantityBrought, produceTypeBrought,
        quantitySold, produceTypeSold, sellingDate, sellingPrice, buyer) {
    $("#quantity-brought").val(quantityBrought);
    if (produceTypeBrought !== "")
        $("#produce-type-brought option[value=" + produceTypeBrought + "]").attr('selected', 'selected');
    $("#warehouse-operation-quantity-sold").val(quantitySold);
    if (produceTypeSold !== "")
        $("#produce-type-sold option[value=" + produceTypeSold + "]").attr('selected', 'selected');
    $("#selling-date").val(sellingDate);
    $("#selling-price").val(sellingPrice);
    $("#buyer").val(buyer);
    $("#warehouse-operation-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_warehouse_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {

                $.ajax({
                    url: "doEditWarehouseOperation",
                    type: "POST",
                    data: "id=" + id +
                            "&warehouseId=" + warehouseId +
                            "&quantityBrought=" + $("#quantity-brought").val() +
                            "&produceTypeBrought=" + $("#produce-type-brought").val() +
                            "&quantitySold=" + $("#warehouse-operation-quantity-sold").val() +
                            "&produceTypeSold=" + $("#produce-type-sold").val() +
                            "&sellingDate=" + $("#selling-date").val() +
                            "&sellingPrice=" + $("#selling-price").val() +
                            "&buyer=" + $("#buyer").val(),
                    success: function () {
                        clearWarehouseOperationFields();
                        loadEquimentWindow(warehouseId);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            clearWarehouseOperationFields();
        }
    });
}

function deleteWarehouseOperation(id, warehouseId) {
    $("#message").text("Are you sure you want to remove this warehouse operation?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_warehouse",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "doDeleteWarehouseOperation",
                    type: "POST",
                    data: "id=" + id,
                    success: function () {
                        clearWarehouseOperationFields();
                        loadEquimentWindow(warehouseId);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

function clearWarehouseOperationFields() {
    $("#quantity-brought").val("");
    $("#produce-type-brought").val("");
    $("#warehouse-operation-quantity-sold").val("");
    $("#produce-type-sold").val("");
    $("#selling-date").val("");
    $("#selling-price").val("");
    $("#buyer").val("");
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Password check">
function validatePassword() {
    $.ajax({
        url: "validatePassword",
        type: "POST",
        data: "password=" + $("#old-password").val() + "&personId=" + $("#person-id").val(),
        success: function (data) {
            $("#valid-password-information").html(data);
        }
    });
}

function matchPassword() {

    var password = $("#new-password").val();
    var confirmationPassword = $("#confirm-password").val();
    if (password !== confirmationPassword) {
        $("#matching-password-information").html("<span class=\"btn btn-warning\">Passwords do not match!</span>");
    } else {
        $("#matching-password-information").html("");
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="User account">
function editUserAccount() {
    $.ajax({
        url: "editUserAccount",
        type: "POST",
        data: "username=" + $("#username").val() + "&oldPassword=" + $("#old-password").val() + "&newPassword=" + $("#new-password").val() + "&confirmPassword=" + $("#confirm-password").val() + "&personId=" + $("#person-id").val(),
        success: function (response) {
            showMessage("success_label", response);
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Numeric inputs">
if (!Modernizr.inputtypes.date) {

    $("input[type='number']").keydown(function (e) {
        /* Allow: backspace, delete, tab, escape, enter and . */
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1
                /* Allow: Ctrl + A, Command + A */
                || (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true))
                /* Allow: Ctrl + C, Command + C */
                || (e.keyCode === 67 && (e.ctrlKey === true || e.metaKey === true))
                /* Allow: Ctrl + V, Command + V */
                || (e.keyCode === 86 && (e.ctrlKey === true || e.metaKey === true))
                /* Allow: Ctrl + X, Command + X */
                || (e.keyCode === 88 && (e.ctrlKey === true || e.metaKey === true))
                /* Allow: home, end, left, right, down, up */
                || (e.keyCode >= 35 && e.keyCode <= 40)) {
            /* Let it happen by doing nothing */
            return;
        }
        /* Ensure that it is a number else stop the keypress */
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Uploaded file">
$(function () {
    $("#documents-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Upload new document',
                action: function () {
                    $("#documents-dialog").dialog({
                        width: 495,
                        height: "auto",
                        title: "upload_document_title",
                        resizable: false,
                        modal: false,
                        buttons: {
                            "Submit": function () {
                                $("#document-form").submit();
                                $(this).dialog("close");
                            }
                        },
                        close: function () {
                        }
                    });
                }
            }]
    });
});

function deleteDocument(documentId, cell) {
    $("#message").text("Are you sure you want to remove this document?");
    $("#message-dialog").dialog({
        width: 495,
        height: "auto",
        title: "delete_document_label",
        modal: true,
        resizable: false,
        buttons: {
            "Yes": function () {
                $.ajax({
                    url: "deleteDocument",
                    type: "POST",
                    data: "id=" + documentId,
                    success: function () {
                        $(cell).closest('tr').remove();
                        return;
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                        return;
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
        }
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Merge cells">
//, {
//                text: "Merge cells",
//                action: function () {
//                    $("tr th:nth-child(3)").each(function () {
//                        var t = $(this);
//                        var n = t.next();
//                        t.html(t.html() + n.html());
//                        n.remove();
//                    });
//                    $("tr td:nth-child(3)").each(function () {
//                        var t = $(this);
//                        var n = t.next();
//                        t.html(t.html() + n.html());
//                        n.remove();
//                    });
//                }
//            }
//</editor-fold>
