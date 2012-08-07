###
###    Copyright (C) 2012 Huseyin Kerem Cevahir <kerem@mydlp.com>
###
###--------------------------------------------------------------------------
###    This file is part of MyDLP.
###
###    MyDLP is free software: you can redistribute it and/or modify
###    it under the terms of the GNU General Public License as published by
###    the Free Software Foundation, either version 3 of the License, or
###    (at your option) any later version.
###
###    MyDLP is distributed in the hope that it will be useful,
###    but WITHOUT ANY WARRANTY; without even the implied warranty of
###    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
###    GNU General Public License for more details.
###
###    You should have received a copy of the GNU General Public License
###    along with MyDLP.  If not, see <http://www.gnu.org/licenses/>.
###--------------------------------------------------------------------------

mydlpuidir = $(datadir)/mydlp/ui

mydlpui_DATA = \
	conf/jetty-ajp.xml \
	conf/jetty.conf \
	conf/jetty.defaults \
	conf/solr.schema.xml \
	mydlp-ui-webapp/target/mydlp-ui-webapp.war

apachesitesdir = $(sysconfdir)/apache2/sites-available

solrlibdir = $(datadir)/solr/WEB-INF/lib

solrlib_DATA = \
	target/apache-solr-cell.jar

backenddir = $(datadir)/mydlp/backend

mydlp-ui-webapp/target/mydlp-ui-webapp.war: pom.xml
	@bash bootstrap.sh
	@$(MVN) -Pproduction install

target/apache-solr-cell.jar: pom.xml
	@mkdir -p target
	@$(MVN) dependency:get -DrepoUrl=http://repo1.maven.apache.org/maven2 -Dartifact=org.apache.solr:solr-cell:1.4.1
	@cp -af $(HOME)/.m2/repository/org/apache/solr/solr-cell/1.4.1/solr-cell-1.4.1.jar target/apache-solr-cell.jar

clean-local:
	@$(MVN) -Pproduction clean

install-data-hook:
	mkdir -p $(DESTDIR)$(apachesitesdir) && \
		cp -af conf/mydlp.apache-site $(DESTDIR)$(apachesitesdir)/mydlp
	mkdir -p $(DESTDIR)$(solrlibdir) && \
		ln -s $(backenddir)/mydlp-backend.jar $(DESTDIR)$(solrlibdir)/mydlp-backend.jar
	mkdir -p $(DESTDIR)$(solrlibdir) && \
		ln -s $(backenddir)/tika-xps.jar $(DESTDIR)$(solrlibdir)/tika-xps.jar

