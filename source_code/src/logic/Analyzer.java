package logic;

import io.IOHelper;
import util.Catalog;
import util.Constant;
import util.State;
import util.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * logic of lexical analyzer
 * @author cuihao
 */
public class Analyzer {
    private IOHelper ioHelper;
    private List<Token> tokens = new ArrayList<>();

    public Analyzer(IOHelper ioHelper) {
        this.ioHelper = ioHelper;
    }

    public void lexicalAnalyze() {
        String line = "";
        while ((line = ioHelper.nextLine()) != null) {
            int index = 0;
            String tempWord = "";
            State state = State.STATE_0;
            while (index < line.length()) {
                char current = line.charAt(index);
                switch (state){
                    case STATE_0:
                        if (Constant.isDigit(current)) {
                            state = State.STATE_2;
                            tempWord += current;
                        } else if (Constant.isLetter(current)) {
                            state = State.STATE_1;
                            tempWord += current;
                        } else if (Constant.isOperator(current)) {
                            addToken(current+"",Catalog.OPERATOR);
                            state = State.STATE_0;
                        }
                }
                index++;
            }
        }
    }

    private void addToken(String lex, Catalog catalog) {
        Token token = new Token(catalog,lex);
        tokens.add(token);
    }
}
