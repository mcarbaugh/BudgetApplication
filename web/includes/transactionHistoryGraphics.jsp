<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="transactionGraphicsSection">
    <table>
        <thead>
            <tr id="transactionHeader">
                <td colSpan="5" class="headerCell">Transaction Graphics</td>
            </tr>
             
        </thead>
        <tbody id="transactionTableBody">
           
            
            <c:forEach items="${weekTransactions}" var="transaction" varStatus="counter">
                
            </c:forEach>
        </tbody>
    </table>
</div>

<div id="LineChartContainer">
    <canvas id="myChart" width="400" height="250"></canvas>
</div>

<input type="button" value="Week" id="weekButton">
<input type="button" value="Month" id="monthButton">
<script>
    /*
    (function() {
        var canvas = document.getElementById("myChart");
        var data = {
            labels:["1yue", "2yue"], 
            datasets: [{ 
                    label: "week transactions",
                    fill: false,
                    lineTension: 0.1,
                    backgroundColor: "rgba(75,192,192,0.4)",
                    boderColor:"rgba(75,192,192,1)",
                    borderCapStyle: 'butt',
                    borderDash: [],
                    borderDashOffset: 0.0,
                    borderJoinStyle: 'miter',
                    data: [65, 59]}]
        };
        
        document.getElementById("testButton").addEventListener("click", addData);

        function addData(){
            myLineChart.data.datasets[0].data[2] = 90;
            myLineChart.data.labels[2] = "Newly Added";
            myLineChart.update();
        }

        var option = {
            showLines: true
        };

        var myLineChart = new Chart(canvas,{
            type: "line",
            data:data,
            options:option
        });
    })();*/
</script>