cmake_minimum_required(VERSION 3.17)

find_package(Java REQUIRED)
include(UseJava)

project(balls-playground NONE)

set(CMAKE_JAVA_COMPILER_FLAGS -source 14 -target 14)

add_jar(balls-playground
    SOURCES
    ApplicationWindow.java
    Ball.java
    BallRefresher.java
    BallsFactory.java
    BallsPlayground.java
    RandomGenerator.java
    ShapeDrawer.java
    Vector2.java
    Vector3.java
    VectorN.java
    VectorNIterator.java
    ENTRY_POINT ApplicationWindow)