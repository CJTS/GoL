/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.States;

/**
 *
 * @author CJ
 */
public class DeadCell implements CellState{
    public final static String state = "Dead";

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public String draw() {
        return "|     |";
    }

    @Override
    public String whichState() {
        return "Dead";
    }
    
}
