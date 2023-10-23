package org.example;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@Builder
public class Producer implements Serializable {
    private String name;
    private String country;
}