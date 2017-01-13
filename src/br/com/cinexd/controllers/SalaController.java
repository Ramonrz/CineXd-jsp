package br.com.cinexd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cinexd.bo.SalaBO;
import br.com.cinexd.models.Sala;

@WebServlet("/SalaController")
public class SalaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String LIST = "/Views/sala/index.jsp";
	private static String EDIT = "/Views/sala/editar.jsp";
	private static String LIST_SERVLET = "/CineXd/SalaController?action=list";

	SalaBO salaBO = new SalaBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			if (request.getParameter("action").equals("list")) {
				request.setAttribute("salas", salaBO.listar());
				request.getRequestDispatcher(LIST).forward(request, response);

			} else if (request.getParameter("action").equals("desativar")) {
				int status = 0;
				int id = Integer.parseInt(request.getParameter("id"));
				salaBO.desativar(status, id);
				response.sendRedirect(LIST_SERVLET);

			} else if (request.getParameter("action").equals("editar")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("salas", salaBO.obterPorId(id));
				request.getRequestDispatcher(EDIT).forward(request, response);
			}

		} else {
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Sala sala = new Sala();

		int qtdeFileira = Integer.parseInt(request.getParameter("qtdeFileiras"));
		int qtdePoltronaFileira = Integer.parseInt(request.getParameter("qtdePoltronaFileira"));

		sala.setQuantidadeFileira(qtdeFileira);
		sala.setCategoria(request.getParameter("categoria"));
		sala.setQuantidadePoltronaFileira(qtdePoltronaFileira);
		sala.setNumero(Integer.parseInt(request.getParameter("numero")));
		sala.setQuantidadePoltrona(qtdeFileira * qtdePoltronaFileira);

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			if (request.getParameter("action").equals("add")) {
				sala.setStatus(1);
				salaBO.inserir(sala);

			} else if (request.getParameter("action").equals("editar")) {
				sala.setId(Integer.parseInt(request.getParameter("id")));
				salaBO.editar(sala);
			}

			response.sendRedirect(LIST_SERVLET);
			
		} else{
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
	}
}