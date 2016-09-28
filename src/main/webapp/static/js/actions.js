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
    $(".datefield").datepicker();
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Year picker">
$(function () {
    var yearNow = new Date().getFullYear();
    var yearInTheFuture = yearNow + 10;
    var decadeAgo = yearNow - 10;
    for (yearOption = decadeAgo; yearOption <= yearInTheFuture; yearOption++) {
        if (yearOption === yearNow) {
            $(".yearfield").append($("<option/>").val(yearOption).attr("selected", "selected").html(yearOption));
        } else {
            $(".yearfield").append($("<option/>").val(yearOption).html(yearOption));
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
    $("#performance-indicator-table").DataTable({
        responsive: true,
        "scrollX": true,
        "scrollY": "200",
        "scrollCollapse": true,
        dom: "Blftip",
        buttons: [
            {
                text: 'Add year',
                action: function () {
                    $("#year-of-use-dialog").dialog({
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
                                                url: "addYearOfUse",
                                                type: "POST",
                                                data: "yearOfUse=" + $("#year-of-use").val(),
                                                success: function () {
                                                    $("#name").val("");
                                                    loadAjaxWindow("performance_indicators");
                                                    return;
                                                },
                                                error: function (response) {
                                                    showError("error_label", response.responseText);
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
                            $("#year-of-use").val("");
                        }
                    });
                }
            },
            'excel',
//            {
//                extend: 'colvis',
//                text: "Hide / show columns"
//            }
            {
                text: "Output level report",
                action: function () {
                    loadAjaxWindow("indicatorReports");
                }
            }],
        columnDefs: [{
                targets: [2, 3],
                render: function (data, type) {
                    return type === "display" && data.length > 31 ? data.substr(0, 31) + "..." : data;
                }
            }]
    });
});

$(function () {
    $("#indicator-report-table").DataTable({
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
            }],
        columnDefs: [{
                targets: [1, 2],
                render: function (data, type) {
                    return type === "display" && data.length > 40 ? data.substr(0, 40) + "..." : data;
                }
            }]
    });
});

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
        responsive: true,
        "scrollCollapse": true,
        dom: "Brt",
        buttons: ['excel', 'print',
            {
                extend: 'colvis',
                text: "Hide / show columns"
            }]
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

//<editor-fold defaultstate="collapsed" desc="Indicator AV:EV ratio calculation">
$("#actual-value").on("input", function () {
    calculateRatio();
});
$("#expected-value").on("input", function () {
    calculateRatio();
});
function calculateRatio() {

    var actualValue = $("#actual-value").val();
    var expectedValue = $("#expected-value").val();
    if (actualValue.trim() === "") {
        actualValue = 0;
    }
    if (expectedValue.trim() === "") {
        expectedValue = 0;
    }

    $("#ratio").val((parseFloat(actualValue) / parseFloat(expectedValue) * 100).toFixed(2));
}
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
    var quantityHarvested = $("#quantity-harvested").val();
    var familyConsumption = $("#family-consumption").val();
    var quantitySold = $("#quantity-sold").val();
    if (quantityHarvested.trim() === "") {
        quantityHarvested = 0;
    }
    if (familyConsumption.trim() === "") {
        familyConsumption = 0;
    }
    if (quantitySold.trim() === "") {
        quantitySold = 0;
    }
    console.log(quantityHarvested);
    console.log(quantitySold);
    console.log(familyConsumption);
    console.log(parseFloat(quantityHarvested) - (parseFloat(familyConsumption) + parseFloat(quantitySold)));
    console.log("");
    $("#post-harvest-loss").val((parseFloat(quantityHarvested) -
            (parseFloat(familyConsumption) + parseFloat(quantitySold))).toFixed(2));
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
                "&county=" + $("#county").val() + "&subCounty=" + $("#sub-county").val() +
                "&personRole=" + $("#person-role").val() + "&ward=" + $("#ward").val() +
                "&farmerSubGroup=" + $("#farmer-sub-group").val() + "&postalAddress=" + $("#postal-address").val() +
                "&dateOfBirth=" + $("#date-of-birth").val(),
        success: function () {
            clearPersonFields();
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
        success: function (response) {
            $("tr#people-summary").html(response);
        },
        error: function (response) {
            showError("error_label", response.responseText);
        }
        , dataType: 'HTML'
    });
}

function editPerson(id, name, sex, personRole, nationalId, dateOfBirth, businessName,
        farmerGroup, farmerSubGroup, location, county, subCounty, ward, contactId, phone, email) {
    $("#person-name").val(name);
    if (sex !== "")
        $("#sex option[value=" + sex + "]").attr('selected', 'selected');
    if (personRole !== "")
        $("#person-role option[value=" + personRole + "]").attr('selected', 'selected');
    $("#national-id").val(nationalId);
    $("#date-of-birth").val(dateOfBirth);
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
                            "&dateOfBirth=" + $("#date-of-birth").val(),
                    success: function () {
                        clearPersonFields();
                        loadAjaxWindow('people');
                        return;
                    }, error: function (response) {
                        showError("error_label", response.responseText);
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
//                        showError("error_label", response.responseText);
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
    $("#date-of-birth").val("");
    $("#person-name").val("");
    $("#farmer-group").val("");
    $("#ward").val("");
    $("#postal-address").val("");
    $("#county").val("");
    $("#business-name").val("");
    $("#farmer-sub-group").val("");
    $("#sub-county").val("");
}

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Training">
$("#training").ajaxForm({
    success: function () {
        clearTrainingFields();
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
                    url: "doDeletEVoucher",
                    type: "POST",
                    data: "id=" + id,
                    success: function (response) {
//                        loadAjaxWindow("eVouchers");
                        $("table#e-voucher-table tbody").html(response);
                    },
                    error: function (response) {
                        showError("error_label", response.responseText);
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

//<editor-fold defaultstate="collapsed" desc="Warehouse">
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
        },
        dataType: "HTML"
    });
}

function editWarehouse(id, name, warehouseType, capacity, units, offers,
        certified, location, subCounty, county, latitude, longitude, operator) {
    $("#warehouse-name").val(name);
    $("#warehouse-type option[value=" + warehouseType + "]").attr('selected', 'selected');
    $("#warehouse-operator option[value=" + operator + "]").attr('selected', 'selected');
    $("#capacity").val(capacity);
    if (units !== "")
        $("#capacity-units option[value=" + units + "]").attr('selected', 'selected');
    $("#offers-wrs").val(offers);
    $("#certified").val(certified);
    $("#warehouse-latitude").val(latitude);
    $("#warehouse-longitude").val(longitude);
    if (county !== "")
        $("#county option[value=" + county + "]").attr('selected', 'selected');
    if (subCounty !== "")
        $("#sub-county option[value=" + subCounty + "]").attr('selected', 'selected');
    $("#warehouse-type option[value=" + operator + "]").val(operator);
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
                            "&subCounty=" + $("#county").val() +
                            "&warehouseType=" + $("#warehouse-type").val(),
                    success: function () {
                        clearWarehouseFields();
                        loadAjaxWindow("warehouses");
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
function addWarehouseOperation(warehouseId) {
    $("#warehouse-operation-dialog").dialog({
        width: 495,
        height: "auto",
        title: "edit_warehouse_label",
        resizable: false,
        modal: false,
        buttons: {
            "Save": function () {
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
    }
}
);
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sub-activity">
function addSubActivity() {
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
    $("#annual-workplan-reference-code").val(annualWorkplanReferenceCode);
    if (gfssCode !== "")
        $("#gfss-code option[value=" + gfssCode + "]").attr('selected', 'selected');
    $("#expected-outcome").val(expectedOutcome);
    $("#component option[value=" + component + "]").attr('selected', 'selected');
    if (subComponent !== "")
        $("#sub-component option[value=" + subComponent + "]").attr('selected', 'selected');
    if (activityName !== "")
        $("#activity-name option[value=" + activityName + "]").attr('selected', 'selected');
    if (subActvityName !== "")
        $("#sub-activity-name option[value=" + subActvityName + "]").attr('selected', 'selected');
    $("#start-date").val(startDate);
    $("#end-date").val(endDate);
    if (measurementUnit !== "")
        $("#measurement-unit option[value=" + measurementUnit + "]").attr('selected', 'selected');
    $("#unit-cost").val(unitCost);
    $("#awpb-target").val(awpbTarget);
    $("#programme-target").val(programmeTarget);
    $("#totals").val(totals);
    if (responsePcu !== "")
        $("#response-pcu option[value=" + responsePcu + "]").attr('selected', 'selected');
    if (implementingPartner !== "")
        $("#implementing-partner option[value=" + implementingPartner + "]").attr('selected', 'selected');
    $("#procurement-plan").val(procurementPlan);
    $("#description").val(description);
    $("#value-achieved").val(valueAchieved);
    $("#allocated-budget").val(allocatedBudget);
    if (expenditureCategory !== "")
        $("#expected-category option[value=" + expenditureCategory + "]").attr('selected', 'selected');
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

function addToAnnualIndicators() {
    $("#annual-indicator-ids").val($("#annual-indicator-ids").val() + "-" + $("#annual-indicator").val());
    if ($("#annual-indicator-descriptions").val() === "") {
        $("#annual-indicator-descriptions").val($("#annual-indicator option[value='" + $("#annual-indicator").val() + "']").text());
    } else {
        $("#annual-indicator-descriptions").val($("#annual-indicator-descriptions").val() + ", " + $("#annual-indicator option[value='" + $("#annual-indicator").val() + "']").text());
    }
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
            clearPerformanceIndicatorFields();
            loadAjaxWindow("performance_indicators");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
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
    $("#year-of-use").val("");
    $("#actual-value").val("");
    $("#expected-value").val("");
    $("#ratio").val("");
}

function editPerformanceIndicator(id, type, resultHierarchyDescription, description,
        baselineDate, baselineValue, yearOfUse, actualValue, expectedValue, ratio) {
    $("#performance-indicator-type option[value=" + type + "]").attr('selected', 'selected');
    $("#result-hierarchy option[value=" + resultHierarchyDescription + "]").attr('selected', 'selected');
    $("#description").val(description);
    $("#baseline-date").val(baselineDate);
    $("#baseline-value").val(baselineValue);
    $("#year-of-use").val(yearOfUse);
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
                            "&yearOfUse=" + $("#year-of-use").val() +
                            "&ratio=" + $("#ratio").val(),
                    success: function () {
                        clearPerformanceIndicatorFields();
                        loadAjaxWindow("performance_indicators");
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

function editBaselineDate(id, baselineDate, description) {
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
                        $("#baseline-date").val("");
                        loadAjaxWindow("performance_indicators");
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
            $("#ratio").val("");
        }
    });
}

function editBaselineValue(id, baselineValue, description) {
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
                        $("#baseline-value").val("");
                        loadAjaxWindow("performance_indicators");
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
            $("#ratio").val("");
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
                    },
                    dataType: "HTML"
                });
                $(this).dialog("close");
            }
        },
        close: function () {
            $("#loan-amount").val("");
            $("#loan-type").val("");
            $("#issuing-bank").val("");
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
                        $("#agro-dealer").val("");
                        $("#input-variety").val("");
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
            $("#input-variety").val("");
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

function updateTopics() {
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
        data: "name=" + $("#name").val() + "&activityNameId=" + $("#activityNameId").val(),
        success: function () {
            $("#name").val("");
            loadAjaxWindow("sub_activity_names");
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}

function loadSubActivityNamesWindow(activityNameId) {
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
            showError("error_label", response.responseText);
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
                    url: "editAccount",
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
