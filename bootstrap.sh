#!/bin/bash

if [ ! -f "$HOME/.m2/repository/dpHibernate/dpHibernate-core/2.0-RC6/dpHibernate-core-2.0-RC6.jar" ]; then
	mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate-core -Dpackaging=jar -Dversion=2.0-RC6 -Dfile=external-lib/dpHibernate-core-2.0-RC6.jar -DgeneratePom=true
fi

if [ ! -f "$HOME/.m2/repository/dpHibernate/dpHibernate-springExtensions3.0/2.0-RC6/dpHibernate-springExtensions3.0-2.0-RC6.jar" ]; then
	mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate-springExtensions3.0 -Dpackaging=jar -Dversion=2.0-RC6 -Dfile=external-lib/dpHibernate-springExtensions3.0-2.0-RC6.jar -DgeneratePom=true
fi

if [ ! -f "$HOME/.m2/repository/dpHibernate/dpHibernate/2.0-RC6/dpHibernate-2.0-RC6.swc" ]; then
	mvn install:install-file -DgroupId=dpHibernate -DartifactId=dpHibernate -Dpackaging=swc -Dversion=2.0-RC6 -Dfile=external-lib/dpHibernate-2.0-RC6.swc -DgeneratePom=true
fi

if [ ! -f "$HOME/.m2/repository/AlivePDF/AlivePDF/0.1.5-RC/AlivePDF-0.1.5-RC.swc" ]; then
	mvn install:install-file -DgroupId=AlivePDF -DartifactId=AlivePDF -Dpackaging=swc -Dversion=0.1.5-RC -Dfile=external-lib/AlivePDF-0.1.5-RC.swc -DgeneratePom=true
fi

