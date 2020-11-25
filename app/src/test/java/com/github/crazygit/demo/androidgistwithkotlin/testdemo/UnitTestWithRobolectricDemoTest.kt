package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * 修改`build.gradle`文件，添加如下依赖

```groovy
// Required -- JUnit 4 framework
testImplementation 'junit:junit:4.13.1'

// java使用没有ktx扩展的包
// testImplementation 'androidx.test:core:1.3.0'
// testImplementation 'androidx.test.ext:junit:1.1.2'
testImplementation 'androidx.test:core-ktx:1.3.0'
testImplementation 'androidx.test.ext:junit-ktx:1.1.2'

// Robolectric environment
testImplementation 'org.robolectric:robolectric:4.4'

// 使用truth断言库
testImplementation 'androidx.test.ext:truth:1.3.0'
testImplementation 'com.google.truth:truth:1.0'

// Optional -- Mockito framework
testImplementation 'org.mockito:mockito-core:3.3.3'
```

并添加

```groovy
android {
// ...
testOptions {
unitTests.includeAndroidResources = true
}
}
```

并且由于目前`robolectric`不支持`targetSdkVersion`大于`29`，因此需要设置`targetSdkVersion`为小于或等于29

```
targetSdkVersion 29
```

或者再创建测试用例的时候类的时候通过`@Config`注解指定SDK的版本信息。

 */

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Q])
class UnitTestWithRobolectricDemoTest {


    private val context: Context = ApplicationProvider.getApplicationContext()
    fun test_getPackageName() {
        assertThat(context.packageName).contains("com.github.crazygit.demo.androidgistwithkotlin")
    }

    @Test
    fun test_getStringWithContext() {
        val expectedString = "This string is for unit test purpose"
        val result = UnitTestWithRobolectricDemo.getStringWithContext(context)
        assertThat(result).isEqualTo(expectedString)
    }
}