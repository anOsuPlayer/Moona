#include "nativelinkerimpl.hpp"

JavaImpl jlong Java_moonaframework_hallway_NativeLinker_constructNative(StaticArgs, jlong objCode) {
    return 123;
}

JavaImpl jlong Java_moonaframework_hallway_NativeLinker_constructNativeArray(StaticArgs, jlong objCode, jint size) {
    return 124;
}

JavaImpl void Java_moonaframework_hallway_NativeLinker_destroyNative(StaticArgs, jlong address) {

}

JavaImpl void Java_moonaframework_hallway_NativeLinker_destroyNativeArray(StaticArgs, jlong address) {

}