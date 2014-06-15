/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.GameTemplates;

import br.unb.cic.lp.gol.Model.BoardModel;
import br.unb.cic.lp.gol.Statistics;

/**
 *
 * @author CJ
 */
public class HighLife extends CellModel{
    public HighLife(Statistics statistics, BoardModel model){
        super(statistics, model);
    }
    
    /* verifica se uma celula deve ser mantida viva */
    @Override
    public boolean shouldKeepAlive(int i, int j) {
        return (getCell(i, j).isAlive())
                        && (numberOfNeighborhoodAliveCells(i, j) == 2 || numberOfNeighborhoodAliveCells(i, j) == 3);
    }

    /* verifica se uma celula deve (re)nascer */
    @Override
    public boolean shouldRevive(int i, int j) {
        return (!getCell(i, j).isAlive())
                        && (numberOfNeighborhoodAliveCells(i, j) == 3 || numberOfNeighborhoodAliveCells(i, j) == 6);
    }
}
