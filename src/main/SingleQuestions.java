package main;

//import static main.CS2004.UI;

import java.util.ArrayList;

public class SingleQuestions {

  //  q2 initial starting point {{{
//  public static ArrayList<Integer> initClustArrangement(int[][] mdg) {
//    if (!validMDG(mdg)) {
//      return new ArrayList<>();
//    }
//
//    ArrayList<Integer> res = new ArrayList<>();
//
//    // create random clustering points to start
//    for (int i = 1; i <= mdg.length; i++) {
//      res.add(i);
//    }
//
//    return res;
//  }

  // q3 fitness function {double or int}
  public static int evmFitness(ArrayList<Integer> clusterA, int[][] mdg) {
//    if (!validMDG(mdg)) {
//      return -1;
//    }

    if (clusterA == null || clusterA.isEmpty()) {
      return -1;
    }

    int evm = 0;
    for (int row = 0; row < mdg.length - 1; row++) {
      for (int col = row + 1; col < mdg.length; col++) {
        int c1 = clusterA.get(row);
        int c2 = clusterA.get(col);

        if (c1 == c2) {
          // EVM = EVM + 2*M[j][k] – 1
          evm += ((2 * mdg[row][col]) - 1);
        }
      }
    }

    return evm;
  }

  // q4 small change operator
//  public static ArrayList<Integer> smallChange(ArrayList<Integer> cArrangement) {
//    ArrayList<Integer> res = new ArrayList<>(cArrangement);
//
//    int randIndex = UI(0, res.size() - 1);
//    int randValue = UI(1, res.size());
//
//    while (true) {
//      if ((randIndex == randValue) || (res.get(randIndex) == randValue)) {
//        randValue = UI(1, res.size() - 1);
//      } else {
//        break;
//      }
//    }
//
//    // replace element at the specific index
//    res.set(randIndex, randValue);
//
//    return res;
//  }
}