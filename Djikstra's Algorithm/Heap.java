
import java.util.ArrayList;


public class Heap {

	private ArrayList<Node> minHeap; // do not remove

	public Heap() {
		minHeap = new ArrayList<Node>(); // do not remove
	}
	
	// buildHeap
  //
  // Given an ArrayList of Nodes, build a minimum heap keyed on each
  // Node's minDistance
  //
  // Time Complexity Requirement: theta(n)
	public void buildHeap(ArrayList<Node> nodes) {
		for(int i=nodes.size()/2; i>=0; i--){
			heapify(i);
		}

  	}

  	public void heapify(int index){
  		int left = index*2+1;
  		int right = index*2+2;
  		int newIndex;
  		if(left<minHeap.size()-1 && minHeap.get(left).minDistance < minHeap.get(index).minDistance){
  			newIndex = left;
  		} else {
  			newIndex = right;
  		}

  		if(right<minHeap.size()-1 && minHeap.get(right).minDistance < minHeap.get(newIndex).minDistance){
  			newIndex = right;
  		} 

  		if(newIndex != index){
  			int temp = minHeap.get(newIndex).minDistance;
			minHeap.get(newIndex).minDistance = minHeap.get(index).minDistance;
			minHeap.get(index).minDistance = temp;
			heapify(newIndex);
  		}

  	}

  // insertNode
  //
  // Insert a Node into the heap
  //
  // Time Complexity Requirement: theta(log(n))
	public void insertNode(Node in) {

		minHeap.add(in);

		int index = minHeap.size()-1;
		siftUp(index);
	
	}

	public void siftUp(int index){
		int parent = (index-1)/2;
		int temp;
		if(index!=0){
			if(minHeap.get(parent).minDistance > minHeap.get(index).minDistance){
				int temp = minHeap.get(parent).minDistance;
				minHeap.get(parent).minDistance = minHeap.get(index).minDistance;
				minHeap.get(index).minDistance = temp;
				siftUp(parent);
			}
		}
	}
	
  // findMin
  //
  // Returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(1)
	public Node findMin() {

		if(minHeap.size()==0){
			return null;	
		}

		return minHeap[0];
		
	}

  // extractMin
  //
  // Removes and returns the minimum element of the heap
  //
  // Time Complexity Requirement: theta(log(n))
	public Node extractMin() {

		if(minHeap.size()==0){
			return null;	
		}

		Node minNode = findMin();

		minHeap.remove(0);
		minHeap.add(0, minHeap.get(minHeap.size()-1));
		minHeap.remove(minHeap.size()-1); 

		if(minHeap.size()>0){
			siftDown(0);
		}

    return minNode;
  }

  public void siftDown(int index){
  	int left = index*2+1;
  	int right = index*2+2;
  	int newIndex;
	if(right>=minHeap.size()){
		if(left>=minHeap.size()){
			return;
		} else{
			newIndex = left;
		}
	} else{
		if(minHeap.get(left).minDistance <= minHeap.get(right).minDistance){
			newIndex = left;
		} else {
			newIndex = right;
		}
	}
	if(minHeap.get(index).minDistance > minHeap.get(newIndex).minDistance){
		int temp = minHeap.get(newIndex).minDistance;
		minHeap.get(newIndex).minDistance = minHeap.get(index).minDistance;
		minHeap.get(index).minDistance = temp;
		siftDown(newIndex);
	}
  }
	
  public String toString() {
		String output = "";
		for(int i = 0; i < minHeap.size(); i++) {
			output+= minHeap.get(i).getNodeName() + " ";
		}
		return output;
	}
	
///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

	public ArrayList<Node> toArrayList() {
		return minHeap;
	}
}
