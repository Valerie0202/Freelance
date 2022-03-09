<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>Freelance App</title>
    <link rel="stylesheet" type="text/css" href="/pub/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="icon" href="/pub/images/logo.png">
</head>

<body>


<div class="nav">
    <a href="/index" class="logoNav"><img src="/pub/images/logo.png" alt="Logo"/></a>
    <a href="/index">HOME</a>
    <sec:authorize access="isAuthenticated()">
        <a href="/client/viewClients">CLIENTS</a>
        <a href="/invoice/viewInvoices">INVOICES</a>
        <a href="/login/logout">SIGN OUT</a>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <a href="/registration/register">SIGN UP</a>
        <a href="/login/login">SIGN IN</a>
    </sec:authorize>
</div>

<div class="container">