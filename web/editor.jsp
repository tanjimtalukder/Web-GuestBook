<%-- 
    Document   : editor
    Created on : Sep 22, 2016, 2:27:12 PM
    Author     : TanjimTalukder
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>EmailWeb Editor</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
        <h1>Registered Users</h1>
    <body>
    <c:forEach var="guest" items="${guestList}">
        <p>${guest.id} ${guest.firstName} ${guest.lastName} ${guest.emailAddress}</p>
    </c:forEach>
        <form method="POST" action="EmailEntryServlet">
            <h1>Please Register your email</h1>
            <table>
                <tr><td>ID: </td><td><input type="text" name="guestID" /></td></tr>
                <tr><td>First Name: </td><td><input type="text" name="firstName" /></td></tr>
                <tr><td>Last Name: </td><td><input type="text" name="lastName" /></td></tr>
                 <tr><td>Email Address: </td><td><input type="text" name="emailAddress" /></td></tr>
                <tr><td><input type="submit" value="Submit" name="action"/></td></tr>
            </table>
        </form>
        <form method="POST" action="EmailEntryServlet">
            <h1>Search a Record by Last Name</h1>
            <table>
                <tr><td>Last Name: </td><td><input type="text" name="lastName" /></td></tr>
                <tr><td><input type="submit" value="Search" name="action"/></td></tr>
            </table>
        </form>
        <form method="POST" action="EmailEntryServlet">
            <h1>Show All Records</h1>
            <table>
                <tr><td><input type="submit" value="Show All" name="action" /></td></tr>
            </table>
        </form>
        <form method="POST" action="EmailEntryServlet">
            <h1>Edit a Record</h1>
            <table>
                <tr><td>ID: </td><td><input type="text" name="guestID" /></td></tr>
                <tr><td>First Name: </td><td><input type="text" name="firstName" /></td></tr>
                <tr><td>Last Name: </td><td><input type="text" name="lastName" /></td></tr>
                <tr><td>Email Address </td><td><input type="text" name="emailAddress" /></td></tr>
                <tr><td><input type="submit" value="Edit" name="action"/></td></tr>
                <tr><td>Response from Server: ${message1}</td></tr>
            </table>
        </form>
        <form method="POST" action="EmailEntryServlet">
            <h1>Delete a Record by ID</h1>
            <table>
                <tr><td>ID: </td><td><input type="text" name="guestID" /></td></tr>
                <tr><td><input type="submit" value="Delete" name="action" /></td></tr>
                <tr><td>Response from Server: ${message2}</td></tr>
            </table>
        </form>
        <form method="POST" action="EmailEntryServlet">
            <h1>Show Number of Records in the Database</h1>
            <table>
                <tr><td><input type="submit" value="Total Records" name="action" /></td></tr>
                <tr><td>Total number of records is: ${message3}</td></tr>
            </table>
        </form>
    </body>
</html>