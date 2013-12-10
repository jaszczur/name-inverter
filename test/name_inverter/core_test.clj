(ns name-inverter.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [name-inverter.core :refer :all]))

(defn ommit-honorifics [[first & rest :as names]]
  (if (some (partial = first) ["Mr." "Mrs."]) rest names))

(defn invert-name [name]
  (if (nil? name)
    ""
    (let [names (str/split (str/trim name) #"\s+")
          names (ommit-honorifics names)
          [first last & post-nominals] names]
      (if (= (count names) 1)
        first
        (str/trim (format "%s, %s %s" last first (apply str (interpose " " post-nominals))))))))



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
    (assert-inverted "  John   Smith" "Smith, John"))
  (testing "should ignore honorifics"
    (assert-inverted "Mr. John Smith" "Smith, John")
    (assert-inverted "Mrs. Barbara Smith" "Smith, Barbara"))
  (testing "post nominals should stay at the end"
    (assert-inverted "John Smith Jr." "Smith, John Jr.")))
