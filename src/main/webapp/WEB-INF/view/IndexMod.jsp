<%-- 
    Document   : indexMod
    Created on : 17/05/2018, 21:25:56
    Author     : White
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.messages"/--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <title>${rb.getString("site.titulo")}</title>
    </head>
    <body>
        ${rb.getString("index.titulo.saudacao")}
        <c:if test="${sessionScope.loggedUser.userName != null}" >
            <c:out value="${sessionScope.loggedUser.userName}"/>
        </c:if>
        <c:if test="${sessionScope.loggedUser.userName == null}" >
            ${rb.getString("index.titulo.visitante")}
        </c:if> 


        <h1>${rb.getString("index.titulo.principal")}</h1>
        <fieldset>
            <legend>${rb.getString("index.titulo.legenda")}</legend>
            <form action="Busca" method="get">
                ${rb.getString("index.campo.nome")} <input type="text" id="txtBusca" name="busca" value=""/>
                <input type="submit" id="btnBusca"value="${rb.getString("index.botao.buscar")}"/>
            </form>
        </fieldset>

        <br>
        <form action="InsertServlet" method="get">
            <input type="submit" name="inserir" value="${rb.getString("index.botao.novoEvento")}">
        </form>

        <br>
        <table>
            <tr> 
                <th> ${rb.getString("index.campo.evento")}</th>
                <th> ${rb.getString("index.campo.data")}</th>
                <th> ${rb.getString("index.campo.usuario")}</th>
            </tr>
            <c:forEach items="${listaEventos}" var="evento">
                <tr>
                    <td> ${evento.nome}</td>
                    <td> ${evento.data}</td>
                    <td> ${evento.userId}</td>
                </tr>            
            </c:forEach>
        </table>
    </body>

    <script>
        $(document).ready(function () {
            $("#btnBusca").click(function () {
                var nomeBusca = '{}';
                $.ajax({
                    type: "GET",
                    url: "/BuscaRest",
                    //url: "rest/Busca/porNome/"+document.getElementsByTagName("#txtBusca").value,
                    data: nomeBusca,
                    dataType: "json",
                    success: function (msg, status) {
                        alert(msg);
                    },
                    error: function (xhr, msg, e) {
                        alert(msg+" E: "+e);
                    }
                });
            });
        });
    </script>
</html>
