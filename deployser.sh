mvn clean package -Dmaven.test.skip -Prelease
scp -r target/root.war root@118.89.150.174:/home/admin/jetty/webapps/