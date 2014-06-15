/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.Memento;

import br.unb.cic.lp.gol.Model.States.CellState;

/**
 *
 * @author CJ
 */
public class CellMemento {
    protected CellState cellState;
    
    public CellMemento(CellState cellState){
        this.cellState = cellState;
    }
    
    public CellState getState(){
        return cellState;
    }
}
