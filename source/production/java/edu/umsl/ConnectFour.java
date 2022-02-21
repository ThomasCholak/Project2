/*
Thomas Cholak
Professor Steve Riegerix
CMP SCI 2261
20 February 2022

Assignment: Coding a Connect 4 game using a multi-dimensional array, where connecting four pieces is counted as a victory.
 */

package edu.umsl;

import java.util.Scanner;
import java.util.InputMismatchException; //imported for error handling
//import java.util.*;

public class ConnectFour {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        int turn = 0;
        boolean retry = false;

        char[][] board = new char[6][7]; //initialize board

        while (retry == false && turn < 41) {          //42 turns = draw
            displayBoard(board);
            if (turn % 2 == 0) { //alternates between players depending on turn
                turn++;
                dropADisc('Y', board);
                checkForWinner('Y', board);
                if(checkForWinner('Y', board) == true) {
                    displayBoard(board); //prints out winning board
                    System.out.println(" Yellow player has won.");
                    break; }
            } else {
                turn++;
                dropADisc('R', board);
                checkForWinner('R', board);
                if(checkForWinner('R', board) == true) {
                    displayBoard(board);
                    System.out.println(" Red player has won.");
                    break; }
            }
        }
    }
    //used method from the book
    //source: https://media.pearsoncmg.com/ph/esm/ecs_liang_ijp_11/cw/#solutions
    static void displayBoard(char[][] board) {
        System.out.println(" 0 1 2 3 4 5 6"); //added labelled rows
        for (int column = board.length - 1; column >= 0; column--) {
            System.out.print("|");
            for (int row = 0; row < board[column].length; row++)
                System.out.print(board[column][row] != '\u0000' ? board[column][row] + "|": " |");
            System.out.println();
        }
        System.out.println("---------------");
    }
    //used method from the book
    //modified significantly in order to add error handling
    //source: https://media.pearsoncmg.com/ph/esm/ecs_liang_ijp_11/cw/#solutions
    public static void dropADisc(char player, char[][] board) {
        boolean done = false;
        try {
            do {
                System.out.print("Drop a " + (player == 'R' ? "red" : "yellow") + " disk at column (0-6): ");
                int column = in.nextInt();

                if (column < 0 || column > 6) { //checks for correct number
                    System.out.println("Please enter a correct column.");
                    break;
                }
                if (placeADisk(board, column, player)) {
                    done = true;
                }
                else
                    System.out.println("Try a different column");
            }
            while (!done);
        } catch (InputMismatchException ex) {
            System.out.println("Please input a valid integer."); //stop letter input
        } catch (Exception ex) {
            System.out.println("Please enter a valid number.");
        }
    }
    //used method from the book
    //source: https://media.pearsoncmg.com/ph/esm/ecs_liang_ijp_11/cw/#solutions
    static boolean placeADisk(char[][] board, int column, char player) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][column] == '\u0000') {
                board[i][column] = player;
                return true; // piece added successfully
            }
        }
        return false; // column is already full
    }

    static boolean checkForWinner (char player, char[][] board) {
        /*for (int i = 0; i < board.length; i++) {
            String winner = Arrays.toString(board[i]);
            if (winner == "YYYY" || winner == "RRRR") {
                System.out.println(winner);
                System.out.println("Winner!");
                return true;
            }
        }
        return false;*/
        //the method I coded for horizontal checking didn't work

        /*////////////////////////////////////////////////////////////
        used the following code below from:
        *    Title: Connect 4
        *    Author: Jonathan Irving
        *    Date: April 16, 2015
        *    Availability: https://gist.github.com/jonathan-irvin/97537512eb65a8bbcb9a
        *///////////////////////////////////////////////////////////////

        for(int row = 0; row<board.length; row++){
            for (int col = 0;col < board[0].length - 3;col++){
                if (board[row][col] == player   &&
                        board[row][col+1] == player &&
                        board[row][col+2] == player &&
                        board[row][col+3] == player){
                    return true;
                }
            }
        }
        //check vertical
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length; col++){
                if (board[row][col] == player   &&
                        board[row+1][col] == player &&
                        board[row+2][col] == player &&
                        board[row+3][col] == player){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 3; row < board.length; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row-1][col+1] == player &&
                        board[row-2][col+2] == player &&
                        board[row-3][col+3] == player){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 0; row < board.length - 3; row++){
            for(int col = 0; col < board[0].length - 3; col++){
                if (board[row][col] == player   &&
                        board[row+1][col+1] == player &&
                        board[row+2][col+2] == player &&
                        board[row+3][col+3] == player){
                    return true;
                }
            }
        }
        return false;
    }
}
