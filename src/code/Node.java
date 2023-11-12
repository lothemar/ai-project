package code;
public class Node {

    public NodeState state;
    public Node parent;
    public String action;
    public int cost;
    public int depth;
    public String toString(){
        return "Node: "+ action + " cost: " + cost + " depth: " + depth + " state: " + state;
    }
    public Node(String state, Node parent, int cost, int depth){
        this.state = new NodeState(state);
        this.parent = parent;
        this.cost = cost;
        this.depth = depth;
    }
    public Node(NodeState state, Node parent, int cost, int depth, String action){
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.depth = depth;
        this.action = action;
    }
}