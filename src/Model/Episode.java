package Model;

public class Episode {
    boolean watched = false;
    int episodeNumber;

    public Episode(int episodeNumber)
    {
        this.episodeNumber = episodeNumber;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }


}
