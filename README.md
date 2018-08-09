
#    APPLICATION DETAILS
#				This application is meant for maintaining the advertiser inventory where in each advertiser are identified by the 
# Unique Name and each advertiser is given with an unique Id.
# There can be 3 status available .ACTIVE,INACTIVE,PAUSED. This is for future purposes. Currently we are just capturing the info
# This field can be used to do a soft delete instead of a hard delete.
#
#Future enhancements possible is to have a tenant based approach added to our implementation.Each tenant(IHEARTCOMMUNICATION,
#IHEARTMEDIA,IHEARTCAPITALS) can maintain its own advertiser list where in we can share the same application and database.
#  
#Advertiser Repository Info/Important URLs

#Embedded H2 Console: http://localhost:8080/h2-console
#Embedded H2 JDBC url: jdbc:h2:mem:testdb

#Swagger-UI url: http://localhost:8080/swagger-ui.html

#Health Check url: http://localhost:8090/actuator/health

#Actuator url: http://localhost:8090/actuator/info


#To run the app:

#Go the project root folder and run
#mvn install. A jar file will be generated under target folder in root of project
#java -jar target/XXXXXX.jar
#Make sure maven & Java is there PATH of your system to do above operation							
#
#getAlladvetisers is a functionality which we can build for CSR/business to pull up the reports, But haven't implemented.
#			
#         For implementing the features we have used 
#				Spring boot 2.0.4
#              H2 JDBC
#              MyBatis ORM
#				                
#
#            Next Action Item:
#				Manage H2 Schema via Flyway
#				Build the resulting jar into a Dockerfile