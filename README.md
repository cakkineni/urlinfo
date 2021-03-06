# urlinfo
## Introduction
 This service provides a simple service `urlinfo` that responds to GET requests where the caller passes in a URL and the service responds indicating if the url is malware or not. 
 The GET requests should look like this:   GET /urlinfo/1/{hostname_and_port}/{original_path_and_query_string}
 The service responds with the following:
  - If the url IS NOT malware: Response code: 204, Body: (empty)
  - If the url IS malware: Response code: 200, Body: malware

 The response codes and the body content returned follows the same spec as the Google implementation (https://developers.google.com/safe-browsing/v3/lookup-guide).
  
## Implementation

####Request Handling:
The current implementation in dev is using a builtin tomcat server embedded in spring boot. But for production deployments, it is preferred to have this front ended by nginix, and spin up new nodes if request response time is going up. Spinning up new nodes and serving new requests can all be automated by providing API endpoints to spin up new instances in the cloud of choice and deploying the urlinfo service.

####Storage:
TODO: Redis Sets
TODO: Sharding

####Handling data updates:
The class `Model` exposes a method `setKey` that can be used to push new data to the Redis. This can be throttled by pushing the messages to a Queue and reading from the queue and pushing to redis.

## Installation
####Docker: 
The application is being tested on docker version 1.12.
 
####Redis:
In the current dev environment, Redis is being run as a docker container (https://hub.docker.com/_/redis/). Bring up the redis container using `docker run --name redis -d -p 6379:6379 redis:3.0.7-alpine`

####Source
The Application is being developed using `Java 1.8`, `Maven`, `Spring boot`, `Jedis`.

####Dev Deployment
Spring boot packages an internal Tomcat server and should be able to bring up the webservice locally without having to install additional tomcat.

## Versions
1.0-Snapshot

## User Guide
To use the webservice `urlinfo` you can run a curl command as follows

Example:
```Bash
   ##MALWARE URL (Return Code: 200, Body Content: malware)##
   
   curl -vvvv http://localhost:8080/urlinfo/1/geil.alon3.tk/geil.alon3.tk%2Frncbu3.html
   
   *   Trying ::1...
   * Connected to localhost (::1) port 8080 (#0)
   > GET /urlinfo/1/geil.alon3.tk/geil.alon3.tk%2Frncbu3.html HTTP/1.1
   > Host: localhost:8080
   > User-Agent: curl/7.43.0
   > Accept: */*
   >
   < HTTP/1.1 200
   < Content-Disposition: inline;filename=f.txt
   < Content-Type: text/html;charset=UTF-8
   < Content-Length: 7
   < Date: Fri, 09 Sep 2016 03:00:48 GMT
   <
   * Connection #0 to host localhost left intact
   malware

```

```Bash
   ##NON MALWARE URL (Return Code: 204, Body Content:  (empty))##
   
   curl -vvvv http://localhost:8080/urlinfo/1/google.com:8080/httpswww.google.com
   
   *   Trying ::1...
   * Connected to localhost (::1) port 8080 (#0)
   > GET /urlinfo/1/google.com:8080/httpswww.google.com HTTP/1.1
   > Host: localhost:8080
   > User-Agent: curl/7.43.0
   > Accept: */*
   >
   < HTTP/1.1 204
   < Content-Disposition: inline;filename=f.txt
   < Content-Type: text/plain;charset=UTF-8
   < Date: Fri, 09 Sep 2016 03:15:36 GMT
   <
   * Connection #0 to host localhost left intact

```


## Related Resources
https://developers.google.com/safe-browsing/v3/lookup-guide

## TODO
1. Move redis connection string to config file.
2. Use Redis sharding so that when a server runs out of space, the shards can be moved to different servers and brought back up with no downtime.
3. Bug when parsing urls with %2f (/). Issue with how Spring resolves urls. Currently throws a 404.


