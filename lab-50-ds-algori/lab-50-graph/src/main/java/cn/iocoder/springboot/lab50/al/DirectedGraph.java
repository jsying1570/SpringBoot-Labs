package cn.iocoder.springboot.lab50.al;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salter
 * 未带权重的有向图（邻接表表示）
 */
public class DirectedGraph {
    /**
     * 节点数量
     */
    private final int numVertices;
    /**
     * 邻接表
     */
    private final List<List<Integer>> adjList;

    public DirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        this.adjList = new ArrayList<List<Integer>>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<Integer>());
        }
    }

    /**
     * 添加一条从src指向dest的有向边
     * @param src 源点
     * @param dest 目标点
     */
    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
    }

    // 打印图
    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("顶点 " + i + " 指向: ");
            for (int j : adjList.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 创建一个有5个顶点的有向图
        DirectedGraph graph = new DirectedGraph(5);

        // 添加一些有向边
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // 打印图
        graph.printGraph();
    }
}