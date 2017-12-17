# IjkCmake
this is an android demo which use ijkplayer which is based on ffmpeg can play multi-kind format audio and video, ijkplayer use ndk-build to pack all this jni c/c++ libs, there in my project, i migrate these code from old way of compiling jni c/c++ codes to the new way of compiling which use CMake, this is more convinect for debuging project, but still leave libijkffmpeg.so use old way of building, libijkffmpeg is actually ffmpeg, it use ffmpeg source code, and compile by android ndktool ndk-build, android you can find this compile script at ijkplayer url, which name is compile-ffmpeg.sh, and you can find the way of use it at ijplayer url. the way of building libijkffmpeg.so will use CMake in the future. 

ijkplayer url
https://github.com/Bilibili/ijkplayer

ffmpeg url
https://github.com/FFmpeg/FFmpeg

这是一个android的例城，基于ijkplayer的播放器，ijkplayer是基于ffmpeg的封装，它适合多种格式的音视频编码格式的播放，ijkplayer使用的是ndk-build这种方式来打包jni层的so库，在这个例程中，将ijkplayer迁移到CMake这种编译方式，CMake这种编译方式，但是libijkffmpeg还是使用的ndk-build工具编译的，libijkffmpeg就是使用的ffmpeg的源码，由于代码量较多且复杂，暂时没有使用CMake来编译，以后会把它改写成CMake编译的方式，编译libijkffmpeg的方法可以在ijkplayer url中找到，脚本的名字为compile-ffmpeg.sh.

ijkplayer url
https://github.com/Bilibili/ijkplayer

ffmpeg url
https://github.com/FFmpeg/FFmpeg

