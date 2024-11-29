package cn.iocoder.springboot.lab50.am;

/**
 * @author salter
 * 带权有向图
 */
public class WeightedDirectedGraph {
    /**
     * 点数
     */
    private final int vertexNum;
    /**
     * 邻接矩阵
     */
    private final int[][] adjMatrix;

    public WeightedDirectedGraph(int v) {
        this.vertexNum = v;
        this.adjMatrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                if (i == j){
                    adjMatrix[i][j] = 0;
                }else {
                    adjMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    /**
     * 添加边
     * @param v 横轴编号
     * @param u 竖轴编号
     * @param weight 权重
     */
    public void addEdge(int v, int u, int weight) {
        if (v >= 0 && v < vertexNum && u >= 0 && u < vertexNum){
            //设置权重
            adjMatrix[v][u] = weight;
        }
    }

    /**
     * 打印矩阵
     */
    public void printGraph() {
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                if (adjMatrix[i][j] == Integer.MAX_VALUE) {
                    System.out.print("∞ ");
                } else {
                    System.out.print(adjMatrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WeightedDirectedGraph graph = new WeightedDirectedGraph(5);
        // 添加带权有向边
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 5);
        // 形成一个环
        graph.addEdge(4, 0, 6);
        // 从1到4的边
        graph.addEdge(1, 4, 7);
        graph.printGraph();
    }
}
