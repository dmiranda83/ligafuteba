package br.com.ligafuteba.utils;

public class CustomErrorType {
    private String errorMessage;

    public CustomErrorType(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
