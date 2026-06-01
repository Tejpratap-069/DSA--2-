import java.util.*;

public class IoT_Gateway_Network_CO3 {

    static class Edge implements Comparable<Edge> {

        char u, v;
        int weight;

        Edge(char u, char v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }

    static Map<Character, Character> parent =
            new HashMap<>();

    static char find(char x) {
        if (parent.get(x) == x)
            return x;

        char root = find(parent.get(x));
        parent.put(x, root);
        return root;
    }

    static boolean union(char a, char b) {

        char ra = find(a);
        char rb = find(b);

        if (ra == rb)
            return false;

        parent.put(ra, rb);
        return true;
    }

    public static void main(String[] args) {

        char[] vertices =
                {'A','B','C','D','E','F','G'};

        for(char v : vertices)
            parent.put(v,v);

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge('A','B',6));
        edges.add(new Edge('A','C',4));
        edges.add(new Edge('A','D',7));
        edges.add(new Edge('B','C',2));
        edges.add(new Edge('B','E',5));
        edges.add(new Edge('C','D',3));
        edges.add(new Edge('C','E',8));
        edges.add(new Edge('C','F',6));
        edges.add(new Edge('D','F',4));
        edges.add(new Edge('E','F',1));
        edges.add(new Edge('E','G',9));
        edges.add(new Edge('F','G',2));

        Collections.sort(edges);

        int cost = 0;

        System.out.println("MST Edges:");

        for(Edge e : edges) {

            if(union(e.u, e.v)) {

                System.out.println(
                        e.u + " - " +
                        e.v + " : " +
                        e.weight);

                cost += e.weight;
            }
        }

        System.out.println("Total MST Cost = "
                + cost);
    }
}