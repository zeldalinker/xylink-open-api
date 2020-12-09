#!/bin/bash
#offline jdk install
jdk_path="/usr/local"
install_path=$(cd `dirname $0`; pwd)
j=`whereis java`
java=$(echo ${j} | grep "jdk")
if [[ "$java" != "" ]]
then
    echo "java was installed!"
else
    echo "java not installed!"
    echo;
    echo;
    echo "解压 jdk-*-linux-x64.tar.gz"
    tar -zxvf jdk-*-linux-x64.tar.gz >/dev/null 2>&1
    echo;
    echo;
    cd jdk* && jdk_name=`pwd | awk -F '/' '{print $NF}'`
    echo "获取jdk版本: ${jdk_name}"
    echo;
    echo;
    cd ${install_path}
    echo "获取当前目录:${install_path}"
    echo;
    echo;
    mv ${jdk_name} ${jdk_path}
    echo "转移${jdk_name}文件到${jdk_path}安装目录"
    echo "jdk安装目录:${jdk_path}/${jdk_name}"
    echo;
    echo;
    echo "#java jdk" >> /etc/profile
    echo "export JAVA_HOME=${jdk_path}/${jdk_name}" >> /etc/profile
    echo 'export JRE_HOME=${JAVA_HOME}/jre' >> /etc/profile
    echo 'export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib' >> /etc/profile
    echo 'export PATH=${JAVA_HOME}/bin:$PATH' >> /etc/profile
    source /etc/profile > /dev/null 2>&1
    echo "jdk 安装完毕!"
    echo;
    echo;
    echo "请执行以下命令以使jdk环境生效"
    echo;
    echo;
    echo "source /etc/profile"
    echo;
    echo;
fi