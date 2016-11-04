var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function (r) {
    if (this.getStatus() == BMAP_STATUS_SUCCESS) {
        if (r.address != null && r.address.city != null) {
            var location = {city: r.address.city, lat: r.point.lat, lng: r.point.lng}
            if (window.localStorage && location != undefined) {
                var storageLocation = localStorage.getItem("_location");
                if (storageLocation == null || storageLocation == '') {
                    alert(location.city);
                } else {
                    var storageLocationObj = JSON.parse(storageLocation);
                    if (storageLocationObj.city != location.city) {
                        layer.confirm('您当前定位到' + location.city + ",是否需要切换到" + location.city, {
                            btn: ['是', '否'] //按钮
                        }, function () {
                            alert(location.city);
                            //layer.close();
                        }, function () {
                            //layer.close();
                        });
                    }
                }
            }
            return location;
        }
    }
    else {
        layer.msg("定位失败");
    }
}, {enableHighAccuracy: true})