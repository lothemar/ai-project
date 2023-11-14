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
    public int h1(){
        return state.h1Value();
    }
    public int h2(){
        return state.h2Value();
    }
    public String getHash(String method){
        String prevAction;
        if (this.parent == null) prevAction = "";
        else prevAction = this.parent.action;
        return this.state.getHash(prevAction, method);
    }
    public Node(NodeState state, Node parent, int cost, int depth, String action){
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.depth = depth;
        this.action = action;
    }
}