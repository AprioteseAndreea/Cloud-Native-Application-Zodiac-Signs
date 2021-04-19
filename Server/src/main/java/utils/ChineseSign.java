package utils;

import java.util.ArrayList;

public class ChineseSign {
    private String name;
    private ArrayList<String> years;

    public ChineseSign(String name, ArrayList<String> years) {
        this.name = name;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getYears() {
        return years;
    }

    public void setYears(ArrayList<String> years) {
        this.years = years;
    }
}
