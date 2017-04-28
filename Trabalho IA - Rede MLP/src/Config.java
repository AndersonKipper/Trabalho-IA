
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Config {
	
	static long learningRate;
	static long Entrada;
	static long NeuronioCamOculta;
	static long Saida;

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
            Iterator<Long> iterator = layer.iterator();
            
            
            Entrada = (Long) layer.get(0);
            //System.out.println("*******" + Entrada + "*******"); //teste
            
            NeuronioCamOculta = (Long) layer.get(1);
            //System.out.println("*******" + CamadaOculta + "*******"); //teste
            
            Saida = (Long) layer.get(2);
            //System.out.println("*******" + Saida + "*******"); //teste
            
            JSONArray pesos = (JSONArray) jsonObject.get("weights");
            iterator = pesos.iterator();
            
            
            

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
    

}