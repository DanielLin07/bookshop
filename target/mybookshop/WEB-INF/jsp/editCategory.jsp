<%@ taglib prefix="fmt"
           uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="UTF-8"%>
<html>
<body>
    <form method="post" action="../category/${c.id}">
        <input type="hidden" value="${c.id}" name="id">
        <input type="text" value="${c.name}" name="name">
        <input type="submit" value="修改">
    </form>
</body>
</html>
