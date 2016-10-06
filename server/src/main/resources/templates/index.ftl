<!DOCTYPE html>

<html lang="en">

<body>
	<table>
		<thead>
			<tr>
				<td>Title</td>
				<td>Author</td>
				<td>Description</td>
				<td>Price</td>
			</tr>
		</thead>
		<tbody>
			<#list books as book>
						<tr>
							<td>${book.title}</td>
							<td>${book.author}</td>
							<td>${book.description}</td>
							<td>${book.price}</td>
						</tr>
			</#list>
		</tbody>
	</table>
</body>

</html>