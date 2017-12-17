cmake_minimum_required(VERSION 2.8)

option(TEST "Built unit tests" OFF)

set(ly_base_dir ${CMAKE_CURRENT_LIST_DIR})
set(ly_src_dir ${ly_base_dir})
set(ly_inc_dir ${ly_base_dir})

add_subdirectory(${ly_base_dir}/ijksdl)
add_subdirectory(${ly_base_dir}/android-ndk-prof)
add_subdirectory(${ly_base_dir}/ijksoundtouch)

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${ly_base_dir}/../../../../libs/${ANDROID_ABI}/${ly_lib_name})

set(ly_lib_name "ijkplayer")

set(ly_source_files
  ${ly_src_dir}/ff_cmdutils.c
  ${ly_src_dir}/ff_ffplay.c
  ${ly_src_dir}/ff_ffpipeline.c
  ${ly_src_dir}/ff_ffpipenode.c
  ${ly_src_dir}/ijkmeta.c
  ${ly_src_dir}/ijkplayer.c
  ${ly_src_dir}/pipeline/ffpipeline_ffplay.c
  ${ly_src_dir}/pipeline/ffpipenode_ffplay_vdec.c
  ${ly_src_dir}/android/ffmpeg_api_jni.c
  ${ly_src_dir}/android/ijkplayer_android.c
  ${ly_src_dir}/android/ijkplayer_jni.c
  ${ly_src_dir}/android/pipeline/ffpipeline_android.c
  ${ly_src_dir}/android/pipeline/ffpipenode_android_mediacodec_vdec.c
  ${ly_src_dir}/ijkavformat/allformats.c
  ${ly_src_dir}/ijkavformat/ijklivehook.c
  ${ly_src_dir}/ijkavformat/ijkmediadatasource.c
  ${ly_src_dir}/ijkavformat/ijkio.c
  ${ly_src_dir}/ijkavformat/ijkiomanager.c
  ${ly_src_dir}/ijkavformat/ijkiocache.c
  ${ly_src_dir}/ijkavformat/ijkioffio.c
  ${ly_src_dir}/ijkavformat/ijkioandroidio.c
  ${ly_src_dir}/ijkavformat/ijkioprotocol.c
  ${ly_src_dir}/ijkavformat/ijkioapplication.c
  ${ly_src_dir}/ijkavformat/ijkiourlhook.c
  ${ly_src_dir}/ijkavformat/ijkasync.c
  ${ly_src_dir}/ijkavformat/ijkurlhook.c
  ${ly_src_dir}/ijkavformat/ijklongurl.c
  ${ly_src_dir}/ijkavformat/ijksegment.c
  ${ly_src_dir}/ijkavutil/ijkdict.c
  ${ly_src_dir}/ijkavutil/ijkutils.c
  ${ly_src_dir}/ijkavutil/ijkthreadpool.c
  ${ly_src_dir}/ijkavutil/ijktree.c
  ${ly_src_dir}/ijkavutil/ijkfifo.c
  ${ly_src_dir}/ijkavutil/ijkstl.cpp
)


add_library("ijkffmpeg" SHARED IMPORTED)
set_target_properties(ijkffmpeg PROPERTIES IMPORTED_LOCATION
${ly_base_dir}/../../../../libs/${ANDROID_ABI}/libijkffmpeg.so)

include_directories(${ly_inc_dir}
                    ${ly_inc_dir}/..
                    ${ly_inc_dir}/ffmpeg/include
                    ${ly_base_dir}/ijksdl/ijkj4a)

#add_custom_command(TARGET add POST_BUILD
#                   COMMAND ${CMAKE_COMMAND} -E
#                   copy ${ly_base_dir}/../../../libs/${ANDROID_ABI}/libijkffmpeg.so
#                   ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libadd.so

add_library(${ly_lib_name} SHARED ${ly_source_files})

target_link_libraries(
  ${ly_lib_name}
  ijkffmpeg
  ijksdl
  android-ndk-prof
  ijksoundtouch
  log
  android
)

#file(COPY ${ly_base_dir}/ffmpeg/${ANDROID_ABI}/libijkffmpeg.so DESTINATION ${ly_base_dir}/../../../../build/intermediates/cmake/${ANDROID_ABI}/)