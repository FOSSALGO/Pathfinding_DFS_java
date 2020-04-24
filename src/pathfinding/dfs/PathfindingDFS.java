package pathfinding.dfs;

public class PathfindingDFS {

    public static void main(String[] args) {
        int[][] maze = {
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {-1, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1},
            {-1, 0, 0, 0, 0, 0, 0, -1, 0, 0, -1},
            {-1, -1, -1, 0, -1, -1, -1, -1, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, -1, -1, -1, -1, 0, 0, -1, 0, 0, -1},
            {-1, 0, 0, 0, 0, -1, 0, -1, 0, 0, -1},
            {-1, 1, 0, 0, -1, -1, -1, -1, 0, 0, -1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0, -2, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };

        System.out.println("");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + ", ");
            }
            System.out.println("");
        }

        //inisialisasi array arah
        //ARAH = 0 --> NORTH
        //ARAH = 1 --> EAST
        //ARAH = 2 --> SOUTH
        //ARAH = 3 --> WEST
        int[][] arah = new int[maze.length][maze[0].length];
        for (int i = 0; i < arah.length; i++) {
            for (int j = 0; j < arah[i].length; j++) {
                arah[i][j] = -1;
            }
        }

        Stack<Posisi> stack = new Stack<Posisi>();
        int startX = 8, startY = 1;
        Posisi start = new Posisi(startX, startY);
        stack.push(start);
        arah[startX][startY] = 0;//NORTH        
        while (!stack.isEmpty()) {                        
            Posisi top;
            top = stack.peek();
            int io = top.I;
            int jo = top.J;
            int value = maze[io][jo];
            int nextValue = value + 1;
            int direction = arah[io][jo];

            Posisi front = null;
            Posisi right = null;
            Posisi left = null;

            Posisi north = null;
            Posisi east = null;
            Posisi south = null;
            Posisi west = null;

            //NORTH
            int i = io - 1;
            int j = jo;
            if (i >= 0 && i < maze.length && j >= 0 && j < maze[io].length) {
                north = new Posisi(i, j);
            }

            //EAST
            i = io;
            j = jo + 1;
            if (i >= 0 && i < maze.length && j >= 0 && j < maze[io].length) {
                east = new Posisi(i, j);
            }

            //SOUTH
            i = io + 1;
            j = jo;
            if (i >= 0 && i < maze.length && j >= 0 && j < maze[io].length) {
                south = new Posisi(i, j);
            }

            //WEST
            i = io;
            j = jo - 1;
            if (i >= 0 && i < maze.length && j >= 0 && j < maze[io].length) {
                west = new Posisi(i, j);
            }

            //inisialisasi posisi left,front,right
            switch (direction) {
                case 0:
                    left = west;
                    front = north;
                    right = east;
                    break;
                case 1:
                    left = north;
                    front = east;
                    right = south;
                    break;
                case 2:
                    left = east;
                    front = south;
                    right = west;
                    break;
                case 3:
                    left = south;
                    front = west;
                    right = north;
                    break;
                default:
                    break;
            }

            //cek FINISH
            if (front != null && maze[front.I][front.J] == -2) {
                System.out.println("Jumlah langkah = " + value);
                System.out.println("FINISH");
                break;
            }
            if (right != null && maze[right.I][right.J] == -2) {
                System.out.println("Jumlah langkah = " + value);
                System.out.println("FINISH");
                break;
            }
            if (left != null && maze[left.I][left.J] == -2) {
                System.out.println("Jumlah langkah = " + value);
                System.out.println("FINISH");
                break;
            }

            //jika belum finish maka
            //cek front ; right ; left
            if (front != null && maze[front.I][front.J] == 0) {
                //bergerak maju
                maze[front.I][front.J] = nextValue;
                int d = direction;
                arah[front.I][front.J] = d;
                stack.push(front);
            } else if (right != null && maze[right.I][right.J] == 0) {
                maze[right.I][right.J] = nextValue;
                int d = (direction + 1) % 4;
                arah[right.I][right.J] = d;
                stack.push(right);
            } else if (left != null && maze[left.I][left.J] == 0) {
                maze[left.I][left.J] = nextValue;
                int d = (direction - 1) % 4;
                if (d < 0) {
                    d = 4 + d;
                }
                arah[left.I][left.J] = d;
                stack.push(left);
            } else {
                //Backward menggunakan operasi pop pada stack
                maze[io][jo] = -3;
                try {
                    stack.pop();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }               
            
            //cetak stack
            System.out.print("[");
            if (!stack.isEmpty()) {
                Object[] path = stack.getElements();
                for (int p = 0; p < path.length; p++) {
                    if (p > 0) {
                        System.out.print(",");
                    }
                    Posisi pos = (Posisi) path[p];
                    System.out.print(" (" + pos.I + "," + pos.J + ")");
                }
            }
            System.out.println(" ]");
        }
        
        System.out.println("");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + ", ");
            }
            System.out.println("");
        }
        
    }
}
