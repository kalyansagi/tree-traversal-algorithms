package main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;


@Data
@AllArgsConstructor
public class Node implements Comparable<Node> {

    private String state;
    private Node parent;

    public Node(String state) {
        this.state = state;
    }

    @Override
    public int compareTo(@NotNull Node node) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!Node.class.isAssignableFrom(obj.getClass()))
            return false;

        final Node other = (Node) obj;
        return this.state.equals(other.getState());
    }
}
