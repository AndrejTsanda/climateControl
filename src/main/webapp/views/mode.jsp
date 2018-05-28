<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <title>Режимы / Умный инкубатор</title>

    <!-- Include CSS styles -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/normalize.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/unsemantic-grid-responsive-tablet.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/ionicons.min.css"/>">
</head>
<body class="mode-page">

    <div class="header-container">
        <div class="grid-container">
            <div class="grid-50">
                <div class="nav-bar">
                    <ul>
                        <li><a href="<c:url value="/dashboard" />">Главная</a></li>
                        <li><a class="active" href="<c:url value="/mode" />">Режимы</a></li>
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

    <div class="modes-container">
        <div class="grid-container">
            <div class="grid-100">
                <div class="modes-nav">
                    <a class="open" href="#open" title="Добавить новый режим"><i class="ion-plus-round"></i></a>

                    <div id="open" class="modal-window">
                        <div>
                            <a class="close" href="#close">
                                <i class="ion-close-round"></i>
                            </a>

                            <div class="modal-header">
                                <h2>Добавить новый режим:</h2>
                            </div>

                            <c:url var="user_confirmed_add"  value="/admin/users/confirmed/add" />
                            <form cssClass="user-form" modelAttribute="user" method="post" action="${user_confirmed_add}">
                                <div class="mode-field">
                                    <input type="text" placeholder="Название режима">
                                </div>
                                <div class="mode-field">
                                    <input type="text" placeholder="Минимальная температура">
                                </div>
                                <div class="mode-field">
                                    <input type="text" placeholder="Максимальная температура">
                                </div>
                                <div class="mode-field">
                                    <input type="text" placeholder="Минимальная влажность">
                                </div>
                                <div class="mode-field">
                                    <input type="text" placeholder="Максимальная влажность">
                                </div>
                                <div class="mode-field">
                                    <input type="text" placeholder="Время оборота двигателя">
                                </div>
                                <div class="mode-field">
                                    <input type="text" placeholder="Время работы режима">
                                </div>

                                <div class="mode-field">
                                    <input type="reset">
                                    <input type="submit">
                                </div>
                            </form>
                        </div>
                    </div><!-- .END modal window -->
                </div><!-- .END modes nav -->
            </div>

            <div class="grid-100">
                <c:choose>
                    <c:when test="${not empty mode}">
                        <c:forEach items="${mode}" var="mode" >
                            <div class="mode-container">
                                <div class="mode-header"><c:out value="${mode.modeName}" /></div>

                                <div class="grid-container">
                                    <div class="grid-20">
                                        <div class="temperature-mode">
                                            <div class="temperature-low">
                                                Мин. температура:
                                                <span><c:out value="${mode.lowLimTemperature}" />°C</span>
                                            </div>
                                            <div class="temperature-high">
                                                Макс. температура:
                                                <span><c:out value="${mode.upLimTemperature}" />°C</span>
                                            </div>
                                        </div><!-- .END temperature mode -->
                                    </div>
                                    <div class="grid-20">
                                        <div class="humidity-mode">
                                            <div class="humidity-low">
                                                Мин. влажности:
                                                <span><c:out value="${mode.lowLimHumidity}" />%</span>
                                            </div>
                                            <div class="humidity-high">
                                                Макс. влажности:
                                                <span><c:out value="${mode.upLimHumidity}" />%</span>
                                            </div>
                                        </div><!-- .END humidity mode -->
                                    </div>

                                    <div class="grid-20">
                                        <div class="days-mode">
                                            <div class="number-days">Количество дней: <c:out value="${mode.workTime}" /></div>
                                            <div class="remaining-days">Осталось дней: <c:out value="${mode.workTime}" /></div>
                                        </div><!-- .END days mode -->
                                    </div>

                                    <div class="grid-20">
                                        <div class="turnaround-time-engine">Время оборота двигателя: <c:out value="${mode.engineSpeed}" /></div><!-- .END turnaround time engine mode -->
                                    </div>

                                    <div class="grid-20">
                                        <div class="mode-nav">
                                            <c:choose>
                                                <c:when test="${mode.activationStatus == false}">
                                                    <div class="deactived-mode">Неактивный</div>
                                                    <div class="mn-mode">
                                                        <a class="blue" href="<c:url value="/mode/delete/${mode.id}" />" title="Удалить"><i class="ion-trash-b"></i></a>
                                                        <a class="green" href="<c:url value="/mode/set/active/${mode.id}" />" title="Включить"><i class="ion-checkmark-round"></i></a>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="actived-mode">Активный</div>
                                                    <div class="mn-mode">
                                                        <a class="blue" href="<c:url value="/mode/delete/${mode.id}" />" title="Удалить"><i class="ion-trash-b"></i></a>
                                                        <a class="red" href="<c:url value="/mode/set/inactive/${mode.id}" />" title="Выключить"><i class="ion-minus-round"></i></a>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </div><!-- .END mode nav -->
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div style="text-align: center;padding: 10px;font-size: 13px;">
                            <span class="ion-alert-circled" style="font-size: 25px;"></span><br> Нет режимов
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div><!-- .END modes container -->

    <!-- Include JS libraries -->
    <script src="<c:url value="/assets/js/vendor/jquery-1.11.2.min.js"/>"></script>
</body>
</html>
