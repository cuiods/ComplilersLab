import analyzer.Controller;
import analyzer.PPT;

public class Main {

    public static void main(String[] args) {
        PPT ppt = new PPT();
        Controller controller = new Controller(ppt);
        controller.startAnalyze();
    }
}
