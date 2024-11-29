package cn.iocoder.springboot.lab50.am;

/**
 * @author salter
 * 有向图
 */
public class DirectedGraph {
    /**
     * 点的个数
     */
    private final int vertex;

    private final int[][] directedGraph;

    public DirectedGraph(int v) {
        this.vertex = v;
        this.directedGraph = new int[vertex][vertex];
    }

    /**
     * 添加边
     * @param v 编号
     * @param u 编号
     */
    public void addEdge(int v, int u) {
        if (v >= 0 && v < vertex && u >= 0 && u < vertex){
            directedGraph[v][u] = 1;
        }
    }

    /**
     * 打印图
     */
    public void printGraph() {
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                System.out.print(directedGraph[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1,3);
        graph.addEdge(3,4);
        graph.addEdge(2,4);
        graph.addEdge(2,3);
        graph.printGraph();
    }
}
