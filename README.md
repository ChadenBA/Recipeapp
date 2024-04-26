
# Recipe Application 

Welcome to the Recipe Application! This application is built using Spring Boot for the backend and React for the frontend. Below you will find information on how to set up the application, deploy it using a CI/CD pipeline, and monitor it using Prometheus and Grafana.


## Purpose
The Recipe Application is designed to streamline the process of discovering, managing, and sharing recipes for users. By combining Spring Boot and React, it offers a user-friendly interface with robust functionality. The application aims to enhance user engagement through features such as recipe management, interaction, and community participation. Integration with CI/CD pipelines automates deployment processes, while Prometheus and Grafana enable efficient monitoring for optimal performance. Overall, the application's goal is to provide a seamless and enjoyable experience for users passionate about cooking and culinary exploration.
## Installation

Install my-project with npm

```bash
  git clone https://github.com/ChadenBA/Recipeapp.git
  cd RouterManagementApp
  mvn compile
```
    
## Running Tests

To run tests, run the following command

```bash
mvn test
```


## Kubernetes  Install 


```bash
apt-get update -y
apt-get install docker.io -y
service docker restart
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add -
echo "deb http://apt.kubernetes.io/ kubernetes-xenial main" >/etc/apt/sources.list.d/kubernetes.list
apt-get update
apt install kubeadm=1.20.0-00 kubectl=1.20.0-00 kubelet=1.20.0-00 -y

kubeadm init --pod-network-cidr=192.168.0.0/16

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

kubectl apply -f https://raw.githubusercontent.com/projectcalico/calico/v3.25.1/manifests/calico.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.49.0/deploy/static/provider/baremetal/deploy.yaml
```

## Kubernetes Configuration

* serviceaccount.yml
This YAML configuration file defines a RoleBinding named `app-rolebinding` within the `routerapp` namespace. It binds the role `my-role` to the service account `jenkins`. This RoleBinding grants permissions to the Jenkins service account to interact with Kubernetes resources within the specified namespace.

```bash
 apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: app-rolebinding
  namespace: recipe
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: Role
  name: my-role
subjects:
- namespace: recipe
  kind: ServiceAccount
  name: jenkins
```
* role.yml

This YAML configuration file defines a Role named my-role within the routerapp namespace. It specifies the permissions granted to the role, allowing actions such as listing, watching, creating, updating, and deleting various Kubernetes resources within the namespace.


```bash
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  name: my-role
  namespace: recipe
rules:
- apiGroups:
  - ""
  - apps
  - autoscaling
  - batch
  - policy
  - rbac.authorization.k8s.io
  resources:
  - pods
  - services
  - componentstatuses
  - daemonsets
  - deployments
  - events
  - endpoints
  - ingresses
  - jobs
  - namespaces
  - nodes
  - persistentvolumes
  - replicasets
  - services
  - serviceaccounts
  verbs: ["get", "list", "watch", "create", "update", "patch", "delete"]

```
* secret.yml
This YAML configuration file defines a Secret named mysecret within the Kubernetes cluster. It is annotated with the kubernetes.io/service-account.name: jenkins, indicating that it is associated with the jenkins service account. This Secret is used to securely store the service account token.
```bash
apiVersion: v1
kind: Secret
metadata:
  name: secret
  annotations:
    kubernetes.io/service-account.name: jenkins
type: kubernetes.io/service-account-token
```

* service.yml
This YAML configuration file defines a ServiceAccount named jenkins within the routerapp namespace. It provides Jenkins with the necessary permissions to interact with Kubernetes resources within the specified namespace.
```bash
apiVersion: v1
kind: ServiceAccount
metadata:
  name: jenkins
  namespace: recipe

```
## Montoring

To ensure the health and performance of our system, we've implemented monitoring using the following tools:

1. **Blackbox Exporter**: Probes endpoints over HTTP, HTTPS, DNS, TCP, and ICMP.
2. **Prometheus**: Collects metrics from various targets and serves as a powerful monitoring system and time-series database.
3. **Grafana**: Provides visualization capabilities, allowing us to create insightful dashboards to monitor collected data effectively.

## Jenkins Pipeline Steps

The deployment of the Router Management Application is facilitated through a Jenkins Pipeline. The pipeline automates various stages of the development process, ensuring seamless integration, testing, and deployment. Below are the steps involved in the Jenkins Pipeline:

1. **Git Checkout**: This stage checks out the latest code from the Git repository, ensuring that the pipeline operates on the most up-to-date codebase.

2. **Compile**: The application code is compiled to ensure that it is free of syntax errors and can be executed properly.

3. **Unit Test**: The unit tests are executed to verify the functionality of individual components of the application.

4. **SonarQube Analysis**: The code is analyzed using SonarQube to identify potential code smells, bugs, and security vulnerabilities.

5. **Trivy Scan**: A vulnerability scan is performed on the Docker image using Trivy to detect any security vulnerabilities.

6. **Build**: The application is built, resulting in an executable artifact that can be deployed.

7. **Deploy to Nexus**: The built artifact is deployed to the Nexus repository manager for version control and artifact management.

8. **Build & Tag Docker Image**: This stage builds and tags a Docker image for the application, ensuring consistency and portability across environments.

9. **Trivy Scan**: A vulnerability scan is performed on the Docker image using Trivy to detect any security vulnerabilities.

10. **Docker Push**: The Docker image is pushed to a Docker registry, making it accessible for deployment.

11. **Kubernetes Deploy**: The application is deployed to a Kubernetes cluster, ensuring scalability and high availability.

1. **Monitoring**: Monitoring the deployed application with Grafana and Prometheus.
---

## Screenshots

![App Screenshot](https://res.cloudinary.com/drgzrvosx/image/upload/v1714170171/Screenshot_from_2024-04-26_23-22-08_p8rhpe.png)


![App Screenshot](https://res.cloudinary.com/drgzrvosx/image/upload/v1714170236/Screenshot_from_2024-04-26_23-23-45_rpedb5.png)


![App Screenshot](https://res.cloudinary.com/drgzrvosx/image/upload/v1714170194/Screenshot_from_2024-04-26_23-23-03_iadgig.png)

## Tech Stack

The Recipe Application is built using the following technologies and tools:

SpringBoot, React, Maven, JUnit, SonarQube,Docker, Trivy, Kubernetes, Jenkins, Nexus, Prometheus, Grafana 

## License

This project is licensed under the MIT License.

Thank you for using the Recipe Application! Happy cooking! 🍳🥗🍰
