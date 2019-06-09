import Lexer.Lexer;
import Lexer.Token;
import Parcer.Parser;
import Parcer.Poliz;
import Stack_Maschine.PolizCalculation;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer();
        LinkedList<Token> tokens = lexer.lex("с = 10; и = 0; а новый Лист; а.добавить(10); а.добавить(10+10); а.добавить(5); б новый Сет; б.добавить(3); б.добавить(3); с = а.получить(1); пока(и < 5){и = и + 1;} с++;" );
        System.out.println("Токены:");
        for (int i = 0; i < tokens.size(); i++)
        {
            System.out.println(tokens.get(i));
        }
        try {
            Parser.parse(tokens);
        }catch ( Exception ex)
        { System.err.println(ex);
            System.exit(1);
        }
        System.out.println("ОПЗ:");
        LinkedList<Token> testPoliz = Poliz.makePoliz(tokens);
        int i = 0;
        for (Token token : testPoliz) {
            System.out.println(i + " " + token);
            i++;
        }
        System.out.println("Таблица переменных:");
        PolizCalculation.calculate(testPoliz);

    }
}
