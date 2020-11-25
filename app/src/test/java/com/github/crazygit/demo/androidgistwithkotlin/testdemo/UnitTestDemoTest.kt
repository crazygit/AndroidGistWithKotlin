package com.github.crazygit.demo.androidgistwithkotlin.testdemo

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTestDemoTest {

    @Test
    fun `test assert usage`() {
        // 使用Junit库的断言方式
        assertEquals(
            3, 1 + 2
        )
        // 使用truth库的断言方式
        assertThat({ 1 + 2 }()).isEqualTo(3)
    }

    @Test
    fun test_add() {
        assertThat(UnitTestDemo.add(1, 3)).isEqualTo(4)
    }
}