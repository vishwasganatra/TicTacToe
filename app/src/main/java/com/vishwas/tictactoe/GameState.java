package com.vishwas.tictactoe;

import java.util.Random;


public class GameState {
    private int size, board[][], moveCount;
    private Random rnd;

    public GameState() {

    }

    public GameState(int size) {
        this.moveCount = 0;
        this.size = size;
        board = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                board[i][j] = 0; // board[i][j] = -1 for O move and +1 for X move
            }
        }
        rnd = new Random();
        //board[0][0] = -1;
        //board[1][2] = 1;
//		board[1][0] = -1;
//		board[0][2] = 1;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public void printBoard() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void drop(Poi p, int player) {
        this.board[ p.getX() ][ p.getY() ] = player;
    }

    public int check(int grid[][]) {
        int cnt;
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                int val = grid[i][j];
                if(val == 0)continue;

                cnt = 0;
                for(int k=0; k<size && j+k<size; k++) {
                    int x = i;
                    int y = j + k;
                    if( grid[x][y] == val)cnt++;
                }
                if(cnt == size) return val;

                cnt = 0;
                for(int k=0; k<size && i+k<size; k++) {
                    int x = i + k;
                    int y = j;
                    if( grid[x][y] == val)cnt++;
                }
                if(cnt == size)return val;

                cnt = 0;
                for(int k=0; k<size && i+k<size && j+k<size; k++) {
                    int x = i + k;
                    int y = j + k;
                    if( grid[x][y] == val)cnt++;
                }
                if(cnt == size)return val;

                cnt = 0;
                for(int k=0; k<size && i+k<size && j-k>=0; k++) {
                    int x = i + k;
                    int y = j - k;
                    if( grid[x][y] == val)cnt++;
                }
                if(cnt == size)return val;
            }
        }
        return 0;
    }

    public Poi minimax(int grid[][], int currentPlayer, int depth) {
        int here = check(grid);
        if(here != 0) {
            Poi retHere = new Poi(here);
            retHere.setD(depth);
            return retHere;
        }

        if(currentPlayer == 1) {
            Poi nRet = new Poi(0, true);
            Poi pRet = new Poi(100, true);
            Poi dRet = new Poi(0, true);
            int n=0, p=0, d=0, ret=0;

            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    if(grid[i][j] == 0) {
                        grid[i][j] = currentPlayer;
                        Poi pt = minimax(grid, currentPlayer * (-1), depth+1);
                        if(pt.getH() == -1) {
                            n++;
                            if(pt.getD() >= nRet.getD()) nRet = new Poi(i, j, -1, pt.getD());
                        }
                        else if(pt.getH() == 1) {
                            p++;
                            if(pt.getD() <= pRet.getD()) pRet = new Poi(i, j, 1, pt.getD());
                        }
                        else {
                            d++;
                            if(pt.getD() >= dRet.getD()) dRet = new Poi(i, j, 0, pt.getD());
                        }
                        grid[i][j] = 0;
                    }
                }
            }

            if(p != 0)ret = 1;
            else if(n != 0 && d == 0)ret = -1;

            if(ret == 1) return pRet;
            else if(ret == -1) return nRet;
            else return dRet;
        }
        else { // currentPLayer = -1;
            Poi nRet = new Poi(100, true);
            Poi pRet = new Poi(0, true);
            Poi dRet = new Poi(0, true);
            int n=0, p=0, d=0, ret=0;

            for(int i=0; i<size; i++) {
                for(int j=0; j<size; j++) {
                    if(grid[i][j] == 0) {
                        grid[i][j] = currentPlayer;
                        Poi pt = minimax(grid, currentPlayer * (-1), depth+1);
                        if(pt.getH() == -1) {
                            n++;
                            if(pt.getD() <= nRet.getD()) nRet = new Poi(i, j, -1, pt.getD());
                        }
                        else if(pt.getH() == 1) {
                            p++;
                            if(pt.getD() >= pRet.getD()) pRet = new Poi(i, j, 1, pt.getD());
                        }
                        else {
                            d++;
                            if(pt.getD() >= dRet.getD()) dRet = new Poi(i, j, 0, pt.getD());
                        }
                        grid[i][j] = 0;
                    }
                }
            }

            if(n != 0)ret = -1;
            else if(p != 0 && d == 0)ret = +1;

            if(ret == 1) return pRet;
            else if(ret == -1) return nRet;
            else return dRet;
        }
    }

    /*
    public Poi minimax(int grid[][], int currentPlayer, int depth) {
        int here = check(grid);
        if(here != 0) return new Poi(here);

        Poi nRet = new Poi();
        Poi pRet = new Poi();
        Poi dRet = new Poi();
        int n=0, p=0, d=0;

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(grid[i][j] == 0) {
                    grid[i][j] = currentPlayer;
                    Poi pt = minimax(grid, currentPlayer * (-1), depth+1);
                    if(pt.getH() == -1) {
                        n++;
                        if(n == 1) nRet = new Poi(i, j, -1);
                    }
                    else if(pt.getH() == 1) {
                        p++;
                        if(p == 1) pRet = new Poi(i, j, 1);
                    }
                    else {
                        d++;
                        if(d == 1) dRet = new Poi(i, j, 0);
                    }
                    grid[i][j] = 0;
                }
            }
        }

        int ret = 0;
        if(currentPlayer == 1) {
            if(p != 0)ret = 1;
            else if(n != 0 && d == 0)ret = -1;
        }
        else {
            if(n != 0)ret = -1;
            else if(p != 0 && d == 0)ret = +1;
        }
        if(ret == 1) return pRet;
        else if(ret == -1) return nRet;
        else return dRet;
    }
    */

    public Poi generateNextMove(int player) {
        Poi p = minimax(board, player, 0);
        this.drop(p, player);
        return p;
    }

    public Poi randomMove(int player) {
        moveCount++;
        while(true) {
            int x = (rnd.nextInt() % size + size) % size;
            int y = (rnd.nextInt() % size + size) % size;
            if(board[x][y] == 0) {
                Poi p = new Poi(x, y);
                drop(p, player);
                return p;
            }
        }
    }

    public Poi properMove(int player) {
        moveCount++;
        int arr[][];
        arr = new int[size][size];
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                arr[i][j] = board[i][j];
            }
        }
        Poi p = minimax(arr, player, 0);
        drop(p, player);
        return p;
    }

    public Poi next(Poi p, int player, int level) {
        if(board[ p.getX() ][ p.getY() ] != 0) return p;
        drop(p, player);
        player *= (-1);
        moveCount++;
        GameEnd ge = checkGameEnd();
        if(ge.getIsDraw()) {
            return new Poi(size, size);
        }
        if(ge.getIsResult()) {
            return new Poi(size+1, size+1);
        }
        if(level == 0) { // easy
            return randomMove(player);
        }
        else if(level == 1) { // medium
            int x = rnd.nextInt();
            if(x%10 == 0) return randomMove(player);
            else return properMove(player);
        }
        else if(level == 2) { // hard
            return properMove(player);
        }
        return p;
    }

    public GameEnd endState() {
        GameEnd ge = new GameEnd();
        ge.setIsResult(true);
        int cnt;
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                int val = board[i][j];
                if(val == 0)continue;

                ge.Reset();
                cnt = 0;
                for(int k=0; k<size && j+k<size; k++) {
                    int x = i;
                    int y = j + k;
                    ge.Push(new Poi(x, y), val);
                    if(board[x][y] == val) cnt++;
                }
                if(cnt == size) return ge;

                ge.Reset();
                cnt = 0;
                for(int k=0; k<size && i+k<size; k++) {
                    int x = i + k;
                    int y = j;
                    ge.Push(new Poi(x, y), val);
                    if(board[x][y] == val)cnt++;
                }
                if(cnt == size) return ge;

                ge.Reset();
                cnt = 0;
                for(int k=0; k<size && i+k<size && j+k<size; k++) {
                    int x = i + k;
                    int y = j + k;
                    ge.Push(new Poi(x, y), val);
                    if(board[x][y] == val)cnt++;
                }
                if(cnt == size) return ge;

                ge.Reset();
                cnt = 0;
                for(int k=0; k<size && i+k<size && j-k>=0; k++) {
                    int x = i + k;
                    int y = j - k;
                    ge.Push(new Poi(x, y), val);
                    if(board[x][y] == val)cnt++;
                }
                if(cnt == size) return ge;
            }
        }
        return ge;
    }

    public GameEnd checkGameEnd() {
        GameEnd ge = new GameEnd();
//		System.out.println("checkGameEnd moveCount = " + moveCount);
        if(check(this.board) == 0 && moveCount == size*size) {
            ge.setIsDraw(true);
            return ge;
        }
        else {
            int winner = check(this.board);
            if(winner == 0) return ge;
            else return endState();
        }
    }

    public int check1() {
        return check(this.board);
    }

    public void computerStart() {
        drop(new Poi(0, 0), -1);
        moveCount++;
        //properMove(-1);
    }

//    public boolean twoPlayerMove(Poi p, int player) {
//        if(board[p.getX()][p.getY()] != 0) return false;
//        drop(p, player);
//        moveCount++;
//        return true;
//    }
}