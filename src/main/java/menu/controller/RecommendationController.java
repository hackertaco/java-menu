package menu.controller;

import static menu.utils.Messages.END;
import static menu.utils.Messages.TITLE_FORMAT;
import static menu.utils.Messages.WELCOME;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import menu.Coach;
import menu.Day;
import menu.Menu;
import menu.MenuGroup;
import menu.service.RecommendationService;
import menu.view.InputView;
import menu.view.OutputView;

public class RecommendationController {
    private final OutputView outputView;
    private final InputView inputView;


    private final RecommendationService recommendationService;

    public RecommendationController(OutputView outputView, InputView inputView, RecommendationService recommendationService){
        this.inputView = inputView;
        this.outputView = outputView;
        this.recommendationService = recommendationService;
    }

    public void process(){

        startProgram();
        List<String> coachNames = getCoachNames();
        List<Coach> coaches = setUnavailableMenu(coachNames);

        EnumMap<Day, MenuGroup> menuCategories = recommendationService.pickMenuGroup();
        showOutput(menuCategories, coaches);
        endProgram();

    }
    public List<String> getCoachNames(){
        while (true) {
            try {
                List<String> coaches = inputView.readCoachName();
                recommendationService.validateCoachNames(coaches);
                return coaches;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    public List<Coach> setUnavailableMenu(List<String> coachNames){
        List<Coach> coaches = new ArrayList<>();
        for(String name: coachNames){
            List<Optional<Menu>> unAvailableMenus = getUnavailableMenus(name);
            Coach coach = Coach.createCoach(name, unAvailableMenus);
            coaches.add(coach);
        }
        return coaches;
    }

    public List<Optional<Menu>> getUnavailableMenus(String name){
        while (true) {
            try {
                List<String> unavailableMenus = inputView.readUnavailableMenus(name);
                recommendationService.validateUnavailableMenus(unavailableMenus);
                return recommendationService.parseStringToMenu(unavailableMenus);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    public void startProgram(){
        outputView.printWithArguments(WELCOME);
    }
    private void showOutput(EnumMap<Day, MenuGroup> menuCategories, List<Coach> coaches){
        outputView.print("메뉴 추천 결과입니다.");
        outputView.printOutput(outputView.buildDayString("구분"));
        outputView.printOutput(outputView.buildMenuGroupString("카테고리", menuCategories));


        Map<Coach, List<String>> pickedMenu = recommendationService.pickFinalMenus(menuCategories, coaches);
        for(Coach coach:coaches){
            outputView.printOutput(outputView.buildMenu(coach.getName(), pickedMenu.get(coach)));
        }
    }
    public void endProgram(){
        outputView.printWithArguments(END);
    }
}
