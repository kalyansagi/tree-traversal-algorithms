package main;

import main.constants.Selection;
import main.entity.Node;
import main.implementation.TraversalAlgorithms;

import java.util.*;

public class Application {

    final static private String GOAL_STATE = "123804765";

    /**
     * This is a main function to start the application.
     * We have 3 type of algorithms that can be executed based on user decision.
     *
     * @param args
     */
    public static void main(String[] args) {
        String initial = random();
        TraversalAlgorithms traversalAlgorithms = TraversalAlgorithms.createTraversalAlgorithms(new Node(initial), GOAL_STATE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the type of search you want to perform:  \n (Note: bfs or dfs or ucs only)");
        String userSelection = scanner.next();
        if (userSelection.equalsIgnoreCase(Selection.BFS.name())) {
            traversalAlgorithms.breadthFirstSearch();
        } else if (userSelection.equalsIgnoreCase(Selection.DFS.name())) {
            traversalAlgorithms.depthFirstSearch();
        } else if (userSelection.equalsIgnoreCase(Selection.UCS.name())) {
            traversalAlgorithms.uniformCostSearch();
        } else System.out.println("Selection not found");
    }

    /**
     * This method helps to generate a random 9 digit string that will follow an order or puzzle.
     * Making sure there are no duplicates in the numbers.
     *
     * @return random puzzle string
     */
    private static String random() {
        List<Integer> numbers = new ArrayList<>();
        int i = 0;
        while (i <= 9) {
            numbers.add(i);
            i++;
        }
        Collections.shuffle(numbers);
        StringBuilder sb = new StringBuilder(9);
        for (int j = 0; j < 9; j++) {
            sb.append(numbers.get(j));
        }
        return sb.toString();
    }

}
