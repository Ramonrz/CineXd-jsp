package br.com.cinexd.models;

public class Sala {

	private int id;
	private int status;
	private int numero;
	private String categoria;
	private int quantidadePoltrona;
	private int quantidadeFileira;
	private int quantidadePoltronaFileira;

	public Sala() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getQuantidadePoltrona() {
		return quantidadePoltrona;
	}

	public void setQuantidadePoltrona(int quantidadePoltrona) {
		this.quantidadePoltrona = quantidadePoltrona;
	}

	public int getQuantidadeFileira() {
		return quantidadeFileira;
	}

	public void setQuantidadeFileira(int quantidadeFileira) {
		this.quantidadeFileira = quantidadeFileira;
	}

	public int getQuantidadePoltronaFileira() {
		return quantidadePoltronaFileira;
	}

	public void setQuantidadePoltronaFileira(int quantidadePoltronaFileira) {
		this.quantidadePoltronaFileira = quantidadePoltronaFileira;
	}
	

}