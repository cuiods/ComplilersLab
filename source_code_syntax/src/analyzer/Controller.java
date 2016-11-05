package analyzer;

import util.CFG;
import util.PPTUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * controller of syntax analyzer
 * @author cuihao
 */
public class Controller {
    private Stack<Integer> stateStack = new Stack<>();
    private Stack<String> tagStack = new Stack<>();
    private PPT ppt;
    private List<CFG> cfgList = new ArrayList<>();

    public Controller(PPT ppt) {
        this.ppt = ppt;
        initializeCFGList();
    }

    public void startAnalyze() {
        stateStack.push(0);
        tagStack.push("$");
        try {
            Scanner scanner = new Scanner(new FileInputStream("input/test.txt"));
            loop:while(scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;
                for (int i = 0; i < line.length(); i++) {
                    int state = stateStack.peek();
                    String tag = line.charAt(i)+"";
                    PPTUnit unit = ppt.getAction(state,tag);
                    switch (unit.getType()) {
                        case SHIFT:
                            stateStack.push(unit.getNum());
                            tagStack.push(tag);
                            System.out.print("shift "+unit.getNum()+"  ");
                            break;
                        case REDUCE:
                            i--;
                            CFG cfg = cfgList.get(unit.getNum());
                            for (int j = cfg.getRight().length()-1; j >= 0; j--) {
                                stateStack.pop();
                                tagStack.pop();
                            }
                            tagStack.push(cfg.getLeft());
                            stateStack.push(ppt.getGoto(stateStack.peek(),cfg.getLeft()));
                            System.out.print("reduce "+cfg+" ");
                            break;
                        case ACCEPT:
                            System.out.println("parse succeed!");
                            break loop;
                        case NULL:
                            System.out.println("Unknown Error!");
                            break loop;
                    }
                    printStack();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find test file.");
        }
    }

    private void printStack() {
        System.out.print("state stack:");
        for (int i = 0; i < stateStack.size(); i++) {
            System.out.print(stateStack.get(i));
            if (i != stateStack.size()-1) System.out.print(",");
        }
        System.out.print("  tag stack:");
        for (int i = 0; i < tagStack.size(); i++) {
            System.out.print(tagStack.get(i));
            if (i != stateStack.size()-1) System.out.print(",");
        }
        System.out.println();
    }

    private void initializeCFGList() {
        try {
            Scanner scanner = new Scanner(new FileInputStream("input/CFG.txt"));
            while (scanner.hasNext()) {
                String temp = scanner.nextLine();
                if (temp.isEmpty()) break;
                String[] tempStrs = temp.split("=");
                cfgList.add(new CFG(tempStrs[0],tempStrs[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
