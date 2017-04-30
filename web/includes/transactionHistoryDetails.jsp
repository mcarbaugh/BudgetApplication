<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="transactionDetailsSection">
    <table>
        <thead>
            <tr id="transactionHeader">
                <td colSpan="7" class="headerCell">Transaction Details</td>
            </tr>
            <tr class="headerRow">
                <th></th>
                <th class="leftAlignColumn">Vendor</th>
                <th class="leftAlignColumn">Item</th>
                <th class="leftAlignColumn">Category</th>
                <th class="rightAlignColumn">Amount</th>
                <th>Date</th>
                <th>Action</th>
            </tr>  
        </thead>
        <tbody id="transactionTableBody">

        </tbody>
    </table>
</div>