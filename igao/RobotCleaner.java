package igao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RobotCleaner {

    public static void main(String[] args) {
        int[][] room = {
                {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

        int startRow = 1;
        int startCol = 3;

        RobotCleaner cleaner = new RobotCleaner();
        cleaner.clean(room, 1, 3);
        for (int[] ints : room) {
            System.out.println(Arrays.toString(ints));
        }
        assert Arrays.deepEquals(room, new int[][]{
                {3, 3, 3, 3, 3, 0, 3, 3},
                {3, 3, 3, 3, 3, 0, 3, 3},
                {3, 0, 3, 3, 3, 3, 3, 3},
                {0, 0, 0, 3, 0, 0, 0, 0},
                {3, 3, 3, 3, 3, 3, 3, 3}
        });

    }

    private void clean(int[][] room, int r, int c) {
        new Robot(r, c, room).cleanRoom();
    }

    static class Robot {
        static final int NORTH = 0;
        static final int SOUTH = -2;
        static final int WEST = -1;
        static final int EAST = 1;
        private final int[][] room;
        int r;
        int c;
        int facing;
        Set<Node> visited;


        public Robot(int r, int c, int[][] room) {
            facing = NORTH;
            this.r = r;
            this.c = c;
            this.room = room;
            visited = new HashSet<>();
        }

        public void move() {
            switch (this.facing) {
                case NORTH:
                    bumpOrMove(r - 1, c);
                    break;
                case SOUTH:
                    bumpOrMove(r + 1, c);
                    break;
                case EAST:
                    bumpOrMove(r, c + 1);
                    break;
                case WEST:
                    bumpOrMove(r, c - 1);
                    break;
            }
        }

        private void bumpOrMove(int r, int c) {
            if (r < 0 || r >= room.length || c < 0 || c >= room[r].length) {
                System.out.printf("BUMP WALL AT: %d,%d \n", r, c);
                visited.add(new Node(r, c));
            } else if (room[r][c] == 0) {
                visited.add(new Node(r, c));
                System.out.printf("BUMP OBSTACLE AT: %d,%d \n", r, c);
            } else {
                this.r = r;
                this.c = c;
            }
        }

        public void turnLeft() {
            switch (this.facing) {
                case NORTH:
                    this.facing = WEST;
                    break;
                case SOUTH:
                    this.facing = EAST;
                    break;
                case EAST:
                    this.facing = NORTH;
                    break;
                case WEST:
                    this.facing = SOUTH;
                    break;
            }
        }

        public void turnRight() {
            switch (this.facing) {
                case NORTH:
                    this.facing = EAST;
                    break;
                case SOUTH:
                    this.facing = WEST;
                    break;
                case EAST:
                    this.facing = SOUTH;
                    break;
                case WEST:
                    this.facing = NORTH;
                    break;
            }

        }

        public void clean() {
            System.out.printf("CLEANED %d,%d\n", r, c);
            room[this.r][this.c] = 3;

        }

        public void startCleaning() {

        }

        public void cleanRoom() {
            clean();
            visited.add(new Node(this.r, this.c));
            if (notVisited(WEST) && canGoLeft()) {
                cleanRoom();
                System.out.println("canGoRight() = " + canGoRight());
            }
            if (notVisited(NORTH) && canGoNorth()) {
                cleanRoom();
                System.out.println("canGoSouth() = " + canGoSouth());

            }
            if (notVisited(EAST) && canGoRight()) {
                cleanRoom();
                System.out.println("canGoLeft() = " + canGoLeft());

            }
            if (notVisited(SOUTH) && canGoSouth()) {
                cleanRoom();
                System.out.println("canGoNorth() = " + canGoNorth());

            }
        }

//        public void cleanRoom() {
//            Deque<Node> stack = new LinkedList<>();
//            stack.add(new Node(this.r, this.c));
//            Deque<Integer> backtrackQueue = new LinkedList<>();
//            boolean backtracking = true;
//            while (!stack.isEmpty()) {
//                Node n = stack.pop();
//                backtracking = true;
//                visited.add(n);
//                clean();
//                if (notVisited(NORTH) && canGoNorth()) {
//                    stack.add(new Node(this.r - 1, this.c, n));
//                    backtracking = false;
//                }
//
//                if (notVisited(WEST) && canGoLeft()) {
//                    stack.add(new Node(this.r, this.c - 1, n));
//                    backtracking = false;
//                }
//
//                if (notVisited(SOUTH) && canGoSouth()) {
//                    stack.add(new Node(this.r + 1, this.c, n));
//                    backtracking = false;
//                }
//                if (notVisited(EAST) && canGoRight()) {
//                    stack.add(new Node(this.r, this.c + 1,n ));
//                    backtracking = false;
//                }
//                if (backtracking)  {
//                    backtrackTo()
//                }
//
//            }
//            System.out.println("Finished Cleaning");
//            for (int[] ints : room) {
//                System.out.println(Arrays.toString(ints));
//            }
//        }

        private boolean canGoRight() {
            int lastC = c;
            turnRight();
            move();
            turnLeft();
            return lastC != c;
        }

        private boolean canGoSouth() {
            int lastR = r;
            turnRight();
            turnRight();
            move();
            turnRight();
            turnRight();

            return lastR != r;
        }

        private boolean notVisited(int direction) {
            boolean result = false;
            switch (direction) {
                case NORTH:
                    result = visited.contains(new Node(this.r - 1, this.c));
                    break;
                case SOUTH:
                    result = visited.contains(new Node(this.r + 1, this.c));
                    break;
                case WEST:
                    result = visited.contains(new Node(this.r, this.c - 1));
                    break;
                case EAST:
                    result = visited.contains(new Node(this.r, this.c + 1));
                    break;
            }

            return !result;
        }

        private boolean canGoLeft() {
            int lastC = c;
            turnLeft();
            move();
            turnRight();
            return lastC != c;
        }

        private boolean canGoNorth() {
            int lastR = r;
            move();
            return lastR != r;
        }

    }

    static class Node {
        int r;
        int c;
        Node from;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Node(int r, int c, Node from) {
            this.r = r;
            this.c = c;
            this.from = from;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return r == node.r && c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}