package symbol;

import java.util.Iterator;

/*classe definita per eseguire un checking dinamico del tipo di variabile di cui progressivamente viene eseguito il parsing.
 * definita per eseguire un corretta associazione del tipo alla variabile, e di conseguenza un corretto inserimento nella
 * tabella dei simboli.
 * Per maggiori dettagli vedi relazione allegata
 * */

public class CheckType {

	public static String checkInt(Object a){
		try{
			Integer.parseInt(a.toString());
			return "Integer";
		}catch(NumberFormatException e){}
		return null;
	}
	
	public static String checkString(Object a){
		try{
			String.valueOf(a);
			return "String";
		}catch(Exception e){}
		return null;
	}
	
	public static String checkFloat(Object a){
		try{
			Float.parseFloat(a.toString());
			return "Float";
		}catch(Exception e){}
		return null;
	}
	
	public static String checkDouble(Object a){
		try{
			Double.parseDouble(a.toString());
			return "Double";
		}catch(Exception e){}
		return null;
	}
	
	public static String checkTupla(Object a){
		String x = a.toString();
		try{
			if((x.charAt(0)=='(' && x.charAt(x.length()-1)==')') || x.contains(",")){
				return "Tupla";
			}
		}catch(Exception e){}
		return null;
	}
	
	public static String checkGenericList(Object a){
		String x = a.toString();
		try{
			if(x.charAt(0)=='[' && x.charAt(x.length()-1)==']')
				return "GenericList";
		}catch(Exception e){}
		return null;
	}
	
	public static String checkDictionary(Object a){
		String x = a.toString();
		try{
			if(x.charAt(0)=='{' && x.charAt(x.length()-1)=='}' && x.contains(":"))
				return "PDictionary";
		}catch(Exception e){}
		return null;
	}
	
	public static String checkSet(Object a){
		String x = a.toString();
		try{
			if(x.contains("set"))
				return "PDictionary";
		}catch(Exception e){}
		return null;
	}
	
	
	public static String checkVariable(Object a){
		if(a.toString().matches("[a-zA-Z_][a-zA-Z0-9_]*"))
		{
			return "Object";
		}
		return null;
	}
	
	/**
	 * PRIORITY ORDER: 
	 * 
	 * 1)INTEGER
	 * 2)FLOAT
	 * 3)DOUBLE
	 * 4)LISTA
	 * 5)DIZIONARIO
	 * 6)SET
	 * 7)TUPLA
	 * 8)OBJECT/Variable
	 * 9)STRING
	 * 
	 */
	
	public static String check(Object a){
		
		try{
						
			if(checkInt(a)!=null){
				return checkInt(a);
			}
			if(checkDouble(a)!=null){
				return checkDouble(a);
			}
			if(checkGenericList(a)!=null){
				return checkGenericList(a);
			}
			if(checkDictionary(a)!=null){
				return checkDictionary(a);
			}
			if(checkSet(a)!=null){
				return checkSet(a);
			}
			if(checkTupla(a)!=null){
				return checkTupla(a);
			}
			if(checkVariable(a)!=null){
				return checkVariable(a);
			}
			if(checkString(a)!=null){
				return checkString(a);
			}
			
			
		}catch(Exception e){e.printStackTrace();}
		
		return "ERROR";
	}
	
	
	
	
	public static int getPriority(String a){
		if(a.equals("Integer"))
			return 1;
		if(a.equals("Float"))
			return 2;
		if(a.equals("Double"))
			return 3;
		if(a.equals("GenericList"))
			return 4;
		if(a.equals("PDictionary"))
			return 5;
		if(a.equals("Tupla"))
			return 6;
		if(a.equals("Object"))
			return 7;
		if(a.equals("String"))
			return 8;
		return -1;
	}
	


}
