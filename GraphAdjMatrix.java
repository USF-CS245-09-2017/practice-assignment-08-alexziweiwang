import java.util.Stack;

/**
 * GraphAdjMatrix.java
 * @author Alex Wang
 *
 */
public class GraphAdjMatrix implements Graph {

	private int[][] graph;
	private int size;
	
	/**
	 * Constructor
	 * @param size
	 * 			number of vertices in the graph
	 */
	public GraphAdjMatrix(int vertices) {
		size = vertices; 
		/*create a graph based on given number of vertex*/
		graph = new int[size][size];
		
	}

	
	/**
	 * Add an edge from given v1 to v2
	 */
	@Override
	public void addEdge(int v1, int v2) {
		graph[v1][v2] = 1; //directed edge
	}

	
	/**
	 * Apply topological sort and print all the vertices in such order
	 */
	@Override
	public void topologicalSort() {
		Stack<Integer> s = new Stack<Integer>();
		int v;
		int[] numIncident = new int[size];
		int printed = 0;
		
		for(v = 0; v <size; v++){
			numIncident[v] = 0; //initialize content in the array
		}
		
		/*check the number of precedent vertices of the vertex*/
		for(v =0; v <size; v++){
			int[] thisNeighbors = neighbors(v);
			for(int i=0; i < thisNeighbors.length; i++){
				int dest = thisNeighbors[i];
				numIncident[dest]++;
			}
		}
		
		for(v = 0; v <size; v++){
			if(numIncident[v] == 0){
				/*if the vertex has no precedent vertex, can start here*/
				s.push(v);
			}
		}
		
		if(s.empty()){
			/*case that not a single vertex has precedent vertex*/
			System.out.println("This graph is cyclic! Printing probably partially...");
			
			/*at least show vertices related with vertex 0*/
			v=0;
			numIncident[v] = 0;
			s.push(v);
		}
		
		
		while(!s.empty()){
			v = s.pop();
			System.out.print(v+" ");
			printed ++; //keep track of how many vertices are printed
			
			int[] thisNeighbors = neighbors(v);
			for(int i=0; i < thisNeighbors.length; i++){
				/*for every vertex of v's neighbor(destination)*/
				
				int dest = thisNeighbors[i];//current destination
				numIncident[dest]--;//precedent of dest minus one(v)
				if(numIncident[dest] == 0){//if no precedent for dest
					s.push(dest);		
				}
			}

		}
				
		if(printed< size){
			/*case that there are still vertex unprinted*/
			/*because nowhere to start within a cycle*/
			System.out.println("This graph is cyclic!");	
		}
		
		System.out.println("");
	}

	
	
	/**
	 * Returns array of neighbors(destination) of given vertex
	 */
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

		/*renew the fitting size for array of neighbors*/
		int[] neighbor = new int[index];
		System.arraycopy(nbr, 0, neighbor, 0, index);
		
		return neighbor;
	}

	
	
}
