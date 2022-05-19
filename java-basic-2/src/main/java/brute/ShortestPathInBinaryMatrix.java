package brute;

import java.util.*;

public class ShortestPathInBinaryMatrix {
    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if(grid[0][0] == 1 || grid[n-1][m-1] == 1) {
            return -1;
        }
        int res = 0;
        Set<Pair> visited = new HashSet<>();
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(0, 0));
        List<Pair> dirs = List.of(
                new Pair(-1, 0), new Pair(-1, 1),
                new Pair(0, 1), new Pair(1, 1),
                new Pair(1, 0), new Pair(1, -1),
                new Pair(0, -1), new Pair(-1, -1));
        while(!queue.isEmpty()) {
            res += 1;
           // for(int i=0)
        }
        return -1;
    }
}
