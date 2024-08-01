package brute;

import java.util.Arrays;

public class NumberOfSeniorCitizens {
    public int countSeniors(String[] details) {
        return (int) Arrays.stream(details)
                .map(s -> Integer.parseInt(s.substring(11, 13)))
                .filter(age -> age > 60).count();
    }

    public static void main(String[] args) {
        String[] details = new String[] {
                "7868190130M7522","5303914400F9211","9273338290F4010"
        };
        NumberOfSeniorCitizens numberOfSeniorCitizens = new NumberOfSeniorCitizens();
        System.out.println(numberOfSeniorCitizens.countSeniors(details));
    }
}
