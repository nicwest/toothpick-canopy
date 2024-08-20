(ns cad.core
  (:require [clojure.java.io :refer [make-parents]]
            [scad-clj.scad :refer [write-scad]]
            [scad-clj.model :refer [cylinder translate union with-fn cube rotate
                                    difference color sphere intersection
                                    scale hull]])
  (:gen-class))

(def pcb
  [0.2 0.2 0.2])

(def metal
  [0.8 0.8 0.8])

(def camera-color
  [0 0 0])

(defn degrees [n] (* n (/ Math/PI 180)))

(defn render!
  [file-name part]
  (let [file-path (str "out/" file-name ".scad")]
    (make-parents file-path)
    (spit file-path
          (write-scad part))))

(defn config
  ([] (config {}))
  ([opts]
  (let [opts (merge {:pretty? false
                     :post-radius 0.75
                     :post-hole-radius 1.25
                     :post-length 18
                     :post-front-length 9
                     :mounting 25.5
                     :fc-height 6
                     :fc-thickness 1
                     :vtx-height 15
                     :vtx-thickness 1
                     :vtx-protrusion 2
                     :camera-base-width 14
                     :camera-base-height 14
                     :camera-base-thickness 2.5
                     :camera-lens-length 13
                     :camera-lens-radius 4.5
                     :camera-lens-hole-spacing 0.5
                     :camera-offset 8
                     :camera-angle (degrees 20)
                     :camera-angle-min (degrees 5)
                     :camera-angle-max (degrees 50)
                     :camera-height 16
                     :camera-side-mount-hole-radius 0.7
                     :canopy-thickness 1.5
                     :canopy-tab-radius 2.3
                     :canopy-tab-thickness 1.5
                     :canopy-camera-box-spacing 0.2
                     :canopy-camera-lens-spacing 0.2
                     :canopy-camera-side-mount-length 5
                     :canopy-camera-side-mount-thickness 0.9
                     :canopy-camera-side-mount-offset 3.5
                     :canopy-camera-side-mount-hole-radius 1
                     :hull-tab-thickness 4
                     :turret-length 6
                     :turret-height 10
                     :turret-thickness 2
                     :turret-mount-offset 4
                     :hook-outer-radius 2.75
                     :hook-inner-radius 1.75
                     :hook-thickness 5
                     :hook-offset 7
                     }
                    opts)]

    opts)))

(defn posts
  [post-radius {:keys [post-length post-front-length mounting]}]
  (with-fn 30
  (union
    (translate [0 0 (/ post-length 2)]
    (cylinder post-radius post-length))
  (translate [mounting mounting (/ post-length 2)]
    (cylinder post-radius post-length))
  (translate [0 mounting  (/ post-front-length 2)]
    (cylinder post-radius post-front-length))
  (translate [mounting 0 (/ post-length 2)]
    (cylinder post-radius post-length)))))

(defn fc-width
  [{:keys [mounting post-hole-radius]}]
    (+ mounting (* post-hole-radius 2) 1))

(defn flight-controller
  [{:keys [mounting fc-height fc-thickness post-hole-radius] :as cfg}]
  (let [fc-width (fc-width cfg)
        xy (/ mounting 2)]
    (color pcb
           (difference
             (translate [xy xy (+ fc-height (/ fc-thickness 2))]
                        (cube fc-width fc-width fc-thickness)
                        )

             (posts post-hole-radius cfg)
             ))))

(defn vtx
  [{:keys [mounting vtx-height vtx-thickness vtx-protrusion post-hole-radius] :as cfg}]
  (let [vtx-width (+ mounting (* post-hole-radius 2) 1)
        xy (/ mounting 2)
        cutter-size (* mounting 4)
        cutter-offset (Math/sqrt (* 2 (Math/pow vtx-protrusion 2)))
        cutter (cube cutter-size cutter-size cutter-size)]

    (color pcb
           (difference 
             (union
             (translate [xy xy (+ vtx-height (/ vtx-thickness 2))]
                        (cube vtx-width vtx-width vtx-thickness)))
             (posts post-hole-radius cfg)
             (rotate (degrees 45) [0 0 1]
                     (translate [0 (+ (/ cutter-size 2) cutter-offset) (/ cutter-size 2) ]
                                cutter))))))

(defn position-camera
  [{:keys [camera-offset camera-height camera-angle mounting]} x]
    (let [t1 (/ mounting 2)
          t2 (Math/sqrt (/ (Math/pow camera-offset 2) 2))]

    (translate [(- t1 t2) (+ t1 t2) camera-height]
    (rotate (degrees 45) [0 0 1]
            (rotate (+ (degrees -90) camera-angle) [1 0 0]
                    x)))))


(defn camera
  [{:keys [camera-base-width camera-base-height camera-base-thickness
           camera-lens-length camera-lens-radius]
    :as cfg}]

          (position-camera cfg
                  (union
                    (color pcb
                           (translate [0 0 (/ camera-base-thickness 2)]
                                      (cube camera-base-width camera-base-height camera-base-thickness)))
                    (color camera-color
                           (translate [0 0 (+ (/ camera-lens-length 2) camera-base-thickness)]
                                      (cylinder camera-lens-radius camera-lens-length))))))


(defn camera-cutter
  [{:keys [mounting camera-base-thickness camera-lens-length camera-lens-radius
           camera-offset camera-height camera-angle-max camera-angle-min camera-lens-hole-spacing]} x]

  (difference x
  (let [
        t1 (/ mounting 2)
        t2 (Math/sqrt (/ (Math/pow camera-offset 2) 2))]

    (with-fn 30
    (translate [(- t1 t2) (+ t1 t2) camera-height]
               (rotate (degrees 45) [0 0 1]
                       (apply hull
                         (map (fn [a]
                                (rotate (+ (degrees -90) a) [1 0 0]
                                          (translate [0 0 (+ (/ camera-lens-length 2) camera-base-thickness)]
                                                     (cylinder (+ camera-lens-radius camera-lens-hole-spacing) camera-lens-length))))
                         (range camera-angle-min camera-angle-max (/ (- camera-angle-max camera-angle-min) 10))))))))))

(defn canopy-tab
  [{:keys [canopy-tab-radius canopy-tab-thickness]}]
  (with-fn 30 
           (translate [0 0 (/ canopy-tab-thickness 2)]
                      (cylinder canopy-tab-radius canopy-tab-thickness))))

(defn simple-tabs
  [{:keys [mounting fc-height fc-thickness vtx-height vtx-thickness]
    :as cfg}]
  (let [tab (canopy-tab cfg)
        z1 (+ fc-height fc-thickness)
        z2 (+ vtx-height vtx-thickness)]
    [
     (translate [0 0 z1] tab)
     (translate [0 mounting z1] tab)
     (translate [mounting mounting z1] tab)
     (translate [0 0 z2] tab)
     (translate [mounting 0 z2] tab)
     (translate [mounting mounting z2] tab)
     ]
    )
  
  
  )

(defn canopy-camera-box-side
  [{:keys [camera-base-thickness canopy-thickness canopy-camera-box-spacing] :as cfg} side]
  (let [vert? (or (= side :top) (= side :bottom))
        flip? (or (= side :top) (= side :right))
        len (+ (if vert? (:camera-base-width cfg) (:camera-base-height cfg)) (* canopy-camera-box-spacing 2))
        trans-d (+ (/ len 2) (/ canopy-thickness 2) )
        trans-d (if flip? (- trans-d) trans-d)
        trans-x (if vert? 0 trans-d)
        trans-y (if vert? trans-d 0)
        cube-d (+ len (* canopy-thickness 2))
        cube-x (if vert? cube-d canopy-thickness)
        cube-y (if vert? canopy-thickness cube-d)
        ]
  (position-camera
    cfg
    (translate [trans-x trans-y (/ camera-base-thickness 2)]
    (cube cube-x cube-y  camera-base-thickness)))))

(defn hull-tab
  [{:keys [canopy-tab-radius canopy-tab-thickness hull-tab-thickness]
    :as cfg}]
  (let [r (+ canopy-tab-radius canopy-tab-thickness)
        d (/ r 2)
        r2 canopy-tab-radius]
    (difference 
      (intersection
        (translate [d d d]
                   (cube r r r))
        (with-fn 30
                 (cylinder r hull-tab-thickness)))
        (with-fn 30
                 (cylinder r2 (* 3 hull-tab-thickness))))))

(defn front-hull
  [{:keys [mounting fc-height fc-thickness canopy-tab-thickness canopy-tab-radius]
    :as cfg}]
  (let [base (+ fc-height fc-thickness)]
    (difference 
    (hull
      (canopy-camera-box-side cfg :bottom)
      (translate [0 mounting base]
                 (canopy-tab cfg)))
    (translate [0 mounting (+ base canopy-tab-thickness 49)]
    (with-fn 6 
               (cylinder canopy-tab-radius 100)))
    )
  ))

(defn back-hull
  [{:keys [mounting vtx-height vtx-thickness canopy-tab-thickness canopy-tab-radius]
    :as cfg}]
  (let [base (+ vtx-height vtx-thickness)]
    (difference 
    (hull
      (canopy-camera-box-side cfg :top)
      (translate [mounting 0 base]
                 (canopy-tab cfg)))
    (translate [ mounting 0 (+ base canopy-tab-thickness 50)]
    (with-fn 6 
               (cylinder canopy-tab-radius  100)))
    )
  ))

(defn upper-side-hull
  [{:keys [vtx-height vtx-thickness canopy-tab-thickness
           canopy-tab-radius vtx-protrusion]
    :as cfg}]
  (let [base (+ vtx-height vtx-thickness)]
    (union
      (translate [0 0 base]
                 (canopy-tab cfg))
      (difference 
        (hull
          (canopy-camera-box-side cfg :right)
          (translate [0 0 base]
                     (rotate (degrees 155) [0 0 1]
                     (hull-tab cfg))))
        (translate [ 0 0 (+ base -50)]
                   (with-fn 30 
                            (cylinder (+ canopy-tab-radius 1.5) 100)))
        (translate [ 0 0 (+ base canopy-tab-thickness 50)]
                   (with-fn 6 
                            (cylinder canopy-tab-radius 100)))
        (vtx (assoc cfg
                    :vtx-thickness (+ vtx-height vtx-thickness)
                    :vtx-height 0
                    :vtx-protrusion (+ vtx-protrusion 3)))
        (let [d (Math/sqrt(/ (Math/pow 6.5 2) 2))]
          (translate [d d base]
                     (rotate (degrees 135) [0 0 1]
                             (cube 8 8 8))))
        )
      )))

(defn upper-side-hull2
  [{:keys [mounting vtx-height vtx-thickness canopy-tab-thickness
           canopy-tab-radius vtx-protrusion]
    :as cfg}]
  (let [base (+ vtx-height vtx-thickness)]
    (union
      (translate [mounting mounting base]
                 (canopy-tab cfg))
      (difference 
        (hull
          (canopy-camera-box-side cfg :left)
          (translate [mounting mounting base]
                     (rotate (degrees 35) [0 0 1]
                     (hull-tab cfg))))
        (translate [ mounting mounting (+ base -50)]
                   (with-fn 30 
                            (cylinder (+ canopy-tab-radius 1.5) 100)))
        (translate [ mounting mounting (+ base canopy-tab-thickness 50)]
                   (with-fn 6 
                            (cylinder canopy-tab-radius 100)))
        (vtx (assoc cfg
                    :vtx-thickness (+ vtx-height vtx-thickness)
                    :vtx-height 0
                    :vtx-protrusion (+ vtx-protrusion 3)))
        (let [d (Math/sqrt(/ (Math/pow 6.5 2) 2))]
          (translate [(- mounting d) (- mounting d) base]
                     (rotate (degrees 135) [0 0 1]
                             (cube 8 8 8))))
        )
      )))

(defn camera-side-mounts
  [{:keys [camera-base-width canopy-thickness canopy-camera-box-spacing
           camera-side-mount-hole-radius canopy-camera-side-mount-offset
           canopy-camera-side-mount-hole-radius]
    :as cfg}]
  (let [h (+ canopy-thickness canopy-camera-side-mount-offset (* 2 camera-side-mount-hole-radius))
        m (cube canopy-thickness (+ (* 2 canopy-camera-side-mount-hole-radius) (* 2 canopy-thickness)) h)
        x (+ (/ camera-base-width 2) (/ canopy-thickness 2) canopy-camera-box-spacing)]
    (position-camera cfg
                     (difference
                       (union
                         (translate [x 0 (/ h 2)] m)
                         (translate [(- x) 0 (/ h 2)] m))
                       (translate [0 0 (+ canopy-thickness canopy-camera-side-mount-offset)]
                                  (rotate 
                                    (degrees 90) [0 1 0]
                                    (with-fn 30
                                             (cylinder canopy-camera-side-mount-hole-radius 1000))))))))

(defn turret
  [{:keys [camera-lens-radius turret-thickness turret-length turret-height canopy-camera-lens-spacing]
    :as cfg}]
  (position-camera cfg 
                   (translate [0 0 (+ turret-height (/ turret-length 2))]
                              (difference
                                (with-fn 30
                                         (cylinder (+ camera-lens-radius turret-thickness canopy-camera-lens-spacing) turret-length))
                                (with-fn 30
                                         (cylinder (+ camera-lens-radius canopy-camera-lens-spacing) 10000))
                                (apply union (map 
                                               (fn [a]
                                                 (translate [0 0 (/ turret-length 2)]
                                                            (rotate a [0 0 1]
                                                            (cube 1000 turret-thickness turret-thickness))))
                                               (range 0 (* 2 Math/PI) (/ Math/PI 3))))
                                         ))))

(defn turret-mount
  [{:keys [camera-lens-radius turret-thickness turret-length
           canopy-camera-lens-spacing turret-height
           canopy-camera-side-mount-length
           canopy-thickness canopy-camera-box-spacing camera-base-width
           camera-base-thickness
           turret-mount-offset]}]
  (let [cutter (translate [50 50 50]
                          (cube 100 100 100))
        r (+ camera-lens-radius turret-thickness)
        c (difference
            (cylinder r 1)
            (cylinder (+ camera-lens-radius canopy-camera-lens-spacing) 100))
        s (intersection
            (rotate (degrees 45) [0 0 1]
                    (intersection 
                      cutter
                      c))
            cutter)]
    (difference
      (union
      (hull
        (translate [0 0 (+ turret-height (/ turret-length 2))]
                   (rotate (degrees -67.5) [0 0 1] s))
        (translate [(+ (/ camera-base-width 2) (/ canopy-thickness 2) canopy-camera-box-spacing)
                    0 (+ camera-base-thickness turret-mount-offset)]
                   (cube canopy-thickness canopy-camera-side-mount-length 1))
        )  
        (translate [(+ (/ camera-base-width 2) (/ canopy-thickness 2) canopy-camera-box-spacing)
                    0 (/ (+ camera-base-thickness turret-mount-offset) 2)]
                   (cube canopy-thickness canopy-camera-side-mount-length (+ camera-base-thickness turret-mount-offset)))

      )
      (with-fn 30
               (cylinder (+ camera-lens-radius canopy-camera-lens-spacing) 1000))
      )))

(defn turret-mounts
  [{:keys [canopy-camera-side-mount-hole-radius
           canopy-thickness
           canopy-camera-side-mount-offset] :as cfg}]
  (position-camera cfg
                   (difference 
                   (union (turret-mount cfg)
                          (rotate (degrees 90) [0 0 1]
                                  (turret-mount cfg))
                          (rotate (degrees 180) [0 0 1]
                                  (turret-mount cfg))
                          (rotate (degrees 270) [0 0 1]
                                  (turret-mount cfg))
                          )
                   (translate 
                     [0 0 (+ canopy-thickness canopy-camera-side-mount-offset)]
                     (rotate (degrees 90)
                             [0 1 0]
                             (with-fn 30
                                      (cylinder canopy-camera-side-mount-hole-radius 1000))
                             ))


                   )))

(defn ariel-hooks
  [{:keys [hook-outer-radius hook-inner-radius hook-thickness
           camera-base-height canopy-thickness hook-offset]
    :as cfg}]
  (let [hook (rotate (degrees 90) [0 1 0]
                     (difference 
                       (with-fn 30
                                (cylinder hook-outer-radius hook-thickness))
                       (with-fn 30
                                (cylinder hook-inner-radius 1000))))
        d (- 0 (/ camera-base-height 2) (* 2.5 canopy-thickness))]
    (position-camera
      cfg
      (union 
        (translate [hook-offset d 0] 
              (difference
                hook
                (rotate (degrees -40) [1 1 0]
                (rotate (degrees 90) [0 1 0]
                (translate [0 -2 0]
                (cube 2 2 1000))))
                                                ))
        (translate [(- hook-offset) d 0] hook)
        ))))

(defn canopy
  [{:as cfg}]
  (difference 
    (union
      (front-hull cfg)
      (back-hull cfg)
      (upper-side-hull cfg)
      (upper-side-hull2 cfg)
      ;(camera-side-mounts cfg)
      (turret cfg)
      (turret-mounts cfg)
      (ariel-hooks cfg)
      )
    (posts (:post-hole-radius cfg) cfg)
  ))



(defn thing
  [cfg]
  [
   (color metal
  (posts (:post-radius cfg ) cfg))
  (flight-controller cfg)
  (vtx cfg)
  (camera cfg)
  (canopy cfg)
  ]
  )

(render! "thing" (thing (config)))
(render! "canopy" (canopy (config)))
;(render! "thing" (turret-mounts (config)))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
