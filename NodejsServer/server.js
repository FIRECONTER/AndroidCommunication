var http = require('http');
var url = require('url');
var querystring = require('querystring');
var ob={
	'name':'alex',
	'age':18
}
http.createServer(function(req,res){
	req.setEncoding('utf-8');
	var postData='';
	req.on('data',function(postDataChunk){
		postData+=postDataChunk;
	});
	
	req.on('end',function(){
		console.log('数据接收完毕');
		var params = querystring.parse(postData);// parse the post data to json
		console.log('name',params['name']);
		console.log('age',params['age']);
		res.writeHead(200, {
            "Content-Type": "text/plain;charset=utf-8"
        });
		var back = JSON.stringify(ob);
		res.end(back);
	});
}).listen(3000,'42.96.149.197');

console.log('ok');