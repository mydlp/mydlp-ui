cmd /c "mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate-core -Dpackaging=jar -Dversion=2.0-RC6 -Dfile=external-lib\dpHibernate-core-2.0-RC6.jar -DgeneratePom=true"

cmd /c "mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate-springExtensions3.0 -Dpackaging=jar -Dversion=2.0-RC6 -Dfile=external-lib\dpHibernate-springExtensions3.0-2.0-RC6.jar -DgeneratePom=true"

cmd /c "mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate -Dpackaging=swc -Dversion=2.0-RC6 -Dfile=external-lib\dpHibernate-2.0-RC6.swc -DgeneratePom=true"

cmd /c "mvn install:install-file -DgroupId=AlivePDF -DartifactId=AlivePDF -Dpackaging=swc -Dversion=0.1.5-RC -Dfile=external-lib\AlivePDF-0.1.5-RC.swc -DgeneratePom=true"
