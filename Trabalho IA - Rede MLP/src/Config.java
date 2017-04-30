
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Config {
	
	static long learningRate;
	static long Entrada;
	static long NeuronioCamOculta;
	static long Saida;
    static List<Long> PcamadaOculta = new ArrayList<Long>(); //Pesos da camada oculta
    static List<Long> PcamadaSaida = new ArrayList<Long>(); //Pesos da camada de saida

	Config(){

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("resources/config.js"));

            JSONObject jsonObject = (JSONObject) obj;
            //System.out.println(jsonObject); //teste
            
            learningRate = (Long) jsonObject.get("learningRate");
            /*
            System.out.println("learningRate: " + learningRate); //teste
            */
            
            JSONArray layer = (JSONArray) jsonObject.get("layers");
           
            
            
            Entrada = (Long) layer.get(0);
            //System.out.println("*******" + Entrada + "*******"); //teste
            
            NeuronioCamOculta = (Long) layer.get(1);
            //System.out.println("*******" + CamadaOculta + "*******"); //teste
            
            Saida = (Long) layer.get(2);
            //System.out.println("*******" + Saida + "*******"); //teste
            
            JSONArray pesos = (JSONArray) jsonObject.get("weights");
            Iterator<Long> iterator = pesos.iterator();

            int camada=0;		
            for(int i=0; i < pesos.size() ; i++){
            	JSONArray pesos1 = (JSONArray) pesos.get(i);
            	for(int y=0; y < pesos1.size() ; y++){
            		JSONArray pesos2 = (JSONArray) pesos1.get(y);
            		for(int t=0; t < pesos2.size() ; t++){
            			if(camada == 0) PcamadaOculta.add((Long) pesos2.get(t));
            			else PcamadaSaida.add((Long) pesos2.get(t));
            			
            			//System.out.print("*" + pesos2.get(t) + "*"); //teste
            		}
            		//System.out.print("|||");
            	}
            	camada++;
            	//System.out.print("\n");
            }
            
          //System.out.print("*" + PcamadaSaida.get(16) + "*"); //teste
            
            /*
            while (iterator.hasNext()) {
                System.out.println(":::::::::" + iterator.next());
            }
           */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
	
    public long getLearningRate(){
 		return learningRate;
 	}
     
    public long getEntrada(){
 		return Entrada;
 	}
    
    public long getSaida(){
 		return Saida;
 	}
    
    public long getNeuronioCamOculta(){
 		return NeuronioCamOculta;
 	}
    
    public List<Long> PcamadaOculta(){
 		return PcamadaOculta;
 	}
    
    public List<Long> PcamadaSaida(){
 		return PcamadaSaida;
 	}

}
