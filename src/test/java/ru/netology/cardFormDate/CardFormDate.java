package ru.netology.cardFormDate;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;

public class CardFormDate {

    SelenideElement formCard = $x("//form[contains(@class, form)]");
    SelenideElement notice = $x("//div[@data-test-id='success-notification']");
    SelenideElement replanNotice = $x("//div[@data-test-id='replan-notification']");

    @BeforeAll
    public static void setUpAll() {
        open("http://localhost:9999/");
    }

   // public String generateDate(int days) {
   //     return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
   // }
   // String date = generateDate(6);


    @Test
    public void SuccessfulFormSubmission() {
        DataForUsers users = DataForGroupingFields.DataUsers(8);

        formCard.$x(".//span[@data-test-id='city']//input").val(users.getForCity());
        formCard.$x(".//span[@data-test-id='date']//input").sendKeys(Keys.LEFT_CONTROL + "A");
        formCard.$x(".//span[@data-test-id='date']//input").sendKeys(Keys.BACK_SPACE);
        formCard.$x(".//span[@data-test-id='date']//input").val(users.getForDate());
        formCard.$x(".//span[@data-test-id='name']//input").val(users.getForName());
        formCard.$x(".//span[@data-test-id='phone']//input").val(users.getForPhone());
        formCard.$x(".//label[@data-test-id='agreement']").click();
        formCard.$x(".//span[contains(text(), 'Запланировать')]//ancestor::button").click();
        notice.should(Condition.visible, Duration.ofSeconds(1));
        notice.$x(".//div[@class='notification__title']").should(Condition.text("Успешно!"));
        notice.$x(".//div[@class='notification__content']").should(Condition.text("Встреча успешно запланирована на " + users.getForDate()));


        formCard.$x(".//span[@data-test-id='date']//input").sendKeys(Keys.LEFT_CONTROL + "A");
        formCard.$x(".//span[@data-test-id='date']//input").sendKeys(Keys.BACK_SPACE);
        formCard.$x(".//span[@data-test-id='date']//input").val(DataForGroupingFields.createDate(10));
        formCard.$x(".//span[contains(text(), 'Запланировать')]//ancestor::button").click();
        replanNotice.should(Condition.visible, Duration.ofSeconds(1));
        replanNotice.$x(".//span[contains(text(), 'Перепланировать')]//ancestor::button").click();
        notice.$x(".//div[@class='notification__title']").should(Condition.text("Успешно!"));
        notice.$x(".//div[@class='notification__content']").should(Condition.text("Встреча успешно запланирована на " + DataForGroupingFields.createDate(10)));

    }

}
