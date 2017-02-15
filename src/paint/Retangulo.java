package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;

public class Retangulo extends Figura {

    protected Ponto centro;
    protected int rectWidth, rectHeight;
    protected boolean preenchido;

    public Retangulo(int x, int y, int r1, int r2, boolean preenchido) {

        super();

        this.centro = new Ponto(x, y);

        this.rectWidth = r1;
        this.rectHeight = r2;
        this.preenchido = preenchido;
    }

    public Retangulo(int x, int y, int r1, int r2, Color cor, boolean preenchido) {
        super(cor);

        this.centro = new Ponto(x, y);

        this.rectWidth = r1;
        this.rectHeight = r2;
        this.preenchido = preenchido;
    }

    public Retangulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());

        int r1 = Integer.parseInt(quebrador.nextToken());
        int r2 = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
                Integer.parseInt(quebrador.nextToken()), // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.preenchido = quebrador.nextToken().equalsIgnoreCase("true");

        this.centro = new Ponto(x, y, cor);
        this.rectWidth = r1;
        this.rectHeight = r2;
        this.cor = cor;
    }

    public void setCentro(int x, int y) {
        this.centro = new Ponto(x, y, this.getCor());
    }

    public void setRaio1(int r1) {
        this.rectWidth = r1;
    }

    public void setRaio2(int r2) {
        this.rectHeight = r2;
    }

    public Ponto getCentro() {
        return this.centro;
    }

    public int setRaio1() {
        return this.rectWidth;
    }

    public int setRaio2() {
        return this.rectHeight;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);

        if (preenchido == true) {
            g.fillRect(this.centro.getX() - rectWidth, this.centro.getY() - rectHeight, 2 * rectWidth, 2 * rectHeight);
        } else {
            g.drawRect(this.centro.getX() - rectWidth, this.centro.getY() - rectHeight, 2 * rectWidth, 2 * rectHeight);
        }

    }

    private boolean isPreenchido() {
        return preenchido;
    }

    public String toString() {
        return "r:"
                + this.centro.getX()
                + ":"
                + this.centro.getY()
                + ":"
                + this.rectWidth
                + ":"
                + this.rectHeight
                + ":"
                + this.getCor().getRed()
                + ":"
                + this.getCor().getGreen()
                + ":"
                + this.getCor().getBlue()
                + ":"
                + this.isPreenchido();
    }
}
