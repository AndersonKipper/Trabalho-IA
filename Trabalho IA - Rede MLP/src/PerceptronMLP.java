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
		
		String arquivoCSV = "C:\\Users\\Daniel\\Desktop\\winequality-white.csv";//dargs[0];
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    try {
	    	
	    	int L = 5000; //quantidade de linhas do arquivo, tem que ver como vai pegar depois
	    	int C = 12; //colunas do arquivo, ver como pegar depois
	    	
	    	double entradas[][] = new double[C][L];
	    	double saidaDesejada[] = new double[L];
	    	
	    	br = new BufferedReader(new FileReader(arquivoCSV));
	        br.readLine();
	        
	        int j=0;
	        while ((linha = br.readLine()) != null) {
	        	
	        	String[] pesos = linha.split(csvDivisor);
	        	
	        	int i;
	        	for(i=0; i < C-1; i++){
	        	   entradas[i][j] = Double.parseDouble(pesos[i]);
	        	}
	        	saidaDesejada[j] = Double.parseDouble(pesos[i]);
	        	j++;
	        }
	        	
	        
	        /* Imprime a matriz só para teste */
	        	for(int i=0; i < C-1; i++){
	        		for(j=0; j < 20; j++){
	        	       System.out.print(entradas[i][j] + " \t ");
	        	    }
	        	    System.out.print("\n");
	           }
	           System.out.print("\n ---------Saída desejada -------------\n");
	           for(j=0; j < 20; j++){
	        	    System.out.print(saidaDesejada[j] + " \t ");
	           }

	        	
	        	
	        	
	        br.close();
	        

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

