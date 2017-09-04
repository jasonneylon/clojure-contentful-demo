(ns clojure-contentful-demo.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure-contentful-demo.views :as views]
            [clojure-contentful-demo.content :as content]
            [compojure.handler :refer [site]]
            [ring.adapter.jetty :as jetty]
						[environ.core :refer [env]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] 
      (let [products (content/get-products)] 
        (views/products products)))
  (GET "/products/:slug" [slug] 
      (let [product (content/get-product slug)] 
        (views/product product)))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main  [&  [port]]                                                                
  (let  [port  (Integer.  (or port (env :port) 5000))]                                   
		(jetty/run-jetty  (site #'app)  {:port port :join? false}))) 
