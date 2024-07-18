<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.tp_hopital.entity.Patient" %>
<%
    String messageError = (String) request.getAttribute("messageError");
    Boolean isLogged = (Boolean) request.getAttribute("isLogged");
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    Boolean searchMode = (Boolean) request.getAttribute("searchMode");
%>
<html>
<head>
    <title>Patients</title>
    <jsp:include page="../includes/head.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../includes/header.jsp"></jsp:include>
<div class="container">
    <% if (messageError != null) { %>
    <div class="error-message"><%= messageError %></div>
    <% } %>

    <form method="post">
        <input type="hidden" name="action" value="search">
        <div class="form-group">
            <h2>Rechercher un patient : </h2>
            <input type="text" name="search" class="form-control">
        </div>
        <div class="form-group">
            <button type="submit" class="btn">Valider</button>
        </div>
    </form>
    <hr>
    <% if (isLogged != null && isLogged) { %>
    <h2>Ajouter un patient</h2>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label>Nom : </label>
            <input type="text" name="name" class="form-control">
        </div>
        <div class="form-group">
            <label>Téléphone : </label>
            <input type="text" name="phone" class="form-control">
        </div>
        <div class="form-group">
            <label>Date de Naissance : </label>
            <input type="date" name="dateOfBirth" class="form-control">
        </div>
        <div class="form-group">
            <label>Photo: </label>
            <input type="file" name="photo" class="form-control">
        </div>
        <div class="form-group">
            <button type="submit" class="btn">Valider</button>
        </div>
    </form>
    <% } else {%>
    <h2>Ajouter un patient</h2>
    <div class="form-group">
        <a href="login" class="btn">Se connecter</a>
    </div>
    <% }%>
</div>
<hr>
<div class="container patients-container">
    <% if (!searchMode) { %>
    <h2 class="row">Liste des patients :</h2>
    <% } else { %>
    <h2 class="row">Résultat de la recherche :</h2>
    <% } %>
    <% if (patients != null) { %>
    <% for (Patient patient : patients) { %>
    <div class="patient">
        <p><strong>Nom :</strong> <%= patient.getName() %></p>
        <p><strong>Téléphone :</strong> <%= patient.getPhone() %></p>
        <p><a href="?id=<%= patient.getId() %>" class="btn">Détail</a></p>
    </div>
    <% } %>
    <% } %>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
