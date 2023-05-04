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
