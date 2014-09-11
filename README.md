# claudio

Read and write textual data to/from MP3 ID3 tags with Clojure

(This is just a basic Clojure wrapper to simplify working with 
[JAudiotagger](http://www.jthink.net/jaudiotagger/index.jsp))

```clojure
[claudio "0.1.2"]
```

## Usage

### Setup

```clojure
(require 'clojure.java.io)
(require '[claudio.id3 :as id3])

(def some-mp3 (clojure.java.io/file "/path/to/track.mp3"))
```

### Reading an ID3 tag

```clojure
(id3/read-tag some-mp3)
```

### Writing an ID3 tag

One or more fieldkey/value pairs can be given. Values must be either
strings or nil (in which case, the field key will be removed from ID3 tag).

```clojure
(id3/write-tag! some-mp3
                :title "Something Else"
                :year  "1958"
                :genre nil)
```

#### Available MP3 fields

```clojure
user> (ns claudio.id3)
claudio.id3> field-keys
("ALBUM" "ALBUM_ARTIST" "ALBUM_ARTIST_SORT" "ALBUM_SORT" "AMAZON_ID" "ARTIST" "ARTIST_SORT" "BARCODE" "BPM" "CATALOG_NO" "COMMENT" "COMPOSER" "COMPOSER_SORT" "CONDUCTOR" "COVER_ART" "CUSTOM1" "CUSTOM2" "CUSTOM3" "CUSTOM4" "CUSTOM5" "DISC_NO" "DISC_TOTAL" "ENCODER" "FBPM" "GENRE" "GROUPING" "ISRC" "IS_COMPILATION" "KEY" "LANGUAGE" "LYRICIST" "LYRICS" "MEDIA" "MOOD" "MUSICBRAINZ_ARTISTID" "MUSICBRAINZ_DISC_ID" "MUSICBRAINZ_RELEASEARTISTID" "MUSICBRAINZ_RELEASEID" "MUSICBRAINZ_RELEASE_COUNTRY" "MUSICBRAINZ_RELEASE_GROUP_ID" "MUSICBRAINZ_RELEASE_STATUS" "MUSICBRAINZ_RELEASE_TYPE" "MUSICBRAINZ_TRACK_ID" "MUSICBRAINZ_WORK_ID" "MUSICIP_ID" "OCCASION" "ORIGINAL_ALBUM" "ORIGINAL_ARTIST" "ORIGINAL_LYRICIST" "ORIGINAL_YEAR" "QUALITY" "RATING" "RECORD_LABEL" "REMIXER" "SCRIPT" "TAGS" "TEMPO" "TITLE" "TITLE_SORT" "TRACK" "TRACK_TOTAL" "URL_DISCOGS_ARTIST_SITE" "URL_DISCOGS_RELEASE_SITE" "URL_LYRICS_SITE" "URL_OFFICIAL_ARTIST_SITE" "URL_OFFICIAL_RELEASE_SITE" "URL_WIKIPEDIA_ARTIST_SITE" "URL_WIKIPEDIA_RELEASE_SITE" "YEAR" "ENGINEER" "PRODUCER" "DJMIXER" "MIXER" "ARRANGER")
```


### Silencing JAudiotagger's verbose debugging

This will keep tag reading/writing from flooding your Emacs nREPL server
buffer:

```clojure
(.setLevel (java.util.logging.Logger/getLogger "org.jaudiotagger")
           java.util.logging.Level/OFF)
```

## License

Copyright © 2013 Murphy McMahon

Distributed under the Eclipse Public License, the same as Clojure.
