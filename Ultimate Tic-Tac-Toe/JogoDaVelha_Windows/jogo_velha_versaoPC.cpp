#include <stdio.h>
#include <algorithm>
#include <string>
#include <iostream>
using namespace std;

#define inf 1<<20
int posmax, posmin;
char board[15];

void print_board(){
    int i;
    for (i = 1; i <= 9; i++){
        printf("%c ",board[i]);
        if (i % 3 == 0)
            printf("\n");
    }
    printf("\n");
}

int check_win(char board[]){
    if ((board[1] == 'X' && board[2] == 'X' && board[3] == 'X') ||
        (board[4] == 'X' && board[5] == 'X' && board[6] == 'X') ||
        (board[7] == 'X' && board[8] == 'X' && board[9] == 'X') ||
        (board[1] == 'X' && board[4] == 'X' && board[7] == 'X') ||
        (board[2] == 'X' && board[5] == 'X' && board[8] == 'X') ||
        (board[3] == 'X' && board[6] == 'X' && board[9] == 'X') ||
        (board[1] == 'X' && board[5] == 'X' && board[9] == 'X') ||
        (board[3] == 'X' && board[5] == 'X' && board[7] == 'X'))
    {
        return 1;
    }
    else if((board[1] == 'O' && board[2] == 'O' && board[3] == 'O') ||
            (board[4] == 'O' && board[5] == 'O' && board[6] == 'O') ||
            (board[7] == 'O' && board[8] == 'O' && board[9] == 'O') ||
            (board[1] == 'O' && board[4] == 'O' && board[7] == 'O') ||
            (board[2] == 'O' && board[5] == 'O' && board[8] == 'O') ||
            (board[3] == 'O' && board[6] == 'O' && board[9] == 'O') ||
            (board[1] == 'O' && board[5] == 'O' && board[9] == 'O') ||
            (board[3] == 'O' && board[5] == 'O' && board[7] == 'O'))
    {
        return -1;
    }
    else return 0;
}

int check_draw(char board[]){
    if ((check_win(board) == 0) && (board[1] != '_') && (board[2] != '_') &&
        (board[3] != '_') && (board[4] != '_') && (board[5] != '_') &&
        (board[6] != '_') && (board[7] != '_') && (board[8] != '_') &&
        (board[9] != '_'))
    {
        return 1;
    }
    else return 0;
}

int minimax(int player, char board[], int n){
    int i, res, j;

    int max = -inf;
    int min = inf;

    int chk = check_win(board);
    if (chk == 1)
        return 1;
    else if (chk == (-1))
        return -1;
    else if (chk = check_draw(board))
        return 0;

    for (i = 1; i <= 9; i++){
        if(board[i] == '_'){
            if(player == 2){
                board[i] = 'O';
                res = minimax(1, board, n + 1);

                board[i] = '_';
                if(res < min){
                    min = res;
                    if (n == 0)
                        posmin = i;
                }
            }
            else if (player == 1){
                board[i] = 'X';
                res = minimax(2, board, n + 1);
                board[i] = '_';
                if (res > max){
                    max = res;
                    if (n == 0)
                        posmax = i;
                }
            }
        }
    }

    if (player == 1)
        return max;
    else return min;
}


// 1 is X, 2 is O
int main(){
    int i, j, input, opt;

    for(i = 1; i <= 9; i++)
        board[i] = '_';

    printf("Board:\n");
    print_board();

    for(i = 1; i <= 9; i++){
        if (i % 2 == 0){
            printf("Player O give input:\n");
            input = posmin;
        }
        else {
            printf("Player X give input:\n");
            move:
            scanf("%d", &input);
        }


        if(board[input]=='_'){
            if (i % 2 != 0)
                board[input] = 'X';
            else
                board[posmin] = 'O';
        }
        else{
            goto move;
        }
        printf("Board:\n");
        print_board();

        int chk = check_win(board);
        if (chk == 1){
            printf("Player X wins!\n");
            break;
        }
        else if (chk == -1){
            printf("Player O wins!\n");
            break;
        }
        else if ((chk == 0) && (i != 9)){
            posmax = -1;
            posmin = -1;
            if(i % 2 == 0){
                opt = minimax(1, board, 0);
                //printf("Optimal move for player X is %d\n", posmax);
            }
            else {
                opt = minimax(2, board, 0);
                //printf("Optimal move for player O is %d\n", posmin);
            }
        }
        else
            printf("The game is tied!\n");
    }
    return 0;
}
