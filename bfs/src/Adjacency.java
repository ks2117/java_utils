import java.util.ArrayList;

public class Adjacency {

    private ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
    private int beginning = 0;
    private int finish = 0;
    private final ArrayList<ArrayList<Integer>> paths;

    public Adjacency() {
        this.adjacencyMatrix = new ArrayList<>();
        this.beginning = 0;
        this.finish = 0;
        this.paths = new ArrayList<>();
    }

    Adjacency(int beginning, int finish, ArrayList<ArrayList<Integer>> adjacencyMatrix,
        ArrayList<ArrayList<Integer>> paths) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.beginning = beginning;
        this.finish = finish;
        this.paths = paths;
    }

    private boolean wasVisited(int vertex, ArrayList<Integer> previous) {
        boolean test = false;
        for(int i = 0; i < previous.size(); i++) {
            if(previous.get(i) == vertex) {
                test = true;
            }
        }
        return test;
    }

    private void bfs(int current, ArrayList<Integer> previous) {
        if(current == finish) {
            previous.add(current);
            paths.add(previous);
        } else {
            previous.add(current);
            for (int i = 0; i < adjacencyMatrix.get(current).size(); i++) {
                int next = adjacencyMatrix.get(current).get(i);
                int currentCopy;
                if (!wasVisited(next, previous)) {
                    ArrayList<Integer> previousCopy = new ArrayList<>();
                    previousCopy.addAll(previous);
                    currentCopy = next;
                    bfs(currentCopy, previousCopy);
                }
            }
        }
    }

    void bfs() {
        this.bfs(beginning, new ArrayList<>());
    }

}
