(ns claudio.id3
  (:require [clojure.java.io :as io]
            [clojure.string :as string])
  (:import org.jaudiotagger.audio.AudioFileIO
           org.jaudiotagger.tag.FieldKey))

(def field-keys
  (let [constants (.getEnumConstants org.jaudiotagger.tag.FieldKey)]
    (map #(.name %) constants)))

(defn ->constant [k]
  (.get (.getField org.jaudiotagger.tag.FieldKey k) nil))

(defn keywordify [k]
  (keyword (string/replace (string/lower-case k) #"_" "-")))

(defn constantify [k]
  (string/replace (string/upper-case (name k)) #"-" "_"))

(defn retrieve-field [tag k]
  (let [v (.getFirst tag (->constant k))]
    (when-not (empty? v)
      (vector (keywordify k) v))))

;;
;; API
;;
(defn read-tag
  "Given an mp3 File f, returns a map of ID3 tag or nil if none exists"
  [f]
  (when-let [tag (.getTag (org.jaudiotagger.audio.AudioFileIO/read f))]
    (into {} (remove nil? (map (partial retrieve-field tag) field-keys)))))

(defn write-tag!
  "Given a File f and fieldkey-string pairs kvs, modifies the file with the new value.
Field keys can be given Clojure-style, as lower-case, hyphenated keywords, or as
upper-case strings. Keys must correspond to a valid field key category or an
exception will be thrown.

To delete a field key, provide nil as its corresponding value.

Returns the updated tag map."
  [f & kvs]
  (when-not (empty? kvs)
    (when-not (even? (count kvs))
      (throw (Exception. "Tag-value pairs must an even number")))
    (let [audio-file (org.jaudiotagger.audio.AudioFileIO/read f)]
      (when-let [tag (.getTag audio-file)]
        (doseq [[k v] (partition 2 kvs)]
          (let [field-key (->constant (or (and (keyword? k) (constantify k)) k))]
            (if v
              (.setField tag field-key v)
              (.deleteField tag field-key))))
        (.commit audio-file)
        (read-tag f)))))

