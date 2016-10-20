package util;

import java.util.Arrays;

/**
 * @author cuihao
 */
public class Constant {
    private static final String[] KEYWORDS={"abstract","assert","boolean","break","byte","case","catch","char","class",
            "const","continue","default","do","double","else","enum","extends","final","finally","float","for","goto",
            "if", "implements", "import","instanceof","int","interface","long","native","new","package","private",
            "protected","public","return","strictfp","short","static","super","switch","synchronized","this","throw",
            "throws","transient","try","void","volatile","while"};
    private static final String[] OPERATOR={"+","=","-","*","/","％",">","<","&","|","!","?", ":","[","]","(",")"};
    private static final String[] SEPARATOR={";","{","}"};
    private static final String[] END_TAG={"\b","\n","\t"};

    public static boolean isDigit(char test) {
        return  test>='0' && test<='9';
    }

    public static boolean isLetter(char test) {
        return ( test>='a' && test <= 'z' ) || (test>='A' && test<='Z');
    }

    public static boolean isKeyword(String word) {
        return Arrays.asList(KEYWORDS).contains(word);
    }

    public static boolean isOperator(char test) {
        return Arrays.asList(OPERATOR).contains(test+"");
    }

    public static boolean isSeparator(char test) {
        return Arrays.asList(SEPARATOR).contains(test+"");
    }

    public static boolean isEndTag(char test) {
        return Arrays.asList(END_TAG).contains(test+"");
    }
}
