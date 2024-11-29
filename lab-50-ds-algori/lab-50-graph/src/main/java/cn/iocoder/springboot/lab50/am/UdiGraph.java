package cn.iocoder.springboot.lab50.am;


/**
 * @author salter
 * 无向图的邻接矩阵存储方式
 */
public class UdiGraph {
    /**
     * 顶点数量
     */
    private final int vertices;
    /**
     * 邻接矩阵
     */
    private final int[][] adjacencyMatrix;

    public UdiGraph(int v) {
        this.vertices = v;
        adjacencyMatrix = new int[vertices][vertices];
    }

    /**
     * 给图添加边
     * @param v 横轴编号
     * @param u 竖轴编号
     */
    public void addEdge(int v, int u) {
        if (v >= 0 && v < vertices && u >= 0 && u < vertices){
            adjacencyMatrix[v][u] = 1;
            //无向图
            adjacencyMatrix[u][v] = 1;
        }
    }

    /**
     * 打印图
     */
    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        UdiGraph graph = new UdiGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1,3);
        graph.addEdge(3,4);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.printGraph();
    }
}
