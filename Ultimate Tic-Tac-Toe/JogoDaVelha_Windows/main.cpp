#include <QCoreApplication>
#include <algorithm>
#include <iostream>
#include <stdio.h>
#include <QtCore>
#include <string>
#include <QList>

#include "tabuleiro.h"
#include "board.h"

using namespace std;

//posicao de jogada do O
int posmin;

/*
 * Procura um tabuleiro valido e retorna o numero dele
 * param: tab: tabuleiro atual
 *        k:   numero do board atual
 * return: numero valido de tabuleiro
 */
int findK(Tabuleiro *tab, int k, int player){
    int tempk = k;
    int htemp;
    //Seta o maior/menor caso possível dependendo do jogador.
    if(player==2){
        htemp = 200;
    }
    else{
        htemp = -200;
    }

    for(int i=1; i<=9; i++){
        if(player==2){
            if(tab->at(i)->valid && tab->at(i)->check_heuristic() < htemp){
                htemp = tab->at(i)->check_heuristic();
                tempk = i;
            }
        }
        else{
            if(tab->at(i)->valid && tab->at(i)->check_heuristic() > htemp){
                htemp = tab->at(i)->check_heuristic();
                tempk = i;
            }
        }
    }
    return tempk;
}

/*
 * calcula a minimax com alphabeta pruning e seta o posmin na melhor posicao possivel para O
 * param: boardnum:     numero do board atual
 *        depth:        profundidade maxima que ira percorrer a alphabeta
 *        a:            valor do alpha
 *        b:            valor do beta
 *        player:       numero do jogador
 *        tab:          tabuleiro atual
 *        profundidade: profundidade maxima, igual ao depth, mas que servira apenas como verificador
 * return: heuristica da melhor jogada
 */
int alphabeta(int boardnum, int depth, int a, int b, int player, Tabuleiro tab, int profundidade){
    int aux;

    //casos base
    if(tab.at(boardnum)->check_win() == 1){
        //se esse board fechado der a vitoria para o player X
        if (tab.check_win() == 1){
            return 200;
        }
        return 100;
    }else if (tab.at(boardnum)->check_win() == -1){
        //se esse board fechado der a vitoria para o player O
        if (tab.check_win() == -1){
            return -200;
        }
        return -100;
    }else if (tab.at(boardnum)->check_draw() == 1){
        return 0;
    }else if (depth == 0){
        return tab.at(boardnum)->heuristic;
    }

    if(!tab.at(boardnum)->valid){
        boardnum = findK(&tab, boardnum, player);
    }

    //player 1
    if(player == 1){
        for(int i = 1; i <= 9; i++){
            if(tab.at(boardnum)->board[i] == '_'){

                tab.at(boardnum)->board[i] = 'X';
                aux = alphabeta(i, depth -1, a, b, 2, tab, profundidade);
                tab.at(boardnum)->board[i] = '_';

                if(a < aux){a = aux;}
                if(a >= b){break;}
            }
        }
        return a;
    }
    //player 2
    if(player == 2){
        for(int i = 1; i <= 9; i++){
            if(tab.at(boardnum)->board[i] == '_'){

                tab.at(boardnum)->board[i] = 'O';
                aux = alphabeta(i, depth -1, a, b, 1, tab, profundidade);
                tab.at(boardnum)->board[i] = '_';

                if(b > aux){
                    b = aux;
                    if(depth == profundidade){
                        posmin = i;
                    }
                }
                if(a >= b){break;}
            }
        }
        return b;
    }
}

int main(){
    /*
        1 - X
        2 - O
    */
    int i, input, opt;
    int k=0;

    Tabuleiro *tab = new Tabuleiro();
    tab->print();

    for(i = 1; i <= 81; i++){
        if (i % 2 == 0){
            printf("Player O jogou no tabuleiro %d na posicao %d.\n\n", k, posmin);
            input = posmin;
        }
        else {
            if(k==0 || !tab->at(k)->valid){
                do{
                    printf("Escolha o tabuleiro em que quer jogar:\n");
                    scanf("%d", &k);
                    if(k>9){

                        for(int i=1; i<=9; i++){
                            cout << tab->at(i)->valid << " Tab " << i << "." << endl;
                            k=0;
                        }
                    }
                }while(k==0 || !tab->at(k)->valid);
            }

            printf("Jogador X marque no tabuleiro %d :\n", k);
            do{
            scanf("%d", &input);
            }while(tab->at(k)->board[input]!='_');
        }

        if (i % 2 != 0)
            tab->at(k)->board[input] = 'X';
        else
            tab->at(k)->board[input] = 'O';

        //se estiver no Windows habilite esse clear
        system("CLS");

        tab->print();

        int tempk = findK(tab, k, 2);

        //Verificação da vitoria de um TABULEIRO INTERNO.
        int boardchk = tab->at(k)->check_win();
        if (boardchk == 1){
            printf("Jogador X venceu o tabuleiro %d!\n", k);
            tab->at(k)->valid = false;
            tab->at(k)->winner = 'X';
        }
        else{
            if (boardchk == -1){
                printf("Jogador O venceu o tabuleiro %d!\n", k);
                tab->at(k)->valid = false;
                tab->at(k)->winner = 'O';
                k=0;
            }
            else{
                tab->at(k)->check_draw();
            }
        }

        int chktable = tab->check_win();

        if(chktable == 1){
            printf("Jogador X venceu o jogo!\n");
            break;
        }
        else{
            if(chktable == -1){
                printf("Jogador O venceu o jogo!\n");
                break;
            }
            else if(tab->check_draw() == 1){
                printf("Deu velha!\n");
                break;
            }
            else{
                if((chktable == 0) && (i != 81)){
                    if((i%2!=0) && (input==0 || !tab->at(input)->valid)){
                        k = tempk;
                    }
                    else{
                        k = input;
                    }
                    posmin = -1;
                    if(i % 2 != 0){
                        if(i < 30){
                            opt = alphabeta(k, 14, -400, 400, 2, *tab, 14);
                        }
                        else if(i < 40){
                                opt = alphabeta(k, 24, -400, 400, 2, *tab, 24);
                            }
                        else{
                            opt = alphabeta(k, 28, -400, 400, 2, *tab, 28);
                        }
                    }
                }
            }
        }
    }

    return 0;
}
