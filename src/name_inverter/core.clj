(ns name-inverter.core
  (:require [clojure.string :as str]))

(defn- ommit-honorifics [[first & rest :as names]]
  (if (some (partial = first) ["Mr." "Mrs."]) rest names))

(defn- join-with-space [rest]
  (apply str (interpose " " rest)))

(defn- format-multi-element-name [[first last & post-nominals]]
  (str/trim (format "%s, %s %s" last first (join-with-space post-nominals))))

(defn- split-name [name]
  (str/split (str/trim name) #"\s+"))

(defn- format-name [name]
  (let [names (ommit-honorifics (split-name name))]
      (if (= (count names) 1)
        (nth names 0)
        (format-multi-element-name names))))

(defn invert-name [name]
  (if (empty? name)
    ""
    (format-name name)))

