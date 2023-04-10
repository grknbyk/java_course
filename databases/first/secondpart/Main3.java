package secondpart;

import java.sql.SQLException;
import java.util.List;

import secondpart.model.Artist;
import secondpart.model.Datasource;
import secondpart.model.SongArtist;

public class Main3 {
    public static void main(String[] args) throws SQLException {
        Datasource datasource = new Datasource();
        if (!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }

        List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_ASC);
        if (artists == null) {
            System.out.println("No artists!");
            return;
        } else {
            for (Artist artist : artists) {
                System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
            }
        }

        List<String> albumsForArtist = datasource.queryAlbumsForArtist("Carole King", Datasource.ORDER_BY_DESC);
        if (albumsForArtist == null) {
            System.out.println("No albums for artist");
            return;
        } else {
            for (String album : albumsForArtist) {
                System.out.println(album);
            }
        }

        List<SongArtist> songArtists = datasource.queryArtistsForSong("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songArtists == null) {
            System.out.println("Couldn't find the artist for the song");
            return;
        } else {
            for (SongArtist artist : songArtists) {
                System.out.println("Artist = " + artist.getArtistName() +
                        ", Album = " + artist.getAlbumName() +
                        ", Track = " + artist.getTrack());
            }
        }

        datasource.querySongsMetadata();

        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("Number of songs is: " + count);

        datasource.createViewForSongArtists();

        songArtists = datasource.querySongInfoView("Go Your Own Way");
        if (songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song");
            return;
        } else {
            for (SongArtist artist : songArtists) {
                System.out.println("FROM VIEW - Artist name = " + artist.getArtistName() +
                        ", Album = " + artist.getAlbumName() +
                        ", Track = " + artist.getTrack());
            }
        }
        
        // SELECT name, album, track FROM artist_list
        // WHERE title = "Go Your Own Way" or 1=1 or "";
        
        // SELECT name, album, track FROM artist_list WHERE title = ? OR artist = ?; two placeholders
        // SELECT name, album, track FROM artist_list WHERE title = ?;
        
        // SELECT name, album, track FROM artist_list
        // WHERE title = "Go Your Own Way" or 1=1 or "";
        
        // ? equals to "Go Your Own Way" or 1=1 or "" meanly,
        // it compares the title entirely equals to Go Your Own Way" or 1=1 or "
        
        
        datasource.insertSong("Dancing Queen", "ABBA", "Arrival", 1);

       
        
        datasource.close();

    }

}
