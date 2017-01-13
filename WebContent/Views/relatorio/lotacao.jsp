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

	<div class="row">
		<div class="">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-th-list"></i> Lotação Poltronas
					</h2>
				</div>

				<div class="box-content">
					<table
						class="table table-bordered table-striped table-condensed">
						<thead>
							<tr>
								<th>Nº Sessão</th>
								<th>Filme</th>
								<th>Data Sessão</th>
								<th>Horário Sessão</th>
								<th>Sala</th>
								<th>Poltronas Ocupadas</th>
								<th>Poltronas Livres</th>
								<th>Qtd Poltronas</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="venda" items="${listLotacao}">

								<tr>
									<td>${venda.sessao.id}</td>
									<td>${venda.sessao.filme.titulo}</td>
									<td>${venda.sessao.data}</td>
									<td>${venda.sessao.horario}</td>
									<td>${venda.sessao.sala.numero}</td>
									<td>${venda.qtdeIngressoTotal}</td>
									<td>${venda.qtdeIngressoInteira}</td>
									<td>${venda.sessao.sala.quantidadePoltrona}</td>
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