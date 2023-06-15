import java.util.ArrayList;
import java.util.List;

public class BaseDeConhecimento {
	
	// mundo Wumpus conhecido (0: desconhecido; 1: seguro(brisa); -1: n�o seguro(brisa), 2: seguro(fedor), -2: n�o seguro(fedor), 3: seguro(brisa/fedor), -3: � seguro(brisa/fedor);
	int mundo[][][]; 
	boolean caminho[][];
	boolean movimentoPossivel[][];
	int caminhoPercorrido[][];
	int topo = 0;
	int cont = 0;
	boolean posseTesouro = false;
	
	List<String> percorrido = new ArrayList();
	
	
	
	public BaseDeConhecimento() {
		int i, j, k;
		mundo = new int[4][4][2];
		caminho = new boolean[4][4];
		movimentoPossivel = new boolean[6][6];
		//inicializa��o do mundo inicial conhecido
		
		caminhoPercorrido = new int[2][16];
		for(i=3; i>=0; i--) {	
			for(j=0; j<4; j++) {
				caminho[i][j] = false;				
			}
		}	
		
		for(i = 5; i>=0; i--) {	
			for(j = 0; j<=5; j++) {
				movimentoPossivel[i][j] = true; //inicializa��o da matriz de possiveis movimentos!
				if((i == 5) || (i == 0) || (j == 5) || (j == 0)) {
					movimentoPossivel[i][j] = false;
				}
			}
		}	
		mundo[0][0] = new int[]{2 , 2}; // ([0] = po�o/[1] = wumpus) 0-desconhecido, 1- possivel perigo, 2- certeza de ser seguro
		mundo[0][2] = new int[]{0,0};
		mundo[0][3] = new int[]{0,0};
		mundo[1][0] = new int[]{0,0};
		mundo[1][1] = new int[]{0,0};
		mundo[1][2] = new int[]{0,0};
		mundo[1][3] = new int[]{0,0};
		mundo[2][0] = new int[]{0,0};
		mundo[2][1] = new int[]{0,0};
		mundo[2][2] = new int[]{0,0};
		mundo[2][3] = new int[]{0,0};
		mundo[3][0] = new int[]{0,0};
		mundo[3][1] = new int[]{0,0};
		mundo[3][2] = new int[]{0,0};
		mundo[3][3] = new int[]{0,0};
				
		cont++;
	}
			
	
	public void setPosseTesouro() {
		this.posseTesouro = true;
	}
	
	public List<String> getCaminho(){
		return this.percorrido;
	}
	
	public int[] ask(boolean percep[], int i, int j) {
		int posicao[] = new int[2];
		boolean brisa = percep[0];
		boolean fedor = percep[1];
		boolean brilho = percep[2];
		boolean poco = percep[3];
		boolean Wumpus = percep[4];
		boolean tesouro = percep[5];
		

		percorrido.add(i + ","  + j); 

		caminho[i][j] = true; 
		
		//marcando as salas adjacentes como seguras ou � seguras p/ brisa!
		if(brisa) { // se tem brisa***
			if(movimentoPossivel[i + 2][j + 1] == true) {
				if(mundo[i + 1][j][0] != 2) {
					mundo[i + 1][j][0] = 1;
				}
				if(!fedor){ // se n�o tem fedor***
					mundo[i + 1][j][1] = 2; // recebe a certeza de ser seguro para monstro!
				}
			}
			
			if(movimentoPossivel[i][j + 1] == true) {  // se o movimento � possivel***
				if(mundo[i - 1][j][0] != 2) { // se situa��o � desconhecida***
					mundo[i - 1][j][0] = 1; //recebe a possibilidade de ter po�o
				}
				if(!fedor) { // se n�o tem fedor***
						mundo[i - 1][j][1] = 2; // recebe a certeza de ser seguro para monstro!
				}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {  // se o movimento � possivel***
				if(mundo[i][j + 1][0] != 2) { // se n�o � certeza ser seguro para po�o***
					mundo[i][j + 1][0] = 1; //recebe a possibilidade de ter po�o
				}
				if(!fedor) { // se n�o tem fedor***
					mundo[i][j + 1][1] = 2; // recebe a certeza de ser seguro para monstro!
				}
			}
			
			if(movimentoPossivel[i + 1][j] == true) {  // se o movimento � possivel***
				if(mundo[i][j - 1][0] != 2) {  // se n�o � certeza ser seguro para po�o***
					mundo[i][j - 1][0] = 1;  //recebe a possibilidade de ter po�o
				}
				if(!fedor) { // se n�o tem fedor
					mundo[i][j - 1][1] = 2; // recebe a certeza de n�o ter monstro!
				}
			}
			
		}else { // se n�o tem brisa 
			if(movimentoPossivel[i + 2][j + 1] == true) { // se nao tem brisa e se o movimento � possivel
					mundo[i + 1][j][0] = 2;  // recebe a certeza de ser seguro para po�o
					if(!fedor) { // se n�o tem fedor
						mundo[i + 1][j][1] = 2; //recebe a certeza de ser seguro para monstro! 
					}

			}
			
			if(movimentoPossivel[i][j + 1] == true) { // se movimento � possivel
					mundo[i - 1][j][0] = 2; // recebe a certeza de ser seguro para po�o
					if(!fedor) { // se n�o tem fedor
						mundo[i - 1][j][1] = 2; //recebe a certeza de ser seguro para monstro!
					}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) { // se movimento � possivel
					mundo[i][j + 1][0] = 2; // recebe a certeza de ser seguro para po�o!
					if(!fedor) { // se n�o tem fedor
						mundo[i][j + 1][1] = 2; // recebe a certeza de ser seguro para monstro!
					}
			}
			
			if(movimentoPossivel[i + 1][j] == true) { // se movimento � possivel
					mundo[i][j - 1][0] = 2; // recebe a certeza de ser seguro para po�o!
					if(!fedor) { // se n�o tem fedor
						mundo[i][j - 1][1] = 2; // recebe a certeza de ser seguro para monstro!

					}
			}
			
		}
		
		
		
		
		if(fedor) { // se tem fedor
			if(movimentoPossivel[i + 2][j + 1] == true) { // se movimento � possivel
				if(mundo[i + 1][j][1] != 2 ) { // se n�o � certeza ser seguro para monstro
					mundo[i + 1][j][1] = 1; // recebe a possibilidade de ter monstro
				}
				if(!brisa){ // se n�o tem brisa
					mundo[i + 1][j][0] = 2; // certeza de ser seguro para po�o
				}
						
			}
			 if(movimentoPossivel[i][j + 1] == true) { // se movimento � possivel
				 if(mundo[i - 1][j][1] != 2) { // se n�o � certeza ser seguro para monstro
					 mundo[i - 1][j][1] = 1; // possibilidade de ter monstro
				 }
					 if(!brisa) { // se n�o tem brisa
						 mundo[i - 1][j][0] = 2; // certeza de ser seguro para po�o!
					 }
			 }
			
			 if(movimentoPossivel[i + 1][j + 2] == true) { // se moviomento � possivel
				 if(mundo[i][j + 1][1] != 2) { // sen�o � certeza ser seguro para monstro!
					 mundo[i][j + 1][1] = 1; // possibilidade de ter monstro
				 }
				 if(!brisa) { // se n�o tem brisa
					 mundo[i][j + 1][0] = 2;// certeza de ser seguro para po�o
 			 
				 }
						
			 }
			
			 if(movimentoPossivel[i + 1][j] == true) { // se movimento � possivel
				 if(mundo[i][j - 1][1] != 2) { // se n�o � certeza ser seguro para monstro!
					 mundo[i][j - 1][1] = 1; 
				 }
				 if(!brisa) {
					mundo[i][j - 1][0] = 2 ;
				 }
		
			 }
			
		}else { // se n�o tem fedor 
			if(movimentoPossivel[i + 2][j + 1] == true) { // se movimento � possivel
					mundo[i + 1][j][1] = 2;
					if(!brisa) {
						mundo[i + 1][j][0] = 2; 
					}
			}
			
			if(movimentoPossivel[i][j + 1] == true) {
					mundo[i - 1][j][1] = 2;
					if(!brisa) {
						mundo[i - 1][j][0] = 2;
					}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {
					mundo[i][j + 1][1] = 2;
					if(!brisa) {
						mundo[i][j + 1][0] = 2;
					}

			}
			
			if(movimentoPossivel[i + 1][j] == true) {
					mundo[i][j - 1][1] = 2;
					if(!brisa) {
						mundo[i][j - 1][0] = 2; 
					}
			}
			
		}
		
		
		// marcando sala conhecida como segura!
		if(caminho[i][j] ==  true) {
			mundo[i][j][0] = 2; //0-desconhecido // 1- possibilidade de perigo // 2- certeza de ser seguro
			mundo[i][j][1] = 2;
		}
		
	

		if((movimentoPossivel[i + 1][j + 2]) && (caminho[i][j + 1] == false) && (mundo[i][j + 1][0] == 2) &&  (mundo[i][j + 1][1] == 2) ) { // se movimento e possivel e caminho ainda n�o foi percorrido!
			  // casa adjacente a direita � segura para po�o e para monstro
					posicao[0] = i; 
					posicao[1] = (j + 1);
					cont++;
				
		  
		}else {
			if((movimentoPossivel[i + 2][j + 1]) && (caminho[i + 1][j] == false) && (mundo[i + 1][j][0] == 2) && (mundo[i + 1][j][1] == 2) ) {	
				  
						posicao[0] = (i + 1);
						posicao[1] = j;
						cont++;
			}else {
				if((movimentoPossivel[i + 1][j]) && (caminho[i][j - 1] == false) && (mundo[i][j - 1][0] == 2) && (mundo[i][j - 1][1] == 2) ) {
				  
					
							posicao[0] = i;
							posicao[1] = (j - 1);
							cont++;
						
				  
				}else {
					if((movimentoPossivel[i][j + 1]) && (caminho[i - 1][j] == false) && (mundo[i - 1][j][0] == 2) && (mundo[i - 1][j][1] == 2) ) {
					 
		
								posicao[0] = (i - 1);
								posicao[1] = j;
								cont++;
							
					  
					}else { 
						// pedir pra Deus verificar e tirar a duvida
						posicao[0] = Integer.parseInt(percorrido.get(percorrido.size() - 2).substring(0,1));
						posicao[1] = Integer.parseInt(percorrido.get(percorrido.size() - 2).substring(2,3));
					}
				}
			}
		}
		
		
		return posicao;
	} 
	
	
	
	public void empilharCaminho(int posicaoL, int posicaoC) {
		caminhoPercorrido[0][topo] = posicaoL;
		caminhoPercorrido[1][topo] = posicaoC;
		topo++;
	}
	
	public List<String> desempilharCaminho(){
		percorrido.remove(percorrido.size()-1);

		return percorrido;
		
	}
	
	public void manutencaoLista() {
		int i, j, posicao = 0;
		String aux;
		boolean flag = false;
			if(percorrido.size() > 1) {
				
			
				for(i = 0; i < percorrido.size(); i++ ) {
					 for(j = i + 1; j < percorrido.size(); j++) {
						 if(percorrido.get(i).equals(percorrido.get(j))) {
							 posicao = i;
							 flag  = true;
						 }
					 }
				}
				if(flag) {
					for(i = posicao + 1; i <= percorrido.size() - 1; i++ ) {
						percorrido.remove(i);
					}
				}	
			}	
	}
	
	public List<String> getCaminhoPercorrido(){
		
			this.manutencaoLista();

		return percorrido;
	}
	
	public void tell(int i, int j) {
		caminho[i][j] = true;  //marca a sala como caminho percorrido
	}
}
