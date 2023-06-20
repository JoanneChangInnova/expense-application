## Prerequisite
- docker engine
## How to run
- cd root directory of this project
- Build image based on Dockerfile
    >docker build -t user .
- Start the container and expose port 5557 on the host
    >docker run -d -p 5557:5557 user
- List container, copy the containerID
    >docker ps -a
- Check log and see if the service is started
    >docker exec -it 'containerID' cat /logs/user-service.log  
    curl http://localhost:5557/user # return 200
