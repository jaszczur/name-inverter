(ns name-inverter.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [name-inverter.core :refer :all]))

(defn invert-name [name]
  (if (nil? name)
    ""
    (let [splitted (str/split (str/trim name) #"\s+")]
      (if (= (count splitted) 1)
        (nth splitted 0)
        (str (nth splitted 1) ", " (nth splitted 0))))))



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
    (assert-inverted " Piotr " "Piotr"))
  (testing "given First Last should return Last, First"
    (assert-inverted "John Smith" "Smith, John"))
  (testing "given FL with spaces should return LF w/o spaces"
    (assert-inverted "  John   Smith" "Smith, John")))
