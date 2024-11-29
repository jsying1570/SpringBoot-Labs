package cn.iocoder.springboot.lab50.al;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salter
 * 带权重的有向图（邻接表表示）
 */
public class WeightedDirectedGraph {
    /**
     * 节点的数量
     */
    private final int numVertices;
    /**
     * 邻接表
     */
    private final List<List<Edge>> adjList;

    /**
     * 边的定义
     */
    public static class Edge {
        private final int dest; // 目标节点
        private final int weight; // 权重

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + dest + ", " + weight + ")";
        }
    }

    public WeightedDirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new ArrayList<List<Edge>>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<Edge>());
        }
    }

    /**
     * 添加一条从src指向dest的有向边，并设置权重
     * @param src 源点
     * @param dest 目标点
     * @param weight 权重
     */
    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
    }

    /**
     * 打印图
     */
    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("顶点 " + i + " 指向: ");
            for (Edge edge : adjList.get(i)) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 创建一个有5个顶点的带权重有向图
        WeightedDirectedGraph graph = new WeightedDirectedGraph(5);

        // 添加一些带权重的有向边
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 4);
        graph.addEdge(1, 4, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 1);

        // 打印图
        graph.printGraph();
    }
}