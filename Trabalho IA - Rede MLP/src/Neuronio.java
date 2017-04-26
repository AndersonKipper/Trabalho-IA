public class Neuronio {
	// Neuronio para 2 entradas

	private double w []; // pesos
    
	
	Neuronio(int entradas){
		this.w = new double[entradas];
		//this.w[0] = 1;
	}
	
	public double getW(int pos) {
		return w[pos];
	}

	
	public double calculaV(double [] x) {
		
		double v = w[0];
		
		for(int i = 1; i < x.length; i++){
			System.out.print(w[i] + " - " + x[i] + "-> " + i + "\n" );
			v += w[i] * x[i];
		}
		
		return calculaQ(v);
		
	} 
	
	public double calculaQ(double v){
	
		return Math.pow(v, 3); //calcula Q'(vk)
	
	}
	

	public int calculaY(double [] x) { // aplica a funcao
		double v = calculaV(x);

		if (v >= 0)
			return 1;
		return 0;
	}
	
	public double calculaGradienteSaida(int v, double erro){
		return (3 * Math.sqrt(v) * erro); //faz a raiz como se fosse uma derivada
	}

	public double calculaGradienteOculta(int v, int [] g, int [] w){ //verificar
		int somatorio = 0;
		
		for(int i=0; i > g.length; i++){
			somatorio += g[i] * w[i];
		}
		
		return (3 * Math.sqrt(v) * somatorio);
	}
	public void setW(double w, int pos) {
		this.w[pos] = w;
	}

	
    	
	public String toString(int pos) {		
	
		return "W" + pos + ": " + w[pos] + " " + (pos < w.length-1 ? toString(pos+1) : "\n");
				
	}
}
