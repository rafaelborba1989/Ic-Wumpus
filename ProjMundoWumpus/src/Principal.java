import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		boolean p[]; // = new boolean[7];
		int posicao[] = new int[2];
		boolean mundo[][][];
		List <String> camPercorrido;

		MundoWumpus mw = new MundoWumpus();
		BaseDeConhecimento bc = new BaseDeConhecimento();  //
		mw.inicializaMundo();
		Agente a = new Agente(mw,0,0);  // inserindo o agente no mundoWumpus 

		do {
			bc.manutencaoLista();
			p = a.getPercep();  //recebendo as percepções do agente da sala em que esta inserido
			mw.mostraMundo();
			posicao = a.getPosicaoAtual(); //recebendo a posição atual do agente
			if(p[2]&&p[5]&&p[6]) { // verifica se o agente, o brilho e o tesouro estão na mesma casa!
			   // Refazendo o caminho de volta
				camPercorrido = bc.getCaminhoPercorrido(); 
				posicao[0] = Integer.parseInt(camPercorrido.get(camPercorrido.size() - 1).substring(0,1));
				posicao[1] = Integer.parseInt(camPercorrido.get(camPercorrido.size() - 1).substring(2,3));
				a.mover(posicao[0],posicao[1],mw, p[2]);
				bc.tell(posicao[0],posicao[1]);
				mw.setTesouro(posicao[0], posicao[1]);
				camPercorrido = bc.desempilharCaminho();
				
			}else {
				//
				posicao = bc.ask(p, posicao[0], posicao[1]); //
				a.mover(posicao[0],posicao[1],mw, false);
				bc.tell(posicao[0],posicao[1]);
				camPercorrido = bc.getCaminhoPercorrido();
			}
			System.out.println("Continua 1-SIM/2-NÃO?");
			System.out.println(camPercorrido);
			
			mundo = mw.getMundo();
			/*
			if((mundo[posicao[0]][posicao[1]][6] == true) && (mundo[posicao[0]][posicao[1]][5] == true) && (mundo[posicao[0]][posicao[1]][2] == true)) {
				bc.setPosseTesouro(); // posse do tesouro recebe true!
				while(camPercorrido.size() > 0) {
					posicao[0] = Integer.parseInt(camPercorrido.get(camPercorrido.size() - 1).substring(0,1));
					posicao[1] = Integer.parseInt(camPercorrido.get(camPercorrido.size() - 1).substring(2,3));
					a.mover(posicao[0],posicao[1],mw);
					camPercorrido.remove(camPercorrido.size() - 1);
				}	
			}
			*/
			
		} while(teclado.nextInt()==1 && camPercorrido.size() > 0);
		mw.mostraMundo();
		System.out.println("FIM");
	}

}
