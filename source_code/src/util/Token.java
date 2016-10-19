package util;

/**
 * Token class
 * @author cuihao
 */
public class Token {
    /**
     * catalog of this token
     * {@link Catalog}
     */
    private Catalog catalog;
    /**
     * certain lexeme
     */
    private String lexeme;

    public Token(Catalog catalog, String lexeme) {
        this.catalog = catalog;
        this.lexeme = lexeme;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return "("+catalog+","+lexeme+")";
    }
}
