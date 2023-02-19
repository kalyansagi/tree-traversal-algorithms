package main.helper;

import main.constants.Moves;
import main.entity.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Helper {
    /**
     * This function helps to print the details of the search traversal.
     * i.e., the nodes that were explored, moves required at each step, and final path to reach the goal state.
     *
     * @param exploredNodes
     * @param last
     * @param root
     */
    public static void printMetrics(ArrayList<Node> exploredNodes, Node last, Node root) {
        HashMap<Node, Node> childAndParent = new HashMap<>();
        Stack<Node> solution = new Stack<>();
        for (Node node : exploredNodes) {
            if (node.getState().equals(root.getState())) childAndParent.put(node, null);
            else childAndParent.put(node, node.getParent());
        }
        ArrayList<Moves> path = new ArrayList<>();
        solution.push(last);
        Node parent = childAndParent.get(last);
        while (parent != null) {
            solution.push(parent);
            parent = childAndParent.get(parent);
        }
        int moves = solution.size();
        while (!solution.isEmpty()) {
            Node popNode = solution.pop();
            display(popNode.getState());
            if (popNode.getState().equalsIgnoreCase(last.getState())) break;
            System.out.println("\nFrom here, move " + trackMoves(popNode.getState(), solution.peek().getState()) + " =>");
            path.add(trackMoves(popNode.getState(), solution.peek().getState()));
            System.out.println();
        }

        System.out.println("\nTotal moves to reach goal: " + (moves - 1));
        //System.out.println("Nodes visited: " + exploredNodes.size());
        System.out.println("And thus we are finished, with path " + path);
    }

    /**
     * This function is helping us to calculate the difference in blank space position from start to end and classify the move based on its weight.
     * i.e., LEFT or RIGHT or UP or DOWN
     *
     * @param start
     * @param end
     * @return
     */
    private static Moves trackMoves(String start, String end) {
        int difference = start.indexOf('0') - end.indexOf('0');
        if (difference == 1) return Moves.LEFT;
        else if (difference == 3) return Moves.UP;
        else if (difference == -1) return Moves.RIGHT;
        else if (difference == -3) return Moves.DOWN;
        else return null;
    }

    /**
     * This function is utilized to print the puzzle and its digits in a matrix pattern.
     *
     * @param state
     */
    public static void display(String state) {
        System.out.println(state.charAt(0) + "|" + state.charAt(1) + "|" + state.charAt(2) + "\n" + state.charAt(3) + "|" + state.charAt(4) + "|" + state.charAt(5) + "\n" + state.charAt(6) + "|" + state.charAt(7) + "|" + state.charAt(8));
    }

}
