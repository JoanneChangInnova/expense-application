## How to run

> docker build -t expense .  
> docker run -d -p 5555:5555 expense  
> docker exec -it 'containerId' cat /logs/expense-service.log  
> curl http://localhost:5555