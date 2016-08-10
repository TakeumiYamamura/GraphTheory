import java.util.ArrayList;

class Graph {
  //fields
  int DEFAULT_CAPACITY = 10;
  int numNodes;
  int[][] adjMatrix;
  Node[] datas;

  static class Node {
    String data;

    public Node(String data) {
      this.data = data;
    }
  }

  public Graph(String data) {
    numNodes = 0;
    this.adjMatrix = new int[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
    this.datas = new Node[DEFAULT_CAPACITY];
    datas[numNodes] = new Node(data);
    numNodes++;
  }

  //ノードを追加
  public void addNode() {
    if (numNodes == datas.length) 
      System.out.println("capa orver");
    datas[numNodes] = null;
    for (int i = 0; i < numNodes; i++) {
      adjMatrix[i][numNodes] = 0;
      adjMatrix[numNodes][i] = 0;
    }
    numNodes++;
  }

  public void addNode(String data) {
    if (numNodes == datas.length) 
      System.out.println("capa orver");
    datas[numNodes] = new Node(data);
    for (int i = 0; i < numNodes; i++) {
      adjMatrix[i][numNodes] = 0;
      adjMatrix[numNodes][i] = 0;
    }
    numNodes++;
  }

  //２つのノードを繋げる
  public void addEdge(int index1, int index2) {
    if (indexIsValid(index1) && indexIsValid(index2)) {
      adjMatrix[index1][index2] = 1;
      adjMatrix[index2][index1] = 1;
    }
  }

  public void addEdge(String data1, String data2) {
    addEdge(getIndex(data1), getIndex(data2));
  }

  public void removeEdge(int index1, int index2) {
    if (indexIsValid(index1) && indexIsValid(index2)) {
      adjMatrix[index1][index2] = 0;
      adjMatrix[index2][index1] = 0;
    }
  }

  public void removeEdge(String data1, String data2) {
    removeEdge(getIndex(data1), getIndex(data2));
  }

  public Node[] getAllNodes() {
    return this.datas;
  }

  public ArrayList<Node> getNodes(String data) {
    System.out.println();
    System.out.print(data + "の友達: ");
    ArrayList<Node> nodes = new ArrayList<Node>();
    int index = getIndex(data);
    for (int i = 0; i < numNodes; i++) {
      if (adjMatrix[index][i] == 1) {
        nodes.add(datas[i]);
        System.out.print(datas[i].data + ", ");
      }
    }
    System.out.println();
    return nodes;
  }

  public void printAdjacencyMatrix() {
    System.out.print("   ");
    for (int k = 0; k < numNodes; k++) {
      System.out.print(k + " ");
    }
    System.out.println();
    System.out.print("   ");
    for (int k = 0; k < numNodes; k++) {
      System.out.print("-" + " ");
    }
    System.out.println();
    for (int i = 0; i < numNodes; i++) {
      System.out.print(i + "| ");
      for (int j = 0; j < numNodes; j++) {
        System.out.print(adjMatrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public void printDatas() {
    System.out.println();
    for (int i = 0; i < numNodes; i++) {
      if (datas[i] == null) {
        System.out.print(null + " ");
        continue;
      }
      System.out.print(datas[i].data + " ");
    }
    System.out.println();
  }

  private boolean indexIsValid(int index) {
    return ((index < numNodes) && (index >= 0));
  }

  private int getIndex(String data) {
    for (int i = 0; i < numNodes; i++) {
      if (datas[i] == null)
        continue;
      if (datas[i].data == data)
        return i;
    }
    return -1;
  }
}

class GraphSample {
  public static void main(String[] args) {
    Graph graph = new Graph("たつき");
    graph.addNode("けいた");
    graph.addNode();
    graph.addNode("むうむう");
    graph.addEdge(0, 1);
    graph.addEdge("たつき", "むうむう");
    graph.printAdjacencyMatrix();
    graph.printDatas();
    graph.getNodes("むうむう");

  }
}
