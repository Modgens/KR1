package org.example;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class Producer implements Serializable {
    private String name;
    private String country;

    @Override
    public String toString() {
        return name + ", " + country;
    }
}