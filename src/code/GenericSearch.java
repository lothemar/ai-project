package code;

import java.util.LinkedList;
import java.util.Queue;

public class GenericSearch {
    public static boolean isGoal(NodeState state){
//        System.out.println("prosperity: " + state.prosperity);
        return state.prosperity >= 100;
    }
    public static void queueingFunction(LinkedList<Node> frontier, Node node){
        System.out.println("Doens't get polymorphed");
    }
    public static Node search(Node initialState){
        LinkedList<Node> frontier = new LinkedList<>();
        frontier.add(initialState);
        while(true){
            if (frontier.isEmpty()) {
                return null;
            }
            Node node = frontier.pop();
            if (isGoal(node.state)) {
                return node;
            }
            LLAPSearch.queueingFunction(frontier, node);
        }
    }
}
