public class Neuronio {
	// Neuronio para 2 entradas

	private double w0; // pesos
	private double w1;
	private double w2;
    private double w3;
    private double w4;
    private double w5;
    private double w6;
    private double w7;
    private double w8;
    private double w9;
    private double w10;
    private double w11;
    private double w12;

	public double getW0() {
		return w0;
	}

	public double getW1() {
		return w1;
	}

	public double getW2() {
		return w2;
	}
        
        public double getW3() {
		return w3;
	}
        
        public double getW4() {
		return w4;
	}
        
        public double getW5() {
		return w5;
	}
        
        public double getW6() {
		return w6;
	}
        
        public double getW7() {
		return w7;
	}
        
        public double getW8() {
		return w8;
	}
        
        public double getW9() {
		return w9;
	}
        
        public double getW10() {
		return w10;
	}
        
        public double getW11() {
		return w11;
	}
        
        public double getW12() {
		return w12;
	}

	public double calculaV(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, int x10, int x11, int x12) {
		return w0 + w1 * x1 + w2 * x2 + w3 *x3 + w4 * x4 + w5 *x5 +w6 * x6 + w7 * x7 + w8 * x8 + w9 * x9 + w10 * x10 + w11 * x11 + w12 * x12;

	} // calcula o campo local induzido

	public int calculaY(int x1, int x2, int x3,  int x4, int x5, int x6, int x7, int x8, int x9, int x10, int x11, int x12) { // aplica a funcao
		double v = calculaV(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12);

		if (v >= 0)
			return 1;
		return 0;
	}

	public void setW0(double w0) {
		this.w0 = w0;
	}

	public void setW1(double w1) {
		this.w1 = w1;
	}

	public void setW2(double w2) {
		this.w2 = w2;
	}
        
        public void setW3(double w3) {
		this.w3 = w3;
	}
        
    	public void setW4(double w4) {
    		this.w4 = w4;
    	}
    	
    	public void setW5(double w5) {
    		this.w5 = w5;
    	}
    	
    	public void setW6(double w6) {
    		this.w6 = w6;
    	}

    	public void setW7(double w7) {
    		this.w7 = w7;
    	}
    	
    	
    	public void setW8(double w8) {
    		this.w8 = w8;
    	}
    	
    	public void setW9(double w9) {
    		this.w9 = w9;
    	}
    	
    	public void setW10(double w10) {
    		this.w10 = w10;
    	}
    	
    	public void setW11(double w11) {
    		this.w11 = w11;
    	}
    	
    	public void setW12(double w12) {
    		this.w12 = w12;
    	}
    	
	public String toString() {
		return "w0 = " + w0 + " w1= " + w1 + " w2= " + w2 + " w3= " + w3 + " w4= " + w4 + " w5= " + w5 + " w6= " + w6 + " w7= " + w7 + " w8= " + w8 + " w9= " + w9 + " w10= " + w10 + " w11= " + w11 + " w12= " + w12;
	}
}
