# 1. Docker desktop
 - Must be installed and have a minikube (kubernetes) container running. This is where kubernetes is running.

# 2. Terminal, administrator mode (dependent on Docker desktop running the minikube instance)
 - RabbitMq must be installed on local machine, with connection to the minikube from here
 
 - 3. cmd: minikube start (to start the minikube, connect rabbitmq to minikube)
 - 4. cmd: minikube tunnel (to tunnel the ports between rabbitmq and minikube up to usable by localhost url)

                - 1st Port 5672: AMQP Port  = Where the application connects to
                - 2nd Port 15672: UI Port   = Where the RabbitMQ interface shows up in the browser and show stats/data




_______________________________________________________________________________
# Short (CoPilot) explanation (Thought it was pretty good summarized, even how)

### Summary
- Docker Desktop: Provides the container runtime for Minikube.
- Minikube Start: Initializes and starts the local Kubernetes cluster.
- Minikube Tunnel: Exposes LoadBalancer services to localhost, making RabbitMQ accessible on localhost:5672 and localhost:15672.
- Spring Boot Configuration: Configures the application to connect to RabbitMQ on localhost.