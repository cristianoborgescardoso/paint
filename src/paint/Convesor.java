package paint;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Convesor {

    private InputStream arquivoDeTexto;

    public Convesor(InputStream umArquivoDeTexto) {
        this.arquivoDeTexto = umArquivoDeTexto;
    }

    /**
     * 
     * @param umaClasseNoMesmoDiretorio
     * @param nomeDoArquivoPontoTipo
     */
    public Convesor(Class umaClasseNoMesmoDiretorio, String nomeDoArquivoPontoTipo) {
        this.arquivoDeTexto = umaClasseNoMesmoDiretorio.getResourceAsStream(nomeDoArquivoPontoTipo);

    }

    /**
     * 
     * @return
     * @throws IOException
     */
    public String ConverterInputStreamToString() throws IOException {

        if (arquivoDeTexto != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(
                        new InputStreamReader(arquivoDeTexto, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                arquivoDeTexto.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public ArrayList ConverterInputStreamToList() throws IOException {
        ArrayList<String> linhas = new ArrayList<String>();
        if (arquivoDeTexto != null) {
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
//                Reader reader = new BufferedReader(
//                        new InputStreamReader(arquivoDeTexto, "UTF-8"));
                BufferedReader leitor = new BufferedReader(new InputStreamReader(arquivoDeTexto, "UTF-8"), 1 * 1024 * 1024);
                int n;
                String linha = null;
                while (leitor.ready()) {
                    linhas.add(leitor.readLine());
                }

            } finally {
                System.out.println("Linhas: "+linhas.size());

                arquivoDeTexto.close();
            }
            return linhas;
        } else {
            return null;
        }
    }
}
