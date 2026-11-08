<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Hello World!</h1>

<a href="hello-servlet">Hello Servlet</a>
<br>
<br>
<a href="CounterServlet?inc=1">CounterServlet GET</a>
<br>
<a href="CounterServlet?dec=1">CounterServlet GET dec</a>
<form action="CounterServlet" method="POST">
    <br>inc: <input type="text" name="inc" value="1">
    <br>dec: <input type="text" name="dec" value="1">
    <br><input type="submit" value="CounterServlet POST">
</form>
</body>
</html>