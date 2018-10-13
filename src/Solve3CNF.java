import java.io.File;
import java.io.FileNotFoundException;
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

			File file = new File(fileName);

			try {
				Scanner sc = new Scanner(file);

				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					int[][] graphData = convertCNF(line);
					SolveClique.findKClique(graphData);
				}

				sc.close();

			} catch (FileNotFoundException e) {
				System.out.println("File could not be found.");
			}
		} else {
			System.out.println("No file specified.");
		}
	}

	public static int[][] convertCNF(String line) {
		// set up
		String[] lineArray = line.split(" ");

		int size = Integer.parseInt(lineArray[0]);
		int[] array = new int[lineArray.length - 1];

		for (int i = 1; i < lineArray.length; i++) {
			array[i - 1] = Integer.parseInt(lineArray[i]);
		}

		int[][] graphData = new int[size * 2][size * 2];

		// populate the diagonal
		for (int i = 0; i < graphData.length; i++) {
			graphData[i][i] = 1;
		}

		// populate the graph based on the information from the line
		for (int i = 0; i < array.length; i++) {
			int val = i / 3;
			val = val * 3;
			int val2 = val + 3;

			for (int j = 0; j < array.length; j++) {
				if (array[j] != 0) {
					int iIndex = 0;
					int jIndex = 0;

					// set iIndex
					if (array[i] > 0) {
						iIndex = array[i] - 1;
					} else {
						iIndex = (-(array[i])) + (size - 1);
					}

					// set jIndex
					if (array[j] > 0) {
						jIndex = array[j] - 1;
					} else {
						jIndex = (-(array[j])) + (size - 1);
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
