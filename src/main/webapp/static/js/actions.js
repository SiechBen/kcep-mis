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

//<editor-fold defaultstate="collapsed" desc="Datepicker">
$(function () {
    $(".datefield").datepicker();
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Year picker">
$(function () {
    var yearNow = new Date().getFullYear();
    var yearInTheFuture = yearNow + 100;
    var centuryAgo = yearNow - 100;
    for (yearOption = centuryAgo; yearOption <= yearInTheFuture; yearOption++) {
        if (yearOption === yearNow) {
            $(".yearfield").append($("<option/>").val(yearOption).attr("selected", "selected").html(yearOption));
        } else {
            $(".yearfield").append($("<option/>").val(yearOption).html(yearOption));
        }
    }
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="DataTable">
$(function () {
    $(".data-table").DataTable({
        responsive: true,
        'scrollX': true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
                //"paging": true
    });
});

$(function () {
    $(".reports-table").DataTable({
        responsive: true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Brt",
        buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
                //"paging": true
    });
});

$(function () {
    $(".dt-button").each(function () {
        $(this).removeClass(".dt-button").addClass("btn btn-default");
    });
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Application attributes">
$.ajax({
    url: "load",
    type: "POST",
    data: "",
    error: function (response) {
        showError("error_label", response.responseText);
    },
    dataType: "HTML"
});
function loadApplicationAttributes() {
    $.ajax({
        url: "load",
        type: "POST",
        data: "",
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Indicator AV:EV ratio">
$("#actual-value").on("input", function () {
    var actualValue = $("#actual-value").val();
    var expectedValue = $("#expected-value").val();
    $("#ratio").val((actualValue / expectedValue * 100).toFixed(2));
});

$("#expected-value").on("input", function () {
    var actualValue = $("#actual-value").val();
    var expectedValue = $("#expected-value").val();
    $("#ratio").val((actualValue / expectedValue * 100).toFixed(2));
});
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
            alert('Please correct the errors in the Form');
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
    window.location = target;
}

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

function loadPreviousWindow() {
    parent.history.back();
    return false;
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
                    return;
                },
                error: function (response) {
                    showError("error_label", response.responseText);
                },
                dataType: "HTML"
            });
        }
    }
}


//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Person">
function addPerson() {

    $.ajax({
        url: "doAddPerson",
        type: "POST",
        data: "name=" + $("#person-name").val() + "&nationalId=" + $("#national-id").val() +
                "&businessName=" + $("#business-name").val() + "&sex=" + $("#sex").val() +
                "&farmerGroup=" + $("#farmer-group").val() + "&phoneNumber=" + $("#phone").val() +
                "&email=" + $("#email").val() + "&businessName=" + $("#business-name").val() +
                "&county=" + $("#person-county").val() + "&subCounty=" + $("#person-sub-county").val() +
                "&personRole=" + $("#person-role").val() + "&ward=" + $("#person-ward").val() +
                "&farmerSubGroup=" + $("#farmer-sub-group").val() + "&postalAddress=" + $("#postal-address").val() +
                "&dateOfBirth=" + $("#date-of-birth").val(),
        success: function () {

            $("#sex").val("");
            $("#email").val("");
            $("#phone").val("");
            $("#national-id").val("");
            $("#date-of-birth").val("");
            $("#person-name").val("");
            $("#farmer-group").val("");
            $("#person-ward").val("");
            $("#postal-address").val("");
            $("#person-county").val("");
            $("#business-name").val("");
            $("#farmer-sub-group").val("");
            $("#person-sub-county").val("");
            loadAjaxWindow('people');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}

function updateCounts() {
    $.ajax({
        url: "changeCounter",
        type: "POST",
        data: "counter=" + $("#counter").val(),
        success: function (data) {
            $("tr#people-summary").html(data);
        },
        error: function (response) {
            showError("error_label", response.responseText);
        }
        , dataType: 'HTML'
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Training">
$("#training-form").ajaxForm({
    success: function () {
        $("#start-date").val("");
        $("#end-date").val("");
        $("#trainer").val("");
        $("#topic").val("");
        $("#venue").val("");
        $("#number-of-trainees").val("");
        $("#category-of-trainees").val("");
        $("#attendance-sheet").val("");
        loadAjaxWindow('training');
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
    }
});
function addToTrainers() {
    $("#trainer-ids").val($("#trainer-ids").val() + "-" + $("#trainer").val());
    if ($("#trainer-names").val() === "") {
        $("#trainer-names").val($("#trainer option[value='" + $("#trainer").val() + "']").text());
    } else {
        $("#trainer-names").val($("#trainer-names").val() + ", " + $("#trainer option[value='" + $("#trainer").val() + "']").text());
    }
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
        }, dataType: 'HTML'
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="E-voucher">
$("#e-voucher-form").ajaxForm({
    success: function () {
        $("#e-voucher-amount").val("");
        $("#e-voucher-input-type").val("");
        $("#e-voucher-person").val("");
        $("#date-redeemed").val("");
        $("#inputs-loogbook-page").val("");
        loadAjaxWindow('eVouchers');
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
    }
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Procurement">
$("#procurement-form").ajaxForm({
    success: function () {
        $("#item").val("");
        $("#cost").val("");
        $("#date-purchased").val("");
        $("#serial-number").val("");
        $("#description").val("");
        $("#target-office").val("");
        $("#procurement-county").val("");
        $("#procurement-sub-county").val("");
        $("#lpo-number").val("");
        $("#invoice-or-receipt").val("");
        loadAjaxWindow('procurements');
        return;
    },
    error: function (response) {
        showError("error_label", response.responseText);
    }
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Warehouse">
function addWarehouse() {
    $.ajax({
        url: "doAddWarehouse",
        type: "POST",
        data: "name=" + $("#warehouse-name").val() + "&warehouseOperator=" + $("#warehouse-operator").val() +
                "&capacity=" + $("#capacity").val() + "&capacityUnits=" + $("#capacity-units").val() +
                "&offersWrs=" + $("#offers-wrs").val() + "&certified=" + $("#certified").val() +
                "&latitude=" + $("#warehouse-latitude").val() + "&longitude=" + $("#warehouse-longitude").val() +
                "&county=" + $("#warehouse-county").val() + "&subCounty=" + $("#warehouse-sub-county").val() +
                "&ward=" + $("#warehouse-ward").val() + "&warehouseType=" + $("#warehouse-type").val(),
        success: function () {

            $("#warehouse-name").val("");
            $("#warehouse-operator").val("");
            $("#capacity").val("");
            $("#capacity-units").val("");
            $("#offers-wrs").val("");
            $("#certified").val("");
            $("#warehouse-latitude").val("");
            $("#warehouse-longitude").val("");
            $("#warehouse-county").val("");
            $("#warehouse-sub-county").val("");
            $("#warehouse-ward").val("");
            $("#warehouse-type").val("");
            loadAjaxWindow('warehouses');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Planning">
function addPlanning() {

    $.ajax({
        url: "doAddPlanning",
        type: "POST",
        data: "performanceIndicator=" + $("#performance-indicator").val() + "&implementingPartner=" +
                $("#implementing-partner").val() + "&endPeriod=" + $("#end-period").val() +
                "&requestedBudget=" + $("#requested-budget").val() + "&awpbTarget=" +
                $("#awpb-target").val() + "&planningTarget=" + $("#planning-target").val() +
                "&valueAchieved=" + $("#value-achieved").val() + "&startPeriod=" + $("#start-period").val() +
                "&component=" + $("#component").val() + "&subComponent=" + $("#sub-component").val() +
                "&measurementUnit=" + $("#measurement-unit").val() +
                "&annualWorkplanReferenceCode=" + $("#annual-workplan-reference-code").val(),
        success: function () {

            $("#measurement-unit").val("");
            $("#activity").val("");
            $("#awpb-target").val("");
            $("#component").val("");
            $("#end-period").val("");
            $("#start-period").val("");
            $("#value-achieved").val("");
            $("#sub-component").val("");
            $("#requested-budget").val("");
            $("#planning-target").val("");
            $("#implementing-partner").val("");
            $("#annual-workplan-reference-code").val("");
            loadAjaxWindow('planning');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Equipment">

function loadEquimentWindow(warehouseId) {
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
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}

function addEquipment(warehouseId) {
    $.ajax({
        url: "doAddEquipment",
        type: "POST",
        data: "equipmentType=" + $("#equipment-type").val() + "&equipmentTotalCount=" + $("#equipment-total-count").val() +
                "&equipmentStatus=" + $("#equipment-status").val(),
        success: function () {
            $("#equipment-total-count").val("");
            $("#equipment-status").val("");
            $("#equipment-type").val("");
            loadEquimentWindow()(warehouseId);
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
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
    }
}
);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sub-activity">
function loadSubActivitiesWindow(activityPlanningId) {
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
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}
function addSubActivity() {
    $.ajax({
        url: "doAddSubActivity",
        type: "POST",
        data: "description=" + $("#description").val() + "&measurementUnit=" + $("#measurement-unit").val()
                + "&startDate=" + $("#start-date").val() + "&endDate=" + $("#end-date").val()
                + "&actualExpenditure=" + $("#actual-expenditure").val() + "&activityPlanningId=" + $("#activityPlanningId").val(),
        success: function () {
            $("#description").val("");
            $("#measurement-unit").val("");
            $("#start-date").val("");
            $("#end-date").val("");
            $("#actual-expenditure").val("");
            loadSubActivitiesWindow($("#activityPlanningId").val());
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
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
        }
        , dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Procurement plan -cs">
function addProcurementPlanCs() {

    $.ajax({
        url: "doAddProcurementPlanCs",
        type: "POST",
        data: "procurementPlanType=" + $("#procurement-plan-type").val() +
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
        }
        , dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Performance Indicator">
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
                "&yearOfUse=" + $("#year-of-use").val() +
                "&ratio=" + $("#ratio").val(),
        success: function () {
            $("#performance-indicator-type").val();
            $("#result-hierarchy").val();
            $("#description").val();
            $("#baseline-date").val();
            $("#baseline-value").val();
            $("#year-of-use").val();
            $("#actual-value").val();
            $("#expected-value").val();
            $("#ratio").val();

            loadAjaxWindow("performance_indicators");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Farm">

function loadFarmWindow(farmerId) {
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
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
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
                            "&type=" + $("#loan-type").val(),
                    success: function (response) {
                        $("table#loan-table tbody").html(response);
                        $("#loan-amount").val("");
                        $("#loan-type").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
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
                            "&agroDealerId=" + $("#agro-dealer").val() +
                            "&inputTypeId=" + $("#input-type").val() +
                            "&quantity=" + $("#quantity").val(),
                    success: function (response) {
                        $("table#inputs-collection-table tbody").html(response);
                        $("#date-collected").val("");
                        $("#agro-dealer").val("");
                        $("#input-type").val("");
                        $("#quantity").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#date-collected").val("");
            $("#agro-dealer").val("");
            $("#input-type").val("");
            $("#quantity").val("");
        }
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
                            "&farmActivityName=" + $("#farm-activity-name").val() +
                            "&quantityHarvested=" + $("#quantity-harvested").val() +
                            "&averageSellingPrice=" + $("#average-selling-price").val(),
                    success: function (response) {
                        $("table#farm-activity-table tbody").html(response);
                        $("#yield").val("");
                        $("#quantity-sold").val("");
                        $("#farm-activity-date").val("");
                        $("#farm-activity-name").val("");
                        $("#quantity-harvested").val("");
                        $("#average-selling-price").val("");
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
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
            $("#farm-activity-name").val("");
            $("#quantity-harvested").val("");
            $("#average-selling-price").val("");
        }
    });
}
//</editor-fold>
