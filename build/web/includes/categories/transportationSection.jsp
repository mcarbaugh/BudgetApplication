
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="categorySection">
    <table>
        <thead>
            <tr id="transportationHeader">
                <td colSpan="6" class="headerCell">Transportation</td>
            </tr>
            <tr class="headerRow">
                <th>&nbsp;</th>
                <th class="leftAlignColumn">Item</th>
                <th class="rightAlignColumn">Planned</th>
                <th class="rightAlignColumn">Spent</th>
                <th class="rightAlignColumn">Remaining</th>
                <th>Action</th>
            </tr>  
        </thead>
        <tbody id="transportationCategoryTableBody">
            <c:forEach items="${transportationItems}" var="item" varStatus="counter">
                <c:url value="transportation${item.id}" var="rowId"/>
                <tr id="${rowId}">
                    <td>                        
                        <input type="button" value="+" class="addButton"/>
                    </td>
                    <td class="leftAlignColumn"><c:out value="${item.name}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" maxIntegerDigits="6" value="${item.amount}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" maxIntegerDigits="6" value="${item.spent}"/></td>
                    <td class="rightAlignColumn"><fmt:formatNumber type="currency" maxIntegerDigits="6" value="${item.getRemaining()}"/></td>
                    <td>
                        <input type="button" value="Edit" class="editButton" 
                               onclick="openDialogWithCurrentValues(
                                           '${item.id}', 
                                           '${item.name}', 
                                           '${item.amount}', 
                                           '${item.spent}',
                                           '${item.category.name()}', '${rowId}')"/>
                        <input type="button" onclick="deleteItemCallback('${item.getId()}', this)" value="Delete" class="deleteButton"/>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button onclick="openNewTransportationItemDialog()" class="AddItemButton">Add Item</button>
</div>