package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Producer implements Serializable {
    private String name;
    private String country;

    @Override
    public String toString() {
        return name + ", " + country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        return (Objects.equals(name, producer.name));
        // Припустимо що у нас не може бути варіанту коли Виробники
        // мають однакову назву, навіть якщо вони з різних країн
        // Таким чином Назва Виробника стає його унікальним значенням і
        //  лише назви достатньо для порівняння в equals()
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}