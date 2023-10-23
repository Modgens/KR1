package org.example;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@ToString
public class Souvenir implements Serializable {
    private String name;
    private Producer producer;
    private LocalDate releaseDate;
    private double price;

    public void save(Souvenir souvenir) {
        this.name = souvenir.getName();
        this.producer = souvenir.getProducer();
        this.releaseDate = souvenir.getReleaseDate();
        this.price = souvenir.getPrice();
    }
}
