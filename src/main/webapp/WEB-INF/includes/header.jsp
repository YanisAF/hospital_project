<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="container">
        <h1>Bienvenue à l'Hôpital Princeton-Plainsboro</h1>
    </div>
</header>
<nav>
    <div class="container">
        <ul>
            <li><a href="<%= request.getContextPath() %>/">Accueil</a></li>
            <li><a href="<%= request.getContextPath() %>/patient">Liste des Patients</a></li>
        </ul>
    </div>
</nav>

