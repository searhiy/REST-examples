Spring MVC REST caching example
=============

ETags are used for two things – caching and conditional requests.
The ETag value can be though as a hash computed out of the bytes of the Response body.

Using an If-* header turns a standard GET request into a conditional GET.
The two If-* headers that are using with ETags are “If-None-Match” and “If-Match”

##Caching in HTTP is actually quite complex and it involves several other headers:

 - Cache-Control: public (Indicates that the response MAY be cached by any cache, even if it would normally be non-cacheable or cacheable only within a non- shared cache.)
 - Expires - when given resource should be considered stale
 - Last-Modified - when was resource last modified
 - ETag - unique tag of resource, changed in every revision
 - Vary - separate caching based on different headers
 - If-Modified-Since, If-None-Match, ...

##Useful blogs:

 - [FileServlet supporting resume and caching and GZIP] (http://balusc.blogspot.in/2009/02/fileservlet-supporting-resume-and.html)

 - [Implementing HTTP byte-range requests in Spring MVC] (http://datum-bits.blogspot.com/2013/01/implementing-http-byte-range-requests_30.html)
