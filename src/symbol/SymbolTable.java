package symbol;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;


/*classe che contiene la definizione della tabella dei simboli, costituita da una hashtable doppiamente connessa. 
 * L'uso dell'hashtable permette di ridurre la complessita' di ricerca di un elemento, con conseguente riduzione dei tempi.
 * Per maggiori dettagli vedi relazione allegata
 * 
 * */
public class SymbolTable {

	Hashtable<Integer,Hashtable<String,SymbolType>> symtable;
	
	public SymbolTable(){
		symtable = new Hashtable<Integer,Hashtable<String,SymbolType>>();
	}
	
	public int getSize(){
		return symtable.size();
	}
	
	public void clearSymTable(Integer level){

		Object[] o=getKeys();		
		for(Integer i=0;i<o.length;i++){
			if(Integer.parseInt(o[i].toString())>level)
				symtable.remove(Integer.parseInt(o[i].toString()));
			
		}

	}
	
	public ArrayList<Integer> getAllScope(){
		ArrayList<Integer> allKeys = new ArrayList<Integer>();
		Enumeration<Integer> k = symtable.keys();
		while(k.hasMoreElements()){
			Integer singleK = k.nextElement();
			allKeys.add(singleK);
		}
		return allKeys;
	}
	
	public Object [] getKeys(){
		Set<Integer> set=symtable.keySet();
		Object i[]=set.toArray();
		return i;
	}
	
	public Hashtable<Integer, Hashtable<String, SymbolType>> getScopeSym() {
		return symtable;
	}

	public Hashtable<String,SymbolType> getSymTable(Integer key){
		return symtable.get(key);
	}
	
	public void putSymbol(Integer key,String key_2,SymbolType sym){
		
		if(symtable.containsKey(key))
		{	
			Hashtable<String,SymbolType> value =symtable.get(key);
			value.put(key_2, sym);
			symtable.put(key, value);
		}
		else
		{
			Hashtable<String,SymbolType> tmp = new Hashtable<String,SymbolType>();
			tmp.put(key_2, sym);
			symtable.put(key,tmp);	
		}
	}
	
	public SymbolType getRegexConstructor(String cName, String cOwner){
		Hashtable<String,SymbolType> sym = getSymTable(1);
		Enumeration e = sym.keys();
		while(e.hasMoreElements()){
			String Ckey = (String) e.nextElement();
			String CurrName = sym.get(Ckey).getName();
			String CurrOwner = sym.get(Ckey).getOwner();
			if(CurrName.equals(CurrOwner)){
				if(CurrName.equals(cName) && CurrOwner.equals(cOwner) && sym.get(Ckey).getType().equals("constructor")){ 
					//System.out.println("#FOUND CONSTRUCTOR: "+Ckey);
					return sym.get(Ckey); 
				}
			}
		}
		return null;
	}
	
	public SymbolType getSymbol(Integer scope, String key){
		if(symtable.get(scope)!=null){
			return symtable.get(scope).get(key);
		}
		else
			return null;
	}
	
	public void remove(Integer scope,String key){
		symtable.get(scope).remove(key);
	}
	
	public ArrayList<Hashtable<String,SymbolType>> getAllSymbol(int level){
		
		ArrayList<Hashtable<String,SymbolType>> array=new ArrayList<Hashtable<String,SymbolType>>();
		for(int i=level;i<symtable.size();i++)
			array.add(symtable.get(i));
		return array;
	}
	
	public boolean checkEntity(String symName){
		//System.out.println("I SEARCH: "+symName.split("#")[0]);
		for(int i=0;i<getSize();i++){
			//System.out.println("I SEARCH: "+getSymbol(i, symName).toString());
			if(getSymbol(i, symName)!=null && getSymbol(i, symName).getName().equals(symName.split("#")[0])){
				//System.out.println("#FOUND!");
				//System.out.println("xdxd"+getSymbol(i, symName).toString());
				return true;
			}
		}
		return false;
	}
	
	public boolean checkObject(String symName){
		//System.out.println("I SEARCH OBJECT: "+symName);
		for(int i=0;i<getSize();i++){
			if(getSymbol(i, symName)!=null && getSymbol(i, symName).getName().equals(symName)){
				//System.out.println("#FOUND!");
				//System.out.println("xdxd"+getSymbol(i, symName).toString());
				return true;
			}
		}
		return false;
	}
	
	public boolean checkClass(String symName){
		//System.out.println("I SEARCH CLASS: "+symName);
			if(getSymbol(0, symName)!=null && getSymbol(0, symName).getName().equals(getSymbol(0, symName).getOwner())){
				//System.out.println("#FOUND!");
				//System.out.println("xdxd"+getSymbol(0, symName).toString());
				return true;
			}
		//System.out.println("#IT ISN'T A CLASS!");
		return false;
	}
	
	
	
	
	public boolean checkMethod(String symName){
		//System.out.println("I SEARCH: "+symName);
		for(int i=0;i<getSize();i++){
			if(getSymbol(i, symName)!=null){
				String p=getSymbol(i, symName).getName();
				if(p.equals(symName.split("#")[0])){
						//System.out.println("#FOUND!");
						return true;
				}
			}
		}
		return false;
	}
	
	//Si suppone che si passi gia' il nome splittato..
	public boolean checkEquivalentMethods(String symName){
		//System.out.println("I SEARCH: "+symName);
		for(int i=0;i<getSize();i++){
			if(getSymbol(i, symName)!=null){
					//System.out.println("PRESENT!");
					return true;
			}
		}
		//System.out.println("NOT PRESENT!");
		return false;
	}
	
	public String checkType(String symName){
		//System.out.println("I SEARCH TYPE FOR: "+symName);
		for(int i=0;i<getSize();i++){
			if(getSymbol(i, symName)!=null && getSymbol(i, symName).getName().equals(symName)){
				//System.out.println("#FOUND!");
				return getSymbol(i, symName).getType();
			}
		}
		return null;
	}
	
	public int getParamSize(String symName){
		//System.out.println("I SEARCH TYPE FOR: "+symName);
		for(int i=0;i<getSize();i++){
			if(getSymbol(i, symName)!=null && getSymbol(i, symName).getName().equals(symName) && getSymbol(i, symName).getParameters()!=null){
				//System.out.println("#FOUND!");
				return getSymbol(i, symName).getParameters().size();
			}
		}
		return 0;
	}
	
	public String getClass(String symName){
		//System.out.println("I SEARCH OWNER FOR SYMBOL: "+symName);
		for(int i=0;i<getSize();i++){
			if(getSymbol(i, symName)!=null && getSymbol(i, symName).getName().equals(symName)){
				//System.out.println("#I FOUND: "+getSymbol(i, symName).getType());
				return getSymbol(i, symName).getType();
			}
		}
		return null;
	}
	
	
	public void setIstanceAttribute(String istanceName,String className, int scope){
		Hashtable<String,SymbolType> scopeOne = getSymTable(1);
		Enumeration<String> keys =  scopeOne.keys();
		while(keys.hasMoreElements()){
			String k = keys.nextElement();
			SymbolType sy = scopeOne.get(k);
			if(sy.getOwner()!=null){
				if(sy.getOwner().equals(className) && !sy.getType().equals("class") && !sy.getType().equals("function")){
					String newElem = istanceName +"."+ sy.getName() + "#" + className;
					//System.out.println("newElem: " + newElem);
					putSymbol(scope,newElem, new SymbolType(istanceName +"."+ sy.getName(),sy.getType(),sy.getValue(),null,null,scope,null));
				}
			}
		}
	}
	
	/*
	public void setIstanceAttribute_Classe_Ereditata(String istanceName,String className_reale,String className_ereditata, int scope){
		Hashtable<String,SymbolType> scopeOne = getSymTable(1);
		Enumeration<String> keys =  scopeOne.keys();
		while(keys.hasMoreElements()){
			String k = keys.nextElement();
			SymbolType sy = scopeOne.get(k);
			if(sy.getOwner()!=null){
				if(sy.getOwner().equals(className_ereditata) && !sy.getType().equals("class")){
					String newElem = istanceName +"."+ sy.getName() + "#" + className_reale;
					//System.out.println("newElem_ereditato: " + newElem);
					//putSymbol(scope,newElem, new SymbolType(istanceName +"."+ sy.getName(),sy.getType(),sy.getValue(),null,null,scope,null));
				}
			}
		}
	}
	*/
	
}
