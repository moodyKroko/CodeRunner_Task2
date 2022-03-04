package main;

import java.util.ArrayList;

public class Munch {

  /**
   * This takes in an int which determines how many iterations to run the Hill climbing algorithm for
   * and 2D array of integers.
   * <br>
   * The algorithm will find linked modules and groups them in a cluster which will be then returned.
   *
   * @param iteration number of times to run the MunchAlgorithm
   * @param mdg 2D array integers of only 0's and 1's
   * @return {@code ArrayList<Integer>} that contains the arrangement of clusters for the 2D array
   */
  public static ArrayList<Integer> MunchAlgo(int iteration, int[][] mdg) {
    if (!validMDG(mdg)) {
      return null;
    }

    // new cluster arrangement object
    ClusterSol sol = new ClusterSol(initClustArrangement(mdg));

    int f = sol.evmFitness(mdg);

    for (int i = 0; i < iteration; i++) {
      // backup copy
      ClusterSol oldSol = new ClusterSol(sol.getCluster());

      sol.smallChange();
      int newF = sol.evmFitness(mdg);

      System.out.println("iter: " + i + ", f: " + f + ", newF: " + newF);

      if (f > newF) {
        sol.setCluster(oldSol.getCluster());
      } else {
        f = newF;
      }
    }

    System.out.println("Fitness value: " + f);
    return sol.getCluster();
  }

  /**
   * Checks if the given 2D array is a valid MDG.<br>
   * It checks for null, empty, equal length, valid data and symmetry.
   *
   * @param mdg a 2D array of integer.
   * @return {@code boolean} true/false
   */
  public static boolean validMDG(int[][] mdg) {
    if (mdg == null || mdg.length == 0) {
      return false;
    }

    for (int i = 0; i < mdg.length; i++) {
      // check if rows & col are equal
      if (mdg.length != mdg[i].length) {
        return false;
      }

      for (int j = 0; j < mdg.length; j++) {
        // check for values other than 0,1
        if (mdg[i][j] < 0 || mdg[i][j] > 1) {
          return false;
        }

        // check to make sure node itself doesn't have an edge
        // node 1 should be 0 as there's no other node 1
        if ((i == j) && (mdg[i][j] != 0)) {
          return false;
        }

        // check if there's symmetry
        if (mdg[i][j] != mdg[j][i]) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Takes in 2D array of integer and produces {@code ArrayList<Integer>} that ranges from 1 to {@code mdg.length}.
   * @param mdg 2D array of integer
   * @return {@code ArrayList<Integer>} with same length as that of {@code mdg}
   */
  public static ArrayList<Integer> initClustArrangement(int[][] mdg) {
    ArrayList<Integer> res = new ArrayList<>();

    // create random clustering points to start
    for (int i = 1; i <= mdg.length; i++) {
      res.add(i);
    }

    return res;
  }
}