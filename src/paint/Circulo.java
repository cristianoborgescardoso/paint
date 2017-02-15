package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.util.StringTokenizer;

public class Circulo extends Figura {

    protected Ponto centro;
    protected int raio;
    protected boolean preenchido;

    public Circulo(int x, int y, int r, boolean preenchido) {
        super();

        this.centro = new Ponto(x, y);
        this.raio = r;
        this.preenchido = preenchido;
    }

    public Circulo(int x, int y, int r, Color cor, boolean preenchido) {
        super(cor);

        this.centro = new Ponto(x, y);
        this.raio = r;
        this.preenchido = preenchido;
    }

    public Circulo(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");

        quebrador.nextToken();

        int x = Integer.parseInt(quebrador.nextToken());
        int y = Integer.parseInt(quebrador.nextToken());

        int r = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
                Integer.parseInt(quebrador.nextToken()), // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.preenchido = quebrador.nextToken().equalsIgnoreCase("true");

        this.centro = new Ponto(x, y, cor);
        this.raio = r;
        this.cor = cor;
    }

    public void setCentro(int x, int y) {
        this.centro = new Ponto(x, y, this.getCor());
    }

    public void setRaio(int r) {
        this.raio = r;
    }

    public Ponto getCentro() {
        return this.centro;
    }

    public int setRaio() {
        return this.raio;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(this.cor);
        if (preenchido == true) {
            g.fillOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);
        } else {
            g.drawOval(this.centro.getX() - raio, this.centro.getY() - raio, 2 * raio, 2 * raio);
        }

    }
    public boolean isPreenchido() {
        return preenchido;
    }

    public void setPreenchido(boolean preenchido) {
        this.preenchido = preenchido;
    }

    public String toString() {
        return "c:"
                + this.centro.getX()
                + ":"
                + this.centro.getY()
                + ":"
                + this.raio
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
