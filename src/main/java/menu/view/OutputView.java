package menu.view;


import static menu.utils.Messages.OUTPUT_FORMAT;
import static menu.utils.Messages.TITLE_FORMAT;

import java.util.EnumMap;
import java.util.List;
import menu.Day;
import menu.MenuGroup;
import menu.utils.Printer;


public class OutputView {
    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }
    public void print(String message){
        printer.print(message);
    }
    public void printError(String errorMessage){
        String errorPrefix = "[ERROR] ";
        printer.print(errorPrefix + errorMessage);
    }

    public void printWithArguments(String message, Object... args){
        printer.printf(message, args);
    }

    public String buildDayString(String title){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TITLE_FORMAT, title));
        for(Day day: Day.values()){
            String delimeter = "|";
            sb.append(delimeter);
            sb.append(" ");
            sb.append(Day.valueOf(String.valueOf(day)).getName() +"요일");
            sb.append(" ");
        }
        return String.valueOf(sb);
    }

    public String buildMenuGroupString(String title, EnumMap<Day, MenuGroup> menuCategories){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TITLE_FORMAT, title));
        for(MenuGroup menuGroup: menuCategories.values()){
            String delimeter = "|";
            sb.append(delimeter);
            sb.append(" ");
            sb.append(MenuGroup.valueOf(String.valueOf(menuGroup)).getMenuGroupName());
            sb.append(" ");
        }
        return String.valueOf(sb);
    }

    public String buildMenu(String title, List<String> pickedMenu){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(TITLE_FORMAT, title));
        for(String m: pickedMenu){
            String delimeter = "|";
            sb.append(delimeter);
            sb.append(" ");
            sb.append(m);
            sb.append(" ");
        }
        return String.valueOf(sb);
    }

    public void printOutput(String message){
        printer.printf(OUTPUT_FORMAT, message);
        addLineForReadability();
    }




    private void addLineForReadability(){
        printer.print("");
    }
}
