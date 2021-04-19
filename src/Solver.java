import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solver {

    private State goal1;
    private State goal2;
    private State goal3;
    private State min3;
    private int cost1;
    private int cost2;
    private int cost3;
    private PriorityQueue<State> PQ;
    private ArrayDeque<State> Q;

    private class State {
        private int moves;
        private Board board;
        private State prev;
        private int cost;

        public State(Board initial) {
            moves = 0;
            prev = null;
            board = initial;
            cost = 0;
        }
    }

    public Solver(Board initial) {
        PQ = new PriorityQueue<State>(new Comparator<State>(){
            @Override
            public int compare(State a, State b) {
                int p = a.board.manhattan() + a.moves;
                int q = b.board.manhattan() + b.moves;
                return p - q;
            }
        });

        Q = new ArrayDeque<State>();

        PQ.offer(new State(initial));
        Q.offer(new State(initial));
        min3 = new State(initial);
        cost3 = Integer.MAX_VALUE;
    }

    public void dijkstra() {
        while (!PQ.isEmpty()) {
            State min = PQ.poll();
            if (min.moves > 30) {
                goal1 = null;
                break;
            }
            cost1 = min.cost;
            if (min.board.isGoal()) {
                goal1 = min;
                break;
            }
            for (Board b : min.board.getNeighbors()) {
                if (min.prev == null || !b.equals(min.prev.board)) {
                    State n = new State(b);
                    n.moves = min.moves + 1;
                    n.cost = b.val + min.cost;
                    n.prev = min;
                    PQ.offer(n);
                }
            }
        }
    }

    public void bfs() {
        while (!Q.isEmpty()) {
            int size = Q.size();
            for (int i = 0; i < size; i++) {
                State min = Q.poll();
                cost2 = min.cost;
                if (min.board.isGoal()) {
                    goal2 = min;
                    return;
                }
                if (min.moves > 15) {
                    goal2 = null;
                    return;
                }
                for (Board b : min.board.getNeighbors()) {
                    if (min.prev == null || !b.equals(min.prev.board)) {
                        State n = new State(b);
                        n.moves = min.moves + 1;
                        n.cost = b.val + min.cost;
                        n.prev = min;
                        Q.offer(n);
                    }
                }
            }
        }
    }

    public void dfs() {
        callDFS(min3);
    }

    public void callDFS(State min) {
        if (min.board.isGoal()) {
            goal3 = min;
            if (min.cost < cost3) cost3 = min.cost;
            return;
        }
        if (min.moves > 15) return;
        for (Board b : min.board.getNeighbors()) {
            if (min.prev == null || !b.equals(min.prev.board)) {
                State n = new State(b);
                n.moves = min.moves + 1;
                n.cost = b.val + min.cost;
                n.prev = min;
                callDFS(n);
            }
        }
    }

    public boolean isSolvabledij() {
        return goal1 != null;
    }

    public boolean isSolvablebfs() {
        return goal2 != null;
    }

    public boolean isSolvabledfs() {
        return goal3 != null;
    }

    public int dijCost() {
        dijkstra();
        return isSolvabledij() ? cost1 : -1;
    }

    public int bfsCost() {
        bfs();
        return isSolvablebfs() ? cost2 : - 1;
    }

    public int dfsCost() {
        dfs();
        return isSolvabledfs() ? cost3 : -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("SampleFile3.txt");
        Scanner sc = new Scanner(file);
        int[][] in = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                in[i][j] = sc.nextInt();

        Board initial = new Board(in);
        Solver solver = new Solver(initial);
        System.out.println("Minimum cost by Dijkstra's: " + solver.dijCost());
        System.out.println("Minimum cost by BFS: " + solver.bfsCost());
        System.out.println("Minimum cost by DFS: " + solver.dfsCost());
        sc.close();
    }
}

