
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<div class="tableContainer">
    <div class="tableToolbar">
        <button class="tableToolbarButton">New</button>
        <div class="verticalRule"></div>
        <button class="tableToolbarButton">Sort by:</button>
    </div>
    <div class="scrollEnabledTable">
        <table>
        <c:if test="${fn:length(items) gt 0}">
            <thead>
                <tr>
                    <th>Budget Item</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item" varStatus="counter">
                    <tr>
                        <td><c:out value="${item.description}"/></td>
                        <td><fmt:formatNumber type="currency" value="${item.amount}"/></td>
                        <td><c:out value="${item.category.toString()}"/></td>
                        <td><a href="#" class="deleteButton">X</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </c:if>
    </table>
    </div>
</div>
