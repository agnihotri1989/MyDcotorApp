# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# SLF4J rules
-dontwarn org.slf4j.impl.StaticLoggerBinder

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
#-renamesourcefileattribute

# 1. Keep KotlinX Serialization Data Models
# Taaki json parsing ke time keys match ho sakein
-keepattributes *Annotation*,Signature,InnerClasses
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

# 2. Ktor Client Rules
-keep class io.ktor.** { *; }
-dontwarn java.lang.management.**
-dontwarn kotlinx.coroutines.debug.DebugProbesImpl

# 3. Supabase Rules
-keep class jetbrains.supabase.** { *; }

# 4. BuildConfig Keep Rules (Taaki aapke API Keys aur Secrets safe rahein)
-keep class com.kshitiz.mydoctorapp.BuildConfig { *; }

# 5. Line numbers retain karein taaki crash logs readable rahein mapping file ke saath
-keepattributes SourceFile,LineNumberTable
