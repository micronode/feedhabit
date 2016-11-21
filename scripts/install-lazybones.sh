#sudo apt-get update && sudo apt-get install -y openjdk-7-jdk
sudo add-apt-repository ppa:webupd8team/java && sudo apt-get update
sudo apt-get install oracle-java8-installer && sudo apt-get install oracle-java8-set-default

curl -s "https://get.sdkman.io" | bash && source "/home/ubuntu/.sdkman/bin/sdkman-init.sh"
sdk install lazybones
