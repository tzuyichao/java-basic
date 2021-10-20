## Examples

    curl -v -H "Content-Type: application/json" -X POST http://localhost:8080/movies
    {"timestamp":"2019-09-01T11:18:50.535+0000","status":400,"error":"Bad Request","message":"Required request body is missing: public void org.greenrivers.controllervalidatelab.controller.MovieController.addAll(java.util.List<org.greenrivers.controllervalidatelab.model.Movie>)","path":"/movies"}

    curl -v -d [] -H "Content-Type: application/json" -X POST http://localhost:8080/movies
    {"timestamp":"2019-09-01T11:19:59.893+0000","status":500,"error":"Internal Server Error","message":"addAll.movies: must not be empty","path":"/movies"}
    
    curl -v -d '[{"name": "The Matrix"}]' -H "Content-Type: application/json" http://localhost:8080/movies
    Server Log:
    2019-09-01 19:32:39.162  WARN 6488 --- [io-8080-exec-10] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Unexpected character ('n' (code 110)): was expecting double-quote to start field name; nested exception is com.fasterxml.jackson.databind.JsonMappingException: Unexpected character ('n' (code 110)): was expecting double-quote to start field name
     at [Source: (PushbackInputStream); line: 1, column: 2] (through reference chain: java.util.ArrayList[0])]
    add [Movie(id=null, name=The Matrix)]
    
    curl -v -d '[{"name": "The Matrix"}, {"name": "Movie1"}, {"name": "Movie2"}, {"name": "Movie3"}, {"name": "Movie4"}]' -H "Content-Type: application/json" http://localhost:8080/movies
    {"timestamp":"2019-09-01T12:02:47.566+0000","status":500,"error":"Internal Server Error","message":"addAll.movies: The input list cannot contain more than 4 movies.","path":"/movies"}