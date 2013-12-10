(ns name-inverter.core-test
  (:require [clojure.test :refer :all]
            [name-inverter.core :refer :all]))

(defn invert-name [name]
  (if (nil? name) "" name))

(defn assert-inverted [name inverted]
  (is (= (invert-name name) inverted)))

(deftest invert-name-test
  (testing "simple name"
    (assert-inverted "Piotr" "Piotr"))
  (testing "null name"
    (assert-inverted nil "")))
