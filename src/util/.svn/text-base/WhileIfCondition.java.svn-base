package util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import symbol.*;
import models.*;


public class WhileIfCondition {

	
	/*
	*
	***********************************  GESTIONE CONDIZIONI PER WHILE/IF **********************
	*
	*/
	
	public static String analyzePartOfCondition(String partOfCondition, int level, SymbolTable symTable, ArrayList<String> ereditClasses){

		String type = CheckType.check(partOfCondition);
		
		//System.out.println("type: " +type);
		
		if(type.equals("Object")){
			//verifico se esiste
			boolean flag = UtilsToSymTable.isExist(partOfCondition,level, symTable, ereditClasses);
			if(flag){
				type = UtilsToSymTable.getVariableType(partOfCondition,level, symTable, ereditClasses);
			}else{
				return null;
			}
		
		}if(type.equals("String")){
			//se ho p.attr, sono qui
			
			ArrayList<String> withoutPoint = UtilsToSymTable.tokenParameters(partOfCondition,".");
			if(withoutPoint.size()==2){
				//withoutPoint.get(0) = p
				//withoutPoint.get(1) = attr
				
				String className = UtilsToSymTable.getVariableType(withoutPoint.get(0),level, symTable, ereditClasses);
				
				if(className!=null){
					
					if(className.equals("Object")){
						className = UtilsToSymTable.getClassNameOfAttribute(withoutPoint.get(1),withoutPoint.get(0) ,level, symTable);
					}
					
					String nameAttr = partOfCondition + "#" + className;
					//System.out.println("nameAttr: " + nameAttr);
					type = UtilsToSymTable.getVariableType(nameAttr,level, symTable, ereditClasses);	
				}else{
					return null;
				}
			}else if(withoutPoint.get(0).contains("[") && withoutPoint.get(0).contains("]")){
				
				ArrayList<String> withouthParents = UtilsToSymTable.tokenParameters(withoutPoint.get(0),"[]");
				ArrayList<String> withouthDuePunti = UtilsToSymTable.tokenParameters(withouthParents.get(1),":");
				if(withouthDuePunti.size()>1){
					//return Tupla, GenericList or PDictionary
					String varTYPE = UtilsToSymTable.getVariableType(withouthParents.get(0),level, symTable, ereditClasses);
					return varTYPE;
				}else{
					String varTYPE = UtilsToSymTable.getVariableType(withouthParents.get(0),level, symTable, ereditClasses);
					//System.out.println("varTYPE: " +varTYPE);
					if(varTYPE.equals("GenericList")){
						String newValues = new String();
						if(CheckType.check(withouthDuePunti.get(0)).equals("Object"))
							newValues = UtilsToSymTable.getValueModels("subGenericList",null,"0",withouthParents.get(0),level, symTable);
						else
							newValues = UtilsToSymTable.getValueModels("subGenericList",null,withouthDuePunti.get(0),withouthParents.get(0),level, symTable);
						return CheckType.check(newValues);
					}
					else if(varTYPE.equals("PDictionary")){
						String newValues = new String();
						if(CheckType.check(withouthDuePunti.get(0)).equals("Object"))
							newValues = UtilsToSymTable.getValueModels("getTypeObject",null,"0",withouthParents.get(0),level, symTable);
						else
							newValues = UtilsToSymTable.getValueModels("getSigleValue",null,withouthDuePunti.get(0),withouthParents.get(0),level, symTable);
						return CheckType.check(newValues);
					}
					else if(varTYPE.equals("Tupla")){
						String newValues = new String();
						if(CheckType.check(withouthDuePunti.get(0)).equals("Object"))
							newValues = UtilsToSymTable.getValueModels("subTupla",null,"0",withouthParents.get(0),level, symTable);
						else
							newValues = UtilsToSymTable.getValueModels("subTupla",null,withouthDuePunti.get(0),withouthParents.get(0),level, symTable);
						return CheckType.check(newValues);
					}
				}
				
			}
			
		}
		
		return type;
	}
	
	
	public static String getStringBuilderSingleCondition(String cond, String type, String finalType, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		
		
		StringBuilder sb = new StringBuilder();
		
		ArrayList<String> withoutPoint = UtilsToSymTable.tokenParameters(cond,".");
		if(withoutPoint.size()==2){
			//withoutPoint.get(0) = p
			//withoutPoint.get(1) = attr
			
			String className = UtilsToSymTable.getVariableType(withoutPoint.get(0),level, symTable, ereditClasses);
			
			String nomeInterno = "(("+className+")"+withoutPoint.get(0)+")."+withoutPoint.get(1);
			
			if(type.equals("Object")){
			
				if(finalType.equals("Integer")){
					// Integer.parseInt( ((NomeClasse)p).attr.toString() )
					sb.append("Integer.parseInt( "+nomeInterno+".toString() )");
				}else if(finalType.equals("Float")){
					sb.append("Double.parseDouble( "+nomeInterno+".toString() )");
				}else if(finalType.equals("Double")){
					sb.append("Double.parseDouble( "+nomeInterno+".toString() )");
				}else{
					sb.append(nomeInterno+".toString()");
				}
			
			}else{
				//es: ((Tupla)((ProvaClass)p).attr)
				//sb.append("(("+type+")"+nomeInterno+")");
				
				if(type.equals("Integer")){
					sb.append("Integer.parseInt("+nomeInterno+".toString())");
				}else if(type.equals("Float")){
					sb.append("Double.parseDouble("+nomeInterno+".toString())");
				}else if(type.equals("Double")){
					sb.append("Double.parseDouble("+nomeInterno+".toString())");
				}else if(type.equals("String")){
					sb.append(nomeInterno+".toString().replace(\"\\\"\", \"\")");
				}else{
					sb.append(nomeInterno);
				}
				
			}
			
		}else{
			//casi "normali" con variabili "normali" o addirittura numeri/stringhe
			if(type.equals("Object")){
				if(finalType.equals("Integer")){
					//Integer.parseInt(cond.toString());
					sb.append("Integer.parseInt("+cond+".toString())");
				}else if(finalType.equals("Float")){
					sb.append("Double.parseDouble("+cond+".toString())");
				}else if(finalType.equals("Double")){
					sb.append("Double.parseDouble("+cond+".toString())");
				}else{
					sb.append(cond+".toString()");
				}
			}else if(type.equals("TupListDict")){
				ArrayList<String> withouthParents = UtilsToSymTable.tokenParameters(withoutPoint.get(0),"[ ]");
    			boolean isE = UtilsToSymTable.isExist(withouthParents.get(0),level, symTable, ereditClasses);
    			//System.out.println("INDENT: "+ level+ " withouthParents.get(0): " + withouthParents.get(0));
    			if(isE){
    				ArrayList<String> withouthDuePunti = UtilsToSymTable.tokenParameters(withouthParents.get(1),":");
    				
    				String argoment = new String();
    				
    				if(withouthParents.get(1).contains(":")){
    					if(withouthDuePunti.size()>1){
    						//es: 12:29
    						String type_one = CheckType.check(withouthDuePunti.get(0));
    						
	    					if(type_one.equals("Object"))
	    						argoment = withouthDuePunti.get(0)+ ".toString() + ";
	    					else
	    						argoment = "\"" + withouthDuePunti.get(0) + "\" + ";
	    					
	    					argoment = argoment + "\":\" + ";
	    					
	    					String type_two = CheckType.check(withouthDuePunti.get(1));
	    					
	    					if(type_two.equals("Object"))
	    						argoment = argoment + withouthDuePunti.get(0) + ".toString()";
	    					else
	    						argoment = argoment + "\"" + withouthDuePunti.get(0) + "\"";
    						
    					}else{
    						//es :2  opp 3:
    						if(withouthDuePunti.get(0).charAt(0)==':'){
    							//es :2
    							
    							argoment = "\":\" + ";
		    					
		    					String type_two = CheckType.check(withouthDuePunti.get(1));
		    					
		    					if(type_two.equals("Object"))
		    						argoment = argoment + withouthDuePunti.get(0) + ".toString()";
		    					else
		    						argoment = argoment + "\"" + withouthDuePunti.get(0) + "\"";
    							
    						}else{
    							//es 3:
    							
    							String type_one = CheckType.check(withouthDuePunti.get(0));
	    						
		    					if(type_one.equals("Object"))
		    						argoment = withouthDuePunti.get(0)+ ".toString() + ";
		    					else
		    						argoment = "\"" + withouthDuePunti.get(0) + "\" + ";
		    					
		    					argoment = argoment + "\":\"";
		    					
    						}
    					}
    				}else{
    					//es [7]
    					String type_single = CheckType.check(withouthDuePunti.get(0));
    					if(type_single.equals("Object"))
    						argoment = withouthDuePunti.get(0);
    					else
    						argoment = "\"" + withouthDuePunti.get(0) + "\"";
    				}
    					
    				//System.out.println("ARGOMENT: " +argoment);
    				
					String varTYPE = UtilsToSymTable.getVariableType(withouthParents.get(0),level, symTable, ereditClasses);
					
					String nomeInterno = new String();
					
					if(varTYPE.equals("GenericList"))
						nomeInterno = "((GenericList)"+withouthParents.get(0)+").subGenericList("+argoment+".toString())";
					else if(varTYPE.equals("PDictionary"))
						nomeInterno = "((PDictionary)"+withouthParents.get(0)+").getSigleValue("+argoment+".toString())";
					else if(varTYPE.equals("Tupla"))
						nomeInterno = "((Tupla)"+withouthParents.get(0)+").subTupla("+argoment+".toString())";
    				
					
					if(finalType.equals("Integer")){
						sb.append("Integer.parseInt("+nomeInterno+".toString())");
					}else if(finalType.equals("Float")){
						sb.append("Double.parseDouble("+nomeInterno+".toString())");
					}else if(finalType.equals("Double")){
						sb.append("Double.parseDouble("+nomeInterno+".toString())");
					}else if(finalType.equals("String")){
						sb.append(nomeInterno+".toString().replace(\"\\\"\", \"\")");
					}else{
						sb.append(nomeInterno);
					}
					
					
    			}
    			else{
    				sb.append("//Error - GenericList/Tupla/PDictionary in Print not declared!");
    			}
	    		
			}
			else{
				
				sb.append("(("+type+")"+cond+")");
			/*	
				if(type.equals("Integer")){
					sb.append("Integer.parseInt("+cond+".toString())");
				}else if(type.equals("Float")){
					sb.append("Double.parseDouble("+cond+".toString())");
				}else if(type.equals("Double")){
					sb.append("Double.parseDouble("+cond+".toString())");
				}else if(type.equals("String")){
					sb.append(cond+".toString().replace(\"\\\"\", \"\")");
				}else{
					sb.append(cond);
				}
				*/
				
			}
		}
		
		return sb.toString();
		
	}
	
	
	public static boolean isTupListDict(String cond){
		if(cond.contains("[") && cond.contains("]") && cond.charAt(0)!='['){
			return true;    			
		}
		else
			return false;
	}
	
	
	public static String getStringBuilderCondition(String condition, SymbolTable symTable, Hashtable<String, Integer> scopeHash, ArrayList<String> ereditClasses){
	
		//gestione indentazione!
		int level = scopeHash.get(condition);
		//System.out.println("CONDIZIONE - "+condition);
		//con self:
		String condition_con_self = condition.replace("(","");
		condition_con_self = condition_con_self.replace(")","");
		ArrayList<String> arrayWithoutLogicOperator_ma_con_self = UtilsToSymTable.tokenParameters(condition_con_self,"\\|\\||&&| ");
		
		//senza self:
		//es: supponiamo di avere ( d!=0 && f==3 )
		String condition_senza_self = condition.replace("self.","");
		String c = condition_senza_self.replace("(","");
		//String c = condition.replace("(","");
		c = c.replace(")","");
		
		//key = d!=0  ;  value = ((Integer)d)!=0
		//key = f==3  ;  value = ((Integer)f)==3
		Hashtable<String,String> hashTMP = new Hashtable<String,String>();
		
		//key = d!=0   ;   value = self.d!=0
		Hashtable<String,String> hashTMP_con_self = new Hashtable<String,String>();
		
		ArrayList<String> arrayWithoutLogicOperator = UtilsToSymTable.tokenParameters(c,"\\|\\||&&| ");
		
		for(int i=0; i < arrayWithoutLogicOperator.size(); i++){
			StringBuilder sb2 = new StringBuilder();
		
			//arrayWithoutLogicOperator.get(i) -> è una singola condizione
			//es: arrayWithoutLogicOperator.get(0)-> d!=0
			//es: arrayWithoutLogicOperator.get(1)-> f==3
			String[] p = arrayWithoutLogicOperator.get(i).split("(~=|!=|==|<=|>=|<|>)");

			//String operator = arrayWithoutLogicOperator.get(i).replace(p[0],"");
			//operator = operator.replace(p[1],"");
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(p[0]);
			tmp.add(p[1]);
			String operator = getOperator(tmp,arrayWithoutLogicOperator.get(i));
			
			//affinche' la condizione sia corretta:
			//p[0] e p[1] -> devono esistere ed essere dello stesso tipo!
			
			String typeP0 = analyzePartOfCondition(p[0],level, symTable, ereditClasses);
			//System.out.println("typeP0: " + typeP0);
			String typeP1 = analyzePartOfCondition(p[1],level, symTable, ereditClasses);
			//System.out.println("typeP1: " + typeP1);
			
			//NB: typeP0/typeP1 = Object se p[0]/p[1] e' una variabile il cui valore le è stato 
			//assegnato tramite return di funzione oppure se è argomento della funzione
			
			if(typeP0!=null && typeP1!=null){
				if(typeP0.equals(typeP1)){
					if(typeP0.equals("Object") && typeP1.equals("Object")){
						//se sono entrambi Object -> cast a ToString per entrambi

						//p[0]
						ArrayList<String> withoutPoint = UtilsToSymTable.tokenParameters(p[0],".");
						String className = UtilsToSymTable.getVariableType(p[0],level, symTable, ereditClasses);
						if(withoutPoint.size()==2){
							sb2.append("(("+className+")"+withoutPoint.get(0)+")."+withoutPoint.get(1)+".toString().replace(\"\\\"\", \"\")");
						}else{
							sb2.append("("+p[0]+").toString().replace(\"\\\"\", \"\")");
						}
						
						sb2.append(".compareTo(");
						
						//p[1]
						ArrayList<String> withoutPoint2 = UtilsToSymTable.tokenParameters(p[1],".");
						String className2 = UtilsToSymTable.getVariableType(p[1],level, symTable, ereditClasses);
						if(withoutPoint2.size()==2){
							sb2.append("(("+className2+")"+withoutPoint2.get(0)+")."+withoutPoint2.get(1)+".toString().replace(\"\\\"\", \"\")");
						}else{
							sb2.append("("+p[1]+").toString().replace(\"\\\"\", \"\")");
						}
						
						
						sb2.append(") ");
						sb2.append(operator);
						sb2.append(" 0 ");
						
						
						
					}else{
					
						if(operator.equals("!="))
							sb2.append("!");
						
						//p[0]
						if(isTupListDict(p[0])==false)
							sb2.append( getStringBuilderSingleCondition(p[0],typeP0,null,level, symTable, ereditClasses) );
						else
							sb2.append( getStringBuilderSingleCondition(p[0],"TupListDict",typeP0,level, symTable, ereditClasses) );
						//System.out.println("first: "+sb2.toString());
						
						//operator
						if( (operator.equals("==") || operator.equals("!=")) && typeP0.equals("String")){
							sb2.append(".equals(");
						}else{
							sb2.append(operator);
						}
						//System.out.println("second: "+sb2.toString());
						//System.out.println("operator: "+operator);
						
						//p[1]
						if(isTupListDict(p[1])==false)
							sb2.append( getStringBuilderSingleCondition(p[1],typeP1,null,level, symTable, ereditClasses) );
						else
							sb2.append( getStringBuilderSingleCondition(p[1],"TupListDict",typeP1,level, symTable, ereditClasses) );
						//System.out.println("thirt: "+sb2.toString());
						
						if( (operator.equals("==") || operator.equals("!=")) && typeP0.equals("String")){
							sb2.append(")");
						}
						
					}
				}else{
					//se sono diversi, l'unico caso ammesso e' se P0 oppure P1 sono Object
					//in tutti gli altri casi, ERRORE!!!!! (cioè ritorno "null")
					if(typeP0.equals("Object")){
					
						if(operator.equals("!="))
							sb2.append("!");
						
						//p[0]
						sb2.append( getStringBuilderSingleCondition(p[0],"Object",typeP1,level, symTable, ereditClasses) );
						
						//operator
						if( (operator.equals("==") || operator.equals("!=")) && typeP1.equals("String")){
							sb2.append(".equals(");
						}else{
							sb2.append(operator);
						}
						
						//p[1]
						if(isTupListDict(p[1])==false)
							sb2.append( getStringBuilderSingleCondition(p[1],typeP1,null,level, symTable, ereditClasses) );
						else
							sb2.append( getStringBuilderSingleCondition(p[1],"TupListDict",typeP1,level, symTable, ereditClasses) );
						
						if( (operator.equals("==") || operator.equals("!=")) && typeP1.equals("String")){
							sb2.append(")");
						}
						
					}else if(typeP1.equals("Object")){
						
						if(operator.equals("!="))
							sb2.append("!");
						
						//p[0]
						if(isTupListDict(p[0])==false)
							sb2.append( getStringBuilderSingleCondition(p[0],typeP0,null,level, symTable, ereditClasses) );
						else
							sb2.append( getStringBuilderSingleCondition(p[0],"TupListDict",typeP0,level, symTable, ereditClasses) );
						
						//operator
						if( (operator.equals("==") || operator.equals("!=")) && typeP0.equals("String")){
							sb2.append(".equals(");
						}else{
							sb2.append(operator);
						}
						
						//p[1]
						sb2.append( getStringBuilderSingleCondition(p[1],"Object",typeP0,level, symTable, ereditClasses) );
						
						if( (operator.equals("==") || operator.equals("!=")) && typeP0.equals("String")){
							sb2.append(")");
						}
						
					}else{
						return null;
					}
				}
				
				//inserisco nella Hashtable
				hashTMP.put(arrayWithoutLogicOperator.get(i),sb2.toString());
				hashTMP_con_self.put(arrayWithoutLogicOperator.get(i),arrayWithoutLogicOperator_ma_con_self.get(i));
				
			}
			
		}//fine for
		
		Enumeration<String> keys = hashTMP.keys();
		while(keys.hasMoreElements()){
			String key = keys.nextElement();
			//System.out.println("condition: " + key);
			String value = hashTMP.get(key);
			String key_con_self = hashTMP_con_self.get(key);
			if(key_con_self.contains("self.")){
				//System.out.println("Contiene self.");
				String[] p = key_con_self.split("(~=|!=|==|<=|>=|<|>)");
				//System.out.println("CP[0]: " +p[0]);
				if(p[0].contains("self.")){
					String elem_senza_self = p[0].replace("self.","");
					value = value.replace(elem_senza_self, "self."+elem_senza_self);
					//System.out.println("newValue: " + value);
				}
					
				//System.out.println("CP[1]: " +p[1]);
				if(p[1].contains("self.")){
					String elem_senza_self = p[1].replace("self.","");
					value = value.replace(elem_senza_self, "self."+elem_senza_self);
					//System.out.println("newValue: " + value);
				}
				
			}
			//condition = condition.replace(key, value);
			condition_senza_self = condition_senza_self.replace(key, value);
		}

		//System.out.println("condition_senza_self: " + condition_senza_self);
		
		//return condition;
		return condition_senza_self;
	}
	
	
	//**** GET SINGOLO OPERATORE ****
	public static String getOperator(ArrayList<String> arrayWhithoutOperator,String allString){
		
		StringBuilder operator = new StringBuilder();
		
		for(int l=0;l<arrayWhithoutOperator.size();l++){
			if(l!=arrayWhithoutOperator.size()-1)
				operator.append(arrayWhithoutOperator.get(l)+"|") ;
			else
				operator.append(arrayWhithoutOperator.get(l)) ;
		}

		ArrayList<String> arrayOperator=new ArrayList<String>();
		arrayOperator=UtilsToSymTable.tokenParameters(allString,operator.toString());
		
		if(!arrayOperator.isEmpty())
			return arrayOperator.get(0);
		else
			return null;
	}
	
	
}
