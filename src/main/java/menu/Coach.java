package menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Coach {
    private final String name;
    private final List<Optional<Menu>> unavailableMenus;

    private Coach(String name, List<Optional<Menu>> unavailableMenus){
        this.name = name;
        this.unavailableMenus = unavailableMenus;
    }

    public static Coach createCoach(String name, List<Optional<Menu>> menus){
        return new Coach(name, menus);
    }
    public String getName(){
        return name;
    }

    public boolean cannotEat(String menu){
        Menu pickedMenu = Arrays.stream(Menu.values()).filter(m->m.getName().equals(menu)).findAny().get();
        return unavailableMenus.contains(pickedMenu);
    }
}
