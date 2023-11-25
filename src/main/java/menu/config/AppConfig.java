package menu.config;


import menu.controller.RecommendationController;
import menu.service.RecommendationService;
import menu.utils.Input;
import menu.utils.InputConsole;
import menu.utils.Printer;
import menu.utils.SystemPrinter;
import menu.view.InputView;
import menu.view.OutputView;

public class AppConfig {
    public RecommendationController recommendationController(){
        return new RecommendationController(outputView(), inputView(), recommendationService());
    }

    private RecommendationService recommendationService(){
        return new RecommendationService();
    }

    private Printer printer(){
        return new SystemPrinter();
    }

    private Input input(){
        return new InputConsole();
    }

    private InputView inputView(){
        return new InputView(printer(), input());
    }

    private OutputView outputView(){
        return new OutputView(printer());
    }

}
