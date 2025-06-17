


javac -cp ".;../lib/mysql/mysql-connector-j-9.3.0.jar;../src" -d ../class ../src/model/dao/*.java
java -cp "../class;../lib/mysql/mysql-connector-j-9.3.0.jar" model.dao.TestConnexion
