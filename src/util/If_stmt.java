package util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import symbol.SymbolTable;

public class If_stmt {

	public static String if_stmt_1(String t, String s,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
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
		
		if(condition!=null)
		{
			String result= tab+"if( "+condition+" ){\n"+s+tab+"}\n";
			if(level==0){
        		arrayMain.add(result);
            	return RESULT = "";
        	}
			else
				return RESULT = tab+"if( "+condition+" ){\n"+s+tab+"}\n";
		}
		else{
			//semanticError("INVALID CONDITION DEFINED!");
			if(level==0){
        		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
            	return RESULT = "";
        	}
			else
				return RESULT=tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
	
	
	public static String if_stmt_2(String t, String s1,String s2,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
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
		
		if(condition!=null)
		{
			String result= tab+"if( "+condition+" ){\n"+s1.toString()+tab+"}"+"\n"+tab+"else{\n"+s2.toString()+tab+"}\n";
			if(level==0){
        		arrayMain.add(result);
            	return RESULT = "";
        	}
			else
				return RESULT = tab+"if( "+condition+" ){\n"+s1.toString()+tab+"}"+"\n"+tab+"else{\n"+s2.toString()+tab+"}\n";
		}
		else{
			//semanticError("INVALID CONDITION DEFINED!");
			//TODO: Definire un logger..
			if(level==0){
        		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
            	return RESULT = "";
        	}
			else
				return RESULT=tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
	
	
	public static String if_stmt_3(String t, String s,String else_rec,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
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
		
		
		if(condition!=null)
		{
			String result=tab+"if( "+condition+" ){\n"+s.toString()+tab+"}"+"\n"+tab+else_rec.toString();
			if(level==0){
        		arrayMain.add(result);
            	return RESULT = "";
        	}
			else
				return RESULT = tab+"if( "+condition+" ){\n"+s.toString()+tab+"}"+"\n"+tab+else_rec.toString();
		}
		else{
			//semanticError("INVALID CONDITION DEFINED!");
			if(level==0){
        		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
            	return RESULT = "";
        	}
			else
				return RESULT=tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
	
	
	
	public static String if_stmt_4(String t, String s1,String else_rec,String s2,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
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
		
		if(condition!=null)
		{
			String result=tab+"if( "+condition+" ){\n"+s1.toString()+tab+"}"+"\n"+tab+else_rec.toString()+"\n"+tab+"else{\n"+s2.toString()+tab+"}\n";

			if(level==0){
        		arrayMain.add(result);
            	return RESULT = "";
        	}
			else
				return RESULT = tab+"if( "+condition+" ){\n"+s1.toString()+tab+"}"+"\n"+tab+else_rec.toString()+"\n"+tab+"else{\n"+s2.toString()+tab+"}\n";
		}
		else{
			//semanticError("INVALID CONDITION DEFINED!");
			//TODO: Definire il logger..
			if(level==0){
        		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
            	return RESULT = "";
        	}
			else
				return RESULT=tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
	
	
	
	public static String if_stmt_elif(String t, String s,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
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
			//System.out.println("CurrLev: "+currLev);
			//System.out.println("IndentLev: "+level);
			if(currLev > level){
				Enumeration ez = symTable.getSymTable(currLev).keys();
				while(ez.hasMoreElements()){
					String currKeyz= (String)ez.nextElement();
					if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
						//System.out.println("Removing - "+symTable.getSymbol(currLev, currKeyz).getName()+" - from ELSE1 function");
						symTable.getSymTable(currLev).remove(currKeyz);
					}										
				}
			}
		}
		
		if(condition!=null){
			String result=tab+"else if( "+condition+" ){\n"+s+tab+"}\n";
			return RESULT = result;
		}
		else{
			//TODO: definire il logger..
			//semanticError("INVALID CONDITION DEFINED!");
			return RESULT="//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
	
	
	
	public static String if_stmt_elif_2(String t, String s,String else_rec,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
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
				//System.out.println("CurrLev: "+currLev);
				//System.out.println("IndentLev: "+level);
				if(currLev > level){
					Enumeration ez = symTable.getSymTable(currLev).keys();
					while(ez.hasMoreElements()){
						String currKeyz= (String)ez.nextElement();
						if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
							//System.out.println("Removing - "+symTable.getSymbol(currLev, currKeyz).getName()+" - from ELSE2 function");
							symTable.getSymTable(currLev).remove(currKeyz);
						}										
					}
				}
			}
			
			if(condition!=null){
				String result=else_rec + tab+"else if( "+condition+" ){\n"+s+tab+"}\n";
				return RESULT = result;
			}
			else{
				//TODO: Definire logger..
				//semanticError("INVALID CONDITION DEFINED!");
				return RESULT= tab+"//INVALID CONDITION! IGNORING BODY;\n";
			}
	}
	
	
	/*
	
	public static String else_single(int level,String s,SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain){
		
		String RESULT="";
		String tab=" ";
		for(int i=0; i<level; i++){
			tab+="\t";
		}
		
		//Cancello gli eventuali scope di livello inferiore al mio
		Enumeration et = symTable.getScopeSym().keys();
		while(et.hasMoreElements()){
			int currLev = (Integer)et.nextElement();
			System.out.println("CurrLev: "+currLev);
			System.out.println("IndentLev: "+level);
			if(currLev > level){
				Enumeration ez = symTable.getSymTable(currLev).keys();
				while(ez.hasMoreElements()){
					String currKeyz= (String)ez.nextElement();
					if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
						System.out.println("Removing - "+symTable.getSymbol(currLev, currKeyz).getName()+" - from ELSE2 function");
						symTable.getSymTable(currLev).remove(currKeyz);
					}										
				}
			}
		}
		
		
		String result= "\n"+tab+"else{\n"+s+tab+"}\n";
		if(level==0){
    		arrayMain.add(result);
        	return RESULT = "";
    	}
		else
			return RESULT = result;

	}
	
	*/	
}
