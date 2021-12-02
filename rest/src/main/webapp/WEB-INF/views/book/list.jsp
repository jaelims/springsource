<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../includes/header.jsp"%>
<table class="table" style="margin-top: 20px">
	<thead class="thead-light">
		<tr>
			<th scope="col">code</th>
			<th scope="col">title</th>
			<th scope="col">writer</th>
			<th scope="col">price</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${list}">
			<tr>
				<th scope="row">${dto.code}</th>
				<td><a href="${dto.code}" class="move">${dto.title}</a></td>
				<td>${dto.writer}</td>
				<td>${dto.price}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div id='result'>

</div>
<script src="/resources/js/list.js"></script>
<%@include file="../includes/footer.jsp"%>