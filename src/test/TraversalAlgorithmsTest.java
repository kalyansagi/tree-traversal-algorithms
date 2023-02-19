package test;

import main.entity.Node;
import main.implementation.TraversalAlgorithms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TraversalAlgorithmsTest {

    private static final String INITIAL_STATE = "134805726";
    private static final String GOAL_STATE = "123804765";
    private Node root = new Node(INITIAL_STATE);
    private TraversalAlgorithms traversalAlgorithms = TraversalAlgorithms.createTraversalAlgorithms(root, GOAL_STATE);

    @Test
    @DisplayName("Verifying BFS with first example in Assignment. i.e., initial state " + INITIAL_STATE)
    void testBreadthFirstSearch() {
        traversalAlgorithms.breadthFirstSearch();
    }

    @Test
    @DisplayName("Verifying DFS with first example in Assignment. i.e., initial state " + INITIAL_STATE)
    void testDepthFirstSearch() {
        traversalAlgorithms.depthFirstSearch();
    }

    @Test
    @DisplayName("Verifying UCS with first example in Assignment. i.e., initial state " + INITIAL_STATE)
    void testUniformCostSearch() {
        traversalAlgorithms.uniformCostSearch();
    }
}
