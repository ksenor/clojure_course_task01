(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

(defn just-vector [a]
    (filter vector? a))


(defn just-tag
  [a]
  (filter
        (fn [y] (= ':a (first y)))
        (just-vector a)))


(defn just-link [a]
  (map (fn [y] (:href (second y)))
       (just-tag a)))


(defn vector-data
  [a]
  (if (and (= ':h3 (first a))
  (= (:class (second a)) "r"))
    (just-link a)
    (into [] (mapcat vector-data (just-vector a)))))

(defn get-links []
" 1) Find all elements containing {:class \"r\"}.

Example:
[:h3 {:class \"r\"} [:a {:shape \"rect\", :class \"l\",
                         :href \"https://github.com/clojure/clojure\",
                         :onmousedown \"return rwt(this,'','','','4','AFQjCNFlSngH8Q4cB8TMqb710dD6ZkDSJg','','0CFYQFjAD','','',event)\"}
                     [:em {} \"clojure\"] \"/\" [:em {} \"clojure\"] \" · GitHub\"]]

   2) Extract href from the element :a.

The link from the example above is 'https://github.com/clojure/clojure'.

  3) Return vector of all 10 links.

Example: ['https://github.com/clojure/clojure', 'http://clojure.com/', . . .]
"
  (let [data (parse "clojure_google.html")]
    (vector-data data)))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))

(-main)