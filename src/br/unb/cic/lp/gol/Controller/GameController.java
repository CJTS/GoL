package br.unb.cic.lp.gol.Controller;

import br.unb.cic.lp.gol.Model.GameTemplates.CellModel;
import br.unb.cic.lp.gol.Model.GameTemplates.DayAndNight;
import br.unb.cic.lp.gol.Model.GameTemplates.HighLife;
import br.unb.cic.lp.gol.Model.GameTemplates.Normal;
import br.unb.cic.lp.gol.Model.BoardModel;
import br.unb.cic.lp.gol.Statistics;
import br.unb.cic.lp.gol.View.GameView;
import java.security.InvalidParameterException;

/**
 * Classe que atua como um controlador do 
 * padr�o MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController{

    private GameView board;
    private Statistics statistics;
    private CellModel engine;
    private BoardModel model;

    public CellModel getEngine() {
            return engine;
    }

    public void setEngine(CellModel engine) {
            this.engine = engine;
    }

    public GameView getBoard() {
            return board;
    }

    public void setBoard(GameView board) {
            this.board = board;
    }

    public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
    }
    
    public void setModel(BoardModel model) {
            this.model = model;
    }

    public void start() {
        board.update();
    }

    public void halt() {
            //oops, nao muito legal fazer sysout na classe Controller
            System.out.println("\n \n");
            board.display();
            System.exit(0);
    }

    public void makeCellAlive(int i, int j) {
            try {
                    engine.makeCellAlive(i, j);
                    board.update();
            }
            catch(InvalidParameterException e) {
                    System.out.println(e.getMessage());
            }
    }
    
    public void undo(){
        engine.undo();
        board.update();
    }
    
    public void redo(){
        engine.redo();
        board.update();
    }

    public void nextGeneration() {
        engine.nextGeneration();
        board.update();
    }
    
    public CellModel rule(){
        int option = board.rule();
        
        if(option == 1){
            return new Normal(statistics, model);
        } else if(option == 2){
            return new HighLife(statistics, model);
        } else if(option == 3){
            return new DayAndNight(statistics, model);   
        }
        
        return null;
    }
}
