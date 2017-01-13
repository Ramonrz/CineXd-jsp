package br.com.cinexd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cinexd.bo.FilmeBO;
import br.com.cinexd.models.Filme;

@WebServlet("/FilmeController")
public class FilmeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String LIST = "/Views/filme/index.jsp";
	private static String EDIT = "/Views/filme/editar.jsp";
	private static String LIST_SERVLET = "/CineXd/FilmeController?action=list";

	FilmeBO filmeBO = new FilmeBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getSession().getAttribute("perfil").equals("Gerente-Admin")){
			
			if (request.getParameter("action").equals("list")) {
				request.setAttribute("filmes", filmeBO.listar());
				request.getRequestDispatcher(LIST).forward(request, response);

			} else if (request.getParameter("action").equals("desativar")) {
				int status = 0;
				int id = Integer.parseInt(request.getParameter("id"));
				filmeBO.desativar(status, id);
				response.sendRedirect(LIST_SERVLET);

			} else if (request.getParameter("action").equals("editar")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("filmes", filmeBO.obterPorId(id));
				request.getRequestDispatcher(EDIT).forward(request, response);
			}
			
		} else{
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Filme filme = new Filme();

		filme.setTitulo(request.getParameter("titulo"));
		filme.setGenero(request.getParameter("genero"));
		filme.setIdioma(request.getParameter("idioma"));
		filme.setDuracao(Integer.parseInt(request.getParameter("duracao")));
		filme.setFaixaEtaria(Integer.parseInt(request.getParameter("faixaEtaria")));

		if(request.getSession().getAttribute("perfil").equals("Gerente-Admin")){
			
			if (request.getParameter("action").equals("add")) {
				filme.setStatus(1);
				filmeBO.inserir(filme);

			} else if (request.getParameter("action").equals("editar")) {
				filme.setId(Integer.parseInt(request.getParameter("id")));
				filmeBO.editar(filme);
			}

			response.sendRedirect(LIST_SERVLET);
			
		} else {
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
	}
}