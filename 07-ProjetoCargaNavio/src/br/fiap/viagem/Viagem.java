package br.fiap.viagem;
import br.fiap.carga.Carga;

public class Viagem {
	private double capacidade;
	private int index;
	private Carga[] carga;
	
	public Viagem() {
		this.capacidade = 10000;
		this.index = 0;
		this.carga = new Carga[20];
	}
	
	public boolean reservar(Carga carga) {
		if(permitidoReservar(carga.getPeso())) {
			this.carga[index] = carga;
			this.index++;
			return true;
		}
		return false;
	}
	
	public double capacidadeReservada() {
		double total = 0;
		for(int i = 0; i < this.index; i++) {
			total += carga[i].getPeso();
		}
		return total;
	}

	private boolean permitidoReservar(double peso) {
		return capacidadeReservada() + peso <= capacidade;
	}
	
	public Carga pesquisar (int cnpj) {
		int p = encontrarCarga (cnpj);
		if(p!= -1) {
			return carga [p];
		}
		return null;
		
	}

	public boolean cancelar(int cnpj) {
		int p = encontrarCarga (cnpj);
		if(p != -1) {
			for(int i = p; i < index; i++) {
				carga [i] =  carga [i + 1];
			}
			index--;
			return true;
		}
		return false;
		
	}
	
	private int encontrarCarga (int cnpj) {
		
		for(int i = 0; i < index; i++) {
			if(carga[i].getCliente().getCnpj() == cnpj) {
				return i;
			}
		}
		return -1;
	}

	public String listar() {
		String listagem = "";
		for(int i = 0; i < index; i++) {
			listagem += carga[i].getDados() + "\n\n";
		}
		return listagem;
	}
	
}
