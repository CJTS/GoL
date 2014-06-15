package br.unb.cic.lp.gol.View;

import br.unb.cic.lp.gol.Controller.GameController;
import br.unb.cic.lp.gol.Model.GameTemplates.CellModel;
import br.unb.cic.lp.gol.Model.BoardModel;
import br.unb.cic.lp.gol.Observable;
import br.unb.cic.lp.gol.Observer;
import br.unb.cic.lp.gol.Statistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * 
 * @author rbonifacio
 */
public class GameView implements Observer{
    
    private static final String LINE = "+-----+";

    private static final int INVALID_OPTION = 0;
    private static final int MAKE_CELL_ALIVE = 1;
    private static final int NEXT_GENERATION = 2;
    private static final int HALT = 5; 
    private static final int UNDO = 3;
    private static final int REDO = 4;

    private BoardModel model;
    private GameController controller;
    private CellModel engine;
    private Statistics statistics;

    /**
     * Construtor da classe GameBoard
     */
    public GameView(GameController controller) {
            this.controller = controller;
    }

    /**
     * Atualiza o componente view (representado pela classe GameBoard),
     * possivelmente como uma resposta a uma atualiza��o do jogo.
     */
    public void update() {
            printFirstRow();
            printLine();
            for (int i = 0; i < model.getHeight(); i++) {
                    for (int j = 0; j < model.getWidth(); j++) {
                            System.out.print(engine.getCell(i, j).draw());
                    }
                    System.out.println("   " + i);
                    printLine();
            }
            printOptions();
    }

    private void printOptions() {
            Scanner s = new Scanner(System.in);
            int option;
            System.out.println("\n \n");

            do {
                    System.out.println("Select one of the options: \n \n"); 
                    System.out.println("[1] Make a cell alive");
                    System.out.println("[2] Next generation");
                    System.out.println("[3] Undo");
                    System.out.println("[4] Redo");
                    System.out.println("[5] Halt");

                    System.out.print("\n \n Option: ");

                    option = parseOption(s.nextLine());
            }while(option == 0);

            switch(option) {
                    case MAKE_CELL_ALIVE : makeCellAlive(); break;
                    case NEXT_GENERATION : nextGeneration(); break;
                    case UNDO : undo(); break;
                    case REDO : redo(); break;
                    case HALT : halt();
            }
    }

    private void makeCellAlive() {
            int i, j = 0;
            Scanner s = new Scanner(System.in);

            do {
                    System.out.print("\n Inform the row number (0 - " + model.getHeight() + "): " );

                    i = s.nextInt();

                    System.out.print("\n Inform the column number (0 - " + model.getWidth() + "): " );

                    j = s.nextInt();
            }while(!validPosition(i,j));

            controller.makeCellAlive(i, j);
    }

    private void nextGeneration() {
            controller.nextGeneration();
    }

    private void halt() {
            controller.halt();
    }

    private boolean validPosition(int i, int j) {
            System.out.println(i);
            System.out.println(j);
            return i >= 0 && i < model.getHeight() && j >= 0 && j < model.getWidth();
    }

    private int parseOption(String option) {
            if(option.equals("1")) {
                    return MAKE_CELL_ALIVE;
            }
            else if (option.equals("2")) {
                    return NEXT_GENERATION;
            }
            else if (option.equals("3")) {
                    return UNDO;
            }
            else if (option.equals("4")) {
                    return REDO;
            }
            else if (option.equals("5")) {
                    return HALT;
            }
            else return INVALID_OPTION;
    }

    /* Imprime uma linha usada como separador das linhas do tabuleiro */
    private void printLine() {
            for (int j = 0; j < model.getWidth(); j++) {
                    System.out.print(LINE);
            }
            System.out.print("\n");
    }

    /*
     * Imprime os identificadores das colunas na primeira linha do tabuleiro
     */
    private void printFirstRow() {
            System.out.println("\n \n");
            for (int j = 0; j < model.getWidth(); j++) {
                    System.out.print("   " + j + "   ");
            }
            System.out.print("\n");
    }
    
    public int rule() {
        Scanner s = new Scanner(System.in);
        int option;
        
        System.out.println("Qual estilo de jogo que voce deseja Jogar?");
        do {     
            System.out.println("[1] Normal");
            System.out.println("[2] High Life");
            System.out.println("[3] Day And Night");
            System.out.print("Option: ");

            option = s.nextInt();
        }while(option < 0 && option > 4);
        
        if(option == 1){
            System.out.println("Voce escolheu o modo normal");
        } else if(option == 2){
            System.out.println("Voce escolheu o modo high life");
        } else if(option == 3){
            System.out.println("Voce escolheu o modo day and night");
        }
        
        return option;
    }
    
    public void undo(){
        controller.undo();
    }
    
    public void redo(){
        controller.redo();
    }
    
    public void setEngine (CellModel engine){
        this.engine = engine;
    }

    @Override
    public void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void display() {
            System.out.println("=================================");
            System.out.println("           Statistics            ");
            System.out.println("=================================");
            System.out.println("Revived cells: " + statistics.getRevivedCells());
            System.out.println("Killed cells: " + statistics.getKilledCells());
            System.out.println("=================================");
    }
}
