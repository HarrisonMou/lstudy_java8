package com.harrison.study.general;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static java.util.Arrays.asList;

public class SampleData {
    public static final Artist johnColtrane = new Artist("John Coltrane", "US");

    public static final Artist johnLennon = new Artist("John Lennon", "UK");
    public static final Artist paulMcCartney = new Artist("Paul McCartney", "UK");
    public static final Artist georgeHarrison = new Artist("George Harrison", "UK");
    public static final Artist ringoStarr = new Artist("Ringo Starr", "UK");

    public static final List<Artist> fourMenbers = Arrays.asList(johnLennon, paulMcCartney, georgeHarrison, ringoStarr);

    public static final List<Artist> threeMenbers = Arrays.asList(johnLennon, paulMcCartney, georgeHarrison);

    public static final List<Artist> anotherThreeMenbers = Arrays.asList(ringoStarr, paulMcCartney, georgeHarrison);

    public static final List<Artist> twoMembers = Arrays.asList(johnLennon, paulMcCartney);

    public static final Artist fourMenberBeatles= new Artist("The Beatles with four menbers", fourMenbers, "USA");

    public static final Artist threeMenberBeatles= new Artist("The Beatles with three menbers", threeMenbers, "UK");

    public static final Artist twoMenberBeatles = new Artist("The Beatles with two menbers", twoMembers, "CN");

    public static final Artist anotherThreeMenberBeatles = new Artist("The Beatles with three menbers （another）", anotherThreeMenbers, "PK");

    public static final Album aLoveSupreme = new Album("A Love Supreme", asList(new Track("Acknowledgement", 467), new Track("Resolution", 442)), asList(johnColtrane));

    public static final Album sampleShortAlbum = new Album("sample Short Album", asList(new Track("short track", 30)), asList(johnColtrane));

    public static final Album manyTrackAlbum = new Album("sample Short Album", asList(new Track("short track", 30), new Track("short track 2", 30), new Track("short track 3", 30), new Track("short track 4", 30), new Track("short track 5", 30)), asList(johnColtrane));

    public static Stream<Album> albums = Stream.of(aLoveSupreme);

    public static Stream<Artist> threeArtists() {
        return Stream.of(johnColtrane, johnLennon, fourMenberBeatles);
    }

    public static List<Artist> getThreeArtists() {
        return Arrays.asList(johnColtrane, johnLennon, fourMenberBeatles);
    }
}
