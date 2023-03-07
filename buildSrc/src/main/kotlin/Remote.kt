object Remote {

    private const val retrofitVersion = "2.9.0"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"

    private const val moshiKotlinVersion = "1.14.0"
    val moshi = "com.squareup.moshi:moshi:$moshiKotlinVersion"
    val moshi_kotlin_code_generation = "com.squareup.moshi:moshi-kotlin-codegen:$moshiKotlinVersion"
    private const val moshiConverterVersion = "2.3.0"
    val moshi_converter = "com.squareup.retrofit2:converter-moshi:$moshiConverterVersion"

    private const val retrofit_interceptor_version = "4.10.0"
    const val retrofit_interceptor = "com.squareup.okhttp3:logging-interceptor:$retrofit_interceptor_version"
}