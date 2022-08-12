# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#实体---------------------
-keep class com.startpineapple.kbllivesdk.bean.**{*;}
-keep class com.startpineapple.kbllivesdk.enums.**{*;}

-keep class com.ryjoin.livesdk.data.**{*;}
-keep class com.ryjoin.livesdk.liveView.RyjoinLeftScreenPagerView{public <methods> ;}
-keep class com.ryjoin.livesdk.**{*;}
#--------------------------------

#material androidx--------------------------------
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
#--------------------------------

#ARouter---------------------
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
#--------------------------------

#BaseRecyclerViewAdapterHelper---------------------
-keep public class * extends com.chad.library.adapter.base.viewholder.BaseViewHolder{
 <init>(...);
}
-keepclassmembers  class **$** extends com.chad.library.adapter.base.viewholder.BaseViewHolder {
     <init>(...);
}