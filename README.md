
To run the tests, you need Maven and the chosen browser driver installed locally. Parameters can be set either externally with Maven command or by editing test.properties file.

| Parameter                 | Default       | Description   |	
| :------------------------ |:-------------:| :-------------|
| -Dbrowser	       | chrome          | parameter for the browser with which the tests will run, either chrome or firefox
| -Ddriver.path      | D://install         | location the browser driver has been downloaded to 

Example:

```
mvn clean test -Dbrowser=chrome -Ddriver.path=D://install
```

