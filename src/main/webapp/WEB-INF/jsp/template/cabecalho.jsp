<!doctype html>
<html class="fuelux" lang="en">
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<c:if test="${not empty param.language}">
	<fmt:setLocale value="${param.language}" scope="session" />
</c:if>

<!-- Mirrored from creativico.com/envato/simplicity/ by HTTrack Website Copier/3.x [XR&CO'2013], Tue, 15 Apr 2014 22:23:28 GMT -->
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Simplicity - Massive Admin Pack</title>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/assets/weather-icons/weather-icons.min.css">

<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/assets/effects/menu-effects.css">

<!-- Google Fonts -->
<link href='../../../fonts.googleapis.com/css5805.css?family=Lobster' rel='stylesheet' type='text/css'>
<link href='../../../fonts.googleapis.com/cssd912.css?family=Lato:400,100italic,100,300italic,300,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
  <script src="${ctx}/resources/tema-admin/assets/bootstrap/js/html5shiv.js"></script>
  <script src="${ctx}/resources/tema-admin/assets/bootstrap/js/respond.js"></script>
  <![endif]-->

<!-- Assets -->
<link rel='stylesheet' type='text/css' href='${ctx}/resources/tema-admin/assets/jquery-ui/ui-lightness/jquery-ui-1.10.3.custom.css' />
<link rel='stylesheet' type='text/css' href='${ctx}/resources/tema-admin/assets/morrischarts/morris.css' />
<link rel='stylesheet' type='text/css' href='${ctx}/resources/tema-admin/assets/fullcalendar/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='${ctx}/resources/tema-admin/assets/datatables/jquery.dataTables.css' />
<link rel='stylesheet' type='text/css' href='${ctx}/resources/tema-admin/assets/icheck/flat/_all.css' />
<link rel='stylesheet' type='text/css' href='${ctx}/_demo/introjs/introjs.min.css' />

<!-- Theme Styles -->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/styles-less.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/responsive.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/animate.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/_demo/demo.css">

<!-- Purchase Bar -->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/tema-admin/css/purchase-bar.css">

<!--[if IE 8]><script type="text/javascript" src="${ctx}/resources/tema-admin/assets/flotcharts/excanvas.min.js"></script><![endif]-->

<!-- Google Maps -->
<script src="../../../maps.googleapis.com/maps/api/js19f6?v=3.exp&amp;sensor=false"></script>

<link rel="shortcut icon" href="favicon.ico" type="image/png">
<link rel="shortcut icon" type="image/png" href="${ctx}/resources/tema-admin/favicon.ico" />

</head>

<body>
	<section class="content">