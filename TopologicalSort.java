/*
トポロジカルソート
http://abc041.contest.atcoder.jp/tasks/abc041_d
*/





import java.io.*;
import java.util.*;

class Graph {
  private int count;
  private int[][] adj;

  Graph(int count) {
    this.count = count;
    this.adj = new int[count][count];
    for (int i = 0; i < count; i++) {
      for (int j = 0; j < count; j++) {
        adj[i][j] = 0;
      }
    }
  }

  public void addEdge(int fromIndex, int toIndex) {
    adj[fromIndex - 1][toIndex - 1] = 1;
  }

  public void removeEdge(int fromIndex, int toIndex) {
    adj[fromIndex][toIndex] = 0;
  }

  //出力辺先のノード達を取得
  public LinkedList getAdjNodes(int index) {
    LinkedList list = new LinkedList();
    for (int i = 0; i < count; i++) {
      if (this.adj[index][i] == 1) {
        list.addNode(i);
      }
    }
    return list;
  }

  //入力辺の持たないノード達を取得
  public LinkedList getNoInputNodes() {
    LinkedList list = new LinkedList();
    for (int j = 0; j < count; j++) {
      boolean isNoInput = true;
      for (int i = 0; i < count; i++) {
        if (this.adj[i][j] == 1) {
           isNoInput = false;
        }
      }
      if (isNoInput) {
        list.addNode(j);
      }
    }
    return list;
  }

  public boolean isHaveInputEdge(int index) {
    for (int i = 0; i < count; i++) {
      if (adj[i][index] == 1) {
        return true;
      }
    }
    return false;
  }

  public LinkedList topologicalSort() {
    //トポロジカルソートした結果を蓄積する空リスト
    LinkedList list = new LinkedList();

    //入力辺を持たない全てのノードの集合
    LinkedList que = getNoInputNodes();

    while (!que.isEmpty()) {
      ListNode node = que.removeNodeAtFirst();
      list.addNode(node.data);

      LinkedList l = getAdjNodes(node.data);
      ListNode n = l.head.next;
      while (n != null) {
        System.out.println(n.data);
        System.out.println(node.data);
        removeEdge(node.data, n.data);
        
        if (!isHaveInputEdge(n.data)) {
          que.addNode(n.data);
        }
        n = n.next;
      }
    }

    return list;
  }

  public void printAdj() {
    for (int i = 0; i < count; i++) {
      for (int j = 0; j < count; j++) {
        System.out.print(adj[i][j] + " ");
      }
      System.out.println();
    }
  }

}

class TreeNode {
  private int data;

  public TreeNode(int data) {
    this.data = data;
  }
}

class LinkedList {
  ListNode head;

  public LinkedList() {
    this.head = new ListNode();
  }

  //末尾に追加
  public void addNode(int data) {
    ListNode addNode = new ListNode(data);
    ListNode currentNode = this.head;
    while (currentNode.next != null) {
      currentNode = currentNode.next;
    }
    currentNode.next = addNode;
  }


  public ListNode removeNodeAtFirst() {
    ListNode node = head.next;
    head.next = head.next.next;
    return node;
  }

  public boolean isEmpty() {
    if (head.next == null) {
      return true;
    } else {
      return false;
    }
  }

  public void printList() {
    ListNode node = head.next;
    while (node != null) {
      System.out.print(node.data + 1 + " ");
      node = node.next;
    }
    System.out.println();
  }

}

class ListNode {
  ListNode next;
  int data;

  public ListNode() {

  }
  public ListNode(int data) {
    this.data = data;
  }
}


class TopologicalSort {
  public static void main(String[] args) {
    Graph g = new Graph(3);
    g.addEdge(1, 2);
    g.addEdge(3, 2);
    g.printAdj();
    g.topologicalSort().printList();
  }
}
