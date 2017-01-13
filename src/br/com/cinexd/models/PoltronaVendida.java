package br.com.cinexd.models;

public class PoltronaVendida {
	private int id;
	private int numero;
	private float valor;
	private int tipo;
	private int fileira;
	private Venda venda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getFileira() {
		return fileira;
	}
	public void setFileira(int fileira) {
		this.fileira = fileira;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	
	

}
