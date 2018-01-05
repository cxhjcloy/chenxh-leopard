#MongoDb

##MongoDB安装

安装MongoDB于Ubuntu上。

sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2930ADAE8CAF5059EE73BB4B58712A2291FA4AD5

echo "deb [ arch=amd64,arm64,ppc64el,s390x ] http://repo.mongodb.com/apt/ubuntu xenial/mongodb-enterprise/3.6 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-enterprise.list

sudo apt-get update

sudo apt-get install -y mongodb-enterprise

sudo service mongod start

sudo service mongod stop

sudo service mongod restart

sudo apt-get purge mongodb-enterprise*

sudo rm -r /var/log/mongodb

sudo rm -r /var/lib/mongodb

##常用命令

cd <mongodb installation dir>

./bin/mongo #连接mongodb 

db #To display the database you are using

show dbs #To list the available databases

##启动鉴权
use admin
db.createUser(
  {
    user: "sysadmin",
    pwd: "cxh123",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)

use chenxh
db.createUser(  
{  
   user: "chenxh",  
   pwd: "cxh123",  
   roles:  
   [  
     {  
       role: "readWrite",  
       db: "chenxh"  
     }  
   ]  
 }  
)

vi /etc/mongod.conf
添加：
security:
    authorization: enabled
sudo systemctl restart mongod.service




##docker mongo

docker pull mongo

docker run  --name mymongo   -p 27017:27017   -d mongo   --auth

docker exec -it some-mongo mongo admin

> db.createUser({ user: 'jsmith', pwd: 'some-initial-password', roles: [ { role: "userAdminAnyDatabase", db: "admin" } ] });
db.createUser(
  {
    user: "sysadmin",
    pwd: "cxh123",
    roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
  }
)

use chenxh
db.createUser(  
{  
   user: "chenxh",  
   pwd: "cxh123",  
   roles:  
   [  
     {  
       role: "readWrite",  
       db: "chenxh"  
     }  
   ]  
 }  
)