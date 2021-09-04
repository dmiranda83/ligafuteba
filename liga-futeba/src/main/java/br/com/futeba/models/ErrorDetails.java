package br.com.futeba.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private String title;
    private Long status;
    private Long timestamp;
    private String message;
}
