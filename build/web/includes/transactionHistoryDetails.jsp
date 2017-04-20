<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="transactionDetailsSection">
    <table>
        <thead>
            <tr id="transactionHeader">
                <td colSpan="5" class="headerCell">Transaction Details</td>
            </tr>
            <tr class="headerRow">
                <th></th>
                <th class="leftAlignColumn">Date</th>
                <th class="rightAlignColumn">Name</th>
                <th class="rightAlignColumn">Vendor</th>
                <th class="rightAlignColumn">Amount</th>
            </tr>  
        </thead>
        <tbody id="transactionTableBody">
            <c:forEach items="${transactions}" var="transaction" varStatus="counter">
                <tr>
                    <td class="leftAlignColumn"><c:out value="${transaction.date}"/></td>
                    <td class="rightAlignColumn"><c:out value="${transaction.name}"/></td>
                    <td class="rightAlignColumn"><c:out value="${transaction.vendor}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" value="${transaction.amount}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>