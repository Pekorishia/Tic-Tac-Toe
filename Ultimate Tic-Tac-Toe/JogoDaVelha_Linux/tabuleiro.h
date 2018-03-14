#ifndef TABULEIRO_H
#define TABULEIRO_H

#include "board.h"
#include <QList>
#include <QtCore>

class Tabuleiro
{
public:
    Tabuleiro();
    QList<Board*> boards;    
    char winner;

    Board* at(int i);
    void print();
    int check_win();
    int check_draw();
};

#endif // TABULEIRO_H
