cmake_minimum_required(VERSION 2.8)

# CMakeLists for libyuv
# Originally created for "roxlu build system" to compile libyuv on windows
# Run with -DTEST=ON to build unit tests
option(TEST "Built unit tests" OFF)

set(ly_base_dir ${CMAKE_CURRENT_LIST_DIR})
set(ly_src_dir ${ly_base_dir})
set(ly_inc_dir ${ly_base_dir})
set(ly_lib_name "ijksdl")

add_subdirectory(${ly_base_dir}/ijkj4a)
add_subdirectory(${ly_base_dir}/ijkyuv)

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${ly_base_dir}/../../../../../libs/${ANDROID_ABI}/${ly_lib_name})

set(ly_source_files
    ${ly_src_dir}/ijksdl_aout.c
    ${ly_src_dir}/ijksdl_audio.c
    ${ly_src_dir}/ijksdl_egl.c
    ${ly_src_dir}/ijksdl_error.c
    ${ly_src_dir}/ijksdl_mutex.c
    ${ly_src_dir}/ijksdl_stdinc.c
    ${ly_src_dir}/ijksdl_thread.c
    ${ly_src_dir}/ijksdl_timer.c
    ${ly_src_dir}/ijksdl_vout.c
    ${ly_src_dir}/ijksdl_extra_log.c
    ${ly_src_dir}/gles2/color.c
    ${ly_src_dir}/gles2/common.c
    ${ly_src_dir}/gles2/renderer.c
    ${ly_src_dir}/gles2/renderer_rgb.c
    ${ly_src_dir}/gles2/renderer_yuv420p.c
    ${ly_src_dir}/gles2/renderer_yuv444p10le.c
    ${ly_src_dir}/gles2/shader.c
    ${ly_src_dir}/gles2/fsh/rgb.fsh.c
    ${ly_src_dir}/gles2/fsh/yuv420p.fsh.c
    ${ly_src_dir}/gles2/fsh/yuv444p10le.fsh.c
    ${ly_src_dir}/gles2/vsh/mvp.vsh.c

    ${ly_src_dir}/dummy/ijksdl_vout_dummy.c
    
    ${ly_src_dir}/ffmpeg/ijksdl_vout_overlay_ffmpeg.c
    ${ly_src_dir}/ffmpeg/abi_all/image_convert.c
    
    ${ly_src_dir}/android/android_audiotrack.c
    ${ly_src_dir}/android/android_nativewindow.c
    ${ly_src_dir}/android/ijksdl_android_jni.c
    ${ly_src_dir}/android/ijksdl_aout_android_audiotrack.c
    ${ly_src_dir}/android/ijksdl_aout_android_opensles.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediacodec_dummy.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediacodec_internal.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediacodec_java.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediacodec.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediadef.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediaformat_java.c
    ${ly_src_dir}/android/ijksdl_codec_android_mediaformat.c
    ${ly_src_dir}/android/ijksdl_vout_android_nativewindow.c
    ${ly_src_dir}/android/ijksdl_vout_android_surface.c
    ${ly_src_dir}/android/ijksdl_vout_overlay_android_mediacodec.c
    
)

#set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -std=c99")

include_directories(${ly_inc_dir}
                    ${ly_inc_dir}/..
                    ${ly_inc_dir}/../..
                    ${ly_inc_dir}/ijkj4a
                    ${ly_inc_dir}/ijkyuv/include
                    ${ly_inc_dir}/../ffmpeg/include
)

#add_library(ijkffmpeg STATIC IMPORTED)
#set_target_properties(ijkffmpeg PROPERTIES IMPORTED_LOCATION
#  ${distribution_DIR}/gmath/lib/${ANDROID_ABI}/libijkffmpeg.a
#)

#include(AndroidNdkModules)
#android_ndk_import_module_cpufeatures()

add_library(ijkffmpeg SHARED IMPORTED)
set_target_properties(ijkffmpeg PROPERTIES IMPORTED_LOCATION
${ly_base_dir}/../../../../../libs/${ANDROID_ABI}/libijkffmpeg.so)

add_library(${ly_lib_name} SHARED ${ly_source_files})

target_link_libraries(${ly_lib_name}
                        ijkffmpeg
                        ijkj4a
                        ijkyuv
                        log
                        android
                        OpenSLES
                        EGL
                        GLESv2
                       # cpufeatures
)