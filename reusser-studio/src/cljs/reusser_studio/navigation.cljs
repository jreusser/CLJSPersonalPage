(ns reusser-studio.navigation
  (:require [reusser-studio.constants :refer [target-image amazon-image]]))


(defn content-home []
  [:div.col-sm-12.text-center
   [:div "Welcome to the McFarland Reusser Wedding Landing Page"]
   [:img.rounded.image-fluid {:src "images/hogwarts.jpg"}]])

(defn go-home [application-state]
  #(swap! application-state assoc :to-display content-home))

(defn content-guest-list []
  [:div.col-sm-12.text-center "Please sign the Guest List :D"])

(defn go-guest-list [application-state]
  #(swap! application-state assoc :to-display content-guest-list))

(defn content-registry []
  (let [registries
        [["Target"
          "https://www.target.com/gift-registry/giftgiver?registryId=4f2e663546804f79b47fdb7b55d152e0&type=GENERIC&occasionType=HOUSEWARMING" target-image]
         ["Amazon"
          "http://amazon.com/wedding/share/mcfarland-reusser-registry"
          amazon-image]]]

    (map (fn [[name link image]] [:a.col-sm-12.text-center
                                  {:key name
                                   :href link
                                   :target "_blank"}
                                  [:div [:div name] [:img {:src image}]]]) registries)))

(defn go-registry [application-state]
  #(swap! application-state assoc :to-display content-registry))

(defn go-request-song [application-state])
(defn go-schedule [application-state])

