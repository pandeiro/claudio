# claudio

Read and write textual data to/from MP3 ID3 tags with Clojure

(This is just a basic Clojure wrapper to simplify working with 
[JAudiotagger](http://www.jthink.net/jaudiotagger/index.jsp))

```clojure
[claudio "0.1.1"]
```

## Usage

### Setup

```clojure
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

## License

Copyright © 2013 Murphy McMahon

Distributed under the Eclipse Public License, the same as Clojure.
