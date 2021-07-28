<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manager Page</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <h3>Welcome Employee</h3>
    <a href ='logout'  style="float:right">Click here to logout!</a>
    <label for="cars">View by Status:</label>

    <select name="statuSelection" id="status">
      <option value="2">APPROVE</option>
      <option value="3">DENY</option>
      <option value="1">PENDING</option>
    </select>
    <br>
    <button id="viewStatus" class="btn btn-success">Submit</button>
<table class="table">
    <thead>
      <tr>
        <th class="col-sm-1">TicketID</th>
        <th class="col-sm-1">Amount</th>
        <th class="col-sm-1">Submitted</th>
        <th class="col-sm-1">Resolved</th>
        <th class="col-sm-2">Description</th>
        <th class="col-sm-1">Author</th>
        <th class="col-sm-1">Resolver</th>
        <th class="col-sm-1">Status</th>
        <th class="col-sm-1">Type</th>
      </tr>
    </thead>
    <tbody id="reimbursements">
    </tbody>
    <br>
    <br>
    <button id="statusUpdate" class="btn btn-primary">Update TicketID</button>
    <form class="form-control">
        <label for="TicketID">Enter TicketID:</label>
        <input type="text" id="TicketID" >
        <label for="Type">Select from Drop menu:</label>
        <select id="selection">
            <option value="2">APPROVE</option>
            <option value="3">DENY</option>
          </select>
    </form>
</body>
</html>