/* global google */

//<editor-fold defaultstate="collapsed" desc="Get locations">
function getLocations() {
    $.ajax({
        url: "getLocations",
        type: "POST",
        data: "personType=" + $("#person-type").val(),
        dataType: "json",
        success: function (locations) {

            var map = new google.maps.Map(document.getElementById('map'), {
                zoom: 6,
                center: new google.maps.LatLng(locations[0].lat, locations[0].long),
                mapTypeId: google.maps.MapTypeId.ROADMAP
            });
            var infowindow = new google.maps.InfoWindow({});
            var marker, i;
            for (i = 0; i < locations.length; i++) {
                marker = new google.maps.Marker({
                    position: new google.maps.LatLng(locations[i].lat, locations[i].long),
                    map: map
                });
                google.maps.event.addListener(marker, 'mouseover', (function (marker, i) {
                    return function () {
                        infowindow.setContent(locations[i].info);
                        infowindow.open(map, marker);
                    };
                })(marker, i));
                google.maps.event.addListener(marker, 'mouseout', (function () {
                    return function () {
                        infowindow.close();
                    };
                })(marker, i));
            }
        },
        error: function (response) {
            showError("error_label", response.responseText);
            return;
        }
    });
}

//</editor-fold>