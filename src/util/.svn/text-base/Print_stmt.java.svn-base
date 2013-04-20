package util;

import java.util.ArrayList;
import java.util.Hashtable;

import symbol.*;

public class Print_stmt {

	public static String print_stmt(String t, int level, SymbolTable symTable, ArrayList<String> arrayMain, ArrayList<String> ereditClasses){
		String RESULT = "";
		StringBuilder sb = new StringBuilder();
	    
	    //ArrayList<String> testlistWithouthVirgola = UtilsToSymTable.tokenParameters(t.toString(),",");
		String all = t.toString();
		String newParameters = all.replace("self.","");
		
		String newSenzaSelf = UtilsToSymTable.replaceCharInsideParents(newParameters, ',');
		//System.out.println("FOLLIA: "+ newSenzaSelf);
		
		String newConSelf = UtilsToSymTable.replaceCharInsideParents(all, ',');
		//System.out.println("FOLLIA -newConSelf: "+ newConSelf);
		
		ArrayList<String> testlistWithouthVirgola_with_self=UtilsToSymTable.tokenParameters( newConSelf, ",");
		ArrayList<String> testlistWithouthVirgola=UtilsToSymTable.tokenParameters( newSenzaSelf, ",");
	    
		//UtilsToSymTable.printArrayList(testlistWithouthVirgola, "testlistWithouthVirgola");
		
	    boolean flag = true;
	    
	    for(int i = 0; i < testlistWithouthVirgola.size(); i++){
	    	
	    	String type = CheckType.check(testlistWithouthVirgola.get(i));
	    	//System.out.println("TYPE - PRINT: " +type);
	    	
	    	if(type.equals("Object")){
	    		//System.out.println("ma lo vede come un OBJECT? ");
	    		//verifico che esiste
	    		if(UtilsToSymTable.isExist(testlistWithouthVirgola.get(i),level, symTable, ereditClasses)){
		    		String variableType = UtilsToSymTable.getVariableType(testlistWithouthVirgola.get(i),level,symTable, ereditClasses);
		    		
		    		if(variableType.equals("Tupla")){
		    			sb.append("((Tupla)"+testlistWithouthVirgola_with_self.get(i)+").returnTuplaValue()");
		    		}else if(variableType.equals("GenericList")){
		    			sb.append("((GenericList)"+testlistWithouthVirgola_with_self.get(i)+").returnGenericListValue()");
		    		}else if(variableType.equals("PDictionary")){
		    			sb.append("((PDictionary)"+testlistWithouthVirgola_with_self.get(i)+").returnPDictionaryValue()");
		    		}else if(variableType.equals("Object")){
		    			sb.append(testlistWithouthVirgola_with_self.get(i)+".toString()");
		    		}
		    		else{
		    			sb.append(testlistWithouthVirgola_with_self.get(i));
		    		}
	    		}else{
	    			
	    			flag = false;
	    		}
	    		
	    		
	    	}else if(type.equals("String")){
	    		
	    		if(testlistWithouthVirgola.get(i).contains("\"") && !testlistWithouthVirgola.get(i).contains("(") && !testlistWithouthVirgola.get(i).contains(")")){
	    			//System.out.println("STRING - PRINT: " );
    				//STRINGA SEMPLICE
    				sb.append(testlistWithouthVirgola_with_self.get(i));
	    		}
	    		else if(testlistWithouthVirgola.get(i).contains("[") && testlistWithouthVirgola.get(i).contains("]")){
	    			//lista
	    			//potrebbe essere l[3] oppure l[0:3]
	    			
	    			ArrayList<String> withouthParents = UtilsToSymTable.tokenParameters(testlistWithouthVirgola_with_self.get(i),"[]");
	    			boolean isE = UtilsToSymTable.isExist(withouthParents.get(0),level, symTable, ereditClasses);
	    			if(isE){
	    				ArrayList<String> withouthDuePunti = UtilsToSymTable.tokenParameters(withouthParents.get(1),":");
	    				boolean isObject = false;
	    				for(int h = 0; h<withouthDuePunti.size(); h++){
	    					String type_2 = CheckType.check(withouthDuePunti.get(h));
	    					if(type_2.equals("Object"))
	    						isObject = true;
	    				}
	    				
	    				// NB:
	    				//se ho: l[1]-> mi restituisce un solo elemento! 
    					//se ho: l[1:2] -> mi restituisce una tupla!
	    				if(isObject){
	    					String varTYPE = UtilsToSymTable.getVariableType(withouthParents.get(0),level,symTable, ereditClasses);
	    					if(withouthParents.get(1).contains(":")){
	    						//caso: l[i:x]
		    					if(varTYPE.equals("GenericList"))
		    						sb.append("((GenericList)((GenericList)"+withouthParents.get(0)+").subGenericList("+withouthParents.get(1)+".toString())).returnGenericListValue()");
		    					else if(varTYPE.equals("PDictionary"))
		    						sb.append("((PDictionary)"+withouthParents.get(0)+").getSigleValue("+withouthParents.get(1)+".toString())");
		    					else if(varTYPE.equals("Tupla"))
		    						sb.append("((Tupla)((Tupla)"+withouthParents.get(0)+").subTupla("+withouthParents.get(1)+".toString())).returnTuplaValue()");
	    					}else{
	    						//caso: l[i]
		    					if(varTYPE.equals("GenericList"))
		    						sb.append("((GenericList)"+withouthParents.get(0)+").subGenericList("+withouthParents.get(1)+".toString())");
		    					else if(varTYPE.equals("PDictionary"))
		    						sb.append("((PDictionary)"+withouthParents.get(0)+").getSigleValue("+withouthParents.get(1)+".toString())");
		    					else if(varTYPE.equals("Tupla"))
		    						sb.append("((Tupla)"+withouthParents.get(0)+").subTupla("+withouthParents.get(1)+".toString())");
	    					}
	    				}
	    				else{
	    					String varTYPE = UtilsToSymTable.getVariableType(withouthParents.get(0),level,symTable, ereditClasses);
	    					if(withouthParents.get(1).contains(":")){
	    						//caso: l[0:2]
		    					if(varTYPE.equals("GenericList"))
		    						sb.append("((GenericList)((GenericList)"+withouthParents.get(0)+").subGenericList(\""+withouthParents.get(1)+"\")).returnGenericListValue()");
		    					else if(varTYPE.equals("PDictionary"))
		    						sb.append("((PDictionary)"+withouthParents.get(0)+").getSigleValue(\""+withouthParents.get(1)+"\")");
		    					else if(varTYPE.equals("Tupla"))
		    						sb.append("((Tupla)((Tupla)"+withouthParents.get(0)+").subTupla(\""+withouthParents.get(1)+"\")).returnTuplaValue()");
	    					}else{
	    						//caso: l[1]
		    					if(varTYPE.equals("GenericList"))
		    						sb.append("((GenericList)"+withouthParents.get(0)+").subGenericList(\""+withouthParents.get(1)+"\")");
		    					else if(varTYPE.equals("PDictionary"))
		    						sb.append("((PDictionary)"+withouthParents.get(0)+").getSigleValue(\""+withouthParents.get(1)+"\")");
		    					else if(varTYPE.equals("Tupla"))
		    						sb.append("((Tupla)"+withouthParents.get(0)+").subTupla(\""+withouthParents.get(1)+"\")");
	    					}
	    				}
	    			}
	    			else{
	    				sb.append("//Error - List in Print not declared!\n");
	    			}
	    		}else{
	    			//gestione funzioni o istanze di classi
	    			//System.out.println("GESTIONE FUNZIONI O ISTANZE DI CLASSI - PRINT\n");
	    			
	    			ArrayList<String> a = UtilsToSymTable.tokenParameters(testlistWithouthVirgola.get(i),".");
	    			
	    			if(a.size()>1){
	    				//c'è il punto
	    				
	    				boolean isAvailable = UtilsToSymTable.isExist(a.get(0), level, symTable, ereditClasses);
    					if(isAvailable){
    						String className = UtilsToSymTable.getVariableType(a.get(0), level, symTable, ereditClasses);
	    					if(a.get(1).contains("(") && a.get(1).contains(")")){
		    					//p.funz(...)
	    						ArrayList<String> array=UtilsToSymTable.tokenParameters(a.get(1),"();");
	    						int numFunctionParam = 0;
    					        if(array.size()>1){
    					                ArrayList<String> params = UtilsToSymTable.tokenParameters(array.get(1),"#");
    					                numFunctionParam = params.size();
    					        }
    					        //System.out.println("numFunctionParam: "+ numFunctionParam);
    					        boolean funcOK = UtilsToSymTable.isCorrectFunctionInvocation(array.get(0),String.valueOf(numFunctionParam),className, level,symTable);
    					        if(funcOK){
    					        	String args = "";
    				        		if(array.size()>1)
    				        			args = array.get(1).replace("#",",");
    					        	if(UtilsToSymTable.functionHasReturn(array.get(0), String.valueOf(numFunctionParam), className, level, symTable)){
    					        		sb.append("(("+className+")"+a.get(0)+")."+array.get(0)+"("+args+").toString()");
    					        	}else{
	    	    						sb.append("//ERROR on print - FUNZIONE INVOCATA NON RITORNA NULLA\n");
	    	    					}
    					        }else{
    	    						sb.append("//ERROR on print - FUNZIONE INVOCATA NON ESISTENTE\n");
    	    					}
		    					
		    				}else{
		    					//p.attr
		    					//p.attr#className = testlistWithouthVirgola.get(i)
		    					boolean attrOK = UtilsToSymTable.isCorrectClassAttributeUsed(testlistWithouthVirgola.get(i), className, level, symTable);
		    					if(attrOK){
		    						//System.out.println("TUTTO OK per attrOK");
		    						if(className.equals("Object") && a.size()>1){
		    							className = UtilsToSymTable.getClassNameOfAttribute(a.get(1),a.get(0) ,level, symTable);
		    						}
		    						//if classname!=null
		    						if(className!=null){
			    						String nomeInterno = "(("+className+")"+a.get(0)+")."+a.get(1);
			    						
			    						String variableType = UtilsToSymTable.getVariableType(testlistWithouthVirgola.get(i),level,symTable, ereditClasses);
			    			    		
			    						if(variableType!=null){
				    			    		if(variableType.equals("Tupla")){
				    			    			sb.append("((Tupla)"+nomeInterno+").returnTuplaValue()");
				    			    		}else if(variableType.equals("GenericList")){
				    			    			sb.append("((GenericList)"+nomeInterno+").returnGenericListValue()");
				    			    		}else if(variableType.equals("PDictionary")){
				    			    			sb.append("((PDictionary)"+nomeInterno+").returnPDictionaryValue()");
				    			    		}else{
				    			    			sb.append(nomeInterno+".toString()");
				    			    		}
			    						}else{
			    			    			sb.append(nomeInterno+".toString()");
			    			    		}
		    						}else{
			    						sb.append("//ERROR on print - Attribute or Class doesn't exist!\n");
			    					}
		    						
		    					}else{
		    						sb.append("//ERROR on print - Attributo della classe "+ className+" NON esistente\n");
		    					}
		    				}
    					}
	    				
	    			}else{
	    				//non ci sono punti
	    				//funz(...)
	    				sb.append(gestioneFunzioni(a.get(0),level, symTable, ereditClasses));
	    			}//fine else funz(...)
	    		}
	    		
	    	}else{
	    		//System.out.println("OTHERRRRRRRRRRRRRRRRRRRR");
	    		sb.append(testlistWithouthVirgola.get(i));
	    	}
	    	
	    	//serve per aggiungere l'operatore "concatenazione"
	    	if(i<testlistWithouthVirgola.size()-1){
	    			sb.append("+");
	    	}
	    }
	    
	    if(flag==true){
	    	if(!sb.toString().contains("ERROR"))
	    		return RESULT="System.out.println("+sb.toString()+");\n";
	    	else
	    		return RESULT= "//ERROR on PRINT -> "+ sb.toString()+"\n";
	    }else{
	    	return RESULT="//ERROR ON PRINT: AUTO GENERATE COMMENT\n";
	    }
	    
	}
	
	
	
	
	public static String gestioneFunzioni(String allFunc,int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		//allFunc = pippo(....)
		StringBuilder sb = new StringBuilder();
		ArrayList<String> array=UtilsToSymTable.tokenParameters(allFunc,"();");
		//UtilsToSymTable.printArrayList(array, "array - PRINT");
                                                
        int numFunctionParam = 0;
        if(array.size()>1){
                ArrayList<String> params = UtilsToSymTable.tokenParameters(array.get(1),"#");
                numFunctionParam = params.size();
        }
        
        String className2 = null;
        //cerco nello stella stessa classe
        boolean funcOK = UtilsToSymTable.isCorrectFunctionInvocation(array.get(0),String.valueOf(numFunctionParam),null, level,symTable);
        if(!funcOK){
       	 //cerco tra le classi ereditate
       	 int caught = -1;
       	 if(ereditClasses.size()!=0){
				for(int qw=0; qw<ereditClasses.size(); qw++){
					funcOK = UtilsToSymTable.isCorrectFunctionInvocation(array.get(0),String.valueOf(numFunctionParam),ereditClasses.get(qw), level,symTable);
                   if(funcOK)
                   	caught = qw;
				}
            }
       	 if(caught!=-1){
       		className2 = ereditClasses.get(caught); 
       	 }else {
       		 //cerco nella classe di default
       		funcOK = UtilsToSymTable.isCorrectFunctionInvocation(array.get(0),String.valueOf(numFunctionParam),"DefaultClass", level,symTable);
       		if(funcOK)
       			className2 = "DefaultClass";
       	 	}
       	 
        }
        
        if(funcOK){
        	//verifico se c'è il tipo di ritorno e in caso faccio il .toString()
        	if(UtilsToSymTable.functionHasReturn(array.get(0), String.valueOf(numFunctionParam), className2, level, symTable)){
        		String args = "";
        		if(array.size()>1)
        			args = array.get(1).replace("#", ",");
        		//System.out.println("args: "+args);
        		//System.out.println("className2: "+className2);
        		if(className2!= null && className2.equals("DefaultClass"))
        			sb.append("DefaultClass."+array.get(0)+"("+args+").toString()");
        		else
        			sb.append(array.get(0)+"("+args+").toString()");
        	}else{
        		sb.append("//Error on PRINT - Method Hasn't Return Value! \n");
        	}
        }else{
        	sb.append("//Error on PRINT - Method don't Exist! \n");
        }
        
        //System.out.println("FINO A QUI OK");
        
        return sb.toString();
	}

	
}
