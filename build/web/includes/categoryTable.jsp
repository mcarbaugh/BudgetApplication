
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="categoryTableContainer">
    <div id="TransactionsButtonContainer">
        <c:url value="/TransactionHistory" var="transactionURL"/>
        <form method="GET" Action="${transactionURL}">
            <input type="text" value="${user.getId()}" name="userId" class="hiddenField"/>
            <input type="submit" value="Show Transaction History" id="TransactionsButton"/>
        </form>
    </div>
            
    <jsp:include page="/includes/categories/foodSection.jsp"/>
    <jsp:include page="/includes/categories/transportationSection.jsp"/>
    <jsp:include page="/includes/categories/lifestyleSection.jsp"/>
    <jsp:include page="/includes/categories/housingSection.jsp"/>
    <jsp:include page="/includes/categories/insuranceSection.jsp"/>
    <jsp:include page="/includes/categories/givingSection.jsp"/>
</div>