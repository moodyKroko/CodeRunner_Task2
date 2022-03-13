package main;
import static main.CS2004.UI;

import main.CS2004;

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
   * This method takes in 2D int array which is symmetrical and should only contain 0 or 1. It calculates fitness based
   * on the cluster arrangements.
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
          evm += ((2 * mdg[row][col]) - 1);
        }
      }
    }

    return evm;
  }

  /**
   * This method changes a random element in ArrayList with a random value. Doesn't return anything.
   */
  public void smallChange() {
    ArrayList<Integer> res = new ArrayList<>(cluster);

    int randIndex = UI(0, res.size() - 1);
    int randValue = UI(1, res.size());

    while (true) {
      if ((randIndex == randValue) || (res.get(randIndex) == randValue)) {
        randValue = UI(1, res.size() - 1);
      } else {
        break;
      }
    }

    // replace element at the specific index
    res.set(randIndex, randValue);
    cluster = new ArrayList<>(res);
  }


  /**
   * It gets the saved cluster Arrangement in as a list.
   *
   * @return {@code ArrayList<Integer>} cluster arrangement of the MDG.
   */
  public ArrayList<Integer> getCluster() {
    return cluster;
  }

  public void setCluster(ArrayList<Integer> cluster) {
    this.cluster = cluster;
  }

  @Override
  public String toString() {
    return "ClusterSol: " + cluster;
  }
}