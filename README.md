# urlinfo
## Introduction
 This service provides a simple service `urlinfo` that responds to GET requests where the caller passes in a URL and the service responds indicating if the url is malware or not. 
 The GET requests should look like this:   GET /urlinfo/1/{hostname_and_port}/{original_path_and_query_string}
 The service responds with the following:
  - If the url IS NOT malware: Response code: 204, Body: (empty)
  - If the url IS malware: Response code: 200, Body: malware

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
