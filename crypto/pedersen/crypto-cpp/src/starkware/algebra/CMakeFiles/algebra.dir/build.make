# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.28

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/local/Cellar/cmake/3.28.3/bin/cmake

# The command to remove a file.
RM = /usr/local/Cellar/cmake/3.28.3/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto

# Include any dependencies generated for this target.
include pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/compiler_depend.make

# Include the progress variables for this target.
include pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/progress.make

# Include the compile flags for this target's objects.
include pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/flags.make

pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.o: pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/flags.make
pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.o: pedersen/crypto-cpp/src/starkware/algebra/prime_field_element.cc
pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.o: pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.o"
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.o -MF CMakeFiles/algebra.dir/prime_field_element.cc.o.d -o CMakeFiles/algebra.dir/prime_field_element.cc.o -c /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra/prime_field_element.cc

pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/algebra.dir/prime_field_element.cc.i"
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra/prime_field_element.cc > CMakeFiles/algebra.dir/prime_field_element.cc.i

pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/algebra.dir/prime_field_element.cc.s"
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra && /Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra/prime_field_element.cc -o CMakeFiles/algebra.dir/prime_field_element.cc.s

# Object files for target algebra
algebra_OBJECTS = \
"CMakeFiles/algebra.dir/prime_field_element.cc.o"

# External object files for target algebra
algebra_EXTERNAL_OBJECTS =

pedersen/crypto-cpp/src/starkware/algebra/libalgebra.a: pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/prime_field_element.cc.o
pedersen/crypto-cpp/src/starkware/algebra/libalgebra.a: pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/build.make
pedersen/crypto-cpp/src/starkware/algebra/libalgebra.a: pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --bold --progress-dir=/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX static library libalgebra.a"
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra && $(CMAKE_COMMAND) -P CMakeFiles/algebra.dir/cmake_clean_target.cmake
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/algebra.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/build: pedersen/crypto-cpp/src/starkware/algebra/libalgebra.a
.PHONY : pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/build

pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/clean:
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra && $(CMAKE_COMMAND) -P CMakeFiles/algebra.dir/cmake_clean.cmake
.PHONY : pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/clean

pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/depend:
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/DependInfo.cmake "--color=$(COLOR)"
.PHONY : pedersen/crypto-cpp/src/starkware/algebra/CMakeFiles/algebra.dir/depend

