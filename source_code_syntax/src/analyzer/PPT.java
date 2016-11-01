package analyzer;

import util.PPTType;
import util.PPTUnit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * analyzer data storage
 * @author cuihao
 */
public class PPT {
    private String ACTION[] = {};
    private String GOTO[] = {};
    private PPTUnit ACTION_DATA[][];
    private int GOTO_DATA[][];

    public PPT() {
        initialize();
    }

    private void initialize() {
        try {
            Scanner scanner = new Scanner(new FileInputStream("input/table.txt"));
            int totalNum = Integer.parseInt(scanner.nextLine());
            String temp = scanner.nextLine();
            String tempArray[] = temp.split(":");
            int actionNum = Integer.parseInt(tempArray[0]);
            ACTION = tempArray[1].split(",");
            temp = scanner.nextLine();
            tempArray = temp.split(":");
            int gotoNum = Integer.parseInt(tempArray[0]);
            GOTO = tempArray[1].split(",");
            ACTION_DATA = new PPTUnit[totalNum][actionNum];
            GOTO_DATA = new int[totalNum][gotoNum];
            for (int i = 0; i < totalNum; i++) {
                for (int j = 0; j < actionNum; j++) {
                    ACTION_DATA[i][j] = new PPTUnit(PPTType.NULL,-1);
                }
            }
            for (int i = 0; i < totalNum; i++) {
                for (int j = 0; j < gotoNum; j++) {
                    GOTO_DATA[i][j] = -1;
                }
            }
            scanner.nextLine();
            for (int i = 0; i < totalNum; i++) {
                temp = scanner.nextLine().split(":")[1];
                tempArray = temp.split("\\|");
                for (int j = 0; j < tempArray.length; j++) {
                    String[] str = tempArray[j].split("%");
                    int index = Arrays.asList(ACTION).indexOf(str[0]);
                    char test = str[1].charAt(0);
                    if (test=='S') {
                        ACTION_DATA[i][index] = new PPTUnit(PPTType.SHIFT,Integer.parseInt(str[1].substring(1)));
                    } else if (test=='R') {
                        ACTION_DATA[i][index] = new PPTUnit(PPTType.REDUCE,Integer.parseInt(str[1].substring(1)));
                    } else if (test=='$') {
                        ACTION_DATA[i][index] = new PPTUnit(PPTType.ACCEPT,-1);
                    }
                }
            }
            scanner.nextLine();
            while (scanner.hasNext()) {
                temp = scanner.nextLine();
                if (temp.isEmpty()) {
                    break;
                }
                String[] strings = temp.split(":");
                int line = Integer.parseInt(strings[0]);
                temp = strings[1];
                tempArray = temp.split("\\|");
                for (int j = 0; j < tempArray.length; j++) {
                    String[] str = tempArray[j].split("%");
                    int index = Arrays.asList(GOTO).indexOf(str[1]);
                    GOTO_DATA[line][index] = Integer.parseInt(str[0]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getGoto(int state, String tag) {
        return GOTO_DATA[state][Arrays.asList(GOTO).indexOf(tag)];
    }

    public PPTUnit getAction(int state, String tag) {
        return ACTION_DATA[state][Arrays.asList(ACTION).indexOf(tag)];
    }

    public static void main(String[] args) {
        PPT ppt = new PPT();
        System.out.println(ppt.getAction(1,"$").getType());
    }
}
