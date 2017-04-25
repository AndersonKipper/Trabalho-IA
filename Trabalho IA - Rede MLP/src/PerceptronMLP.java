import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

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
	        	int ySaida[] = new int[neuronioSaida.length];
	        	int erro[] = new int[neuronioSaida.length];
	        	int d[] = new int[neuronioSaida.length];
	        	
	        	
				for (int n = 0; n < 1/*neuronioCamOcult.length*/; n++) {
					String saida = getBinario(Integer.parseInt(pesos[pesos.length -1])); //converter para binario
					
					//System.out.print("\n" + saida + ": ");
				    for(int c = 0; c < saida.length(); c++){
				    	d[c] = Integer.parseInt(saida.charAt(c)+"");
				    	//System.out.print("\n" + c + " -- " + d[c]);
				    }
							
					entrada[0] = 1;
					
					for (int i = 1; i < pesos.length; i++) { 
						
						/* c1' = (c1 - cMin) / (cMax - c/min) */
						
						
						entrada[i] = (Double.parseDouble(pesos[i-1]));//normalizar entre 0 e 1
						System.out.print("\n" + i + " -- ");

						

					}
					System.out.print(" saiu ");
					y[n] = neuronioCamOcult[n].calculaY(entrada);
					//neuronioCamOcult[n].setW(entrada, i + 1);
					
					//Camada saida
					for(int n2 = 0; n2 < neuronioSaida.length; n2++){
					
						neuronioSaida[n2] = new Neuronio(y.length);
						ySaida[n2] = neuronioSaida[n2].calculaY(y); 
						//System.out.print("ysaida: " + ySaida[n2] + "\n");
						
					  
						//fazer um IF testando retorno ySaida com binario
						//Os 4 precisam estaar corretos para passar? o if deve ter os 4 ou um de cada vez?
						if(ySaida[n2] == d[n2]){
							System.out.print("foi\n");
						}else{
							System.out.print("nao deu\n" + ySaida[n2] + " -> ");
							
							//System.out.print(getBinarioDecimal(saidaBinaria));
							
							erro[n2] = d[n2] - ySaida[n2]; //ver se eh double
							
							//DESCOBRIR como calcular o gradiente
							//neuronioSaida[n2].calculaGradienteSaida(v, erro);
						}
						
					}
					
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
	
	private static String getBinario(int num){
		String binario = Integer.toBinaryString(num).toString();
		System.out.print(binario + " -- " + num);
		return binario;
	}
	
	private static int getBinarioDecimal(String binario){
		int num = 0, p = 1;
		for(int i = binario.length()-1 ; i >= 0; i-- ){
			num += Integer.parseInt(binario.charAt(i) + "") * p;
			//System.out.print(num + " -x- " + p + "\n");
			p = p * 2;
		}
		
		return num;
	}
}

