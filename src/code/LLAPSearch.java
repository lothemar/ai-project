package code;

import tests.LLAPPlanChecker;

import java.util.*;

public class LLAPSearch extends GenericSearch {
    static String method;
    static HashSet<String> visited = new HashSet<String>();
    public static void clearVisited(){
        visited = new HashSet<String>();
    }
    public static Node[] getNodes(Node node){
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
        return possibleNodes;
    }
    public static void queueingFunctionPrioQueue(PriorityQueue<Node> frontier, Node node){
        Node[] possibleNodes = getNodes(node);
        ArrayList<Node> possibleChoices = new ArrayList<Node>();

        for(Node possibleNode : possibleNodes){
            if(possibleNode.state != null && !visited.contains(possibleNode.getHash(method))){
                possibleChoices.add(possibleNode);
                visited.add(possibleNode.getHash(method));
            }
        }
        frontier.addAll(possibleChoices);
    }
    public static void queueingFunction(LinkedList<Node> frontier, Node node){
        Node[] possibleNodes = getNodes(node);
        ArrayList<Node> possibleChoices = new ArrayList<Node>();

        for(Node possibleNode : possibleNodes){
            if(possibleNode.state != null && !visited.contains(possibleNode.getHash(method))){
                possibleChoices.add(possibleNode);
                visited.add(possibleNode.getHash(method));
            }
        }

        switch(method){
            case "BF":
                frontier.addAll(possibleChoices);
                break;
            case "DF":
                for(Node possibleNode: possibleChoices){
                    frontier.addFirst(possibleNode);
                }
                break;

        }
    }
    public static String getAnswer(Node node){
        Node initial = node;
        LinkedList<String> answer = new LinkedList<String>();
        LinkedList<Node> wrongAnswer = new LinkedList();
        while(node.parent != null){
            answer.addFirst(node.action);
            wrongAnswer.addFirst(node);
            node = node.parent;
        }
        return String.join(",", answer);
    }
    public static String solve(String initialState, String method2, boolean visualize){
        method = method2;
        Node initialNode = new Node(initialState, null, 0, 0);
        Node answer = search(initialNode, method2);
        if (answer == null){
            return "nosolution";
        }
        return getAnswer(answer) + ";" + answer.cost + ";" +answer.depth;
    }
    public static void genSol(String[] steps, String initialState){
        Node n = new Node(initialState, null, 0, 0);
        for(String step: steps){
            NodeState stepNode = n.state.newNode(step);
            Node m = new Node(stepNode, n, n.state.getCost(stepNode, n.cost), n.depth+1, step);
            n = m;
            System.out.println(n.state);
        }
    }
    public static void main(String[] args){
        String steps = "requestfood,requestfood,requestfood,requestfood,requestfood,requestmaterials,wait,requestmaterials,wait,requestenergy,requestmaterials,wait,requestenergy,requestenergy,requestmaterials,build2,requestenergy,requestenergy,build2";

        String initialState= "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";
        genSol(steps.split(","), initialState);
        System.out.println("separtor");
        LLAPPlanChecker s = new LLAPPlanChecker(initialState);
        String sol = steps + ";18600;18";

        s.applyPlan(initialState, sol);
    }
}
