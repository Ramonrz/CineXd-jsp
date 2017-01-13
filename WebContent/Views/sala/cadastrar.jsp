<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<i class="glyphicon glyphicon-edit"></i> Cadastrar Sala
					</h2>

				</div>
				<div class="box-content">
					<form role="form" action="/CineXd/SalaController?action=add"
						method="post" id="formulario">


						<div class="form-group">
							<label>Número :</label> <input type="number" class="form-control"
								name="numero">
						</div>

						<div class="form-group">
							<label>Categoria :</label> <select class="form-control"
								name="categoria">
								<option value="" selected disabled>Selecione</option>
								<option value="2D">2D</option>
								<option value="3D">3D</option>
							</select>
						</div>

						<div class="form-group">
							<label>Quantidade de Fileiras :</label> <input type="number"
								class="form-control" name="qtdeFileiras">
						</div>
						
						<div class="form-group">
							<label>Quantidade de poltronas fileira :</label> <input type="number"
								class="form-control" name="qtdePoltronaFileira">
						</div>

						<button type="submit" class="btn btn-default">Cadastrar</button>
					</form>

				</div>
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->


	<jsp:include page="/Views/footer/footer.jsp"></jsp:include>

</body>
	<script type="text/javascript" src="../../js/jquery.validate.js"></script>
	<script type="text/javascript" src="../../js/validate.js"></script>
</html>