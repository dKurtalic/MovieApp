package com.example.myapplication


import com.example.myapplication.data.Movie
import com.example.myapplication.data.MovieRepository


import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.hamcrest.beans.HasPropertyWithValue.hasProperty

import org.hamcrest.CoreMatchers.`is` as Is
import org.junit.Assert.assertEquals


import org.junit.Test

class RepositoryUnitTest {
    @Test
    fun testGetFavoriteMovies(){
        val movies = MovieRepository.getFavoriteMovies()
        assertEquals(movies.size,10)
        assertThat(movies, hasItem<Movie>(hasProperty("title", Is("Pride and prejudice"))))
        assertThat(movies, not(hasItem<Movie>(hasProperty("title", Is("Black Widow")))))
    }
    @Test
    fun testGetRecentMovies(){
        val movies = MovieRepository.getRecentMovies()
        assertEquals(movies.size,3)
        assertThat(movies, hasItem<Movie>(hasProperty("title", Is("Bridget Jones's Diary"))))
        assertThat(movies, not(hasItem<Movie>(hasProperty("title", Is("Titanic")))))
    }

}

