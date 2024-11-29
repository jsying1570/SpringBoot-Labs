package cn.iocoder.springboot.lab50.al;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salter
 * 无向图（邻接表表示）
 */
public class UdiGraph {
    private final int vertexNum;
    private final List<List<Integer>> adj;

    public UdiGraph(int v) {
        this.vertexNum = v;
        //构建基础结构
        adj = new ArrayList<List<Integer>>(v);
        for (int i = 0; i < vertexNum; i++) {
            adj.add(new ArrayList<Integer>());
        }
    }

    public void addEdge(int src, int dest) {
        if (src >= 0 && src < vertexNum && dest >= 0 && dest < vertexNum){
            //由于是无向图，需要加两条边
            adj.get(src).add(dest);
            adj.get(dest).add(src);
        }
    }

    public void printGraph() {
        for (int i = 0; i < vertexNum; i++) {
            System.out.print("点 " + i + " 的邻居: ");
            for (int j = 0; j < adj.get(i).size();j++){
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //创建一个有5个顶点的图
        UdiGraph graph = new UdiGraph(5);

        // 添加一些边
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
