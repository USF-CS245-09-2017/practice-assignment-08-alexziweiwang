import java.util.Stack;

public class GraphAdjMatrix implements Graph {

	int[][] graph;
	int size;
	
	/**
	 * Constructor
	 * @param size
	 * 			number of nodes in the graph
	 */
	public GraphAdjMatrix(int size) {
		this.size = size;
		graph = new int[size][size];
	}

	@Override
	public void addEdge(int v1, int v2) {
		graph[v1][v2] = 1; //directed
	
	}

	@Override
	public void topologicalSort() {
		Stack<Integer> s = new Stack<Integer>();
		int v, n;
		int[] numIncident = new int[size];
		int[] order = new int[size];
		int printed = 0;
		
		for(v = 0; v <size; v++){
			numIncident[v] = 0;
		}
		for(v =0; v <size; v++){
			int[] thisNeighbors = neighbors(v);
			for(int i=0; i < thisNeighbors.length; i++){
				int dest = thisNeighbors[i];
				numIncident[dest]++;
			}
		}
		
		for(v = 0; v <size; v++){
			if(numIncident[v] == 0){
				s.push(v);
			}
		}
		
		if(s.empty()){
			/*not a single node has precedent node*/
			System.out.println("This graph is cyclic!");
			v=0;
			numIncident[v] = 0;
			s.push(v);
		}
		
		n = 0;
		while(!s.empty()){
			v = s.pop();
			System.out.print(v+" ");
			printed ++;
			if(n+1 < order.length){
				order[n++] = v;
			}
			int[] thisNeighbors = neighbors(v);
			for(int i=0; i < thisNeighbors.length; i++){
				int dest = thisNeighbors[i];
				numIncident[dest]--;
				if(numIncident[dest] == 0){
					s.push(dest);		
				}
			}

		}
				
		if(printed< size){
			System.out.println("This graph is cyclic!");	
		}
		
		System.out.println("");
	}

	
	
	
	@Override
	public int[] neighbors(int vertex) {
		int[] nbr = new int[size];
		int index = 0;
		for(int i=0; i < size ; i++){
			int thisVet = graph[vertex][i]; //position in the matrix
			if(thisVet==1 && i != vertex){
				nbr[index] = i;
				index++;
			
			}
			
		}
		
		int[] neighbor = new int[index];
		System.arraycopy(nbr, 0, neighbor, 0, index);
		
		return neighbor;
	}

	
	
}
