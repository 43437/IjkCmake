cmake_minimum_required(VERSION 2.8)

option(TEST "Built unit tests" OFF)

set(ly_base_dir ${CMAKE_CURRENT_LIST_DIR})
set(ly_src_dir ${ly_base_dir})
set(ly_inc_dir ${ly_base_dir})
set(ly_lib_name "ijkj4a")

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${ly_base_dir}/../../../../../../libs/${ANDROID_ABI}/${ly_lib_name})

set(ly_source_files
  ${ly_src_dir}/j4a/j4a_allclasses.c
  ${ly_src_dir}/j4a/j4a_base.c
  ${ly_src_dir}/j4a/class/android/media/AudioTrack.c
  ${ly_src_dir}/j4a/class/android/media/MediaCodec.c
  ${ly_src_dir}/j4a/class/android/media/MediaFormat.c
  ${ly_src_dir}/j4a/class/android/media/PlaybackParams.c
  ${ly_src_dir}/j4a/class/android/os/Build.c
  ${ly_src_dir}/j4a/class/android/os/Bundle.c
  ${ly_src_dir}/j4a/class/java/nio/Buffer.c
  ${ly_src_dir}/j4a/class/java/nio/ByteBuffer.c
  ${ly_src_dir}/j4a/class/java/util/ArrayList.c
  ${ly_src_dir}/j4a/class/tv/danmaku/ijk/media/player/misc/IMediaDataSource.c
  ${ly_src_dir}/j4a/class/tv/danmaku/ijk/media/player/misc/IAndroidIO.c
  ${ly_src_dir}/j4a/class/tv/danmaku/ijk/media/player/IjkMediaPlayer.c
  ${ly_src_dir}/j4au/class/android/media/AudioTrack.util.c
  ${ly_src_dir}/j4au/class/java/nio/ByteBuffer.util.c
)

include_directories(${ly_inc_dir})
add_library(${ly_lib_name} STATIC ${ly_source_files})

#include(AndroidNdkModules)
#android_ndk_import_module_cpufeatures()

target_link_libraries(${ly_lib_name}
                        #cpufeatures
                        android
                        log
)