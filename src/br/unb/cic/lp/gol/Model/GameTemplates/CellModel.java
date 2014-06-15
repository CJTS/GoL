/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unb.cic.lp.gol.Model.GameTemplates;

import br.unb.cic.lp.gol.Model.BoardModel;
import br.unb.cic.lp.gol.Model.Memento.CellContext;
import br.unb.cic.lp.gol.Statistics;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CJ
 */
public abstract class CellModel {
    
    protected CellContext[][] cells;
    private Statistics statistics;
    private BoardModel model;
    
    public CellModel(Statistics statistics, BoardModel model){
        this.model = model;
        cells = new CellContext[model.getHeight()][model.getWidth()];
        for (int i = 0; i < model.getHeight(); i++) {
                for (int j = 0; j < model.getWidth(); j++) {
                        cells[i][j] = new CellContext();   
                        cells[i][j].registerObserver(statistics);                     
                }
        }
    }
    
    public CellContext getCell(int i, int j){
        return cells[i][j];
    }
    
    /**
     * Verifica se uma celula na posicao (i, j) estah viva.
     * 
     * @param i Posicao vertical da celula
     * @param j Posicao horizontal da celula
     * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
     * 
     * @throws InvalidParameterException caso a posicao (i,j) nao seja valida. 
     */
    public boolean isCellAlive(int i, int j) {
        return cells[i][j].isAlive();
    }
    
    public void nextGeneration() {
        List<CellContext> mustRevive = new ArrayList<CellContext>();
        List<CellContext> mustKill = new ArrayList<CellContext>();
        
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if(getCell(i, j).getState().whichState().equals("Dead")){
                    getCell(i, j).markNotAlive();
                }
                else if(getCell(i, j).getState().whichState().equals("Alive")){
                    getCell(i, j).markAlive();
                }
                else if(getCell(i, j).getState().whichState().equals("Revived")){
                    getCell(i, j).markRevived();
                }
            }
        }
    
        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if (shouldRevive(i, j)) {
                    mustRevive.add(getCell(i, j));
                } 
                else if ((!shouldKeepAlive(i, j)) && getCell(i, j).isAlive()) {
                    mustKill.add(getCell(i, j));
                }
            }
        }

        for (CellContext cell : mustRevive) {
                cell.revive();
        }

        for (CellContext cell : mustKill) {
                cell.kill();
                cell.notifyObservers();
        }
        
    }
    
    public abstract boolean shouldRevive(int i, int j);
    public abstract boolean shouldKeepAlive(int i, int j);

    /**
     * Torna a celula de posicao (i, j) viva
     * 
     * @param i posicao vertical da celula
     * @param j posicao horizontal da celula
     * 
     * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
     */
    public void makeCellAlive(int i, int j) throws InvalidParameterException {
            if(model.validPosition(i, j)) {
                    getCell(i, j).alive();
                    getCell(i, j).notifyObservers();
            }
            else {
                    new InvalidParameterException("Invalid position (" + i + ", " + j + ")" );
            }
    }

    /**
     * Retorna o numero de celulas vivas no ambiente. 
     * Esse metodo eh particularmente util para o calculo de 
     * estatisticas e para melhorar a testabilidade.
     * 
     * @return  numero de celulas vivas.
     */
    public int numberOfAliveCells() {
            int aliveCells = 0;
            for(int i = 0; i < model.getHeight(); i++) {
                    for(int j = 0; j < model.getWidth(); j++) {
                            if(isCellAlive(i,j)) {
                                    aliveCells++;
                            }
                    }
            }
            return aliveCells;
    }
    
    /*
     * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
     * de referencia identificada pelos argumentos (i,j).
     */
    protected int numberOfNeighborhoodAliveCells(int i, int j) {
            int alive = 0;
            for (int a = i - 1; a <= i + 1; a++) {
                    for (int b = j - 1; b <= j + 1; b++) {
                            if (model.validPosition(a, b)  && (!(a==i && b == j)) && getCell(a, b).isAlive()) {
                                    alive++;
                            }
                    }
            }
            return alive;
    }
    
    public void undo(){
        if(getCell(0, 0).getundoListSize() < 0){
            for(int i = 0; i < model.getWidth(); i++){
                for(int j = 0; j < model.getHeight(); j++){
                    getCell(i, j).undo();
                }
            }
        }
        else{
            System.out.println("Não há gerações passadas");
        }
    }
    
    public void redo(){
        if(getCell(0, 0).getredoListSize() < 0){
            for(int i = 0; i < model.getWidth(); i++){
                for(int j = 0; j < model.getHeight(); j++){
                    getCell(i, j).redo();
                }
            }
        }
        else{
            System.out.println("Não há gerações futuras");
        }
    }

}
