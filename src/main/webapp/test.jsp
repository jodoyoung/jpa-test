<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>JPA Test</title>
</head>
<body>
	<h1>JPA Test Page</h1>
	
	<br/>--------------------------------------------------<br/>
	
	<c:forEach var="api" items="${apiList}">
	
	<h3>${api.url} [${api.httpMethod}]</h3>
	
	<form action="${api.url}" method="${api.httpMethod}">
		<c:forEach var="param" items="${api.paramList}">
		<input type="text" name=${param}/>
		</c:forEach>
		<input type="submit">
	</form>
	
	<br/>--------------------------------------------------<br/>
	
	</c:forEach>
</body>
</html>