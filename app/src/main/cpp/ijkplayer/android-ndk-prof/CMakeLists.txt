cmake_minimum_required(VERSION 2.8)

option(TEST "Built unit tests" OFF)

set(ly_base_dir ${CMAKE_CURRENT_LIST_DIR})
set(ly_src_dir ${ly_base_dir})
set(ly_inc_dir ${ly_base_dir})
set(ly_lib_name "android-ndk-prof")

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${ly_base_dir}/../../../../../libs/${ANDROID_ABI}/${ly_lib_name})

set(ly_source_files
  ${ly_src_dir}/prof.c
)

include_directories(${ly_inc_dir})
add_library(${ly_lib_name} STATIC ${ly_source_files})