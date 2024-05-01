package ru.ssau.flightsmonitor.pojo;

import lombok.Data;
import ru.ssau.flightsmonitor.entity.Card;
import ru.ssau.flightsmonitor.entity.Video;

@Data
public class VideoPojo {
    private Long id;
    private String name;
    private String filePath;

    public static VideoPojo fromEntity(Video video) {
        VideoPojo pojo = new VideoPojo();

        pojo.setId(video.getId());
        pojo.setName(video.getName());
        pojo.setFilePath(video.getFilePath());

        return pojo;
    }

    public static Video toEntity(VideoPojo pojo) {
        Video video = new Video();

        video.setId(pojo.getId());
        video.setName(pojo.getName());
        video.setFilePath(pojo.getFilePath());

        return video;
    }
}
