package util;

/**
 * CFG expression data structure
 * @author cuihao
 */
public class CFG {
    private String left;
    private String right;

    public CFG(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
