<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Libro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    List<Libro> lista = (List<Libro>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <title>Libros</title>
    </head>
    <body id="principal">
        <div id="prin">
            <h1>Lista de libros</h1>
            <p><a href="MainController?op=nuevo">Nuevo libro</a></p>
            <table id="table" border="1" >
                <tr>
                    <th>Id</th>
                    <th>Isbn</th>
                    <th>Titulo</th>
                    <th>Categoria</th>
                    <th>Editar</th>
                    <th>Eliminar</th>   
                </tr>  
                <c:forEach  var="item" items="${lista}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.isbn}</td>
                        <td>${item.titulo}</td>
                        <td>${item.categoria}</td>
                        <td><a href="MainController?op=editar&id=${item.id}">Editar</a>
                        <td><a href="MainController?op=eliminar&id=${item.id}"
                               onclick="return(confirm('Esta seguro?'))">Eliminar</a></td>                   
                    </tr>         
                </c:forEach>          
            </table>
        </div>   
    </body>
</html>

