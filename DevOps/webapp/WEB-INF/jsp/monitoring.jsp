<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Monitoring Data</title>
</head>
<body>
<h1>Monitoring Data</h1>
Welcome to the DevOps monitoring system<br/>
<a href="monitoring.html">Click here to refresh.</a>
<h2><c:out value="${model.title}"/></h2>
<table border="1">
<tr><th>Time</th><th>Severity</th><th>Message</th></tr>
<c:forEach var="docker" items="${model.dockerData}">
   <tr><td><c:out value="${docker.eventTime}"/></td><td><c:out value="${docker.severity}"/></td><td><c:out value="${docker.message}"/></td></tr>
</c:forEach>
</table>
</body>
</html>