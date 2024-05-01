package ru.ssau.flightsmonitor.pojo;

import lombok.Data;
import ru.ssau.flightsmonitor.entity.Card;
import ru.ssau.flightsmonitor.entity.Violation;

import java.time.LocalTime;

@Data
public class ViolationPojo {
    private Long id;
    private String name;
    private String description;
    private LocalTime time;

    public static ViolationPojo fromEntity(Violation violation) {
        ViolationPojo pojo = new ViolationPojo();

        pojo.setId(violation.getId());
        pojo.setName(violation.getName());
        pojo.setDescription(violation.getDescription());
        pojo.setTime(violation.getTime());

        return pojo;
    }

    public static Violation toEntity(ViolationPojo pojo) {
        Violation violation = new Violation();

        violation.setId(pojo.getId());
        violation.setName(pojo.getName());
        violation.setDescription(pojo.getDescription());
        violation.setTime(pojo.getTime());

        return violation;
    }
}
