<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="/Views/content/content.jsp"></jsp:include>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-edit"></i> Cadastrar Venda
					</h2>

				</div>
				<div class="box-content">
					<form role="form" action="/CineXd/VendaController" method="post" id="formulario">

						<div class="form-group">
							<label>Filme :</label> ${filme.filme.titulo}
							<div class="form-group">
								<label>Sessão :</label> 
								
								<select class="form-control" name="sessao">
									<option value="" selected disabled>Selecione</option>
									<c:forEach var="sessao" items="${comboSessao}">
										<option value="${sessao.id}">Sala:
											${sessao.sala.numero} Horário ${sessao.horario} Data:
											${sessao.data}</option>
									</c:forEach>
								</select>
								
							</div>
						</div>
						<button type="submit" name="action" value="proximoSegunda"
							class="btn btn-default">Próximo</button>
					</form>

				</div>
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->


	<jsp:include page="/Views/footer/footer.jsp"></jsp:include>

</body>
	<script type="text/javascript" src="js/jquery.validate.js"></script>
	<script type="text/javascript" src="js/validate.js"></script>
</html>