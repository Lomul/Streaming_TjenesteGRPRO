package Model;

import java.util.*;

public class Season {
    int seasonNumber;
    List<Episode> episodes;

    public Season(int seasonNumber)
    {
        this.seasonNumber = seasonNumber;
        episodes = new ArrayList<>();
    }

    public void addEpisode(Episode episode)
    {
        episodes.add(episode);
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
