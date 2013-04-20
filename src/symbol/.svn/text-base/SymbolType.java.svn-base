package symbol;

import java.util.ArrayList;

/*questa classe � stata definita per racchiudere tutti gli attributi di un simbolo, contenuto a sua volta nella symboltable
 *contiene i seguenti attributi: 
 * type
 * scope
 * value
 * name
 * parameters
 * returned
 * owner
 * All'interno della relazione allegata vi e' una spiegazione dettagliata del significato di ciascun attributo sopra scritto
 * 
 * */

public class SymbolType {

	 	private String type;
	 	private int scope;
	    private String value;
	    private String name;
	    private ArrayList<String> parameters;
	    private String returned;
	    private String owner;

	    /* Costruttore utilizzato per costruire simboli di tipo "identifier" */
	    public SymbolType(String name, String type, String value,ArrayList<String> parameters, String returned, int scope,String owner){
	    	this.name=name;
	        this.type = type;
	        this.value=value;
	        this.parameters=parameters;
	        this.returned=returned;
	        this.scope=scope;
	        this.owner=owner;
	        //System.out.println("NEW SYMBOL: \nNAME: "+name+"\nTYPE: "+type+"\nVALUE: "+value+"\nPARAMETERS: "+parameters+"\nRETURN: "+returned+"\nSCOPE: "+scope+"\nOWNER: "+owner+"\n");
	    }
	    
	    public SymbolType(){
	    	this.name="";
	        this.type = "";
	        this.value="";
	        this.parameters=null;
	        this.returned=null;
	        this.scope=0;
	        this.owner="";
	   }

	    

	    public int getScope() {
			return scope;
		}



		public void setScope(int scope) {
			this.scope = scope;
		}



		public String getType() {
			return type;
		}



		public void setType(String type) {
			this.type = type;
		}



		public String getValue() {
			return value;
		}



		public void setValue(String value) {
			this.value = value;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public ArrayList<String> getParameters() {
			return parameters;
		}



		public void setParameters(ArrayList<String> parameters) {
			this.parameters = parameters;
		}



		public String getReturned() {
			return returned;
		}



		public void setReturned(String returned) {
			this.returned = returned;
		}



		public boolean checkType(SymbolType sym){
	        if (sym.type.equals(type))
	            return true;
	        else
	            return false;
	    }

		public boolean checkName(SymbolType sym){
	        if (sym.name.equals(name))
	            return true;
	        else
	            return false;
	    }
		
		public boolean checkParams(SymbolType sym){
	        for(int i=0;i<parameters.size();i++){
	        		if (sym.parameters.get(i).equals(parameters.get(i)))
	        			return true;
	        }
			return false;
		}



		public String getOwner() {
			return owner;
		}



		public void setOwner(String owner) {
			this.owner = owner;
		}
		
		public String toString(){
			return "NEW SYMBOL: \nNAME: "+this.getName()+"\nTYPE: "+this.getType()+"\nVALUE: "+this.getValue()+"\nPARAMETERS: "+this.getParameters()+"\nRETURN: "+this.getReturned()+"\nSCOPE: "+this.getScope()+"\nOWNER: "+this.getOwner()+"\n";
		}
}
