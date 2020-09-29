(ns claire.domain.tag.category
  (:require
   [claire.center.db :as db]))

(defn schema []
  (str "create table if not exists "
       "category ("
       "name text primary key, "
       "class text not null)"))

(defn set-db! []
  (db/execute! (schema))
  (println "<category> table is set up."))

;;; MOCKING
(defn set-mock! []
  (let [data
        [{:name "Ease" :class "A"}
         {:name "Hedge" :class "A"}
         {:name "Specuation" :class "A"}
         {:name "America" :class "B"}
         {:name "Europe" :class "B"}
         {:name "Asia" :class "B"}
         {:name "Rider" :class "C"}
         {:name "Non-Rider" :class "C"}
         {:name "Biker" :class "C"}
         {:name "Rock" :class "D"}
         {:name "Hiphop" :class "D"}
         {:name "Classic" :class "D"}
         {:name "Basketball" :class "E"}
         {:name "Football" :class "E"}
         {:name "Golf" :class "E"}
         {:name "Violin" :class "F"}
         {:name "Piano" :class "F"}
         {:name "Flute" :class "F"}
         {:name "Math" :class "G"}
         {:name "Science" :class "G"}
         {:name "English" :class "G"}]]
    (db/insert! :category data)))
