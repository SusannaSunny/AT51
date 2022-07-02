package ru.netology.cardFormDate;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DataForUsers {
    private final String forCity;
    private final String forDate;
    private final String forName;
    private final String forPhone;

}
