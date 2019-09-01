#include "engenoid_tessocrtest_MainActivity.h"

JNIEXPORT jstring JNICALL Java_engenoid_tessocrtest_FotoFrameWork_callNative
        (JNIEnv* env, jobject object){

    return (*env) -> NewStringUTF(env, "With great power, comes great responsibility.");
}
