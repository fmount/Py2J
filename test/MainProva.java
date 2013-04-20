package models;

public class MainProva {

	public static void main(String[] args){

		
		//PROVE TUPLA
		
		Tupla t1 = new Tupla("1, 2,3");
		Tupla t2 = new Tupla("4,5 , 6");
		
		t1.printTupla();
		t2.printTupla();
		
		Tupla tSum = new Tupla();
		tSum.sum(t1,t2);
		
		tSum.printTupla();
		
		Tupla tMult = new Tupla();
		tMult.mult(t1, 3);
		
		tMult.printTupla();
		
		((Tupla)tMult).subTupla( "[1:2]");

		
		System.out.println(" -------------- ");
		
		
		Tupla tt = new Tupla("1,2,3,4,5,6,7,8,9");
		((Tupla)tt.subTupla("[-2:-1]")).printTupla();

		
		//PROVE LISTA
		Object l1 = new GenericList("['ciao','pippo', 3]");
		((GenericList) l1).printGenericList();
		
		Object l3 = new GenericList("[4,'r', 12.45]");
		((GenericList) l3).printGenericList();
		
		Object prodotto = new GenericList();
		((GenericList) prodotto).sum(l1,l3);
		//((GenericList) prodotto).mult(l1,2);
		((GenericList) prodotto).printGenericList();
		((GenericList) prodotto).sum(prodotto,l3);
		//((GenericList) prodotto).mult(l1,2);
		((GenericList) prodotto).printGenericList();
		
		
		System.out.println("\n\n======= CON OBJECT ===========\n\n");
		
		Object l2 = l1; //assegnazione
		
		((GenericList) l2).printGenericList();
		
		//PROVE DIZIONARIO
		
		PDictionary d = new PDictionary("1:'ciao', 2: 'casa'");
		d.printPDictionary();
		
		System.out.println("===============================");
		d.insertNewObject("pippo", "new_value");
		d.printPDictionary();
		
		//Prove Object:
		
		
		
		System.out.println("======== alla fine di tutto ======" + "ciao" + 3);
		Object tmp_2 = new Tupla();
		((Tupla) tmp_2).resultOperationsTupla("2*2*(1,2) + (4,5) + (4,5)*2");
		((Tupla) tmp_2).printTupla();
		
		
		Object list = new GenericList(" 1, 2");
		list = new PDictionary();
		((PDictionary)list).insertNewObject("key", "value");
		((PDictionary)list).printPDictionary();
		
		Object f = new PDictionary();
		((PDictionary)f).insertNewObject("ciao","value");
		
		Object w = new Tupla("(1,2,3)");
		
		f = ((Tupla)w).subTupla("[0:1]");
		((Tupla) f).printTupla();
		
	 	Object LISTA = new GenericList("[1,2,\"pippo\"]");
	 	((GenericList) LISTA).printGenericList();
	 	
	 	Object c = new Tupla();
	 	((Tupla)c).resultOperationsTupla("8*(1,2,3,4,5,6)");
	 	((Tupla) c).printTupla();
	 	System.out.println("C: " + ((Tupla)c).returnTuplaValue());
	 	
	 	Object r = new PDictionary("{\"dic\":1}");
	 	((PDictionary)r).insertNewObject("\"altro\"",3);
	 	
	 	((PDictionary)r).printPDictionary();
	 	
	 	System.out.println("valori tupla: "+((Tupla)w).returnTuplaValue());
	 	
	 	System.out.println("dizionario:"+((PDictionary)r).returnPDictionaryValue());
	 	
	 	Object ob = provaF();
	 	System.out.println(((Tupla)ob).returnTuplaValue());
	 	
	 	
	 	String str_t = ((Tupla)ob).returnTuplaValue().replace("(|)", "");
	 	System.out.println( str_t );
	 	//str_t = "1,2,3";
	 	
	 	//Object tuplas = new Tupla("1" + " , " + "\"ciao\"" + " , " + "\"pippo\"" + " , " + "2.33333" + "," + ((Tupla)ob).returnTuplaValue() );
	 	Object tuplas = new Tupla("1" + " , " + "\"ciao\"" + " , " + str_t + " , " + "2.33333" );
	 	((Tupla)tuplas).printTupla();
	 	System.out.println( ((Tupla)tuplas).getSize() );
	 	
	 	Object altra_tup = new Tupla("9" + " , " + "8" + " , " + "7" + " , " + "6" + " , " + ((Tupla)ob).returnTupla());
	 	((Tupla)altra_tup).printTupla();
	 	
	 	
	 	//Object altro_dic = new PDictionary("{\"pippo\""+":"+u+"}");
	 	Object dict = new PDictionary("1" + ":" + "\"rosso\"" + " , " + "2" + ":" + "\"verde\"" + " , " + "3" + ":" + "\"blu\"");
	 	Object x = "0";
	 	if( ((String)"rosso")==((PDictionary)dict).getSigleValue(x.toString()).toString() ){
	 		System.out.println("OKKKKKKKKKKKKKKKKKK");
	 	}
	 	
	 	
	 	
	 	 Object rubrica = new PDictionary("\"Federica\""+":"+"34718187"+","+"\"Azzurra\""+":"+"34822122");

	 	 ((PDictionary)rubrica).insertNewObject("\"Francesca\"",33474347);
	 	((PDictionary)rubrica).insertNewObject(111,"\"Francesca\"");

	 	Integer ei = 111;
	 	 Object numero = ((PDictionary)rubrica).getSigleValue(ei);
	 	 System.out.println( ((PDictionary)rubrica).getSize() );
	 	 System.out.println( ((PDictionary)rubrica).returnPDictionaryValue() );
	 	 System.out.println( "NUmero: " + numero.toString());
	 	numero = ((PDictionary)rubrica).getSigleValue("\"Federica\"");
	 	System.out.println( "NUmero: " + numero.toString());
	}
	
	
	public static Object provaF(){
		
		
		return (new Tupla("(1,2,3)"));
		
	}
	
	
	 public void  altra_func(){
			Object tupla_tmpx =new Tupla("1,5");
		for(int index_x=0;index_x<2;index_x++){
		Object x = ((Tupla)tupla_tmpx).getObj(index_x);
		 		System.out.println("Daniela!");

		}	Object genericListy= new GenericList("0,1,2,3,4,5,6,7,8,9");
		for(int index_y = 0;index_y<10;index_y++){
		Object y = ((GenericList)genericListy).getObj(index_y);
		 		System.out.println("Francesco!");

		}
			Object genericListkk= new GenericList("1,2,3,4,5,6,7,8,9,10,11,12");
		for(int index_kk = 1;index_kk<13;index_kk++){
		Object kk = ((GenericList)genericListkk).getObj(index_kk);
		 		System.out.println("Marco!");

		}
		Object genericListzz= new GenericList("1,2,3,4,5,6,7,8,9,10,11,12,13,14");
		for(int index_zz = 1;index_zz<15;index_zz=index_zz+10){
		Object zz = ((GenericList)genericListzz).getObj(index_zz);
		 		System.out.println("Francesco!!XD");

		}
		}

	
}


