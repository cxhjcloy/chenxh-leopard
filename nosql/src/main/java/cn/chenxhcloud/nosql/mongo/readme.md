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


