import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PerceptronMLP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Neuronio neuronio[] = new Neuronio[7];
		
		String arquivoCSV = "C:\\Users\\106289810\\Desktop\\winequality-white.csv";//dargs[0];
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    try {

	        
	        
	        for(int n = 0; n < neuronio.length; n++){
	        	
	        	neuronio[n] = new Neuronio();
	        	br = new BufferedReader(new FileReader(arquivoCSV));
		        br.readLine();
		        int i = 0;
			        while ((linha = br.readLine()) != null) {
		
			            String[] pesos = linha.split(csvDivisor);
			            
			            neuronio[n].setW0(1);
			            neuronio[n].setW1(Double.parseDouble(pesos[0]));
			            neuronio[n].setW2(Double.parseDouble(pesos[1]));
			            neuronio[n].setW3(Double.parseDouble(pesos[2]));
			            neuronio[n].setW4(Double.parseDouble(pesos[3]));
			            neuronio[n].setW5(Double.parseDouble(pesos[4]));
			            neuronio[n].setW6(Double.parseDouble(pesos[5]));
			            neuronio[n].setW7(Double.parseDouble(pesos[6]));
			            neuronio[n].setW8(Double.parseDouble(pesos[7]));
			            neuronio[n].setW9(Double.parseDouble(pesos[8]));
			            neuronio[n].setW10(Double.parseDouble(pesos[9]));
			            neuronio[n].setW11(Double.parseDouble(pesos[10]));
			            
			            
			            System.out.print("neuronio-" + n + " linha: " + i + "\tN0: " + neuronio[n].getW0() + " N1: " + neuronio[n].getW1()+ " N2: " + neuronio[n].getW2() + " N3: " + " " + neuronio[n].getW3() + " N4: " + neuronio[n].getW4() + " N5: " + neuronio[n].getW5() + "\n");
			            i++;
			        }
			        br.close();
	        }
	        
	        
	        

	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}

}
