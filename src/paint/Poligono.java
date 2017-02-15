package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Poligono extends Figura {

    protected ArrayList<Ponto> pontosPoligono;
    protected boolean preenchido;

    public Poligono(ArrayList<Ponto> unsPontosPoligono, boolean preenchido) {

        super();
        this.pontosPoligono = unsPontosPoligono;
        this.preenchido = preenchido;
    }

    public Poligono(ArrayList<Ponto> unsPontosPoligono, Color cor, boolean preenchido) {
        super(cor);
        this.pontosPoligono = unsPontosPoligono;
        this.preenchido = preenchido;
    }

    public Poligono(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");
        pontosPoligono = new ArrayList<Ponto>();

        quebrador.nextToken();
        this.preenchido = quebrador.nextToken().equalsIgnoreCase("true");
        this.cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
                Integer.parseInt(quebrador.nextToken()), // G
                Integer.parseInt(quebrador.nextToken())); // B

        while(quebrador.hasMoreElements())
        {
            pontosPoligono.add(new Ponto(Integer.parseInt(quebrador.nextToken()),Integer.parseInt(quebrador.nextToken())));
        }
    }

    public ArrayList<Ponto> getPontosPoligono() {
        return pontosPoligono;
    }

    public void setPontosPoligono(ArrayList<Ponto> pontosPoligono) {
        this.pontosPoligono = pontosPoligono;
    }

    public void torneSeVisivel(Graphics umGraphics) {
        Graphics g = umGraphics.create();
        g.setColor(this.cor);

        int xPontos[] = new int[pontosPoligono.size()];
        int yPontos[] = new int[pontosPoligono.size()];
        int posicao = 0;
        for (Ponto umPontoPoligono : pontosPoligono) {
            xPontos[posicao] = umPontoPoligono.getX();
            yPontos[posicao] = umPontoPoligono.getY();
            posicao++;
        }
        System.out.println("x: " + xPontos.length + " y: " + yPontos.length + " p: " + pontosPoligono.size());
        Polygon poligono = new Polygon(xPontos, yPontos, pontosPoligono.size());
        if (preenchido) {
            g.fillPolygon(poligono);
        } else {
            g.drawPolygon(poligono);
        }
        g.dispose();

    }

    private boolean isPreenchido() {
        return preenchido;
    }

    public String toString() {
        String poligono = "n:"
                + this.isPreenchido()
                + ":"
                + this.getCor().getRed()
                + ":"
                + this.getCor().getGreen()
                + ":"
                + this.getCor().getBlue()
                + ":";
        for (Ponto umPonto : this.pontosPoligono) {
            poligono += umPonto.getX() + ":" + umPonto.getY() + ":";
        }
        return poligono;

    }
}
