# http

封装了Message+Handler+线程池方式异步请求网络的http库（在1.0.0）。

注意：
在1.1.0版本之后，删除了HttpClient，使用OkHttp。
（1.1.0版本暂时使用zhy的OkHttpUtils）


Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			jcenter()
		}
	}
  

Step 2. Add the dependency

	dependencies {
	        compile 'com.wuguangxin:http:1.0.0'
	}
