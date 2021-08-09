# This shell should be run before gradle load, it prepares libindy.a file for KMP library
./gradlew :"PreparePods"
./gradlew :"CopyLibIndy"
