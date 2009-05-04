package Generated;
import java.io.*;


public class SokoCompiler
{
    static public void main(String argv[])
    {
    try {
        // Istanzio lo scanner aprendo il file di ingresso argv[0]
        Lexer l = new Lexer(new FileReader("Maps/mappa.map"));
        // Istanzio il parser
        SokoParserCup p = new SokoParserCup(l);
        // Avvio il parser
        Object result = p.parse();
        }
    catch (Exception e) {
        e.printStackTrace();
        }
    }
}
