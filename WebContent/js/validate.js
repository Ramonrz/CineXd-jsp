$(document).ready(function() {
	$('#formulario').validate({
		rules : {
			nome : {
				required : true,
				minlength : 3
			},
			login : {
				required : true,
				email : true
			},
			senha : {
				required : true,
				minlength : 6
			},
			cpf : {
				required : true
			},
			funcao : {
				required : true
			},
			perfil : {
				required : true
			},
			titulo : {
				required : true
			},
			genero : {
				required : true
			},
			idioma : {
				required : true
			},
			duracao : {
				required : true,
				min : 0
			},
			faixaEtaria : {
				required : true,
				min : 0
			},
			numero : {
				required : true,
				min : 0
			},
			categoria : {
				required : true
			},
			qtdeFileiras : {
				required : true,
				min : 0,
				max: 10
			},
			qtdePoltronaFileira : {
				required : true,
				min : 0,
				max: 10
			},
			horario : {
				required : true
			},
			data : {
				required : true
			},
			valorInteiro : {
				required : true
			},
			filme : {
				required : true
			},
			sala : {
				required : true
			},
			sessao : {
				required : true
			},
			qtdMeia : {
				required : true,
				maxlength : 2,
				min : 0
			}

		},
		messages : {
			nome : {
				required : "O campo nome é obrigatório.",
				minlength : "O campo nome deve conter no mínimo 3 caracteres."
			},
			senha : {
				required : "O campo senha é obrigatório.",
				minlength : "O campo senha deve conter no mínimo 6 caracteres."
			},
			login : {
				required : "O campo login é obrigatório.",
				email : "O campo login deve conter um email válido."
			},
			cpf : {
				required : "O campo cpf é obrigatório."
			},
			funcao : {
				required : "O campo função é obrigatório.",
			},
			perfil : {
				required : "O campo perfil é obrigatório."
			},
			titulo :{
				required : "O campo título é obrigatório."
			},
			genero : {
				required : "O campo gênero é obrigatório."
			},
			idioma : {
				required : "O campo idioma é obrigatório."
			},
			duracao : {
				required : "O campo duração é obrigatório.",
				min: "Digite um valor maior ou igual a 0." 
			},
			faixaEtaria : {
				required : "O campo faixa etária é obrigatório.",
				min: "Digite um valor maior ou igual a 0." 	
			},
			numero : {
				required : "O campo número é obrigatório.",
				min: "Digite um valor maior ou igual a 0." 	
			},
			categoria : {
				required : "O campo categoria é obrigatório."	
			},
			qtdeFileiras : {
				required : "O campo qtdeFileiras é obrigatório.",
				min: "Digite um valor maior ou igual a 0.",
				max: "Digite um valor menor ou igual a 10." 
			},
			qtdePoltronaFileira : {
				required : "O campo qtdePoltronaFileira é obrigatório.",
				min: "Digite um valor maior ou igual a 0.",
				max: "Digite um valor menor ou igual a 10." 
			},
			horario : {
				required : "O campo horario é obrigatório.",
			},
			data : {
				required : "O campo data é obrigatório.",
			},
			valorInteiro : {
				required : "O campo valorInteiro é obrigatório.",
			},
			filme : {
				required : "O campo filme é obrigatório.",
			},
			sala : {
				required : "O campo sala é obrigatório.",
			},
			sessao : {
				required : "O campo sessão é obrigatório.",
			},
			qtdMeia : {
				required : "O campo qtdMeia é obrigatório.",
				min: "Digite um valor maior ou igual a 0.",
				maxlength : "Por favor, insira no máximo 2 caracteres."
			},

		}

	});
});