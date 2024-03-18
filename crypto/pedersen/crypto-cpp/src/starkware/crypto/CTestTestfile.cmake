# CMake generated Testfile for 
# Source directory: /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto
# Build directory: /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto
# 
# This file includes the relevant testing commands required for 
# testing this directory and lists subdirectories to be tested as well.
add_test(elliptic_curve_constants_test "elliptic_curve_constants_test")
set_tests_properties(elliptic_curve_constants_test PROPERTIES  _BACKTRACE_TRIPLES "/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto/CMakeLists.txt;8;add_test;/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto/CMakeLists.txt;0;")
add_test(pedersen_hash_test "pedersen_hash_test")
set_tests_properties(pedersen_hash_test PROPERTIES  _BACKTRACE_TRIPLES "/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto/CMakeLists.txt;12;add_test;/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto/CMakeLists.txt;0;")
add_test(ecdsa_test "ecdsa_test")
set_tests_properties(ecdsa_test PROPERTIES  _BACKTRACE_TRIPLES "/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto/CMakeLists.txt;16;add_test;/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/crypto/pedersen/crypto-cpp/src/starkware/crypto/CMakeLists.txt;0;")
subdirs("ffi")
