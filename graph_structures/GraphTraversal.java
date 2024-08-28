package graph_structures;
import java.util.*;

class GraphTraversal extends Graph{

    Boolean[] visited;

    public GraphTraversal(int n) {
        super(n);
        visited = new Boolean[getVertices()];

        for(int i=0; i<n; i++){
            visited[i] = false;
        }
    }

    public void resetTraversal(){
        for(int i=0; i<getVertices(); i++){
            visited[i] = false;
        }
        System.out.println();
    }
    
    public void DFS(int source){
        if(visited[source]==true){
            return;
        }

        System.out.print(source+" ");
        this.visited[source] = true;
        getAdjList().get(source).forEach(point -> DFS(point));
    }

    public void BFS(int source){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while(queue.size() != 0){
            int cur = queue.poll();
            System.out.print(cur+" ");

            this.getAdjList().get(cur).stream()
                                        .filter(point -> !this.visited[point])
                                        .forEach(point -> {
                                            visited[point] = true;
                                            queue.add(point);
                                        });
        }
    }
}

class Sample1{
    public static void main(String[] args) {
        GraphTraversal graph = new GraphTraversal(5);
        
        graph.addEdges(0,  Arrays.asList(1,2,3,4));
        graph.addEdges(1, Arrays.asList(0,2,3));
        graph.addEdges(2, Arrays.asList(0,1,4));
        graph.addEdges(3, Arrays.asList(0,1,4));
        graph.addEdges(4, Arrays.asList(0,2,3));

        graph.view();

        System.out.println("DFS Traversal : ");
        graph.DFS(4);

        graph.resetTraversal();

        System.out.println("BFS traversal : ");
        
        graph.BFS(4);
    }
}