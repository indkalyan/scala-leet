A resource is an entity that can be identified, named, addressed, or handled on the web.


A resource that exists only within another resource can be better represented as a sub-resource instead of a top-level resource in the URL. This makes the relationship clear for the developers using the API.

POST /repos/:owner/:repo/issues Create an issue. GET /repos/:owner/:repo/issues/:number Retrieve an issue. GET /repos/:owner/:repo/issues List all issues. PATCH /repos/:owner


 Render an action as part of a field of a resource. 
PATCH /repos/saurabhsahni/Hacks HOST api.github.com Content-Type: application/json Authorization: token OAUTH-TOKEN { "archived": true } 


Treat an action like a sub resource. 
PUT /repos/:owner/:repo/issues/:number/lock locks an issue.

action verb in the API URL. GET /search/code?q=:query: finds files in GitHub matching the given query.

Remote Procedure Call (RPC) is one of the simplest API paradigms, in which a client executes a block of code on another server.

High performance protocols that are available for RPC-style APIs, including Apache Thrift and gRPC.


GraphQL is a query language for APIs that has gained significant traction recently.
you do not need different HTTP verbs to describe the operation. Instead, you indicate in the JSON body whether you’re performing a query or a mutation



server needs to do additional processing to parse complex queries and verify parameters. Optimizing performance of GraphQL queries can be difficult, too. 

					To share data about events in real time, there are three common mechanisms: WebHooks, WebSockets, and HTTP Streaming.
					
					WebHooks
					A WebHook is just a URL that accepts an HTTP POST (or GET, PUT,
					or DELETE). An API provider implementing WebHooks will simply
					POST a message to the configured URL when something happens.
					Unlike with request–response APIs, with WebHooks, you can
					receive updates in real time. 
					
					Failures and retries
					Security
					Firewalls 
					Applications running behind firewalls can access APIs over HTTP, but they are unable to receive inbound traffic.
					Noise 
					Typically, each WebHook call represents one single event. When there are thousands of events happening in a short time that need to be sent via a single WebHook, it can be noisy
					
					
					WebSockets 
					WebSocket is a protocol used to establish a two-way streaming com‐ munication channel over a single Transport Control Protocol (TCP) connection. Although the protocol is generally used between a web client (e.g., a browser) and a server, it’s sometimes used for serverto-server communication, as well.
					
					on mobile devices or in regions where connectivity can be spotty. Clients are supposed to keep the connection alive. If the connection dies, the client needs to reinitiate it. 
					There are also issues related to scalability
					
