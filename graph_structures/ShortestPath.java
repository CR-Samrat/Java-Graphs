package graph_structures;

import java.util.*;

class ShortestPath extends WeightedGraph{

    public ShortestPath(int vertices) {
        super(vertices);
    }
    
    public void Dijkstra(int source){

        // Creating a distance array and assuming all distances from source are infinity
        double[] distances = new double[this.getVertices()];
        Arrays.fill(distances, Double.MAX_VALUE);

        // Creating a path array and assuming all distances from source are infinity
        int[] paths = new int[this.getVertices()];
        Arrays.fill(paths, source);

        // Distance from source to source is zero
        distances[source] = 0;

        // Creating an array for tracking visited nodes
        Boolean[] visited = new Boolean[this.getVertices()];
        Arrays.fill(visited, false);

        // Creating a priority queue to store the neighbors sorted by weight
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(Node::getWeight));
        pq.add(new Node(source, 0));

        // While all neighbors are not traversed i.e. queue is not empty, run a loop
        while(pq.size() != 0){

            // Pull the node with smallest distance from priority queue
            Node curNode = pq.poll();

            // If the node is already visited then continue otherwise make it visited
            if(visited[curNode.getPoint()]) continue;

            visited[curNode.getPoint()] = true;

            // For the current node, fetch its neighbors and add them in the queue
            this.getAdjList().get(curNode.getPoint()).stream()
                                                    .forEach(neighbor -> {
                                                        int neighborPoint = neighbor.getPoint();

                                                        // Previous distance is distance stored in the distances array
                                                        double prevDistance = distances[neighborPoint];

                                                        // new distance is distance from current node to the neighbor
                                                        double newDistance = distances[curNode.getPoint()] + neighbor.getWeight();

                                                        // if the new distance is shorter then update the distance array and queue
                                                        if(newDistance < prevDistance){
                                                            distances[neighborPoint] = newDistance;
                                                            paths[neighborPoint] = curNode.getPoint();
                                                            pq.add(new Node(neighborPoint, distances[neighborPoint]));
                                                        }
                                                    });
        }

        // Output
        System.out.println("\nShortest path from "+source+" :");
        Arrays.stream(distances).forEach(dist -> System.out.print(dist+"\t"));
        System.out.println();
        Arrays.stream(paths).forEach(path -> System.out.print(path+"\t"));
    }

}

class Sample4{
    public static void main(String[] args) {
        ShortestPath graph = new ShortestPath(6);

        graph.addEdges(0, Arrays.asList(
            new Node(1, 10), new Node(2, 15)
        ));
        graph.addEdges(1, Arrays.asList(
            new Node(3, 12), new Node(5, 15)
        ));
        graph.addEdges(2, Arrays.asList(
            new Node(4, 10)
        ));
        graph.addEdges(3, Arrays.asList(
            new Node(4, 2), new Node(5, 1)
        ));
        graph.addEdges(5, Arrays.asList(
            new Node(4, 5)
        ));

        graph.view();

        graph.Dijkstra(0);
    }
}