@echo off
"C:\\Users\\Kania Galih Widowati\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\cmake.exe" ^
  "-HD:\\My Doc\\BINUS\\SEMS 5\\COMPUTER VISION\\StuntMed\\OpenCV4\\libcxx_helper" ^
  "-DCMAKE_SYSTEM_NAME=Android" ^
  "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
  "-DCMAKE_SYSTEM_VERSION=21" ^
  "-DANDROID_PLATFORM=android-21" ^
  "-DANDROID_ABI=arm64-v8a" ^
  "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
  "-DANDROID_NDK=C:\\Users\\Kania Galih Widowati\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_ANDROID_NDK=C:\\Users\\Kania Galih Widowati\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393" ^
  "-DCMAKE_TOOLCHAIN_FILE=C:\\Users\\Kania Galih Widowati\\AppData\\Local\\Android\\Sdk\\ndk\\25.1.8937393\\build\\cmake\\android.toolchain.cmake" ^
  "-DCMAKE_MAKE_PROGRAM=C:\\Users\\Kania Galih Widowati\\AppData\\Local\\Android\\Sdk\\cmake\\3.22.1\\bin\\ninja.exe" ^
  "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\My Doc\\BINUS\\SEMS 5\\COMPUTER VISION\\StuntMed\\OpenCV4\\build\\intermediates\\cxx\\Debug\\4e6y4x3h\\obj\\arm64-v8a" ^
  "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\My Doc\\BINUS\\SEMS 5\\COMPUTER VISION\\StuntMed\\OpenCV4\\build\\intermediates\\cxx\\Debug\\4e6y4x3h\\obj\\arm64-v8a" ^
  "-DCMAKE_BUILD_TYPE=Debug" ^
  "-BD:\\My Doc\\BINUS\\SEMS 5\\COMPUTER VISION\\StuntMed\\OpenCV4\\.cxx\\Debug\\4e6y4x3h\\arm64-v8a" ^
  -GNinja ^
  "-DANDROID_STL=c++_shared"
