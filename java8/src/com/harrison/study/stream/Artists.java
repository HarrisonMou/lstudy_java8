package com.harrison.study.stream;

import com.harrison.study.general.Artist;

import java.util.List;
import java.util.Optional;

public class Artists {
    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public void indexException(int index){
        throw new IllegalArgumentException(index + "doesn't correspond to an Artist");
    }

    public Optional<Artist> getArtist(int index){
        if(index < 0 || index > artists.size()){
            indexException(index);
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistName(int index){
        Optional<Artist> artist = getArtist(index);
        return artist.map(Artist::getName).orElse("Unknown");
    }

    public static void main(String[] args) {

    }
}

