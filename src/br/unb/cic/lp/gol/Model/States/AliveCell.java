/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.States;

/**
 *
 * @author CJ
 */
public class AliveCell implements CellState{
    @Override
    public boolean isAlive() {
        return true;
    }
    
    @Override
    public String draw() {
        return "|  o  |";
    }

    @Override
    public String whichState() {
        return new String("Alive");
    }    
}
