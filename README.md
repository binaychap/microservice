# microservice

### How to run the Eureka server
1. Run discovery service - run the Eureka server locally which provides the dashboard for service discovery
2. http://localhost:8761/

### Run microservice
1. run the movie-catalog-service : this service depends on both movie-info-service and ratings-data-service
2. run movie-info-service
3. run ratings-data-service

### Run Prometheus server locally
1. Download and edit the prometheus.yml file pointing to local running movie-catalog-service Prometheus endpoints
   ```
  - job_name: 'spring-resillience'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: ["localhost:8081"]
   ```
