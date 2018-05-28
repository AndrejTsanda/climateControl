<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Главная / Умный инкубатор</title>

    <!-- Include CSS styles -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/normalize.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/unsemantic-grid-responsive-tablet.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/ionicons.min.css"/>">
</head>
<body class="dashboard-page">

    <div class="header-container">
        <div class="grid-container">
            <div class="grid-50">
                <div class="nav-bar">
                    <ul>
                        <li><a class="active" href="<c:url value="/dashboard" />">Главная</a></li>
                        <li><a href="<c:url value="/mode" />">Режимы</a></li>
                    </ul>
                </div>
            </div>
            <div class="grid-50">
                <div class="stats-bar">
                    <div class="stats-mode-title">Текущий режим:</div>
                    <div class="stats-mode-name">
                        <c:choose>
                            <c:when test="${current_mode != null}">
                                <c:out value="${current_mode}" />
                            </c:when>
                            <c:otherwise>
                                Не доступно
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- .END header container -->


    <!-- NEED FOR TESTING, AFTER DELETE -->
    <div class="device-test">
        <div class="grid-container">
            <a href="<c:url value="/devices/cooling" />" title="Тестирования охдаждения"><i class="ion-ios-flower"></i></a>
            <a href="<c:url value="/devices/heating" />" title="Тестирования нагревания"><i class="ion-ios-flame"></i></a>
        </div>
    </div>


    <div class="statistics-container">
        <div class="grid-container">
            <div class="grid-25">
                <div class="temperature-bar">
                    <div class="temperature-header">Температура:</div>
                    <div id="temperature-sensor-1" class="temperature-info circle"></div>
                </div>
            </div>
            <div class="grid-25">
                <div class="temperature-bar">
                    <div class="temperature-header">Температура:</div>
                    <div id="temperature-sensor-2" class="temperature-info circle"></div>
                </div>
            </div>
            <div class="grid-25">
                <div class="temperature-bar">
                    <div class="temperature-header">Температура:</div>
                    <div id="temperature-sensor-3" class="temperature-info circle"></div>
                </div>
            </div>

            <div class="grid-25">
                <div class="humidity-bar">
                    <div class="humidity-header">Влажность:</div>
                    <div id="humidity-sensor-1" class="humidity-info circle"></div>
                </div>
            </div>
        </div>
    </div><!-- .END statistics container -->

    <div class="charts-container">
        <div class="grid-container">
            <div class="grid-100">
                <div class="chart-container">
                    <div id="temperature-chart"></div>
                </div>
            </div>
            <div class="grid-100">
                <div class="chart-container">
                    <div id="humidity-chart"></div>
                </div>
            </div>
        </div>
    </div><!-- .END charts container -->

    <!-- Include JS libraries -->
    <script src="<c:url value="/assets/js/vendor/jquery-1.11.2.min.js"/>"></script>
    <script src="<c:url value="/assets/js/vendor/circles.min.js"/>"></script>
    <script src="<c:url value="/assets/js/vendor/highcharts.js"/>"></script>
    <script src="<c:url value="/assets/js/vendor/exporting.js"/>"></script>
    <script src="<c:url value="/assets/js/chart-theme.js"/>"></script>

    <script type="text/javascript">
        $( document ).ready(function() {
            var temperature_sensor_1 = Circles.create({
                id: 'temperature-sensor-1',
                radius: 80,
                value: 0,
                maxValue: 100,
                width: 4,
                text: function(value){return value + '°C';},
                colors: ['#323c51', '#6BB6FF'],
                duration: 400,
                textClass: 'circles-text'
            });

            var temperature_sensor_2 = Circles.create({
                id: 'temperature-sensor-2',
                radius: 80,
                value: 0,
                maxValue: 100,
                width: 4,
                text: function(value){return value + '°C';},
                colors: ['#323c51', '#6BB6FF'],
                duration: 400,
                textClass: 'circles-text'
            });

            var temperature_sensor_3 = Circles.create({
                id: 'temperature-sensor-3',
                radius: 80,
                value: 0,
                maxValue: 100,
                width: 4,
                text: function(value){return value + '°C';},
                colors: ['#323c51', '#6BB6FF'],
                duration: 400,
                textClass: 'circles-text'
            });

            var humidity_sensor_1 = Circles.create({
                id: 'humidity-sensor-1',
                radius: 80,
                value: 0,
                maxValue: 100,
                width: 4,
                text: function(value){return value + '%';},
                colors: ['#323c51', '#FF5360'],
                duration: 400,
                textClass: 'circles-text'
            });

            // call ajax query each 3 seconds and update circles for temperature and humidity
            setInterval(function() {
                $.ajax({
                    type: 'GET',
                    // maybe not need this header
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'text/plain'
                    },
                    // or url: 'http://192.168.1.2:8080/smart_incubator/statistic/get',
                    url: '<c:url value="/statistic/get" />',
                    dataType: 'json',
                    success: function (response) {
                        var data = JSON.stringify(response);
                        var obj = JSON.parse(data);

                        // update temperature
                        temperature_sensor_1.update(obj.temperatureSensor_1);
                        temperature_sensor_2.update(obj.temperatureSensor_2);
                        temperature_sensor_3.update(obj.temperatureSensor_3);

                        // update humidity
                        humidity_sensor_1.update(obj.humiditySensor_1)
                    }
                });
            }, 3000);
        });
    </script>

    <script type="text/javascript">
        $(function () {
            $(document).ready(function () {
                Highcharts.setOptions({
                    global: {
                        useUTC: false
                    }
                });

                $('#temperature-chart').highcharts({
                    chart: {
                        // set type charts
                        //type: 'spline',
                        type: 'areaspline',
                        marginRight: 10,
                        zoomType: 'x',
                        events: {
                            load: function () {
                                var series = this.series[0];

                                // call ajax query each 3 seconds and update temperature charts (add new point)
                                setInterval(function () {
                                    $.ajax({
                                        type: 'GET',
                                        // maybe not need this header
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'text/plain'
                                        },
                                        // or url: 'http://192.168.1.2:8080/smart_incubator/statistic/get',
                                        url: '<c:url value="/statistic/get" />',
                                        dataType: 'json',
                                        success: function (response) {
                                            var data = JSON.stringify(response);
                                            var obj = JSON.parse(data);

                                            var x = (new Date()).getTime(),
                                                    y = (obj.temperatureSensor_1 + obj.temperatureSensor_2 + obj.temperatureSensor_3) / 3;

                                            // add new point on chart
                                            series.addPoint([x, y], true, true);
                                        }
                                    });
                                }, 3000);
                            }
                        }
                    },
                    title: {
                        text: 'Средняя температура в инкубаторе'
                    },
                    subtitle: {
                        text: 'Данные предоставлены сервисом Smart Incubator'
                    },
                    xAxis: {
                        type: 'datetime',
                        tickPixelInterval: 150
                    },
                    yAxis: {
                        title: {
                            text: 'Температура (°C)'
                        }
                    },
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>' +
                                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                                    Highcharts.numberFormat(this.y, 2);
                        },
                        valueSuffix: ' units'
                    },
                    exporting: {
                        enabled: true
                    },
                    plotOptions: {
                        spline: {
                            marker: {
                                enabled: true
                            }
                        }
                    },
                    series: [{
                        name: 'Средняя температура',
                        data: (function () {
                            // create random data array, need for create chart, this code need rewrite
                            var data = [],
                                    time = (new Date()).getTime(),
                                    i;

                            for (i = -18; i <= 0; i += 1) {
                                data.push({
                                    x: time + i * 1000,
                                    y: Math.random()
                                });
                            }
                            return data;
                        }())
                    }]
                });
            });
        });
    </script>

    <script type="text/javascript">
        $(function () {
            $(document).ready(function () {
                Highcharts.setOptions({
                    global: {
                        useUTC: false
                    }
                });

                $('#humidity-chart').highcharts({
                    chart: {
                        // set type charts
                        //type: 'spline',
                        type: 'areaspline',
                        marginRight: 10,
                        zoomType: 'x',
                        events: {
                            load: function () {
                                var series = this.series[0];

                                // call ajax query each 3 seconds and update temperature charts (add new point)
                                setInterval(function () {
                                    $.ajax({
                                        type: 'GET',
                                        // maybe not need this header
                                        headers: {
                                            'Accept': 'application/json',
                                            'Content-Type': 'text/plain'
                                        },
                                        // or url: 'http://192.168.1.2:8080/smart_incubator/statistic/get',
                                        url: '<c:url value="/statistic/get" />',
                                        dataType: 'json',
                                        success: function (response) {
                                            var data = JSON.stringify(response);
                                            var obj = JSON.parse(data);

                                            var x = (new Date()).getTime(),
                                                    y = obj.humiditySensor_1;

                                            // add new point on chart
                                            series.addPoint([x, y], true, true);
                                        }
                                    });
                                }, 3000);
                            }
                        }
                    },
                    title: {
                        text: 'Средняя влажность в инкубаторе'
                    },
                    subtitle: {
                        text: 'Данные предоставлены сервисом Smart Incubator'
                    },
                    xAxis: {
                        type: 'datetime',
                        tickPixelInterval: 150
                    },
                    yAxis: {
                        title: {
                            text: 'Влажность (%)'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    tooltip: {
                        formatter: function () {
                            return '<b>' + this.series.name + '</b><br/>' +
                                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                                    Highcharts.numberFormat(this.y, 2);
                        },
                        valueSuffix: ' units'
                    },
                    exporting: {
                        enabled: true
                    },
                    plotOptions: {
                        spline: {
                            marker: {
                                enabled: true
                            }
                        }
                    },
                    series: [{
                        name: 'Средняя влажность',
                        data: (function () {
                            // create random data array, need for create chart, this code need rewrite
                            var data = [],
                                    time = (new Date()).getTime(),
                                    i;

                            for (i = -18; i <= 0; i += 1) {
                                data.push({
                                    x: time + i * 1000,
                                    y: Math.random()
                                });
                            }
                            return data;
                        }())
                    }]
                });
            });
        });
    </script>
</body>
</html>
