(defproject clojure-contentful-demo "0.1.0-SNAPSHOT"
	:description "FIXME: write description"
	:url "http://example.com/FIXME"
	:min-lein-version "2.0.0"
	:dependencies [[org.clojure/clojure "1.8.0"]
								 [compojure "1.5.1"]
								 [ring/ring-jetty-adapter  "1.4.0"]
								 [ring/ring-defaults "0.2.1"]
								 [cheshire "5.8.0"]
								 [hiccup "1.0.5"]
								 [environ "1.0.0"]
								 [http-kit "2.2.0"]]
	:plugins [[lein-ring "0.9.7"]
						[environ/environ.lein  "0.3.1"]]
	:uberjar-name "clojure-contentful-demo-standalone.jar"
	:ring {:handler clojure-contentful-demo.handler/app}
	:hooks [environ.leiningen.hooks] 
	:profiles
	{:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
												[ring/ring-mock "0.3.0"]]}})
