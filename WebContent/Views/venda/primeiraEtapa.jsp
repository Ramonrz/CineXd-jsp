                        <%@ page import="java.util.List"%>
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
						<i class="glyphicon glyphicon-edit"></i> Cadastrar Venda
					</h2>
                
				</div>
				<div class="box-content">
					<form role="form" action="/CineXd/VendaController" method="post" id="formulario">
                  
						
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

						

					

						<button type="submit" name="action" value="proximoPrimeira" class="btn btn-default">Próximo </button>
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
</html>                                                                                                                                                                                                                                                                                                                                       