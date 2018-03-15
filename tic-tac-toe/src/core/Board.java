package core;

public class Board {
    private char[] board;
    private boolean valid;
    private char winner;
    private int heuristic;
   	private boolean draw;
    
	public Board () {
		board = new char[10];
		
		for(int i = 1; i <= 9; i++)
	        board[i] = '_';

	    this.setWinner('_');
	    this.setValid(true);
	    this.heuristic = 0;
	    this.setDraw(false);
	}
    
	public void print_board(){
	    int i;
	    for (i = 1; i <= 9; i++){
	        System.out.print(board[i]);
	        if (i % 3 == 0)
	        	System.out.println();
	    }
	    System.out.println();
	}
	
	public int check_heuristic(){

	    //zera para recalcular
	    this.heuristic = 0;

	    // a heuristica eh com base no O (pc)
	    //por isso que quando check_win for -1 a heuristica eh -100
	    int chkW = check_win();

	    if(chkW == 1){
	       this.heuristic = 100;
	        return 100;
	    }
	    else if (chkW == -1){
	        this.heuristic = -100;
	        return -100;
	    }

	    //se ninguém ganhou no tabuleiro, soma todas as heuristicas das linhas
	    int chkT = check_two();
	    int chkO = check_one();

	    this.heuristic = -(chkT + chkO);

	    return this.heuristic;
	}

	
	public int check_win(){
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
	
	public int check_draw(){
	    if ((check_win() == 0) && (board[1] != '_') && (board[2] != '_') &&
	        (board[3] != '_') && (board[4] != '_') && (board[5] != '_') &&
	        (board[6] != '_') && (board[7] != '_') && (board[8] != '_') &&
	        (board[9] != '_'))
	    {
	        this.setValid(false);
	        this.setDraw(true);
	        return 1;
	    }
	    else{
	        this.setValid(true);
	        this.setDraw(false);
	        return 0;
	    }
	}
	
	public int check_two(){
	    int sum = 0;
	    //estados ruins para O
	    if ((board[1] == '_' && board[2] == 'X' && board[3] == 'X') ||
	        (board[1] == 'X' && board[2] == '_' && board[3] == 'X') ||
	        (board[1] == 'X' && board[2] == 'X' && board[3] == '_')){
	            sum += -10;
	    }

	    if ((board[4] == '_' && board[5] == 'X' && board[6] == 'X') ||
	        (board[4] == 'X' && board[5] == '_' && board[6] == 'X') ||
	        (board[4] == 'X' && board[5] == 'X' && board[6] == '_')){
	            sum += -10;
	    }

	    if ((board[7] == '_' && board[8] == 'X' && board[9] == 'X') ||
	        (board[7] == 'X' && board[8] == '_' && board[9] == 'X') ||
	        (board[7] == 'X' && board[8] == 'X' && board[9] == '_')){
	            sum += -10;
	    }

	    if ((board[1] == '_' && board[4] == 'X' && board[7] == 'X') ||
	        (board[1] == 'X' && board[4] == '_' && board[7] == 'X') ||
	        (board[1] == 'X' && board[4] == 'X' && board[7] == '_')){
	            sum += -10;
	    }

	    if ((board[2] == '_' && board[5] == 'X' && board[8] == 'X') ||
	        (board[2] == 'X' && board[5] == '_' && board[8] == 'X') ||
	        (board[2] == 'X' && board[5] == 'X' && board[8] == '_')){
	            sum += -10;
	    }

	    if ((board[3] == '_' && board[6] == 'X' && board[9] == 'X') ||
	        (board[3] == 'X' && board[6] == '_' && board[9] == 'X') ||
	        (board[3] == 'X' && board[6] == 'X' && board[9] == '_')){
	            sum += -10;
	    }

	    if ((board[1] == '_' && board[5] == 'X' && board[9] == 'X') ||
	        (board[1] == 'X' && board[5] == '_' && board[9] == 'X') ||
	        (board[1] == 'X' && board[5] == 'X' && board[9] == '_')){
	            sum += -10;
	    }

	    if ((board[3] == '_' && board[5] == 'X' && board[7] == 'X') ||
	        (board[3] == 'X' && board[5] == '_' && board[7] == 'X') ||
	        (board[3] == 'X' && board[5] == 'X' && board[7] == '_')){
	            sum += -10;
	    }

	    //estados bons para o O
	    if((board[1] == '_' && board[2] == 'O' && board[3] == 'O') ||
	       (board[1] == 'O' && board[2] == '_' && board[3] == 'O') ||
	       (board[1] == 'O' && board[2] == 'O' && board[3] == '_')){
	            sum += 10;
	    }

	    if ((board[4] == '_' && board[5] == 'O' && board[6] == 'O') ||
	        (board[4] == 'O' && board[5] == '_' && board[6] == 'O') ||
	        (board[4] == 'O' && board[5] == 'O' && board[6] == '_')){
	            sum += 10;
	    }

	    if ((board[7] == '_' && board[8] == 'O' && board[9] == 'O') ||
	        (board[7] == 'O' && board[8] == '_' && board[9] == 'O') ||
	        (board[7] == 'O' && board[8] == 'O' && board[9] == '_')){
	            sum += 10;
	    }

	    if ((board[1] == '_' && board[4] == 'O' && board[7] == 'O') ||
	        (board[1] == 'O' && board[4] == '_' && board[7] == 'O') ||
	        (board[1] == 'O' && board[4] == 'O' && board[7] == '_')){
	            sum += 10;
	    }

	    if ((board[2] == '_' && board[5] == 'O' && board[8] == 'O') ||
	        (board[2] == 'O' && board[5] == '_' && board[8] == 'O') ||
	        (board[2] == 'O' && board[5] == 'O' && board[8] == '_')){
	            sum += 10;
	    }

	    if ((board[3] == '_' && board[6] == 'O' && board[9] == 'O') ||
	        (board[3] == 'O' && board[6] == '_' && board[9] == 'O') ||
	        (board[3] == 'O' && board[6] == 'O' && board[9] == '_')){
	            sum += 10;
	    }

	    if ((board[1] == '_' && board[5] == 'O' && board[9] == 'O') ||
	        (board[1] == 'O' && board[5] == '_' && board[9] == 'O') ||
	        (board[1] == 'O' && board[5] == 'O' && board[9] == '_')){
	            sum += 10;
	    }

	   if ((board[3] == '_' && board[5] == 'O' && board[7] == 'O') ||
	       (board[3] == 'O' && board[5] == '_' && board[7] == 'O') ||
	       (board[3] == 'O' && board[5] == 'O' && board[7] == '_')){
	           sum += 10;
	    }

	   return sum;
	}
	
	public int check_one(){
	    int sum = 0;

	    //estados ruins para O
	    if ((board[1] == '_' && board[2] == '_' && board[3] == 'X') ||
	        (board[1] == 'X' && board[2] == '_' && board[3] == '_') ||
	        (board[1] == '_' && board[2] == 'X' && board[3] == '_')){
	            sum += -1;
	    }

	    if ((board[4] == '_' && board[5] == '_' && board[6] == 'X') ||
	        (board[4] == 'X' && board[5] == '_' && board[6] == '_') ||
	        (board[4] == '_' && board[5] == 'X' && board[6] == '_')){
	            sum += -1;
	    }

	    if ((board[7] == '_' && board[8] == '_' && board[9] == 'X') ||
	        (board[7] == 'X' && board[8] == '_' && board[9] == '_') ||
	        (board[7] == '_' && board[8] == 'X' && board[9] == '_')){
	            sum += -1;
	    }

	    if ((board[1] == '_' && board[4] == '_' && board[7] == 'X') ||
	        (board[1] == 'X' && board[4] == '_' && board[7] == '_') ||
	        (board[1] == '_' && board[4] == 'X' && board[7] == '_')){
	            sum += -1;
	    }

	    if ((board[2] == '_' && board[5] == '_' && board[8] == 'X') ||
	        (board[2] == 'X' && board[5] == '_' && board[8] == '_') ||
	        (board[2] == '_' && board[5] == 'X' && board[8] == '_')){
	            sum += -1;
	    }

	    if ((board[3] == '_' && board[6] == '_' && board[9] == 'X') ||
	        (board[3] == 'X' && board[6] == '_' && board[9] == '_') ||
	        (board[3] == '_' && board[6] == 'X' && board[9] == '_')){
	            sum += -1;
	    }

	    if ((board[1] == '_' && board[5] == '_' && board[9] == 'X') ||
	        (board[1] == 'X' && board[5] == '_' && board[9] == '_') ||
	        (board[1] == '_' && board[5] == 'X' && board[9] == '_')){
	            sum += -1;
	    }

	    if ((board[3] == '_' && board[5] == '_' && board[7] == 'X') ||
	        (board[3] == 'X' && board[5] == '_' && board[7] == '_') ||
	        (board[3] == '_' && board[5] == 'X' && board[7] == '_')){
	            sum += -1;
	    }

	    //estados bons para o O
	    if((board[1] == '_' && board[2] == '_' && board[3] == 'O') ||
	       (board[1] == 'O' && board[2] == '_' && board[3] == '_') ||
	       (board[1] == '_' && board[2] == 'O' && board[3] == '_')){
	            sum += 1;
	    }

	    if ((board[4] == '_' && board[5] == '_' && board[6] == 'O') ||
	        (board[4] == 'O' && board[5] == '_' && board[6] == '_') ||
	        (board[4] == '_' && board[5] == 'O' && board[6] == '_')){
	            sum += 1;
	    }

	    if ((board[7] == '_' && board[8] == '_' && board[9] == 'O') ||
	        (board[7] == 'O' && board[8] == '_' && board[9] == '_') ||
	        (board[7] == '_' && board[8] == 'O' && board[9] == '_')){
	            sum += 1;
	    }

	    if ((board[1] == '_' && board[4] == '_' && board[7] == 'O') ||
	        (board[1] == 'O' && board[4] == '_' && board[7] == '_') ||
	        (board[1] == '_' && board[4] == 'O' && board[7] == '_')){
	            sum += 1;
	    }

	    if ((board[2] == '_' && board[5] == '_' && board[8] == 'O') ||
	        (board[2] == 'O' && board[5] == '_' && board[8] == '_') ||
	        (board[2] == '_' && board[5] == 'O' && board[8] == '_')){
	            sum += 1;
	    }

	    if ((board[3] == '_' && board[6] == '_' && board[9] == 'O') ||
	        (board[3] == 'O' && board[6] == '_' && board[9] == '_') ||
	        (board[3] == '_' && board[6] == 'O' && board[9] == '_')){
	            sum += 1;
	    }

	    if ((board[1] == '_' && board[5] == '_' && board[9] == 'O') ||
	        (board[1] == 'O' && board[5] == '_' && board[9] == '_') ||
	        (board[1] == '_' && board[5] == 'O' && board[9] == '_')){
	            sum += 1;
	    }

	   if ((board[3] == '_' && board[5] == '_' && board[7] == 'O') ||
	       (board[3] == 'O' && board[5] == '_' && board[7] == '_') ||
	       (board[3] == '_' && board[5] == 'O' && board[7] == '_')){
	           sum += 1;
	    }
	   return sum;
	}

	public char getWinner() {
		return winner;
	}

	public void setWinner(char winner) {
		this.winner = winner;
	}

	public char getBoardCharAt(int i) {
		return board[i];
	}

	public void setBoardCharAt(int i, char a) {
		board[i] = a;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}
	
	 public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
}
