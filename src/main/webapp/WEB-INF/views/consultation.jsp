<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="com.example.tp_hopital.entity.Consultation" %>
<%@ page import="com.example.tp_hopital.entity.FicheSoins" %>
<%@ page import="com.example.tp_hopital.entity.Prescription" %>
<%
    Consultation consultation = (Consultation) request.getAttribute("consultation");
%>
<html>
<head>
    <title>Consultation <%= consultation.getId() %></title>
    <jsp:include page="../includes/head.jsp"></jsp:include>
</head>
<body class="consultation-body">
<jsp:include page="../includes/header.jsp"></jsp:include>
<div class="consultation-title">
    <h1>  Patient : <a href="patient?id=<%= consultation.getPatient().getId() %>">
        <%= consultation.getPatient().getName() %></a> , consultation ref :
        <%= consultation.getId() %> ,
        Date :
        <%=
    consultation.getDateConsultation() %></h1>
</div>
<div class="consultation-fiche">
    <h2>Fiche soins</h2>
    <% if (consultation.getFicheSoins() != null) { %>
    <div>
        <%= consultation.getFicheSoins().getContent() %>
    </div>
    <% } else { %>
    <h3>Soins à remplir :</h3>
    <form action="<%= request.getContextPath() %>/soins" method="post">
        <input type="hidden" name="consultationId" value="<%= consultation.getId() %>">
        <div>
            <label>Contenu</label>
            <textarea name="content"></textarea>
        </div>
        <div>
            <button type="submit">Valider</button>
        </div>
    </form>
    <% } %>
</div>

<div class="consultation-prescription">
    <h2>Préscription</h2>
    <% if (consultation.getPrescription() != null) { %>
    <div>
        <%= consultation.getPrescription().getContent() %>
    </div>
    <% } else { %>
    <h3>Prescription à remplir :</h3>
    <form action="<%= request.getContextPath() %>/prescription" method="post">
        <input type="hidden" name="consultationId" value="<%= consultation.getId() %>">
        <div>
            <label>Contenu</label>
            <textarea name="content"></textarea>
        </div>
        <div>
            <button type="submit">Valider</button>
        </div>
    </form>
    <% } %>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
</body>
</html>
