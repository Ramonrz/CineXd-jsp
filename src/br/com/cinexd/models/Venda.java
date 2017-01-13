package br.com.cinexd.models;

import java.util.Date;

public class Venda {

	private int id;
	private Date data;
	private float valor;
	private Sessao sessao;
	private Usuario usuario;
	private int qtdeIngressoMeia;
	private int qtdeIngressoInteira;
	private int qtdeIngressoTotal;

	public Venda() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public int getQtdeIngressoMeia() {
		return qtdeIngressoMeia;
	}

	public void setQtdeIngressoMeia(int qtdeIngressoMeia) {
		this.qtdeIngressoMeia = qtdeIngressoMeia;
	}

	public int getQtdeIngressoInteira() {
		return qtdeIngressoInteira;
	}

	public void setQtdeIngressoInteira(int qtdeIngressoInteira) {
		this.qtdeIngressoInteira = qtdeIngressoInteira;
	}

	public int getQtdeIngressoTotal() {
		return qtdeIngressoTotal;
	}

	public void setQtdeIngressoTotal(int qtdeIngressoTotal) {
		this.qtdeIngressoTotal = qtdeIngressoTotal;
	}

}