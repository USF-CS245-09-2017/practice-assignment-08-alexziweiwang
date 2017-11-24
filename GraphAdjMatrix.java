

public class GraphAdjMatrix implements Graph {

	int[][] graph;
	int size;
	
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
		boolean[] visited = new boolean[size];
		
		for(int i = 0; i < visited.length ; i++){
			visited[i] = false; //set the default status of visited list
		}

		for(int j = 0; j < size ; j ++){
			if(visited[j] == false){
				dfs(j, visited);
			}
		}
		
	}

	/**
	 * Depth-first
	 * @param vertex
	 * @param visited
	 */
	private void dfs(int vertex, boolean[] visited){
		ArrayQueue queue = new ArrayQueue();
		queue.enqueue(vertex);
		visited[vertex] = true;
		
		while(!queue.empty()){
			int u = (Integer) queue.dequeue();//current item to remove from queue
		//	System.out.print(+u+" "); //TODO
			
			int[] thisNbr = neighbors(u);
			for(int k=0;k < thisNbr.length ; k ++ ){
				
				int v = thisNbr[k]; // every neighbor of given vertex
				if(visited[v] == false){
					visited[v] = true;
					queue.enqueue(v); // add this neighbor into queue
				}
			}
			
		}
		printVertexWay();
	}
	
	private void printVertexWay(){
		int v = 0;
		boolean[] printed = new boolean[size];
		for(int i=0; i < size ; i++){
			printed[i] = false;
		}
		System.out.print(0+ " ");
		while(deepestDirection(v) != -1){
			System.out.print(deepestDirection(v)+ " ");
			v = deepestDirection(v);
			printed[v] = true;
		}
		System.out.println(" ");
		
		
	}
	
	private int deepestDirection(int vertex){
		int deepestVertex=-1; //position of vertex
		int deepesttWay = -1; //number of turning
		int[] neighborArr = neighbors(vertex);
		
		for(int i = 0; i < neighborArr.length ; i++){
			if(deepestNum(vertex, 0) > deepesttWay){
				deepestVertex = neighborArr[i]; //the vertex with deepest way to go
			}
			
		}
		return deepestVertex;
	}
	
	private int deepestNum(int vertex, int turning){
		int turningNum = turning;
		if(hasWayIn(vertex)){
			int[] nbr = neighbors(vertex);
			if(nbr.length!= 0 ){
				turningNum ++;
				for(int i=0; i < nbr.length ; i++){
				
					if(!twoWay(vertex, nbr[i])){
						turningNum = turningNum + deepestNum(nbr[i], turning);
					}
				}
			}
			
		}
		
		if(turningNum > size){
			System.out.println("this is cycling!!");
		}
		return turningNum;
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

	
	private boolean hasWayIn(int vertex){
		boolean wayIn = false;
		
		
		for(int i =0; i < size ; i ++){
			if(graph[vertex][i] ==1){
				wayIn = true;
			}
		}
		
		return wayIn;
	}
	
	private boolean twoWay(int v1, int v2){
		boolean both = false;
		
		if(graph[v1][v2] == 1 && graph[v2][v1] == 1){
			both = true;
		}
		
		return both;
	}
	
}
