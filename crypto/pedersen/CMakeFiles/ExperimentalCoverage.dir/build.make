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

# Utility rule file for ExperimentalCoverage.

# Include any custom commands dependencies for this target.
include pedersen/CMakeFiles/ExperimentalCoverage.dir/compiler_depend.make

# Include the progress variables for this target.
include pedersen/CMakeFiles/ExperimentalCoverage.dir/progress.make

pedersen/CMakeFiles/ExperimentalCoverage:
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen && /usr/local/Cellar/cmake/3.28.3/bin/ctest -D ExperimentalCoverage

ExperimentalCoverage: pedersen/CMakeFiles/ExperimentalCoverage
ExperimentalCoverage: pedersen/CMakeFiles/ExperimentalCoverage.dir/build.make
.PHONY : ExperimentalCoverage

# Rule to build all files generated by this target.
pedersen/CMakeFiles/ExperimentalCoverage.dir/build: ExperimentalCoverage
.PHONY : pedersen/CMakeFiles/ExperimentalCoverage.dir/build

pedersen/CMakeFiles/ExperimentalCoverage.dir/clean:
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen && $(CMAKE_COMMAND) -P CMakeFiles/ExperimentalCoverage.dir/cmake_clean.cmake
.PHONY : pedersen/CMakeFiles/ExperimentalCoverage.dir/clean

pedersen/CMakeFiles/ExperimentalCoverage.dir/depend:
	cd /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/CMakeFiles/ExperimentalCoverage.dir/DependInfo.cmake "--color=$(COLOR)"
.PHONY : pedersen/CMakeFiles/ExperimentalCoverage.dir/depend

