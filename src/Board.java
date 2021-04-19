import java.util.*;

public class Board {

    /*
     * N x N Board. Since Goal shown in assignment
     * description is 3 x 3, we have initialized the length
     * N = 3 to to allow and work with 3 x 3 boards only.
     */
    private int[] goal = new int[]{1, 2, 3, 8, 0, 4, 7, 6, 5}; // goal state
    private static int N = 3; // dimension
    private int[] in; // initial State
    int val;

    // constructor
    public Board(int[][] in) {
        this.in = new int[N * N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.in[i * N + j] = in[i][j];
    }

    private Board(int[] board) {
        this.in = new int[N * N];
        for (int i = 0; i < N * N; i++) this.in[i] = board[i];
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int d = 0;
        Integer[] g = new Integer[]{1, 2, 3, 8, 0, 4, 7, 6, 5};
        for (int i = 0; i < N * N; i++) {
            if (in[i] != 0 && in[i] != g[i]) {

                d += (Math.abs(i%3 - Arrays.asList(g).indexOf(in[i])%3) + Math.abs(i/3 - Arrays.asList(g).indexOf(in[i])/3));
            }
        }
        return d;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < N * N; i++) {
            if (in[i] != goal[i]) return false;
        }
        return true;
    }


    // does this board equal x?
    public boolean equals(Object x) {
        if (x == this)  return true;
        if (x == null)  return false;
        if (x.getClass() != this.getClass()) return false;

        Board y = (Board) x;
        return Arrays.equals(this.in, y.in);
    }

    private Board swap(Board a, int i, int j) { // swap elements
        int temp = a.in[j];
        a.in[j] = a.in[i];
        a.in[i] = temp;
        return a;
    }

    // get all neighboring boards i.e. states
    public Iterable<Board> getNeighbors() {
        int index = 0; // stores index of block : '0'
        boolean found = false;
        Board b;
        ArrayDeque<Board> q = new ArrayDeque<Board>();

        for (int i = 0; i < N * N; i++) {   // search for empty block
            if (in[i] == 0) {
                index = i;
                found = true;
                break;
            }
        }

        if (!found)  return null;

        if (index / N != 0) {
            b = new Board(in);
            b.val = in[index - N];
            swap(b, index, index - N);
            q.offer(b);
        }
        if (index / N != (N - 1)) {
            b = new Board(in);
            b.val = in[index + N];
            swap(b, index, index + N);
            q.offer(b);
        }

        if ((index % N) != 0) {
            b = new Board(in);
            b.val = in[index - 1];
            swap(b, index, index - 1);
            q.offer(b);
        }

        if ((index % N) != N - 1) {
            b = new Board(in);
            b.val = in[index + 1];
            swap(b, index, index + 1);
            q.offer(b);
        }

        return q;
    }

    // convert board to string
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < N * N; i++) {
            s.append(String.format("%2d ", in[i]));
            if ((i + 1) % N == 0) s.append("\n");
        }
        return s.toString();
    }

}
