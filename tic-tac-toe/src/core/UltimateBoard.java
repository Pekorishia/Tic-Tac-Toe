package core;

import java.util.ArrayList;
import java.util.List;

public class UltimateBoard {
	private List<Board> boards;
    private char winner;
    
    public UltimateBoard(){
    	boards = new ArrayList<Board>(10);
    	
    	System.out.println(boards.size());
    	
        for(int i=0; i<9; i++){
            this.boards.add(new Board());
        }
        boards.add(0, null);  //Tabuleiros vão de 1 à 9.

        this.winner = '_';
    }

    public Board at(int i){
        if(i!=0){
            return boards.get(i);
        }
        else{
            return null;
        }
    }

    public int check_win(){
        if ( (boards.get(1).getWinner() == 'X' && boards.get(2).getWinner() == 'X' && boards.get(3).getWinner() == 'X')||
             (boards.get(4).getWinner() == 'X' && boards.get(5).getWinner() == 'X' && boards.get(6).getWinner() == 'X')||
             (boards.get(7).getWinner() == 'X' && boards.get(8).getWinner() == 'X' && boards.get(9).getWinner() == 'X')||
             (boards.get(1).getWinner() == 'X' && boards.get(4).getWinner() == 'X' && boards.get(7).getWinner() == 'X')||
             (boards.get(2).getWinner() == 'X' && boards.get(5).getWinner() == 'X' && boards.get(8).getWinner() == 'X')||
             (boards.get(3).getWinner() == 'X' && boards.get(6).getWinner() == 'X' && boards.get(9).getWinner() == 'X')||
             (boards.get(1).getWinner() == 'X' && boards.get(5).getWinner() == 'X' && boards.get(9).getWinner() == 'X')||
             (boards.get(3).getWinner() == 'X' && boards.get(5).getWinner() == 'X' && boards.get(7).getWinner() == 'X')
           ){
            this.winner = 'X';
            return 1;
        }
        else if((boards.get(1).getWinner() == 'O' && boards.get(2).getWinner() == 'O' && boards.get(3).getWinner() == 'O')||
                (boards.get(4).getWinner() == 'O' && boards.get(5).getWinner() == 'O' && boards.get(6).getWinner() == 'O')||
                (boards.get(7).getWinner() == 'O' && boards.get(8).getWinner() == 'O' && boards.get(9).getWinner() == 'O')||
                (boards.get(1).getWinner() == 'O' && boards.get(4).getWinner() == 'O' && boards.get(7).getWinner() == 'O')||
                (boards.get(2).getWinner() == 'O' && boards.get(5).getWinner() == 'O' && boards.get(8).getWinner() == 'O')||
                (boards.get(3).getWinner() == 'O' && boards.get(6).getWinner() == 'O' && boards.get(9).getWinner() == 'O')||
                (boards.get(1).getWinner() == 'O' && boards.get(5).getWinner() == 'O' && boards.get(9).getWinner() == 'O')||
                (boards.get(3).getWinner() == 'O' && boards.get(5).getWinner() == 'O' && boards.get(7).getWinner() == 'O')
                ){
                this.winner = 'O';
                return -1;
        }
        else
            return 0;
    }

    public int check_draw(){
        if ((check_win() == 0)                 && (boards.get(1).getWinner()!= '_') && (boards.get(2).getWinner() != '_') &&
            (boards.get(3).getWinner() != '_') && (boards.get(4).getWinner()!= '_') && (boards.get(5).getWinner() != '_') &&
            (boards.get(6).getWinner() != '_') && (boards.get(7).getWinner()!= '_') && (boards.get(8).getWinner() != '_') &&
            (boards.get(9).getWinner() != '_'))
        {
            return 1;
        }
        else
            return 0;
    }

    public void print(){
        int conti=0; //0 até 4, sempre.
        int contj=0;
        int i, j;

        while(conti!=3){
            for(i=1; i<4; i++){
                for(j=1; j<4; j++){
                    System.out.print(boards.get(i).getBoardCharAt(j+contj) + " ");
                }
                if(j%3!=0)
                	System.out.print(" | ");
            }
            contj+=3;
            System.out.println();
            conti++;
        }
        System.out.println( "---------------------------------");

        conti=0;
        contj=0;

        while(conti!=3){
            for(i=4; i<7; i++){
                for(j=1; j<4; j++){
                	System.out.print( boards.get(i).getBoardCharAt(j+contj) + " ");
                }
                if(j%3!=0)
                	System.out.print(" | ");
            }
            contj+=3;
            System.out.println();
            conti++;
        }
        System.out.println( "---------------------------------");

        conti=0;
        contj=0;

        while(conti!=3){
            for(i=7; i<10; i++){
                for(j=1; j<4; j++){
                	System.out.print( boards.get(i).getBoardCharAt(j+contj) + " ");
                }
                if(j%3!=0)
                	System.out.print(" | ");
            }
            contj+=3;
            System.out.println();
            conti++;
        }

        System.out.println();
    }


}
