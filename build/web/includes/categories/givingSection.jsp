
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="categorySection">
    <table>
        <thead>
            <tr id="givingHeader">
                <td colSpan="5" class="headerCell">Giving</td>
            </tr>
            <tr class="headerRow">
                <th></th>
                <th class="leftAlignColumn">Item</th>
                <th class="rightAlignColumn">Planned</th>
                <th class="rightAlignColumn">Spent</th>
                <th class="rightAlignColumn">Remaining</th>
            </tr>  
        </thead>
        <tbody>
            <c:forEach items="${givingItems}" var="item" varStatus="counter">
                <tr>
                    <td><a href="#">+</a></td>
                    <td class="leftAlignColumn"><c:out value="${item.description}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" value="${item.amount}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" value="${item.spent}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" value="${item.getRemaining()}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="addItemButtonContainer">
        <button onclick="">Add Item</button>
    </div>
</div>