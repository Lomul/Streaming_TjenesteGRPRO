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
}
