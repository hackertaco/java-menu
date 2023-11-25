package menu;

import menu.config.AppConfig;
import menu.controller.RecommendationController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        RecommendationController recommendationController = appConfig.recommendationController();
        recommendationController.process();
    }
}
