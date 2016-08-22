(defproject tic-tac-toe "0.2.2-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [io.aviso/pretty "0.1.26"]]
  :profiles {:dev {:dependencies
                   [[speclj "3.3.1"]]}}
  :plugins [[speclj "3.3.1"]
            [io.aviso/pretty "0.1.2"]]
  :test-paths ["spec"]
  :aot :all
  :main tic-tac-toe.game-loop)
