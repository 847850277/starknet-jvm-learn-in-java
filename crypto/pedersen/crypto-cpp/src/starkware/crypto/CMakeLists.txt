add_subdirectory(ffi)

add_library(crypto elliptic_curve_constants.cc pedersen_hash.cc ecdsa.cc)
target_link_libraries(crypto algebra)

add_executable(elliptic_curve_constants_test elliptic_curve_constants_test.cc)
target_link_libraries(elliptic_curve_constants_test gtest crypto gtest_main gmock pthread)
add_test(elliptic_curve_constants_test elliptic_curve_constants_test)

add_executable(pedersen_hash_test pedersen_hash_test.cc)
target_link_libraries(pedersen_hash_test crypto gtest gtest_main pthread)
add_test(pedersen_hash_test pedersen_hash_test)

add_executable(ecdsa_test ecdsa_test.cc)
target_link_libraries(ecdsa_test crypto gtest gtest_main pthread)
add_test(ecdsa_test ecdsa_test)
