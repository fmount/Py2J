package util;

public class ForRange {

	/*
	*
	***********************************  GESTIONE RANGE PER CICLI FOR **************************
	*
	*/
	
	public static int checkrange(String range){
		String[] rangeStmt = range.split("#");
		return rangeStmt.length;
	}
	
	public static String getRange(String range){
		String[] par = range.split("#");
		String toRet="";
		for(int i=Integer.parseInt(par[0]);i<Integer.parseInt(par[1]);i++){
			toRet+=String.valueOf(i)+",";
		}
		toRet = toRet.substring(0,toRet.length()-1);
		return toRet;
	}
	
	public static String getRangeHop(String range){
		String[] par = range.split("#");
		String toRet="";
		for(int i=Integer.parseInt(par[0]);i<Integer.parseInt(par[2]);i++){
			toRet+=String.valueOf(i)+",";
		}
		toRet = toRet.substring(0,toRet.length()-1);
		return toRet;
	}
	
	public static int getHop(String range){
		String[] par = range.split("#");
		if(par.length>=2)
			return Integer.parseInt(par[1]);
		else return -1;
	}

}
