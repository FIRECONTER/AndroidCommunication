var http = require('http');
var httpserver = http.createServer(function(req,res){

}).listen(3001,'42.96.149.197');
console.log('httpserver is on');
var io = require('socket.io').listen(httpserver);
io.sockets.on('connection',function(socket){

console.log('socketserver linked');
socket.emit('message',[{"name":"alex","age":18},{"name":"bob","age":20}]);
socket.on('push',function(data){
console.log('back'+data);


});
    socket.on('disconnect',function(){

        console.log('socketserver disconnect');
    });

});
