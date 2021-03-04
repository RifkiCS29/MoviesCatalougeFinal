package com.rifki.jetpackpro.mymoviesfinal.utils

import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailMovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.DetailTvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.MovieEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.local.entity.TvShowEntity
import com.rifki.jetpackpro.mymoviesfinal.data.source.remote.response.*

object DataDummy {

//    fun generateDummyMovies(): List<MovieEntity> {
//        return listOf(
//                MovieEntity(
//                        "581389",
//                        "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg4",
//                        "Space Sweepers",
//                        7.3
//                ),
//                MovieEntity(
//                        "464052",
//                        "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
//                        "Wonder Woman 1984",
//                        6.9
//                ),
//                MovieEntity(
//                        "587996",
//                        "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
//                        "Below Zero",
//                        6.4
//                ),
//                MovieEntity(
//                        "602269",
//                        "/c7VlGCCgM9GZivKSzBgzuOVxQn7.jpg",
//                        "The Little Things",
//                        6.4
//                )
//
//        )
//    }
//
//    fun generateDummyTvShows(): List<TvShowEntity> {
//        return listOf(
//                TvShowEntity(
//                        "85271",
//                        "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
//                        "WandaVision",
//                        8.4
//                ),
//                TvShowEntity(
//                        "69050",
//                        "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
//                        "Riverdale",
//                        8.6
//                ),
//                TvShowEntity(
//                        "71712",
//                        "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
//                        "The Good Doctor",
//                        8.6
//                ),
//                TvShowEntity(
//                        "1416",
//                        "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
//                        "Grey's Anatomy",
//                        8.2
//                )
//        )
//    }
//
//    fun generateDetailMovie(): DetailMovieEntity {
//        return DetailMovieEntity(
//                "Space Sweepers",
//                "/drulhSX7P5TQlEMQZ3JoXKSDEfz.jpg",
//                listOf("Drama", "Fantasy", "Science Fiction"),
//                581389,
//                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
//                136,
//                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
//                "2021-02-05",
//                7.3,
//                "2092, the space sweep begins!"
//        )
//    }
//
//    fun generateDetailTvShow(): DetailTvShowEntity {
//        return DetailTvShowEntity(
//                "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
//                listOf("Sci-Fi & Fantasy", "Mystery", "Drama"),
//                85271,
//                "2021-01-15",
//                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
//                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
//                8.5,
//                "WandaVision",
//                "Experience a new vision of reality."
//        )
//    }
//
//    fun generateRemoteDummyMovies(): List<ResultsMovieItem> {
//        return listOf(
//                ResultsMovieItem(
//                        "Space Sweepers",
//                        "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg4",
//                        8.4,
//                        "581389"
//                ),
//                ResultsMovieItem(
//                        "Wonder Woman 1984",
//                        "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
//                        6.9,
//                        "464052"
//                ),
//                ResultsMovieItem(
//                        "Below Zero",
//                        "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
//                        6.4,
//                        "587996"
//                ),
//                ResultsMovieItem(
//                        "The Little Things",
//                        "/c7VlGCCgM9GZivKSzBgzuOVxQn7.jpg",
//                        6.4,
//                        "602269"
//                )
//
//        )
//    }
//
//    fun generateRemoteDummyTvShows(): List<ResultsTvShowItem> {
//        return listOf(
//                ResultsTvShowItem(
//                        "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
//                        8.4,
//                        "WandaVision",
//                        "85271"
//                ),
//                ResultsTvShowItem(
//                        "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
//                        8.6,
//                        "Riverdale",
//                        "69050"
//                ),
//                ResultsTvShowItem(
//                        "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
//                        8.6,
//                        "The Good Doctor",
//                        "71712"
//                ),
//                ResultsTvShowItem(
//                        "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
//                        8.2,
//                        "Grey's Anatomy",
//                        "1416"
//                )
//        )
//    }
//
//    fun generateRemoteDetailMovie(): DetailMovieResponse {
//        return DetailMovieResponse(
//                "Space Sweepers",
//                "/drulhSX7P5TQlEMQZ3JoXKSDEfz.jpg",
//                listOf(
//                        GenresMovieItem(
//                                "Drama", 18
//                        ),
//                        GenresMovieItem(
//                                "Fantasy", 14
//                        ),
//                        GenresMovieItem(
//                                "Science Fiction", 878
//                        )
//                ),
//                581389,
//                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
//                136,
//                "/y2Yp7KC2FJSsdlRM5qkkIwQGCqU.jpg",
//                "2021-02-05",
//                7.3,
//                "2092, the space sweep begins!"
//        )
//    }
//
//    fun generateRemoteDetailTvShow(): DetailTvShowResponse {
//        return DetailTvShowResponse(
//                "/lOr9NKxh4vMweufMOUDJjJhCRHW.jpg",
//                listOf(
//                        GenresItem(
//                                "Sci-Fi & Fantasy", 10765
//                        ),
//                        GenresItem(
//                                "Mystery", 9648
//                        ),
//                        GenresItem(
//                                "Drama",18
//                        )
//                ),
//                85271,
//                "2021-01-15",
//                "Disturbances on Halloween separate Wanda from Vision, who looks into anomalous activity in Westview.",
//                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
//                8.4,
//                "WandaVision",
//                "Experience a new vision of reality."
//        )
//    }
}