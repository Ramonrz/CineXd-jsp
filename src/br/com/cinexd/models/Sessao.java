package br.com.cinexd.models;

import java.util.Date;

public class Sessao {

	private int id;
	private Date data;
	private Sala sala;
	private int status;
	private Filme filme;
	private String horario;
	private String observacao;
	private float valorInteira;
	private float valorMeia;

	public Sessao() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public float getValorInteira() {
		return valorInteira;
	}

	public void setValorInteira(float valorInteira) {
		this.valorInteira = valorInteira;
	}

	public float getValorMeia() {
		return valorMeia;
	}

	public void setValorMeia(float valorMeia) {
		this.valorMeia = valorMeia;
	}

}