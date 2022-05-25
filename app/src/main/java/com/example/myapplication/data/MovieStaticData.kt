package com.example.myapplication.data

fun favoriteMovies():List<Movie>{
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            null,null),
        Movie(2, "Harry Potter and the Philosopher's Stone", "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
        "14.11.2001","https://www.imdb.com/title/tt0241527/",null,null),
        Movie(3,"House of Gucci", "When Patrizia Reggiani, an outsider from humble beginnings, marries into the Gucci family, her unbridled ambition begins to unravel their legacy and triggers a reckless spiral of betrayal, decadence, revenge, and ultimately...murder.",
        "24.11.2021","https://www.imdb.com/title/tt11214590/",null,null),
        Movie(4, "Home alone","An eight-year-old troublemaker must protect his house from a pair of burglars when he is accidentally left home alone by his family during Christmas vacation.",
        "16.11.1990","https://www.imdb.com/title/tt0099785/", null,null),
        Movie(5,"The Intouchables","After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.",
        "2.11.2011","https://www.imdb.com/title/tt1675434/",null,null),
        Movie(6,"The pianist", "A Polish Jewish musician struggles to survive the destruction of the Warsaw ghetto of World War II.",
        "6.9.2002","https://www.imdb.com/title/tt0253474/",null,null),
        Movie(7,"Dune", "A noble family becomes embroiled in a war for control over the galaxy's most valuable asset while its scion becomes troubled by visions of a dark future.",
        "3.9.2021","https://www.imdb.com/title/tt1160419/",null,null),
        Movie(8, "My girl", "A young girl, on the threshold of her teen years, finds her life turning upside down, when she is accompanied by an unlikely friend.","" +
                "27.11.1991", "https://www.imdb.com/title/tt0102492/",null,null),
        Movie(9,"Twilight","When Bella Swan moves to a small town in the Pacific Northwest, she falls in love with Edward Cullen, a mysterious classmate who reveals himself to be a 108-year-old vampire.",
        "21.11.2008", "https://www.imdb.com/title/tt1099212/",null,null),
        Movie(10,"Not So Friendly Neighborhood Affair","Deset u pola is a comedy set in Sarajevo in May 2021, as the city's famous Old Town tries to recover after a difficult pandemic year. When a visitor from Zagreb comes looking for the best kebabs in town, a harmless gesture causes the disintegration of the business and private lives of several people.",
        "1.12.2021", "https://www.imdb.com/title/tt14577742/",null,null)
    )
         }


fun recentMovies():List<Movie>{
    return listOf(
        Movie(11,"Bridget Jones's Diary","Bridget Jones is determined to improve herself while she looks for love in a year in which she keeps a personal diary.","13.4.2001","https://www.imdb.com/title/tt0243155/",null,null),
        Movie(12,"The Devil Wears Prada","A smart but sensible new graduate lands a job as an assistant to Miranda Priestly, the demanding editor-in-chief of a high fashion magazine.","30.6.2006","https://www.imdb.com/title/tt0458352/",null,null),
        Movie(13,"The fast and furious", "Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.","https://www.imdb.com/title/tt0232500/","22.6.2001",null,null)
    )
}

fun movieActors():Map<String,List<String>>{
    return mapOf<String,List<String>>(
        "Pride and prejudice" to listOf("Keira Knightley","Matthew Macfadyen","Brenda Blethyn"),
        "Harry Potter and the Philosopher's Stone" to listOf("Daniel Radcliffe","Emma Watson", "Rupert Grint"),
        "House of Gucci" to listOf("Lady Gaga","Adam Driver","Al Pacino"),
        "Home alone" to listOf("Macaulay Culkin","Joe Pesci","Daniel Stern"),
        "The Intouchables" to listOf("François Cluzet","Omar Sy","Anne Le Ny"),
        "The pianist" to listOf("Adrien Brody","Thomas Kretschmann","Frank Finlay"),
        "Dune" to listOf("Timothée Chalamet","Rebecca Ferguson","Zendaya"),
        "My girl" to listOf("Anna Chlumsky","Macaulay Culkin","Dan Aykroyd"),
        "Twilight" to listOf("Kristen Stewart","Robert Pattinson","Billy Burke"),
        "Not So Friendly Neighborhood Affair" to listOf("Branko Đurić", "Izudin Bajrović","Helena Vuković")
        )

}
fun similarMovies():Map<String,List<String>>{
    return mapOf<String,List<String>>(
        "Pride and prejudice" to listOf("Little woman", "Sense and sensibility", "Atonement"),
        "Harry Potter and the Philosopher's Stone" to listOf("Harry Potter and the Goblet of Fire", "Harry Potter and the Order of Phoenix", "Harry Potter and the Chamber of Secrets"),
        "House of Gucci" to listOf("Don't look up", "King Richard", "Spencer"),
        "Home alone" to listOf("Home alone 2", "Home alone 3","Charlie and the Chocolate Factory"),
        "The Intouchables" to listOf("The pianist", "Leon: The professional","Life is beautiful"),
        "The pianist" to listOf("Schindler's List", "Leon: The professional", "Life is beautiful"),
        "Dune" to listOf("Don't look up", "Spider-Man: No Way Home", "Joker"),
        "My girl" to listOf("My girl 2", "Sister act", "Father of the Bride"),
        "Twilight" to listOf("The Twilight Saga: New Moon", "The Twilight Saga: Eclipse", "The Twilight Saga: Breaking Dawn"),
        "Not So Friendly Neighborhood Affair" to listOf("Toma","Kod amidže Idriza", "It's hard to be nice")
        )
}

