(ns name-inverter.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [name-inverter.core :refer :all]))

(defn invert-name [name]
  (if (nil? name)
    ""
    (str/trim name)))

(defn assert-inverted [name inverted]
  (is (= (invert-name name) inverted)))

(deftest invert-name-test
  (testing "simple name"
    (assert-inverted "Piotr" "Piotr"))
  (testing "given null should return empty string"
    (assert-inverted nil ""))
  (testing "given empty string should return empty string"
    (assert-inverted "" ""))
  (testing "given simple name with spaces should return it w/o spaces"
    (assert-inverted " Piotr " "Piotr")))
