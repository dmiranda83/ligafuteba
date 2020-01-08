<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="commons/header.jsp"></jsp:include>

<div class="container">

	<h3>Place Registration</h3>
	<br>
	<form action='/places' method='post'>

		<table class='table table-hover table-responsive table-bordered'>

			<tr>
				<td><b>Name</b></td>
				<td><input type='text' name='name' class='form-control'
					required /></td>
			</tr>
			<tr>
				<td><b>Type</b></td>
				<td><input type='text' name='type' class='form-control' /></td>
			</tr>
			<tr>
				<td><b>Address</b></td>
				<td><input type='text' name='address' class='form-control' /></td>
			</tr>
			<tr>
				<td><b>City</b></td>
				<td><input type='text' name='city' class='form-control' /></td>
			</tr>
			<tr>
				<td><b>Neighborhood</b></td>
				<td><input type='text' name='neighborhood' class='form-control' /></td>
			</tr>
			<tr>
				<td><b>Zip Code</b></td>
				<td><input type='text' name='zipCode' class='form-control' /></td>
			</tr>

			<tr>
				<td></td>
				<td>
					<button type="submit" class="btn btn-primary">Register</button>
				</td>
			</tr>

		</table>
		<b><c:out value="${danger}"></c:out></b>
	</form>

	<a href="/">
		<button type="submit" class="btn btn-primary">Back Home</button>
	</a>


	<h3>List of Game Places</h3>
	<br>
	<table class="table table-hover">

		<thead>
			<tr>
				<th><b>Place Name</b></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="place">
				<tr>
					<td><c:out value="${place.name}"></c:out></td>

					<td><a href="/places/${place.id}/edit">
							<button type="submit" class="btn btn-primary">Edit</button>
					</a> <a href="/places/${place.id}/delete">
							<button type="submit" class="btn btn-primary">Delete</button>
					</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="commons/footer.jsp"></jsp:include>