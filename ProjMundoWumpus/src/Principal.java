import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		boolean p[]; // = new boolean[7];
		int posicao[] = new int[2];

		MundoWumpus mw = new MundoWumpus();
		BaseDeConhecimento bc = new BaseDeConhecimento();  //
		mw.inicializaMundo();
		Agente a = new Agente(mw,0,0);  // inserindo o agente no mundoWumpus 

		do {
			p = a.getPercep();  //recebendo as percepções do agente da sala em que esta inserido
			mw.mostraMundo();
			posicao = a.getPosicaoAtual(); //recebendo a posição atual do agente
			posicao = bc.ask(p, posicao[0], posicao[1]); //
			a.mover(posicao[0],posicao[1],mw);
			bc.tell(posicao[0],posicao[1]);
			System.out.println("Continua 1-SIM/2-NÃO?");
		} while(teclado.nextInt()==1);
	}

}
