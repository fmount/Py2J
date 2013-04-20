package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

import symbol.*;
import models.*;

public class UtilsToSymTable {

	
	/*
	*
	***********************************  METODI UTILI IN VARIE PARTI ****************************
	*
	*/
	
	
	public static ArrayList<String> tokenParameters(String s,String token) {
        ArrayList <String> arrayToken=new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(s, token);
        int i=0;
        while (st.hasMoreTokens()) {
            String val = st.nextToken();
            arrayToken.add(i, val);
            i++;
        }
        return arrayToken;
	}

	
	public static String getVariableType(String variableName,int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		//cerco la variabile su tutti i livelli di scope uguali o minori di quello attuale

		ArrayList<Integer> scopeMAX = new ArrayList<Integer>();
		
		ArrayList<Integer> allKeys = symTable.getAllScope();
		
		Iterator<Integer> it = allKeys.iterator();
		while(it.hasNext()){
			Integer numberScoper = it.next();
			if(numberScoper <= level)
				scopeMAX.add(numberScoper);
		}
		
		Collections.sort(scopeMAX);
		
		int k = scopeMAX.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(scopeMAX.get(k),variableName);
			if(s!=null){
				if(s.getOwner()!=null){
					if(s.getOwner().equals("DefaultClass"))
						return s.getType()+"#DefaultClass";
					else
						return s.getType();
				}
				else
					return s.getType();
			}
			k--;
		}
		
		
		//ereditClasses - arrayList di classi ereditate
		if(ereditClasses!=null){
			//printArrayList(ereditClasses, "ereditClasses - get Variable Type");
			for(int j=0; j<ereditClasses.size(); j++){
				String variableEreditated = variableName + "#" + ereditClasses.get(j);
				SymbolType s = symTable.getSymbol(1,variableEreditated);
				if(s!=null){
					if(s.getOwner().equals(ereditClasses.get(j)))
						return s.getType();
				}
			}
		}
		
		
		return null;
	}
	
	
	public static String getValue(String variableName,int level,SymbolTable symTable, ArrayList<String> ereditClasses){
	
		ArrayList<Integer> scopeMAX = new ArrayList<Integer>();
		
		ArrayList<Integer> allKeys = symTable.getAllScope();
		
		Iterator<Integer> it = allKeys.iterator();
		while(it.hasNext()){
			Integer numberScoper = it.next();
			if(numberScoper <= level)
				scopeMAX.add(numberScoper);
		}
		
		Collections.sort(scopeMAX);
		
		int k = scopeMAX.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(scopeMAX.get(k),variableName);
			if(s!=null){
				String value = s.getValue();
				return  value;
			}
			k--;
		}
		
		//ereditClasses - arrayList di classi ereditate
		if(ereditClasses!=null){
			//printArrayList(ereditClasses, "ereditClasses - get Value");
			for(int j=0; j<ereditClasses.size(); j++){
				String variableEreditated = variableName + "#" + ereditClasses.get(j);
				SymbolType s = symTable.getSymbol(1,variableEreditated);
				if(s!=null){
					if(s.getOwner().equals(ereditClasses.get(j)))
						return s.getValue();
				}
			}
		}
		
		return null;
	}
	
	
	public static boolean isExist(String variableName,int level,SymbolTable symTable, ArrayList<String> ereditClasses){

		ArrayList<Integer> scopeMAX = new ArrayList<Integer>();
		ArrayList<Integer> allKeys = symTable.getAllScope();
		
		Iterator<Integer> it = allKeys.iterator();
		while(it.hasNext()){
			Integer numberScoper = it.next();
			if(numberScoper <= level)
				scopeMAX.add(numberScoper);
		}
		
		Collections.sort(scopeMAX);
		
		int k = scopeMAX.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(scopeMAX.get(k),variableName);
			if(s!=null){
				if(!s.getType().equals("class") || !s.getType().equals("function"))
					return true;
			}
			k--;
		}
		
		//ereditClasses - arrayList di classi ereditate
		if(ereditClasses!=null){
			//printArrayList(ereditClasses, "ereditClasses");
			for(int j=0; j<ereditClasses.size(); j++){
				String variableEreditated = variableName + "#" + ereditClasses.get(j);
				SymbolType s = symTable.getSymbol(1,variableEreditated);
				if(s!=null){
					if(!s.getType().equals("class") || !s.getType().equals("function"))
						return true;
				}
			}
		}
		
		return false;
	}
	
	
	public static boolean isExistClass(String variableName,int level, SymbolTable symTable){

		ArrayList<Integer> allKeys = symTable.getAllScope();	
		Collections.sort(allKeys);
		int k = allKeys.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(allKeys.get(k),variableName);
			if(s!=null){
				if(s.getType().equals("class") || s.getType().equals("Class")){
					return true;
				}
			}
			k--;
		}
		return false;
	}
	
	
	public static boolean isExistFunction(String variableName,int level, SymbolTable symTable){	
		
		ArrayList<Integer> allKeys = symTable.getAllScope();	
		Collections.sort(allKeys);
		int k = allKeys.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(allKeys.get(k),variableName);
			//System.out.println("allKeys.get(k): "+ allKeys.get(k) + " variableName:" + variableName);
			if(s!=null){
				if(s.getType().equals("function") || s.getType().equals("constructor")){
					return true;
				}
			}
			k--;
		}
		
		return false;
	}
	
	
	
	public static boolean isCorrectFunctionInvocation(String functionName, String numberOfParameters, String className, int level, SymbolTable symTable){
		String key = new String();
		if(className!=null)
			key = functionName+"#"+numberOfParameters+"#"+className;
		else
			key = functionName+"#"+numberOfParameters;
		//System.out.println("key: "+ key);
		return isExistFunction(key,level,symTable);	
	}
	
	
	public static boolean functionHasReturn(String functionName, String numberOfParameters, String className, int level,SymbolTable symTable){
		
		String key = new String();
		if(className!=null)
			key = functionName+"#"+numberOfParameters+"#"+className;
		else
			key = functionName+"#"+numberOfParameters;
		
		ArrayList<Integer> allKeys = symTable.getAllScope();	
		Collections.sort(allKeys);
		int k = allKeys.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(allKeys.get(k),key);
			if(s!=null){
				if(s.getReturned().equals("Object") || s.getReturned().equals("Object ") || s.getReturned().equals(" Object") || s.getReturned().equals(" Object ")){
					return true;
				}
			}
			k--;
		}
		
		return false; 	
	}
	
	// COSI È CORRETTO??????????????????????????????
	public static boolean isCorrectClassAttributeUsed(String attributeName, String className, int level, SymbolTable symTable){
		String key = new String();
		if(className.equals("Object")){
			//System.out.println("CLASS: OBJECT: "+ attributeName);
			ArrayList<String> a = tokenParameters(attributeName, ".");
			Hashtable<String,SymbolType> allONEscope = symTable.getSymTable(1);
			Enumeration<String> e = allONEscope.keys();
			while(e.hasMoreElements()){
				String keyONE = e.nextElement();
				if(a.size()>1){
					if(keyONE.contains(a.get(1))){
						return true;
					}
				}else{
					if(keyONE.contains(a.get(0))){
						return true;
					}
				}
			}
		}
		else if(className!=null)
			key = attributeName+"#"+className;
		else
			key = attributeName;
		
		ArrayList<Integer> allKeys = symTable.getAllScope();	
		Collections.sort(allKeys);
		int k = allKeys.size()-1;
		while(k>=0){
			SymbolType s = symTable.getSymbol(allKeys.get(k),key);
			if(s!=null){
				if(!s.getType().equals("function") && !s.getType().equals("class")){
					return true;
				}
			}
			k--;
		}
		
		return false; 	
	}
	
	
	
	public static ArrayList<String> getTuplaValue(String variableName,int level, SymbolTable symTable, ArrayList<String> ereditClasses){
        
        String type = getVariableType(variableName, level, symTable, ereditClasses);
        
        if(type!=null){
        if(type.equals("Tupla")){
		        String value = getValue(variableName,level,symTable, ereditClasses);
		        ArrayList<String> withoutParents = tokenParameters(value,"(),");
		        return  withoutParents;
	        }
        }
        return null;
	}
	
	
	
	public static String getValueModels(String functionName, String key, String value, String variableName, int level, SymbolTable symTable){
		
		//con TUPLE e LISTE: Key==null  mentre value==[1:2]
		//con DIZIONARIO: Key!=null  mentre value!=null
		
		if(level!=-1){
    		for(int j=level;j>=0;j--){
				SymbolType symbol=symTable.getSymbol(j,variableName);
				if(symbol!=null){
							if(functionName.equals("subTupla")){
									Object tmp = new Tupla(symbol.getValue());
									Object tmp2 = new Tupla();
									tmp2 = ((Tupla)tmp).subTupla(value);
									if(tmp2 instanceof String)
										return tmp2.toString();
									else
										return ((Tupla)tmp2).returnTuplaValue();
								}
							if(functionName.equals("subGenericList")){
									Object tmp3 = new GenericList(symbol.getValue());
									Object tmp4 = new GenericList();
									tmp4 = ((GenericList)tmp3).subGenericList(value);
									if(tmp4 instanceof String)
										return tmp4.toString();
									else
										return ((GenericList)tmp4).returnGenericListValue();
								}
							if(functionName.equals("insertNewObject")){
									Object tmp5 = new models.PDictionary(symbol.getValue());
									((models.PDictionary)tmp5).insertNewObject(key,value);
									return ((models.PDictionary)tmp5).returnPDictionaryValue();
								}
							if(functionName.equals("getSize")){
									Object tmp8 = new GenericList(symbol.getValue());
									return String.valueOf(((GenericList)tmp8).getSize());
								}
							if(functionName.equals("getTypeObject")){
									Object tmp9 = new models.PDictionary(symbol.getValue());
									String oneElement = ((models.PDictionary)tmp9).getTypeObject();
									return oneElement;
								}
							if(functionName.equals("getSigleValue")){
								Object tmp10 = new models.PDictionary(symbol.getValue());
								Object oneElement = ((models.PDictionary)tmp10).getSigleValue(value);
								return oneElement.toString();
							}
						}
				}
			}
		else{
				if(functionName.equals("resultOperationsTupla")){
					Object tmp6 = new Tupla();
					((Tupla)tmp6).resultOperationsTupla(value);
					return ((Tupla)tmp6).returnTuplaValue();
					}
				if(functionName.equals("resultOperationsGenericList")){
					Object tmp7 = new GenericList();
					((GenericList)tmp7).resultOperationsGenericList(value);
					return ((GenericList)tmp7).returnGenericListValue();
					}
			}
		return null;
	}
	   	
	
	public static String getFunctionModifier(String functionName){
		if(functionName.charAt(0)=='_' && functionName.charAt(1)=='_'){
			return "private ";
		}else if(functionName.charAt(0)=='_' ){
			return "protected ";
		}
		else{
			return "public ";
		} 
	}
	
	
	public static void printArrayList(ArrayList<String> a, String arrayName){
	
		for(int i =0; i<a.size(); i++){
			System.out.println(arrayName+" - "+a.get(i));
		}
	}
	
	
	public static String getStringBuilderToCast(String val, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		//solo nel caso in cui val sia una VARIABILE!
		//Possibili tipi: Object, Tupla, List, Dict, String, Integer, Float
		String type_val = getVariableType(val,level,symTable, ereditClasses);
		return getFormattedStringToCast(type_val,val);
	}
	
	
	public static String getFormattedStringToCast(String type, String val){

		if(type.equals("Object")){
			return val+".toString()";
		}
		if(type.equals("Tupla")){
			return "((Tupla)"+val+").returnTupla()";
		}
		if(type.equals("GenericList")){
			return "((GenericList)"+val+").returnGenericList()";
		}
		if(type.equals("PDictionary")){
			return "((PDictionary)"+val+").returnPDictionarye()";
		}
		if(type.equals("Integer")){
			return val+".toString()";
		}
		if(type.equals("Float")){
			return val+".toString()";
		}
		if(type.equals("Double")){
			return val+".toString()";
		}
		if(type.equals("String")){
			return val+".toString()";
		}
		return val;
	}
	
	
	public static String getStringBuilderToAssignamentWithExpression(String value, String ty_pe){
		if(ty_pe.equals("Integer")){
	            //parameterDX.append("Integer.parseInt("+arrayLP.get(r)+".toString())");
	    		return "Integer.parseInt("+value+".toString())";
	    }else if(ty_pe.equals("Float")){
	            //parameterDX.append("Float.parseFloat("+arrayLP.get(r)+".toString())");
	            return "Double.parseDouble("+value+".toString())";
	    }else if(ty_pe.equals("Double")){
	            //parameterDX.append("Doulbe.parseDoulbe("+arrayLP.get(r)+".toString())");
	    		return "Double.parseDouble("+value+".toString())";
	    }else{
	            //caso semplice Stringa
	            //parameterDX.append(arrayLP.get(r)+".toString()");
	            return value+".toString()";
	    }
	}
	
	
	public static String getFormattedStringToCastInRETURN(String type, String val){

		if(type.equals("Object")){
			return val+".toString()";
		}
		if(type.equals("Tupla")){
			return "((Tupla)"+val+").returnTupla()";
		}
		if(type.equals("GenericList")){
			return "((GenericList)"+val+").returnGenericList()";
		}
		if(type.equals("PDictionary")){
			return "((PDictionary)"+val+").returnPDictionarye()";
		}
		if(type.equals("Integer")){
			return val;
		}
		if(type.equals("Float")){
			return val;
		}
		if(type.equals("Double")){
			return val;
		}
		if(type.equals("String")){
			return val+".toString()";
		}
		return val;
	}
	
	public static String replaceCharInsideParents(String s, char singolC){
		StringBuilder newS = new StringBuilder();
		int numChar = 0;
		String flag = "fuori";
		while(numChar<s.length()){
			char c = s.charAt(numChar);
			if(c=='(')
				flag = "dentro";
			if(c==')')
				flag = "fuori";
			if(flag.equals("dentro")){
				if(c==singolC)
					newS.append("#");
				else
					newS.append(c);
			}else{
				newS.append(c);
			}
			numChar++;
		}
		return newS.toString();
	}
	
	//listParameters = p#attr,x 
	public static boolean isCorrectListOfParametersInsideFunction(String listParameters,int level, SymbolTable symTable, ArrayList<String> ereditClasses ){
		boolean OK = true;
		if(listParameters!=null){
			//al posto del # -> metto il PUNTO
			listParameters = listParameters.replace("#", ".");
			//splitto per gli "n" argomenti
			ArrayList<String> oneArgoments = tokenParameters(listParameters, ",");
			//printArrayList(oneArgoments, "oneArgoments");
			for(int i=0; i<oneArgoments.size();i++){
				if(oneArgoments.get(i).contains(".") && !oneArgoments.get(i).contains("\"") && !oneArgoments.get(i).contains("\'")){
					//p.attr
					ArrayList<String> withouthPoint = tokenParameters(oneArgoments.get(i), ".");
					//printArrayList(withouthPoint, "withouthPoint - constructor");
					if(isExist(withouthPoint.get(0),level,symTable, ereditClasses)){
						String className = getVariableType(withouthPoint.get(0),level,symTable, ereditClasses);
						if(!isCorrectClassAttributeUsed(oneArgoments.get(i), className, level, symTable))
							OK = false;
					}else
						OK = false;
				}else{
					//x
					String type = CheckType.check(oneArgoments.get(i));
					if(!isExist(oneArgoments.get(i),level,symTable, ereditClasses) && type.equals("Object"))
						OK = false;
				}
			}
		}
		return OK;		
	}
	
	
	public static String getStringBuilderOfParametersInsideFunction(String listParameters,int level, SymbolTable symTable, ArrayList<String> ereditClasses ){
		StringBuilder sb = new StringBuilder();
		if(listParameters!=null){
			//al posto del # -> metto il PUNTO
			listParameters = listParameters.replace("#", ".");
			//splitto per gli "n" argomenti
			ArrayList<String> oneArgoments = tokenParameters(listParameters, ",");
			for(int i=0; i<oneArgoments.size();i++){
				if(oneArgoments.get(i).contains(".") && !oneArgoments.get(i).contains("\"") && !oneArgoments.get(i).contains("\'")){
					//p.attr
					ArrayList<String> withouthPoint = tokenParameters(oneArgoments.get(i), ".");
					String className = getVariableType(withouthPoint.get(0),level,symTable, ereditClasses);
					sb.append("(("+className+")"+withouthPoint.get(0)+")."+withouthPoint.get(1));
				}else{
					//x
					sb.append(oneArgoments.get(i));
				}
				
				if(i<oneArgoments.size()-1)
					sb.append(",");
			}
		}
		return sb.toString();		
	}
	
	public static String getStringBuilderModelsEmpty(String type,ArrayList<String> splitStringBuilder, String parSX_con_self, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		StringBuilder sb = new StringBuilder();
		String parSX_senza_self = parSX_con_self.replace("self.", "");
		
    	if(splitStringBuilder.get(0).equals("NOTHING-CERTAIN")){
    		if(!UtilsToSymTable.isExist(parSX_senza_self,level,symTable,ereditClasses))
    			sb.append("Object ");
    		sb.append(parSX_con_self);
    		sb.append(" = ");
    		sb.append("new "+type+"();\n");
            
            symTable.putSymbol(level,parSX_senza_self, new SymbolType(parSX_senza_self,type,"",null,null,level,null));
        }else{
            //qui splitStringBuilder.get(0).equals("OBJECT-ASSIGNMENT") 
            //es: ((nomeClasse)p).attr
            String parameterSX = "(("+splitStringBuilder.get(3)+")"+splitStringBuilder.get(1)+")."+splitStringBuilder.get(2);
            sb.append(parameterSX);
            sb.append(" = ");
            sb.append("new GenericList();\n");
            
            String newNameVariable = parSX_senza_self+"#"+splitStringBuilder.get(3);
            symTable.putSymbol(level,newNameVariable, new SymbolType(parSX_senza_self,type,"",null,null,level,null)); 
        }
		
		
		return sb.toString();
	}
	
	
	//parSX_con_self -> è la DX
	public static String isSumTuplaOrGenericList(String VeraSX, String parSX_con_self, ArrayList<String> splitStringBuilder, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		
		String tab=" ";
 		for(int i=0; i<level; i++){
			tab+="\t";
		}
		
		StringBuilder sb = new StringBuilder();
		//System.out.println("VeraSX: "+ VeraSX);
		String parSX_senza_self = parSX_con_self.replace("self.", "");
		
		ArrayList<String> arrayToSumOrMult = UtilsToSymTable.tokenParameters(parSX_con_self,"+");
		//printArrayList(arrayToSumOrMult, "arrayToSumOrMult");
    	if(arrayToSumOrMult.size()==2){
    		
    		String type1 = getTypeSingleElement(arrayToSumOrMult.get(0), level, symTable, ereditClasses);
    		String type2 = getTypeSingleElement(arrayToSumOrMult.get(1), level, symTable, ereditClasses);
    		//System.out.println("type1: "+ type1 + " type2" +type2);
    		
    		if(type1!=null && type2!=null){
	    		if( (type1.equals("Tupla") && type2.equals("Tupla")) || (type1.equals("GenericList") && type2.equals("GenericList"))){
	    			
	    			//getStringBuilderSingleElement(arrayToSumOrMult.get(0),level,symTable,ereditClasses);
	    			
	    			if(splitStringBuilder.get(0).equals("NOTHING-CERTAIN")){
	    				
	    	    		if(!UtilsToSymTable.isExist(VeraSX,level,symTable,ereditClasses)){
	    	    			//se non esiste
	    	    			sb.append(tab);
		    	    		sb.append("Object ");
		    	    		sb.append(VeraSX);
		    	    		sb.append(" = ");
		    	    		sb.append("new "+type1+"();\n");
	    	    		}
	    	    		sb.append(tab);
	    	    		sb.append("(("+type1+")"+VeraSX+")");
	    	    		sb.append(".sum");
	    	    		sb.append("(");
	    	    		sb.append(getStringBuilderSingleElement(arrayToSumOrMult.get(0), level, symTable, ereditClasses));
	    	    		sb.append(",");
	    	    		sb.append(getStringBuilderSingleElement(arrayToSumOrMult.get(1), level, symTable, ereditClasses));
	    	    		sb.append(")");
	    	    		sb.append(";\n");
	    	            
	    	            symTable.putSymbol(level,VeraSX, new SymbolType(VeraSX,type1,"",null,null,level,null));
	    	        }else{
	    	            //qui splitStringBuilder.get(0).equals("OBJECT-ASSIGNMENT") 
	    	            //es: ((nomeClasse)p).attr
	    	            String parameterSX = "(("+splitStringBuilder.get(3)+")"+splitStringBuilder.get(1)+")."+splitStringBuilder.get(2);
	    	            sb.append(tab);
	    	            sb.append(parameterSX);
	    	            //System.out.println("parameterSX: "+ parameterSX);
	    	            sb.append(".sum");
	    	    		sb.append("(");
	    	    		sb.append(getStringBuilderSingleElement(arrayToSumOrMult.get(0), level, symTable, ereditClasses));
	    	    		sb.append(",");
	    	    		sb.append(getStringBuilderSingleElement(arrayToSumOrMult.get(1), level, symTable, ereditClasses));
	    	    		sb.append(")");
	    	    		sb.append(";\n");
	    	            
	    	            String newNameVariable = parSX_senza_self+"#"+splitStringBuilder.get(3);
	    	            symTable.putSymbol(level,newNameVariable, new SymbolType(VeraSX,type1,"",null,null,level,null)); 
	    	        }
	    			return sb.toString();
	    			
	    		}else{
	    			return "ERROR";
	    		}
    		}else{
    			return "ERROR";
    		}
    		
    	}
		return "ERROR";
	}
	
	
	public static String getTypeSingleElement(String singleEl, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		String type = CheckType.check(singleEl);
		if(type.equals("Object")){
			//o semplice variabile
			//oppure p.attr
			if(singleEl.contains(".")){
				ArrayList<String> withouthPoint = tokenParameters(singleEl, ".");
				String className = getVariableType(withouthPoint.get(0),level,symTable, ereditClasses);
				boolean isOK = isCorrectClassAttributeUsed(singleEl,className,level,symTable);
				if(isOK)
					type = getVariableType(singleEl,level,symTable, ereditClasses);
					//type = getVariableType(singleEl+"#"+className,level,symTable, ereditClasses);
			}else{
				type = getVariableType(singleEl,level, symTable, ereditClasses);
			}
		}
		return type;
	}
	
	
	public static String getStringBuilderSingleElement(String singleEl, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		StringBuilder sb = new StringBuilder();
		String type = CheckType.check(singleEl);
		if(type.equals("Object")){
			//o semplice variabile
			//oppure p.attr
			if(singleEl.contains(".")){
				ArrayList<String> withouthPoint = tokenParameters(singleEl, ".");
				String className = getVariableType(withouthPoint.get(0),level,symTable, ereditClasses);
				sb.append("(("+className+")"+withouthPoint.get(0)+")."+withouthPoint.get(1));
			}else{
				sb.append(singleEl);
			}
		}if(type.equals("Tupla")){
			//(1,2,3)
			sb.append("new Tupla(\""+singleEl+"\")");
		}if(type.equals("GenericList")){
			//[1,2,3]
			sb.append("new GenericList(\""+singleEl+"\")");
		}
		
		return sb.toString();
	}
	
	
	public static String getClassNameOfFunction(String variableName,int level,SymbolTable symTable, ArrayList<String> ereditClasses){
		
		Hashtable<String,SymbolType> allONEscope = symTable.getSymTable(1);
		
		Enumeration<String> e = allONEscope.keys();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			if(key.contains(variableName)){
				ArrayList<String> a = tokenParameters(key,"#");
				return a.get(2);
			}
		}
		
		return null;
	}
	
	
	public static String getClassNameOfAttribute(String attributeName,String istanceName, int level, SymbolTable symTable){
		String className = null;
		Hashtable<String,SymbolType> allONEscope = symTable.getSymTable(1);
		Enumeration<String> e = allONEscope.keys();
		while(e.hasMoreElements()){
			String keyONE = e.nextElement();
			if(keyONE.contains(attributeName)){
				ArrayList<String> a = tokenParameters(keyONE,"#");
				className = a.get(1);
			}
		}
		
		if(className!=null){
			//aggiorno la tabella dei simboli -> settando istanceName di tipo "className"
			ArrayList<Integer> scopeMAX = new ArrayList<Integer>();
			ArrayList<Integer> allKeys = symTable.getAllScope();
			Iterator<Integer> it = allKeys.iterator();
			while(it.hasNext()){
				Integer numberScoper = it.next();
				if(numberScoper <= level)
					scopeMAX.add(numberScoper);
			}
			Collections.sort(scopeMAX);
			int k = scopeMAX.size()-1;
			while(k>=0){
				SymbolType s = symTable.getSymbol(scopeMAX.get(k),istanceName);
				if(s!=null){
					if(s.getType().equals("Object")){
						//System.out.println("ffffffffffffffffffffffffffff");
						s.setType(className);
						symTable.putSymbol(k, istanceName, s);
					}
				}
				k--;
			}
			
			//creo pure -> p.attr#className e p.attr2#className se attr e attr2 appartengono alla classeName
			allONEscope = symTable.getSymTable(1);
			e = allONEscope.keys();
			while(e.hasMoreElements()){
				String keyONE = e.nextElement();
				if(keyONE.contains("#" +className)){
					SymbolType s = symTable.getSymbol(1,keyONE);
					if(!s.getType().equals("function") && !s.getType().equals("class") && s.getOwner().equals(className)){
						String newElem = istanceName + "." + s.getName() + "#" + className;
						//System.out.println("newElem: " +newElem);
						symTable.putSymbol(level, newElem, s);
					}
				}
			}
		}
		
		return className;
	}
	
	
	//funzione usata solo per riconoscere i seguenti casi:
	//1) elemento[-1]
	//2) elemento[4:-1]
	//3) elemento[x:-1]
	public static boolean isSubTupLisDictNEGATIVEindex(String lastParameter, int level, SymbolTable symTable, ArrayList<String> ereditClasses){
		ArrayList<String> whithoutParentesi = tokenParameters(lastParameter,"[]");
		if(whithoutParentesi.size()==2){
			//whithoutParentesi.get(0) = nome dizionario/tupla/lista
			//whithoutParentesi.get(1) = elemento con indice negativo
			
			if(CheckType.check(whithoutParentesi.get(0)).equals("Object") ){
				String type = getVariableType(whithoutParentesi.get(0),level, symTable,ereditClasses);
				if(type.equals("Tupla") || type.equals("GenericList") || type.equals("PDictionary")){
					if(whithoutParentesi.get(1).contains(":")){
						return true;
					}else{
						if(whithoutParentesi.get(1).contains("-") && !whithoutParentesi.get(1).contains("+\\-*/\\^&\\|;<>=%")){
							return true;
						}
					}
				}
				
			}
		}
		return false;
	}
	
	
}
