package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import symbol.CheckType;

/*classe definita per eseguire un mapping del tipo predefinito in python tupla. Per maggiori dettagli vedi 
 * relazione allegata
 * 
 * */
public class Tupla {

	private ArrayList<Object> tupla;
	
	
	public Tupla(){
		tupla = new ArrayList<Object>();
	}
	
	public Tupla(String t){
		if(t==null)
			tupla.add("0");
		else{
			tupla = new ArrayList<Object>();
			ArrayList<String> l = tokenParameters(t, "()");
			if(l.size()>0){
				ArrayList<String> tup = tokenParameters(l.get(0), ", ");
				Iterator<String> it = tup.iterator();
				while(it.hasNext()){
					tupla.add(it.next());
				}
			}
			else
				tupla.add("0");
		}
	}
	
	public Tupla(Object t){
		tupla = new ArrayList<Object>();
		tupla.add(t);
	}
	
	public Tupla(ArrayList<Object> t){
		tupla = t;
	}
	
	
	public ArrayList<Object> getTupla(){
		return tupla;
	}
	
	public void setTupla(ArrayList<Object> t){
		tupla = t;
	}
	
	
	public Object resultOperationsTupla(Object str){

		//es u = 2*2*(1,2) + (4,5) + (4,5)*2
		if(str instanceof String){
			ArrayList<Tupla> arrayT = new ArrayList<Tupla>();
			
			ArrayList<String> arrayWithoutPlus = tokenParameters((String)str,"[+ ]+");
			
			for(int h = 0; h<arrayWithoutPlus.size(); h++){

				int tuplaPosition = -1;
				String flag = null;
				int operando = 1;

				ArrayList<String> arrayWithoutStar = tokenParameters(arrayWithoutPlus.get(h),"[* ]+");

				
				for(int p=0; p<arrayWithoutStar.size(); p++){
					//(1,2)  e  3  e 4
					String ty = CheckType.check((Object)arrayWithoutStar.get(p));
					
					if(ty.equals("Tupla") && tuplaPosition==-1){
						tuplaPosition = p;
						flag = "TROVATO";
					}else if(ty.equals("Tupla") && tuplaPosition!=-1){
						//ho trovato un'altra tupla in precedenza
						flag = "ERROR";
					}else if(!ty.equals("Integer")){
						flag = "ERROR";
					}else if(ty.equals("Integer")){
						operando = operando * Integer.parseInt(arrayWithoutStar.get(p));
					}
				}

				
				if(flag!=null && !flag.equals("ERROR") && tuplaPosition!=-1 ){
					Tupla tmp = new Tupla();
					Object o = new Tupla(arrayWithoutStar.get(tuplaPosition));
					tmp.mult((Tupla)o,operando);
					tmp.printTupla();
					arrayT.add(tmp);
				}else{
					
					return "";
				}
			}
			

			for(int h=0; h < arrayT.size(); h++){
				Tupla tmp1 = (Tupla)arrayT.get(h);
				Iterator<Object> i= tmp1.getTupla().iterator();
				while(i.hasNext()){
					tupla.add(i.next());
				}
			}
			
			Tupla tmpResult = new Tupla();
			tmpResult.setTupla(tupla);
			
			return tmpResult;
			
		}else{
			//System.out.println("Errore! Non stai sommando oggetti dello stesso tipo!");
			return "";
		}
		
		
	}
	
	
	public void sum(Object t1,Object t2){
		
		if((t1 instanceof Tupla)&&(t2 instanceof Tupla)){
			/*
			Tupla tmp1 = (Tupla)t1;
			Tupla tmp2 = (Tupla)t2; 
			Iterator<Object> i= tmp1.getTupla().iterator();
			while(i.hasNext()){
				tupla.add(i.next());
			}
			Iterator<Object> i2= tmp2.getTupla().iterator();
			while(i2.hasNext()){
				tupla.add(i2.next());
			}
			*/
			Tupla altro1 = new Tupla();
			
			Tupla tmp1 = new Tupla(((Tupla)t1).getTupla());	
			Iterator<Object> i= tmp1.getTupla().iterator();
			while(i.hasNext()){
				Object o = i.next();
				//i.remove();
				altro1.getTupla().add(o);
			}

			//genericList.add(o);
			

			Tupla tmp2 = new Tupla(((Tupla)t2).getTupla());
			Iterator<Object> i2= tmp2.getTupla().iterator();
			while(i2.hasNext()){
				Object o = i2.next();
				altro1.getTupla().add(o);
			}

			
			tupla.clear();
			for(int j=0; j < ((Tupla)altro1).getSize(); j++)
				tupla.add(altro1.getTupla().get(j));	
			
		}
		else{
			//System.out.println("Errore! Non stai sommando oggetti dello stesso tipo!");

		}
	}
	
	
	public String returnTupla(){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < tupla.size(); i++){
			sb.append(tupla.get(i).toString());
			if(i!=tupla.size()-1)
				sb.append(",");
		}
		return sb.toString();
	}

	
	public void mult(Object t,Object num){
		
		if((t instanceof Tupla)&&(num instanceof Integer)){
			Tupla tmp=(Tupla)t;
			Integer numeroTmp=(Integer)num;
			for(int x =numeroTmp; x>0; x--){
				for(int i=0; i < tmp.getTupla().size(); i++){
					tupla.add(tmp.getTupla().get(i));
				}
			}	
		}
		else{
			//System.out.println("Errore! Non stai moltiplicando oggetti dello stesso tipo!");

		}
		
		
	}
	
	public Object subTupla(Object subExpres){
		
		String subExpression=new String();
		if(subExpres instanceof String){
			subExpression =(String)subExpres;
		
		ArrayList<Object> newSubTupla = new ArrayList<Object>();
		
		Tupla tNew = new Tupla(newSubTupla);

		ArrayList<String> withoutParents = tokenParameters(subExpression, "[]");
		
		int indexStart = 0;
		int indexEnd = 0;
		
		if(!subExpression.contains("-")){
			
			//tutti gli indici sono positivi
			
			if(!subExpression.contains(":")){
				indexStart = Integer.parseInt(withoutParents.get(0));
				indexEnd = Integer.parseInt(withoutParents.get(0));
				
				newSubTupla.add(tupla.get(indexStart));
				
				//return tNew;
				return tupla.get(indexStart);
			}else{
				
				if(withoutParents.get(0).charAt(0)==':'){
					//[:2] -> prendo da 0 a 2
					ArrayList<String>indexTupla = tokenParameters(withoutParents.get(0), ":");
					indexStart = 0;
					indexEnd = Integer.parseInt(indexTupla.get(0));
				}else if(withoutParents.get(0).charAt(withoutParents.get(0).length()-1)==':'){
					//[1:] -> prendo da 1 fino alla fine
					ArrayList<String>indexTupla = tokenParameters(withoutParents.get(0), ":");
					indexStart = Integer.parseInt(indexTupla.get(0));
					indexEnd = tupla.size();
				}else{
					ArrayList<String>indexTupla = tokenParameters(withoutParents.get(0), ":");
					indexStart = Integer.parseInt(indexTupla.get(0));
					indexEnd = Integer.parseInt(indexTupla.get(1));
				}
			}
			
		}else{
			//almeno un indice è NEGATIVO
			
			if(!subExpression.contains(":")){
				//es: [-2]
				indexStart = tupla.size() + Integer.parseInt(withoutParents.get(0));
				indexEnd = tupla.size() + Integer.parseInt(withoutParents.get(0));
				
				newSubTupla.add(tupla.get(indexStart));
				
				//return tNew;
				return tupla.get(indexStart);
			}else{
				
				if(withoutParents.get(0).charAt(0)==':'){
					//[:2] -> prendo da 0 a 2
					ArrayList<String>indexTupla = tokenParameters(withoutParents.get(0), ":");
					indexStart = 0;
					indexEnd = tupla.size() + Integer.parseInt(indexTupla.get(0));
				}else if(withoutParents.get(0).charAt(withoutParents.get(0).length()-1)==':'){
					//[1:] -> prendo da 1 fino alla fine
					ArrayList<String>indexTupla = tokenParameters(withoutParents.get(0), ":");
					indexStart = tupla.size() + Integer.parseInt(indexTupla.get(0));
					indexEnd = tupla.size();
				}else{
					ArrayList<String>indexTupla = tokenParameters(withoutParents.get(0), ":");
					if(indexTupla.get(0).contains("-"))
						indexStart = tupla.size() + Integer.parseInt(indexTupla.get(0));
					else
						indexStart = Integer.parseInt(indexTupla.get(0));
					
					if(indexTupla.get(1).contains("-"))
						indexEnd = tupla.size() + Integer.parseInt(indexTupla.get(1));
					else
						indexEnd = Integer.parseInt(indexTupla.get(1));

				}
			}	
		}
			
		//estraggo gli elementi
		for(int i = indexStart ; i< indexEnd ; i++){
			newSubTupla.add(tupla.get(i));
		}
		return tNew;
		}
		return "";
	}
	
	
	public Object getOneTuplaElement(Object subExpres){
		
		String subExpression=new String();
		if(subExpres instanceof String){
		subExpression=(String)subExpres;
		ArrayList<String> withoutParents = tokenParameters(subExpression, "[]");
		
		if(!withoutParents.get(0).contains("-")){
			//indice positivo
			return tupla.get(Integer.parseInt(withoutParents.get(0)));
			
		}else{
			//indice negativo
			int index = tupla.size() + Integer.parseInt(withoutParents.get(0));
			return tupla.get(index);
		}
		}
		return "";
	}
	
	
	public String returnTuplaValue(){
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for(int i=0; i < tupla.size(); i++){
			sb.append(tupla.get(i).toString());
			if(i!=tupla.size()-1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
	
	
	public Integer getSize(){
		return tupla.size();
	}
	
	
	public void printTupla(){
		System.out.println("******* TUPLA *******");
		for(int i=0; i < tupla.size(); i++){
			System.out.println(tupla.get(i).toString());
		}
		System.out.println("*********************");
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
		if(index <= tupla.size())	
			return tupla.get(index);
		else return "";
	}
	
	
}
