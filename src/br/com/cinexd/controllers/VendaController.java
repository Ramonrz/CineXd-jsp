package br.com.cinexd.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cinexd.bo.FilmeBO;
import br.com.cinexd.bo.PoltronaVendidaBO;
import br.com.cinexd.bo.SessaoBO;
import br.com.cinexd.bo.VendaBO;
import br.com.cinexd.models.Filme;
import br.com.cinexd.models.PoltronaVendida;
import br.com.cinexd.models.Sessao;
import br.com.cinexd.models.Usuario;
import br.com.cinexd.models.Venda;

/**
 * Servlet implementation class VendaController
 */
@WebServlet("/VendaController")
public class VendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST = "/Views/venda/index.jsp";
	private static String PRIMEIRAETAPA = "/Views/venda/primeiraEtapa.jsp";
	private static String SEGUNDAETAPA = "/Views/venda/segundaEtapa.jsp";
	private static String TERCEIRAETAPA = "/Views/venda/terceiraEtapa.jsp";
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	VendaBO vendaBO=new VendaBO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 
		 switch(request.getParameter("action")){
		  case "list":{
			  request.setAttribute("venda", vendaBO.listar());
			  request.getRequestDispatcher(LIST).forward(request, response);
			  
		  }break;
		  case "primeiraEtapa":{
			  FilmeBO filmeBO = new FilmeBO();
			  request.setAttribute("comboFilme", filmeBO.listar());
			  request.getRequestDispatcher(PRIMEIRAETAPA).forward(request, response);
		  }break;
		  
		  }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("action"));
		switch(request.getParameter("action")){
		case "proximoPrimeira":{
			  SessaoBO sessaoBO = new SessaoBO();
			  int filmeId = Integer.parseInt(request.getParameter("filme"));
			  request.setAttribute("filme", sessaoBO.obterFilme(filmeId));
			  request.setAttribute("comboSessao", sessaoBO.obterPorFilme(filmeId));
			  request.getRequestDispatcher(SEGUNDAETAPA).forward(request, response);
			
		}break;
		case "proximoSegunda":{
			 SessaoBO sessaoBO = new SessaoBO();
			 PoltronaVendida poltronaVendida=new PoltronaVendida();
			 PoltronaVendidaBO poltronaVendidaBO=new PoltronaVendidaBO();
			  Sessao sessao = new Sessao();
			
			  sessao.setId(Integer.parseInt(request.getParameter("sessao")));
			  poltronaVendida.setVenda(new Venda());
			  poltronaVendida.getVenda().setSessao(new Sessao()); 
			  poltronaVendida.getVenda().getSessao().setId(sessao.getId());
			  
			 
			  poltronaVendidaBO.listar(poltronaVendida);
			  request.setAttribute("sessao", sessaoBO.obterPorId(sessao));
			  request.setAttribute("poltronaVendida", poltronaVendidaBO.listar(poltronaVendida));
			  request.getRequestDispatcher(TERCEIRAETAPA).forward(request, response);
			
		}break;
		case "proximoTerceira":{
			 System.out.println("Presen�a ");
			 int idsessao=Integer.parseInt(request.getParameter("sessao"));
			 String[] fileira=request.getParameterValues("linha[]");
			 String[] poltrona=request.getParameterValues("poltrona[]");
			
			 
			 int qtdeIngressoMeia = Integer.parseInt(request.getParameter("qtdMeia"));
			 int qtdeIngressoTotal = Integer.parseInt(request.getParameter("quantidade"));
			 float valorTotal = (Float.parseFloat(request.getParameter("valor"))*Integer.parseInt(request.getParameter("quantidade")))-(Float.parseFloat(request.getParameter("valormeia"))*Integer.parseInt(request.getParameter("qtdMeia")));
			 int usuarioId = Integer.parseInt(request.getParameter("usuario"));
			 
			 Venda venda = new Venda();
			 VendaBO vendaBO = new VendaBO();
			 venda.setUsuario(new Usuario());
			 venda.setValor(valorTotal);
			 venda.getUsuario().setId(usuarioId);
			 venda.setQtdeIngressoMeia(qtdeIngressoMeia);
			 venda.setQtdeIngressoTotal(qtdeIngressoTotal);
			 venda.setQtdeIngressoInteira(qtdeIngressoTotal-qtdeIngressoMeia);
			 venda.setSessao(new Sessao());
			 venda.getSessao().setId(idsessao);
			 
			 int idvenda=vendaBO.inserir(venda);
			 
		        for(int i=0;i<fileira.length;i++){
		        	
		        	
		         System.out.println("Fileira:"+fileira[i]);
		         
		         String[] cadeira=poltrona[i].split(",");
		         System.out.println("Poltrona:"+poltrona[i]);
		         
		         
		         for(int k=0;k<cadeira.length;k++){
		        	 PoltronaVendida poltronaVendida =new PoltronaVendida(); 
		        	 PoltronaVendidaBO poltronaVendidaBO =new PoltronaVendidaBO();
		        	 
		        	 poltronaVendida.setNumero(Integer.parseInt(cadeira[k].replaceAll(" ", "")));
		        	 poltronaVendida.setValor(28);
		        	 poltronaVendida.setTipo(1);
		        	 poltronaVendida.setFileira(Integer.parseInt(fileira[i].replaceAll(" ", "")));
		        	 
		        	 poltronaVendida.setVenda(new Venda());
		        	 poltronaVendida.getVenda().setId(idvenda);
		        	 poltronaVendidaBO.inserir(poltronaVendida);
		        	 
		        	 
		         }
		         
		        
		       }
		        
		        request.setAttribute("venda", vendaBO.listar());
				  request.getRequestDispatcher(LIST).forward(request, response);
		}break;
		
		}
	}

}