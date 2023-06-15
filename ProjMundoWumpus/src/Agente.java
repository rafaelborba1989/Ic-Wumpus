
public class Agente {
	int iAtual, jAtual;
	boolean percep[]; //[brisa, fedor, brilho]
	
	public Agente(MundoWumpus mw, int iInicial, int jInicial) {
		iAtual = iInicial;
		jAtual = jInicial;
		percep = new boolean[7]; // vetor de percepções do agente?
		mw.inserirAgente(iAtual,jAtual);  // mundando para true a posição 6 do vetor "mundo" que representa a presença do agente
		this.setPercep(mw, false);
	}
	
	public int[] getPosicaoAtual() {  // retorna a posição atual do agente
		int posAtual[] = new int[2];
		posAtual[0] = iAtual;
		posAtual[1] = jAtual;
		return posAtual;
	}
	
	public void setPosAtual(int i, int j) {
		iAtual = i;
		jAtual = j;
	}
	
	public boolean[] getPercep() {
		return percep;
	}
	
	public void setPercep(MundoWumpus mw, boolean brilho) {
		int i;
		boolean[] p = mw.getPercepcoesSala(iAtual, jAtual);
		for(i=0; i<7; i++) {
			percep[i] = p[i];  // recebendo os valores boleanos relativos ao conjunto de percepções do agente da sala em que o mesmo foi inserido!
		}
		if(brilho)
			percep[2] = percep[5] = brilho; //levando o tesouro
	}

	public void mover(int iNovo, int jNovo, MundoWumpus mw, boolean brilho) {
		mw.atualizarAgente(iAtual, jAtual, iNovo, jNovo);
		this.setPosAtual(iNovo,jNovo);
		this.setPercep(mw, brilho);
	}
	

}
