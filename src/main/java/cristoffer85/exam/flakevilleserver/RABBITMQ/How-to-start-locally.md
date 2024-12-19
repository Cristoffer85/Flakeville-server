# 1. Docker desktop
 - Must be installed and have a minikube (kubernetes) container running

# 2. Terminal, administrator mode (dependent on Docker desktop running the minikube instance)
 - RabbitMq must be installed on local machine, with connection to the minikube from here
 
 - 3. cmd: minikube tunnel 
                (To connect the minikube to correct rabbitmq ports from terminal with help through vpn(tunnel))
                - 1st Port 5672: AMQP Port  = Where the application connects to
                - 2nd Port 15672: UI Port   = Where the RabbitMQ interface shows up in the browser and show stats/data