package menu.service;

import static menu.utils.ErrorMessages.DUPLICATE;
import static menu.utils.ErrorMessages.INVALID_COACH_NAME;
import static menu.utils.ErrorMessages.INVALID_COACH_NAME_LENGTH;
import static menu.utils.ErrorMessages.INVALID_UNAVAILABLE_MENU_LENGTH;
import static menu.utils.ErrorMessages.NOT_EXIST;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import menu.Coach;
import menu.Day;
import menu.Menu;
import menu.MenuGroup;


public class RecommendationService {
    private static Integer MIN_COACH_COUNT = 2;
    private static Integer MAX_COACH_COUNT = 5;
    private static Integer MIN_NAME_LENGTH = 2;
    private static Integer MAX_NAME_LENGTH = 4;
    private static Integer MIN_UNAVAILABLE_MENU_LENGTH = 2;
    private static Integer MAX_SAME_CATERORY_COUNT = 2;

    public List<String> validateCoachNames(List<String> coaches){
        if(coaches.size() < MIN_COACH_COUNT || coaches.size() > MAX_COACH_COUNT){
            throw new IllegalArgumentException(INVALID_COACH_NAME);
        }
        if(isDuplicate(coaches)){
            throw new IllegalArgumentException(DUPLICATE);
        }
        coaches.stream().forEach(coach->validateName(coach));
        return coaches;
    }

    public void validateUnavailableMenus(List<String> menus){
        if(isDuplicate(menus)){
            throw new IllegalArgumentException(DUPLICATE);
        }
        for(String menu:menus){
            if(!existMenu(menu)){
                throw new IllegalArgumentException(NOT_EXIST);
            }
        }
        if(menus.size() > MIN_UNAVAILABLE_MENU_LENGTH){
            throw new IllegalArgumentException(INVALID_UNAVAILABLE_MENU_LENGTH);
        }
    }
    public List<Optional<Menu>> parseStringToMenu(List<String> menus){
        List<Optional<Menu>> unavailableMenus = new ArrayList<>();
        if(menus.isEmpty()){
            return unavailableMenus;
        }
        for(String menu:menus){
            unavailableMenus.add(
                    Optional.of(Arrays.stream(Menu.values()).filter(m -> m.getName().equals(menu)).findAny().get()));
        }
        return unavailableMenus;
    }
    public EnumMap<Day, MenuGroup> pickMenuGroup(){
        EnumMap<Day, MenuGroup> menuCategories = new EnumMap<>(Day.class);
        List<MenuGroup> categories = getRandomMenuGroup();

        for(int i = 0; i<Day.values().length; i++){
            menuCategories.put(Day.values()[i], categories.get(i));
        }
        return menuCategories;
    }

    public Map<Coach, List<String>> pickFinalMenus(EnumMap<Day, MenuGroup> menuCategories, List<Coach> coaches){
        Map<Coach, List<String>> map = new HashMap<>();

        for(MenuGroup menuGroup: menuCategories.values()){
            List<String> menus = extractMenuNames(menuGroup);
            addCoachMenu(map, coaches, menus);
        }
        return map;
    }
    private void addCoachMenu(Map<Coach, List<String>> map,List<Coach> coaches, List<String> menus){
        for(Coach coach: coaches){
            List<String> list = map.getOrDefault(coach, new ArrayList<>());
            addMenu(menus, coach, list, map);
        }
    }
    private List<String> extractMenuNames(MenuGroup menuGroup) {
        return menuGroup.getMenus().stream()
                .map(Menu::getName)
                .collect(Collectors.toList());
    }
    private void addMenu(List<String> menus, Coach coach, List<String> list, Map<Coach, List<String>> map){
        while (true){
            String m = Randoms.shuffle(menus).get(0);
            if(coach.cannotEat(m) || list!=null && list.contains(m)){
                continue;
            }
            list.add(m);
            map.put(coach, list);
            break;
        }
    }

    private List<MenuGroup> getRandomMenuGroup(){
        List<MenuGroup> groups = new ArrayList<>();
        while(groups.size() < Day.values().length){
            int idx = Randoms.pickNumberInRange(1, 5);
            MenuGroup menuGroup = MenuGroup.values()[idx-1];

            if(groups.stream().filter(s->s == MenuGroup.values()[idx-1]).count() > MAX_SAME_CATERORY_COUNT-1){
                continue;
            }
            groups.add(menuGroup);
        }
        return groups;
    }


    private static void validateName(String beforeValidatedName){
        if(beforeValidatedName.length() < MIN_NAME_LENGTH ||  beforeValidatedName.length() > MAX_NAME_LENGTH){
            throw new IllegalArgumentException(INVALID_COACH_NAME_LENGTH);
        }
    }
    private boolean isDuplicate(List<String> input){
        return input.size() != new HashSet<>(input).size();
    }

    private boolean existMenu(String menu){
        return Arrays.stream(Menu.values()).anyMatch(m->m.getName().equals(menu));
    }


}
