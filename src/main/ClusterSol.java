package main;

import java.util.ArrayList;

/*
  Cluster Solution class which takes a cluster ArrayList arrangement.
 */
public class ClusterSol {
  private ArrayList<Integer> cluster;

  public ClusterSol(ArrayList<Integer> cluster) {
    this.cluster = cluster;
  }

  /**
   * This method takes in 2D int array which is symmetrical and
   * should only contain 0 or 1.
   *
   * @param mdg a symmetrical 2D matrix of 0's and 1's
   * @return fitness value of int
   */
  public int evmFitness(int[][] mdg) {
    if (cluster == null || cluster.isEmpty()) {
      return -1;
    }

    int evm = 0;
    for (int row = 0; row < mdg.length - 1; row++) {
      for (int col = row + 1; col < mdg.length; col++) {
        int c1 = cluster.get(row);
        int c2 = cluster.get(col);

        if (c1 == c2) {
          // EVM = EVM + 2*M[j][k] – 1
          evm = evm + 2 * mdg[row][col] - 1;
        }
      }
    }

    return evm;
  }

  /**
   * This method changes a random element in ArrayList with a random value.
   * Doesn't return anything.
   */
  public void smallChange() {
    ArrayList<Integer> res = new ArrayList<>(cluster);

    int randIndex = Utility.UI(1, res.size() - 1);
    int randValue = Utility.UI(1, res.size() - 1);

    // replace element at the specific index
    res.set(randIndex, randValue);
    cluster = new ArrayList<>(res);
  }

  public ArrayList<Integer> getCluster() {
    return cluster;
  }

  @Override
  public String toString() {
    return "ClusterSol: " + cluster;
  }
}