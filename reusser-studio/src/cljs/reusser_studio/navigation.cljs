(ns reusser-studio.navigation
  (:require [reusser-studio.constants :refer [target-image amazon-image bed-bath-and-beyond-image]]))

(defn content-home []

  [:div.d-flex.justify-content-center.container
   [:div.row
    [:a {:href "https://photos.google.com/share/AF1QipO6h25oVzLblII0zSgTDeNh66Jy5Li4iH2fPxcbesuL3X3MLt43gV12pT2gbq7OPA?key=NTlzV2pIUEI2M1M4dEpaM0JOQ2l1ajVNbXNOZC1n&source=ctrlq.org"
         :target "_blank"}
     [:img.rounded-r.image-fluid.border-rust {:src "images/hogwarts.jpg"}]]]])

(defn go-home [application-state]
  #(swap! application-state assoc :to-display content-home))

(defn content-registry []
  [:div.d-flex.justify-content-center.container
   [:div.row
    (let [registries
          [["Target"
            "https://www.target.com/gift-registry/giftgiver?registryId=9dc799e639c146fb81f6f6753cd5b3f6&type=WEDDING" target-image]
           ["Amazon"
            "http://amazon.com/wedding/share/mcfarland-reusser-registry"
            amazon-image]
           ["BB&B"
            "https://www.bedbathandbeyond.com/store/giftregistry/viewregistryguest/548662564?eventType=Wedding"
            bed-bath-and-beyond-image]]]
      (map (fn [[name link image]]  [:a.text-center.border-rust.rounded-r.p-5.m-2.background-white
                                     {:key name
                                      :href link
                                      :target "_blank"}
                                     [:div [:div.h1 name] [:img {:src image}]]]) registries))]])

(defn go-registry [application-state]
  #(swap! application-state assoc :to-display content-registry))

(defn content-schedule-events []
  [:div.h3.p-3.d-flex.justify-content-around.flex-fill.flex-column.align-items-center
   [:h1 "All times in EST"]
   [:ul
    [:li "4:30pm - Ceremony Begins"]
    [:li "5:30pm - Hors d'oeuvres and board games while the wedding party takes pictures"]
    [:li "6:00pm - Dinner laid out; buffet!"]
    [:li "7:30pm - Dancing!"]
    [:li "9:00pm - Bride and Groom head out; Grand Canyon here we come!"]]])

(defn go-schedule [application-state]
  #(swap! application-state assoc :to-display content-schedule-events))

(defn content-wedding-party []
  [:div.h3.p-3
   [:div.d-flex.justify-content-around.flex-fill.row.pl-5
    [:div.h1.text-ivy.col-lg-6.col-sm-12 "Bridal Party"
     [:ol
      [:li "Kendra " [:span "M" [:sup "c"] "Farland (Yes; she's taking the Reusser name 😏)"]]
      [:li "Kelly Marcum"]
      [:li "Ayriole Frost"]
      [:li "Justin Pratt"]]]
    [:div..h1.text-rust.col-lg-6.col-sm-12 "Groomal Party"
     [:ol
      [:li "Jake Reusser"]
      [:li "Ben Reusser"]
      [:li "Helen Reusser"]
      [:li "Dylan Latimer"]]]]])

(defn go-wedding-party [application-state]
  #(swap! application-state assoc :to-display content-wedding-party))
