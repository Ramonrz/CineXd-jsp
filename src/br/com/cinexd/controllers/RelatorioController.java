package br.com.cinexd.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cinexd.bo.FilmeBO;
import br.com.cinexd.bo.SessaoBO;
import br.com.cinexd.bo.UsuarioBO;
import br.com.cinexd.bo.VendaBO;
import br.com.cinexd.models.Filme;
import br.com.cinexd.models.Sessao;
import br.com.cinexd.models.Usuario;
import br.com.cinexd.models.Venda;

@WebServlet("/RelatorioController")
public class RelatorioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String LIST = "/Views/relatorio/lotacao.jsp";
	private static String RELATORIO = "/Views/relatorio/relatorioVendas.jsp";

	VendaBO vendaBO = new VendaBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			switch (request.getParameter("action")) {
			case "listLotacao": {

				request.setAttribute("listLotacao", vendaBO.gerarRelatorioLotacao());
				request.getRequestDispatcher(LIST).forward(request, response);

			}
				break;
			case "relatorio": {

				UsuarioBO userBO = new UsuarioBO();
				FilmeBO filmeBO = new FilmeBO();
				SessaoBO sessaoBO = new SessaoBO();
				Venda venda = new Venda();
				VendaBO vendaBO = new VendaBO();

				int ano = 0;
				int mes = 0;
				int atendente = 0;
				int filmeId = 0;
				String horario = "";

				venda.setSessao(new Sessao());

				venda.getSessao().setHorario(horario);
				venda.setUsuario(new Usuario());
				venda.getUsuario().setId(atendente);
				venda.getSessao().setFilme(new Filme());
				venda.getSessao().getFilme().setId(filmeId);

				request.setAttribute("comboUsuario", userBO.listar());
				request.setAttribute("comboFilme", filmeBO.listar());
				request.setAttribute("comboSessao", sessaoBO.listarHorario());
				request.setAttribute("listar", vendaBO.relatorioVenda(ano, mes, venda));
				request.getRequestDispatcher(RELATORIO).forward(request, response);

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

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			switch (request.getParameter("action")) {
			case "Buscar": {

				Venda venda = new Venda();
				VendaBO vendaBO = new VendaBO();
				UsuarioBO userBO = new UsuarioBO();
				FilmeBO filmeBO = new FilmeBO();
				SessaoBO sessaoBO = new SessaoBO();
				int ano = 0;
				if (request.getParameter("ano") != "") {
					ano = Integer.parseInt(request.getParameter("ano"));
				}
				int mes = 0;
				if (request.getParameter("mes") != "") {
					mes = Integer.parseInt(request.getParameter("mes"));
				}
				int atendente = 0;
				if (request.getParameter("atendente") != "") {
					atendente = Integer.parseInt(request.getParameter("atendente"));
				}
				int filmeId = 0;
				if (request.getParameter("filme") != "") {
					filmeId = Integer.parseInt(request.getParameter("filme"));

				}

				venda.setSessao(new Sessao());

				venda.getSessao().setHorario(request.getParameter("sessao"));
				venda.setUsuario(new Usuario());
				venda.getUsuario().setId(atendente);
				venda.getSessao().setFilme(new Filme());
				venda.getSessao().getFilme().setId(filmeId);

				request.setAttribute("comboUsuario", userBO.listar());
				request.setAttribute("comboFilme", filmeBO.listar());
				request.setAttribute("comboSessao", sessaoBO.listarHorario());
				request.setAttribute("listar", vendaBO.relatorioVenda(ano, mes, venda));
				request.getRequestDispatcher(RELATORIO).forward(request, response);

			}
				break;
			}

		} else {
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
	}
}