package test;

import static main.Munch.validMDG;
import static main.Utility.ReadArrayFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import main.ClusterSol;
import main.Munch;
import main.SingleQuestions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MunchTest {
  static int[][] mdgP78;

  @BeforeAll
  static void setUp() {
    mdgP78 = ReadArrayFile("Perfect78.txt", " ");
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
  @DisplayName("Test EVMFitness")
  void evmFitnessReturns0() {
    ClusterSol cSol = new ClusterSol(Munch.initClustArrangement(mdgP78));

    int fValue = SingleQuestions.evmFitness(cSol.getCluster(), mdgP78);
    assertEquals(0, fValue);
  }

  @Test
  @DisplayName("Changed list is not the same as original")
  void smallChangeReturnChangedArrList() {
    ClusterSol cSol = new ClusterSol(Munch.initClustArrangement(mdgP78));

    ArrayList<Integer> unChangedList = new ArrayList<>(cSol.getCluster());

    cSol.smallChange();

    assertNotEquals(unChangedList, cSol.getCluster());
  }

  @Test
  @DisplayName("Test Munch with Perfect78")
  void testPerfect78Munch() {
    System.out.println(Munch.MunchAlgo(3000, mdgP78));
  }


}