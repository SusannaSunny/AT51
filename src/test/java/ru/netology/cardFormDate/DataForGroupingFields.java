package ru.netology.cardFormDate;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataForGroupingFields {

public static DataForUsers DataUsers(int newDate) {
String forCity = createCity();
String forDate = createDate(newDate);
String forName = createName();
String forPhone = createPhone();
DataForUsers dataU = new DataForUsers(forCity, forDate, forName, forPhone);
return dataU;
}

public static String createCity() {

    String[] listOfCities = new String[]{"Москва","Санкт-Петербург","Кемерово","Смоленск","Тамбов","Ростов-на-Дону","Астрахань"};
    Faker faker = new Faker();
    int fCity = faker.number().numberBetween(0, listOfCities.length - 1);
    String forCity = listOfCities[fCity];
    return forCity;
}
    public static String createDate(int newDate) {
    String date = LocalDate.now().plusDays(newDate).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    return date;
}

public static String createName() {
    Faker faker = new Faker(new Locale("ru"));
    String forName = faker.name().firstName() + " " + faker.name().lastName();
    return forName;
}

public static String createPhone() {
    Faker faker = new Faker(new Locale("ru"));
    String forPhone = faker.phoneNumber().phoneNumber();
    return forPhone;
}

}
