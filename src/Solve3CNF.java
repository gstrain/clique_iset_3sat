import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Solve 3CNF Program for Assignment 1 in 328 to reduce 3CNF to K-clique to find
 * the satisfiability
 * 
 * @author gstrain
 *
 */
public class Solve3CNF {

	public static void main(String[] args) {

		if (args.length > 0) {
			String fileName = args[0];

//			File file = new File(fileName);

//			try {
//				Scanner sc = new Scanner(file);
//
//				while (sc.hasNextLine()) {
//					String line = sc.nextLine();
			int[][] graphData = convertCNF("4 2 1 -3 -1 -2 4 2 -4 3");
			printGraph(graphData);
//					SolveClique.findKClique(graphData);
//				}

//				sc.close();

//			} catch (FileNotFoundException e) {
//				System.out.println("File could not be found.");
//			}
		} else {
			System.out.println("No file specified.");
		}
	}
	
	public static void printGraph(int[][] graph) {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}
	}

	public static int[][] convertCNF(String line) {
		// set up
		int size = Integer.parseInt(line.substring(0, 1));
		String toParse = line.substring(2);
		toParse = toParse.replaceAll("\\s+", "");
		
		System.out.println(toParse);

		int[] array = new int[toParse.length()];
		
		int count = 0;

		for (int i = 0; i < toParse.length(); i++) {
			String a = toParse.substring(i, i + 1);

			if (!a.equals("-")) {
				array[i] = Integer.parseInt(a);
			} else {
				int val = Integer.parseInt(toParse.substring(i + 1, i + 2)) * -1;
				array[i] = val;
				toParse = toParse.substring(0, i) + toParse.substring(i + 1);
				count++;
			}
		}
		
		int[] array2 = new int[array.length - count];
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array2[i] = array[i];
			}
		}
		
		array = array2;

		int[][] graphData = new int[size * 2][size * 2];

		for (int i = 0; i < array.length; i++) {
			int val = i / 3;
			val = val * 3;
			int val2 = val + 3;

			for (int j = 0; j < array.length; j++) {
				if (array[j] != 0) {
					int iIndex = 0;
					int jIndex = 0;
					
					System.out.println(array[i] + " " + array[j]);
					
					// set iIndex
					if (array[i] > 0) {
						iIndex = array[i] - 1;
					} else {
						iIndex = (-(array[i])) + 3;
					}
					
					// set jIndex
					if (array[j] > 0) {
						jIndex = array[j];
					} else {
						jIndex = -(array[j]) + 3;
					}
					
					if (!(val <= j && j < val2)) {	
						if (array[i] != -array[j]) {
							graphData[iIndex][jIndex] = 1;
						} else {
							graphData[iIndex][jIndex] = 0;
						}
					} 
				}
			}
		}

		return graphData;
	}

}
