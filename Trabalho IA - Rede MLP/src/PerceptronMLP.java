import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

public class PerceptronMLP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Neuronio neuronioCamOcult[];
		Neuronio neuronioSaida[];
		int entradas;
		
		/* APRENDER A LER o ARQUIVO -->
		 * // Realiza o Parse do JSON, criando um objeto JSONObject
			JSONObject obj = new JSONObject(json);
			
			// Busca o nodo "signo", conforme definido pelo trabalho			
			JSONObject objSigno = obj.getJSONObject("signo");
		
			for (String i : JSONObject.getNames(objSigno)){
			
				String trabalho = objSigno.getJSONObject(i).getString("trabalho");
				String dinheiro = objSigno.getJSONObject(i).getString("dinheiro");
				String amor = objSigno.getJSONObject(i).getString("amor");
				String saude = objSigno.getJSONObject(i).getString("saude");
				System.out.println(i + " \n " + trabalho + " - " + dinheiro + " - " + amor + " - "  + saude + "\n ------------------------");
				
			}
		 */		
		entradas = 12; //pegar do json -- 11 entradas + w0
		neuronioCamOcult = new Neuronio[7];
		neuronioSaida = new Neuronio[4];
		
		
		String arquivoCSV = "F:\\winequality-red.csv";//"C:\\Users\\Daniel\\Desktop\\winequality-white.csv";//dargs[0];
	    BufferedReader br = null;
	    String linha = "";
	    String csvDivisor = ";";
	    try {
	    	
	    	int L = 5000; //quantidade de linhas do arquivo, tem que ver como vai pegar depois
	    	int C = 12; //colunas do arquivo, ver como pegar depois
	    	
	    	//double entradas[][] = new double[C][L];
	    	//double saidaDesejada[] = new double[L];
	    	
	    	br = new BufferedReader(new FileReader(arquivoCSV));
	        br.readLine();
	        
	        int j=0;
	        
	        for(int i = 0; i < neuronioCamOcult.length; i++){
	        	neuronioCamOcult[i] = new Neuronio(entradas);
	        }
	        
	        while ((linha = br.readLine()) != null) {
	        	
	        	String[] pesos = linha.split(csvDivisor);
	        	double [] entrada = new double[pesos.length];
	        	double y[] = new double[neuronioCamOcult.length];
	        	double ySaida[] = new double[neuronioSaida.length];
	        	double erro, d;
	        	
				for (int n = 0; n < 1/*neuronioCamOcult.length*/; n++) {
					d = (Double.parseDouble(pesos[pesos.length -1])); //converter para binario
					
					for (int i = 0; i < pesos.length - 1; i++) { 
						
						/* c1' = (c1 - cMin) / (cMax - c/min) */
						
						
						entrada[i] = (Double.parseDouble(pesos[i]));//normalizar entre 0 e 1
						

						

					}
					
					y[n] = neuronioCamOcult[n].calculaY(entrada);
					//neuronioCamOcult[n].setW(entrada, i + 1);
					
					//Camada saida
					for(int n2 = 0; n2 < neuronioSaida.length; n2++){
					
						neuronioSaida[n2] = new Neuronio(y.length);
						ySaida[n2] = neuronioSaida[n2].calculaY(y); 
						
						
					}
					
					//fazer um IF testando retorno ySaida com binario
					
					
					System.out.print(neuronioCamOcult[n].toString(0));
				}
				
				
				
				
	        	break;//apagar
	        	
	        	
	        	
	        	
	        	/*
	        	int i;
	        	for(i=0; i < C-1; i++){
	        	   entradas[i][j] = Double.parseDouble(pesos[i]);
	        	}
	        	saidaDesejada[j] = Double.parseDouble(pesos[i]);
	        	j++;
	        	*/
	        }
	        	
	        
	        /* Imprime a matriz só para teste */
	       /* 	for(int i=0; i < C-1; i++){
	        		for(j=0; j < 20; j++){
	        	       System.out.print(entradas[i][j] + " \t ");
	        	    }
	        	    System.out.print("\n");
	           }
	           System.out.print("\n ---------Saída desejada -------------\n");
	           for(j=0; j < 20; j++){
	        	    System.out.print(saidaDesejada[j] + " \t ");
	           }*/

	        	
	        	
	        	
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

