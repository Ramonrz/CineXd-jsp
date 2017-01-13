<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cine Xd</title>
</head>
<body>

	<jsp:include page="/Views/content/content.jsp"></jsp:include>

	<a class="btn btn-success" href="/CineXd/Views/filme/cadastrar.jsp">
		<i class="glyphicon glyphicon-plus"></i> Cadastrar Filme
	</a>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-film"></i> Filmes
					</h2>
				</div>

				<div class="box-content">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<thead>
							<tr>
								<th>Título</th>
								<th>Gênero</th>
								<th>Idioma</th>
								<th>Duração</th>
								<th>Faixa Etária</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="filme" items="${filmes}">

								<tr>
									<td>${filme.titulo}</td>
									<td>${filme.genero}</td>
									<td>${filme.idioma}</td>
									<td>${filme.duracao} min</td>
									<td>${filme.faixaEtaria} anos</td>
									<td class="center">
											
											<a class="btn btn-info"
										href="/CineXd/FilmeController?action=editar&id=${filme.id}">
											<i class="glyphicon glyphicon-edit icon-white"></i> Editar

									</a> <a class="btn btn-danger"
										href="/CineXd/FilmeController?action=desativar&id=${filme.id}" onclick="return confirm('Deseja Realmente Excluir ?')">
											<i class="glyphicon glyphicon-trash icon-white"></i> Deletar
									</a></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->

	<jsp:include page="/Views/footer/footer.jsp"></jsp:include>

</body>
</html>