<%-- 
    Document   : itemTable
    Created on : Apr 4, 2017, 10:12:29 AM
    Author     : Eclat
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="itemTableContainer">
    <table>
        <c:if test="${fn:length(items) gt 0}">
            <thead>
                <tr>
                    <th>Description</th>
                    <th>Amount</th>
                    <th>Category</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item" varStatus="counter">
                    <tr>
                        <td><c:out value="${item.description}"/></td>
                        <td><c:out value="${item.amount}"/></td>
                        <td><c:out value="${item.category.toString()}"/></td>
                        <td><input type="button" value="Modify" class="updateItemButton"/></td>
                        <td><input type="button" value="X" class="deleteBudgetButton"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
</div>
