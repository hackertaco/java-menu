package menu.view;


import static menu.utils.ErrorMessages.BLANK;
import static menu.utils.Messages.REQUEST_COACH_NAME;
import static menu.utils.Messages.REQUEST_UNAVAILABLE_MENUS;

import java.util.ArrayList;
import java.util.List;
import menu.utils.Input;
import menu.utils.Printer;


public class InputView {
    private final Printer printer;
    private final Input input;

    private static String SPLITTER = ",";

    public InputView(Printer printer, Input input) {
        this.printer = printer;
        this.input = input;
    }

    public List<String> readCoachName(){
        printer.printf(REQUEST_COACH_NAME, SPLITTER);
        String pickedCoaches = input.getInput();
        validateBlank(pickedCoaches);

        return parseStringToList(pickedCoaches);
    }

    public List<String> readUnavailableMenus(String name){
        printer.printf(REQUEST_UNAVAILABLE_MENUS, name);
        String menus = input.getInput();
        if(menus.length() == 0){
            return new ArrayList<>();
        }
        return parseStringToList(menus);
    }


    private void validateBlank(String input){
        if(input.isBlank()){
            throw new IllegalArgumentException(BLANK);
        }
    }


    private List<String> parseStringToList(String input){
        return List.of(input.split(SPLITTER));
    }
}
