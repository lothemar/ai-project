package code;

import java.util.LinkedList;
import java.util.List;

public class LLAPSearch extends GenericSearch {
    static String method;
    public static void queueingFunction(LinkedList<Node> frontier, Node node){
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
        LinkedList<Node> possibleChoices = new LinkedList<Node>();
        for(Node possibleNode : possibleNodes){
            if(possibleNode.state != null){
                possibleChoices.add(possibleNode);
            }
        }

        switch(method){
            case "BF":
                frontier.addAll(possibleChoices);
                break;
            case "DFS":
                frontier.addFirst(node);
                break;
            case "UCS":
                frontier.addLast(node);
                break;
            case "GS":
                frontier.addLast(node);
                break;
            case "A*":
                frontier.addLast(node);
                break;
            case "ID":
                frontier.addLast(node);
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
        node = initial;
        // join all the strings in the list using a comma
//        //System.out.println("wrongAnswer: "+wrongAnswer);
//        while(node.parent != null){
//            System.out.println(node.state.action2 + " : " + (node.parent.state.money - node.state.money) );
//            node = node.parent;
//        }
        return String.join(",", answer);
    }
    public static String solve(String initialState, String method2, boolean visualize){
        method = method2;
        Node initialNode = new Node(initialState, null, 0, 0);
        Node answer = search(initialNode);
        if (answer == null){
            return "nosolution";
        }
        System.out.println("Absolute difference");
        System.out.println(initialNode.state.money - answer.state.money);
        System.out.println(getAnswer(answer));
        return getAnswer(answer) + ";" + answer.cost + ";" +answer.depth;
    }
    public static void main(String[] args){
        solve("", "BF", true);
    }
}
