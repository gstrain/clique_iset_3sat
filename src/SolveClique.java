import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SolveClique {
	
	public static void main(String[] args) {
		/* This will have to be different depending on workspace. Eventually, this will 
		 * be localized to the files on the Linux server for turn-in
		 */
		
		File file = new File("/home/gstrain/Desktop/npc/graphs18.txt");

		try {
			Scanner sc = new Scanner(file);

			int[][] graphData = new int[10][10];

			while (sc.hasNextLine()) {

				int size = Integer.parseInt(sc.next());

				graphData = new int[size][size];
				
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						int val = Integer.parseInt(sc.next());
						graphData[i][j] = val;
					}
				}
				
				findKClique(graphData);

				if (size == 0)
					break;
			}
			
			sc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found.");
		}
	}
	
	public static void findKClique(int[][] graphData) {
		for (int i = 0; i < graphData.length; i++) {
			for (int j = 0; j < graphData[i].length; j++) {
				System.out.print(graphData[i][j]);
			}
			
			System.out.println();
		}
		
		System.out.println("\n");
	}
}
