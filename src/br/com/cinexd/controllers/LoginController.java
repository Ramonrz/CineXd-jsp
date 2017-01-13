package br.com.cinexd.controllers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import br.com.cinexd.bo.UsuarioBO;
import br.com.cinexd.models.Usuario;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UsuarioBO userBO = new UsuarioBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("action").equals("sair")) {
			HttpSession sessao = request.getSession();
			sessao.invalidate();
			response.sendRedirect("/CineXd/login.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");

		Usuario usuario = new Usuario();

		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(request.getParameter("senha").getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			String senha = hexString.toString();
			usuario = userBO.autenticar(login, senha);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (usuario != null) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("userAutenticado", usuario);
			sessao.setAttribute("perfil", usuario.getPerfil());
			response.sendRedirect("/CineXd/Views/dashboard/index.jsp");

		} else {
			request.setAttribute("mensagem", Mensagens.erroLogin);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}