<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
    <meta charset="utf-8">
    <title>Cine Xd</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The styles -->
    <link href="../../css/bootstrap-spacelab.min.css" rel="stylesheet">
    <link href="css/bootstrap-spacelab.min.css" rel="stylesheet">

    <link href="../../css/charisma-app.css" rel="stylesheet">
    <link href="css/charisma-app.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="../../bower_components/jquery/jquery.min.js"></script>
    
    <script src="bower_components/jquery/jquery.min.js"></script>
      
</head>

<body>

    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
			
            <a class="navbar-brand" href="index.jsp">
                <h4>Cine Xd</h4></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${userAutenticado.nome}</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
               	    <li><a href="/CineXd/UsuarioController?action=alterarSenha&id=${userAutenticado.id}">Alterar Senha</a></li>
                    <li class="divider"></li>
                    <li><a href="/CineXd/LoginController?action=sair">Sair</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->

        </div>
    </div>
    <!-- topbar ends -->
    
    
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Menu</li>
                        <li><a class="ajax-link" href="/CineXd//Views/dashboard/index.jsp"><i class="glyphicon glyphicon-home"></i><span> Dashboard</span></a>
                        </li>
                        <li><a class="ajax-link" href="/CineXd/UsuarioController?action=list"><i class="glyphicon glyphicon-user"></i><span> Usuários</span></a>
                        </li>
                        <li><a class="ajax-link" href="/CineXd/FilmeController?action=list"><i class="glyphicon glyphicon-film"></i><span> Filmes</span></a>
                        </li>
                        <li><a class="ajax-link" href="/CineXd/SalaController?action=list"><i class="glyphicon glyphicon-play-circle"></i><span> Salas</span></a></li>
                        <li><a class="ajax-link" href="/CineXd/SessaoController?action=list"><i class="glyphicon glyphicon-time"></i><span> Sessões</span></a>
                        </li>
                        <li><a class="ajax-link" href="/CineXd/VendaController?action=list"><i class="glyphicon glyphicon-shopping-cart"></i><span> Venda</span></a>
                        </li>
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-th-list"></i><span> Relatórios</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="/CineXd/RelatorioController?action=relatorio">Venda Ingressos</a></li>
                                <li><a href="/CineXd/RelatorioController?action=listLotacao">Lotação Poltronas</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
            <div>
</div>


<!-- external javascript -->

<script src="../../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="../../js/jquery.cookie.js"></script>
<script src="js/jquery.cookie.js"></script>

<!-- calender plugin -->
<script src='../../bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='../../bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>

<!-- data table plugin -->
<script src='../../js/jquery.dataTables.min.js'></script>
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="../../bower_components/chosen/chosen.jquery.min.js"></script>
<script src="bower_components/chosen/chosen.jquery.min.js"></script>

<!-- plugin for gallery image view -->
<script src="../../bower_components/colorbox/jquery.colorbox-min.js"></script>
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>

<!-- notification plugin -->
<script src="../../js/jquery.noty.js"></script>
<script src="js/jquery.noty.js"></script>

<!-- star rating plugin -->
<script src="../../js/jquery.raty.min.js"></script>
<script src="js/jquery.raty.min.js"></script>

<!-- for iOS style toggle switch -->
<script src="../../js/jquery.iphone.toggle.js"></script>
<script src="js/jquery.iphone.toggle.js"></script>

<!-- autogrowing textarea plugin -->
<script src="../../js/jquery.autogrow-textarea.js"></script>
<script src="js/jquery.autogrow-textarea.js"></script>

<!-- multiple file upload plugin -->
<script src="../../js/jquery.uploadify-3.1.min.js"></script>
<script src="js/jquery.uploadify-3.1.min.js"></script>

<!-- history.js for cross-browser state change on ajax -->
<script src="../../js/jquery.history.js"></script>
<script src="js/jquery.history.js"></script>

<!-- application script for Charisma demo -->
<script src="../../js/charisma.js"></script>
<script src="js/charisma.js"></script>

</body>
</html>