/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.Memento;

import br.unb.cic.lp.gol.Model.States.AliveCell;
import br.unb.cic.lp.gol.Model.States.CellState;
import br.unb.cic.lp.gol.Model.States.DeadCell;
import br.unb.cic.lp.gol.Model.States.RevivedCell;
import br.unb.cic.lp.gol.Observable;
import br.unb.cic.lp.gol.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CJ
 */
public class CellContext implements CellState, Observable{
    private List<Observer> observers = new ArrayList<Observer>();
    
    CellState cellState;
    CellCareTaker careTaker;
    
    public CellContext(){
        careTaker = new CellCareTaker();
        cellState = new DeadCell();
        careTaker.addUndoMemento(new CellMemento(new DeadCell()));
    }
    
    public void setState(CellState state){
        this.cellState = state;
    }

    public CellState getState(){
        return this.cellState;
    }

    public CellCareTaker getCareTaker(){
        return this.careTaker;
    }
    
    public void undo(){
        careTaker.addRedoMemento(new CellMemento(cellState));
        CellMemento lastState = careTaker.undoState();
        if(lastState != null){
            cellState = lastState.getState();
        }
    }
    
    public void redo(){
        careTaker.addUndoMemento(new CellMemento(cellState));
        CellMemento lastState = careTaker.redoState();
        if(lastState != null){
            cellState = lastState.getState();
        }
    }
    
    @Override
    public boolean isAlive(){
        return this.cellState.isAlive();
    }

    @Override
    public String whichState() {
        return this.cellState.whichState();
    }

    public void kill() {
        cellState = new DeadCell();
    }

    public void revive() {
        cellState = new RevivedCell();
    }
    
    public void alive(){
        cellState = new AliveCell();
    }
    
    public void markAlive(){
        careTaker.addUndoMemento(new CellMemento(new AliveCell()));
    }
    
    public void markNotAlive(){
        careTaker.addUndoMemento(new CellMemento(new DeadCell()));
    }
    
    public void markRevived(){
        careTaker.addUndoMemento(new CellMemento(new RevivedCell()));
    }
    
    public int getundoListSize(){
        return careTaker.undoStates.size();
    }
    
    public int getredoListSize(){
        return careTaker.redoStates.size();
    }

    @Override
    public String draw() {
        return this.cellState.draw();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer ob : observers){
            ob.update(this.getState().whichState());
        }
    }
}