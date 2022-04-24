
<%@page import="com.emergentes.modelo.Libro"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  Libro lb= (Libro) request.getAttribute("lib");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <title>Editar</title>
    </head>
    <body id="edit">
        <div id="editar">
        <h1>
            <c:if test="${requestScope.op=='grabar'}" var="var1" scope="request">
            Registro de  
            </c:if>
             <c:if test="${requestScope.op=='grabar'}" var="var1" scope="request">
           Modificar           
            </c:if>
           Libro
            </h1>
            <form action="MainController" method="post">
                <table id="table-edit">
                    <input type="hidden" name="id" value="${lib.id}">
                    <tr>
                        <td>Isbn</td>
                        <td><input type="text" name="isbn" value="${lib.isbn}"></td>
                    </tr>
                    <tr>
                        <td>Titulo</td>
                        <td><input type="text" name="titulo" value="${lib.titulo}"></td>
                    </tr>
                     <tr>
                        <td>Categoria</td>
                        <td><input type="text" name="categoria" value="${lib.categoria}"></td>
                    </tr>
                     <tr>
                         <td> <input type="hidden" name="op" value="${requestScope.op}"></td>
                        <td><input type="submit" value="Enviar"></td>
                    </tr>
                </table> 
                
            </form>
        <p><a href="MainController">Volver</a></p>
       </div> 
    </body>
</html>
