/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.Memento;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author CJ
 */
public class CellCareTaker {
    protected LinkedList<CellMemento> undoStates = new LinkedList<CellMemento>();
    protected LinkedList<CellMemento> redoStates = new LinkedList<CellMemento>();
    
    public void addUndoMemento(CellMemento memento){
        undoStates.addFirst(memento);
    }
    
    public void addRedoMemento(CellMemento memento){
        redoStates.addFirst(memento);
    }
    
    public CellMemento undoState(){
        if(undoStates.size() <= 0){
            return null;
        }
        CellMemento savedState = undoStates.removeFirst();
        return savedState;
    }
    
    public CellMemento redoState(){
        if(redoStates.size() <= 0){
            return null;
        }
        CellMemento savedState = redoStates.removeFirst();
        return savedState;
    }
    
}