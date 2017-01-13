<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="br.com.cinexd.models.Filme"%>
<%@ page import="br.com.cinexd.models.Sessao"%>
<%@ page import="br.com.cinexd.models.Usuario"%>
<%@ page import="br.com.cinexd.models.Venda"%>
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
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well">
					<h2>
						<i class="glyphicon glyphicon-th-list"></i> Venda Ingressos
					</h2>
				</div>
<style>

.field-filtro-venda {
  position: relative;
  margin: 15px 5px;
  padding: 39px 19px 14px;
  *padding-top: 19px;
  background-color: #fff;
  border: 1px solid #ddd;
  -webkit-border-radius: 4px;
     -moz-border-radius: 4px;
          border-radius: 4px;
}

.field-filtro-venda:after {
  content: "Filtro Venda";
  position: absolute;
  top: -1px;
  left: -1px;
  padding: 3px 7px;
  font-size: 12px;
  font-weight: bold;
  background-color: #f5f5f5;
  border: 1px solid #ddd;
  color: #9da0a4;
  -webkit-border-radius: 4px 0 4px 0;
     -moz-border-radius: 4px 0 4px 0;
          border-radius: 4px 0 4px 0;
}
#editor {
	max-height: 250px;
	height: 250px;
	background-color: white;
	border-collapse: separate; 
	border: 1px solid rgb(204, 204, 204); 
	padding: 4px; 
	box-sizing: content-box; 
	-webkit-box-shadow: rgba(0, 0, 0, 0.0745098) 0px 1px 1px 0px inset; 
	box-shadow: rgba(0, 0, 0, 0.0745098) 0px 1px 1px 0px inset;
	border-top-right-radius: 3px; border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px; border-top-left-radius: 3px;
	overflow: scroll;
	outline: none;
}

</style>				
<div class="field-filtro-venda" style="text-align: justify;">
 <div id="alerts"></div>
 <form role="form" action="/CineXd/RelatorioController" method="post">
                    <script src="js/MascarasInputText.js"></script>
                    <script src="../../js/MascarasInputText.js"></script>
	 <div class="form-group">
		<label for="dataDe">Ano:</label> 
		<input type="number" id="ano" onkeypress="return MascarasInputText(this, '9999', event);" maxlength="4" class="form-control" name="ano" placeholder="ex:2016">
		<label for="mes">Mes:</label> 
		<select class="form-control" id="mes" name="mes">
			<option value=""></option>
			<option value="01">Janeiro</option>
			<option value="02">Fevereiro</option>
			<option value="03">Março</option>
			<option value="04">Abril</option>
			<option value="05">Maio</option>
			<option value="06">Junho</option>
			<option value="07">Julho</option>
			<option value="08">Agosto</option>
			<option value="09">Setembro</option>
			<option value="10">Outubro</option>
			<option value="11">Novembro</option>
			<option value="12">Dezembro</option>
		</select>
	 </div>
	 <div class="form-group">
		<label>Filme :</label> 
		<select class="form-control" name="filme">
		<option value="" ></option>
		<% List<Filme> comboFilme=(List<Filme>) request.getAttribute("comboFilme");
           for (Filme filme : comboFilme) {%>
		   <option value="<%=filme.getId()%>"><%=filme.getTitulo()%></option>
        <% }%>
		</select>
	  </div>
	  <div class="form-group">
		<label>Sessão :</label> 
		<select class="form-control" name="sessao">
		<option value="" ></option>
		<% List<Sessao> comboSessao=(List<Sessao>) request.getAttribute("comboSessao");
           for (Sessao sessao : comboSessao) {%>
		   <option value="<%=sessao.getHorario()%>"><%= sessao.getHorario() %></option>
        <% }%>
		</select>
	  </div>
	  <div class="form-group">
		<label for="atendente">Atendente :</label> 
		<select id="atendente" class="form-control" name="atendente">
		<option value="" ></option>
		<% List<Usuario> comboUsuario=(List<Usuario>) request.getAttribute("comboUsuario");
           for (Usuario user : comboUsuario) {%>
		   <option value="<%=user.getId()%>"><%= user.getNome() %></option>
        <% }%>
        <option value="0" >Todos Usuários</option>
		</select>
	  </div>
	   <div class="form-group">
	    <button type="submit" name="action" value="Buscar" class="btn btn-info">
      <span class="glyphicon glyphicon-search"></span> Buscar
    </button>
	 
	  </div>
</form>
</div>				

				<div class="box-content">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<thead>
							<tr>
								<th>Data Venda</th>
								<th>Sessão</th>
								<th>Filme</th>
								<th>Qtd ingressos inteira</th>
								<th>Qtd ingressos meia</th>
								<th>Qtd ingressos vendidos</th>
								<th>Valor</th>
							</tr>
						</thead>
						<tbody>
			             <% List<Venda> vendaRelatorio=(List<Venda>) request.getAttribute("listar");
             for (Venda relatorio : vendaRelatorio) {
            	 SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");  
				    String data=formato.format(relatorio.getData());
				    String[] arrayData=data.split("/");
             %>

								<tr>
									<td><%= arrayData[2]+"/"+arrayData[1]+"/"+arrayData[0] %></td>
									<td><%= relatorio.getSessao().getHorario() %></td>
									<td><%= relatorio.getSessao().getFilme().getTitulo() %></td>
									<td><%= relatorio.getQtdeIngressoInteira() %></td>
									<td><%= relatorio.getQtdeIngressoMeia() %></td>
									<td><%= relatorio.getQtdeIngressoTotal() %></td>
									<td><%= relatorio.getValor() %></td>
									
								</tr>

							<% } %>

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