package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Janela extends JFrame {

    private JButton btnPonto = new JButton("Ponto", new ImageIcon(getClass().getResource("/paint/figuras/ponto.jpg"))),
            btnLinha = new JButton("Linha", new ImageIcon(getClass().getResource("/paint/figuras/linha.jpg"))),
            btnCirculo = new JButton("Circulo", new ImageIcon(getClass().getResource("/paint/figuras/circulo.jpg"))),
            btnElipse = new JButton("Elipse", new ImageIcon(getClass().getResource("/paint/figuras/elipse.jpg"))),
            btnQuadrado = new JButton("Quadrado", new ImageIcon(getClass().getResource("/paint/figuras/quadrado.jpg"))),
            btnRetangulo = new JButton("Retangulo", new ImageIcon(getClass().getResource("/paint/figuras/retangulo.jpg"))),
            btnCores = new JButton("Cores", new ImageIcon(getClass().getResource("/paint/figuras/cores.jpg"))),
            btnTexto = new JButton("Texto", new ImageIcon(getClass().getResource("/paint/figuras/texto.png"))),
            btnAbrir = new JButton("Abrir", new ImageIcon(getClass().getResource("/paint/figuras/abrir.jpg"))),
            btnSalvar = new JButton("Salvar", new ImageIcon(getClass().getResource("/paint/figuras/salvar.jpg"))),
            btnApagar = new JButton("Apagar", new ImageIcon(getClass().getResource("/paint/figuras/apagar.jpg"))),
            btnSair = new JButton("Sair", new ImageIcon(getClass().getResource("/paint/figuras/sair.jpg"))),
            btnPoligono = new JButton("Poligono", new ImageIcon(getClass().getResource("/paint/figuras/poligono.jpg")));
    private JCheckBox chekPreenchido = new JCheckBox();
    private JCheckBox chekNegrito = new JCheckBox();
    private JCheckBox chekItalico = new JCheckBox();
    private JCheckBox chekSublinhando = new JCheckBox();
    private JComboBox cbFonte = new JComboBox();
    private JComboBox cbTamanho = new JComboBox();
    private MeuJPanel pnlDesenho = new MeuJPanel();
    private JLabel statusBar1 = new JLabel("Mensagem:"),
            statusBar2 = new JLabel("Coordenada:"),
            lbCor = new JLabel(""),
            lbFonte = new JLabel("Fonte"),
            lbTamanho = new JLabel("Tamanho");
//            corTexto = new JLabel("Cor");
    boolean esperaPonto = false,
            esperaInicioReta = false,
            esperaFimReta = false;
    private Color corAtual = Color.black;
    private Ponto p1;
    private Ponto pe1;
    private Ponto pe2;
    private int elipseR1 = 20,
            elipseR2 = 10;
    private Ponto elipseCentro;
    private Ponto pc1;
    private Ponto pc2;
    private Ponto circuloCentro;
    private int circuloR1 = 0,
            circuloR2 = 0,
            circuloR3 = 0;
    private Ponto pq1;
    private Ponto pq2;
    private int quadR1 = 0,
            quadR2 = 0,
            quadR3 = 0;
    private Ponto quadCentro;
    private Ponto po1;
    private Ponto pr1;
    private Ponto pr2;
    private int retangR1 = 20,
            retangR2 = 10;
    private Ponto retangCentro;
    private final String elipse = "elipse";
    private final String ponto = "ponto";
    private final String linha = "linha";
    private final String circulo = "circulo";
    private final String quadrado = "quadrado";
    private final String retangulo = "retangulo";
    private final String poligono = "poligono";
    private String desenhoAtual = "";
    private String texto = "texto";
    private JTextField txt = new JTextField();
    private ArrayList<Figura> figuras = new ArrayList<Figura>();
    private ArrayList<String> textoDoArquivo;
    private ArrayList<Ponto> pontosPoligono = new ArrayList<Ponto>();

    public Janela() {
        super("Editor Gráfico");

        btnPonto.addActionListener(new DesenhoDePonto());
        btnLinha.addActionListener(new DesenhoDeReta());
        btnElipse.addActionListener(new DesenhoDeElipse());
        btnSair.addActionListener(new BtnSair());
        btnCirculo.addActionListener(new DesenhoDeCirculo());
        btnQuadrado.addActionListener(new DesenhoDeQuadrado());
        btnRetangulo.addActionListener(new DesenhoDeRetangulo());
        btnApagar.addActionListener(new BtnLimpar());
        btnCores.addActionListener(new BtnCor());
        btnAbrir.addActionListener(new AbrirArquivo());
        btnTexto.addActionListener(new DesenharTexto());
        btnSalvar.addActionListener(new SalvarDesenhos());
        btnPoligono.addActionListener(new DesenhoDePoligono());

        chekPreenchido.setText("Preenchido");
        chekItalico.setText("Italico");
        chekNegrito.setText("Negrito");
        chekSublinhando.setText("Sublinhado");


        JPanel pnlBotoes = new JPanel();
        GridLayout flwBotoes = new GridLayout(0, 7);
//        pnlBotoes.setLayout(flwBotoes);
        pnlBotoes.setLayout(flwBotoes);

        pnlBotoes.add(btnAbrir);
        pnlBotoes.add(btnSalvar);
        pnlBotoes.add(btnApagar);
        pnlBotoes.add(btnSair);
        pnlBotoes.add(btnPonto);
        pnlBotoes.add(btnLinha);
        pnlBotoes.add(btnCirculo);
        pnlBotoes.add(btnElipse);
        pnlBotoes.add(btnQuadrado);
        pnlBotoes.add(btnRetangulo);
        pnlBotoes.add(btnPoligono);
        pnlBotoes.add(btnTexto);
        pnlBotoes.add(btnCores);
        pnlBotoes.add(lbCor);
        pnlBotoes.add(lbFonte);
        pnlBotoes.add(cbFonte);
        pnlBotoes.add(lbTamanho);
        pnlBotoes.add(cbTamanho);
        pnlBotoes.add(chekPreenchido);
        pnlBotoes.add(chekNegrito);
        pnlBotoes.add(chekItalico);
        pnlBotoes.add(chekSublinhando);



        JPanel pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1, 4);
//        pnlStatus.setLayout(grdStatus);
        pnlStatus.setLayout(grdStatus);

        //  pnlStatus.add(corTexto);
//        cor.setSize(25, 25);
//        pnlStatus.add(cor);
        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout(new BorderLayout());
        cntForm.add(pnlBotoes, BorderLayout.NORTH);
        cntForm.add(pnlDesenho, BorderLayout.CENTER);
        cntForm.add(pnlStatus, BorderLayout.SOUTH);

        this.addWindowListener(new FechamentoDeJanela());
        lbCor.setBackground(corAtual);
        lbCor.setOpaque(true);



        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fontNames = ge.getAvailableFontFamilyNames();

        for (int i = 0; i < fontNames.length; i++) {
            cbFonte.addItem(fontNames[i]);
        }

        cbTamanho.addItem("10");
        cbTamanho.addItem("12");
        cbTamanho.addItem("14");
        cbTamanho.addItem("16");
        cbTamanho.addItem("18");
        cbTamanho.addItem("20");
        cbTamanho.addItem("26");
        cbTamanho.addItem("28");
        cbTamanho.addItem("32");
        cbTamanho.addItem("36");
        cbTamanho.addItem("44");
        cbTamanho.addItem("48");
        cbTamanho.addItem("56");
        cbTamanho.addItem("68");
        cbTamanho.addItem("70");
        cbTamanho.addItem("76");


        this.setSize(1200, 500);
        this.setVisible(true);
    }

    private class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener {

        public MeuJPanel() {
            super();

            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void paint(Graphics g) {
            for (int i = 0; i < figuras.size(); i++) {
                figuras.get(i).torneSeVisivel(g);
            }
        }

        public void mousePressed(MouseEvent evt) {


            if (evt.getButton() == MouseEvent.BUTTON3) {
                if (desenhoAtual.equalsIgnoreCase(poligono)) {
                    statusBar1.setText("Mensagem: desenhando poligono");
                    desenhaPoligono();
                }
            } else if (desenhoAtual.equalsIgnoreCase(ponto)) {
                desenhaPonto(evt.getX(), evt.getY());
            } else if (desenhoAtual.equalsIgnoreCase(linha)) {
                desenhaLinha(evt.getX(), evt.getY());
            } else if (desenhoAtual.equalsIgnoreCase(elipse)) {
                desenhaElipse(evt.getX(), evt.getY());
            } else if (desenhoAtual.equalsIgnoreCase(circulo)) {
                desenhaCirculo(evt.getX(), evt.getY());
            } else if (desenhoAtual.equalsIgnoreCase(quadrado)) {
                desenhaQuadrado(evt.getX(), evt.getY());
            } else if (desenhoAtual.equalsIgnoreCase(retangulo)) {
                desenhaRetangulo(evt.getX(), evt.getY());
            } else if (desenhoAtual.equalsIgnoreCase(poligono)) {
                adicionaPontosPoligono(evt.getX(), evt.getY());

            } else if (desenhoAtual.equalsIgnoreCase(texto)) {
                desenhaTexto(evt.getX(), evt.getY());
            }
        }

        public void desenhaPonto(int x, int y) {
            figuras.add(new Ponto(x, y, corAtual));
            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
            esperaPonto = false;
        }

        public void desenhaTexto(int x, int y) {
            int negrito = Font.PLAIN;
            int italico = Font.PLAIN;
            Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
            boolean fonteSublinhada = chekSublinhando.isSelected();
            if (chekNegrito.isSelected()) {
                negrito = Font.BOLD;
            }
            if (chekItalico.isSelected()) {
                italico = Font.ITALIC;
            }
            //Copiado de : http://pt.w3support.net/index.php?db=so&id=325840
            if (chekSublinhando.isSelected()) {
                fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            }

            Font umaFonte = new Font(cbFonte.getSelectedItem().toString(), negrito + italico, Integer.parseInt(cbTamanho.getSelectedItem().toString())).deriveFont(fontAttributes);

            String textoDigitado = JOptionPane.showInputDialog(Editor.janela, "Digite o texto desejado");
            figuras.add(new Texto(x, y, umaFonte, fonteSublinhada, textoDigitado, corAtual));
            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
            desenhoAtual = "";
        }

        public void desenhaLinha(int x, int y) {
            if (esperaInicioReta) {
                p1 = new Ponto(x, y, corAtual);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final da reta");
            } else if (esperaFimReta) {
                esperaInicioReta = false;
                esperaFimReta = false;
                figuras.add(new Linha(p1.getX(), p1.getY(), x, y, corAtual));
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
        }

        public void desenhaElipse(int x, int y) {
            if (esperaInicioReta) {
                pe1 = new Ponto(x, y, corAtual);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");
            } else if (esperaFimReta) {
                esperaInicioReta = false;
                esperaFimReta = false;
                pe2 = new Ponto(x, y, corAtual);
                boolean preenchido;
                preenchido = chekPreenchido.isSelected();
                calculaRaiosECentro();
                figuras.add(new Elipse(elipseCentro.getX(), elipseCentro.getY(), elipseR1, elipseR2, corAtual, preenchido));
                System.out.println(figuras.get(figuras.size() - 1).toString());
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
        }

        public void desenhaCirculo(int x, int y) {
            if (esperaInicioReta) {
                pc1 = new Ponto(x, y, corAtual);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final do circulo");
            } else if (esperaFimReta) {
                esperaInicioReta = false;
                esperaFimReta = false;
                pc2 = new Ponto(x, y, corAtual);
                boolean preenchido;
                preenchido = chekPreenchido.isSelected();
                calculaRaioCirculo();
                figuras.add(new Circulo(circuloCentro.getX(), circuloCentro.getY(), circuloR3, corAtual, preenchido));
                System.out.println(figuras.get(figuras.size() - 1).toString());
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
        }

        public void desenhaQuadrado(int x, int y) {
            if (esperaInicioReta) {
                pq1 = new Ponto(x, y, corAtual);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final do quadrado");
            } else if (esperaFimReta) {
                esperaInicioReta = false;
                esperaFimReta = false;

                pq2 = new Ponto(x, y, corAtual);
                boolean preenchido;
                preenchido = chekPreenchido.isSelected();
                calculaRaioQuadrado();
                figuras.add(new Quadrado(quadCentro.getX(), quadCentro.getY(), quadR3, corAtual, preenchido));
                System.out.println(figuras.get(figuras.size() - 1).toString());
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
        }

        public void desenhaRetangulo(int x, int y) {
            if (esperaInicioReta) {
                pr1 = new Ponto(x, y, corAtual);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final do retangulo");
            } else if (esperaFimReta) {
                esperaInicioReta = false;
                esperaFimReta = false;
                pr2 = new Ponto(x, y, corAtual);
                boolean preenchido;
                preenchido = chekPreenchido.isSelected();
                calculaRaioRetangulo();
                figuras.add(new Retangulo(retangCentro.getX(), retangCentro.getY(), retangR1, retangR2, corAtual, preenchido));
                System.out.println(figuras.get(figuras.size() - 1).toString());
                figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
        }

        public void adicionaPontosPoligono(int x, int y) {
            pontosPoligono.add(new Ponto(x, y));
        }

        public void desenhaPoligono() {
            boolean preenchido = chekPreenchido.isSelected();
            figuras.add(new Poligono((ArrayList<Ponto>) pontosPoligono.clone(), corAtual, preenchido));
            System.out.println(figuras.get(figuras.size() - 1).toString());
            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
            pontosPoligono.clear();
            statusBar1.setText("Mensagem:");
            desenhoAtual = "";

        }

        public void calculaRaiosECentro() {
            int distanciaDeXEntreOsDoisPontos, distanciaDeYEntreOsDoisPontos;
            int xTemp = 0, yTemp = 0;
            if (pe2.getX() > pe1.getX() && pe2.getY() < pe1.getY()) {
                yTemp = pe2.getY();
                pe2.setY(pe1.getY());
                pe1.setY(yTemp);
            } else if (pe2.getX() < pe1.getX() && pe2.getY() > pe1.getY()) {
                xTemp = pe2.getX();
                pe2.setX(pe1.getX());
                pe1.setX(xTemp);
            } else if (pe2.getX() < pe1.getX() && pe2.getY() < pe1.getY()) {
                xTemp = pe2.getX();
                pe2.setX(pe1.getX());
                pe1.setX(xTemp);
                yTemp = pe2.getY();
                pe2.setY(pe1.getY());
                pe1.setY(yTemp);
            }

            distanciaDeXEntreOsDoisPontos = (pe2.getX() - pe1.getX());
            distanciaDeYEntreOsDoisPontos = (pe2.getY() - pe1.getY());

            elipseR1 = (distanciaDeXEntreOsDoisPontos / 2);
            elipseR2 = (distanciaDeYEntreOsDoisPontos / 2);

            System.out.println("DistX: " + distanciaDeXEntreOsDoisPontos + " DistY: " + distanciaDeYEntreOsDoisPontos + " R1: " + elipseR1 + " R2: " + elipseR2);
            System.out.println("P1: " + pe1.toString() + " P2: " + pe2.toString());

            elipseCentro = new Ponto(((pe2.getX() + pe1.getX()) / 2), ((pe2.getY() + pe1.getY()) / 2));
        }

        public void calculaRaioCirculo() {
            int distanciaDeXEntreOsDoisPontos, distanciaDeYEntreOsDoisPontos;
            int xTemp = 0, yTemp = 0;

            if (pc2.getX() > pc1.getX() && pc2.getY() < pc1.getY()) {
                yTemp = pc2.getY();
                pc2.setY(pc1.getY());
                pc1.setY(yTemp);
            } else if (pc2.getX() < pc1.getX() && pc2.getY() > pc1.getY()) {
                xTemp = pc2.getX();
                pc2.setX(pc1.getX());
                pc1.setX(xTemp);
            } else if (pc2.getX() < pc1.getX() && pc2.getY() < pc1.getY()) {
                xTemp = pc2.getX();
                pc2.setX(pc1.getX());
                pc1.setX(xTemp);
                yTemp = pc2.getY();
                pc2.setY(pc1.getY());
                pc1.setY(yTemp);
            }

            distanciaDeXEntreOsDoisPontos = (pc2.getX() - pc1.getX());
            distanciaDeYEntreOsDoisPontos = (pc2.getY() - pc1.getY());

            circuloR1 = (distanciaDeXEntreOsDoisPontos / 2);
            circuloR2 = (distanciaDeYEntreOsDoisPontos / 2);


            if (circuloR1 > circuloR2) {
                circuloR3 = circuloR1;

                System.out.println("DistX: " + distanciaDeXEntreOsDoisPontos + " DistY: " + distanciaDeYEntreOsDoisPontos + " Raio: " + circuloR3);
                System.out.println("P1: " + pc1.toString() + " P2: " + pc2.toString());
                circuloCentro = new Ponto(((pc2.getX() + pc1.getX()) / 2), ((pc2.getY() + pc1.getY()) / 2));
            } else {
                circuloR3 = circuloR2;

                System.out.println("DistX: " + distanciaDeXEntreOsDoisPontos + " DistY: " + distanciaDeYEntreOsDoisPontos + " Raio: " + circuloR3);
                System.out.println("P1: " + pc1.toString() + " P2: " + pc2.toString());
                circuloCentro = new Ponto(((pc2.getX() + pc1.getX()) / 2), ((pc2.getY() + pc1.getY()) / 2));
            }
        }

        public void calculaRaioQuadrado() {
            int distanciaDeXEntreOsDoisPontos, distanciaDeYEntreOsDoisPontos;
            int xTemp = 0, yTemp = 0;

            if (pq2.getX() > pq1.getX() && pq2.getY() < pq1.getY()) {
                yTemp = pq2.getY();
                pq2.setY(pq1.getY());
                pq1.setY(yTemp);
            } else if (pq2.getX() < pq1.getX() && pq2.getY() > pq1.getY()) {
                xTemp = pq2.getX();
                pq2.setX(pq1.getX());
                pq1.setX(xTemp);
            } else if (pq2.getX() < pq1.getX() && pq2.getY() < pq1.getY()) {
                xTemp = pq2.getX();
                pq2.setX(pq1.getX());
                pq1.setX(xTemp);
                yTemp = pq2.getY();
                pq2.setY(pq1.getY());
                pq1.setY(yTemp);
            }

            distanciaDeXEntreOsDoisPontos = (pq2.getX() - pq1.getX());
            distanciaDeYEntreOsDoisPontos = (pq2.getY() - pq1.getY());

            quadR1 = (distanciaDeXEntreOsDoisPontos / 2);
            quadR2 = (distanciaDeYEntreOsDoisPontos / 2);


            if (quadR1 > quadR2) {
                quadR3 = quadR1;

                System.out.println("DistX: " + distanciaDeXEntreOsDoisPontos + " DistY: " + distanciaDeYEntreOsDoisPontos + " Raio: " + quadR3);
                System.out.println("P1: " + pq1.toString() + " P2: " + pq2.toString());
                quadCentro = new Ponto(((pq2.getX() + pq1.getX()) / 2), ((pq2.getY() + pq1.getY()) / 2));
            } else {
                quadR3 = quadR2;

                System.out.println("DistX: " + distanciaDeXEntreOsDoisPontos + " DistY: " + distanciaDeYEntreOsDoisPontos + " Raio: " + quadR3);
                System.out.println("P1: " + pq1.toString() + " P2: " + pq2.toString());
                quadCentro = new Ponto(((pq2.getX() + pq1.getX()) / 2), ((pq2.getY() + pq1.getY()) / 2));
            }
        }

        public void calculaRaioRetangulo() {
            int distanciaDeXEntreOsDoisPontos, distanciaDeYEntreOsDoisPontos;
            int xTemp = 0, yTemp = 0;
            if (pr2.getX() > pr1.getX() && pr2.getY() < pr1.getY()) {
                yTemp = pr2.getY();
                pr2.setY(pr1.getY());
                pr1.setY(yTemp);
            } else if (pr2.getX() < pr1.getX() && pr2.getY() > pr1.getY()) {
                xTemp = pr2.getX();
                pr2.setX(pr1.getX());
                pr1.setX(xTemp);
            } else if (pr2.getX() < pr1.getX() && pr2.getY() < pr1.getY()) {
                xTemp = pr2.getX();
                pr2.setX(pr1.getX());
                pr1.setX(xTemp);
                yTemp = pr2.getY();
                pr2.setY(pr1.getY());
                pr1.setY(yTemp);
            }

            distanciaDeXEntreOsDoisPontos = (pr2.getX() - pr1.getX());
            distanciaDeYEntreOsDoisPontos = (pr2.getY() - pr1.getY());

            retangR1 = (distanciaDeXEntreOsDoisPontos / 2);
            retangR2 = (distanciaDeYEntreOsDoisPontos / 2);

            System.out.println("DistX: " + distanciaDeXEntreOsDoisPontos + " DistY: " + distanciaDeYEntreOsDoisPontos + " R1: " + retangR1 + " R2: " + retangR2);
            System.out.println("P1: " + pr1.toString() + " P2: " + pr2.toString());

            retangCentro = new Ponto(((pr2.getX() + pr1.getX()) / 2), ((pr2.getY() + pr1.getY()) / 2));
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            statusBar2.setText("Coordenada: " + e.getX() + "," + e.getY());
        }
    }

    private class DesenhoDePonto implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = true;
            esperaInicioReta = false;
            esperaFimReta = false;
            desenhoAtual = ponto;
            statusBar1.setText("Mensagem: clique o local do ponto desejado");
        }
    }

    private class AbrirArquivo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // declara e cria o objeto JFileChooser
            javax.swing.JFileChooser fc;
            fc = new javax.swing.JFileChooser();
            // Define o título da janela do objeto JFileChooser
            fc.setDialogTitle("Procurar Arquivos de imagem");
            // atribui a "resposta" o retorno do método showDialog()
            //        fc.setCurrentDirectory(new File("."));
            fc.setCurrentDirectory(new File(System.getProperty("user.home")));
            System.out.println("DIRETORIO PADRAO" + System.getProperty("user.home"));
            // exibe apenas tipos de arquivo suportado pela label
            fc.setFileFilter(new javax.swing.filechooser.FileFilter() {

                @Override
                public boolean accept(File f) {
                    String name = f.getName().toLowerCase();
                    return name.endsWith(".txt")
                            || f.isDirectory();
                }
                // sobreescreve a descrição do tipo de arquivo

                @Override
                public String getDescription() {
                    return "Arquivos no formato .txt";
                }
            });
            int resposta = fc.showDialog(Editor.janela, "Escolher");
            // Se for escolhida a opção de abrir o arquivo...
            if (resposta == javax.swing.JFileChooser.APPROVE_OPTION) {
                // Objeto file recebe mesma referencia do arquivo
                // selecionado.
                File file = fc.getSelectedFile();
                if (!file.exists()) {
                    statusBar1.setText("Mensagem: Arquivo Inexistente!");
                    return;
                }

                try {
                    // atribui ao campo de texto tfEnderecoDoBanco
                    // o endereço do arquivo selecionado
                    textoDoArquivo = new Convesor(new FileInputStream(new File(file.getPath()))).ConverterInputStreamToList();
                    desenharImagens();
                } catch (IOException ex) {
                    Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            statusBar1.setText("Mensagem: Arquivo aberto");
        }

        public void desenharImagens() {
            if (textoDoArquivo != null) {
                if (textoDoArquivo.size() > 0) {
                    for (String umaLinha : textoDoArquivo) {
                        if (umaLinha.startsWith("e")) {
                            System.out.println("Elipse: '" + umaLinha + "'");
                            figuras.add(new Elipse(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("l")) {
                            System.out.println("Linha: '" + umaLinha + "'");
                            figuras.add(new Linha(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("q")) {
                            System.out.println("Quadrado: '" + umaLinha + "'");
                            figuras.add(new Quadrado(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("p")) {
                            System.out.println("Ponto: '" + umaLinha + "'");
                            figuras.add(new Ponto(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("r")) {
                            System.out.println("Retangulo: '" + umaLinha + "'");
                            figuras.add(new Retangulo(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("c")) {
                            System.out.println("Circulo: '" + umaLinha + "'");
                            figuras.add(new Circulo(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("n")) {
                            System.out.println("Poligono: '" + umaLinha + "'");
                            figuras.add(new Poligono(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                        if (umaLinha.startsWith("t")) {
                            System.out.println("Texto: '" + umaLinha + "'");
                            figuras.add(new Texto(umaLinha));
                            figuras.get(figuras.size() - 1).torneSeVisivel(pnlDesenho.getGraphics());
                            statusBar1.setText("Mensagem:");
                        }
                    }
                }
            }
        }
    }

    private class DesenhoDeReta implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            desenhoAtual = linha;
            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    private class DesenhoDeElipse implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            desenhoAtual = elipse;
            statusBar1.setText("Mensagem: clique o ponto inicial da elipse");
        }
    }

    private class DesenhoDeCirculo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            desenhoAtual = circulo;
            statusBar1.setText("Mensagem: clique o ponto inicial do circulo");
        }
    }

    public class DesenhoDeQuadrado implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            desenhoAtual = quadrado;
            statusBar1.setText("Mensagem: clique o ponto inicial do quadrado");
        }
    }

    private class DesenhoDeRetangulo implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            desenhoAtual = retangulo;
            statusBar1.setText("Mensagem: clique o ponto inicial da retangulo");
        }
    }

    private class DesenhoDePoligono implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            esperaPonto = false;
            esperaInicioReta = true;
            esperaFimReta = false;
            desenhoAtual = poligono;
            statusBar1.setText("Mensagem: 1 click = adiciona vertice poligono, 2 clicks = termina poligono");
        }
    }

    private class FechamentoDeJanela extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    private class BtnSair implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class BtnLimpar implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            figuras.clear();
            pnlDesenho.repaint();
            repaint();
        }
    }

    private class BtnCor implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Color umaCor;

            umaCor = JColorChooser.showDialog(null, "Escolha a cor do fundo", corAtual);

            if (umaCor != null) {
                corAtual = new Color(umaCor.getRed(), umaCor.getGreen(), umaCor.getBlue());
                lbCor.setBackground(corAtual);
                lbCor.setOpaque(true);
            }
        }
    }

    private class DesenharTexto implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            desenhoAtual = texto;
            statusBar1.setText("Mensagem: clique no local do texto");
        }
    }

    private class SalvarDesenhos implements ActionListener {

        int resultado;
        boolean continuarProcurando = true;
        int sobrescrever;
        JFileChooser salvandoArquivo = new JFileChooser();
        File salvarArquivoEscolhido;

        public void actionPerformed(ActionEvent e) {

            while (continuarProcurando) {
                if (salvandoArquivo.showSaveDialog(Editor.janela) == JFileChooser.APPROVE_OPTION) {
                    salvarArquivoEscolhido = salvandoArquivo.getSelectedFile();
                    if (salvarArquivoEscolhido.exists()) {
                        sobrescrever = JOptionPane.showConfirmDialog(Editor.janela, "O arquivo já existe! Deseja sobrescreve-lo?");
                        if (sobrescrever == JOptionPane.OK_OPTION) {
                            continuarProcurando = false;
                        } else if (sobrescrever == JOptionPane.CANCEL_OPTION) {
                            return;
                        }
                    } else {
                        continuarProcurando = false;
                    }
                } else {
                    return;
                }
            }
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(salvarArquivoEscolhido));
                for (Figura umaFigura : figuras) {
                    pw.println(umaFigura.toString());
                }
                pw.close();
            } catch (IOException ex) {
                Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
            }
            continuarProcurando = true;
        }
    }
}
