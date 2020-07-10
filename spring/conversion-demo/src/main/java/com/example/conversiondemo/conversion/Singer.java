package com.example.conversiondemo.conversion;

import lombok.Data;
import org.joda.time.DateTime;

import java.net.URL;
import java.text.SimpleDateFormat;

@Data
public class Singer {
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private URL personalSite;

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("{First Name: %s, Last Name: %s, Birthday: %s, Site: %s}",
                firstName, lastName, simpleDateFormat.format(birthDate.toDate()), personalSite);
    }
}
