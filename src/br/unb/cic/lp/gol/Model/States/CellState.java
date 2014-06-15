/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.States;

import br.unb.cic.lp.gol.Observer;

/**
 *
 * @author CJ
 */
public interface CellState{
    public String whichState();
    public boolean isAlive();
    public String draw();
}
