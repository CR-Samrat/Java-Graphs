package graph_structures;
import java.util.*;

class Graph{
    private int vertices;
    private List<List<Integer>> adjList;

    public Graph(int n){
        this.vertices = n;
        this.adjList = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdges(int source, List<Integer> dest){

        adjList.get(source).addAll(dest);
    }

    public void view(){
        for(int i=0 ; i<adjList.size(); i++){
            System.out.print(i+" : [ ");
            adjList.get(i).forEach(point -> System.out.print(point+" "));
            System.out.println("]");
        }
    }

    public List<Integer> getConnectedVertices(int source){
        return this.adjList.get(source);
    }

    public int getVertices() {
        return vertices;
    }

    public List<List<Integer>> getAdjList() {
        return adjList;
    }
}


class Sample2{
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        
        graph.addEdges(0,  Arrays.asList(1,2,3,4));
        graph.addEdges(1, Arrays.asList(0,2,3));
        graph.addEdges(2, Arrays.asList(0,1,4));
        graph.addEdges(3, Arrays.asList(0,1,4));
        graph.addEdges(4, Arrays.asList(0,2,3));

        graph.view();
    }
}