
public class BaseDeConhecimento {
	
	// mundo Wumpus conhecido (0: desconhecido; 1: seguro(brisa); -1: não seguro(brisa), 2: seguro(fedor), -2: não seguro(fedor), 3: seguro(brisa/fedor), -3: ñ seguro(brisa/fedor);
	int mundo[][][]; 
	boolean caminho[][];
	boolean movimentoPossivel[][];
	int caminhoPercorrido[][];
	int topo = 0;
	
	public BaseDeConhecimento() {
		int i, j, k;
		mundo = new int[4][4][2];
		caminho = new boolean[4][4];
		movimentoPossivel = new boolean[6][6];
		//inicialização do mundo inicial conhecido
		
		caminhoPercorrido = new int[2][16];
		for(i=3; i>=0; i--) {	
			for(j=0; j<4; j++) {
				caminho[i][j] = false;				
			}
		}	
		
		for(i = 5; i>=0; i--) {	
			for(j = 0; j<=5; j++) {
				movimentoPossivel[i][j] = true; //inicialização da matriz de possiveis movimentos!
				if((i == 5) || (i == 0) || (j == 5) || (j == 0)) {
					movimentoPossivel[i][j] = false;
				}
			}
		}	
		mundo[0][0] = new int[]{2 , 2}; // ([0] = poço/[1] = wumpus) 0-desconhecido, 1- possivel perigo, 2- certeza de ser seguro, 3- certeza de perigo
		mundo[0][1] = new int[]{0,0};
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
				
		caminho[0][0] = true;
	}
			
	public int[] ask(boolean percep[], int i, int j) {
		int posicao[] = new int[2];
		boolean brisa = percep[0];
		boolean fedor = percep[1];
		boolean brilho = percep[2];
		boolean poco = percep[3];
		boolean Wumpus = percep[4];
		boolean tesouro = percep[5];
		
		caminho[i][j] = true; //marca a sala onde o agente esta como conhecida!
		
		
		//marcando as salas adjacentes como seguras ou ñ seguras p/ brisa!
		if(brisa) { // se tem brisa***
			if(movimentoPossivel[i + 2][j + 1] == true) { // se o movimento é possivel***
				if(mundo[i + 1][j][0] != 2) { // se não é certeza ser seguro para poço***
					mundo[i + 1][j][0] = 1; //possibilidade de ter poço
				}
				if(!fedor){ // se não tem fedor***
					mundo[i + 1][j][1] = 2; // certeza de ser seguro para monstro!
				}
			}
			
			if(movimentoPossivel[i][j + 1] == true) {  // se o movimento é possivel***
				if(mundo[i - 1][j][0] != 2) { // se situação é desconhecida***
					mundo[i - 1][j][0] = 1; //possibilidade de ter poço
				}
				if(!fedor) { // se não tem fedor***
						mundo[i - 1][j][1] = 2; // certeza de ser seguro ´para monstro!
				}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {  // se o movimento é possivel***
				if(mundo[i][j + 1][0] != 2) { // se não é certeza ser seguro para poço***
					mundo[i][j + 1][0] = 1; //possibilidade de ter poço
				}
				if(!fedor) { // se não tem fedor***
					mundo[i][j + 1][1] = 2; // certeza de ser seguro para monstro!
				}
			}
			
			if(movimentoPossivel[i + 1][j] == true) {  // se o movimento é possivel***
				if(mundo[i][j - 1][0] != 2) {  // se não é certeza ser seguro para poço***
					mundo[i][j - 1][0] = 1;  //possibilidade de ter poço
				}
				if(!fedor) { // se não tem fedor
					mundo[i][j - 1][1] = 2; // certeza de não ter monstro!
				}
			}
			
		}else { // se não tem brisa 
			if(movimentoPossivel[i + 2][j + 1] == true) { // se movimento é possivel
					mundo[i + 1][j][0] = 2;  // certeza de ser seguro para poço
					if(!fedor) {
						mundo[i + 1][j][1] = 2; //certeza de ser seguro para monstro! 
					}

			}
			
			if(movimentoPossivel[i][j + 1] == true) { // se movimento é possivel
					mundo[i - 1][j][0] = 2; // certeza de ser seguro para poço
					if(!fedor) {
						mundo[i - 1][j][1] = 2; // certeza de ser seguro para monstro!
					}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) { // se movimento é possivel
					mundo[i][j + 1][0] = 2; // certeza de ser seguro para poço!
					if(!fedor) { // se não tem fedor
						mundo[i][j + 1][1] = 2; // certeza de ser seguro para monstro!
					}
			}
			
			if(movimentoPossivel[i + 1][j] == true) { // se movimento é possivel
					mundo[i][j - 1][0] = 2; // certeza de ser seguro para poço!
					if(!fedor) {
						mundo[i][j - 1][1] = 2; // certeza de ser seguro para monstro!

					}
			}
			
		}
		
		
		
		
		if(fedor) { // se tem fedor
			if(movimentoPossivel[i + 2][j + 1] == true) { // se movimento é possivel
				if(mundo[i + 1][j][1] != 2 ) { // se não é certeza ser seguro para monstro
					mundo[i + 1][j][1] = 1; // possibilidade de ter monstro
				}
				if(!brisa){ // se não tem brisa
					mundo[i + 1][j][0] = 2; // certeza de ser seguro para poço
				}
						
			}
			 if(movimentoPossivel[i][j + 1] == true) { // se movimento é possivel
				 if(mundo[i - 1][j][1] != 2) { // se não é certeza ser seguro para monstro
					 mundo[i - 1][j][1] = 1; // possibilidade de ter monstro
				 }
					 if(!brisa) { // se não tem brisa
						 mundo[i - 1][j][0] = 2; // certeza de ser seguro para poço!
					 }
				 }
						
			 }
			
			 if(movimentoPossivel[i + 1][j + 2] == true) { // se moviomento é possivel
				 if(mundo[i][j + 1][1] != 2) {
					 mundo[i][j + 1][1] = 1;
				 }
				 if(!brisa) {
						 mundo[i][j + 1][0] = 2;
					 }
				 }
						
			 }
			
			 if(movimentoPossivel[i + 1][j] == true) {
				 if(mundo[i][j - 1][2] == false) {
					 mundo[i][j - 1][1] = true;
					 mundo[i][j - 1][2] = true;
				 }else {
					 if((mundo[i][j - 1][1] == false) && (!brisa)) {
						 mundo[i][j - 1][0] = false;
					 }
				 }
						
			 }
			
		}else { // se não tem brisa e probabilidade de perigo é conhecida
			if(movimentoPossivel[i + 2][j + 1] == true) {
					mundo[i + 1][j][1] = false;
					mundo[i + 1][j][2] = true;

			}
			
			if(movimentoPossivel[i][j + 1] == true) {
					mundo[i - 1][j][1] = false;
					mundo[i - 1][j][2] = true;

			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {
					mundo[i][j + 1][1] = false;
					mundo[i][j + 1][2] = true;

			}
			
			if(movimentoPossivel[i + 1][j] == true) {
					mundo[i][j - 1][1] = false;
					mundo[i][j - 1][2] = true;

			}
			
		}
		
		
		// marcando sala conhecida como segura!
		if(caminho[i][j] ==  true) {
			mundo[i][j][0] = false;
			mundo[i][j][1] = false;
			mundo[i][j][2] = true;
		}
		
		//mundo[i][j][0] == false -- sem perigo de poço
		//mundo[i][j][1] == false -- sem perigo de monstro
		//mundo[i][j][2] == false -- situação ñ conhecido
		//mundo[i][j][2] == true -- situação conhecida 



		if(movimentoPossivel[i + 1][j + 2]) {
			if( (mundo[i][j + 1][0] == false) && (mundo[i][j + 1][1] == false) && (mundo[i][j + 1][2] == true) ) {
				posicao[0] = i;
				posicao[i] = (j + 1);
				caminho[i][j + 1] = true;
				
			}
		}else {
			if(movimentoPossivel[i + 2][j + 1]) {	
				if( (mundo[i + 1][j][0] == false) && (mundo[i + 1][j][1] == false) && (mundo[i + 1][j][2] == true) ) {
				
					posicao[0] = (i + 1);
					posicao[i] = j;
					caminho[i + 1][j] = true;
				}
			}else {
				if(movimentoPossivel[i + 1][j]) {
					if( (mundo[i][j - 1][0] == false) && (mundo[i][j - 1][1] == false) && (mundo[i][j - 1][2] == true) ) {
					
						posicao[0] = i;
						posicao[i] = (j - 1);
						caminho[i][j -1] = true;
					}
				}else {
					if(movimentoPossivel[i][j + 1]) {
						if( (mundo[i - 1][j][0] == false) && (mundo[i - 1][j][1] == false) && (mundo[i - 1][j][2] == true) ) {
						
							posicao[0] = (i - 1);
							posicao[i] = j;
							caminho[i - 1][j] = true;
						}
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
	
	public int[] desempilharCaminho(){
		int vetor[] = new int[2];
		vetor[0] = caminhoPercorrido[0][topo - 1];
		vetor[1] = caminhoPercorrido[1][topo - 1];
		
		return vetor;
		
	}
	
	public void tell(int i, int j) {
		caminho[i][j] = true;  //marca a sala como caminho percorrido
	}
}
