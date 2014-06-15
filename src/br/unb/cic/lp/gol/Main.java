package br.unb.cic.lp.gol;

import br.unb.cic.lp.gol.Model.BoardModel;
import br.unb.cic.lp.gol.Controller.GameController;
import br.unb.cic.lp.gol.Model.GameTemplates.CellModel;
import br.unb.cic.lp.gol.View.GameView;

public class Main{
    public static void main(String args[]) {
        
        GameController controller = new GameController();
        
        BoardModel model = new BoardModel(10,10);
        
        GameView board = new GameView(controller);        
        controller.setBoard(board);
        
        Statistics statistics = new Statistics();        
        controller.setStatistics(statistics);
        
        CellModel engine = controller.rule();
        
        controller.setEngine(engine);
        
        board.setEngine(engine);
        
        controller.start();
    }
}