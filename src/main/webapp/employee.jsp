<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Page</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h3>Welcome Employee java</h3>

<button id="create" class="btn btn-success">Create Reimbursement</button>
<a href ='logout'  style="float:right">Click here to logout!</a>
<form class="form-control">
    <label for="Amount">Enter Amount:</label>
    <input type="text" id="amount" >
    <label for="Description">Enter Description:</label>
    <input type="text" id="shortDescription" >
    <label for="Type">Select From Drop Menu:</label>
    <select id="selection">
        <option value="1">LODGING</option>
        <option value="2">TRAVEL</option>
        <option value="3">FOOD</option>
        <option selected value="4">OTHER</option>
      </select>
</form>
<button id="viewReim" class="btn btn-primary">View All Reimbursement</button>
<table class="table">
    <thead>
      <tr>
        <th class="col-sm-1">Amount</th>
        <th class="col-sm-1">Submitted</th>
        <th class="col-sm-1">Resolved</th>
        <th class="col-sm-2">Description</th>
        <th class="col-sm-1">Resolver</th>
        <th class="col-sm-1">Status</th>
        <th class="col-sm-1">Type</th>
      </tr>
    </thead>
    <tbody id="reimbursements">
    </tbody>
</body>
</html>