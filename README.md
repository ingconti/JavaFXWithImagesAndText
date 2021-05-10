# JavaFXWithImagesAndText

how to to use POM to build JAR


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

            <!-- end of added
           -->
        

 
 2) be sure to use YOUR main class:
  <mainClass>com.ingconti.Launcher</mainClass>
  
 3) do NOT try skip out Laucher class! it must be used to foce loading of inner class derived from:
    javafx.application.Application;
    
 4) pack it using MAVEN cmd in right Maven panel:
 
 mvn package

5) 

DO NOT run Java JAR in "red" folder, 
(error: "no main manifest attribute, in ...../target/JavaFXWithImages-1.0-SNAPSHOT.jar"")

RUN Java in "shade" YELLOW folder.
