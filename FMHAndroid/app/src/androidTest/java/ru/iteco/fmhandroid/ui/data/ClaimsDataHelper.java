package ru.iteco.fmhandroid.ui.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClaimsDataHelper {

    public static String generateDate(int years) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static class ClaimsInfo {
        private String textField;
        private String date;

        public ClaimsInfo(String textField, String date) {
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


    public static ClaimsInfo getInfoForClaimCreation(int years) {
        return new ClaimsInfo("Новая заявка №775", generateDate(years));
    }

    public static ClaimsInfo getInfoForClaimEditing(int years) {
        return new ClaimsInfo("Claim!@#$%^&*(){}:+=?", generateDate(years));
    }

    public static ClaimsInfo getInfoForExecutedStatus(int years) {
        return new ClaimsInfo("NeW ClAiM No.12345", generateDate(years));
    }

    public static ClaimsInfo getInfoForCancelledStatus(int years) {
        return new ClaimsInfo("New cancelled claim 2", generateDate(years));
    }

    public static ClaimsInfo getInfoForClaimWithComment(int years) {
        return new ClaimsInfo("Create claim with comment 20", generateDate(years));
    }

    public static class CommentsInfo {
        private String commentText;

        public CommentsInfo(String commentText) {
            this.commentText = commentText;
        }

        public String getCommentText() {
            return commentText;
        }
    }

    public static CommentsInfo getFirstComment() {
        return new CommentsInfo("Комментарий №1123");
    }

    public static CommentsInfo getSecondComment() {
        return new CommentsInfo("New comment");
    }

    public static CommentsInfo getThirdComment() {
        return new CommentsInfo("!@#$%^&*(){}:");
    }

    public static CommentsInfo getCommentForNewClaim() {
        return new CommentsInfo("Ё");
    }
}
