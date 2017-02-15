package paint;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Texto extends Figura {

    protected Ponto ponto;
    protected String texto;
    protected Font fonte;
    protected boolean fonteSublinhada;

    public Texto(int x1, int y1, Font umaFonte, boolean isFonteSublinhada, String texto) {
        super();
        this.texto = texto;
        this.ponto = new Ponto(x1, y1);
        this.fonte = umaFonte;
        this.fonteSublinhada = isFonteSublinhada;

        //this.texto = new Texto();
    }

    public Texto(int x1, int y1, Font umaFonte, boolean isFonteSublinhada, String texto, Color cor) {
        super(cor);
        this.texto = texto;
        this.ponto = new Ponto(x1, y1, cor);
        this.fonte = umaFonte;
        this.fonteSublinhada = isFonteSublinhada;
        //this.texto = new Texto();
    }

    public Texto(String s) {
        StringTokenizer quebrador = new StringTokenizer(s, ":");
        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();


        quebrador.nextToken();

        int x1 = Integer.parseInt(quebrador.nextToken());
        int y1 = Integer.parseInt(quebrador.nextToken());
        int tamanho = Integer.parseInt(quebrador.nextToken());
        int bold = Font.PLAIN;
        int italic = Font.PLAIN;
        String nomeDaFonte = quebrador.nextToken();
        if (quebrador.nextToken().equalsIgnoreCase("true")) {
            bold = Font.BOLD;
        }
        if (quebrador.nextToken().equalsIgnoreCase("true")) {
            italic = Font.ITALIC;
        }
        if (quebrador.nextToken().equalsIgnoreCase("true")) {
            fonteSublinhada = true;
        } else {
            fonteSublinhada = false;
        }
        if (fonteSublinhada) {
            fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        }

        this.ponto = new Ponto(x1, y1);
        this.fonte = new Font(nomeDaFonte, bold + italic, tamanho).deriveFont(fontAttributes);

        this.cor = new Color(Integer.parseInt(quebrador.nextToken()), // R
                Integer.parseInt(quebrador.nextToken()), // G
                Integer.parseInt(quebrador.nextToken())); // B
        this.texto = quebrador.nextToken();


    }

    public void setP1(int x, int y) {
        this.ponto = new Ponto(x, y, this.getCor());
    }

    public Ponto getP1() {
        return this.ponto;
    }

    public void torneSeVisivel(Graphics g) {
        // Font font = new Font("Courier", Font.BOLD, 30);
        g.setFont(this.fonte);
        g.setColor(this.cor);
        g.drawString(this.texto, this.ponto.getX(), this.ponto.getY());
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String toString() {
        return "t:"
                + this.ponto.getX()
                + ":"
                + this.ponto.getY()
                + ":"
                + (int)Float.parseFloat(this.fonte.getAttributes().get(TextAttribute.SIZE).toString())
                + ":"
                + this.fonte.getFontName()
                + ":"
                + this.fonte.isBold()
                + ":"
                + this.fonte.isItalic()
                + ":"
                + this.fonteSublinhada
                + ":"
                + this.getCor().getRed()
                + ":"
                + this.getCor().getGreen()
                + ":"
                + this.getCor().getBlue()
                + ":"
                + this.getTexto()
                + ":";
    }
}
