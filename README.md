# Rest-Train-Reservation

			Jersey 2 et Swagger 
Exemple de projet:  RestWebserviceJersey et Rest-Train-Reservation

1- Téléchager swagger-ui et copier le contenue du fichier dist dans le webapp de mon projet

2- dans le pom mettre
     <dependencies>
    	<!--jersey-->
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-server</artifactId>
            <version>1.19</version>
        </dependency>
        <!--pour récupérer le json produit dans les services-->
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-json</artifactId>
            <version>1.19</version>
        </dependency>
    	
        <!-- other dependency -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>${servlet-api-version}</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- swagger -->
        <dependency>
            <groupId>com.wordnik</groupId>
            <artifactId>swagger-jersey-jaxrs_2.9.1</artifactId>
            <version>${swagger-version}</version>
        </dependency>
        <dependency>
            <groupId>com.wordnik</groupId>
            <artifactId>swagger-annotations_2.9.1</artifactId>
            <version>${swagger-version}</version>
        </dependency>
        <dependency>
            <groupId>com.wordnik</groupId>
            <artifactId>swagger-core_2.9.1</artifactId>
            <version>${swagger-version}</version>
        </dependency>
        <dependency>
            <groupId>com.wordnik</groupId>
            <artifactId>swagger-jaxrs_2.9.1</artifactId>
            <version>${swagger-version}</version>
        </dependency>
    </dependencies>

3- Dans le web.xml rajouter dans les param-value les paramètres: com.wordnik.swagger.jaxrs.json ou com.wordnik.swagger.jersey.listing et adapter la config swager à notre choix
 (lire commentaire ci dessous)

 <!-- Jersey Servlet configurations -->
    <servlet>
        <servlet-name>Jersey REST Service</servlet-name>
        <servlet-class>com.sun.jersey.spi.container.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>com.sun.jersey.config.property.packages</param-name>
            <!--com.wordnik.swagger.jersey.listing permet de dire que dans l'index.html de swagger
            on récupèrera les data en mettant dans l'url api-docs -->
            <!--com.wordnik.swagger.jaxrs.json, permet de dire que dans l'index.html de swagger
            on récupèrera les data en mettant dans l'url swagger.json -->
            <param-value> com.wordnik.swagger.jersey.listing, com.ymagis.restwebservicejersey.service</param-value>
        </init-param>
    
        <init-param>
            <param-name>jersey.config.server.provider.classnames</param-name>
            <param-value>
                com.wordnik.swagger.jersey.listing.ApiListingResourceJSON,
                com.wordnik.swagger.jersey.listing.JerseyApiDeclarationProvider,
                com.wordnik.swagger.jersey.listing.JerseyResourceListingProvider
            </param-value>
        </init-param>
        
        <load-on-startup>1</load-on-startup>
    </servlet>



4- ajouter dans le web.xml la nouvelle servlet

<!--servlet de swagger-->
<servlet>
        <servlet-name>Jersey2Config</servlet-name>
        <servlet-class>io.swagger.jaxrs.config.DefaultJaxrsConfig</servlet-class>
        <init-param>
            <param-name>api.version</param-name>
            <param-value>1.0.0</param-value>
        </init-param>
        <init-param>
            <param-name>swagger.api.basepath</param-name>
            <param-value>http://localhost:8080/rest</param-value>
        </init-param>
        <load-on-startup>2</load-on-startup>
    </servlet>

5- rajouter les annotations swagger (@Api,@ApiOperation and @ApiParam ) pour décrire dans swagger la classe qui a les services ou les ressources. cf mon projet 

6- remplacer dans index.html de swagger l'url api-docs ou swagger.json en fonction du choix fait dans le web.xml (cf 3)
 donc si mon site est configuré dans le web.xml pour tourner sur le serveur (point d'entrée) http://localhost:8080/person-rest/rest
 alors dans url j'aurai 
 url = "/person-rest/rest/api-docs";
ou 
url = "/person-rest/rest/swagger.json";

7 après avoir buider déployer sur tomcat et charger pour avoir l'interface swagger

Liens utiles:
http://riteshkrmodi.blogspot.fr/2014/06/swagger-integration-jersey.html
https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup