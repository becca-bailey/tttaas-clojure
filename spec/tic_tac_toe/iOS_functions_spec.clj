(ns tic-tac-toe.iOS-functions-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.iOS-functions :as iOS]))

(describe "iOS functions"
  (context "#minimax-move"
    (it "returns the best computer move given a board"
      (should= 8 (iOS/minimax-move [0,1,2,3,4,5,6,7,8])))))
