package main

import (
	"os"
	"log"
	"net/http"
	"net/http/httputil"
	"net/url"
	"strings"
)

func main() {
	r := newRouter()

	port := getEnv("PORT", "8080")

	log.Printf("Eshop-Gateway service listening on :%s", port)

	if err := http.ListenAndServe(":"+port, r); err != nil {
		log.Fatalf("Error starting the server: %s", err)
	}
}

func newRouter() *http.ServeMux {
	mux := http.NewServeMux()

	mux.HandleFunc("/gw-order/", reverseProxy)
	mux.HandleFunc("/gw-catalog/", reverseProxy)
	mux.HandleFunc("/gw-payment/", reverseProxy)
	mux.HandleFunc("/gw-order", reverseProxy)
	mux.HandleFunc("/gw-catalog", reverseProxy)
	mux.HandleFunc("/gw-payment", reverseProxy)

	return mux
}

func reverseProxy(w http.ResponseWriter, r *http.Request) {

	targetURL := ""

	var (
		orderServiceURL   = getEnv("ORDER_SERVICE_URL", "http://localhost:3000")
		catalogServiceURL = getEnv("CATALOG_SERVICE_URL", "http://localhost:3000")
		paymentServiceURL = getEnv("PAYMENT_SERVICE_URL", "http://localhost:3000")
	)

	switch {
		case strings.Contains(r.URL.Path, "/gw-order"):
			targetURL = orderServiceURL + r.URL.Path[len("/gw-order"):]
		case strings.Contains(r.URL.Path, "/gw-catalog"):
			targetURL = catalogServiceURL + r.URL.Path[len("/gw-catalog"):]
		case strings.Contains(r.URL.Path, "/gw-payment"):
			targetURL = paymentServiceURL + r.URL.Path[len("/gw-payment"):]
		default:
			http.NotFound(w, r)
			return
	}


	target, err := url.Parse(targetURL)
	if err != nil {
		http.Error(w, "Bad Gateway", http.StatusBadGateway)
		return
	}

	log.Printf("Eshop-Gateway - redirecting to: %s", targetURL)

	r.URL.Path = ""

	proxy := httputil.NewSingleHostReverseProxy(target)

	proxy.ServeHTTP(w, r)
}

func getEnv(key, defaultValue string) string {
	value := os.Getenv(key)
	if value == "" {
		return defaultValue
	}
	return value
}

