package br.unb.cic.lp.gol.Model;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 * 
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).
 * 
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 * 
 * @author rbonifacio
 */
public class BoardModel{
    private int height;
    private int width;
    
    public BoardModel(int height, int width) {
            this.height = height;
            this.width = width;
    }

    /*
     * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
     */
    public boolean validPosition(int a, int b) {
            return a >= 0 && a < height && b >= 0 && b < width;
    }

    /* Metodos de acesso as propriedades height e width */

    public int getHeight() {
            return height;
    }

    public void setHeight(int height) {
            this.height = height;
    }

    public int getWidth() {
            return width;
    }

    public void setWidth(int width) {
            this.width = width;
    }
}