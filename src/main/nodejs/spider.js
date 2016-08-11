'use strict';

var redis = require('redis');
var client = redis.createClient('6379','127.0.0.1');

client.on("error",function(error)
{
        console.log(error);
}
);

client.auth("redis");

var P2PSpider = require('../lib');

var p2p = P2PSpider({
    nodesMaxSize: 3000,   // be careful
    maxConnections: 3000, // be careful
    timeout: 5000
});

p2p.ignore(function (infohash, rinfo, callback) {
    // false => always to download the metadata even though the metadata is exists.
    var theInfohashIsExistsInDatabase = false;
    callback(theInfohashIsExistsInDatabase);
});

p2p.on('metadata', function (metadata) {
    console.log(metadata);
    client.select('0', function(error){
    if(error) {
        console.log(error);
    } else {
        // lpush
        var data = {};
        data.magnet = metadata.magnet;
        data.name = metadata.info.name ? metadata.info.name.toString() : '';
        data.fetchedAt = new Date().getTime();
        var metadataJson = JSON.stringify(data);
        client.lpush('metadata', metadataJson);
    }
});
});

p2p.listen(6881, '0.0.0.0');
