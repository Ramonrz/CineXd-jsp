package br.com.cinexd.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cinexd.bo.FilmeBO;
import br.com.cinexd.bo.SalaBO;
import br.com.cinexd.bo.SessaoBO;
import br.com.cinexd.models.Filme;
import br.com.cinexd.models.Sala;
import br.com.cinexd.models.Sessao;

/**
 * Servlet implementation class SessaoController
 */
@WebServlet("/SessaoController")
public class SessaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String LIST = "/Views/sessao/index.jsp";
	private static String EDIT = "/Views/sessao/editar.jsp";
	private static String ADD = "/Views/sessao/cadastrar.jsp";
	private static String LIST_SERVLET = "/CineXd/SessaoController?action=list";

	SessaoBO sessaoBO = new SessaoBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			switch (request.getParameter("action")) {
			case "list": {
				request.setAttribute("sessao", sessaoBO.listar());
				request.getRequestDispatcher(LIST).forward(request, response);
			}
				break;
			case "add": {
				FilmeBO filmeBO = new FilmeBO();
				SalaBO salaBO = new SalaBO();
				request.setAttribute("comboSala", salaBO.listar());
				request.setAttribute("comboFilme", filmeBO.listar());
				request.getRequestDispatcher(ADD).forward(request, response);

			}
				break;
			case "desativar": {
				Sessao sessao = new Sessao();
				sessao.setId(Integer.parseInt(request.getParameter("id")));
				sessao.setStatus(0);
				sessaoBO.desativar(sessao);
				request.setAttribute("sessao", sessaoBO.listar());
				request.getRequestDispatcher(LIST).forward(request, response);

			}
				break;
			case "editar": {
				Sessao sessao = new Sessao();
				FilmeBO filmeBO = new FilmeBO();
				SalaBO salaBO = new SalaBO();

				sessao.setId(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("sessao", sessaoBO.obterPorId(sessao));
				request.setAttribute("comboSala", salaBO.listar());
				request.setAttribute("comboFilme", filmeBO.listar());

				request.getRequestDispatcher(EDIT).forward(request, response);
			}
				break;

			}

		} else {
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		Sessao sessao = new Sessao();
		SessaoBO sessaoBO = new SessaoBO();

		sessao.setHorario(request.getParameter("horario"));

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = null;
		try {
			dataFormatada = formato.parse(request.getParameter("data"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		sessao.setData(dataFormatada);

		sessao.setValorInteira(Float.parseFloat(request.getParameter("valorInteiro")));
		float valorMeia = Float.parseFloat(request.getParameter("valorInteiro")) / 2;
		sessao.setValorMeia(valorMeia);
		sessao.setFilme(new Filme());
		sessao.getFilme().setId(Integer.parseInt(request.getParameter("filme")));
		sessao.setObservacao(request.getParameter("observacao"));
		sessao.setSala(new Sala());
		sessao.getSala().setId(Integer.parseInt(request.getParameter("sala")));

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			switch (action) {
			case "Cadastrar": {
				sessao.setStatus(1);
				sessaoBO.inserir(sessao);
				request.setAttribute("sessao", sessaoBO.listar());
				response.sendRedirect(LIST_SERVLET);
			}
				break;
			case "Salvar": {

				sessao.setId(Integer.parseInt(request.getParameter("id")));
				sessaoBO.editar(sessao);
				response.sendRedirect(LIST_SERVLET);
			}
				break;
			default: {

				response.sendRedirect(LIST_SERVLET);
			}
				break;

			}
		} else {
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
	}
}