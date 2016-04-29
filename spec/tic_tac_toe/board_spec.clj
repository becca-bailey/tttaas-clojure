(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.player :as player]))

(def player-1 (player/human "X"))
(def player-2 (player/computer "O"))

(def default-players
  [player-1 player-2])

(def x-wins (board/make-board {"X" #{0 4 8}}))
(def o-wins (board/make-board {"O" #{3 4 5}}))
(def tie-game (board/make-board {"X" #{1 3 4 6 8} "O" #{0 2 5 7}}))
(def first-move-x (board/make-board {"X" #{4}}))

(describe "Board"
  (context "initial-board"
    (it "generates an empty board"
      (should= [0 1 2 3 4 5 6 7 8] board/initial-board)))

  (context "#make-board"
    (it "returns an empty board given no arguments"
      (should= board/initial-board (board/make-board)))

    (it "returns a board with default markers given a map of spots and players"
      (should= first-move-x (board/make-board {"X" #{4}}))
      (should= ["O" 1 2 3 "X" 5 6 7 8] (board/make-board {"X" #{4} "O" #{0}})))

    (it "returns a board with custom markers given a map of spots and players"
      (should= [0 1 2 3 "A" 5 6 7 8] (board/make-board {"A" #{4}}))
      (should= ["B" 1 2 3 "A" 5 6 7 8] (board/make-board {"A" #{4} "B" #{0}}))))

  (context "#place-marker"
    (it "places a marker on the board"
      (should= first-move-x (board/place-marker board/initial-board 4 "X"))))

  (context "#three-in-a-row"
    (it "returns true if the given three spaces have the expected marker"
      (should= true (board/three-in-a-row x-wins [0 4 8] "X"))
      (should= false (board/three-in-a-row x-wins [0 1 2] "X"))))

  (context "#is-winner?"
    (it "returns true if a given player has won the game"
      (let [x-wins-2 ["O" "X" "O" "X" "X" "O" "O" "X" 8]
            o-wins-2 ["O" "X" "X" "X" "X" "O" "O" "O" "O"]]
        (should= true (board/is-winner? x-wins player-1))
        (should= false (board/is-winner? tie-game player-1))
        (should= false (board/is-winner? x-wins player-2))
        (should= false (board/is-winner? o-wins player-1))
        (should= true (board/is-winner? o-wins player-2))
        (should= true (board/is-winner? x-wins-2  player-1)))))

  (context "#available-spots"
    (it "returns a collection of spots without X or O"
      (should= [0 1 2 3 5 6 7 8] (board/available-spots first-move-x))))

  (context "#won?"
    (it "returns true if any player has won the game"
      (should= true (board/won? x-wins default-players))
      (should= true (board/won? o-wins  default-players))
      (should= false (board/won? tie-game default-players))))

  (context "#spot-is-empty"
    (it "returns true if a spot is empty"
      (should= true (board/spot-is-empty first-move-x 0))
      (should= false (board/spot-is-empty first-move-x 4))))

  (context "#tie?"
    (it "returns true if there is a tie"
      (should= true (board/tie? tie-game default-players)))

    (it "returns false if there is a winner"
      (let [game-with-winner ["O" "X" "O" "X" "X" "O" "O" "X" "X"]]
        (should= false (board/tie? game-with-winner default-players)))))

  (context "#winner"
    (it "returns winning player"
      (should= player-1 (board/winner x-wins default-players))
      (should= player-2 (board/winner o-wins default-players)))

    (it "returns nil if the game is a tie"
      (should-be-nil (board/winner tie-game default-players)))))
