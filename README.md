# urlinfo
## Introduction
 This service provides a simple method that takes in a url and checks if its a malware url or not. The sources for malware urls come from multiple sources. 

 The response codes and the body content returned follows the same spec as the Google implementation (https://developers.google.com/safe-browsing/v3/lookup-guide).
  
## Implementation

## Installation

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
