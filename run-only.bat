cmd /c "cd mydlp-ui-webapp\hsqldb && del /q MyDLP.script MyDLPLog.script && copy MyDLP.sample.log MyDLP.log && copy MyDLPLog.sample.log MyDLPLog.log"
cmd /c "cd mydlp-ui-webapp && mvn jetty:run-war"
