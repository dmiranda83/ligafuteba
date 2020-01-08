<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="commons/header.jsp"></jsp:include>

<div class="container">

	<h3>Category Registration</h3>
	<br>
	<form action='/teamCategories' method='post'>

		<table class='table table-hover table-responsive table-bordered'>

			<tr>
				<td><b>Name</b></td>
				<td><input type='text' name='name' class='form-control'
					required /></td>
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


	<h3>List of Categories</h3>
	<br>
	<table class="table table-hover">

		<thead>
			<tr>
				<th><b>Category Name</b></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="category">
				<tr>
					<td><c:out value="${category.name}"></c:out></td>

					<td><a href="/teamCategories/${category.id}/edit">
							<button type="submit" class="btn btn-primary">Edit</button>
					</a> <a href="/teamCategories/${category.id}/delete">
							<button type="submit" class="btn btn-primary">Delete</button>
					</a></td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="commons/footer.jsp"></jsp:include>