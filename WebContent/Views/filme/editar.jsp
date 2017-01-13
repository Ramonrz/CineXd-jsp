<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cine Xd</title>
</head>
<body>

	<jsp:include page="/Views/content/content.jsp"></jsp:include>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-edit"></i> Editar Filme
					</h2>

				</div>
				<div class="box-content">
					<form role="form" action="/CineXd/FilmeController?action=editar&id=${filmes.id}" method="post" id="formulario">


						<div class="form-group">
							<label>Título :</label> <input type="text" class="form-control"
								name="titulo" value="${filmes.titulo}">
						</div>

						<div class="form-group">
							<label>Gênero :</label> <input type="text" class="form-control"
								name="genero" placeholder="Ex: Ação" value="${filmes.genero}">
						</div>

						<div class="form-group">
							<label>Idioma :</label> <select class="form-control" name="idioma">
								<option value="${filmes.idioma}">${filmes.idioma}</option>
								<option value="Português">Português</option>
								<option value="Inglês-DUB">Inglês - LEG</option>
								<option value="Inglês-LEG">Inglês - DUB</option>
							</select>
						</div>

						<div class="form-group">
							<label>Duração :</label> <input type="number" class="form-control"
								name="duracao" placeholder="Ex: 98" value="${filmes.duracao}">
						</div>

						<div class="form-group">
							<label>Faixa Etária :</label> <input type="number" class="form-control"
								name="faixaEtaria" value="${filmes.faixaEtaria}">
						</div>

						<button type="submit" class="btn btn-default">Salvar</button>
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