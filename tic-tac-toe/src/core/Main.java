package core;

public class Main {
	//posicao de jogada do O
	static int posmin;

	/*
	 * Procura um UltimateBoard valido e retorna o numero dele
	 * param: tab: UltimateBoard atual
	 *        k:   numero do board atual
	 * return: numero valido de UltimateBoard
	 */
	static int findK(UltimateBoard tab, int k, int player){
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
	            if(tab.at(i).isValid() && tab.at(i).check_heuristic() < htemp){
	                htemp = tab.at(i).check_heuristic();
	                tempk = i;
	            }
	        }
	        else{
	            if(tab.at(i).isValid() && tab.at(i).check_heuristic() > htemp){
	                htemp = tab.at(i).check_heuristic();
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
	 *        tab:          UltimateBoard atual
	 *        profundidade: profundidade maxima, igual ao depth, mas que servira apenas como verificador
	 * return: heuristica da melhor jogada
	 */
	static int alphabeta(int boardnum, int depth, int a, int b, int player, UltimateBoard tab, int profundidade){
	    int aux;

	    //casos base
	    if(tab.at(boardnum).check_win() == 1){
	        //se esse board fechado der a vitoria para o player X
	        if (tab.check_win() == 1){
	            return 200;
	        }
	        return 100;
	    }else if (tab.at(boardnum).check_win() == -1){
	        //se esse board fechado der a vitoria para o player O
	        if (tab.check_win() == -1){
	            return -200;
	        }
	        return -100;
	    }else if (tab.at(boardnum).check_draw() == 1){
	        return 0;
	    }else if (depth == 0){
	        return tab.at(boardnum).getHeuristic();
	    }

	    if(!tab.at(boardnum).isValid()){
	        boardnum = findK(tab, boardnum, player);
	    }

	    //player 1
	    if(player == 1){
	        for(int i = 1; i <= 9; i++){
	            if(tab.at(boardnum).getBoardCharAt(i) == '_'){

	                tab.at(boardnum).setBoardCharAt(i, 'X');
	                aux = alphabeta(i, depth -1, a, b, 2, tab, profundidade);
	                tab.at(boardnum).setBoardCharAt(i, '_');

	                if(a < aux){a = aux;}
	                if(a >= b){break;}
	            }
	        }
	        return a;
	    }
	    //player 2
	    if(player == 2){
	        for(int i = 1; i <= 9; i++){
	            if(tab.at(boardnum).getBoardCharAt(i) == '_'){

	                tab.at(boardnum).setBoardCharAt(i, 'O');
	                aux = alphabeta(i, depth -1, a, b, 1, tab, profundidade);
	                tab.at(boardnum).setBoardCharAt(i, '_');

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
	    return 0; 	
	}
	
	public static void main(String[] args) {
		/*
	        1 - X
	        2 - O
	    */
	    int i, input, opt;
	    int k=0;
	
	    UltimateBoard tab = new UltimateBoard();
	    tab.print();
	
	    for(i = 1; i <= 81; i++){
	        if (i % 2 == 0){
	            System.out.println("Player O jogou no UltimateBoard " + k + " na posicao " + posmin + ". \n" );
	            input = posmin;
	        }
	        else {
	            if(k==0 || !tab.at(k).valid){
	                do{
	                	System.out.printf("Escolha o UltimateBoard em que quer jogar:\n");
	                    scanf("%d", &k);
	                    if(k>9){
	
	                        for(int i=1; i<=9; i++){
	                        	 System.out.println( tab.at(i).isValid() + " Tab " + i + ".");
	                            k=0;
	                        }
	                    }
	                }while(k==0 || !tab.at(k).isValid());
	            }
	
	            printf("Jogador X marque no UltimateBoard %d :\n", k);
	            do{
	            scanf("%d", &input);
	            }while(tab.at(k).getBoardCharAt(input) !='_');
	        }
	
	        if (i % 2 != 0)
	            tab.at(k).setBoardCharAt( i, 'X');
	        else
	            tab.at(k).setBoardCharAt( i, 'O');
	
	        //se estiver no Windows habilite esse clear
	        system("CLS");
	
	        tab.print();
	
	        int tempk = findK(tab, k, 2);
	
	        //Verificação da vitoria de um UltimateBoard INTERNO.
	        int boardchk = tab.at(k).check_win();
	        if (boardchk == 1){
	            printf("Jogador X venceu o UltimateBoard %d!\n", k);
	            tab.at(k).valid = false;
	            tab.at(k).winner = 'X';
	        }
	        else{
	            if (boardchk == -1){
	                printf("Jogador O venceu o UltimateBoard %d!\n", k);
	                tab.at(k).valid = false;
	                tab.at(k).winner = 'O';
	                k=0;
	            }
	            else{
	                tab.at(k).check_draw();
	            }
	        }
	
	        int chktable = tab.check_win();
	
	        if(chktable == 1){
	            printf("Jogador X venceu o jogo!\n");
	            break;
	        }
	        else{
	            if(chktable == -1){
	                printf("Jogador O venceu o jogo!\n");
	                break;
	            }
	            else if(tab.check_draw() == 1){
	                printf("Deu velha!\n");
	                break;
	            }
	            else{
	                if((chktable == 0) && (i != 81)){
	                    if((i%2!=0) && (input==0 || !tab.at(input).valid)){
	                        k = tempk;
	                    }
	                    else{
	                        k = input;
	                    }
	                    posmin = -1;
	                    if(i % 2 != 0){
	                        if(i < 30){
	                            opt = alphabeta(k, 14, -400, 400, 2, tab, 14);
	                        }
	                        else if(i < 40){
	                                opt = alphabeta(k, 24, -400, 400, 2, tab, 24);
	                            }
	                        else{
	                            opt = alphabeta(k, 28, -400, 400, 2, tab, 28);
	                        }
	                    }
	                }
	            }
	        }
	    }

	}

}
