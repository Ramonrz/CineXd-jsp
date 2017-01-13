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

	<a class="btn btn-success" href="/CineXd/VendaController?action=primeiraEtapa">
		<i class="glyphicon glyphicon-plus"></i> Cadastrar Venda
	</a>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-shopping-cart"></i> Venda
					</h2>
				</div>

				<div class="box-content">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<thead>
							<tr>
								<th>Filme</th>
								<th>Data</th>
								<th>Sessão</th>
								<th>Sala</th>
								<th>Atendente</th>
								<th>Valor total da venda</th>
							
							</tr>
						</thead>
						<tbody>
							<c:forEach var="venda" items="${venda}">

								<tr>
									<td>${venda.getSessao().getFilme().getTitulo()}</td>
									<td>${venda.getData()}</td>
									<td>${venda.getSessao().getHorario()}</td>
									<td>${venda.getSessao().getSala().getNumero()}</td>
									<td>${venda.getUsuario().getNome()}</td>
									<td>${venda.getValor()} </td>
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