package br.fiap.util;

import static javax.swing.JOptionPane.*;

import br.fiap.carga.Carga;
import br.fiap.cliente.Cliente;
import br.fiap.viagem.Viagem;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Util {
	private Viagem viagem = new Viagem();
	
	// cria e exibe o menu de opções para o usuário
	public void menu() {
		int opcao;
		String aux = "Reserva de Cargas Boa Viagem\n";
		aux += "1. Reservar viagem\n";
		aux += "2. Pesquisar reserva\n";
		aux += "3. Visualizar reservas\n";
		aux += "4. Capacidade reservada\n";
		aux += "5. Cancelar reserva\n";
		aux += "6. Finalizar\n";
		
		do {
			opcao = parseInt(showInputDialog(aux));
			if(opcao < 1 || opcao > 6) {
				showMessageDialog(null, "Opção inválida!");
			}
			else {
				switch(opcao) {
					case 1:
						reservar();
						break;
					case 2:
						pesquisar ();
						break;
					case 3: 
						listar();
						break;
					case 4 :
						capacidade();
						break;
					case 5 :
						cancelar ();
						break;
				}
			}
		} while(opcao != 6);
		
	}
	
	private void capacidade() {
		showMessageDialog(null,"Capacidade reservada\n"+ viagem.capacidadeReservada());
		
	}

	private void listar() {
		String listagem = viagem.listar();
		showMessageDialog(null,listagem);
	}

	private void cancelar() {
		int cnpj;
		cnpj = parseInt (showInputDialog("CNPJ"));
		boolean status =  viagem.cancelar(cnpj);
		if(status) {
			showMessageDialog(null,"Reserva cancelada com sucesso");
		}
		else {
			showMessageDialog(null , "CNPJ não encontrado para ccancelar a reserva");
		}
	}

	private void pesquisar() {
		int cnpj;
		cnpj = parseInt (showInputDialog("CNPJ"));
		Carga carga = viagem.pesquisar(cnpj);
		if(carga == null) {
			showMessageDialog(null,"CNPJ não tem carga reservada");
		}
		else {
			showMessageDialog(null,carga.getDados());
		}
	}

	private void reservar() {
		int cnpj;
		String destino, nome;
		double peso;
		
		cnpj = parseInt(showInputDialog("CNPJ"));
		destino = showInputDialog("Destino");
		nome = showInputDialog("Nome do cliente");
		peso = parseDouble(showInputDialog("Peso em kgs"));
		
		Cliente cliente = new Cliente(cnpj, nome);
		Carga carga = new Carga(destino, peso, cliente);
		if(viagem.reservar(carga)) {
			showMessageDialog(null, "Reserva realizada com sucesso");
		}
		else {
			showMessageDialog(null, "Não foi possível realizar a reserva");
		}
		
	}
	
	
}


