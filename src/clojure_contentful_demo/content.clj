(ns clojure-contentful-demo.content
  (:require [org.httpkit.client :as http]
						[clojure.string :as s]
            [cheshire.core :refer [parse-string]]))

(def space-id "ic8pqmnpu6nu")
(def access-token "c6f70ac17e67187fa3a1758e59b13ed84c41f7a43c11585cdb0f0d0b6ad5a0c0")
(def base-url "https://cdn.contentful.com/spaces/")
(def options {:headers {"Authorization" (str "Bearer " access-token)}})

(defn- map->query-string [m]
	(s/join "&" (map (fn [[k v]] (str (s/replace (name k) "-" "_") "=" v) ) m)))

(defn- get-id [sys-map]
  (get-in sys-map [:sys :id]))

(defn- get-image-url [entries fields]
  (let [image-id (get-id (first (:image fields)))]
   (-> entries
      (get-in [:includes :Asset])
      ((partial filter #(= (get-id %) image-id)))
      first
      (get-in [:fields :file :url]))))

(defn- entries->product [entries]
	(->> entries
			 :items
			 (map :fields)
			 (map #(assoc % :image-url (get-image-url entries %)))))

(defn get-entries [query]
  (let [url (str base-url space-id "/entries/?" (map->query-string query))
        response @(http/get url options)]
    (parse-string (:body response) true)))

(defn get-products []
  (let [entries (get-entries {:content-type "2PqfXUJwE8qSYKuM0U6w8M"}) ]
    (entries->product entries)))

(defn get-product [slug]
  (let [entries (get-entries {:content-type "2PqfXUJwE8qSYKuM0U6w8M" :fields.slug slug}) ]
    (first (entries->product entries))))

