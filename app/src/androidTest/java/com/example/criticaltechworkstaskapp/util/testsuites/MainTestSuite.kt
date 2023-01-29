package com.example.criticaltechworkstaskapp.util.testsuites


import com.example.criticaltechworkstaskapp.api.ApiIsolationTests
import org.junit.runner.RunWith
import org.junit.runners.Suite
// test suite to test Room database and API functionality

@RunWith(Suite::class)
@Suite.SuiteClasses(
    //UnitTest
    ApiIsolationTests::class,
)
class MainTestSuite