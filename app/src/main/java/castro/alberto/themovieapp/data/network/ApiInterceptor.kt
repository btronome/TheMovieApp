package castro.alberto.themovieapp.data.network

import castro.alberto.themovieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
