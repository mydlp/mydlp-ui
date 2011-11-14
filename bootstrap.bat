cmd /c "mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate-core -Dpackaging=jar -Dversion=2.0-RC6 -Dfile=external-lib\dpHibernate-core-2.0-RC6.jar -DgeneratePom=true"

cmd /c "mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate-springExtensions3.0 -Dpackaging=jar -Dversion=2.0-RC6 -Dfile=external-lib\dpHibernate-springExtensions3.0-2.0-RC6.jar -DgeneratePom=true"

cmd /c "mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate -Dpackaging=swc -Dversion=2.0-RC6 -Dfile=external-lib\dpHibernate-2.0-RC6.swc -DgeneratePom=true"

cmd /c "mvn clean"

cmd /c "cd mydlp-ui-domain-as3\src\main\flex\com\mydlp\ui\domain && del /q *"

cmd /c "mvn install"

cmd /c "cd mydlp-ui-domain && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-dao && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-remoting-blazeds && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-webapp && mvn eclipse:eclipse"

cmd /c "cd mydlp-ui-domain-as3 && mvn org.sonatype.flexmojos:flexmojos-maven-plugin:3.9:flashbuilder"

cmd /c "cd mydlp-ui-flex && mvn org.sonatype.flexmojos:flexmojos-maven-plugin:3.9:flashbuilder"

