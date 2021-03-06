package com.bamzhy.My_LeetCode.Pojo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            E = scanner.nextInt();

            if (V < 0) throw new IllegalArgumentException("V must be non-negative");
            if (E < 0) throw new IllegalArgumentException("E must be non-negative");

            adj = new int[V][V];
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();

                validateVertex(a);
                validateVertex(b);
                if (a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if (adj[a][b] == 1) throw new IllegalArgumentException("Parallel Edges are Detected");

                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    public int degree(int v) {
        return adj(v).size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V = ").append(V).append("  E = ").append(E).append("\n");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(adj[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix.toString());
        System.out.println(adjMatrix.adj(2));
        System.out.println(adjMatrix.degree(2));
        System.out.println(adjMatrix.hasEdge(2, 3));
        System.out.println(adjMatrix.hasEdge(2, 6));
    }
}
