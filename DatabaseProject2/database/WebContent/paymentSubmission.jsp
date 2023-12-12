<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Submission</title>
</head>
<body>
    <div align="center">
        <form action="billPayment" method="post">
            <input type="hidden" name="email" value="${param.email}">
            <input type="hidden" name="billid" value="${param.billid}">
            <%-- <input type="hidden" name="payment" value="${param.payment}"> --%>
            
            <table border="1" cellpadding="6">
                <caption><h2>Pay Here</h2></caption>
                <tr>
                    <th>Payment amount: </th>
                    <td align="center" colspan="3">${param.bill}</td>
                </tr>
                <tr>
                    <td align="center" colspan="6">
                        <input type="submit" value="Pay Bill"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
