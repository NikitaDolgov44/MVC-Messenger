<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Messages</title>
    <link href="<c:url value="/css/messages.css" />" rel="stylesheet">
</head>
<body>
<div class="chat-container">
    <h1>Сообщения</h1>
    <div class="back-button-container">
        <a href="<c:url value="/" />" class="back-button">Назад</a>
    </div>
    <br>
    <div class="chat-window">
        <div class="chat-messages">
            <c:forEach items="${messages}" var="message">
                <div class="message">
                    <span class="sender"><c:out value="${message.senderName}" /></span>
                    <span class="timestamp"><c:out value="${fn:formatDate(message.sentAt, 'hh:mm a')}" /></span>
                    <p><c:out value="${message.text}" /></p>
                </div>
            </c:forEach>
        </div>
        <form id="messageForm" method="post" modelAttribute="newMessage" class="message-form">
            <input type="text" id="text" name="text" required placeholder="Type your message...">
            <input type="hidden" id="senderName" name="senderName" value="${senderName}" />
            <button type="submit">Отправить</button>
        </form>
        <form action="<c:url value="/messages" />" method="get" class="filter-form">
            <input type="text" id="filterName" name="filterName" placeholder="Filter by Sender Name...">
            <button type="submit">Фильтр</button>
        </form>
    </div>
</div>
<script src="<c:url value="/js/messages.js" />"></script>
</body>
</html>