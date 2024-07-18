
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String messageError = (String) request.getAttribute("messageError");
%>
<html>
<head>
    <title>Se connecter</title>
    <jsp:include page="../includes/head.jsp"></jsp:include>
</head>
<body class="login-body">
<jsp:include page="../includes/header.jsp"></jsp:include>
<div class="login-title">
    <h1>Se connecter</h1>
</div>
<div class="login-form">
    <% if (messageError != null) { %>
    <div class="login-error"><%= messageError %></div>
    <% } %>
    <form action="<%= request.getContextPath() %>/login" method="post">
        <input type="hidden" name="action" value="login">
        <div>
            <label>Login</label>
            <input type="text" name="login">
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password">
        </div>
        <div>
            <button type="submit">Valider</button>
        </div>
    </form>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
