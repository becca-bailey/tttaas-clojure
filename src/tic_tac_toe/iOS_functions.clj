(ns tic-tac-toe.iOS-functions
  (:require [tic-tac-toe.ai :as ai]
            [tic-tac-toe.game :as game]))

(defn minimax-move [board]
  (ai/best-computer-move (game/game-state board)))
