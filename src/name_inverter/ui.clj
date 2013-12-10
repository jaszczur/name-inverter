(ns name-inverter.ui
  (:gen-class)
  (:require [name-inverter.core :as nic]))

(defn map-names [& names]
  (map nic/invert-name names))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (interpose "\n"
                      (map-names
                       "Mr. John Smith Jr"
                       "Mrs. Teresa Hawk"
                       "Mrs. Hannah Buczynsky Phd."))))

