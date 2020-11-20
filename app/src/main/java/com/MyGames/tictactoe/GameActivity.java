package com.MyGames.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    static final char HUMAN ='x';
    static final char Computer ='o';
    char[][] board;
    int[] aquiedMoves;
    Boolean end = false;

    LinearLayout LL1, LL2, LL3, LL4, LL5, LL6, LL7, LL8, LL9;
    ImageView IV1, IV2, IV3, IV4, IV5, IV6, IV7, IV8, IV9;
    Button quitBtn;

    static class MoveCoord{
        int row,column;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LL1 = findViewById(R.id.LL1);
        LL2 = findViewById(R.id.LL2);
        LL3 = findViewById(R.id.LL3);
        LL4 = findViewById(R.id.LL4);
        LL5 = findViewById(R.id.LL5);
        LL6 = findViewById(R.id.LL6);
        LL7 = findViewById(R.id.LL7);
        LL8 = findViewById(R.id.LL8);
        LL9 = findViewById(R.id.LL9);

        IV1 = findViewById(R.id.IV1);
        IV2 = findViewById(R.id.IV2);
        IV3 = findViewById(R.id.IV3);
        IV4 = findViewById(R.id.IV4);
        IV5 = findViewById(R.id.IV5);
        IV6 = findViewById(R.id.IV6);
        IV7 = findViewById(R.id.IV7);
        IV8 = findViewById(R.id.IV8);
        IV9 = findViewById(R.id.IV9);

        quitBtn = findViewById(R.id.quitBtn);

        board = new char[3][3];
        aquiedMoves = new int[9];

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++)
                board[i][j]='_';
        }

        LL1.setOnClickListener(this);
        LL2.setOnClickListener(this);
        LL3.setOnClickListener(this);
        LL4.setOnClickListener(this);
        LL5.setOnClickListener(this);
        LL6.setOnClickListener(this);
        LL7.setOnClickListener(this);
        LL8.setOnClickListener(this);
        LL9.setOnClickListener(this);
        quitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LL1:
                playersMove(1);
                break;
            case R.id.LL2:
                playersMove(2);
                break;
            case R.id.LL3:
                playersMove(3);
                break;
            case R.id.LL4:
                playersMove(4);
                break;
            case R.id.LL5:
                playersMove(5);
                break;
            case R.id.LL6:
                playersMove(6);
                break;
            case R.id.LL7:
                playersMove(7);
                break;
            case R.id.LL8:
                playersMove(8);
                break;
            case R.id.LL9:
                playersMove(9);
                break;
            case R.id.quitBtn:
                startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", 2));
                break;

        }
    }

    void playersMove(int move){
        int m = move;
        ImageView IV = null;

        for (int i=0; i<aquiedMoves.length; i++){
            if (aquiedMoves[i] == m){
                return;
            }
        }

        board[(m-1)/3][(m-1)%3]=HUMAN;
        showBoardState(board);
        aquiedMoves[m-1] = m;
        IV = MoveSelector(m);
        IV.setImageResource(R.drawable.spaceship);
        IV.setVisibility(View.VISIBLE);
        if (!areMovesLeft(board)) {
            if (calculateScore(board) == 0) {
                end = true;
                startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", 0));
                return;
            }
        }

        if (calculateScore(board) == 10) {
            end = true;
            startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", 1));
            return;
        }else if (calculateScore(board) == -10) {
            end = true;
            startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", -1));
            return;
        }

        m = findBestMove(board);
        System.out.println(m);
        board[(m-1)/3][(m-1)%3]=Computer;
        showBoardState(board);
        aquiedMoves[m-1] = m;
        IV = MoveSelector(m);
        IV.setImageResource(R.drawable.ufo);
        IV.setVisibility(View.VISIBLE);
        if (!areMovesLeft(board)) {
            if (calculateScore(board) == 0) {
                end = true;
                startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", 0));
                return;
            }
        }

        if (calculateScore(board) == 10) {
            end = true;
            startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", 1));
            return;
        }else if (calculateScore(board) == -10) {
            end = true;
            startActivity(new Intent(GameActivity.this, DialogActivity.class).putExtra("status", -1));
            return;
        }
    }

    ImageView MoveSelector(int move){
        ImageView IV = null;

        switch (move){
            case 1:
                IV = IV1;
                break;
            case 2:
                IV = IV2;
                break;
            case 3:
                IV = IV3;
                break;
            case 4:
                IV = IV4;
                break;
            case 5:
                IV = IV5;
                break;
            case 6:
                IV = IV6;
                break;
            case 7:
                IV = IV7;
                break;
            case 8:
                IV = IV8;
                break;
            case 9:
                IV = IV9;
                break;
        }

        return IV;
    }

    static Boolean areMovesLeft(char[][] board){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_')
                    return true;
            }
        }
        return false;
    }

    static int calculateScore(char[][] board){
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == HUMAN)
                    return +10;
                else if(board[row][0] == Computer)
                    return -10;
            }
        }

        for (int Column = 0; Column < 3; Column++) {
            if (board[0][Column] == board[1][Column] && board[1][Column] == board[2][Column]) {
                if (board[0][Column] == HUMAN)
                    return +10;
                else if(board[0][Column] == Computer)
                    return -10;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == HUMAN)
                return +10;
            else if(board[0][0] == Computer)
                return -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == HUMAN)
                return +10;
            else if(board[0][2] == Computer)
                return -10;
        }

        return 0;
    }

    static int minmax(char[][] board, int depth, boolean isMax){
        int score = calculateScore(board);

        if (score == 10 || score == -10)
            return score;

        if (!areMovesLeft(board))
            return 0;

        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = HUMAN;
                        best = Math.max(best, minmax(board, depth+1, !isMax));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '_') {
                        board[i][j] = Computer;
                        best = Math.min(best, minmax(board, depth+1, !isMax));
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    static int findBestMove(char[][] board){
        int bestVal = 1000;
        MoveCoord bestMove = new MoveCoord();
        bestMove.row = -1;
        bestMove.column = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = Computer;
                    int moveVal = minmax(board, 0, true);
                    board[i][j] = '_';

                    if(moveVal < bestVal){
                        bestMove.row = i;
                        bestMove.column = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return (3*bestMove.row +bestMove.column + 1);
    }

    static void showBoardState(char[][] board) {		////Prints state
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++)
                System.out.print(" "+board[i][j]);
            System.out.println();
        }
    }
}