package com.harrison.study.stream;

import com.harrison.study.general.Album;
import com.harrison.study.general.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Exercises {
    /**
     * 测试求和函数
     */
    @Test
    public void test1(){
        Integer sum = add(Stream.of(1, 5, 6, 10, 55));
        System.out.println(sum);
    }

    public Integer add(Stream<Integer> stream){
        return stream.reduce((x, y) -> x + y).get();
    }

    //测试 参数：艺术家列表 返回：艺术家的国籍和姓名
    @Test
    public void test2(){
        List<Artist> artists = Arrays.asList(new Artist("Beyond", "中国"),
                new Artist("Backstreet Boys", "美国"),
                new Artist("HKT","越南"));
        List<String> artistNameAndCountry = getArtistNameAndCountry(artists);
        System.out.println(artistNameAndCountry);

        List<String> namesAndOrigins = getNamesAndOrigins(artists);
        System.out.println(namesAndOrigins);
    }

    public List<String> getArtistNameAndCountry(List<Artist> artists){
        return artists.stream()
                .map(artist -> "乐队名:" + artist.getName() + " 国籍:" + artist.getNationality())
                .collect(Collectors.toList());
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
                .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
                .collect(toList());
    }

    /**
     * 测试3 参数：专辑列表 返回:专辑中曲目最多包含3首的专辑列表
     */
    @Test
    public void test3(){
        //测试getAlbum()函数
    }

    public List<Album> getAlbum(List<Album> albums){
        return albums.stream().filter(album -> album.getTrackList().size() <= 3).collect(Collectors.toList());
    }

    /**
     * 比较重构方法
     */
    @Test
    public void test4(){
        List<Artist> artists = Arrays.asList(new Artist("1","1"),
                new Artist("2","2"),
                new Artist("3","3"));
        List<Artist> artists2 = Arrays.asList(new Artist("4","4"),
                new Artist("5","5"),
                new Artist("6","6"));
        Artist artist = new Artist("11", artists, "11");
        Artist artist1 = new Artist("12", artists2, "12");
        List<Artist> list = Arrays.asList(artist, artist1);
        Assertions.assertEquals(getTotalNumbers(list), getAllNumbers(list));
    }
    public Long getTotalNumbers(List<Artist> artists){
        Long totalMembers = 0L;
        for (Artist artist : artists){
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
        return totalMembers;
    }

    public Long getAllNumbers(List<Artist> artists){
        return artists.stream().flatMap(artist -> artist.getMembers()).count();
    }

    @Test
    public void test5() {
        String s = "asSasf";
        long count = s.chars().filter(Character::isLowerCase).count();
        System.out.println(count);
    }
}
