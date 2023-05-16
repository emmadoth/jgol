import java.io.*;

class Cell {
    public boolean state;
    public byte neighbours;

    public Cell() {
        this.state = false;
        this.neighbours = 0;
    }

    @Override
    public String toString() {
        return this.state ? "*" : " ";
    }
}

class Board {
    public short width;
    public short height;
    public Cell[][] board;

    public Board(int width, int height) {
        this.width = (short)width;
        this.height = (short)height;
        this.board = new Cell[this.height][this.width];

        for(short y = 0; y < this.height; ++y) {
            for(short x = 0; x < this.width; ++x) {
                this.board[y][x] = new Cell();
    }   }   }

    public void setNeighbours() {
        for(short y = 0; y < this.height; ++y) {
            for(short x = 0; x < this.width; ++x) {
                byte n = 0;

                if(y > 0) {
                    if(                      this.board[y - 1][x    ].state) ++n;     // n
                    if(x < this.width - 1 && this.board[y - 1][x + 1].state) ++n;     // ne
                    if(x > 0              && this.board[y - 1][x - 1].state) ++n;     // nw
                }

                if(x < this.width - 1 && this.board[y    ][x + 1].state) ++n;     // e
                if(x > 0              && this.board[y    ][x - 1].state) ++n;     // w


                if(y < this.height - 1) {
                    if(                      this.board[y + 1][x    ].state) ++n; // s
                    if(x < this.width - 1 && this.board[y + 1][x + 1].state) ++n; // se
                    if(x > 0              && this.board[y + 1][x - 1].state) ++n; // sw
                }

                this.board[y][x].neighbours = n;
    }   }   }

    public void applyRules() {
        for(short y = 0; y < this.height; ++y) {
            for(short x = 0; x < this.width; ++x) {
                if(this.board[y][x].neighbours < 2 || this.board[y][x].neighbours > 3) {
                    this.board[y][x].state = false;
                } else if(this.board[y][x].neighbours == 3) {
                    this.board[y][x].state = true;
    }   }   }   }

    public void nextGeneration() {
        this.setNeighbours();
        this.applyRules();
    }

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();

        for(short y = 0; y < this.height; ++y) {
            for(short x = 0; x < this.width + 1; ++x) {
                if(x == this.height) {
                    str.append('\n');
                    continue;
                }
                str.append(this.board[y][x]);
        }   }
        return str.toString();
}   }


class jgol {

    private static boolean handleInput() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        switch(r.readLine()) {
            case "q":
                return false;
            default:
                break;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        Board board = new Board(10, 10);

        board.board[0][1].state = true;
        board.board[1][2].state = true;
        board.board[2][0].state = true;
        board.board[2][1].state = true;
        board.board[2][2].state = true;

        System.out.println(board);
        boolean run = handleInput();

        while(run) {
            board.nextGeneration();
            System.out.println(board);
            run = handleInput();
}   }   }

