<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOC TYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Messages - Name Input</title>
    <link href="<c:url value="/css/home.css" />" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1>Добро пожаловать</h1>
    <form id="nameForm" action="/saveSenderName" method="post">
        <label for="senderName">Введите ваше имя:</label>
        <input type="text" id="senderName" name="senderName" required>
        <button type="submit" class="submit-button">Отправить</button>
    </form>
</div>
<script src="<c:url value="/js/home.js" />"></script>
</body>
</html>