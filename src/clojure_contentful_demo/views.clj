(ns clojure-contentful-demo.views
  (:require [hiccup.core :refer :all]
            [hiccup.page :refer [html5]]))



(defn- navbar []
	[:nav
	 [:div.nav-wrapper.container
		[:a {:href "#" :class "brand-logo left"} "Contentful demo"]
		[:ul {:id "nav-mobile" :class "left hide-on-med-and-down"}]]])
 
(defn- page [content]
  (html5 
    [:head
		 [:title "Contentful Clojure Demo"]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1.0"}]
     [:link {:href"https://fonts.googleapis.com/icon?family=Material+Icons" :rel "stylesheet"}]
     [:link {:rel "stylesheet" :href "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"}] ]
    [:body
     (navbar)
     content 
     [:script {:src "https://code.jquery.com/jquery-2.1.1.min.js"}]
     [:script {:src "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"}]]))

(defn products [products]
  (html5 
    (page 
      [:div.container
       [:h1.header.center "Products"]
       [:div.row
         (for [{:keys [image-url productName productDescription price slug tags] :as product} products
							 :let [product-url (str "/products/" slug)]]
           [:div {:class "col s12 m6"}
           [:div.card
            [:div.card-image
						 [:a {:href product-url}
							[:img {:src (str image-url "?w=500")}]]]
            [:div.card-content
             [:a {:href product-url} [:h2.card-title.blue-text productName]]
             [:p productDescription]
             [:p
              [:span (str price " €")]]
             [:p
              [:h5 "Tags:"]
              (for [tag tags]
                [:div.chip tag])]]
            [:div.card-action
             [:a {:href product-url} "Read more"]]]])]])))

(defn product [{:keys [image-url productName productDescription price website sku sizetypecolor tags quantity] :as product}]
  (html5 
    (page 
      [:div.container
       [:div.row
				[:div {:class "col s12 m12"}
				 [:div.card.horizontal
					[:div.card-image
					 [:a {:href website}
						[:img {:src (str image-url "?w=800")}]]]
					[:div.card-stacked
					[:div.card-content
					 [:h2.card-title.blue-text productName]
					 [:p productDescription]
					 [:p sizetypecolor]
					 [:p (str quantity " in stock")]
					 [:p (str "SKU: " sku)]
					 [:p
						[:span (str price " €")]]
					 [:p
						[:h5 "Tags:"]
						(for [tag tags]
							[:div.chip tag])]]
					[:div.card-action
					 [:a {:href website} "Buy"]]]]]]])))

