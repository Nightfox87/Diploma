package ru.iteco.fmhandroid.ui.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewsDataHelper {
    public static String generateDate(int years) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static class NewsInfo {
        private String textField;
        private String date;

        public NewsInfo(String textField, String date) {
            this.textField = textField;
            this.date = date;
        }

        public String getTextField() {
            return textField;
        }

        public String getDate() {
            return date;
        }
    }

    public static NewsInfo getFirstNewsData(int years) {
        return new NewsInfo("Праздник 1 мая", generateDate(years));
    }

    public static NewsInfo getNewsDataForCancelCreation(int years) {
        return new NewsInfo("1st May Holiday", generateDate(years));
    }

    public static NewsInfo getDataForNewsEditing(int years) {
        return new NewsInfo("News of today", generateDate(years));
    }

    public static NewsInfo getDataForNewsToDelete(int years) {
        return new NewsInfo("June+July!@#$%^&*()±§{}[]:|<>?", generateDate(years));
    }

    public static NewsInfo getDataForNewsWithPastDate(int years) {
        return new NewsInfo("Ё", generateDate(years));
    }

    public static class FilterInfo {
        private String category;
        private String startDate;
        private String endDate;

        public FilterInfo(String category, String startDate, String endDate) {
            this.category = category;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getCategory() {
            return category;
        }

        public String getStartDate() {
            return startDate;
        }

        public String getEndDate() {
            return endDate;
        }
    }

    public static FilterInfo getFilterInfoWithDatesAndEmptyCategory() {
        return new FilterInfo("", "20.05.2023", "23.05.2023");
    }

    public static FilterInfo getFilterInfoWithNonExistingCategory() {
        return new FilterInfo("Профсоюз №1", "20.05.2023", "23.05.2023");
    }
}
