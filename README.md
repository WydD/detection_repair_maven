detection_repair_maven
======================

Maven Project with services which correspond on the Change Detection and Validation/Repair Modules 

Configurations Notes
=====================
Folder Config Files contains two sample configuration files
 - change detection parameters for multidimensional model (md_config.properties)
 - change detection parameters for ontological model (config.properties)

If someone plans to use a diferrent virtuoso triple store to store the diachron dataset versions, then the repository fields (i.e., Repository_IP, Repository_Username etc.) should change w.r.t. the Virtuoso setting. 

All the detected changes are stored in named graph which is denoted by Changes_Ontology field. Moreover in our Virtuoso, we have stored the definition of changes in a separate named graph whish is denoted by the field Changes_Ontology_Schema. Again, if someone plans to use a different triple store, make sure to import the ontology of change definitions before the change detection process. The corresponding files for both the ontological model and multidimensional can be found in folder: Changes_Ontology_Schema.  

Finally field Simple_Changes_Folder refers on tomcat-accessible folder which contains the SPARQL query templates which will be used for the change detection process. This field has to be changed to a new accessible path w.r.t. the new deployment setting of each data pilot. 

Module Setup and Deployment
===========================
The project is fully compliant with Maven and can be built with a mvn install. The two additional dependencies are automatically installed at the initialization phase.

Deployment can be made into any web-application container (jetty, tomcat, jboss). The configuration file is loaded in ```./config.properties```
