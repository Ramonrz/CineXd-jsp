<%@ page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cine Xd</title>
<script src="js/mascaraValidacao.js"></script>
</head>
<body>

	<jsp:include page="/Views/content/content.jsp"></jsp:include>

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-edit"></i> Editar Usuário
					</h2>

				</div>
				<div class="box-content">
					<form role="form"
						action="/CineXd/UsuarioController?action=editar&id=${usuarios.id}"
						method="post" id="formulario" name="form1">


						<div class="form-group">
							<label>Nome :</label> <input type="text" class="form-control"
								name="nome" value="${usuarios.nome}">
						</div>

						<div class="form-group">
							<label>CPF :</label> <input type="text" class="form-control"
								name="cpf" onBlur="validarCPF(form1.cpf);" onKeyPress="mascaraCPF(form1.cpf);" maxlength="14" value="${usuarios.cpf}">
						</div>

						<div class="form-group">
							<label>Login :</label> <input type="text" class="form-control"
								name="login" placeholder="Email" value="${usuarios.login}">
						</div>

						<div class="form-group">
							<label>Função :</label> <input type="text" class="form-control"
								name="funcao" value="${usuarios.funcao}">
						</div>

						<div class="form-group">
							<label>Perfil :</label> <select class="form-control"
								name="perfil">
								<option value="${usuarios.perfil}">${usuarios.perfil}</option>
								<option value="Gerente-Admin">Gerente - Admin</option>
								<option value="Atendente">Atendente</option>
							</select>
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