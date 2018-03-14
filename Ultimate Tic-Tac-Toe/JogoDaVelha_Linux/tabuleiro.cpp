#include "tabuleiro.h"


Tabuleiro::Tabuleiro()
{
    for(int i=0; i<9; i++){
        this->boards.append(new Board());
    }
    boards.insert(0, NULL);  //Tabuleiros vão de 1 à 9.

    this->winner = '_';
}

Board* Tabuleiro::at(int i){
    if(i!=0){
        return boards.at(i);
    }
    else{
        return NULL;
    }
}

int Tabuleiro::check_win(){
    if ( (boards.at(1)->winner == 'X' && boards.at(2)->winner == 'X' && boards.at(3)->winner == 'X')||
         (boards.at(4)->winner == 'X' && boards.at(5)->winner == 'X' && boards.at(6)->winner == 'X')||
         (boards.at(7)->winner == 'X' && boards.at(8)->winner == 'X' && boards.at(9)->winner == 'X')||
         (boards.at(1)->winner == 'X' && boards.at(4)->winner == 'X' && boards.at(7)->winner == 'X')||
         (boards.at(2)->winner == 'X' && boards.at(5)->winner == 'X' && boards.at(8)->winner == 'X')||
         (boards.at(3)->winner == 'X' && boards.at(6)->winner == 'X' && boards.at(9)->winner == 'X')||
         (boards.at(1)->winner == 'X' && boards.at(5)->winner == 'X' && boards.at(9)->winner == 'X')||
         (boards.at(3)->winner == 'X' && boards.at(5)->winner == 'X' && boards.at(7)->winner == 'X')
       ){
        this->winner = 'X';
        return 1;
    }
    else if((boards.at(1)->winner == 'O' && boards.at(2)->winner == 'O' && boards.at(3)->winner == 'O')||
            (boards.at(4)->winner == 'O' && boards.at(5)->winner == 'O' && boards.at(6)->winner == 'O')||
            (boards.at(7)->winner == 'O' && boards.at(8)->winner == 'O' && boards.at(9)->winner == 'O')||
            (boards.at(1)->winner == 'O' && boards.at(4)->winner == 'O' && boards.at(7)->winner == 'O')||
            (boards.at(2)->winner == 'O' && boards.at(5)->winner == 'O' && boards.at(8)->winner == 'O')||
            (boards.at(3)->winner == 'O' && boards.at(6)->winner == 'O' && boards.at(9)->winner == 'O')||
            (boards.at(1)->winner == 'O' && boards.at(5)->winner == 'O' && boards.at(9)->winner == 'O')||
            (boards.at(3)->winner == 'O' && boards.at(5)->winner == 'O' && boards.at(7)->winner == 'O')
            ){
            this->winner = 'O';
            return -1;
    }
    else
        return 0;
}

int Tabuleiro::check_draw(){
    if ((check_win() == 0) && (boards.at(1)->winner != '_') && (boards.at(2)->winner != '_') &&
        (boards.at(3)->winner != '_') && (boards.at(4)->winner!= '_') && (boards.at(5)->winner != '_') &&
        (boards.at(6)->winner != '_') && (boards.at(7)->winner!= '_') && (boards.at(8)->winner != '_') &&
        (boards.at(9)->winner != '_'))
    {
        return 1;
    }
    else
        return 0;
}

void Tabuleiro::print(){
    int conti=0; //0 até 4, sempre.
    int contj=0;
    int i, j;

    //    printf("\x1B[31m");  <- vermelho
    //    printf("\x1B[32m");  <- verde
    //    printf("\x1B[34m");  <- azul
    //    printf("\x1B[0m");   <- branco

    while(conti!=3){
        for(i=1; i<4; i++){
            for(j=1; j<4; j++){
                if (i == 1){
                    if(boards.at(1)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(1)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(1)->draw){
                        printf("\x1B[34m");
                    }

                }else if (i == 2){
                    if(boards.at(2)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(2)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(2)->draw){
                        printf("\x1B[34m");
                    }

                }else if (i == 3){
                    if(boards.at(3)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(3)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(3)->draw){
                        printf("\x1B[34m");
                    }

                }
                std::cout << boards.at(i)->board[j+contj] << " ";
            }
            if(j%3!=0)
                printf("\x1B[0m");
                std::cout << " | ";
        }
        contj+=3;
        std::cout << std::endl;

        printf("\x1B[0m");
        conti++;
    }
    std::cout << "--------------------------" << std::endl;

    conti=0;
    contj=0;

    while(conti!=3){
        for(i=4; i<7; i++){
            for(j=1; j<4; j++){
                if (i == 4){
                    if(boards.at(4)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(4)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(4)->draw){
                        printf("\x1B[34m");
                    }

                }else if (i == 5){
                    if(boards.at(5)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(5)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(5)->draw){
                        printf("\x1B[34m");
                    }

                }else if (i == 6){
                    if(boards.at(6)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(6)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(6)->draw){
                        printf("\x1B[34m");
                    }

                }
                std::cout << boards.at(i)->board[j+contj] << " ";
            }
            if(j%3!=0)
                printf("\x1B[0m");
                std::cout << " | ";
        }
        contj+=3;
        std::cout << std::endl;
        printf("\x1B[0m");
        conti++;
    }
    std::cout << "--------------------------" << std::endl;

    conti=0;
    contj=0;

    while(conti!=3){
        for(i=7; i<10; i++){
            for(j=1; j<4; j++){
                if (i == 7){
                    if(boards.at(7)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(7)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(7)->draw){
                        printf("\x1B[34m");
                    }

                }else if (i == 8){
                    if(boards.at(8)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(8)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(8)->draw){
                        printf("\x1B[34m");
                    }

                }else if (i == 9){
                    if(boards.at(9)->winner == 'X'){
                            printf("\x1B[32m");
                    }
                    else if (boards.at(9)->winner == 'O'){
                            printf("\x1B[31m");
                    }
                    else if (boards.at(9)->draw){
                        printf("\x1B[34m");
                    }

                }
                std::cout << boards.at(i)->board[j+contj] << " ";
            }
            if(j%3!=0)
                printf("\x1B[0m");
                std::cout << " | ";
        }
        contj+=3;
        std::cout << std::endl;
        conti++;
    }

    std::cout << std::endl;
}

