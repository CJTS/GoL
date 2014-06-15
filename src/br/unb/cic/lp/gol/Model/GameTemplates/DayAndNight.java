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
public class DayAndNight extends CellModel{
    public DayAndNight(Statistics statistics, BoardModel model){
        super(statistics, model);
    }
    /* verifica se uma celula deve ser mantida viva */
    @Override
    public boolean shouldKeepAlive(int i, int j) {
        return (getCell(i, j).isAlive())
                        && (numberOfNeighborhoodAliveCells(i, j) == 3 || numberOfNeighborhoodAliveCells(i, j) == 4 || numberOfNeighborhoodAliveCells(i, j) == 6 || numberOfNeighborhoodAliveCells(i, j) == 7 || numberOfNeighborhoodAliveCells(i, j) == 8);
    }

    /* verifica se uma celula deve (re)nascer */
    @Override
    public boolean shouldRevive(int i, int j) {
        return (!getCell(i, j).isAlive())
                        && (numberOfNeighborhoodAliveCells(i, j) == 3 || numberOfNeighborhoodAliveCells(i, j) == 6  || numberOfNeighborhoodAliveCells(i, j) == 7 || numberOfNeighborhoodAliveCells(i, j) == 8);
    }
}
