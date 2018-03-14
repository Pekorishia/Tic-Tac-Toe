#ifndef BOARD_H
#define BOARD_H

#include <iostream>

class Board{

public:
    Board();
    ~Board();
    char board[10];
    bool valid;
    char winner;
    int heuristic;
    bool draw;

    void print_board();
    int check_win();
    int check_draw();
    int check_two();
    int check_one();
    int check_heuristic();

};

#endif // BOARD_H
