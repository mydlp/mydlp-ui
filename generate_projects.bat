cmd /c "bootstrap.bat"

cmd /c "mvn clean"

cmd /c "mvn install"

cmd /c "cd mydlp-ui-domain && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-dao && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-remoting-blazeds && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-thrift && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-webapp && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-domain-as3 && mvn org.sonatype.flexmojos:flexmojos-maven-plugin:3.9:flashbuilder"

cmd /c "cd mydlp-ui-flex && mvn org.sonatype.flexmojos:flexmojos-maven-plugin:3.9:flashbuilder"

cmd /c "cd mydlp-ui-flex-framework && mvn org.sonatype.flexmojos:flexmojos-maven-plugin:3.9:flashbuilder"

