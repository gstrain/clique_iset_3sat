import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * IT 328 Assignment 1
 * 
 * @version 001
 * @author gstrain
 * 
 *         IT328 Program to read in graph information from file and then use
 *         k-clique information to reduce the independent set problem to the
 *         k-clique problem to find the maximum independent set for a given
 *         graph
 *
 */
public class SolveISet {

	public static void main(String[] args) {
		/*
		 * This will have to be different depending on workspace. Eventually, this will
		 * be localized to the files on the Linux server for turn-in
		 */

		if (args.length > 0) {
			
			String fileName = args[0];

			File file = new File(fileName);

			try {
				Scanner sc = new Scanner(file);

				int[][] graphData = new int[10][10];

				while (sc.hasNextLine()) {

					int size = Integer.parseInt(sc.next());

					graphData = new int[size][size];

					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							int val = Integer.parseInt(sc.next());
							if (i != j) {
								if (val == 0) {
									graphData[i][j] = 1;
								} else {
									graphData[i][j] = 0;
								}
							} else {
								graphData[i][j] = val;
							}
						}
					}

					SolveClique.findKClique(graphData);

					if (size == 0)
						break;
				}

				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("File could not be found.");
			}
		} else {
			System.out.println("No file specified.");
		}

	}

}
