public class Neuronio {
	// Neuronio para 2 entradas

	private double w []; // pesos
    
	
	Neuronio(int entradas){
		this.w = new double[entradas];
		this.w[0] = 1;
	}
	
	public double getW(int pos) {
		return w[pos];
	}

	
	public double calculaV(double [] x) {
		
		double v = w[0];
		
		for(int i = 0; i < x.length; i++){
			v += w[i+1] * x[i];
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

	public void setW(double w, int pos) {
		this.w[pos] = w;
	}

	
    	
	public String toString(int pos) {		
	
		return "W" + pos + ": " + w[pos] + " " + (pos < w.length-1 ? toString(pos+1) : "\n");
				
	}
}
