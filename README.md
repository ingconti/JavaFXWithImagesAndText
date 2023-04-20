# JavaFXWithImagesAndText

#How to use POM to build JAR


1) in POM add: (in plug-in section)

    <!-- added -->

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.2.0</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <shadedArtifactAttached>true</shadedArtifactAttached>
                            <shadedClassifierName>project-classifier</shadedClassifierName>
                            <outputFile>shade\${project.artifactId}.jar</outputFile>
                            <transformers>
                                <transformer implementation=
                                                     "org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.ingconti.Launcher</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <!-- end of added -->
        

 
 2) be sure to use YOUR main class:
    mine is:
    <mainClass>com.ingconti.Launcher</mainClass>
  
 3) do NOT try skip out Laucher class! it must be used to force loading of inner class derived from:
    javafx.application.Application.

    (for brave and curious... here that's why: http://mail.openjdk.java.net/pipermail/openjfx-dev/2018-June/021977.html)

 4) pack it using MAVEN cmd in right Maven panel:
 
    mvn package

5) DO NOT run Java JAR in "red" folder, 
  (error: "no main manifest attribute, in ...../target/JavaFXWithImages-1.0-SNAPSHOT.jar"")

  RUN Java in "shade" YELLOW folder.

  for cmd line:
  cd /Users/ingconti/IdeaProjects/JavaFXWithImagesAndText/shade
  java -jar JavaFXWithImages.jar

6) to run inside IJ, choose com.ingconti.Launcher as main class in "Configuration"

If You get:
WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @4d088d11'

don't panic. it's ok.


#==== JAVA FX SUPPORT


add:

        <!-- javaFX for M1 -->
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx</artifactId>
            <version>19</version>
            <type>pom</type>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>19</version>
        </dependency>

        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>19</version>
        </dependency>


        <dependency>
            <groupId>org.controlsfx</groupId>
            <artifactId>controlsfx</artifactId>
            <version>11.1.2</version>
        </dependency>


        <!-- end of javaFX for M1 -->


#How use JSON Parser:

I) add in POM:

    <!-- jGson-->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.9.0</version>
        </dependency>


II) See code in God AND in tests.


