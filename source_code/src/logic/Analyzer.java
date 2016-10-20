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
                            state = State.STATE_3;
                            tempWord += current;
                        } else if (Constant.isSeparator(current)) {
                            state = State.STATE_3;
                            tempWord += current;
                        }
                        break;
                    case STATE_1:
                        if (Constant.isDigit(current)) {
                            tempWord += current;
                        } else if (Constant.isLetter(current)) {
                            tempWord += current;
                        } else {
                            index--;
                            state = State.STATE_0;
                            if (Constant.isKeyword(tempWord)) {
                                addToken(tempWord, Catalog.KEYWORD);
                            } else {
                                addToken(tempWord, Catalog.ID);
                            }
                            tempWord = "";
                        }
                        break;
                    case STATE_2:
                        if (Constant.isDigit(current)) {
                            tempWord += current;
                        } else if (current=='.') {
                            tempWord += current;
                            state = State.STATE_4;
                        } else {
                            index--;
                            state = State.STATE_0;
                            addToken(tempWord, Catalog.INT);
                            tempWord = "";
                        }
                        break;
                    case STATE_3:
                        index--;
                        state = State.STATE_0;
                        if (Constant.isOperator(tempWord.charAt(0))) {
                            addToken(tempWord, Catalog.OPERATOR);
                        } else {
                            addToken(tempWord, Catalog.SEPARATOR);
                        }
                        tempWord = "";
                        break;
                    case STATE_4:
                        if (Constant.isDigit(current)) {
                            state = State.STATE_5;
                            tempWord += current;
                        } else {
                            System.out.println("error: state 4");
                        }
                        break;
                    case STATE_5:
                        if (Constant.isDigit(current)) {
                            tempWord += current;
                        } else {
                            index --;
                            state = State.STATE_0;
                            addToken(tempWord, Catalog.DOUBLE);
                            tempWord="";
                        }
                        break;
                }
                index++;
            }
            switch (state) {
                case STATE_1:
                    if (Constant.isKeyword(tempWord)) {
                        addToken(tempWord, Catalog.KEYWORD);
                    } else {
                        addToken(tempWord, Catalog.ID);
                    }
                    break;
                case STATE_2:
                    addToken(tempWord, Catalog.INT);
                    break;
                case STATE_3:
                    if (tempWord.length()>0) {
                        if (Constant.isOperator(tempWord.charAt(0))) {
                            addToken(tempWord, Catalog.OPERATOR);
                        } else {
                            addToken(tempWord, Catalog.SEPARATOR);
                        }
                    }
                    break;
                case STATE_5:
                    addToken(tempWord, Catalog.DOUBLE);
                    break;
            }
        }
        for (Token token: tokens) {
            System.out.println(token);
        }
    }

    private void addToken(String lex, Catalog catalog) {
        Token token = new Token(catalog,lex);
        tokens.add(token);
    }
}
