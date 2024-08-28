package graph_structures;

import java.util.*;

class Edge{
    private int source;
    private int destination;
    private double weight;

    public Edge(int src, int dest, double weight){
        this.source = src;
        this.destination = dest;
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    public int getSource() {
        return source;
    }

    public double getWeight() {
        return weight;
    }
}

class SpanningTree extends WeightedGraph{

    private List<List<Node>> spanningTreeAdjLIst;

    public SpanningTree(int vertices) {
        super(vertices);
        this.spanningTreeAdjLIst = new ArrayList<>(vertices);
        for(int i=0; i<vertices; i++){
            spanningTreeAdjLIst.add(new ArrayList<>());
        }
    }
    
    public void MinimumWeightSpanningTree(){

        // First create a visited set to track the nodes that are added
        HashSet<Integer> visited = new HashSet<>();

        // Add the initial node i.e. node 0
        visited.add(0);
        
        // Run a loop until all nodes are added
        while(visited.size() < this.getVertices()){

            // To track nearest edge
            Edge curEdge = null;

            // Create a priority queue to add the neighbor vertices sorted by weight
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(Edge::getWeight));

            // Add the neighbor edges of the visited nodes
            visited.forEach(point -> this.getAdjList().get(point)
                                                    .forEach(
                                                        node -> pq.add(new Edge(point, node.getPoint(), node.getWeight()))
                                                    ));

            
            // Run a loop to take the vertex with smallest distance
            // If current neighbor is already visited then take another one
            while(pq.size() != 0){
                curEdge = pq.poll();

                if(!visited.contains(curEdge.getDestination())) break;
            }

            // Add the node into the spanningTreeAdjLIst
            this.spanningTreeAdjLIst.get(curEdge.getSource()).add(new Node(curEdge.getDestination(), curEdge.getWeight()));

            // Update the set
            visited.add(curEdge.getDestination());
        }

        viewSpanningTree();
    }

    public void viewSpanningTree(){
        System.out.println("\nMinimum weight Spanning tree :");
        for(int i=0; i<this.getVertices(); i++){
            System.out.print(i+" :");
            
            spanningTreeAdjLIst.get(i).forEach(node -> System.out.print(" [ "+node.getPoint()+" , "+node.getWeight()+" ] "));

            System.out.println();
        }
    }
}

class Sample5{
    public static void main(String[] args) {
        SpanningTree tree = new SpanningTree(5);

        tree.addEdges(0, Arrays.asList(
            new Node(1, 10), new Node(2, 10), new Node(3, 30),new Node(4, 30)
        ));
        tree.addEdges(1, Arrays.asList(
            new Node(0, 10), new Node(2, 20), new Node(3, 15)
        ));
        tree.addEdges(2, Arrays.asList(
            new Node(0, 10), new Node(1, 20), new Node(4, 15)
        ));
        tree.addEdges(3, Arrays.asList(
            new Node(0, 30), new Node(1, 15), new Node(4, 10)
        ));
        tree.addEdges(4, Arrays.asList(
            new Node(0, 30), new Node(2, 15), new Node(3, 10)
        ));

        tree.view();

        tree.MinimumWeightSpanningTree();
    }
}