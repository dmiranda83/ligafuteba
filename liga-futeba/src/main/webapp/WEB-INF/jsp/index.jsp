<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="commons/header.jsp"></jsp:include>

<div class="container">

	<h3>List of Cruds</h3>
	<br>
	<table class="table table-hover">

		<tbody>
			<tr>
				<td><a href="/teamCategories">
						<button type="submit" class="btn btn-primary">Register Game Categories</button>
				</a></td>
			</tr>
			<tr>
				<td><a href="/places">
						<button type="submit" class="btn btn-primary">Register Places</button>
				</a></td>
			</tr>
			<tr>
				<td><a href="/playerPositions">
						<button type="submit" class="btn btn-primary">Register Player Positions</button>
				</a></td>
			</tr>
		</tbody>
	</table>
</div>

<jsp:include page="commons/footer.jsp"></jsp:include>