package CadasterObjects;

public enum Landform {

    ARABLE_LAND ("arable", "02"),
    HOP_FIELD ("hop", "03"),
    VINEYARD ("vineyard", "04"),
    GARDEN ("garden", "05"),
    FRUIT_GROVE ("grove", "06"),
    GRASSFIELD ("grass", "07"),
    WOODS ("woods", "10"),
    WATER_AREA ("water", "11"),
    BUILTUP_AREA ("built", "13"),
    OTHER ("other", "14");

    private final String shortName;
    private final String num;

    private Landform(String shortName, String num) {
        this.shortName = shortName;
        this.num = num;
    }

    public String getShortName() {
        return shortName;
    }

    public String getNum() {
        return num;
    }
}
