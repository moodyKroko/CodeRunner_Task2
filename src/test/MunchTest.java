package test;

import static main.Munch.validMDG;
import static main.Utility.ReadArrayFile;
import static main.Utility.ReadFileToList;
import static main.Utility.UI;
import static main.Utility.mdgMatrix;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import main.ClusterSol;
import main.Munch;
import main.Utility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MunchTest {

  static int[][] mdgP78;
  static int[][] mdgP55;
  static int[][] mdgP36;

  @BeforeAll
  static void setUp() {
    mdgP78 = ReadArrayFile("Perfect78.txt", " ");
    mdgP55 = ReadArrayFile("Perfect55.txt", " ");
    mdgP36 = ReadArrayFile("Perfect36.txt", " ");
  }

  @Test
  @DisplayName("Test Valid MDG")
  void validMDGReturnTrue() {
    assertTrue(validMDG(mdgP78));
  }

  @Test
  @DisplayName("Test Invalid Data MDG")
  void invalidDataReturnFalse() {
    int[][] mdg = ReadArrayFile("InvalidData.txt", " ");

    assertFalse(validMDG(mdg));
  }

  @Test
  @DisplayName("Test Invalid Length of MDG")
  void invalidMDGLenReturnFalse() {
    int[][] mdg = ReadArrayFile("InvalidLength.txt", " ");
    assertFalse(validMDG(mdg));
  }

  @Test
  @DisplayName("Test Invalid Symmetry")
  void invalidSymmetryReturnFalse() {
    int[][] mdg = ReadArrayFile("InvalidSymmetry.txt", " ");
    assertFalse(validMDG(mdg));
  }

  @Test
  @DisplayName("Test Invalid Node Edge")
  void invalidNodeEdgeReturnFalse() {
    int[][] mdg = ReadArrayFile("InvalidNodeEdge.txt", " ");
    assertFalse(validMDG(mdg));
  }

  @Test
  @DisplayName("Test Perfect78 EVMFitness")
  void testEVMFitnessForPerfect78() {
    ClusterSol cSol = new ClusterSol(ReadFileToList("perfect78Cluster.txt"));

    int fValue = cSol.evmFitness(mdgP78);
    assertEquals(286, fValue);
  }

  @Test
  @DisplayName("Test Perfect55 EVMFitness")
  void testEVMFitnessForPerfect55() {
    ClusterSol cSol = new ClusterSol(ReadFileToList("perfect55Cluster.txt"));

    int fValue = cSol.evmFitness(mdgP55);
    assertEquals(165, fValue);
  }


  @Test
  @DisplayName("Test Perfect36 EVMFitness")
  void testEVMFitnessForPerfect36() {
    ClusterSol cSol = new ClusterSol(ReadFileToList("perfect36Cluster.txt"));

    int fValue = cSol.evmFitness(mdgP36);
    assertEquals(84, fValue);
  }

  @Test
  @DisplayName("Changed list is not the same as original")
  void smallChangeReturnChangedArrList() {
    ClusterSol cSol = new ClusterSol(Munch.initClustArrangement(mdgP78));
    int fitness = cSol.evmFitness(mdgP78);

    cSol.smallChange();
    int newFitness = cSol.evmFitness(mdgP78);

    assertNotEquals(fitness, newFitness);
  }

  @Test
  @DisplayName("Test Munch with Perfect78")
  void testPerfect78Munch() {
    ClusterSol sol = null;
    ArrayList<ClusterSol> solList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      sol = new ClusterSol(Munch.MunchAlgo(7000, mdgP78));
      solList.add(sol);
      System.out.print(sol.evmFitness(mdgP78) + ", ");
    }

    ClusterSol bestSol = solList.get(0);
    for (ClusterSol sols : solList) {
      if (bestSol.evmFitness(mdgP78) < sols.evmFitness(mdgP78)) {
        bestSol.setCluster(sols.getCluster());
      }
    }

    System.out.println("\nBest fitness: " + bestSol.evmFitness(mdgP78) + "\n" + bestSol);
  }


}