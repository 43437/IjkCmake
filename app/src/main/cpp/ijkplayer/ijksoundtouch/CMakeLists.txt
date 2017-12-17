cmake_minimum_required(VERSION 2.8)

option(TEST "Built unit tests" OFF)

set(CMAKE_VERBOSE_MAKEFILE on)

set(ly_base_dir ${CMAKE_CURRENT_LIST_DIR})
set(ly_src_dir ${ly_base_dir})
set(ly_inc_dir ${ly_base_dir}/)
set(ly_lib_name "ijksoundtouch")

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${ly_base_dir}/../../../../libs/${ANDROID_ABI})

set(ly_source_files
  ${ly_src_dir}/source/SoundTouch/AAFilter.cpp
  ${ly_src_dir}/source/SoundTouch/FIFOSampleBuffer.cpp
  ${ly_src_dir}/source/SoundTouch/FIRFilter.cpp
  ${ly_src_dir}/source/SoundTouch/cpu_detect_x86.cpp
  ${ly_src_dir}/source/SoundTouch/sse_optimized.cpp
  ${ly_src_dir}/source/SoundTouch/RateTransposer.cpp
  ${ly_src_dir}/source/SoundTouch/InterpolateCubic.cpp
  ${ly_src_dir}/source/SoundTouch/InterpolateLinear.cpp
  ${ly_src_dir}/source/SoundTouch/InterpolateShannon.cpp
  ${ly_src_dir}/source/SoundTouch/TDStretch.cpp
  ${ly_src_dir}/source/SoundTouch/BPMDetect.cpp
  ${ly_src_dir}/source/SoundTouch/PeakFinder.cpp
  ${ly_src_dir}/source/SoundTouch/SoundTouch.cpp
  ${ly_src_dir}/source/SoundTouch/mmx_optimized.cpp
  ${ly_src_dir}/ijksoundtouch_wrap.cpp
)

#set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

include_directories(${ly_inc_dir}
                    ${ly_inc_dir}/include
)

message(${CMAKE_SOURCE_DIR})

set(distribution_DIR ${ly_base_dir}/../../../../../libs)

#set_target_properties(${ly_lib_name} PROPERTIES IMPORTED_LOCATION
#  ${distribution_DIR}/${ANDROID_ABI}/lib${ly_lib_name}.so)


add_library(${ly_lib_name} STATIC ${ly_source_files})

include(AndroidNdkModules)
android_ndk_import_module_cpufeatures()


target_link_libraries(${ly_lib_name}
                        cpufeatures
)