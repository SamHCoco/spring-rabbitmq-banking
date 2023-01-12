#!/bin/zsh
echo "Building ACCOUNT service Docker image:"
cd account
mvn compile jib:dockerBuild
echo "Succesfully built ACCOUNT service docker image"
echo "------------------------------------------------------------------------"
cd ..

echo "Building CREDIT service Docker image:"
cd credit
mvn compile jib:dockerBuild
echo "Succesfully built CREDIT service docker image"
echo "------------------------------------------------------------------------"
cd ..

echo "Building TRANSFER service Docker image:"
cd transfer
mvn compile jib:dockerBuild
echo "Succesfully built TRANSFER service docker image"
echo "------------------------------------------------------------------------"
cd ..