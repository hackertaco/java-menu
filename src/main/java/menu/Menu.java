package menu;


public enum Menu {
    KYUDON("규동"),
    UDON("우동"),
    MISO_SOUP("미소시루"),
    SUSHI("스시"),
    KATCUDON("가츠동"),
    ONIGIRI("오니기리"),
    HIRICE("하이라이스"),
    RAMEN("라멘"),
    OKONOMIYAKI("오코노미야끼"),
    KIMBAB("김밥"),
    KIMCHI_SOUP("김치찌개"),
    SSAMBAB("쌈밥"),
    BEAN_SOUP("된장찌개"),
    BIBIMBAB("비빔밥"),
    KAL_NOODLE("칼국수"),
    BULGOGI("불고기"),
    DDEOKBOKKI("떡볶이"),
    JEYUK_BOKEUM("제육볶음"),
    KKANPUNGI("깐풍기"),
    BOKEUM_MYEON("볶음면"),
    DONGPAYUK("동파육"),
    JJAJANG_MYEON("짜장면"),
    JJAMBBONG("짬뽕"),
    MAPA_TOFU("마파두부"),
    TANGSUYUK("탕수육"),
    TOMATO_EGG("토마토 달걀볶음"),
    PEPPER_JAPCHAE("고추잡채"),
    POD_THAI("팟타이"),
    KAO_POD("카오 팟"),
    NASHIGORANG("나시고렝"),
    PINEAPPLE_FRIED_RICE("파인애플 볶음밥"),
    RICE_NOODLE("쌀국수"),
    DDOMYAN("똠얌꿍"),
    BANMI("반미"),
    WOLNAMSSAM("월남쌈"),
    BUNJJA("분짜"),
    RASAGNA("라자냐"),
    GRATANG("그라탱"),
    KNOKKI("뇨끼"),
    KKISHU("끼슈"),
    FRENCH_TOAST("프렌치 토스트"),
    BAGUETTE("바게트"),
    SPAGHETTI("스파게티"),
    PIZZA("피자"),
    PANINI("파니니"),
    ;

    private String name;

    Menu(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


}
