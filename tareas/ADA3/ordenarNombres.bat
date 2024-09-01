@echo off
cd .\src\
cd .\domain\
javac LinesDatasource.java
Javac LinesRepository.java
cd ..
cd .\controller\
javac Organizator.java
cd ..
cd .\infrastructure\
javac LinesRepositoryImpl.java
javac Reader.java
javac TextFileLinesDatasourceImpl.java
cd ..
javac Main.java
java Main
pause