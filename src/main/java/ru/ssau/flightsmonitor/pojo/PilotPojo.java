package ru.ssau.flightsmonitor.pojo;

import lombok.Data;
import ru.ssau.flightsmonitor.entity.Card;
import ru.ssau.flightsmonitor.entity.Pilot;

@Data
public class PilotPojo {
    private Long id;
    private String name;

    public static PilotPojo fromEntity(Pilot pilot) {
        PilotPojo pojo = new PilotPojo();

        pojo.setId(pilot.getId());
        pojo.setName(pilot.getName());

        return pojo;
    }

    public static Pilot toEntity(PilotPojo pojo) {
        Pilot pilot = new Pilot();

        pilot.setId(pojo.getId());
        pilot.setName(pojo.getName());

        return pilot;
    }
}
