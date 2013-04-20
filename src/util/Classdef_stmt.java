package util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import symbol.SymbolTable;
import symbol.SymbolType;

public class Classdef_stmt {
	
	
	public static String mergeClasses(ArrayList<String> classes, String mark, SymbolTable symTable,Hashtable<String,String> InherSupport, ArrayList<String> ignore){
		String newCl="";
		ArrayList<Hashtable<String, ArrayList<SymbolType>>> m = new ArrayList<Hashtable<String, ArrayList<SymbolType>>>(); 
		for(int i=0;i<classes.size();i++){
				m.add(getClass(classes.get(i), symTable));
				getEntity(m.get(i),classes.get(i), symTable);
		}			
		//System.out.println("TO MERGE: "+m.toString());
		for(int i=0;i<classes.size();i++){
			newCl+=classes.get(i);
		}
		return merge(classes.size(),m,mark,symTable, InherSupport,ignore);
	}		
	
	public static String merge(int dim,ArrayList<Hashtable<String, ArrayList<SymbolType>>> toMerge,String mark,SymbolTable symTable, Hashtable<String,String> InherSupport, ArrayList<String> ignore){
		String str ="";
		String par="";
		String constructor="";
		ArrayList<SymbolType> name = new ArrayList<SymbolType>();
		ArrayList<SymbolType> attributes = new ArrayList<SymbolType>();
		ArrayList<SymbolType> methods = new ArrayList<SymbolType>();
		ArrayList<String> params = new ArrayList<String>();
		ArrayList<SymbolType> constr = new ArrayList<SymbolType>();
		
		//RECUPERO IL NOME DELLA NUOVA CLASSE DA ESTENDERE..
		String newClass="";
		for(int i=0;i<dim;i++){
			newClass = newClass+toMerge.get(i).get("class").get(0).getName();
			if(!toMerge.get(i).get("constructor").isEmpty())
				constr.add(toMerge.get(i).get("constructor").get(0));
			else
				constr.add(new SymbolType(toMerge.get(i).get("class").get(0).getName(),"constructor","",new ArrayList<String>(),"",1,toMerge.get(i).get("class").get(0).getName()));
		}
		symTable.putSymbol(0,newClass,new SymbolType(newClass,"class",null,null,null,0,newClass));
		str = "public class "+newClass+"{\n";
					
		//RECUPERO GLI ATTRIBUTI ASSOCIATI ALLE CLASSI..
		for(int i=0;i<toMerge.size();i++){
			for(int j=0;j<toMerge.get(i).get("attr").size();j++){
				//String currAttr = toMerge.get(i).get("attr").get(j).getOwner()+"_"+toMerge.get(i).get("attr").get(j).getName();
				String currAttr = toMerge.get(i).get("attr").get(j).getName();
				String currType = toMerge.get(i).get("attr").get(j).getType();
				String currValue = toMerge.get(i).get("attr").get(j).getValue();
				//String currOwner = toMerge.get(i).get("attr").get(j).getOwner();
				String currOwner = mark;
				
				if(symTable.checkEntity(currAttr+"#"+currOwner)){
				//	System.out.println("ATTRIBUTE DUPLICATE!");
				}else{
					if(currType.equals("Tupla")){
						currValue = currValue.replace("\"","\\\"");
					//	System.out.println("FOUND TUPLA WITH PARAMETERS: "+currValue);
					//	System.out.println("I PUT: \n"+currAttr+"#"+currOwner+"\n"+currType+"\n"+currValue+"\n"+currOwner+"\n");
						symTable.putSymbol(1,currAttr+"#"+currOwner,new SymbolType(currAttr,currType,currValue,null,null,1,currOwner));
						str = str+"Object"+" "+currAttr+"= new Tupla(\""+currValue+"\");\n";
					}
					else if(currType.equals("GenericList")){
						currValue = currValue.replace("\"","\\\"");
					//	System.out.println("FOUND GENERIC LIST WITH PARAMETERS: "+currValue);
					//	System.out.println("I PUT: \n"+currAttr+"#"+currOwner+"\n"+currType+"\n"+currValue+"\n"+currOwner+"\n");
						symTable.putSymbol(1,currAttr+"#"+currOwner,new SymbolType(currAttr,currType,currValue,null,null,1,currOwner));
						str = str+"Object"+" "+currAttr+"= new GenericList(\""+currValue+"\");\n";
					}
					else if(currType.equals("PDictionary")){
						currValue = currValue.replace("\"","\\\"");
					//	System.out.println("FOUND PDICTIONARY WITH PARAMETERS: "+currValue);
					//	System.out.println("I PUT: \n"+currAttr+"#"+currOwner+"\n"+currType+"\n"+currValue+"\n"+currOwner+"\n");
						symTable.putSymbol(1,currAttr+"#"+currOwner,new SymbolType(currAttr,currType,currValue,null,null,1,currOwner));
						str = str+"Object"+" "+currAttr+"= new PDictionary(\""+currValue+"\");\n";
					}
				else{
				//System.out.println("I PUT: \n"+currAttr+"#"+currOwner+"\n"+currType+"\n"+currValue+"\n"+currOwner+"\n");
				symTable.putSymbol(1,currAttr+"#"+currOwner,new SymbolType(currAttr,currType,currValue,null,null,1,currOwner));
				str = str+"Object"+" "+currAttr+"="+currValue+";\n";
				}
				}				
			}
		}
		
		//GENERO IL COSTRUTTORE DELLA CLASSE UNIONE RISULTANTE..
		String p = getConstructors(constr, ignore);
		String bod = mergeBod(constr,ignore);
		if(!bod.equals("")){
			constructor += "public "+newClass+"("+getConstructors(constr, ignore)+"){\n"+mergeBod(constr, ignore)+"\n}\n";
			//System.out.println("GENERATE CONSTRUCTOR: "+constructor);
			//Lo assegno alla stringa principale..
			str+=constructor;
		}else{
			constructor += "//Costruttore di default\n public "+newClass+"(){}\n";
			//System.out.println("GENERATE CONSTRUCTOR: "+constructor);
			//Lo assegno alla stringa principale..
			str+=constructor;
		}
					
		//RECUPERO I METODI ASSOCIATI ALLE CLASSI..
		for(int i=0;i<toMerge.size();i++){
			for(int j=0;j<toMerge.get(i).get("meth").size();j++){
			//	System.out.println("NOW WORKING WITH: "+toMerge.get(i).get("meth").toString());
				if(toMerge.get(i).get("meth").get(j).getParameters()!=null){
					String currMeth=toMerge.get(i).get("meth").get(j).getName();
					ArrayList<String> currParams = toMerge.get(i).get("meth").get(j).getParameters();
					String currReturn = toMerge.get(i).get("meth").get(j).getReturned();
					String currOwner = mark;
					String nparam=String.valueOf(currParams.size());
					if(symTable.checkEquivalentMethods(currMeth+"#"+nparam+"#"+currOwner)){
						//System.out.println("FOUND EQUIVALENT METHODS!");
					}else{
				//	System.out.println("I PUT: \n"+currMeth+"#"+nparam+"#"+currOwner+"\nTYPE: Function\n"+currParams+"\n"+currReturn+"\n"+currOwner+"\n");
					symTable.putSymbol(1,currMeth+"#"+nparam+"#"+currOwner,new SymbolType(currMeth,"function",null,currParams,currReturn,1,currOwner));
					String body = InherSupport.get(toMerge.get(i).get("meth").get(j).getName());				
					str = str+UtilsToSymTable.getFunctionModifier(toMerge.get(i).get("meth").get(j).getName())+toMerge.get(i).get("meth").get(j).getReturned()+" "+toMerge.get(i).get("meth").get(j).getName()+"("+getParams(toMerge.get(i).get("meth").get(j).getParameters())+"){\n"+body+"\n}\n";
					}
				}else{
					String currMeth=toMerge.get(i).get("meth").get(j).getName();
					String currReturn=toMerge.get(i).get("meth").get(j).getReturned();
					String currOwner= mark;
					String nparam=String.valueOf(0);
					if(symTable.checkEquivalentMethods(currMeth+"#"+nparam+"#"+currOwner)){
					//	System.out.println("FOUND EQUIVALENT METHODS!");
					}else{
				//	System.out.println("I PUT (NO PARAMS): \n"+currMeth+"#"+nparam+"#"+currOwner+"\n"+currReturn+"\n"+currOwner+"\n");
					symTable.putSymbol(1,currMeth+"#"+nparam+"#"+currOwner,new SymbolType(currMeth,"function",null,new ArrayList<String>(),currReturn,1,currOwner));
					String body = InherSupport.get(toMerge.get(i).get("meth").get(j).getName());
					str = str+UtilsToSymTable.getFunctionModifier(toMerge.get(i).get("meth").get(j).getName())+toMerge.get(i).get("meth").get(j).getReturned()+" "+toMerge.get(i).get("meth").get(j).getName()+"(){}\n";
					}
					}
			}
		}
		//Chiudo la classe..
		str = str+"}\n";
		WriteClassToFile.writeFile2(str,newClass);
		return str;	
		}
	
	
	/**
	 * Restituisce la lista di parametri (Sotto forma di stringa) da inserire nel costruttore unione..
	 * **/
	public static String getConstructors(ArrayList<SymbolType> constr, ArrayList<String> ignore){
			if(constr.isEmpty()) return "";
			String par="";
			ArrayList<String> tmp = new ArrayList<String>();
			for(int i=0;i<constr.size();i++){
				for(int j=0;j<constr.get(i).getParameters().size();j++){
					boolean b=false;
					for(int k=0;k<tmp.size();k++){
						if(tmp.get(k).equals(constr.get(i).getParameters().get(j))) b=true;
					}
					if(!b){
						tmp.add(constr.get(i).getParameters().get(j));
						par+=constr.get(i).getParameters().get(j)+",";
					}else{
						//System.out.println("Parameter "+constr.get(i).getParameters().get(j)+ "Ignored!");
						ignore.add(constr.get(i).getName());
					}
				}
			}
			return par.substring(0, par.length()-1);
	}
	
	/***
	 * Restituisce il corpo del nuovo costruttore frutto del merge dei precedenti..
	 * 
	 * */
	
	public static String mergeBod(ArrayList<SymbolType> constr, ArrayList<String> ignore){
		if(constr.isEmpty()) return "";
		String body="";
		//System.out.println("TO IGNORE: "+ignore.toString());
		if(ignore.isEmpty()){
			for(int i=0;i<constr.size();i++){
				body +=constr.get(i).getValue();
			}
			return body;
		}
		else{
			body = "";
			//TODO: INSERIRE LOGGER..
			//semanticAlert("Ignoring body: Semantic Error");
			return body;
		}
	}
	
	
	
	public static boolean checkEquivalentMethods(String currMeth,ArrayList<String> met){
		if(met.isEmpty()){
			 //	System.out.println("EQUIVALENT NOT FOUND!");
				return false;
		}
		for(int i=0;i<met.size();i++){
			//System.out.println("CHECK EQUIVALENT FOR: "+currMeth+"\n WITH: "+met.get(i));
				if(met.get(i).equals(currMeth)){
				 	//System.out.println("FOUND EQUIVALENT!");
				 	return true;
			}
		}
	//System.out.println("EQUIVALENT NOT FOUND!");
		return false;
	}
		
	public static String getParams(ArrayList<String> parameters){
		String par="";
		if(!parameters.isEmpty()){
			for(int k=0;k<parameters.size()-1;k++){
				////if(k==0){
					//par=par+parameters.get(k).substring(2,parameters.get(k).length())+", ";
				//}else
					par=par+parameters.get(k)+",";
			}
			par=par+parameters.get(parameters.size()-1);
			return par;
		}return par;
	}
	
	public static Hashtable<String, ArrayList<SymbolType>> getClass(String classes, SymbolTable symTable){
	 	Hashtable<String, ArrayList<SymbolType>> toMerge = new Hashtable<String, ArrayList<SymbolType>>();
		ArrayList<SymbolType> cl = new ArrayList<SymbolType>(); 
		Hashtable<String, SymbolType> a = symTable.getSymTable(0);
		Enumeration e = a.keys();
		while(e.hasMoreElements()){
			String key = (String)e.nextElement();
			if(a.get(key).getType().equals("class") && a.get(key).getName().equals(classes)){
			//	System.out.println("Class found:\n"+a.get(key));
				//Recupero attributi e metodi associati a questa classe.
				cl.add(a.get(key));
				toMerge.put("class",cl);
				//Inizializzo gli array per la classe trovata..
				toMerge.put("attr", new ArrayList<SymbolType>());
				toMerge.put("meth", new ArrayList<SymbolType>());
				toMerge.put("constructor", new ArrayList<SymbolType>());
			}
		}
		return toMerge;
	}
	
	public static void getEntity(Hashtable<String, ArrayList<SymbolType>> toMerge,String currClass, SymbolTable symTable){			
		for(int i=1;i<symTable.getSize();i++){
			Hashtable<String, SymbolType> a = symTable.getSymTable(i);
			Enumeration eps = a.keys();
			while(eps.hasMoreElements()){
				String key = (String)eps.nextElement();
				//System.out.println("CURRENT KEY: "+key);
				if(a.get(key).getOwner().equals(currClass) && a.get(key).getType().equals("function")){
					//	System.out.println("Entity found (FUNCTION):\n"+a.get(key));
						toMerge.get("meth").add(a.get(key));
				}
				else if(a.get(key).getOwner().equals(currClass) && a.get(key).getType().matches("String|Object|Integer|Float|GenericList|PDictionary|Tupla|Double")){
				//	System.out.println("Entity found (ATTR):\n"+a.get(key));
					if(checkduplicated(a.get(key),toMerge.get("attr")))
						toMerge.get("attr").add(a.get(key));
					else System.out.println("#Error: Attr duplicated!"); 
				}
				else if(a.get(key).getOwner().equals(currClass) && a.get(key).getType().equals("constructor")){
					//System.out.println("Entity found (CONSTR):\n"+a.get(key));
					toMerge.get("constructor").add(a.get(key));
				}
			}
		}
	}

	public static boolean checkduplicated(SymbolType currAttr,ArrayList<SymbolType> attr){
		for(int i=0;i<attr.size();i++){
			if(currAttr.getName().equals(attr.get(i).getName())){
					return false;
			}
		}
		return true;
	}		
	
		
	
	/**
	 * UTILITY METHODS
	 * 
	 * **/
	
	public static boolean checkPar(ArrayList<String> argv, int scope, String className, SymbolTable symTable){
		
		boolean ret = false;
		if(argv.isEmpty()){
			//System.out.println("NO PARAMS FOUND!");
			return false;
		}
		ArrayList<String> argz = UtilsToSymTable.tokenParameters(argv.get(0), ",");
		//System.out.println("I HAVE TO CHECK: "+argz.toString());
		if(!argz.isEmpty()){
			for(int i=0;i<argz.size();i++){
				//System.out.println("I ANALYZE SYMBOL: "+argz.get(i)+" IN SCOPE: "+scope+" FOR CLASS: "+className);
				if(symTable.getSymbol(scope,argz.get(i))!=null) ret = true;
				else ret=false;
			}
			//System.out.println("CHECK RESULT: "+ret);
			return ret;
			
		}
		return false;
	}
	
	
	/***
	 * 
	 * @param n
	 * @param s
	 * @param Indentation
	 * @param symTable
	 * @param ereditClassToFunctionInvocation
	 * @return
	 */
	
	public static String classdef_stmt_1(String n,String s, int Indentation, SymbolTable symTable, ArrayList<String> ereditClassToFunctionInvocation){
			String RESULT="";
			//by dany - mi serve
			ereditClassToFunctionInvocation.clear();
			String construct="";
		//	System.out.println(" IND CLASS: "+Indentation);
			//System.out.println("public class "+n+"{\n"+s+"\n}");
			symTable.putSymbol(Indentation,n,new SymbolType(n, "class", null,null, null, Indentation,n));
			//Recupero tutti gli elementi di livello 1 e li marco con l'owner
			Hashtable<String,SymbolType> level = new Hashtable<String,SymbolType>();
			level = symTable.getSymTable(1);
			if(!level.isEmpty()){
				Enumeration e = level.keys();
				//iterate through Hashtable keys Enumeration
				while(e.hasMoreElements()){
							String currKey = (String)e.nextElement();
							//System.out.println("#Owner: "+level.get(currKey).toString());
							if(level.get(currKey).getOwner()==null){
										level.get(currKey).setOwner(n);
										SymbolType tmp = level.get(currKey);
										if(tmp.getType().equals("function")){
												//System.out.println("I_HAVE: "+tmp.getName()+"#"+tmp.getParameters().size()+"");
												symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
											//	System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.remove(1,tmp.getName()+"#"+tmp.getParameters().size());
										}
										else if (tmp.getType().equals("constructor")){
												//System.out.println("I_HAVE: "+tmp.getName()+"#"+tmp.getParameters().size()+"");
												tmp.setName(n.toString());
											//	System.out.println("NEW NAME FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
											//	System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.remove(1,tmp.getName()+"#"+tmp.getParameters().size());
												String par="";
												
												for(int i=0;i<tmp.getParameters().size();i++){
													par+=tmp.getParameters().get(i).toString();
													if(i!=tmp.getParameters().size()-1)
														par+=",";
												}
												//System.out.println("PAR: " + par);
												//par+=tmp.getParameters().get(tmp.getParameters().size());
												construct+= "public "+tmp.getName()+"("+par+"){\n"+tmp.getValue()+"}";
										}
										else{
											symTable.putSymbol(1,tmp.getName()+"#"+n,tmp);
										//	System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
											symTable.remove(1,tmp.getName());
										}
								}
				}
			}
		//	System.out.println("Current SYM Dim: "+symTable.getSize());
			//Cancello gli eventuali scope di livello inferiore
			Enumeration et = symTable.getScopeSym().keys();
			while(et.hasMoreElements()){
				int currLev = (Integer)et.nextElement();
				if(currLev!=0 && currLev!=1){
					//System.out.println("Removing level: "+currLev);
					symTable.getScopeSym().remove(currLev);
				}
				if(currLev==1){
						Enumeration ez = symTable.getSymTable(currLev).keys();
						while(ez.hasMoreElements()){
							String currKeyz= (String)ez.nextElement();
							if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
										symTable.getSymTable(currLev).remove(currKeyz);
									//	System.out.println("Removing an L_1 element");
							}										
						}
				}
			}
		//	System.out.println("New SYM Dim: "+symTable.getSize());
			String body = s.toString().replace("self.","");
			String str="public class "+n+"{\n"+construct+"\n"+body+"\n}";
			//System.out.println("CLASS: "+str);
			//writeClass.writeFile2(str,n.toString());
			WriteClassToFile.writeFile2(str,n.toString());
			//RESULT = "public class "+n+"{\n"+construct+"\n"+s+"\n}";
			return RESULT = str;
	}
	
	public static String classdef_stmt_2(String n,String s, int Indentation, SymbolTable symTable, ArrayList<String> ereditClassToFunctionInvocation){
				String RESULT="";
				ereditClassToFunctionInvocation.clear();	
				//System.out.println(" IND CLASS: "+Indentation);
				//System.out.println("public class "+n+"{\n"+s+"\n}");
				symTable.putSymbol(Indentation,n,new SymbolType(n, "class", null,null, null, Indentation,n));
				String construct="";
				//Recupero tutti gli elementi di livello 1 e li marco con l'owner
				Hashtable<String,SymbolType> level = new Hashtable<String,SymbolType>();
				level = symTable.getSymTable(1);
				if(!level.isEmpty()){
					Enumeration e = level.keys();
					//iterate through Hashtable keys Enumeration
					while(e.hasMoreElements()){
								String currKey = (String)e.nextElement();
								//System.out.println("#Owner: "+level.get(currKey).toString());
								if(level.get(currKey).getOwner()==null){
											level.get(currKey).setOwner(n);
											SymbolType tmp = level.get(currKey);
											if(tmp.getType().equals("function")){
													//System.out.println("I_HAVE: "+tmp.getName()+"#"+tmp.getParameters().size()+"");
													symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
												//	System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
													symTable.remove(1,tmp.getName()+"#"+tmp.getParameters().size());
											}
											else if (tmp.getType().equals("constructor")){
													//System.out.println("I_HAVE: "+tmp.getName()+"#"+tmp.getParameters().size()+"");
													tmp.setName(n.toString());
												//	System.out.println("NEW NAME FOR ELEMENT:\n"+level.get(currKey).toString());
													symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
												//	System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
													symTable.remove(1,tmp.getName()+"#"+tmp.getParameters().size());
													String par="";
													
													for(int i=0;i<tmp.getParameters().size();i++){
														par+=tmp.getParameters().get(i).toString();
														if(i!=tmp.getParameters().size()-1)
															par+=",";
													}
													construct+= "public "+tmp.getName()+"("+par+"){\n"+tmp.getValue()+"}";
													
											}
											else{
												symTable.putSymbol(1,tmp.getName()+"#"+n,tmp);
											//	System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.remove(1,tmp.getName());
											}
											//SymbolType tmp = level.get(currKey); 
											//parser.symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
											//System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
									}
					}
				}
			//	System.out.println("Current SYM Dim: "+symTable.getSize());
				//Cancello gli eventuali scope di livello inferiore
				Enumeration et = symTable.getScopeSym().keys();
				while(et.hasMoreElements()){
					int currLev = (Integer)et.nextElement();
					if(currLev!=0 && currLev!=1){
					//	System.out.println("Removing level: "+currLev);
						symTable.getScopeSym().remove(currLev);
					}
					if(currLev==1){
							Enumeration ez = symTable.getSymTable(currLev).keys();
							while(ez.hasMoreElements()){
								String currKeyz= (String)ez.nextElement();
								if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
											symTable.getSymTable(currLev).remove(currKeyz);
							//				System.out.println("Removing an L_1 element");
								}										
							}
					}
				}
			//	System.out.println("New SYM Dim: "+symTable.getSize());
				String body = s.toString().replace("self.","");
				String str="public class "+n+"{\n"+construct+"\n"+body+"\n}";
				WriteClassToFile.writeFile2(str,n.toString());
				//RESULT = "public class "+n+"{\n"+construct+"\n"+s+"\n}";
				return RESULT = str;
	}
	
	public static String classdef_stmt_3(String n,String s,String t, int Indentation, SymbolTable symTable, ArrayList<String> ereditClassToFunctionInvocation, Hashtable<String,String> InherSupport, ArrayList<String> ignore){
			String RESULT="";
			ereditClassToFunctionInvocation.clear();
			String construct="";
		//	System.out.println(" IND CLASS: "+Indentation);
			//System.out.println("public class "+n+"{\n"+s+"\n}");
			symTable.putSymbol(Indentation,n,new SymbolType(n, "class", null,null, null, Indentation,n));
			//Recupero tutti gli elementi di livello 1 e li marco con l'owner
			Hashtable<String,SymbolType> level = new Hashtable<String,SymbolType>();
			level = symTable.getSymTable(1);
			if(!level.isEmpty()){
				Enumeration e = level.keys();
				//iterate through Hashtable keys Enumeration
				while(e.hasMoreElements()){
							String currKey = (String)e.nextElement();
							//System.out.println("#Owner: "+level.get(currKey).toString());
							if(level.get(currKey).getOwner()==null){
										level.get(currKey).setOwner(n);
										SymbolType tmp = level.get(currKey);
										if(tmp.getType().equals("function")){
												//System.out.println("I_HAVE: "+tmp.getName()+"#"+tmp.getParameters().size()+"");
												symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
										//		System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.remove(1,tmp.getName()+"#"+tmp.getParameters().size());
										}
										else if (tmp.getType().equals("constructor")){
												//System.out.println("I_HAVE: "+tmp.getName()+"#"+tmp.getParameters().size()+"");
												tmp.setName(n.toString());
										//		System.out.println("NEW NAME FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
										//		System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
												symTable.remove(1,tmp.getName()+"#"+tmp.getParameters().size());
												String par="";
												
												for(int i=0;i<tmp.getParameters().size();i++){
													par+=tmp.getParameters().get(i).toString();
													if(i!=tmp.getParameters().size()-1)
														par+=",";
												}
												construct+= "public "+tmp.getName()+"("+par+"){\n"+tmp.getValue()+"}";
										}
										else{
											symTable.putSymbol(1,tmp.getName()+"#"+n,tmp);
									//		System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
											symTable.remove(1,tmp.getName());
										}
										//SymbolType tmp = level.get(currKey); 
										//parser.symTable.putSymbol(1,tmp.getName()+"#"+tmp.getParameters().size()+"#"+n,tmp);
										//System.out.println("OWNER SETTED FOR ELEMENT:\n"+level.get(currKey).toString());
								}
				}
			}
		//	System.out.println("Current SYM Dim: "+symTable.getSize());
			//Cancello gli eventuali scope di livello inferiore
			Enumeration et = symTable.getScopeSym().keys();
			while(et.hasMoreElements()){
				int currLev = (Integer)et.nextElement();
				if(currLev!=0 && currLev!=1){
			//		System.out.println("Removing level: "+currLev);
					symTable.getScopeSym().remove(currLev);
				}
				if(currLev==1){
						Enumeration ez = symTable.getSymTable(currLev).keys();
						while(ez.hasMoreElements()){
							String currKeyz= (String)ez.nextElement();
							if(symTable.getSymbol(currLev, currKeyz).getOwner()==null){
										symTable.getSymTable(currLev).remove(currKeyz);
							//			System.out.println("Removing an L_1 element");
							}										
						}
				}
			}
		//	System.out.println("New SYM Dim: "+symTable.getSize());								
				
			ArrayList<String> classes = UtilsToSymTable.tokenParameters(t.toString(),",");
		//	System.out.println("Inherited from classes: "+classes.toString());
			System.out.println(mergeClasses(classes,n,symTable,InherSupport,ignore));
			String cons="";
			//RECUPERO IL NOME DELLA NUOVA CLASSE DA ESTENDERE..
			String newClass="";
			for(int i=0;i<classes.size();i++){
				newClass = newClass+classes.get(i);
			}
			if(symTable.getSymbol(0,newClass)!=null){
				String body = s.toString().replace("self.","");
				Hashtable<String,SymbolType> lev = new Hashtable<String,SymbolType>();
				lev = symTable.getSymTable(1);
				if(!level.isEmpty()){
					//Trovare il modo di recuperare il suo costruttore..
					if(symTable.getRegexConstructor(n,n)!=null){
						String pa = getParams(symTable.getRegexConstructor(n,n).getParameters());
						//System.out.println("XYZ"+level.toString());
						cons = "public "+symTable.getRegexConstructor(n,n).getName()+"("+pa+"){\n"+symTable.getRegexConstructor(n,n).getValue()+"\n}\n";
			//			System.out.println("paaa :" +symTable.getRegexConstructor(n,n).getParameters());
					}
					
				//	System.out.println("Constructorzzzz :" +cons);
				}
				
				//System.out.println("class "+n+" extends "+newClass+"{\n"+body+"\n}");
				String str="class "+n+" extends "+newClass+"{\n"+cons+body+"\n}";
				WriteClassToFile.writeFile2(str,n.toString()); 
				return RESULT = "class "+n+" extends "+newClass+"{\n"+cons+body+"\n}";
			}
			else{
				String body = s.toString().replace("self.","");
				//System.out.println("class "+n+" extends "+newClass+"{\n"+body+"\n}");
				String str=mergeClasses(classes,n, symTable, InherSupport,ignore)+"class "+n+" extends "+newClass+"{\n"+cons+body+"\n}";
				WriteClassToFile.writeFile2(str,n.toString()); 
				return RESULT = mergeClasses(classes,n, symTable, InherSupport,ignore)+"class "+n+" extends "+newClass+"{\n"+cons+body+"\n}";
			}	
		}
	
	
	

}
