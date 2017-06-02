mvn clean package -Dmaven.test.skip -Prelease
scp -r target/root.war admin@123.206.181.24:/home/admin/jetty/webapps/