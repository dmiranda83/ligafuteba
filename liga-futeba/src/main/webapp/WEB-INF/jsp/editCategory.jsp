<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<<jsp:include page="commons/header.jsp"></jsp:include>

<div class="container">

<h3>Category Registration</h3>
<form action='/teamCategories/update' method='post'>
 
    <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
            <td><b>Name</b></td>
            <td><input type='text' name='name' class='form-control' value="${category.name}" /></td>
        </tr>
 
 			<input type='hidden' id='id' rows='5' class='form-control' name='id' value="${category.id}"/>
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary">Update Category Information</button>
            </td>
        </tr>
 
    </table>
</form>


</div>

<jsp:include page="commons/footer.jsp"></jsp:include>