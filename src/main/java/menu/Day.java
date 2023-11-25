package menu;

public enum Day {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금");
    private String name;

    Day(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
