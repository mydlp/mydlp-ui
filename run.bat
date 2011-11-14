cmd /c "mvn install"
cmd /c "cd mydlp-ui-webapp\hsqldb && del /q MyDLP.script && copy MyDLP.sample.log MyDLP.log && copy MyDLP.sample.properties MyDLP.properties"
cmd /c "cd mydlp-ui-webapp && mvn jetty:run-war"
