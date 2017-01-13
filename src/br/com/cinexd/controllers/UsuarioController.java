package br.com.cinexd.controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cinexd.bo.UsuarioBO;
import br.com.cinexd.models.Usuario;

@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static String LIST = "/Views/usuario/index.jsp";
	private static String EDIT = "/Views/usuario/editar.jsp";
	private static String ALTERAR_SENHA = "/Views/usuario/alterarSenha.jsp";
	private static String LIST_SERVLET = "/CineXd/UsuarioController?action=list";

	UsuarioBO userBO = new UsuarioBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("action").equals("alterarSenha")) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("usuarios", userBO.obterPorId(id));
			request.getRequestDispatcher(ALTERAR_SENHA).forward(request, response);
		}

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			if (request.getParameter("action").equals("list")) {
				request.setAttribute("usuarios", userBO.listar());
				request.getRequestDispatcher(LIST).forward(request, response);

			} else if (request.getParameter("action").equals("desativar")) {
				int status = 0;
				int id = Integer.parseInt(request.getParameter("id"));
				userBO.desativar(status, id);
				response.sendRedirect(LIST_SERVLET);

			} else if (request.getParameter("action").equals("editar")) {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("usuarios", userBO.obterPorId(id));
				request.getRequestDispatcher(EDIT).forward(request, response);
			}

		} else {
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = new Usuario();

		if (request.getSession().getAttribute("perfil").equals("Gerente-Admin")) {

			if (request.getParameter("action").equals("add") || request.getParameter("action").equals("alterarSenha")) {

				if (request.getParameter("senha").equals("")) {
					usuario.setSenha("");

				} else {
					try {
						MessageDigest algorithm = MessageDigest.getInstance("MD5");
						byte messageDigest[] = algorithm.digest(request.getParameter("senha").getBytes("UTF-8"));
						StringBuilder hexString = new StringBuilder();
						for (byte b : messageDigest) {
							hexString.append(String.format("%02X", 0xFF & b));
						}
						String senha = hexString.toString();
						usuario.setSenha(senha);

					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
			}

			if (request.getParameter("action").equals("add") || request.getParameter("action").equals("editar")) {

				usuario.setNome(request.getParameter("nome"));
				usuario.setCpf(request.getParameter("cpf"));
				usuario.setLogin(request.getParameter("login"));
				usuario.setFuncao(request.getParameter("funcao"));
				usuario.setPerfil(request.getParameter("perfil"));
			}

			if (request.getParameter("action").equals("add")) {
				usuario.setStatus(1);
				userBO.inserir(usuario);

			} else if (request.getParameter("action").equals("alterarSenha")) {
				usuario.setId(Integer.parseInt(request.getParameter("id")));
				userBO.alterarSenha(usuario);

			} else if (request.getParameter("action").equals("editar")) {
				usuario.setId(Integer.parseInt(request.getParameter("id")));
				userBO.editar(usuario);
			}
			response.sendRedirect(LIST_SERVLET);

		} else{
			request.setAttribute("permissao", Mensagens.permissao);
			request.getRequestDispatcher("Views/dashboard/index.jsp").forward(request, response);
		}
	}
}