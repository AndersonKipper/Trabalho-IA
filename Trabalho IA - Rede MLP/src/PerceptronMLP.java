import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

public class PerceptronMLP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Neuronio neuronioCamOcult[];
		Neuronio neuronioSaida[];
		Config config = new Config();

		int totalEntradas;
		double eta;
		double erroGeral = 0;

		totalEntradas = (int) config.getEntrada() + 1;
		int N = (int) config.getNeuronioCamOculta();
		neuronioCamOcult = new Neuronio[N];
		N = (int) config.getSaida();
		neuronioSaida = new Neuronio[N];
		eta = (int) config.getLearningRate();
		int epocas = 0;

		String arquivoCSV = "F:\\winequality-red2.csv";// "C:\\Users\\Daniel\\Desktop\\winequality-white.csv";//dargs[0];
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ";";
		try {

		//	br = new BufferedReader(new FileReader(arquivoCSV));
		//	br.readLine();

			int j = 0;

			for (int i = 0; i < neuronioCamOcult.length; i++) {
				neuronioCamOcult[i] = new Neuronio(totalEntradas);
			}

			for (int n2 = 0; n2 < neuronioSaida.length; n2++) {

				neuronioSaida[n2] = new Neuronio(neuronioCamOcult.length);
			}
			
			while(true){
			erroGeral = 0;
			epocas++;
			System.out.println("\n\nEpoca: " + epocas);
			
			br = new BufferedReader(new FileReader(arquivoCSV));
			br.readLine();
			//int nlinha = 0;
			while ((linha = br.readLine()) != null) {
				//System.out.println("\n\nLinha: " + nlinha++);
				
				String[] entradas = linha.split(csvDivisor);
				double[] x = new double[entradas.length];
				double y[] = new double[neuronioCamOcult.length];
				int ySaida[] = new int[neuronioSaida.length];
				int erro[] = new int[neuronioSaida.length];
				int d[] = new int[neuronioSaida.length];
				double gradienteSaida[] = new double[neuronioSaida.length];
				double gradienteOculta[] = new double[neuronioCamOcult.length];
				double gradienteSomatorio[] = new double[neuronioCamOcult.length];
				double ajusteW[] = new double[neuronioCamOcult.length];
				boolean correto = false;
				//while (true) {
					String saida = getBinario(Integer
							.parseInt(entradas[entradas.length - 1])); // converter
																		// para
																		// binario

					// System.out.print("\n" + saida + ": ");
					for (int c = 0; c < saida.length(); c++) {
						d[c] = Integer.parseInt(saida.charAt(c) + "");
						// System.out.print("\n" + c + " -- " + d[c]);
					}

					for (int n = 0; n <  neuronioCamOcult.length ; n++) {

						x[0] = 1;

						for (int i = 1; i < x.length; i++) {

							double c1 = (Double.parseDouble(entradas[i - 1]) - 0) / (67 - Double.parseDouble(entradas[i - 1])/0.076) ; // inventei

							x[i] = (c1);// normalizar
																			// entre
																			// 0
																			// e
																			// 1
							//System.out.print("\n" + i + " --  x = " + c1);

						}
						
						y[n] = neuronioCamOcult[n].calculaY(x);
						// neuronioCamOcult[n].setW(entrada, i + 1);
					}
					// Camada saida
					for (int n2 = 0; n2 < neuronioSaida.length; n2++) {
						//System.out.print("\n - Neuronio Saida");
						ySaida[n2] = neuronioSaida[n2].calculaY(y);
						// System.out.print("ysaida: " + ySaida[n2] + "\n");

						// fazer um IF testando retorno ySaida com binario
						// Os 4 precisam estaar corretos para passar? o if deve
						// ter os 4 ou um de cada vez?
						if (ySaida[n2] != d[n2]) {

							correto = false;

							// System.out.print(getBinarioDecimal(saidaBinaria));

							erro[n2] = d[n2] - ySaida[n2]; // ver se eh double

							// DESCOBRIR como calcular o gradiente
							gradienteSaida[n2] = neuronioSaida[n2]
									.calculaGradienteSaida(ySaida[n2], erro[n2]);

							// ajustar os pesos

							double deltaW[] = new double[neuronioCamOcult.length];

							for (int w = 0, g = 0; w < neuronioCamOcult.length; w++, g++) {
								// delta
								deltaW[w] = gradienteSaida[g] * eta;

								// ajuste
								double oldW = neuronioSaida[n2].getW(w);
								ajusteW[n2] = oldW + deltaW[w];
								neuronioSaida[n2].setW(ajusteW[n2], w);

								/*System.out.printf("\nPeso novo W[" + w + "] = "
										+ neuronioSaida[n2].getW(w) + "\n");*/
								if (g == neuronioSaida.length - 1)
									g = 0;
							}

						}
					}

					// calcular gradiente camada oculta

					double deltaW[] = new double[neuronioCamOcult.length];
					for (int n = 0; n < neuronioCamOcult.length; n++) {

						gradienteOculta[n] = neuronioCamOcult[n]
								.calculaGradienteOculta(x[n], gradienteSaida,
										ajusteW);

						deltaW[n] = gradienteOculta[n] * eta * x[n];

						neuronioCamOcult[n].setW(neuronioCamOcult[n].getW(n)
								+ deltaW[n], n);
					}

					int erroF = 0;
					for (int i = 0; i < neuronioSaida.length; i++) {
						
					   erroF = d[i] - ySaida[i];
						
						//System.out.printf("\n\nY = " + ySaida[i] + " d = " + d[i]);
					}
					
					erroGeral = erroGeral + abs(erroF);
					System.out.printf("\n\nerroGeral = " + erroGeral);
					

			}

			
			System.out.printf("\n\nSai");
			br.close();
			
			System.out.printf("\n\nerroGeral = " + erroGeral);
			if(erroGeral == 0) 
			break;
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
		
		while(true){
			
				
				String[] entradas = new String [totalEntradas];
				double[] x = new double[entradas.length];
				double y[] = new double[neuronioCamOcult.length];
				int ySaida[] = new int[neuronioSaida.length];
				int erro[] = new int[neuronioSaida.length];
				int d[] = new int[neuronioSaida.length];
				double gradienteSaida[] = new double[neuronioSaida.length];
				double gradienteOculta[] = new double[neuronioCamOcult.length];
				double ajusteW[] = new double[neuronioCamOcult.length];
				boolean sair = false;
				
				Scanner dados = new Scanner(System.in);
				
				System.out.println("\n\nDigite -100 para encerrar");
				for(int i = 1; i < x.length; i++){
					System.out.println("Digite X" + i + ": ");
					x[i] = dados.nextDouble();
					
					if(x[i] == -100){
						sair = true;
						break;
					}
				}
				 if(sair == true) break;

					for (int n = 0; n <  neuronioCamOcult.length ; n++) {

						x[0] = 1;

						for (int i = 0; i < x.length; i++) {

							double c1 = (x[i] - 0) / (67 - x[i]/0.076) ; // inventei

							x[i] = (c1);// normalizar
																			

						}
						
						y[n] = neuronioCamOcult[n].calculaY(x);
						// neuronioCamOcult[n].setW(entrada, i + 1);
					}
					// Camada saida
					for (int n2 = 0; n2 < neuronioSaida.length; n2++) {
						//System.out.print("\n - Neuronio Saida");
						ySaida[n2] = neuronioSaida[n2].calculaY(y);
						// System.out.print("ysaida: " + ySaida[n2] + "\n");

						// fazer um IF testando retorno ySaida com binario
						// Os 4 precisam estaar corretos para passar? o if deve
						// ter os 4 ou um de cada vez?
						if (ySaida[n2] != d[n2]) {

							

							// System.out.print(getBinarioDecimal(saidaBinaria));

							erro[n2] = d[n2] - ySaida[n2]; // ver se eh double

							// DESCOBRIR como calcular o gradiente
							gradienteSaida[n2] = neuronioSaida[n2]
									.calculaGradienteSaida(ySaida[n2], erro[n2]);

							// ajustar os pesos

							double deltaW[] = new double[neuronioCamOcult.length];

							for (int w = 0, g = 0; w < neuronioCamOcult.length; w++, g++) {
								// delta
								deltaW[w] = gradienteSaida[g] * eta;

								// ajuste
								double oldW = neuronioSaida[n2].getW(w);
								ajusteW[n2] = oldW + deltaW[w];
								neuronioSaida[n2].setW(ajusteW[n2], w);

								/*System.out.printf("\nPeso novo W[" + w + "] = "
										+ neuronioSaida[n2].getW(w) + "\n");*/
								if (g == neuronioSaida.length - 1)
									g = 0;
							}

						}
					}

					// calcular gradiente camada oculta

					double deltaW[] = new double[neuronioCamOcult.length];
					for (int n = 0; n < neuronioCamOcult.length; n++) {

						gradienteOculta[n] = neuronioCamOcult[n]
								.calculaGradienteOculta(x[n], gradienteSaida,
										ajusteW);

						deltaW[n] = gradienteOculta[n] * eta * x[n];

						neuronioCamOcult[n].setW(neuronioCamOcult[n].getW(n)
								+ deltaW[n], n);
					}

					String saida = "";
					for(int i = 0; i < ySaida.length; i++)
						saida += ySaida[i];
					
					System.out.printf("\n\nSaida = " + getBinarioDecimal(saida));
					

			}

		 System.out.printf("\n\nPrograma Encerrado.");
		

	}

	private static String getBinario(int num) {
		String binario = Integer.toBinaryString(num).toString();
		//System.out.print(binario + " -- " + num);
		return binario;
	}

	private static int getBinarioDecimal(String binario) {
		int num = 0, p = 1;
		for (int i = binario.length() - 1; i >= 0; i--) {
			num += Integer.parseInt(binario.charAt(i) + "") * p;
			// System.out.print(num + " -x- " + p + "\n");
			p = p * 2;
		}

		return num;
	}
}
