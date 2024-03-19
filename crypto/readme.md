# 修成CMakeLists.txt中JAVA_INCLUDE_PATH露肩

# 编译

```bash

cmake .

make

gcc -dynamiclib -I/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/include -I/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/include/darwin -shared -o JNIDemo.dylib JNIDemo.c

```

# 生成动态链接文件

# java.load指定动态链接文件