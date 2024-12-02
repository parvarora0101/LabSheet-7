import DisasterSystem.AVLTree;
import DisasterSystem.Graph;
import java.util.List;

public class DisasterResponseUI {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Graph graph = new Graph();

        // Example 1: Insert regions into AVL Tree
        tree.insert("Region1");
        tree.insert("Region2");
        tree.insert("Region3");

        List<String> treeResult = tree.inOrderTraversal();
        System.out.print("AVL Tree In-Order Traversal: ");
        for (String region : treeResult) {
            System.out.print(region + " ");
        }
        System.out.println();

        // Example 2: Graph BFS Traversal
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "D", 1);
        System.out.print("Graph BFS Traversal from A: ");
        graph.BFS("A");

        // Example 3: Graph Dijkstra's Algorithm
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "C", 4);
        List<String> dijkstraResult = graph.dijkstra("A", "C");
        System.out.print("Dijkstra's Shortest Path from A to C: ");
        for (String node : dijkstraResult) {
            System.out.print(node + " ");
        }
        System.out.println();

        // Example 4: Graph Prim's Algorithm
        List<String> mst = graph.primMST();
        System.out.print("Prim's Minimum Spanning Tree: ");
        for (String edge : mst) {
            System.out.print(edge + " ");
        }
        System.out.println();
    }
}
