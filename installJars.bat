call mvn install:install-file -Dfile="Libraries\ValidationRepair.jar" -DartifactId=validation-repair -DgroupId=org.diachron.repair.master -Dversion=1.0 -Dpackaging=jar -DpomFile=pom.xml
call mvn install:install-file -Dfile="Libraries\ChangeDetection.jar" -DartifactId=change-detection -DgroupId=org.diachron.detection -Dversion=1.0 -Dpackaging=jar -DpomFile=pom.xml