package util;

/**
 * a unit of analyzer
 * @author cuihao
 */
public class PPTUnit {

    private PPTType type;
    private int num;

    public PPTUnit(PPTType type, int num) {
        this.type = type;
        this.num = num;
    }

    public PPTType getType() {
        return type;
    }

    public void setType(PPTType type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
