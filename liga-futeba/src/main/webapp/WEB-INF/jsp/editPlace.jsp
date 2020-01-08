<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<<jsp:include page="commons/header.jsp"></jsp:include>

<div class="container">

	<h3>Game Place Registration</h3>
	<form action='/places/update' method='post'>

		<table class='table table-hover table-responsive table-bordered'>

			<tr>
				<td><b>Name</b></td>
				<td><input type='text' name='name' class='form-control'
					value="${place.name}" /></td>
			</tr>
			<tr>
				<td><b>Type</b></td>
				<td><input type='text' name='type' class='form-control' value="${place.type}" /></td>
			</tr>
			<tr>
				<td><b>Address</b></td>
				<td><input type='text' name='address' class='form-control' value="${place.address}" /></td>
			</tr>
			<tr>
				<td><b>City</b></td>
				<td><input type='text' name='city' class='form-control' value="${place.city}" /></td>
			</tr>
			<tr>
				<td><b>Neighborhood</b></td>
				<td><input type='text' name='neighborhood' class='form-control' value="${place.neighborhood}" /></td>
			</tr>
			<tr>
				<td><b>Zip Code</b></td>
				<td><input type='text' name='zipCode' class='form-control' value="${place.zipCode}" /></td>
			</tr>

			<input type='hidden' id='id' rows='5' class='form-control' name='id'
				value="${place.id}" />
			<tr>
				<td></td>
				<td>
					<button type="submit" class="btn btn-primary">Update Game
						Place Information</button>
				</td>
			</tr>

		</table>
	</form>


</div>

<jsp:include page="commons/footer.jsp"></jsp:include>