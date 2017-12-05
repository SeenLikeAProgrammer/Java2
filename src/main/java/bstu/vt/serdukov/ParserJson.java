package bstu.vt.serdukov;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserJson {
    private BufferedReader fileReader;
    private final Pattern typePattern = Pattern.compile(".*\"value\" : \"([^<]*)\".*");
    private final Pattern builddatePattern = Pattern.compile(".*\"builddate\" : \"([^<]*)\".*");
    private final Pattern materialPattern = Pattern.compile(".*\"material\" : \"([^<]*)\".*");
    private final Pattern squarePattern = Pattern.compile(".*\"square\" : \"(\\S+)\".*");


    ParserJson(String filePath) throws IOException {
        fileReader = Files.newBufferedReader(Paths.get(filePath));
    }

    ArrayList<Build> parseBuildings() throws IOException, ParseException {
        ArrayList<Build> appartaments = new ArrayList<>();
        String type = "";
        String builddate = "";
        String material = "";
        float square = 0;
        String str;
        while ((str = fileReader.readLine()) != null) {
            Matcher typeMatcher = typePattern.matcher(str);
            Matcher materialMatcher = materialPattern.matcher(str);
            Matcher builddateMatcher = builddatePattern.matcher(str);
            Matcher squareMatcher = squarePattern.matcher(str);
            if (typeMatcher.matches()) {
                type = typeMatcher.group(1);
            }
            if (builddateMatcher.matches()){
                builddate=builddateMatcher.group(1);
            }
            if (materialMatcher.matches()) {
                material = materialMatcher.group(1);
            }
            if (squareMatcher.matches()) {

                square = Float.parseFloat(squareMatcher.group(1).replace(',', '.'));
            }
            if (!type.isEmpty() && !builddate.isEmpty() && !material.isEmpty() && square != 0) {
                appartaments.add(new Build(type, builddate, material, square));
                type = "";
                material = "";
                builddate = "";
                square = 0;
            }
        }
        fileReader.close();
        return appartaments;
    }
}
