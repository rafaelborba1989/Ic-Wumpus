
public class BaseDeConhecimento {
	
	// mundo Wumpus conhecido (0: desconhecido; 1: seguro(brisa); -1: não seguro(brisa), 2: seguro(fedor), -2: não seguro(fedor), 3: seguro(brisa/fedor), -3: ñ seguro(brisa/fedor);
	boolean mundo[][][]; 
	boolean caminho[][];
	boolean movimentoPossivel[][];
	
	public BaseDeConhecimento() {
		int i, j, k;
		mundo = new boolean[4][4][3];
		caminho = new boolean[4][4];
		movimentoPossivel = new boolean[6][6];
		//inicialização do mundo inicial conhecido
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
		mundo[0][0] = new boolean[]{false,false,true}; // {brisa, fedor, conhecido}
		mundo[0][1] = new boolean[]{false,false,false};
		mundo[0][2] = new boolean[]{false,false,false};
		mundo[0][3] = new boolean[]{false,false,false};
		mundo[1][0] = new boolean[]{false,false,false};
		mundo[1][1] = new boolean[]{false,false,false};
		mundo[1][2] = new boolean[]{false,false,false};
		mundo[1][3] = new boolean[]{false,false,false};
		mundo[2][0] = new boolean[]{false,false,false};
		mundo[2][1] = new boolean[]{false,false,false};
		mundo[2][2] = new boolean[]{false,false,false};
		mundo[2][3] = new boolean[]{false,false,false};
		mundo[3][0] = new boolean[]{false,false,false};
		mundo[3][1] = new boolean[]{false,false,false};
		mundo[3][2] = new boolean[]{false,false,false};
		mundo[3][3] = new boolean[]{false,false,false};
				
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
		if(brisa) { //se tem brisa
			if(movimentoPossivel[i + 2][j + 1] == true) {
				if(mundo[i + 1][j][2] == false) {
					mundo[i + 1][j][0] = true;
					mundo[i + 1][j][2] = true; // perigo ou ausencia de perigo se torna conhecido!
				}
			}
			
			if(movimentoPossivel[i][j + 1] == true) {
				if(mundo[i - 1][j][2] == false) {
					mundo[i - 1][j][0] = true;
					mundo[i - 1][j][2] = true; // perigo ou ausencia de perigo se torna conhecido!
				}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {
				if(mundo[i][j + 1][2] == false) {
					mundo[i][j + 1][0] = true;
					mundo[i][j + 1][2] = true;
				}
			}
			
			if(movimentoPossivel[i + 1][j] == true) {
				if(mundo[i][j - 1][2] == false) {
					mundo[i][j - 1][0] = true;
					mundo[i][j - 1][2] = true;
				}
			}
			
		}else { // se não tem brisa e probabilidade de perigo é conhecida
			if(movimentoPossivel[i + 2][j + 1] == true) {
				if(mundo[i + 1][j][2] = true) {
					mundo[i + 1][j][0] = false;
				}
			}
			
			if(movimentoPossivel[i][j + 1] == true) {
				if(mundo[i - 1][j][2] = true) {
					mundo[i - 1][j][0] = false;
				}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {
				if(mundo[i][j + 1][2] = true) {
					mundo[i][j + 1][0] = false;
				}
			}
			
			if(movimentoPossivel[i + 1][j] == true) {
				if(mundo[i][j - 1][2] = true) {
					mundo[i][j - 1][2] = false;
				}
			}
			
		}
		
		
		
		
		if(fedor) {
			if(movimentoPossivel[i + 2][j + 1] == true) {
				if(mundo[i + 1][j][2] == false) {
					mundo[i + 1][j][1] = true;
					mundo[i + 1][j][2] = true; // perigo se torna conhecido!
				}
			}
			 if(movimentoPossivel[i][j + 1] == true) {
				 if(mundo[i - 1][j][2] == false)  {
						mundo[i - 1][j][1] = true;
						mundo[i - 1][j][2] = true; // perigo se torna conhecido!
					}
			 }
			
			 if(movimentoPossivel[i + 1][j + 2] == true) {
				 if(mundo[i][j + 1][2] == false) {
						mundo[i][j + 1][1] = true;
						mundo[i][j + 1][2] = true;
					}
			 }
			
			 if(movimentoPossivel[i + 1][j] == true) {
				 if(mundo[i][j - 1][2] == false) {
						mundo[i][j - 1][1] = true;
						mundo[i][j - 1][2] = true;
					}
			 }
			
		}else { // se não tem brisa e probabilidade de perigo é conhecida
			if(movimentoPossivel[i + 2][j + 1] == true) {
				if(mundo[i + 1][j][2] = true) {
					mundo[i + 1][j][1] = false;
				}
			}
			
			if(movimentoPossivel[i][j + 1] == true) {
				if(mundo[i - 1][j][2] = true) {
					mundo[i - 1][j][1] = false;
				}
			}
			
			if(movimentoPossivel[i + 1][j + 2] == true) {
				if(mundo[i][j + 1][2] = true) {
					mundo[i][j + 1][1] = false;
				}
			}
			
			if(movimentoPossivel[i + 1][j] == true) {
				if(mundo[i][j - 1][2] = true) {
					mundo[i][j - 1][1] = false;
				}
			}
			
		}
		
		if((!brisa) || (!fedor)) {
				if(movimentoPossivel[i + 2][j + 1]) {
					mundo[i + 1][j][2] = true;
				}
				if(movimentoPossivel[i][j + 1] == true) {
					mundo[i - 1][j][2] = true;
				}
				if(movimentoPossivel[i + 1][j + 2]) {
					mundo[i][j + 1][2] = true;			
				}
				if(movimentoPossivel[i + 1][j]) {
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
	
	public void tell(int i, int j) {
		caminho[i][j] = true;  //marca a sala como caminho percorrido
	}
}
