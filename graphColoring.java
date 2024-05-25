import java.util.*;
/*
 The code starts by importing the ArrayList class from the java.util package. It defines a class named graphColoring.
 */
class graphColoring {

    /*
     This method graphColoring is the entry point for graph coloring. It takes parameters:
        G: an array of lists representing the graph (adjacency list),
        color: an array to store the color of each node,
        i: the current node being processed,
        C: the total number of colors available.
        It returns true if graph coloring is possible, and false otherwise. It initializes n to the length of the graph (G.length) and calls the solve method to perform the graph coloring.
     */
    public static boolean graphColoring(List < Integer > [] G, int[] color, int i, int C) {
        // Your code here
        int n = G.length;
        if (solve(i, G, color, n, C) == true) return true;
        return false;
    }
    /*
     The isSafe method checks whether it's safe to color a node with a given color. It iterates through the neighbors of the node and checks if any neighbor has the same color (col). If so, it returns false, indicating that the color is not safe for the current node.
     */
    private static boolean isSafe(int node, List < Integer > [] G, int[] color, int n, int col) {
        for (int it: G[node]) {
            if (color[it] == col) return false;
        }
        return true;
    }

    /*
     The solve method is a recursive backtracking algorithm to find a valid coloring for the graph. It iterates through all possible colors for the current node, checks if the color is safe, and then recursively tries to color the next node. If a valid coloring is found, it returns true. If not, it backtracks by resetting the color of the current node to 0.
     */
    private static boolean solve(int node, List < Integer > [] G, int[] color, int n, int m) {
        if (node == n) return true;

        for (int i = 1; i <= m; i++) {
            if (isSafe(node, G, color, n, i)) {
                color[node] = i;
                if (solve(node + 1, G, color, n, m) == true) return true;
                color[node] = 0;
            }
        }
        return false;
    }
    /*
     The main method initializes the graph G with 7 nodes and allocates space for the adjacency lists. The graph is then populated with edges.
     */
    public static void main(String[] args) {
        int N = 7, M =3;
        List < Integer > [] G = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            G[i] = new ArrayList < > ();
        }
        G[0].add(1);
        G[0].add(2);
        G[1].add(2);
        G[1].add(3);
        G[1].add(0);
        G[2].add(1);
        G[2].add(0);
        G[2].add(3);
        G[2].add(4);
        G[2].add(5);
        G[3].add(4);
        G[3].add(4);
        G[3].add(2);
        G[3].add(1);
        G[4].add(5);
        G[4].add(3);
        G[4].add(2);
        G[5].add(2);
        G[5].add(4);
        G[5].add(6);
        G[6].add(5);
        int[] color = new int[N];
        boolean ans = graphColoring(G, color, 0, M);
        if (ans) {
            System.out.println("Graph coloring is possible");
            System.out.println("Node colors:");
            for (int i = 0; i < N; i++) {
                System.out.println("Node " + i + " is colored with " + color[i]);
            }
        } else {
            System.out.println("Graph coloring is not possible with " + M + " colors.");
        }

        if (ans == true)
            System.out.println("1");
        else
            System.out.println("0");
    }
}