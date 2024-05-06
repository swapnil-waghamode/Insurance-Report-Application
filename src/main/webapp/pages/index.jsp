<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Report Project</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">

		<h1 class="pb-3 pt-3">Report Application</h1>

		<form:form action="search" modelAttribute="search" method="POST">

			<table>

				<tr>

					<td>Plan Name :</td>
					<td><form:select path="planName">
							<form:option value="">-select-</form:option>
							<form:options items="${names}" />
						</form:select></td>

					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">-select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select></td>
				</tr>

				<tr class="pt-2">

					<td>Start Date :</td>
					<td><form:input path="startDate" type="date" /></td>

					<td>End Date :</td>
					<td><form:input path="endDate" type="date" /></td>

				</tr>

				<tr>
					<td><input type="submit" value="Search"
						class="btn btn-primary"></td>

				</tr>

			</table>

		</form:form>

		<hr>

		<table class="table table-striped table-hover ">
			<thead>
				<tr>
					<th>S.No</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>
				</tr>

			</thead>
			
			<tbody>
			 <c:forEach items="${plans}" var="plan" varStatus="index" >
			 
			     <tr>
			        <td>${index.count}</td>
			        <td>${plan.citizenName}</td>
			        <td>${plan.gender}</td>
			        <td>${plan.planName}</td>
			        <td>${plan.planStatus}</td>
			        <td>${plan.planStartDate}</td>
			        <td>${plan.planEndDate}</td>
			        <td>${plan.benefitAmt}</td>
			     
			     </tr>
			 </c:forEach>
			
			
			
			</tbody>








		</table>

		<hr>

		Export : <a href="" class="btn btn-success">Excel</a> <a href=""
			class="btn btn-danger">Pdf</a>


	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>