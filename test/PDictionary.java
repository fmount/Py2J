package models;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.StringTokenizer;

/* questa classe e' stata definita per eseguire un mapping della classe dizionario, presente come tipo 
 * predefinito nel linguaggio python. Per maggiori dettagli vedi relazione allegata
 * 
 * 
 * 
 * */
public class PDictionary {

	private Hashtable<Object,Object> dictionary;
	
	
	public PDictionary(){
		dictionary = new Hashtable<Object,Object>();
	}
	
	public PDictionary(Object k, Object v){
		dictionary = new Hashtable<Object,Object>();
		dictionary.put(k, v);
	}
	
	public PDictionary(String t){
		dictionary = new Hashtable<Object,Object>();
		ArrayList<String> all = tokenParameters(t,"{}");
		ArrayList<String> allElements = tokenParameters(all.get(0),",");
		for(int x=0; x<allElements.size();x++){
			ArrayList<String> tmp = tokenParameters(allElements.get(x),":");
			dictionary.put(tmp.get(0), tmp.get(1));
		}	
	}
	
	public PDictionary(Hashtable<Object,Object> d){
		dictionary = d;
	}
	
	
	public Hashtable<Object,Object> getPDictionary(){
		return dictionary;
	}
	
	public void setPDictionary(Hashtable<Object,Object> d){
		dictionary = d;
	}
	
	public String returnPDictionary(){
		StringBuilder sb = new StringBuilder();
		int cont=0;
		Enumeration<Object> e = dictionary.keys();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			sb.append(o.toString()+":"+dictionary.get(o).toString());
			if(cont!=dictionary.size()-1)
				sb.append(",");
			cont++;
		}
		return sb.toString();
	}
	
	public Object getSigleValue(Object key){
		
		Object tmp = dictionary.get(key);
		if(tmp!=null)
			return dictionary.get(key);
		else
			return "";
	}
	
	public String getTypeObject(){
		Enumeration<Object> e = dictionary.keys();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			Object value = dictionary.get(o);
			return value.toString();
		}
		return "Null";
	}
	
	public void insertNewObject(Object key, Object value){
		dictionary.put(key, value);
	}
	
	public Integer getSize(){
		return dictionary.size();
	}
	
	public String returnPDictionaryValue(){
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		int cont=0;
		Enumeration<Object> e = dictionary.keys();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			sb.append(o.toString()+":"+dictionary.get(o).toString());
			if(cont!=dictionary.size()-1)
				sb.append(",");
			cont++;
		}
		sb.append("}");
		return sb.toString();
	}
	
	public void printPDictionary(){
		//System.out.println("Dimensione Dizionario: "+dictionary.size());
		System.out.println("******* DIZIONARIO *******");
		Enumeration<Object> e = dictionary.keys();
		while(e.hasMoreElements()){
			Object o = e.nextElement();
			System.out.println("Key:\t"+ o.toString() + "\tValue: "+dictionary.get(o).toString());
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
	
	
}
