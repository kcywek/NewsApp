object ApplicationId {
    const val packageName = "com.test.newsapp"
}

object Versions {
    const val compileSdk = 29
    const val minSdk = 22
    const val targetSdk = 29
    const val kotlin = "1.5.0"
    const val gradle = "4.2.2"
    const val googlePlayServices = "4.3.8"
    const val junitPlugin = "1.7.1.1"
    const val dataDog = "1.9.1"
    const val datadogPlugin = "1.0.1"
    const val benManes = "0.28.0"
    const val lifecycle = "2.3.1"
    const val lifecycleExtensions = "2.2.0"
    const val room = "2.3.0"
    const val materialComponents = "1.4.0"
    const val constraintLayout = "2.0.4"
    const val swipeRefreshLayout = "1.1.0"
    const val appcompat = "1.3.0"
    const val junit = "4.13.2"
    const val junit5 = "5.7.2"
    const val coroutines = "1.5.0"
    const val navigation = "2.3.5"
    const val fragment = "1.3.4"
    const val activity = "1.2.2"
    const val detekt = "1.16.0"
    const val coreKtx = "1.5.0"
    const val logger = "2.2.0"
    const val glide = "4.12.0"
    const val robolectric = "4.5.1"
    const val androidXTestCore = "1.3.0"
    const val mockitoCore = "3.8.0"
    const val koin = "3.1.2"
    const val retrofit = "2.9.0"
    const val okHttp = "4.9.1"
    const val gridLayout = "1.0.0"
    const val moshi = "1.12.0"
    const val gson = "2.8.7"
    const val paging = "3.0.0"
    const val espresso = "3.3.0"
    const val workManager = "2.5.0"
    const val viewPager2 = "1.0.0"
    const val viewPagerDotsIndicator = "4.1.2"
    const val scrollingPagerIndicator = "1.2.1"
    const val jodaTime = "2.10.10"
    const val sqlCipher = "4.4.0"
    const val timber = "4.7.1"
    const val archCoreTesting = "2.1.0"
    const val liveDataTesting = "1.1.2"
    const val mockitoKotlin = "3.2.0"
    const val runner = "1.3.0"
    const val rules = "1.3.0"
    const val androidJunit = "1.1.2"
    const val jdkDesugar = "1.1.1"
    const val analytics = "17.4.4"
    const val shimmer = "0.5.0"
    const val leakCanary = "2.7"
}

object Classpaths {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val googlePlayServices = "com.google.gms:google-services:${Versions.googlePlayServices}"
    const val junit = "de.mannodermaus.gradle.plugins:android-junit5:${Versions.junitPlugin}"
}

object Libraries {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val activity = "androidx.activity:activity:${Versions.activity}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val gridLayout = "androidx.gridlayout:gridlayout:${Versions.gridLayout}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleViewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveDataExtensions = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleScope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val pagingKtx = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val logger = "com.orhanobut:logger:${Versions.logger}"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val scalarConverter = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val workManagerKotlin = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val workManagerKoin = "io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val viewPagerDotsIndicator = "com.tbuonomo.andrui:viewpagerdotsindicator:${Versions.viewPagerDotsIndicator}"
    const val scrollingPagerIndicator = "ru.tinkoff.scrollingpagerindicator:scrollingpagerindicator:${Versions.scrollingPagerIndicator}"
    const val jodaTime = "joda-time:joda-time:${Versions.jodaTime}"
    const val sqlCipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlCipher}"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:${Versions.jdkDesugar}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val androidXTestCore = "androidx.test:core:${Versions.androidXTestCore}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val workManagerTest = "androidx.work:work-testing:${Versions.workManager}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
    const val liveDataTesting = "com.jraska.livedata:testing-ktx:${Versions.liveDataTesting}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val rules = "androidx.test:rules:${Versions.rules}"
    const val orchestrator = "androidx.test:orchestrator:${Versions.runner}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val espressoWeb = "androidx.test.espresso:espresso-web:${Versions.espresso}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junit5}"
    const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit5}"
    const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junit5}"
}

object KaptLibraries {
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}
