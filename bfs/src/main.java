import java.util.ArrayList;

public class main {

    public static void main(String[] args) {
        System.out.println("This is the bfs algorithm for finding paths in a graph.");
        System.out.print("Enter the method of inputing the edges \n " +
                "0: Adjacency Matrix with 0s and 1s \n " +
                "1: Beginning and end of edges \n " +
                "-->");
        int inputType1 = IOUtil.readInt();
        int inputType2;
        final ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
        int beginning;
        int finish;
        if(inputType1 == 0) {
            System.out.print("Enter the number of vertices in the graph \n -->");
            int n = IOUtil.readInt();
            System.out.print("Enter the adjacency matrix (note the input can't be a boolean) \n");
                int[][] aMatrix = new int[n][n];
                for(int i = 0; i < n; i++) {
                    ArrayList<Integer> edges = new ArrayList<>();
                    for(int j = 0; j < n; j++) {
                        aMatrix[i][j] = IOUtil.readInt();
                        if(aMatrix[i][j] == 1) {
                            edges.add(j);
                        }
                    }
                    adjacencyMatrix.add(edges);
                }
        }
        if(inputType1 == 1) {
            System.out.print("Is the graph directed? \n 0: No \n 1: Yes \n -->");
            inputType2 = IOUtil.readInt();
            System.out.print("Enter the number of edges in the graph \n -->");
            int n = IOUtil.readInt();
            System.out.print("Note that the names of the vertices have to be non-negative integers. \n");
            System.out.print("Enter the edges of the graph:\n");
            int[][] edges = new int[n][2];
            for(int i = 0; i < n; i++) {
                edges[i][0] = IOUtil.readInt();
                edges[i][1] = IOUtil.readInt();
            }
            int max = -1;
            for(int i = 0; i < n; i++) {
                if(edges[i][0] > max) {
                    max = edges[i][0];
                }
                if(edges[i][1] > max) {
                    max = edges[i][1];
                }
            }
            for(int i = 0; i <= max; i++) {
                ArrayList<Integer> edgesFrom = new ArrayList<>();
                for(int j = 0; j < n; j++) {
                    if(edges[j][0] == i) {
                        edgesFrom.add(edges[j][1]);
                    }
                    if(edges[j][1] == i && inputType2 == 0) {
                        edgesFrom.add(edges[j][0]);
                    }
                }
                adjacencyMatrix.add(edgesFrom);
            }
        }

        while(true) {
            System.out.print("Specify the path you want to explore:\n" +
                    "Beginning:");
            beginning = IOUtil.readInt();
            System.out.print("End:");
            finish = IOUtil.readInt();
            ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
            Adjacency adjacency = new Adjacency(beginning, finish, adjacencyMatrix, paths);
            adjacency.bfs();
            if (paths.size() > 0) {
                System.out.print("All the possible paths are:\n");
                for (int i = 0; i < paths.size(); i++) {
                    System.out.print("[ ");
                    for (int j = 0; j < paths.get(i).size(); j++) {
                        System.out.print(paths.get(i).get(j) + " ");
                    }
                    System.out.println("]");
                }
                int shortest = Integer.MAX_VALUE;
                for (int i = 0; i < paths.size(); i++) {
                    if(paths.get(i).size() < shortest) {
                        shortest = paths.get(i).size();
                    }
                }
                System.out.print("The shortest path has length: " + (shortest - 1) + "\n");
                int howMany = 0;
                for(int i = 0; i < paths.size(); i++) {
                    if(paths.get(i).size() == shortest) {
                        howMany++;
                    }
                }
                System.out.println("There are " + howMany + " paths of length " + (shortest - 1));
                System.out.print("All the paths with length " + (shortest - 1) + " are:\n");
                for(int i = 0; i < paths.size(); i++){
                    if(paths.get(i).size() == shortest) {
                        System.out.print("[ ");
                        for(int j = 0; j < shortest; j++) {
                            System.out.print(paths.get(i).get(j) + " ");
                        }
                        System.out.println("]");
                    }
                }
            } else {
                System.out.println("There are no paths from " + beginning + " to " + finish);
            }
        }
    }
}
