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
  # my global config
global:
  scrape_interval: 15s # Set the scrape interval to every 15 seconds. Default is every 1 minute.
  evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute.
  # scrape_timeout is set to the global default (10s).

# Alertmanager configuration
alerting:
  alertmanagers:
    - static_configs:
        - targets:
          # - alertmanager:9093

# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: "prometheus"

    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.

    static_configs:
      - targets: ["localhost:9090"]
  - job_name: 'spring-resillience'
    metrics_path: '/actuator/prometheus'
    scrape_interval: 5s
    static_configs:
      - targets: ["localhost:8081"]

    

   ```
