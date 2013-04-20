package util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import models.GenericList;

import symbol.CheckType;
import symbol.SymbolTable;
import symbol.SymbolType;

public class For_stmt {
	
	public static String for_stmt_1(String t, String s,String e, SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain, ArrayList<String> ereditClasses ){
		String RESULT = "";
		int level = scopeHash.get(e.toString()) -1 ;
		//System.out.println("e.toString(): " + e.toString() + " level for: " + level + " size hash: " + scopeHash.size());
		
	/*	Enumeration<String> k = scopeHash.keys();
		while(k.hasMoreElements()){
			String k2 = k.nextElement();
			System.out.println("for key: " + k2);
			Integer val = scopeHash.get(k2);
			System.out.println("for val: " + val);
		} */
		
		StringBuilder sb=new StringBuilder();
		String tab=" ";
		for(int i=0; i<level; i++){
			tab+="\t";
		}


		String e2 = e.toString().replace("self.","");

		String t2 = t.toString().replace("self.","");
		
		
		if((t.toString()).contains("(") && t.toString().contains(")")){
			 	ArrayList<String> arrayWithParents=UtilsToSymTable.tokenParameters(t.toString(),"(,)");
			 	ArrayList<String> arraySingleParam=new ArrayList<String>();
			 	String params="";
			 	for(int i=0;i<arrayWithParents.size();i++){
			 		if(!arrayWithParents.get(i).contains("(") || !arrayWithParents.get(i).contains(")")){
			 			arraySingleParam.add(arrayWithParents.get(i));
			 			params = params+arrayWithParents.get(i)+",";
			 		}
			 	}
			 	params = params.substring(0,params.length()-1);
			 	if(arraySingleParam.size()>0){
			 		params = params.replace("\"","\\\"");
			 		boolean esiste = UtilsToSymTable.isExist("tupla_tmp"+e2, level, symTable, ereditClasses);
			 		sb.append(tab);
			 		if(!esiste)
			 			sb.append("Object ");
			 		sb.append("tupla_tmp"+e2+" =new Tupla(\""+params+"\");\n");
			 		String s2="index_"+e2;
			 		sb.append(tab);
			 		sb.append("for(int "+s2+"=0;"+s2+" < "+arraySingleParam.size()+";"+s2+"++){\n");
			 	    //Inserire metodo della tupla che restituisce l'oggetto i-esimo
			 		/*
			 		boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
			 		if(flag)
			 			sb.append(tab+e.toString()+" = ((Tupla)tupla_tmp"+e2+").getObj("+s2+");\n"+tab);
			 		else
			 			sb.append(tab+"Object "+e.toString()+" = ((Tupla)tupla_tmp"+e2+").getObj("+s2+");\n"+tab);
			 	    */
			 		sb.append(tab+"\t"+"Object "+e.toString()+" = ((Tupla)tupla_tmp"+e2+").getObj("+s2+");\n");
			 	    sb.append(s.toString()+"\n");
			 	    sb.append(tab);
			 	    sb.append("}");
			 	   sb.append("\n");

				 	//inserisco la variabile temporanea creata dentro la tabella dei simboli
				 	symTable.putSymbol(level, "tupla_tmp"+e2, new SymbolType("tupla_tmp"+e2,"Tupla",params,null,null,level,null));
			 	}
			 
			 	
		}else if((t.toString()).contains("[") && t.toString().contains("]")){
			ArrayList<String> arrayWithParents=UtilsToSymTable.tokenParameters(t.toString(),"[,]");
			ArrayList<String> arraySingleParam=new ArrayList<String>();
			String params="";
			for(int i=0;i<arrayWithParents.size();i++){
				if(!arrayWithParents.get(i).contains("[") || !arrayWithParents.get(i).contains("]")){
					arraySingleParam.add(arrayWithParents.get(i));
					params = params+arrayWithParents.get(i)+",";
				}
			}
			params = params.substring(0,params.length()-1);
		
			if(arraySingleParam.size()>0){
				params = params.replace("\"","\\\"");
				boolean esiste = UtilsToSymTable.isExist("genericList"+e2, level, symTable, ereditClasses);
				sb.append(tab);
				if(!esiste)
		 			sb.append("Object ");
				sb.append("genericList"+e2+"= new GenericList(\""+params+"\");\n");
				String s2="index_"+e2;
				sb.append(tab);
				sb.append("for(int "+s2+"=0;"+s2+" < "+arraySingleParam.size()+";"+s2+"++){\n");
			    //Metodo della Lista che restituisce l'oggetto i-esimo
				/*
				boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
		 		if(flag)
		 			sb.append(tab+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+s2+");\n"+tab);
		 		else
		 			sb.append(tab+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+s2+");\n"+tab);
		 		*/
				sb.append(tab+"\t"+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+s2+");\n");
		 		sb.append(s.toString()+"\n");
		 		sb.append(tab);
			 	sb.append("}");
			 	sb.append("\n");
			 	
			 	//inserisco la variabile temporanea creata dentro la tabella dei simboli
			 	symTable.putSymbol(level, "genericList"+e2, new SymbolType("genericList"+e2,"GenericList",params,null,null,level,null));
			 	
			}
		}else if((CheckType.check(t2)).equals("Object")){
			//ho una variabile
			String type = UtilsToSymTable.getVariableType(t2,level,symTable, ereditClasses);
			if(type.equals("Tupla")){
				String value = UtilsToSymTable.getValue(t2,level,symTable, ereditClasses); 
				ArrayList<String> arrayValue=UtilsToSymTable.tokenParameters(value,",");
				value = value.replace("\"","\\\"");
				
				String insdieTupla = "((Tupla)"+t2+").getTupla()";
		 		
				boolean esiste = UtilsToSymTable.isExist("tupla_tmp"+e2, level, symTable, ereditClasses);
				sb.append(tab);
				if(!esiste)
		 			sb.append("Object ");
				sb.append("tupla_tmp"+e2+" =new Tupla("+insdieTupla+");\n");
		 		String s2="index_"+e2;
		 		sb.append(tab);
		 		sb.append("for(int "+s2+"=0;"+s2+" < ((Tupla)tupla_tmp"+e2+").getTupla().size();"+s2+"++){\n");
		 	    //Inserire metodo della tupla che restituisce l'oggetto i-esimo
		 		/*
		 		boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
		 		if(flag)
		 			sb.append(tab+e.toString()+" = ((Tupla)tupla_tmp"+e2+").getObj("+s2+");\n"+tab);
		 		else
		 			sb.append(tab+"Object "+e.toString()+" = ((Tupla)tupla_tmp"+e2+").getObj("+s2+");\n"+tab);
		 	    */
		 		sb.append(tab+"\t"+"Object "+e.toString()+" = ((Tupla)tupla_tmp"+e2+").getObj("+s2+");\n");
		 	    sb.append(s.toString()+"\n");
		 	    sb.append(tab);
		 	    sb.append("}");
		 	    sb.append("\n");
		 	    
		 	    //inserisco la variabile temporanea creata dentro la tabella dei simboli
			 	symTable.putSymbol(level, "tupla_tmp"+e2, new SymbolType("tupla_tmp"+e2,"Tupla",insdieTupla,null,null,level,null));
				
			}else if(type.equals("GenericList")){
				String value = UtilsToSymTable.getValue(t2,level,symTable, ereditClasses); 
				ArrayList<String> arrayValue=UtilsToSymTable.tokenParameters(value,",");
				value = value.replace("\"","\\\"");
				
				//((GenericList)t2).getGenericList()
				String insdieList = "((GenericList)"+t2+").getGenericList()";
				boolean esiste = UtilsToSymTable.isExist("genericList"+e2, level, symTable, ereditClasses);
				sb.append(tab);
				if(!esiste)
		 			sb.append("Object ");
				sb.append("genericList"+e2+"= new GenericList("+insdieList+");\n");
				String s2="index_"+e2;
				sb.append(tab);
				sb.append("for(int "+s2+"=0;"+s2+" < ((GenericList)genericList"+e2+").getGenericList().size();"+s2+"++){\n");
			    //Metodo della Lista che restituisce l'oggetto i-esimo
				/*
				boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
		 		if(flag)
		 			sb.append(tab+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+s2+");\n"+tab);
		 		else
		 			sb.append(tab+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+s2+");\n"+tab);
		 		*/
				sb.append(tab+"\n"+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+s2+");\n");
		 		sb.append(s.toString()+"\n");
		 		sb.append(tab);
			 	sb.append("}");
			 	sb.append("\n");
			 	
			 	
			 	//inserisco la variabile temporanea creata dentro la tabella dei simboli
			 	symTable.putSymbol(level, "genericList"+e2, new SymbolType("genericList"+e2,"GenericList",insdieList,null,null,level,null));
			 	

			}else{
				sb.append("//ERROR - In FOR condition - Does't Exist Tupla or GenericList");
			}
			
		}else{
			sb.append("//ERROR - In FOR condition");
		}
		
		
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
		

		if(level==0){
			//System.out.println("FOR LEVEL = 0");
    		arrayMain.add(sb.toString());
        	return RESULT = "";
    	}
		else
			return RESULT=sb.toString();

	}
	
	
	
	
	
	public static String for_stmt_2(String s, String r,String e, SymbolTable symTable, Hashtable<String,Integer> scopeHash, ArrayList<String> arrayMain , ArrayList<String> ereditClasses){
		String RESULT="";
		int level = scopeHash.get(e.toString()) -1 ;
		//System.out.println("e.toString(): " + e.toString() + " level for(con range): " + level + " size hash: " + scopeHash.size());
		
		String tab=" ";
		for(int i=0; i<level; i++){
			tab+="\t";
		}
		
		String e2 = e.toString().replace("self.","");
	
		StringBuilder sb=new StringBuilder();
		
		switch(ForRange.checkrange(r.toString())){
			
			case 2: 
					String[] p = r.toString().split("#");
					if(CheckType.check(p[0]).equals("Integer") && CheckType.check(p[1]).equals("Integer")){
						int res = Integer.parseInt(p[1]) - Integer.parseInt(p[0]);
						//System.out.println("RES: "+res);
						String index ="index_"+e2;
						boolean esiste = UtilsToSymTable.isExist("genericList"+e2, level, symTable, ereditClasses);
						sb.append(tab);
						if(!esiste)
				 			sb.append("Object ");
						sb.append("genericList"+e2+"= new GenericList(\""+ForRange.getRange(r.toString())+"\");\n");
						//sb.append("for(int "+index+" = "+p[0]+";"+index+ "<"+p[1]+";"+index+"++){\n"+tab);
						sb.append(tab);
						sb.append("for(int "+index+" = 0 ;"+index+ "<"+res+";"+index+"++){\n");
						
						/*
						boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
				 		if(flag)
				 			sb.append(e.toString()+" = ((GenericList)genericList"+e2+").getObj("+index+");\n"+tab);
				 		else
				 			sb.append("Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+index+");\n"+tab);
						*/
						sb.append(tab+"\t"+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+index+");\n");
						sb.append(s.toString()+"\n");
						sb.append(tab);
						sb.append("}\n");
						
						//inserisco la variabile temporanea creata dentro la tabella dei simboli
					 	symTable.putSymbol(level, "genericList"+e2, new SymbolType("genericList"+e2,"GenericList",ForRange.getRange(r.toString()),null,null,level,null));
					 	

					}else{
						
						String variableName = null;
						String startIndex = "0";
						String endIndex = "0";
						for(int i=0;i<2;i++){	
							if(!CheckType.check(p[i]).equals("Integer")){
								if(p[i].contains("Tupla")){
									variableName = p[i].replace("((Tupla)", "");
									variableName = variableName.replace(").getSize()",""); 
								}else if(p[i].contains("GenericList")){
									variableName = p[i].replace("((GenericList)", "");
									variableName = variableName.replace(").getSize()","");
								}
								
								if(variableName!=null){
									if(i==0)
										startIndex = UtilsToSymTable.getValueModels("getSize", null, null, variableName, level, symTable);
									else
										endIndex = UtilsToSymTable.getValueModels("getSize", null, null, variableName, level, symTable);
									
								}
								
							}else{
								if(i==0)
									startIndex = "0";
								else
									endIndex = String.valueOf(Integer.parseInt(p[1]) - Integer.parseInt(p[0]));
							}
						}
						
						String range = String.valueOf(startIndex) + "#" + String.valueOf(endIndex);
						
						String index ="index_"+e2;
						boolean esiste = UtilsToSymTable.isExist("genericList"+e2, level, symTable, ereditClasses);
						sb.append(tab);
						if(!esiste)
				 			sb.append("Object ");
						sb.append("genericList"+e2+"= new GenericList(\""+ForRange.getRange(range)+"\");\n");
						sb.append(tab);
						sb.append("for(int "+index+" = "+startIndex+";"+index+ "<"+endIndex+";"+index+"++){\n");
						/*
						boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
				 		if(flag)
				 			sb.append(e.toString()+" = ((GenericList)genericList"+e2+").getObj("+index+");\n"+tab);
				 		else
				 			sb.append("Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+index+");\n"+tab);
				 		*/
						sb.append(tab+"\n"+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+index+");\n");
				 		sb.append(s.toString()+"\n");
				 		sb.append(tab);
						sb.append("}\n");
						
						//inserisco la variabile temporanea creata dentro la tabella dei simboli
					 	symTable.putSymbol(level, "genericList"+e2, new SymbolType("genericList"+e2,"GenericList",ForRange.getRange(range),null,null,level,null));
					 	
					}
					
					
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
					
					if(level==0){
                		arrayMain.add(sb.toString());
                    	return RESULT = "";
                	}
					else
						return RESULT=sb.toString();
					
			case 3: 
				
					String[] pp = r.toString().split("#");
					
					String variableName = null;
					String startIndex = "0";
					String endIndex = "0";
					String incrementIndex = "0";
					for(int i=0;i<3;i++){	
						if(!CheckType.check(pp[i]).equals("Integer")){
							if(pp[i].contains("Tupla")){
								variableName = pp[i].replace("((Tupla)", "");
								variableName = variableName.replace(").getSize()",""); 
							}else if(pp[i].contains("GenericList")){
								variableName = pp[i].replace("((GenericList)", "");
								variableName = variableName.replace(").getSize()","");
							}
							
							if(variableName!=null){
								if(i==0)
									startIndex = UtilsToSymTable.getValueModels("getSize", null, null, variableName, level, symTable);
								else if(i==1)
									endIndex = UtilsToSymTable.getValueModels("getSize", null, null, variableName, level, symTable);
								else
									incrementIndex = UtilsToSymTable.getValueModels("getSize", null, null, variableName, level, symTable);
								
							}
							
						}else{
							if(i==0)
								//startIndex = "0";
								startIndex = pp[0];
							else if(i==1)
								//endIndex = String.valueOf(Integer.parseInt(pp[1]) - Integer.parseInt(pp[0]));
								endIndex = pp[1];
							else
								incrementIndex = pp[2];
						}
					}
					
					String range = "0" + "#" + endIndex + "#" + incrementIndex;
					//System.out.println("range:: "+range);

					String Index ="index_"+e2;
					
					boolean esiste = UtilsToSymTable.isExist("genericList"+e2, level, symTable, ereditClasses);
					sb.append(tab);
					if(!esiste)
			 			sb.append("Object ");
					sb.append("genericList"+e2+"= new GenericList(\""+ForRange.getRange(range)+"\");\n");
					//sb.append("for(int "+Index+" = "+startIndex+";"+Index+ "<"+endIndex+";"+Index+"="+Index+"+"+incrementIndex+"){\n"+tab);
					sb.append(tab);
					sb.append("for(int "+Index+" = "+startIndex+";"+Index+ "<"+endIndex+";"+Index+"="+Index+"+"+incrementIndex+"){\n");
					
					//Metodo della Lista che restituisce l'oggetto i-esimo
					/*
					boolean flag = UtilsToSymTable.isExist(e.toString().replace("self.",""),level, symTable);
			 		if(flag)
			 			sb.append(e.toString()+" = ((GenericList)genericList"+e2+").getObj("+Index+");\n"+tab);
			 		else
			 			sb.append("Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+Index+");\n"+tab);
					*/
					sb.append(tab+"\n"+"Object "+e.toString()+" = ((GenericList)genericList"+e2+").getObj("+Index+");\n");
					sb.append(s.toString()+"\n");
					sb.append(tab);
					sb.append("}\n");
					
					
					//inserisco la variabile temporanea creata dentro la tabella dei simboli
				 	symTable.putSymbol(level, "genericList"+e2, new SymbolType("genericList"+e2,"GenericList",ForRange.getRange(range),null,null,level,null));
					
					
					
					//Cancello gli eventuali scope di livello inferiore al mio
					Enumeration et2 = symTable.getScopeSym().keys();
					while(et2.hasMoreElements()){
						int currLev = (Integer)et2.nextElement();
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

					if(level==0){
                		arrayMain.add(sb.toString());
                    	return RESULT = "";
                	}
					else
						return RESULT=sb.toString();
					
					
			default:
				//TODO: Inserire logger..
				//semanticError("INVALID CONDITION DEFINED!");
				
				//Cancello gli eventuali scope di livello inferiore al mio
				Enumeration et3 = symTable.getScopeSym().keys();
				while(et3.hasMoreElements()){
					int currLev = (Integer)et3.nextElement();
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
				
				if(level==0){
            		arrayMain.add(tab+"//INVALID CONDITION! IGNORING BODY;\n");
                	return RESULT = "";
            	}
				else
					return RESULT=tab+"//INVALID CONDITION! IGNORING BODY;\n";
		}
	}
	
}
