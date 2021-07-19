import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Persistencia {

	private String path;
	private Float valor;
	
	public Persistencia(String path) {
		this.path = path;
		valor = null;
	}
	
	
	
	public float recuperar() {
		if(valor != null) {
			return valor;
		}
		try {
		      File file = new File(path);
		      Scanner scanner = new Scanner(file);
		      if (scanner.hasNextLine()) {
		        String data = scanner.nextLine();
		        scanner.close();//aca tambien lo cierro porque con el return no llega abajo y no lo cierra
		        valor = Float.parseFloat(data);
		        return valor;
		      }
		      //si no tiene nada retorno 0;
		      scanner.close();
		    } catch (Exception e) {
		    	//si falla por alguna razon, por default retorno 0
		    	// dejo Exception, apra que, por polimorfismo caiga acá ante cualquier excepcion
		    }
		valor = 0f;
		// si estoy aca no hay un archivo valido, crearlo
		guardar(0);
		return 0;//por defecto está vacio :)	
		
	}
	
	public void guardar(float cantidad){
		
			File myObj = new File(path);
	      try {
	    	  myObj.createNewFile();
	    	  FileWriter myWriter = new FileWriter(path);
	          myWriter.write(cantidad+"");
	          myWriter.close();	    	
	      }catch(Exception e) {}
	      valor = cantidad;
	}
	
	
}


