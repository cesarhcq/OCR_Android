LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := ndktest
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/home/cesar/AndroidStudioProjects/OCR8/app/src/main/jni/main.c \
	/home/cesar/AndroidStudioProjects/OCR8/app/src/main/jni/Android.mk \
	/home/cesar/AndroidStudioProjects/OCR8/app/src/main/jni/Application.mk \
	/home/cesar/AndroidStudioProjects/OCR8/app/src/main/jniLibs/armeabi-v7a/libopencv_java3.so \
	/home/cesar/AndroidStudioProjects/OCR8/app/src/main/jniLibs/x86_64/libopencv_java3.so \

LOCAL_C_INCLUDES += /home/cesar/AndroidStudioProjects/OCR8/app/src/main/jni
LOCAL_C_INCLUDES += /home/cesar/AndroidStudioProjects/OCR8/app/src/main/jniLibs
LOCAL_C_INCLUDES += /home/cesar/AndroidStudioProjects/OCR8/app/src/debug/jni

include $(BUILD_SHARED_LIBRARY)
