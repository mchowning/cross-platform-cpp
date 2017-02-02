# Sets the minimum version of CMake required to build the native
# library. You should either keep the default value or only pass a
# value of 3.4.0 or lower.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds it for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             # Associated headers in the same location as their source
             # file are automatically included.
             src/main/cpp/native-lib.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in the
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )

################################ CUSTOM ###################################################

# add_library(libhelloworld SHARED ../../../generated-src/jni/NativeHelloWorld.cpp)
# add_library(libhelloworld SHARED ../../../generated-src/jni/)
# add_library(libhelloworld SHARED ../../../generated-src/cpp/)
# include_directories(../../../generated-src/jni)


set(root_dir ../..)
set(support_dir ${root_dir}/deps/djinni/support-lib/jni)
set(my_include_dirs ${root_dir}/generated-src/jni/ ${root_dir}/generated-src/cpp/ ${root_dir}/src/cpp/)

message(STATUS "my_include_dirs = ${my_include_dirs}")

file(
    GLOB_RECURSE support_srcs
    ${support_dir}/*.cpp)

message(STATUS "support_srcs = ${support_srcs}")

file(
    GLOB_RECURSE my_srcs
    ${root_dir}/generated-src/jni/*.cpp
    ${root_dir}/generated-src/cpp/*.cpp
    ${root_dir}/src/cpp/*.cpp)

message(STATUS "my_srcs = ${my_srcs}")

# set(textsort_common_flags "-Wall -Werror -std=c++1y")
# set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${textsort_common_flags}")
# if(UNIX OR APPLE)
#   set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")
# endif()

set(all_srcs ${support_srcs} ${my_srcs})

message(STATUS "all_srcs = ${all_srcs}")

add_library(libhelloworld SHARED ${all_srcs})
include_directories(
    libhelloworld
    ${my_include_dirs}
    ${support_dir}
    ${JNI_INCLUDE_DIRS})
# target_link_libraries(TextSortNative ${JNI_LIBRARIES})
# install(
#   TARGETS TextSortNative
#   ARCHIVE DESTINATION "${LIB_INSTALL_DIR}"
#   LIBRARY DESTINATION "${LIB_INSTALL_DIR}")
