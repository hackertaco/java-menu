package menu;

import java.util.Arrays;
import java.util.List;

public enum MenuGroup {
    JAPAN("일식", Arrays.asList(Menu.KYUDON, Menu.UDON, Menu.MISO_SOUP, Menu.SUSHI, Menu.KATCUDON, Menu.ONIGIRI, Menu.HIRICE, Menu.RAMEN, Menu.OKONOMIYAKI)),
    KOREAN("한식", Arrays.asList(Menu.KIMBAB, Menu.KIMCHI_SOUP, Menu.SSAMBAB, Menu.BEAN_SOUP, Menu.BIBIMBAB, Menu.KAL_NOODLE, Menu.BULGOGI, Menu.DDEOKBOKKI, Menu.JEYUK_BOKEUM)),
    CHINESE("중식", Arrays.asList(Menu.KKANPUNGI, Menu.BOKEUM_MYEON, Menu.DONGPAYUK, Menu.JJAJANG_MYEON, Menu.JJAMBBONG, Menu.MAPA_TOFU, Menu.TANGSUYUK, Menu.TOMATO_EGG, Menu.PEPPER_JAPCHAE)),
    ASIAN("아시안", Arrays.asList(Menu.POD_THAI, Menu.KAO_POD, Menu.NASHIGORANG, Menu.PINEAPPLE_FRIED_RICE, Menu.RICE_NOODLE, Menu.DDOMYAN, Menu.BANMI, Menu.WOLNAMSSAM, Menu.BUNJJA)),
    WESTERN("양식", Arrays.asList(Menu.RASAGNA, Menu.GRATANG, Menu.KNOKKI, Menu.KKISHU, Menu.FRENCH_TOAST, Menu.BAGUETTE, Menu.SPAGHETTI, Menu.PIZZA, Menu.PANINI));
    private String menuGroupName;
    private List<Menu> menus;

    MenuGroup(String menuGroupName, List<Menu> menus){
        this.menuGroupName = menuGroupName;
        this.menus = menus;
    }
    public String getMenuGroupName(){
        return menuGroupName;
    }

    public List<Menu> getMenus(){
        return menus;
    }
}
