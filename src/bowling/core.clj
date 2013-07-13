(ns bowling.core
  (:gen-class))

(defn isStrike [pins]
  (== 10 (first pins)))

(defn isSpare [pins]
  (== 10 (reduce + (take 2 pins))))

(defn frameScore [pins]
  (if (or (isStrike pins)
          (isSpare pins))
    (reduce + (take 3 pins))
    (reduce + (take 2 pins))))

(defn nextFramePins [frameNumber pins]
  (if (== frameNumber 10)
    (list)
    (if (isStrike pins)
      (rest pins)
      (drop 2 pins))))

(defn countFrame [frameNumber pins]
  (if (== 0 (count pins))
    0
    (+ (frameScore pins)
       (countFrame (+ frameNumber 1) (nextFramePins frameNumber pins)))))

(def zeros
  (list 0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0))

(def ones
  (list 1, 1, 1, 1,
        1, 1, 1, 1,
        1, 1, 1, 1,
        1, 1, 1, 1,
        1, 1, 1, 1))

(def spare
  (list 5, 5, 3, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0))

(def strike
  (list 10,   3, 4,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0,
        0, 0, 0, 0))

(def perfect
  (list 10,   10,
        10,   10,
        10,   10,
        10,   10,
        10,   10,
        10,   10))

(defn eq [actual expected]
  (printf "Got %d, expected %d\n" actual expected)
  (== actual expected))

(defn total [pins]
  (countFrame 1 pins))

(defn -main []
  (and (eq (total zeros)   0)
       (eq (total ones)    20)
       (eq (total spare)   16)
       (eq (total strike)  24)
       (eq (total perfect) 300)))
