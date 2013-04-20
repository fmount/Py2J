package resultJavaTest;

import models.Tupla;

public class B{

 	Object var2 = 20;
 	Object tupla = new Tupla("1" + " , " + "\"ciao\"" + " , " + "\"pippo\"" + " , " + "2.33333");
 	public void  metodo1_a(){
 		System.out.println("Metodo 'metodo1' della classe B");

	}
 	public void  metodo2_b(){
 		System.out.println("Metodo 'metodo2' della classe B");

	}
	public void  metodo3_b(){
 		System.out.println("Metodo 'metodo3' della classe B");

	}
	
	public B(Object var2){
		((B)this).var2 = var2;
	}

}
