# Introduction
This is the bastardised version that we can start to migrate towards our use case.
[Original Readme](./README-original.md)

# To Build Containers
```bash
./playground.sh
```

# To Run Containers
```bash
docker-compose up -d --build
```

# What's Running
The only real containers of interest are:
- hello-service
- weather-service
- prometheus
- grafana

# Accessing the Services

For details on SCI ISO value - https://sci.greensoftware.foundation/

## weather-service
- http://localhost:8081/weather   - shows the current weather forecast
- http://localhost:8081/actuator/prometheus - Shows Prometheus metrics including - **carbon_sci** and **carbon_emissions**

This service uses the **PowerUtilizationEnergyConsumptionProvider** provider of the **carbon-aware-starter** dependency. This uses the **oshi** library https://github.com/oshi/oshi?tab=readme-ov-file#documentation to utilise OS specific info where possible. Tried the following
- WSL2 - no good: no OS data mined
- Amazon Linux - gets some detail, but not enough for a usuage calculation
- MacOS - **TBD**

*May be a case of identifying the VM types on EC2 and utilising the following to harvest power info: https://doc.api.boavizta.org/Reference/format/component_route/ as described in https://blog.re-cinq.com/posts/cloud-cpu-energy-consumption/.

#### Current View
May be removing Carbon-Aware-SDK as dependency and:
- Identify CPU/Memory/Network usage metrics
- Grab EC2 instance from **oshi**
- Cross-references against the **boasvista** APIs
- Grab current carbon intensity from grid using:  [Carbon Intensity API](https://api.carbonintensity.org.uk/)

## hello-service
- http://localhost:8082/greeting/{name}   - tells {name} the forecast
- http://localhost:8082/actuator/prometheus - Shows Prometheus metrics including - **carbon_sci** and **carbon_emissions**

This service uses the **ResourceUtilizationEnergyConsumptionProvider** provider of the **carbon-aware-starter** dependency. The downside of this is that it requires a *cpuEnergyTax* value, which in this case is statically 100%.

## prometheus
- http://localhost:9090/ - holds the metrics emitted above

## grafana
- http://localhost:3000/login - nothing created as yet!