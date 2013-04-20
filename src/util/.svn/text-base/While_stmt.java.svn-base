package util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import symbol.SymbolTable;

public class While_stmt {

	public static String while_stmt_1(String t,String s, SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
		String RESULT="";
		int level = scopeHash.get(t.toString()); 
		String tab=" ";
		for(int i=0; i<level; i++){
			tab+="\t";
		}
		
		String condition = WhileIfCondition.getStringBuilderCondition(t.toString(),symTable,scopeHash, ereditClasses);
		
		//Cancello gli eventuali scope di livello inferiore al mio
		Enumeration et = symTable.getScopeSym().keys();
		while(et.hasMoreElements()){
			int currLev = (Integer)et.nextElement();
			if(currLev > level){
				Enumeration ez = symTable.getSymTable(currLev).keys();
				while(ez.hasMoreElements()){
					String currKeyz= (String)ez.nextElement();
					if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
						symTable.getSymTable(currLev).remove(currKeyz);
					}										
				}
			}
		}

		//Eseguo l'analisi della condizione per verificare che i parametri esistano..
		if(condition!=null){
			String result= tab+"while( "+condition+" ){\n"+s+tab+"}\n";

			if(level==0){
        		arrayMain.add(result);
            	return RESULT = "";
        	}
			else
				return RESULT = tab+"while( "+condition+" ){\n"+s+tab+"}\n";
		}
		else{
		//	semanticError("INVALID CONDITION DEFINED!");
			//TODO: INSERIRE LOGGER..
			//System.out.println("INVALID CONDITION DEFINED!");
			if(level==0){
        		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
            	return RESULT = "";
        	}
			else
				return RESULT= tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
	
	
	public static String while_stmt_2(String t,String s1,String s2,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
		String RESULT="";
		int level = scopeHash.get(t.toString());
		String tab=" ";
		for(int i=0; i<level; i++){
			tab+="\t";
		} 
		ArrayList<String> array=UtilsToSymTable.tokenParameters(s2.toString(),"\t");
		StringBuilder s=new StringBuilder();
		for(int i=1;i<array.size();i++){
			s.append(tab);
			s.append(array.get(i));
		}
		
		String condition = WhileIfCondition.getStringBuilderCondition(t.toString(),symTable,scopeHash, ereditClasses);
		
		//Cancello gli eventuali scope di livello inferiore al mio
		Enumeration et = symTable.getScopeSym().keys();
		while(et.hasMoreElements()){
			int currLev = (Integer)et.nextElement();
			if(currLev > level){
				Enumeration ez = symTable.getSymTable(currLev).keys();
				while(ez.hasMoreElements()){
					String currKeyz= (String)ez.nextElement();
					if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
						symTable.getSymTable(currLev).remove(currKeyz);
					}										
				}
			}
		}
		
		
		if(condition!=null)
		{
			String result= tab+"while( "+condition+" ){\n"+s1+tab+"}"+"\n"+s+"\n";

			if(level==0){
        		arrayMain.add(result);
            	return RESULT = "";
        	}
			else
				return RESULT = tab+"while( "+condition+" ){\n"+s1+tab+"}"+"\n"+s+"\n";
		}
		else{
			//semanticError("INVALID CONDITION DEFINED!");
			//TODO: INSERIRE LOGGER..
			//System.out.println("INVALID CONDITION DEFINED!");
			if(level==0){
        		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
            	return RESULT = "";
        	}
			else
				return RESULT= tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
}
