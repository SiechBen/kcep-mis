var language = "en";
//<editor-fold defaultstate="collapsed" desc="Menu">
$(function () {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
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

//<editor-fold defaultstate="collapsed" desc="Override href default functionality">
//$('a').click(function (event) {
//    event.preventDefault();
//    $.ajax({
//        url: $(this).attr('href'),
//        success: function (response) {
//            alert(response);
//        }
//    });
//    return false; //for good measure
//});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Date picker, data tables">
$(function () {
    $(".data-table").DataTable({
        responsive: true
    });

    $(".datefield").datepicker();
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Document">
$(document).ready(function () {

    $.i18n.properties({
        name: "text",
        path: "assets/bundles/",
        mode: "both",
        language: language
    });

    //<editor-fold defaultstate="collapsed" desc="Button Icons">
    $(".backButton").button({
        text: false,
        icons: {
            primary: "ui-icon-arrowthick-1-w"
        }
    });
    $(".fowardButton").button({
        text: false,
        icons: {
            primary: "ui-icon-arrowthick-1-e"
        }
    });
    $(".cancelButton").button({
        text: false,
        icons: {
            primary: "ui-icon-cancel"
        }
    });
    $(".addButton").button({
        text: false,
        icons: {
            primary: "ui-icon-plus"
        }
    });
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
    $(".saveButton").button({
        text: false,
        icons: {
            primary: "ui-icon-disk"
        }
    });
    $(".openButton").button({
        text: false,
        icons: {
            primary: "ui-icon-extlink"
        }
    });
    $(".searchButton").button({
        text: false,
        icons: {
            primary: "ui-icon-search"
        }
    });
    $(".openFolderButton").button({
        text: false,
        icons: {
            primary: "ui-icon-folder-open"
        }
    });
    $(".refreshButton").button({
        text: false,
        icons: {
            primary: "ui-icon-refresh"
        }
    });
    $(".downloadButton").button({
        text: false,
        icons: {
            primary: "ui-icon-arrowthick-1-s"
        }
    });
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Button Tooltips">
    $("i").tooltip();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Zebra Stripes">
    //$("tr:odd").addClass("odd");
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Tabs">

    $("#selectSecondTabs").tabs({active: 1});
    $("#selectThirdTabs").tabs({active: 2});
    $("#tabs").tabs();
    $("#tabs2").tabs();
    $("#innertabs").tabs();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Date Pickers">
    $(".datePicker").datepicker({
        dateFormat: "dd-mm-yy",
        changeMonth: true,
        changeYear: true,
        yearRange: "-80:+0" // last eighty years
    });
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Accordion">
    var headers = ["H1", "H2", "H3", "H4", "H5", "H6"];
    $(".accordion").click(function (e) {
        var target = e.target,
                name = target.nodeName.toUpperCase();
        if ($.inArray(name, headers) > -1) {
            var subItem = $(target).next();
            //slideUp all elements (except target) at current depth or greater
            var depth = $(subItem).parents().length;
            var allAtDepth = $(".accordion p, .accordion div").filter(function () {
                if ($(this).parents().length >= depth && this !== subItem.get(0)) {
                    return true;
                }
            });
            $(allAtDepth).slideUp("fast");
            //slideToggle target content and adjust bottom border if necessary
            subItem.slideToggle("fast", function () {
                $(".accordion :visible:last").css("border-radius", "0 0 10px 10px");
            });
            $(target).css({"border-bottom-right-radius": "0", "border-bottom-left-radius": "0"});
        }
    });
    $("#accordion").accordion({
        header: "> div > h3",
        /*event: "click hoverintent",*/
        heightStyle: "content",
        collapsible: true
    })
            .sortable({
                axis: "y",
                handle: "h3",
                stop: function (event, ui) {
                    // IE doesn"t register the blur when sorting
                    // so trigger focusout handlers to remove .ui-state-focus
                    ui.item.children("h3").triggerHandler("focusout");
                }
            });
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AJAX Setup">
    $(function () {
        //setup ajax error handling
        $.ajaxSetup({
            error: function (x) {
                if (x.status === 403) {
                    showMessage("Error", "Sorry, your session has expired. Please login again to continue");
                    window.location.href = "/sci/index.jsp";
                } else {
                    showMessage("Error ", x.responseText);
                }
            },
            headers: {
                "Accept-Language": language
            }
        });
    });
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Events">

    /*Search*/
    $("input#search").keyup(function (e) {
// Set Timeout
        clearTimeout($.data(this, "timer"));
        // Set Search String
        var search_string = $(this).val();
        // Do Search
        if (search_string === "") {
            $("div#results").fadeOut();
        } else {
            $("div#results").fadeIn();
            $(this).data("timer", setTimeout("search", 100));
        }
    });
    $("input#participantsSearch").keyup(function (e) {
        $("#filteredPersonsList tbody").load("/sci/filterOutPotentialParticipants", {
            filter: $(e.target).val()
        }, function () {
            $(".addButton").button({
                text: false,
                icons: {
                    primary: "ui-icon-plus"
                }
            });
            $(".openButton").button({
                text: false,
                icons: {
                    primary: "ui-icon-extlink"
                }
            });
        });
    });
    $("input#organizationsSearch").keyup(function (e) {
        $("#filteredOrganizationsList tbody").load("/sci/filterOutPotentialOrganizers", {
            filter: $(e.target).val()
        }, function () {
            $(".addButton").button({
                text: false,
                icons: {
                    primary: "ui-icon-plus"
                }
            });
            $(".openButton").button({
                text: false,
                icons: {
                    primary: "ui-icon-extlink"
                }
            });
        });
    });
    $("input#publicMemberTypesSearch").keyup(function (e) {
        $("#filteredPublicMemberTypesList tbody").load("/sci/filterOutPotentialTargetPublicMemberTypes", {
            filter: $(e.target).val()
        }, function () {
            $(".addButton").button({
                text: false,
                icons: {
                    primary: "ui-icon-plus"
                }
            });
            $(".openButton").button({
                text: false,
                icons: {
                    primary: "ui-icon-extlink"
                }
            });
        });
    });
    $("#modulesCovered input[type='checkbox']").change(function () {
        if (this.checked) {
// the checkbox was checked
            addModuleCovered($(this).val());
        } else {
// the checkbox was unchecked
            removeModuleCovered($(this).val());
        }
    });
    $("#professionalCompetencyTable input[type='checkbox']").change(function () {
        if (this.checked) {
// the checkbox was checked
            addPersonsProfessionalCompetency($(this).val());
        } else {
// the checkbox was unchecked
            removePersonsProfessionalCompetency($(this).val());
        }
    });
    $("#afflictedFacilityExcludesGroupRow input[type='checkbox'']").change(function () {
        if (this.checked) {
// the checkbox was checked
            $("#exclusionBasisRow").show();
        } else {
// the checkbox was unchecked
            $("#exclusionBasisRow").hide();
        }
    });
    $("#afflictedFacilityIsReopeningRow input[type='checkbox']").change(function () {
        if (this.checked) {
// the checkbox was checked
            $("#reopeningDateRow").show();
        } else {
// the checkbox was unchecked
            $("#reopeningDateRow").hide();
        }
    });
    $("#workgroupTypesPermissions input[type='checkbox']").change(function () {
        if (this.checked) {
// the checkbox was checked
            addWorkgroupTypeViewPermission($(this).val());
        } else {
// the checkbox was unchecked
            removeWorkgroupTypeViewPermission($(this).val());
        }
    });
    $("#workgroupPermissions input[type='checkbox']").change(function () {
        if (this.checked) {
// the checkbox was checked
            addWorkgroupViewPermission($(this).val());
        } else {
// the checkbox was unchecked
            removeWorkgroupViewPermission($(this).val());
        }
    });
    $("#afflictedFacility").change(function (e) {
        $("#afflictedFacilityType").load("/sci/filterOutFacilityTypes", {
            facility: $(e.target).val()
        });
    });
    $("#incomingMajor").change(function (e) {
        $("#incomingCourse").load("/sci/filterOutCourses", {
            major: $(e.target).val()
        });
    });
    $("#incumbentMajor").change(function (e) {
        $("#incumbentCourse").load("/sci/filterOutCourses", {
            major: $(e.target).val()
        });
    });
    $("#initialAlertSource").change(function (e) {
        var source = $("#initialAlertSource option:selected").text();
        if (source.toLowerCase() === "other") {
            addSelectableSourceOfInitialAlert();
        }
    });
    $("#informantCategory").change(function (e) {
        var category = $("#informantCategory option:selected").text();
        if (category.toLowerCase() === "other") {
            addSelectableInformantCategory();
        }
    });
    $("#physicalEvidenceType").change(function (e) {
        var physicalEvidence = $("#physicalEvidenceType option:selected").text();
        if (physicalEvidence.toLowerCase() === "other") {
            addSelectablePhysicalEvidenceType();
        }
    });
    $("#incidenceLocation").change(function (e) {
        var incidenceLocation = $("#incidenceLocation option:selected").text();
        if (incidenceLocation.toLowerCase() === "other") {
            addSelectableIncidenceLocation();
        }
    });
    $("#caseCategory").change(function (e) {
        $("#caseInstance").load("/sci/filterViolations", {
            violationCategoryId: $(e.target).val(),
            violationTypeId: $("#caseSubcategory").val()
        });
    });
    $("#caseSubcategory").change(function (e) {
        $("#caseInstance").load("/sci/filterViolations", {
            violationTypeId: $(e.target).val(),
            violationCategoryId: $("#caseCategory").val()
        });
    });
    $("#perpetratorCategory").change(function (e) {
        $("#perpetratorSubCategory").load("/sci/filterOutPerpetratorSubcategories", {
            category: $(e.target).val()
        });
    });
    $("#facilitiesConsiderations input[type='checkbox']").change(function () {
        if (this.checked) {
// the checkbox was checked
            addFacilityConsideration($(this).val());
        } else {
// the checkbox was unchecked
            removeFacilityConsideration($(this).val());
        }
    });
    $("#afflictedReligion").change(function (e) {
        var religion = $("#afflictedReligion option:selected").text();
        if (religion.toLowerCase() === "other") {
            addSelectableReligion();
        }
    });
    $("#afflictedCivilianStatus").change(function (e) {
        var status = $("#afflictedCivilianStatus option:selected").text();
        if (status.toLowerCase() === "other") {
            addSelectableCivilianStatus();
        }
    });
    $("#afflictedChildCareArrangement").change(function (e) {
        var arrangement = $("#afflictedChildCareArrangement option:selected").text();
        if (arrangement.toLowerCase() === "other") {
            addSelectableChildCareArrangement();
        }
    });
    $("#violationPurpose").change(function (e) {
        var violationPurpose = $("#violationPurpose option:selected").text();
        if (violationPurpose.toLowerCase() === "other") {
            addSelectableViolationPurpose();
        }
    });
    $("#violationContext").change(function (e) {
        var violationContext = $("#violationContext option:selected").text();
        if (violationContext.toLowerCase() === "other") {
            addSelectableViolationContext();
        }
    });
    $("#violationMechanism").change(function (e) {
        var violationMechanism = $("#violationMechanism option:selected").text();
        if (violationMechanism.toLowerCase() === "other") {
            addSelectableViolationMechanism();
        }
    });
    $("#violationOutcome").change(function (e) {
        var violationOutcome = $("#violationOutcome option:selected").text();
        if (violationOutcome.toLowerCase() === "other") {
            addSelectableViolationOutcome();
        }
    });
    $("#evaluationPrepared input[type='checkbox']").change(function () {
        if (this.checked) {
//The checkbox was checked
//Elevate status to ready
            updateStatus("2");
        }
    });
    $("#evaluationReady input[type='checkbox']").change(function () {
        if (!this.checked) {
//The checkbox was unchecked
//Revert status to in-preparation
            updateStatus("1");
        }
    });
    $("#issueEvaluation input[type='checkbox']").change(function () {
        if (this.checked) {
//The checkbox was checked
//Make the evaluation available to the attendants
            updateStatus("3");
        }
    });
    $("#evaluationCompleted input[type='checkbox']").change(function () {
        if (this.checked) {
//The checkbox was checked
//Make the evaluation as having been completed by the trainees
            updateStatus("4");
        }
    });
    $("#courseType").change(function (e) {



//Hide all course blocks
        $("div.courseBlock").hide();
        //Get the selected course type
        courseType = $(e.target).val();
        //Show the selected course type block
        switch (courseType) {
            case "1"://MULTIPLE_CHOICE_ONE_ANSWER,
                $("div#MULTIPLE_CHOICE_ONE_ANSWER").show();
                break;
            case "2"://MULTIPLE_CHOICE_MULTIPLE_ANSWERS,
                $("div#MULTIPLE_CHOICE_MULTIPLE_ANSWERS").show();
                break;
            case "3"://COMMENT_OR_ESSAY_BOX_SINGLE,
                $("div#COMMENT_OR_ESSAY_BOX_SINGLE").show();
                break;
            case "4"://RANKING,
                $("div#RANKING").show();
                break;
            case "5"://RATING,
                $("div#RATING").show();
                break;
            case "6"://MATRIX_OF_CHOICES_ONE_ANSWER,
                $("div#MATRIX_OF_CHOICES_ONE_ANSWER").show();
                break;
            case "7"://MATRIX_OF_CHOICES_MULTIPLE_ANSWERS,
                $("div#MATRIX_OF_CHOICES_MULTIPLE_ANSWERS").show();
                break;
            case "8"://MATRIX_OF_DROP_DOWN_MENUS,
                $("div#MATRIX_OF_DROP_DOWN_MENUS").show();
                break;
            case "9"://TEXTBOXES_GENERAL,
                $("div#TEXTBOXES_GENERAL").show();
                break;
            case "10"://COMMENT_OR_ESSAY_BOX_MULTIPLE,
                $("div#COMMENT_OR_ESSAY_BOX_MULTIPLE").show();
                break;
            case "11"://TEXTBOXES_NUMERICAL,
                $("div#TEXTBOXES_NUMERICAL").show();
                break;
            case "12"://TEXTBOXES_DATE
                $("div#TEXTBOXES_DATE").show();
                break;
        }
    });
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Animations">
    // Animate buttons, move reflection and fade
    $("#report-tools img").hover(function () {
        $(this).stop().animate({
            marginTop: "-10px"
        }, 200);
        $(this).parent().find("span").stop().animate({
            marginTop: "18px",
            opacity: 0.25
        }, 200);
    }, function () {
        $(this).stop().animate({
            marginTop: "0px"
        }, 300);
        $(this).parent().find("span").stop().animate({
            marginTop: "1px",
            opacity: 1
        }, 300);
    });
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    //</editor-fold>

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
                            type: 'POST',
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

    if ($("#username").val().trim() !== "" || $("#password").val().trim() !== "") {
        if ($("#username").val() !== null || $("#password").val() !== null) {

            $.ajax({
                url: "login",
                type: "POST",
                data: "username=" + $("#username").val() + "&password=" + $("#password").val(),
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
    $("#trainer-names").val($("#trainer-names").val() + " -> " + $("#trainer option[value='" + $("#trainer").val() + "']").text());
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

//<editor-fold defaultstate="collapsed" desc="Programme">
function addProgramme() {

    $.ajax({
        url: "doAddProgramme",
        type: "POST",
        data: "activity=" + $("#activity").val() + "&implementingPartner=" + $("#implementing-partner").val() +
                "&endPeriod=" + $("#end-period").val() + "&requestedBudget=" + $("#requested-budget").val() +
                "&awpbTarget=" + $("#awpb-target").val() + "&programmeTarget=" + $("#programme-target").val() +
                "&valueAchieved=" + $("#value-achieved").val() + "&startPeriod=" + $("#start-period").val() +
                "&component=" + $("#component").val() + "&subComponent=" + $("#sub-component").val() +
                "&measurementUnit=" + $("#measurement-unit").val() + "&actualExpenditure=" + $("#actual-expenditure").val(),
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
            $("#programme-target").val("");
            $("#actual-expenditure").val("");
            $("#implementing-partner").val("");
            loadAjaxWindow('programmes');
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
function addEquipment() {

    $.ajax({
        url: "doAddEquipment",
        type: "POST",
        data: "equipmentType=" + $("#equipment-type").val() + "&equipmentTotalCount=" + $("#equipment-total-count").val() +
                "&equipmentStatus=" + $("#equipment-status").val(),
        success: function () {

            $("#equipment-total-count").val("");
            $("#equipment-status").val("");
            $("#equipment-type").val("");
            loadAjaxWindow('equipment');
            return;
        },
        error: function (response) {
            showError("error_label", response.responseText);
        },
        dataType: "HTML"
    });
}
//</editor-fold>

