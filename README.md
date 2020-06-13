#### Build an executable JAR
<p>You can run the application by using:</p>
<pre><code>./mvnw spring-boot:run
</code></pre>
<p>Alternatively, you can build the JAR file with:</p>
<pre><code>./mvnw clean package
</code></pre>
<p>and then run the JAR file, as follows:</p>
<pre><code>java -jar target/stocks-table-back-0.0.1-SNAPSHOT.jar
</code></pre>
<p>Now that the application is running, you can test it. To start with, you can open a browser and go to</p>
<a href="http://localhost:9000/api">http://localhost:9000/api</a>