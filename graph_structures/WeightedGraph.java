package graph_structures;

import java.util.*;

class Node{
    // Each node will represent point as well as its distance from source
    private int point;
    private double weight;

    public Node(int point, double weight){
        this.point = point;
        this.weight = weight;
    }

    public int getPoint() {
        return point;
    }

    public double getWeight() {
        return weight;
    }
}

class WeightedGraph {
    
    private int vertices;
    private List<List<Node>> adjList;

    public WeightedGraph(int vertices){
        this.vertices = vertices;

        adjList = new ArrayList<>(vertices);
        for(int i=0; i<vertices; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int target, double weight){
        adjList.get(source).add(new Node(target, weight));
    }

    public void addEdges(int source, List<Node> dest){
        adjList.get(source).addAll(dest);
    }

    public void view(){
        for(int i=0 ; i<adjList.size(); i++){
            System.out.print(i+" : [ ");
            adjList.get(i).forEach(node -> System.out.print("["+node.getPoint()+" , "+node.getWeight()+"]"));
            System.out.println("]");
        }
    }

    public int getVertices() {
        return vertices;
    }

    public List<List<Node>> getAdjList() {
        return adjList;
    }
}


class Sample3{
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);

        graph.addEdges(0, Arrays.asList(
            new Node(1, 10), new Node(2, 10), new Node(3, 30),new Node(4, 30)
        ));
        graph.addEdges(1, Arrays.asList(
            new Node(0, 10), new Node(2, 20), new Node(3, 15)
        ));
        graph.addEdges(2, Arrays.asList(
            new Node(0, 10), new Node(1, 20), new Node(4, 15)
        ));
        graph.addEdges(3, Arrays.asList(
            new Node(0, 30), new Node(1, 15), new Node(4, 10)
        ));
        graph.addEdges(4, Arrays.asList(
            new Node(0, 30), new Node(2, 15), new Node(3, 10)
        ));

        graph.view();
    }
}