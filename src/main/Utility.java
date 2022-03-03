package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 * A utility class for things like creating random things, creating matrix by given len.
 */
public class Utility {

  private static Random rand;

  /**
   * Generates uniformly distributed random integers
   *
   * @param minVal minimum value it can generate
   * @param maxVal maximum value up-to it can generate
   * @return a random value based on range of minVal to maxVal
   */
  public static int UI(int minVal, int maxVal) {
    int min = Math.min(minVal, maxVal);
    int max = Math.max(minVal, maxVal);
    if (rand == null) {
      rand = new Random();
      rand.setSeed(System.nanoTime());
    }
    return (rand.nextInt((max - min) + 1) + min);
  }

  /**
   * Method which reads data from files for testing purpose
   * @param filename  name of the file to read
   * @param sep type of seperator
   * @return a 2D array of type Integer
   */
  public static int[][] ReadArrayFile(String filename, String sep) {
    int[][] res = null;
    try {
      BufferedReader input = null;
      input = new BufferedReader(new FileReader(filename));
      String line = null;
      int nCol = 0;
      int nRow = 0;

      while ((line = input.readLine()) != null) {
        ++nRow;
        String[] columns = line.split(sep);
        nCol = Math.max(nCol, columns.length);
      }
      res = new int[nRow][nCol];
      input = new BufferedReader(new FileReader(filename));
      int i = 0, j = 0;
      while ((line = input.readLine()) != null) {

        String[] columns = line.split(sep);
        for (j = 0; j < columns.length; j++) {
          res[i][j] = Integer.parseInt(columns[j]);
        }
        i++;
      }
    } catch (Exception E) {
      System.out.println("+++ReadArrayFile: " + E.getMessage());
    }
    return (res);
  }

  /**
   * Generate randomly filled array matrix of given length which
   * is symmetrical.
   *
   * @param len length of the array size
   * @return 2D array with equal row and column size
   */
  public int[][] mdgMatrix(int len) {
    int[][] res = new int[len][len];

    for (int row = 0; row < len; row++) {
      for (int col = 0; col < len; col++) {
        if (row == col) {
          res[row][col] = 0;
          continue;
        }

        int randVal = Utility.UI(0, 1);
        res[row][col] = randVal;
        res[col][row] = randVal;
      }
    }

    return res;
  }
}