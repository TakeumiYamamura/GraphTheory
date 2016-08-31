import java.util.Scanner;
import java.util.ArrayList;

class TopologicalSort {
  public TopologicalSort(Graph graph) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    topologicalSort(graph, list);
  }

  public void topologicalSort(Graph graph, ArrayList<Integer> list) {
    if (graph.isEmpty()) {
      printList(list);
      return;
    }

    for (Node node: graph.nodesThaHaveNoFromNods()) {
      ArrayList<Integer> copyList = cloneList(list);
      Graph copyGraph = graph.clone();

      copyList.add(node.id);
      copyGraph.removeNodeAndItsDirection(node.id);
      topologicalSort(copyGraph, copyList);
    }
  }

  private void printList(ArrayList<Integer> list) {
    for (int num: list) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  private ArrayList<Integer> cloneList(ArrayList<Integer> list) {
    ArrayList<Integer> cloneList = new ArrayList<Integer>(list);
    return cloneList;
  }
}

class Graph {
  ArrayList<Node> nodes;

  public Graph(ArrayList<Node> nodes) {
    this.nodes = nodes;
  }

  public Graph clone() {
    ArrayList<Node> cloneNodes = new ArrayList<Node>();

    for (int i = 0; i < nodes.size(); i++) {
      cloneNodes.add(new Node(nodes.get(i).id));
    }

    for (int i = 0; i < nodes.size(); i++) {
      cloneNodes.get(i).fromNodeIds = new ArrayList<Integer>(this.nodes.get(i).fromNodeIds);
      cloneNodes.get(i).toNodeIds = new ArrayList<Integer>(this.nodes.get(i).toNodeIds);
    }

    Graph cloneGraph = new Graph(cloneNodes);
    return cloneGraph;
  }

  public Node find(int nodeId) {
    Node node = new Node();
    for (Node n : nodes) {
      if (n.id == nodeId) {
        node = n;
      }
    }
    return node;
  }

  public boolean isEmpty() {
    return this.nodes.isEmpty();
  }

  public ArrayList<Node> nodesThaHaveNoFromNods() {
    ArrayList<Node> noFromNodes = new ArrayList<Node>();
    for (Node node: this.nodes) {
      if (node.fromNodeIds.isEmpty()) {
        noFromNodes.add(node);
      }
    }
    return noFromNodes;
  }

  public void removeNodeAndItsDirection(int nodeId) {
    Node node = find(nodeId);
    for (int toNodeId: node.toNodeIds) {
      Node targetNode = find(toNodeId);
      deleteFromId(targetNode.fromNodeIds, node.id);
    }
    deleteNode(node.id);
  }

  private void deleteFromId(ArrayList<Integer> fromNodeIds, int deleteId) {
    fromNodeIds.remove(fromNodeIds.indexOf(deleteId));
  }

  private void deleteNode(int nodeId) {
    for (int i = 0; i < this.nodes.size(); i++) {
      if (this.nodes.get(i).id == nodeId) {
        this.nodes.remove(i);
        return;
      }
    }
  }

  private void printNodes() {
    for (Node node: this.nodes) {
      System.out.print(node.id + " ");
    }
    System.out.println();
  }

  private void printList(ArrayList<Integer> list) {
    System.out.println(list);
  }
}

class Node {
  int id;
  ArrayList<Integer> fromNodeIds;
  ArrayList<Integer> toNodeIds;

  public Node(int id, ArrayList<Integer> fromNodeIds, ArrayList<Integer> toNodeIds) {
    this.id = id;
    this.fromNodeIds = fromNodeIds;
    this.toNodeIds = toNodeIds;
  }

  public Node(int id) {
    this.id = id;
    this.fromNodeIds = new ArrayList<Integer>();
    this.toNodeIds = new ArrayList<Integer>();
  }

  public Node() { }
}

class GraphCreator {

  String[] inputs;
  int nodesCount;
  int directionCount;
  Graph graph;

  public GraphCreator(String[] inputText) {
    this.inputs = inputText;
    this.nodesCount = Integer.parseInt(inputText[0].split(" ")[0]);
    this.directionCount = Integer.parseInt(inputText[0].split(" ")[1]);

    ArrayList<Node> nodes = nodesWithCount(nodesCount);    
    this.graph = new Graph(nodes);

    String[] directionInputs = getDirectionInput(inputText);
    addDirectionInformationToNodes(directionInputs);
  }

  public Graph getGraph() {
    return this.graph;
  }

  private ArrayList<Node> nodesWithCount(int nodesCount) {
    ArrayList<Node> nodes = new ArrayList<Node>();
    for (int i = 0; i < nodesCount; i++) {
      nodes.add(new Node(i + 1));
    }
    return nodes;
  }

  private String[] getDirectionInput(String[] inputs) {
    String[] directionInputs = new String[inputs.length - 1];
    for (int i = 0; i < inputs.length - 1; i++) {
      directionInputs[i] = inputs[i + 1];
    }
    return directionInputs;
  }

  private void addDirectionInformationToNodes(String[] directionInputs) {
    for (String input: directionInputs) {
      int fromNodeId = Integer.parseInt(input.split(" ")[1]);
      int toNodeId = Integer.parseInt(input.split(" ")[0]);

      Node fromNode = this.graph.find(fromNodeId);
      Node toNode = this.graph.find(toNodeId);

      fromNode.toNodeIds.add(toNodeId);
      toNode.fromNodeIds.add(fromNodeId);
    }
  }

}

class TsugitaTopo {
  public static void main(String[] args) {
    GraphCreator graphCreator = new GraphCreator(input());
    Graph graph = graphCreator.getGraph();
    new TopologicalSort(graph);
  }

  public static String[] input() {
    Scanner in = new Scanner(System.in);
    String str = new String();
    while(in.hasNext()) {
      String buf = in.nextLine();
      if (buf.matches("EOS")) {
        break;
      }
      str = str + buf + ",";
    }
    String[] inputText = str.split(",");
    return inputText;
  }
}
