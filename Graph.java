import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Graph{
		int vertexNumber;
		int edgeNumber;
		Edges edges;
		ArrayList<Node> nodes;
		
		/*Creating Graph*/
		Graph(int v){
			vertexNumber = v;
			edgeNumber=0;
			edges = new Edges();			
		}
		
		void setNodes(ArrayList<Node> nodes){
			this.nodes=nodes;
		}
		
		/*Adding Edges*/
		void addEdge(int source, int target){
			if(!edges.edgeMap.containsKey(source)){
				edges.edgeMap.put(source, new ArrayList<Integer>());
			}
			edges.edgeMap.get(source).add(target);
			edgeNumber++;
		}
		
		
		/*Constructing Path Tree*/
		List<List<Integer>> constructTree(int source, int target,int L){
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			if(source==target){
				List<Integer> temp = new ArrayList<Integer>();
				temp.add(source);
				result.add(temp);
				return result;
			}
			boolean[] visited = new boolean[vertexNumber];
			Deque<Integer> path = new ArrayDeque<Integer>();
			getAllPathsDijkstra(source, target, visited, path, result,L);
			return result;
		}
		
		/*Using Dijkstra (Unweighted version) to All Nodes*/
		void getAllPathsDijkstra(int source, int target, boolean[] visited, Deque<Integer> path, List<List<Integer>> result,int L){
			for(int i =0;i<nodes.size();i++)
				if(source==nodes.get(i).intID)
					visited[i] = true; // Mark visited
			path.add(source); // Add to the end
			if(source==target){
				if(path.size()<L)
				result.add(new ArrayList<Integer>(path));
			}
			else{
				if(edges.edgeMap.containsKey(source)){
					for(Integer i : edges.edgeMap.get(source)){
						for(int j =0;j<nodes.size();j++)
							if(i==nodes.get(j).intID)
								if(!visited[j])
									getAllPathsDijkstra(i, target, visited, path, result,L);
					}
				}
			}
			path.removeLast();
			for(int i =0;i<nodes.size();i++)
				if(source==nodes.get(i).intID)
					visited[i] = false;
		}
	}
