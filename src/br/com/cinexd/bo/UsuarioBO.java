package br.com.cinexd.bo;

import java.util.List;

import br.com.cinexd.dao.UsuarioDAO;
import br.com.cinexd.models.Usuario;

public class UsuarioBO {

	public boolean inserir(Usuario usuario) {

		UsuarioDAO userDAO = new UsuarioDAO();
		if (!existeCampoNuloInserir(usuario) && userDAO.verificarRegistroExistente(usuario) == false) {
			userDAO.inserir(usuario);
			return true;
		} else {
			return false;
		}
	}

	public boolean desativar(int status, int id) {

		if (status == 0) {
			UsuarioDAO userDAO = new UsuarioDAO();
			userDAO.desativar(status, id);
			return true;
		} else {
			return false;
		}
	}

	public boolean editar(Usuario usuario) {

		UsuarioDAO userDAO = new UsuarioDAO();
		if (!existeCampoNuloEditar(usuario)) {
			userDAO.editar(usuario);
			return true;
		} else {
			return false;
		}
	}

	public boolean alterarSenha(Usuario usuario) {

		UsuarioDAO userDAO = new UsuarioDAO();
		if (!existeCampoNuloAlterarSenha(usuario)) {
			userDAO.alterarSenha(usuario);
			return true;
		} else {
			return false;
		}
	}

	public List<Usuario> listar() {

		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.listar();
	}

	public Usuario obterPorId(int id) {

		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.obterPorId(id);
	}

	public Usuario autenticar(String login, String senha) {

		UsuarioDAO userDAO = new UsuarioDAO();
		return userDAO.autenticar(login, senha);
	}

	public boolean existeCampoNuloInserir(Usuario usuario) {

		if (usuario.getNome().equals("") || usuario.getCpf().equals("") || usuario.getLogin().equals("")
				|| usuario.getSenha().equals("") || usuario.getFuncao().equals("") || usuario.getPerfil().equals("")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean existeCampoNuloEditar(Usuario usuario) {

		if (usuario.getNome().equals("") || usuario.getCpf().equals("") || usuario.getLogin().equals("")
				|| usuario.getFuncao().equals("") || usuario.getPerfil().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean existeCampoNuloAlterarSenha(Usuario usuario) {

		if (usuario.getSenha().equals("")) {
			return true;
		} else {
			return false;
		}
	}
}