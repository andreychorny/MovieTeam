<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="UTF-8">
    <title>MovieTeam</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1> Hello there </h1>

            <c:if test="${!empty(movies)}">
    		<section id="movies" class="section">
            <c:forEach var="movie" items="${movies}">
    		<div class="movieContainer">
            			<div class="movieContainerItem">
            			     <img id="pic1" src="images/thereWillBeBlood.jpg">
            				<input type="text" name="movie"
            					value="${movie.title}"><br />
            				<input type="text" name="movie"
            					value="${movie.genres}"><br />
            			</div>
            </c:forEach>
            		</div>

            </section>
            </c:if>
</body>
</html>
