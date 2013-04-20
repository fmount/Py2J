package translator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class MainTraslator {

	public static void main(String args[]){
		try {
				
			System.out.println("\n\n*********  SCELTA FILE  *********");
			System.out.println("1 - AllTest.py");
			System.out.println("2 - Assignament.py");
			System.out.println("3 - Carte.py");
			System.out.println("4 - SigleEredit.py");
			System.out.println("5 - MultipleEredit.py");
			System.out.println("6 - Exit To Program");
			System.out.println("Inserisci un numero per scegliere il file da eseguire: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int selectFile = Integer.parseInt(br.readLine());
			System.out.println("*********  ******  *********\n\n");
			
			TranslatorPy2JLex scanner = null;
			switch(selectFile){
				case 1:
					scanner = new TranslatorPy2JLex(new FileReader("AllTest.py"));
					break;
				case 2:
					scanner = new TranslatorPy2JLex(new FileReader("Assignament.py"));
					break;
				case 3:
					scanner = new TranslatorPy2JLex(new FileReader("Carte.py"));
					break;
				case 4:
					scanner = new TranslatorPy2JLex(new FileReader("SingleEredit.py"));
					break;
				case 5:
					scanner = new TranslatorPy2JLex(new FileReader("MultipleEredit.py"));
					break;
				case 6:
					System.exit(0);
					break;
				default:
					System.out.println("Errore! Non hai fatto una scelta valida. ");
					break;
			}
			
			if(scanner!=null){
				TranslatorPy2JCup parser = new TranslatorPy2JCup(scanner);
				Object o = parser.parse();
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
