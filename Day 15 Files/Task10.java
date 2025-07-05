class Graph {

    class Edge {
        int src;
        int dest;

        Edge(int s, int d) {
            src = s;
            dest = d;
        }
    }

    int vertex;
    int edge;
    Edge[] edges;


    Graph(int v, int e) {
        vertex = v;
        edge = e;
        edges = new Edge[e]; 
    }


    void displayEdges() {
        for (int i = 0; i < edge; i++) {
            System.out.println(edges[i].src + " - " + edges[i].dest);
        }
    }
}
public class Task10{

    public static void main(String[] args) {
        int v = 5;
        int e = 8;

        Graph g = new Graph(v, e);


        g.edges[0] = g.new Edge(1, 2);
        g.edges[1] = g.new Edge(1, 3);
        g.edges[2] = g.new Edge(1, 4);
        g.edges[3] = g.new Edge(2, 4);
        g.edges[4] = g.new Edge(2, 5);
        g.edges[5] = g.new Edge(3, 4);
        g.edges[6] = g.new Edge(3, 5);
        g.edges[7] = g.new Edge(4, 5);

        g.displayEdges();
    }
}
