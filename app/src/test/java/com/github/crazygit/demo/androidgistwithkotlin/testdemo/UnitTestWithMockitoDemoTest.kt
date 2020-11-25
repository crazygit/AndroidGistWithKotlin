package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import android.content.Context
import com.github.crazygit.demo.androidgistwithkotlin.R
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UnitTestWithMockitoDemoTest {


    // 创建模拟对象
    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        val fake_package_name = "fake package name"
        // 设置当获取mockContext的packageName时，返回fake_package_name
        `when`(mockContext.packageName)
            .thenReturn(fake_package_name)

        // 获取mockContext的packageName
        val result = mockContext.packageName

        // 检查结果
        assertThat(result).isEqualTo(fake_package_name)
    }

    @Test
    fun test_getStringWithContext() {
        val expectedString = "This string is for unit test purpose"
        `when`(mockContext.getString(R.string.unit_test_get_string))
            .thenReturn(expectedString)
        val result =
            UnitTestWithMockitoDemo.getStringWithContext(mockContext, R.string.unit_test_get_string)
        assertThat(result).isEqualTo(expectedString)
    }
}