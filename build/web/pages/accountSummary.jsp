
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/main.css">  
        <link rel="stylesheet" type="text/css" href="styles/menuBar.css">
        <link rel="stylesheet" type="text/css" href="styles/budgetTable.css">
        <link rel="stylesheet" type="text/css" href="styles/userAccountSummary.css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Budget Application | Account Summary</title>
    </head>
    <body>
        <script>
            function openAddBudgetDialog() {
                document.getElementById("NewBudgetModal").style.display = "block";
            }
            
            function closeAddBudgetDialog() {
                document.getElementById("NewBudgetModal").style.display = "none";
            }
        </script>
        <div class="wrapper">
            <jsp:include page="/includes/menuBar.jsp"/>
            <jsp:include page="/includes/accountSummary.jsp"/>
            <jsp:include page="/includes/budgetTable.jsp"/>
        </div>
        
        <div class="modal" id="NewBudgetModal">
            <div class="modal-content">
                <form id="NewBudgetForm" action="#" method="post">
                    <span class="close" onClick="closeAddBudgetDialog()">&times;</span>
                    <h1>Create Budget</h1>
                    <label>
                        <span>Month:</span>
                        <select name="monthDropdown" form="NewBudgetForm">
                            <c:forEach items="${months}" var="month" varStatus="counter">
                                <option value="month"><c:out value="${months[counter.index]}"/></option>
                            </c:forEach>
                        </select>
                    </label>
                    
                    <label>
                        <span>Year:</span>
                        <select name="yearDropdown" form="NewBudgetForm">
                            <option value="year">1980</option>
                            <option value="year">1981</option>
                        </select>
                    </label>
                    <input type="submit" value="Create"/>
                    <input type="button" value="Cancel"/>
                </form>
            </div>
        </div>
    </body>
</html>