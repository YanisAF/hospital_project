
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.tp_hopital.entity.Patient" %>
<%@ page import="com.example.tp_hopital.entity.Consultation" %>
<%
    Patient patient = (Patient) request.getAttribute("patient");
    List<Consultation> consultations = patient.getConsultations();
    String url = request.getAttribute("fullURL").toString();
%>
<html>
<head>
    <title>Patient <%= patient.getName() %></title>
    <jsp:include page="../includes/head.jsp"></jsp:include>
</head>
<body class="patient-body">
<jsp:include page="../includes/header.jsp"></jsp:include>
<h2>Infos du patient : </h2>
<% if (patient.getUrlPhoto() != null) {%>
<div class="image-container">
    <img src="<%= url %>/<%= patient.getUrlPhoto() %>" alt="<%= patient.getUrlPhoto() %>">
</div>
<% } %>
<div class="patient-info">
    Nom : <%= patient.getName() %>
</div>
<div class="patient-info">
    Date de naissance : <%= patient.getBirthDate() %>
</div>
<div class="patient-info">
    Téléphone : <%= patient.getPhone() %>
</div>
<div class="patient-add-consultation">
    <h2>Ajouter une consultation</h2>
    <form action="consultation" method="post">
        <input type="hidden" name="patientId" value="<%= patient.getId() %>">
        <div>
            <button type="submit">Valider</button>
        </div>
    </form>
</div>
<div class="patient-consultations">
    <% if (!consultations.isEmpty()) {%>
    <h2>Liste des consultations</h2>
    <% for (Consultation consultation : consultations) { %>
    <div>
        réf : <%= consultation.getId() %>
        date : <%= consultation.getDateConsultation() %>
        <a href="<%= request.getContextPath() %>/consultation?id=<%= consultation.getId() %>">Détail de la consultation</a>
    </div>
    <% } %>
    <% } else {%>
    <h2>Aucune consultations pour ce patient</h2>
    <% }%>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
