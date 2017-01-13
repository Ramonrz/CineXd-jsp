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

	<a class="btn btn-success" href="/CineXd/SessaoController?action=add">
		<i class="glyphicon glyphicon-plus"></i> Cadastrar Sessão
	</a>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-time"></i> Sessões
					</h2>
				</div>

				<div class="box-content">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<thead>
							<tr>
								<th>Nº Sessão</th>
								<th>Horário</th>
								<th>Data</th>
								<th>Filme </th>
								<th>Observação</th>
								<th>Sala</th>
								
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sessao" items="${sessao}">

								<tr>
									<td>${sessao.getId()}</td>
									<td>${sessao.getHorario()}</td>
									<td>${sessao.getData()}</td>
									<td>${sessao.getFilme().getTitulo()}</td>
									<td>${sessao.getObservacao()}</td>
									<td>${sessao.getSala().getNumero()}</td>
									
									<td class="center">
											
											<a class="btn btn-info"
										href="/CineXd/SessaoController?action=editar&id=${sessao.id}">
											<i class="glyphicon glyphicon-edit icon-white"></i> Editar

									</a> <a class="btn btn-danger"
										href="/CineXd/SessaoController?action=desativar&id=${sessao.id}" onclick="return confirm('Deseja Realmente Excluir ?')">
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