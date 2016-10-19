import io.IOHelper;
import logic.Analyzer;
import util.Constant;

public class Main {

    public static void main(String[] args) {
        IOHelper helper = new IOHelper("testFile/testProgram.txt");
        Analyzer analyzer = new Analyzer(helper);
        analyzer.lexicalAnalyze();
        helper.closeFile();
    }
}
