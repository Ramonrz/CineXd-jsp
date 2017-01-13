<%@ page import="java.util.List"%>
<%@ page import="br.com.cinexd.models.Filme"%>
<%@ page import="br.com.cinexd.models.Sala" %>
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
						<i class="glyphicon glyphicon-edit"></i> Cadastrar Sessão
					</h2>
                
				</div>
				<div class="box-content">
					<form role="form" action="/CineXd/SessaoController" method="post" id="formulario">
                    <script src="js/MascarasInputText.js"></script>
                    <script src="../../js/MascarasInputText.js"></script>

						<div class="form-group">
							<label>Horário :</label> <input type="text" class="form-control"
								name="horario"  onkeypress="return MascarasInputText(this, '99:99', event);" maxlength="5" placeholder="Ex:00:00">
						</div>
                        
						<div class="form-group">
							<label>Data :</label> <input type="text" class="form-control"
								name="data"   onkeypress="return MascarasInputText(this, '99/99/9999', event);" maxlength="10"  placeholder="Ex: __/___/_____">
						</div>
                        <div class="form-group">
							<label>Valor Inteiro :</label> <input type="text" class="form-control"
								name="valorInteiro" onkeypress="return MascarasInputText(this, '99.99', event);" maxlength="6"     placeholder="Ex:0.00">
						</div>
						<div class="form-group">
							<label>Filme :</label> 
							<select class="form-control" name="filme">
								<option value="" selected disabled>Selecione</option>
								<%  List<Filme> comboFilme=(List<Filme>) request.getAttribute("comboFilme");
							      for (Filme filme : comboFilme) { %>
							    	<option value="<%=filme.getId() %>"><%=filme.getTitulo() %></option>  
							    	  
							    <%  } %>
							</select>
						</div>

						<div class="form-group">
							<label>Observação :</label><textarea name="observacao" class="form-control" rows="" cols=""></textarea>
							 
						</div>

						<div class="form-group">
							<label>Sala :</label> 
							<select class="form-control" name="sala">
								<option value="" selected disabled>Selecione</option>
								<%  List<Sala> comboSala=(List<Sala>) request.getAttribute("comboSala");
							      for (Sala sala : comboSala) { %>
							    	<option value="<%=sala.getId() %>"><%=sala.getNumero() %> - <%=sala.getCategoria() %></option>  
							    	  
							    <%  } %>
							</select>
						</div>

						<button type="submit" name="action" value="Cadastrar" class="btn btn-default">Cadastrar</button>
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