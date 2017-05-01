<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="transactionDetailsSection">
    <table>
        <thead>
            <tr id="transactionHeader">
                <td colSpan="6" class="headerCell">
                    <span>Transactions</span>
                    <div id="TransactionFilterOptions">
                        <select id="SortField">
                            <option value="default" selected>Sort By:</option>
                            <option value="category">Category</option>
                            <option value="amount">Amount</option>
                            <option value="date">Date</option>
                        </select>
                        <select id="SortDirection">
                            <option value="ascending">Ascending</option>
                            <option value="descending">Descending</option>
                        </select>
                        <input type="submit" value="Apply" id="ApplySortButton"/>
                    </div>
                </td>
            </tr>
            <tr class="headerRow">
                <th class="leftAlignColumn">Vendor</th>
                <th class="leftAlignColumn">Item</th>
                <th class="leftAlignColumn">Category</th>
                <th class="rightAlignColumn">Amount</th>
                <th>Date</th>
                <th>Action</th>
            </tr>  
        </thead>
        <tbody id="transactionTableBody"></tbody>
    </table>
</div>