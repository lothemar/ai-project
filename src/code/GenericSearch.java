package code;
import java.util.*;

public class GenericSearch {
    static int depth;
    public static boolean isGoal(NodeState state){
//        System.out.println("prosperity: " + state.prosperity);
        return state.prosperity >= 100;
    }
    public static void queueingFunction(LinkedList<Node> frontier, Node node){
        System.out.println("Doens't get polymorphed");
    }
    public static void IDqueueingFunction(LinkedList<Node> frontier, Node node){
        NodeState buyFood = node.state.newNode("requestfood");
        Node food = new Node(buyFood, node, node.state.getCost(buyFood, node.cost), node.depth+1, "requestfood");
        NodeState buyMaterials = node.state.newNode("requestmaterials");
        Node materials = new Node(buyMaterials, node, node.state.getCost(buyMaterials, node.cost), node.depth+1, "requestmaterials");
        NodeState buyEnergy = node.state.newNode("requestenergy");
        Node energy = new Node(buyEnergy, node, node.state.getCost(buyEnergy, node.cost), node.depth+1, "requestenergy");
        NodeState build1 = node.state.newNode("build1");
        Node buildNode1 = new Node(build1, node, node.state.getCost(build1, node.cost), node.depth+1, "build1");
        NodeState build2 = node.state.newNode("build2");
        Node buildNode2 = new Node(build2, node, node.state.getCost(build2, node.cost), node.depth+1, "build2");
        NodeState wait = node.state.newNode("wait");
        Node waitNode = new Node(wait, node, node.state.getCost(wait, node.cost), node.depth+1, "wait");
        Node[] possibleNodes = {food, materials, energy, buildNode1, buildNode2, waitNode};
//        System.out.println(build1 != null);
        ArrayList<Node> possibleChoices = new ArrayList<Node>();

        for(Node possibleNode : possibleNodes){
            if(possibleNode.state != null){
                if(possibleNode.depth >= depth){
                    continue;
                }
                possibleChoices.add(possibleNode);
            }
        }
        for(Node possibleNode: possibleChoices){
            frontier.addFirst(possibleNode);
        }

    }
    public static Node depthLimitedSearch(Node initialState, String method){
        depth = 1;
        LinkedList<Node> frontier = new LinkedList<>();
        while(true){
            if (frontier.isEmpty()) {
               frontier.add(initialState);
               depth+=1;
            }

            Node node = frontier.pop();
            if (isGoal(node.state)) {

                return node;
            }
            IDqueueingFunction(frontier, node);
        }

    }
    public static Node prioQueueSearch(Node initialState, String method){
        PriorityQueue<Node> frontier;
        switch(method){
            case "UC":
                frontier = new PriorityQueue<>(5, Comparator.comparingInt(o -> o.cost));
                break;
            case "AS1":
                frontier = new PriorityQueue<>(5, Comparator.comparingInt(o -> (o.cost + o.h1())));
                break;
            case "AS2":
                frontier = new PriorityQueue<>(5, Comparator.comparingInt(o -> (o.cost + o.h2())));
                break;
            case "GR1":
                frontier = new PriorityQueue<>(5, Comparator.comparingInt(Node::h1));
                break;
            default:
                frontier = new PriorityQueue<>(5, Comparator.comparingInt(Node::h2));
                break;

        }
        frontier.add(initialState);
        while(true){
            if (frontier.isEmpty()) {
                return null;
            }
            Node node = frontier.poll();
            if (isGoal(node.state)) {
                LLAPSearch.clearVisited();
                return node;
            }
            LLAPSearch.queueingFunctionPrioQueue(frontier, node);
        }
    }
    public static Node search(Node initialState, String method){
        LinkedList<Node> frontier = new LinkedList<>();
        frontier.add(initialState);
        if(method.equals("ID")){
            // iterative deepening
            return depthLimitedSearch(initialState, method);

        }
        else if (method.equals("BF") || method.equals("DF")) {
            while(true){
                if (frontier.isEmpty()) {
                    return null;
                }
                Node node = frontier.pop();
                if (isGoal(node.state)) {
                    LLAPSearch.clearVisited();
                    return node;
                }
                LLAPSearch.queueingFunction(frontier, node);
            }
        }
        else{
            // priority queue
            return prioQueueSearch(initialState, method);
        }
    }
}
