
//<editor-fold defaultstate="collapsed" desc="Flot Pie Chart">

$(function () {
    $.ajax({
        url: "getPeopleCount",
        type: "POST",
        dataType: "json",
        success: function (peopleCount) {

            $.plot($("#flot-pie-chart"), peopleCount, {
                series: {
                    pie: {
                        show: true
                    }
                },
                grid: {
                    hoverable: true
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%p.0%, %s", // show percentages, rounding to 2 decimal places
                    shifts: {
                        x: 20,
                        y: 0
                    },
                    defaultTheme: false
                }
            });
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
    });
});
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Morris Bar Chart">

$(function () {
    $.ajax({
        url: "getOutputValues",
        type: "POST",
        dataType: "json",
        success: function (outputValues) {

            Morris.Bar({
                element: 'morris-bar-chart',
                data: outputValues,
                xkey: 'description',
                ykeys: ['targetValue', 'actualValue'],
                labels: ['Target value', 'Actual value'],
                hideHover: 'auto',
                resize: true
            });
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
    });
});
//</editor-fold>
