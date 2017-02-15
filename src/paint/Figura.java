package paint;

import java.awt.Color;
import java.awt.Graphics;


public abstract class Figura
{
    protected Color cor;
	  
    protected Figura ()
    {
        this.cor = Color.black;
    }
	  
    protected Figura (Color cor)
    {
        this.setCor (cor);
    }
	  
    protected void setCor (Color cor)
    {
        this.cor = cor;
    }
	  
    protected Color getCor()
    {
  	return this.cor;
    }

    @Override
    public abstract String toString       ();	  
    public abstract void   torneSeVisivel (Graphics g);
}