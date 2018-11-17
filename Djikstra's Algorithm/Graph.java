
import java.util.ArrayList;
import java.util.*;

public class Graph {
	private ArrayList<Node> vertices; //this is a list of all vertices, populated by Driver class.
	private Heap minHeap; 	//this is the heap to use for Dijkstra
	public Graph(int numVertices) {
		minHeap = new Heap();
		vertices = new ArrayList<Node>();
    // feel free to add anything else you may want	
	}



  // findShortestPathLength
  //
  // Returns the distance of the shortest path from root to x
  public int findShortestPathLength(Node root, Node x) {
    ArrayList<Node> list = findAShortestPath(root,x);
    if(list == null){
      return -1;
    } else {
      return list.size();
    }
  }

  // findAShortestPath
  //
  // Returns a list of nodes represent one of the shortest paths
  // from root to x
  public ArrayList<Node> findAShortestPath(Node root, Node x){
	  ArrayList<Node> path = new ArrayList<Node>();
    Node newItem = x;
    while(!newItem.equals(root)){
      path.add(0,newItem);
      newItem = newItem.parent;
    }

	  if(path.size()>0){
      return path;
    } else {
      return null;  
    }
	  
  }

  // eachShortestPathLength
  //
  // Returns an ArrayList of Nodes, where minDistance of each node is the
  // length of the shortest path from it to the root. This ArrayList
  // should contain every Node in the graph. Note that 
  // root.getMinDistance() = 0
  public ArrayList<Node> findEveryShortestPathLength(Node root) {
    minHeap.buildHeap(vertices);
    ArrayList<Node> queue = new ArrayList<Node>();
    while(minHeap.size()!=0){
        Node v = minHeap.extractMin();
        queue.add(v);
        for(Node u:v.getNeighbors()){
          int i = v.getNeighbors().indexOf(u);
          if(u.minDistance>v.minDistance+u.getWeights().get(i)){
            u.minDistance = v.minDistance+u.getWeights().get(i);
            u.parent = v;
          }
        }
    }
    if(queue.size()>0){
      return queue;  
    } else {
      return null;
    }
	  
  }
  
  //returns edges and weights in a string.
  public String toString() {
    String o = "";
    for(Node v: vertices) {
      boolean first = true;
      o += "Node ";
      o += v.getNodeName();
      o += " has neighbors: ";
      ArrayList<Node> ngbr = v.getNeighbors();
      for(Node n : ngbr) {
        o += first ? n.getNodeName() : ", " + n.getNodeName();
        first = false;
      }
      first = true;
      o += " with weights ";
      ArrayList<Integer> wght= v.getWeights();
      for (Integer i : wght) {
        o += first ? i : ", " + i;
        first = false;
      }		
      o += System.getProperty("line.separator");
    
    }

    return o;
  }
  
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

  public Heap getHeap() {
    return minHeap;
  }
    
  public ArrayList<Node> getAllNodes(){
    return vertices;
  }
     
	//used by Driver class to populate each Node with correct neighbors and corresponding weights
	public void setEdge(Node curr, Node neighbor, Integer weight) {
		curr.setNeighborAndWeight(neighbor, weight);
	}
    //This is used by Driver.java and sets vertices to reference an ArrayList of all nodes.
  public void setAllNodesArray(ArrayList<Node> x) {
    vertices = x;	
  }    
}
