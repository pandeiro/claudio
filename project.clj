(defproject claudio "0.1.3"
  :description "A simple utility for reading and writing textual ID3 tag data"
  :url "https://github.com/pandeiro/claudio"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"jaudiotagger-repository"
                 {:url "https://dl.bintray.com/ijabz/maven"}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [net.jthink/jaudiotagger "2.2.5"]])
