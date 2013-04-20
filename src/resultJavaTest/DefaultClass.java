package resultJavaTest;

import models.GenericList;
import models.PDictionary;
import models.Tupla;

public class DefaultClass {
public static void main(String args[]){
 System.out.println("\n\n");

 DefaultClass.ereditFunction();

 System.out.println("\n\n");

 DefaultClass.assignamentFunction();

//prova commento!
//String st = ((PDictionary)dict).getSigleValue(x.toString()).toString().replace("\"", "");
 		//st = st.replace(" ", "");
}
public static void  assignamentFunction(){
 	Object dict = new PDictionary("0"+":"+"\"rosso\""+","+"1"+":"+"\"verde\""+","+"2"+":"+"\"blu\"");
 	Object tup = new Tupla("0" + "," + "1" + "," + "2");
 	Object u = 8;
		Object genericListx= new GenericList("0,1,2");
		for(int index_x = 0;index_x<3;index_x++){
		Object x = ((GenericList)genericListx).getObj(index_x);
			if( ((PDictionary)dict).getSigleValue(x.toString()).toString().replace("\"", "").equals(((String)"rosso")) ){
		 			System.out.println("rosso");
		 			Object list = new GenericList("\"rosa\"" + "," + "\"fucsia\"" + "," + "\"rosso fuoco\"");
		 			Object i = ((GenericList)list).getSize();
	while( ((Integer)i)<=((Integer)0) ){
			 				System.out.println(((GenericList)((GenericList)list).subGenericList(i.toString())).returnGenericListValue());
			 				System.out.println(((PDictionary)dict).getSigleValue("1"));
			 				System.out.println(((Tupla)((Tupla)tup).subTupla("1:3")).returnTuplaValue());
		 				i = ((Integer)i)-1;
			}
		}
		else{
		 			System.out.println("Sara' un altro colore...");
		 			Object altra_tup = new Tupla("9" + "," + "8" + "," + "7" + "," + "6" + "," + ((Tupla)tup).returnTupla());
	 			Object altro_dic = new PDictionary("\"pippo\""+":"+u.toString()+","+"\"cio\""+":"+"8");
		}

		}

}
public static void  ereditFunction(){
 	Object obj = new C();
 	Object e = ((C)obj).metodo1();
 	((C)obj).metodo1_a();
 	((C)obj).metodo3_b();
 	System.out.println(e.toString());
	DefaultClass.t();

}
public static void  t(){
 	System.out.println("pi");

}
}
