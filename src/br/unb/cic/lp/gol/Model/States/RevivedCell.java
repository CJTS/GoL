/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.States;

/**
 *
 * @author CJ
 */
public class RevivedCell implements CellState{
    public final static String state = "Revived";

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public String draw() {
        return "|  x  |";
    }

    @Override
    public String whichState() {
        return "Revived";
    }
    
}
