package main.implementation;

import main.helper.Helper;
import main.entity.Node;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static main.helper.Helper.display;

public class TraversalAlgorithms {
    private final Node node;
    private final String goal;

    private TraversalAlgorithms(@NotNull Node root, String goal) {
        this.node = root;
        this.goal = goal;
    }

    /**
     * A constructor in factory pattern
     */
    public static TraversalAlgorithms createTraversalAlgorithms(@NotNull Node root, String goal) {
        return new TraversalAlgorithms(root, goal);
    }

    /**
     * This is a function to perform the breadth first search for the initial node until it reaches goal state that was initialized through its constructor.
     *
     * @param: nothing
     * @return: void
     */
    public void breadthFirstSearch() {
        /* Retrieving the initial node from the class instance. */
        Node node = this.node;
        System.out.println("Beginning BFS with initial state =>");
        display(node.getState());
        System.out.println("BFS in in progress");
        /* verifying if the start and goal states are already same. */
        if (node.getState().equals(goal)) {
            System.out.println("Initial state and goal state are same.");
            return;
        }
        /* Initialize a queue & adding the initial node to it. */
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        /* Initialize an Arraylist to keep track of explored states during the search. */
        ArrayList<Node> explored = new ArrayList<>();
        /* Conditional loop to iterate until the goal state is reached or the queue becomes empty. */
        while (!node.getState().equals(goal)) {
            if (queue.isEmpty()) {
                System.out.println("Unable to find goal state.");
                return;
            }
            /* Retrieve next node from the queue and adding it to explored list */
            node = queue.poll();
            explored.add(node);
            /* Identifying all the possible neighbor boards based on the moves. */
            ArrayList<Node> neighbors = findNeighbors(node);
            /* Iterating through the list of neighbors and adding them to queue if not explored. */
            for (Node neighbor : neighbors) {
                if (!explored.contains(neighbor)) {
                    /*Set the static variables to re-purpose in final result. */
                    setVariables(neighbor, node);
                    if (neighbor.getState().equals(goal)) {
                        explored.add(neighbor);
                        /* calling an internal function to print node & moves. */
                        Helper.printMetrics(explored, neighbor, this.node);
                        return;
                    }
                    queue.add(neighbor);
                }
            }
        }
    }

    /**
     * This is a function to perform the depth first search for the initial node until it reaches goal state that was initialized through its constructor.
     *
     * @param: nothing
     * @return: void
     */
    public void depthFirstSearch() {
        /* Retrieving the initial node from the class instance and adding it to the stack. */
        Node node = this.node;
        System.out.println("Beginning DFS with initial state =>");
        display(node.getState());
        System.out.println("DFS in in progress");
        /* Initializing a stack collection to keep track of nodes as we search through them. */
        Stack<Node> stack = new Stack<>();
        /* Initialize an Arraylist to keep track of explored states during the search. */
        ArrayList<Node> explored = new ArrayList<>();
        /* Initialize an Arraylist to keep track of neighbor states for various movements of blank space during the search process. */
        ArrayList<Node> neighbors;
        stack.add(node);
        /* Conditional loop to iterate until the goal state is reached or the stack becomes empty. */
        while (!node.getState().equals(goal)) {
            /* pop the node from stack */
            node = stack.pop();
            /* add the popped node to explored list */
            explored.add(node);
            /* verify if the current node is already explored. */
            if (!explored.contains(node))
                explored.add(node);
            /* Finding all possible neighbors with blank space movements */
            neighbors = findNeighbors(node);
            /* Iterate through the list of neighbors, check if the node is explored or not, add to stack if not done.
            Also set the static variables to re-purpose in final result. */
            for (Node adjacent : neighbors) {
                if (!explored.contains(adjacent)) {
                    setVariables(adjacent, node);
                    stack.add(adjacent);
                }
            }
            /* once the goal state is met, print the details like node & path. */
            if (node.getState().equals(goal)) {
                Helper.printMetrics(explored, node, this.node);
                return;
            }
        }
        Helper.printMetrics(explored, node, this.node);
    }

    /**
     * This is a function to perform the uniform cost search for the initial node until it reaches goal state that was initialized through its constructor.
     * i.e., the shortest path from start to end state
     *
     * @param: nothing
     * @return: void
     */
    public void uniformCostSearch() {
        /* Retrieving the initial node from the class instance and adding it to the stack. */
        Node node = this.node;
        System.out.println("Beginning UCS with initial state =>");
        display(node.getState());
        System.out.println("UCS in in progress");
        /* Initialize an Arraylist to keep track of explored states during the search. */
        ArrayList<Node> explored = new ArrayList<>();
        /* Initializing a PriorityQueue to keep track of nodes as we search through them. */
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        /* add the initial node to PriorityQueue */
        priorityQueue.add(node);
        /* Conditional loop to iterate until the goal state is reached. */
        while (!node.getState().equals(goal)) {
            /* poll the node from PriorityQueue. */
            node = priorityQueue.poll();
            /* add the polled node to explored list */
            explored.add(node);
            assert node != null;
            /* Finding all possible neighbors with blank space movements */
            ArrayList<Node> neighbors = findNeighbors(node);
            /* Iterate through the list of neighbors, check if the node is explored or not, add to Priority if not done.
            Also set the static variables to re-purpose in final result. */
            for (Node adjacent : neighbors) {
                if (!explored.contains(adjacent)) {
                    setVariables(adjacent, node);
                    priorityQueue.add(adjacent);
                    explored.add(adjacent);
                }
            }
            /* once the goal state is met, print the details like node & path. */
            if (node.getState().equals(goal)) {
                Helper.printMetrics(explored, node, this.node);
                return;
            }
        }
    }

    /**
     * This function is trying to help us find all possible nodes from the node argument based on the blank space moved.
     *
     * @param node: current state of the board.
     * @return neighbors: list of all possible boards.
     */
    private static ArrayList<Node> findNeighbors(final Node node) {
        /* Initializing an Arraylist to store all the neighbors(boards) as we find them. */
        ArrayList<Node> neighbors = new ArrayList<>();
        /* getting the state object from node reference.i.e., puzzle */
        String state = node.getState();
        /**
         * conditional check to see if the blank space is in first spot. i.e., row=1; column=1.
         * If yes, add children nodes obtained by moving blank space right & down.
         */
        if (node.getState().indexOf("0") == 0) {
            neighbors.add(new Node(swap(state, 0, 1)));
            neighbors.add(new Node(swap(state, 0, 3)));
            /**
             * conditional check to see if the blank space is in second spot. i.e., row=1; column=2.
             * If yes, add children nodes obtained by moving blank space left, right & down.
             */
        } else if (node.getState().indexOf("0") == 1) {
            neighbors.add(new Node(swap(state, 1, 0)));
            neighbors.add(new Node(swap(state, 1, 2)));
            neighbors.add(new Node(swap(state, 1, 4)));
            /**
             * conditional check to see if the blank space is in third spot. i.e., row=1; column=3.
             * If yes, add children nodes obtained by moving blank space left & down.
             */
        } else if (node.getState().indexOf("0") == 2) {
            neighbors.add(new Node(swap(state, 2, 1)));
            neighbors.add(new Node(swap(state, 2, 5)));
            /**
             * conditional check to see if the blank space is in fourth spot. i.e., row=2; column=1.
             * If yes, add children nodes obtained by moving blank space up, right & down.
             */
        } else if (node.getState().indexOf("0") == 3) {
            neighbors.add(new Node(swap(state, 3, 0)));
            neighbors.add(new Node(swap(state, 3, 4)));
            neighbors.add(new Node(swap(state, 3, 6)));
            /**
             * conditional check to see if the blank space is in fifth spot. i.e., row=2; column=2.
             * If yes, add children nodes obtained by moving blank space left, up, right & down.
             */
        } else if (node.getState().indexOf("0") == 4) {
            neighbors.add(new Node(swap(state, 4, 3)));
            neighbors.add(new Node(swap(state, 4, 1)));
            neighbors.add(new Node(swap(state, 4, 5)));
            neighbors.add(new Node(swap(state, 4, 7)));
            /**
             * conditional check to see if the blank space is in sixth spot. i.e., row=2; column=3.
             * If yes, add children nodes obtained by moving blank space left, up & down.
             */
        } else if (node.getState().indexOf("0") == 5) {
            neighbors.add(new Node(swap(state, 5, 4)));
            neighbors.add(new Node(swap(state, 5, 2)));
            neighbors.add(new Node(swap(state, 5, 8)));
            /**
             * conditional check to see if the blank space is in seventh spot. i.e., row=3; column=1.
             * If yes, add children nodes obtained by moving blank space up & right.
             */
        } else if (node.getState().indexOf("0") == 6) {
            neighbors.add(new Node(swap(state, 6, 3)));
            neighbors.add(new Node(swap(state, 6, 7)));
            /**
             * conditional check to see if the blank space is in eighth spot. i.e., row=3; column=2.
             * If yes, add children nodes obtained by moving blank space left, up & right.
             */
        } else if (node.getState().indexOf("0") == 7) {
            neighbors.add(new Node(swap(state, 7, 6)));
            neighbors.add(new Node(swap(state, 7, 4)));
            neighbors.add(new Node(swap(state, 7, 8)));
            /**
             * conditional check to see if the blank space is in ninth spot. i.e., row=3; column=3.
             * If yes, add children nodes obtained by moving blank space left & up.
             */
        } else if (node.getState().indexOf("0") == 8) {
            neighbors.add(new Node(swap(state, 8, 7)));
            neighbors.add(new Node(swap(state, 8, 5)));
        }
        return neighbors;
    }

    /**
     * A function to swap the blank space position on the board as
     * part of exploring neighbors based on the blank space position.
     *
     * @param state
     * @param i
     * @param j
     * @return
     */
    private static String swap(String state, int i, int j) {
        StringBuilder sb = new StringBuilder(state);
        sb.setCharAt(i, state.charAt(j));
        sb.setCharAt(j, state.charAt(i));
        return sb.toString();
    }

    private void setVariables(Node child, Node parent) {
        child.setParent(parent);
    }
}
