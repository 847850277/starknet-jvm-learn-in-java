file(REMOVE_RECURSE
  "libposeidon.dylib"
  "libposeidon.pdb"
)

# Per-language clean rules from dependency scanning.
foreach(lang C)
  include(CMakeFiles/poseidon.dir/cmake_clean_${lang}.cmake OPTIONAL)
endforeach()
