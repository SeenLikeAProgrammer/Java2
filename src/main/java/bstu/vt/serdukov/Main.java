package bstu.vt.serdukov;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class Main {
    private static final String F_PATH = "src/main/resources/lab_2_v1.txt";

    public static void main(String[] args) throws IOException, ParseException {
        ArrayList <Build> buildings= new ParserJson(F_PATH).parseBuildings();
        buildings.stream().map(Object::toString).forEach(System.out::println);
    }
}
