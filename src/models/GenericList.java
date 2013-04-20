package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import resultJavaTest.*;

import symbol.CheckType;

/*Questa classe e' stata definita per eseguire un mapping del tipo lista; si tratta di un tipo predefinito in python,
 *all'interno del quale e' possibile inserire tipi eterogenei. Per maggiore dettagli vedi relazione allegata.  
 *  */
public class GenericList {


private ArrayList<Object> genericList;
	
	
	public GenericList(){
		genericList = new ArrayList<Object>();
	}
	
	public GenericList(String t){
		//System.out.println("t: " + t);
		if(t==null)
			genericList.add("0");
		else{
			genericList = new ArrayList<Object>();
			ArrayList<String> l = tokenParameters(t, "[]");
			if(l.size()>0){
				ArrayList<String> tup = tokenParameters(l.get(0), ", ");
				Iterator<String> it = tup.iterator();
				while(it.hasNext()){
					genericList.add(it.next());
				}
			}else
				genericList.add("0");
		}
	}
	
	public GenericList(Object t){
		genericList = new ArrayList<Object>();
		genericList.add(t);
	}

	public GenericList(ArrayList<Object> t){
		genericList = t;
	}
	
	
	public ArrayList<Object> getGenericList(){
		return genericList;
	}
	
	public void setGenericList(ArrayList<Object> t){
		genericList = t;
	}
	
	
	public Object resultOperationsGenericList(Object str){
		
		if(str instanceof String){
			ArrayList<GenericList> arrayT = new ArrayList<GenericList>();
			
			ArrayList<String> arrayWithoutPlus = tokenParameters((String)str,"+");
			
			for(int h = 0; h<arrayWithoutPlus.size(); h++){

				int tuplaPosition = -1;
				String flag = null;
				int operando = 1;

				ArrayList<String> arrayWithoutStar = tokenParameters(arrayWithoutPlus.get(h),"*");

				
				for(int p=0; p<arrayWithoutStar.size(); p++){
					
					String ty = CheckType.check((Object)arrayWithoutStar.get(p));
					
					if(ty.equals("GenericList") && tuplaPosition==-1){
						tuplaPosition = p;
						flag = "TROVATO";
					}else if(ty.equals("GenericList") && tuplaPosition!=-1){
						//ho trovato un'altra tupla in precedenza
						flag = "ERROR";
					}else if(!ty.equals("Integer")){
						flag = "ERROR";
					}else if(ty.equals("Integer")){
						operando = operando * Integer.parseInt(arrayWithoutStar.get(p));
					}
				}

				
				if(flag!=null && !flag.equals("ERROR") && tuplaPosition!=-1 ){
					GenericList tmp = new GenericList();
					Object o = new GenericList(arrayWithoutStar.get(tuplaPosition));
					tmp.mult((GenericList)o,operando);
					arrayT.add(tmp);
				}else{
					
					return "";
				}
			}
			

			for(int h=0; h < arrayT.size(); h++){
				GenericList tmp1 = (GenericList)arrayT.get(h);
				Iterator<Object> i= tmp1.getGenericList().iterator();
				while(i.hasNext()){
					genericList.add(i.next());
				}
			}
			
			GenericList tmpResult = new GenericList();
			tmpResult.setGenericList(genericList);
			
			return tmpResult;
			
		}else{
			//System.out.println("Errore! Non stai sommando oggetti dello stesso tipo!");
			return "";
		}
		
		
	}
	
	
	
	
	public void sum(Object t1,Object t2){
		
		if((t1 instanceof GenericList)&&(t2 instanceof GenericList)){

			GenericList tmp1 = new GenericList(((GenericList)t1).getGenericList());	
			GenericList altro1 = new GenericList();
			Iterator<Object> i= tmp1.getGenericList().iterator();
			while(i.hasNext()){
				Object o = i.next();
				altro1.getGenericList().add(o);
			}

			GenericList tmp2 = new GenericList(((GenericList)t2).getGenericList());
			Iterator<Object> i2= tmp2.getGenericList().iterator();
			while(i2.hasNext()){
				Object o = i2.next();
				altro1.getGenericList().add(o);
			}
			
			genericList.clear();
			for(int j=0; j < ((GenericList)altro1).getSize(); j++)
				genericList.add(altro1.getGenericList().get(j));	
		}
		else{
			//System.out.println("Errore! Non stai sommando oggetti dello stesso tipo!");

		}
	}
	
	
	public String returnGenericList(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < genericList.size(); i++){
			sb.append(genericList.get(i).toString());
			if(i!=genericList.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}

	
	public void mult(Object t,Object num){
		
		if((t instanceof GenericList)&&(num instanceof Integer)){
			GenericList tmp=(GenericList)t;
			Integer numeroTmp=(Integer)num;
			for(int x =numeroTmp; x>0; x--){
				for(int i=0; i < tmp.getGenericList().size(); i++){
					genericList.add(tmp.getGenericList().get(i));
				}
			}	
		}
		else{
			//System.out.println("Errore! Non stai moltiplicando oggetti dello stesso tipo!");

		}
		
		
	}
	
	public Object subGenericList(Object subExpres){
		
		String subExpression=new String();
		if(subExpres instanceof String){
			subExpression =(String)subExpres;
		
			ArrayList<Object> newSubGenericList = new ArrayList<Object>();
			
			GenericList tNew = new GenericList(newSubGenericList);
	
			ArrayList<String> withoutParents = tokenParameters(subExpression, "[]");
			
			int indexStart = 0;
			int indexEnd = 0;
			
			if(withoutParents.size()>0){
				if(!subExpression.contains("-")){
					
					if(!subExpression.contains(":")){
						indexStart = Integer.parseInt(withoutParents.get(0));
						indexEnd = Integer.parseInt(withoutParents.get(0));
	
						newSubGenericList.add(genericList.get(indexStart));
						return genericList.get(indexStart);
						
					}else{
						
						if(withoutParents.get(0).charAt(0)==':'){
							//[:2] -> prendo da 0 a 2
							ArrayList<String>indexGenericList = tokenParameters(withoutParents.get(0), ":");
							indexStart = 0;
							indexEnd = Integer.parseInt(indexGenericList.get(0));
						}else if(withoutParents.get(0).charAt(withoutParents.get(0).length()-1)==':'){
							//[1:] -> prendo da 1 fino alla fine
							ArrayList<String>indexGenericList = tokenParameters(withoutParents.get(0), ":");
							indexStart = Integer.parseInt(indexGenericList.get(0));
							indexEnd = genericList.size();
						}else{
							ArrayList<String>indexGenericList = tokenParameters(withoutParents.get(0), ":");
							indexStart = Integer.parseInt(indexGenericList.get(0));
							indexEnd = Integer.parseInt(indexGenericList.get(1));
						}
					}
					
				}else{
					
					if(!subExpression.contains(":")){
						//es: [-2]
						indexStart = genericList.size() + Integer.parseInt(withoutParents.get(0));
						indexEnd = genericList.size() + Integer.parseInt(withoutParents.get(0));
	
						newSubGenericList.add(genericList.get(indexStart));
						return genericList.get(indexStart);
					}else{
						
						if(withoutParents.get(0).charAt(0)==':'){
							//[:2] -> prendo da 0 a 2
							ArrayList<String>indexGenericList = tokenParameters(withoutParents.get(0), ":");
							indexStart = 0;
							indexEnd = genericList.size() + Integer.parseInt(indexGenericList.get(0));
						}else if(withoutParents.get(0).charAt(withoutParents.get(0).length()-1)==':'){
							//[1:] -> prendo da 1 fino alla fine
							ArrayList<String>indexGenericList = tokenParameters(withoutParents.get(0), ":");
							indexStart = genericList.size() + Integer.parseInt(indexGenericList.get(0));
							indexEnd = genericList.size();
						}else{
							ArrayList<String>indexGenericList = tokenParameters(withoutParents.get(0), ":");
							if(indexGenericList.get(0).contains("-"))
								indexStart = genericList.size() + Integer.parseInt(indexGenericList.get(0));
							else
								indexStart = Integer.parseInt(indexGenericList.get(0));
							
							if(indexGenericList.get(1).contains("-"))
								indexEnd = genericList.size() + Integer.parseInt(indexGenericList.get(1));
							else
								indexEnd = Integer.parseInt(indexGenericList.get(1));
		
						}
					}	
				}
					
				//estraggo gli elementi
				for(int i = indexStart ; i< indexEnd ; i++){
					newSubGenericList.add(genericList.get(i));
				}
	
				return tNew;
			
			}
		
		}
		return "";
	}
	
	public String returnGenericListValue(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0; i < genericList.size(); i++){
			sb.append(genericList.get(i).toString());
			if(i!=genericList.size()-1)
				sb.append(",");
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	public Object getOneGenericListElement(Object subExpres){
		
		String subExpression=new String();
		if(subExpres instanceof String){
		subExpression=(String)subExpres;
		ArrayList<String> withoutParents = tokenParameters(subExpression, "[]");
		
		if(!withoutParents.get(0).contains("-")){
			//indice positivo
			return genericList.get(Integer.parseInt(withoutParents.get(0)));
			
		}else{
			//indice negativo
			int index = genericList.size() + Integer.parseInt(withoutParents.get(0));
			return genericList.get(index);
		}
		}
		return "";
	}
	
	public Integer getSize(){
		return genericList.size();
	}
	
	
	public void printGenericList(){
		System.out.println("''''''' GenericList '''''''");
		for(int i=0; i < genericList.size(); i++){
			System.out.println(genericList.get(i).toString());
		}
		System.out.println("'''''''''''''''''''''''''''");
	}
	
	
	//utils function
	public ArrayList<String> tokenParameters(String s,String token) {
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
	
	public Object getObj(int index){
		if(index <= genericList.size()){
			return genericList.get(index);
		}
		else return "";		
	}
	
	
}
