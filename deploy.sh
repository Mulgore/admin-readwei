mvn clean package -Dmaven.test.skip -Prelease
scp -r target/root.war root@123.206.231.180:/opt/reawei/webapps/
