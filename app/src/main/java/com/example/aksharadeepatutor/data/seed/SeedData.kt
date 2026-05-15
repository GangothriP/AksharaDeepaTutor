package com.example.aksharadeepatutor.data.seed

import com.example.aksharadeepatutor.data.entity.ChapterProgressEntity
import com.example.aksharadeepatutor.data.entity.QuestionEntity

object SeedData {

    val subjects = listOf("Science", "Math", "Social")

    val chapters = mapOf(
        "Science" to listOf(
            "Life Processes",
            "Light",
            "Electricity",
            "Acids, Bases and Salts",
            "Our Environment"
        ),
        "Math" to listOf(
            "Polynomials",
            "Pair of Linear Equations",
            "Triangles",
            "Statistics",
            "Probability"
        ),
        "Social" to listOf(
            "Nationalism in India",
            "Resources and Development",
            "Democracy",
            "Globalisation",
            "Money and Credit"
        )
    )

    val chapterProgress = chapters.flatMap { entry ->
        entry.value.map { chapter ->
            ChapterProgressEntity(
                id = "${entry.key}-$chapter",
                subject = entry.key,
                chapter = chapter,
                completed = false
            )
        }
    }

    val questions = listOf(
        QuestionEntity(1, "Which pigment helps plants absorb sunlight for photosynthesis?", listOf("Chlorophyll", "Haemoglobin", "Iodine", "Melanin"), "Chlorophyll", "Science", "Life Processes"),
        QuestionEntity(2, "Which gas is taken in by plants during photosynthesis?", listOf("Carbon dioxide", "Oxygen", "Nitrogen", "Hydrogen"), "Carbon dioxide", "Science", "Life Processes"),
        QuestionEntity(3, "The process of breaking complex food into simpler food is called:", listOf("Digestion", "Respiration", "Excretion", "Circulation"), "Digestion", "Science", "Life Processes"),
        QuestionEntity(4, "Kidneys are mainly responsible for:", listOf("Excretion", "Respiration", "Photosynthesis", "Reproduction"), "Excretion", "Science", "Life Processes"),
        QuestionEntity(5, "The functional unit of kidney is:", listOf("Nephron", "Neuron", "Alveoli", "Villi"), "Nephron", "Science", "Life Processes"),
        QuestionEntity(6, "Which blood vessels carry blood away from the heart?", listOf("Arteries", "Veins", "Capillaries", "Nerves"), "Arteries", "Science", "Life Processes"),
        QuestionEntity(7, "Respiration mainly releases:", listOf("Energy", "Salt", "Sand", "Light"), "Energy", "Science", "Life Processes"),
        QuestionEntity(8, "The food prepared by plants is stored mainly as:", listOf("Starch", "Protein", "Fat", "Vitamin"), "Starch", "Science", "Life Processes"),

        QuestionEntity(9, "Light generally travels in:", listOf("Straight lines", "Circles", "Zigzag only", "Random paths"), "Straight lines", "Science", "Light"),
        QuestionEntity(10, "Bouncing back of light from a surface is called:", listOf("Reflection", "Refraction", "Dispersion", "Evaporation"), "Reflection", "Science", "Light"),
        QuestionEntity(11, "Bending of light when it changes medium is called:", listOf("Refraction", "Reflection", "Condensation", "Neutralisation"), "Refraction", "Science", "Light"),
        QuestionEntity(12, "A convex lens is also called:", listOf("Converging lens", "Diverging lens", "Plane lens", "Opaque lens"), "Converging lens", "Science", "Light"),
        QuestionEntity(13, "A plane mirror forms an image that is:", listOf("Virtual and erect", "Real and inverted", "Always enlarged", "Always diminished"), "Virtual and erect", "Science", "Light"),
        QuestionEntity(14, "Power of a lens is measured in:", listOf("Dioptre", "Volt", "Newton", "Ampere"), "Dioptre", "Science", "Light"),

        QuestionEntity(15, "SI unit of electric current is:", listOf("Ampere", "Volt", "Ohm", "Watt"), "Ampere", "Science", "Electricity"),
        QuestionEntity(16, "Ohm's law is represented by:", listOf("V = IR", "P = VI", "F = ma", "E = mc2"), "V = IR", "Science", "Electricity"),
        QuestionEntity(17, "Resistance is measured in:", listOf("Ohm", "Volt", "Watt", "Joule"), "Ohm", "Science", "Electricity"),
        QuestionEntity(18, "In a series circuit, current is:", listOf("Same everywhere", "Zero everywhere", "Different in each component", "Only in one branch"), "Same everywhere", "Science", "Electricity"),
        QuestionEntity(19, "Electrical power is commonly calculated as:", listOf("P = VI", "V = IR", "A = l x b", "m = d/v"), "P = VI", "Science", "Electricity"),
        QuestionEntity(20, "A fuse protects a circuit from:", listOf("Overloading", "Photosynthesis", "Refraction", "Digestion"), "Overloading", "Science", "Electricity"),

        QuestionEntity(21, "Acids turn blue litmus:", listOf("Red", "Green", "Yellow", "White"), "Red", "Science", "Acids, Bases and Salts"),
        QuestionEntity(22, "Bases turn red litmus:", listOf("Blue", "Red", "Black", "Orange"), "Blue", "Science", "Acids, Bases and Salts"),
        QuestionEntity(23, "A pH value less than 7 indicates:", listOf("Acidic solution", "Basic solution", "Neutral solution", "Pure salt only"), "Acidic solution", "Science", "Acids, Bases and Salts"),
        QuestionEntity(24, "Neutralisation produces:", listOf("Salt and water", "Only oxygen", "Only hydrogen", "Sugar and oil"), "Salt and water", "Science", "Acids, Bases and Salts"),
        QuestionEntity(25, "Baking soda is commonly known as:", listOf("Sodium hydrogen carbonate", "Sodium chloride", "Calcium carbonate", "Hydrochloric acid"), "Sodium hydrogen carbonate", "Science", "Acids, Bases and Salts"),

        QuestionEntity(26, "The flow of energy in an ecosystem is generally:", listOf("Unidirectional", "Circular", "Random only", "Absent"), "Unidirectional", "Science", "Our Environment"),
        QuestionEntity(27, "Organisms that prepare their own food are called:", listOf("Producers", "Consumers", "Decomposers", "Parasites"), "Producers", "Science", "Our Environment"),
        QuestionEntity(28, "Decomposers help in:", listOf("Recycling nutrients", "Making plastic", "Producing current", "Reflecting light"), "Recycling nutrients", "Science", "Our Environment"),

        QuestionEntity(29, "The degree of a quadratic polynomial is:", listOf("2", "1", "3", "0"), "2", "Math", "Polynomials"),
        QuestionEntity(30, "For ax² + bx + c, sum of zeroes is:", listOf("-b/a", "c/a", "a/b", "b/c"), "-b/a", "Math", "Polynomials"),
        QuestionEntity(31, "A linear polynomial has degree:", listOf("1", "2", "3", "4"), "1", "Math", "Polynomials"),
        QuestionEntity(32, "The zero of p(x) is a value where p(x) becomes:", listOf("0", "1", "-1", "10"), "0", "Math", "Polynomials"),
        QuestionEntity(33, "A cubic polynomial has degree:", listOf("3", "2", "1", "0"), "3", "Math", "Polynomials"),

        QuestionEntity(34, "Two intersecting lines have:", listOf("One solution", "No solution", "Infinite solutions", "No equations"), "One solution", "Math", "Pair of Linear Equations"),
        QuestionEntity(35, "Parallel lines represent equations with:", listOf("No solution", "One solution", "Infinite solutions", "Only zero solution"), "No solution", "Math", "Pair of Linear Equations"),
        QuestionEntity(36, "Coincident lines have:", listOf("Infinite solutions", "No solution", "Only one solution", "Only negative solutions"), "Infinite solutions", "Math", "Pair of Linear Equations"),
        QuestionEntity(37, "A pair of linear equations has variables usually named:", listOf("x and y", "pH and V", "m and kg only", "north and south"), "x and y", "Math", "Pair of Linear Equations"),
        QuestionEntity(38, "Elimination method removes:", listOf("One variable", "All numbers", "The graph", "The chapter"), "One variable", "Math", "Pair of Linear Equations"),

        QuestionEntity(39, "Similar triangles have corresponding angles:", listOf("Equal", "Always 90", "Always 60", "Unequal only"), "Equal", "Math", "Triangles"),
        QuestionEntity(40, "Pythagoras theorem applies to:", listOf("Right triangles", "All quadrilaterals", "Circles", "Straight lines only"), "Right triangles", "Math", "Triangles"),
        QuestionEntity(41, "A triangle has:", listOf("Three sides", "Two sides", "Four sides", "Five sides"), "Three sides", "Math", "Triangles"),
        QuestionEntity(42, "The longest side of a right triangle is:", listOf("Hypotenuse", "Median", "Altitude", "Radius"), "Hypotenuse", "Math", "Triangles"),
        QuestionEntity(43, "Basic Proportionality Theorem is linked with:", listOf("Similar triangles", "Probability", "Statistics", "Trigonometric tables"), "Similar triangles", "Math", "Triangles"),

        QuestionEntity(44, "Mean means:", listOf("Average", "Middle value", "Most repeated value", "Smallest value"), "Average", "Math", "Statistics"),
        QuestionEntity(45, "Median is the:", listOf("Middle value", "Average", "Most repeated value", "Highest frequency only"), "Middle value", "Math", "Statistics"),
        QuestionEntity(46, "Mode is the:", listOf("Most repeated value", "Average", "Middle value", "Largest number"), "Most repeated value", "Math", "Statistics"),
        QuestionEntity(47, "Mean of 2, 4, 6 is:", listOf("4", "2", "6", "12"), "4", "Math", "Statistics"),

        QuestionEntity(48, "Probability value always lies between:", listOf("0 and 1", "1 and 10", "-5 and 5", "10 and 100"), "0 and 1", "Math", "Probability"),
        QuestionEntity(49, "Probability of a sure event is:", listOf("1", "0", "2", "-1"), "1", "Math", "Probability"),
        QuestionEntity(50, "Probability of an impossible event is:", listOf("0", "1", "2", "10"), "0", "Math", "Probability"),

        QuestionEntity(51, "Indian nationalism grew mainly against:", listOf("British rule", "French Revolution", "World Bank", "Local markets"), "British rule", "Social", "Nationalism in India"),
        QuestionEntity(52, "The Salt March was led by:", listOf("Mahatma Gandhi", "Subhas Chandra Bose", "Jawaharlal Nehru", "Bal Gangadhar Tilak"), "Mahatma Gandhi", "Social", "Nationalism in India"),
        QuestionEntity(53, "Quit India Movement demanded:", listOf("End of British rule", "More salt tax", "More foreign goods", "End of voting"), "End of British rule", "Social", "Nationalism in India"),
        QuestionEntity(54, "Non-Cooperation encouraged boycott of:", listOf("Foreign goods", "Clean water", "Local education", "Indian leaders"), "Foreign goods", "Social", "Nationalism in India"),
        QuestionEntity(55, "Satyagraha means insistence on:", listOf("Truth", "Violence", "Luxury", "Trade"), "Truth", "Social", "Nationalism in India"),

        QuestionEntity(56, "Resources should be used:", listOf("Sustainably", "Carelessly", "Only by one person", "Without planning"), "Sustainably", "Social", "Resources and Development"),
        QuestionEntity(57, "Natural resources include:", listOf("Soil and water", "Only machines", "Only currency", "Only buildings"), "Soil and water", "Social", "Resources and Development"),
        QuestionEntity(58, "Sustainable development protects needs of:", listOf("Future generations", "Only factories", "Only cities", "Only one family"), "Future generations", "Social", "Resources and Development"),

        QuestionEntity(59, "Democracy means rule by:", listOf("People", "King", "Army", "One company"), "People", "Social", "Democracy"),
        QuestionEntity(60, "Citizens choose representatives through:", listOf("Elections", "War", "Lottery only", "Police orders"), "Elections", "Social", "Democracy"),
        QuestionEntity(61, "A healthy democracy requires:", listOf("Free and fair elections", "No voting", "No criticism", "One-person rule"), "Free and fair elections", "Social", "Democracy"),

        QuestionEntity(62, "Globalisation connects countries through:", listOf("Trade and technology", "Isolation", "No transport", "No communication"), "Trade and technology", "Social", "Globalisation"),
        QuestionEntity(63, "Multinational companies operate in:", listOf("More than one country", "Only one village", "Only one classroom", "No market"), "More than one country", "Social", "Globalisation"),
        QuestionEntity(64, "Globalisation can increase:", listOf("Consumer choice", "Total isolation", "No transport", "No competition"), "Consumer choice", "Social", "Globalisation"),

        QuestionEntity(65, "Money acts as a:", listOf("Medium of exchange", "Source of light", "Plant pigment", "Unit of current"), "Medium of exchange", "Social", "Money and Credit"),
        QuestionEntity(66, "Credit means:", listOf("Borrowed money to be repaid", "Free gift always", "Only coins", "No repayment"), "Borrowed money to be repaid", "Social", "Money and Credit"),
        QuestionEntity(67, "Formal sources of credit include:", listOf("Banks", "Moneylenders only", "Friends only", "Shopkeepers only"), "Banks", "Social", "Money and Credit")
    )
    val notes = listOf(
        com.example.aksharadeepatutor.model.TopicNote(
            "Science",
            "Life Processes",
            "Life processes are activities needed for survival. Nutrition gives energy, respiration releases energy from food, transportation moves materials inside the body, and excretion removes waste. Plants prepare food by photosynthesis using sunlight, carbon dioxide, water and chlorophyll."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Science",
            "Light",
            "Light travels in straight lines. Reflection means bouncing back of light. Refraction means bending of light when it passes from one medium to another. Mirrors and lenses are used in spectacles, cameras, microscopes and telescopes."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Science",
            "Electricity",
            "Electric current is flow of charge. Its SI unit is ampere. Potential difference is measured in volts. Resistance opposes current. Ohm's law is V = IR. Series circuits have same current and parallel circuits have same voltage."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Math",
            "Polynomials",
            "A polynomial contains variables and constants. Degree means highest power of the variable. A quadratic polynomial has degree 2. Zeroes of a polynomial are values that make the polynomial equal to zero."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Math",
            "Triangles",
            "Two triangles are similar when their corresponding angles are equal and sides are proportional. Pythagoras theorem says that in a right triangle, hypotenuse square equals the sum of squares of the other two sides."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Math",
            "Statistics",
            "Statistics is the study of data. Mean is average, median is middle value, and mode is the most repeated value. These help understand large data in a simple way."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Social",
            "Nationalism in India",
            "Indian nationalism grew during British rule. Gandhiji used non-violence and satyagraha. Important movements include Non-Cooperation, Civil Disobedience, Salt March and Quit India Movement."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Social",
            "Democracy",
            "Democracy means rule by the people. Citizens elect representatives through elections. A good democracy needs free elections, equal rights, active citizens and respect for law."
        ),
        com.example.aksharadeepatutor.model.TopicNote(
            "Social",
            "Globalisation",
            "Globalisation connects countries through trade, transport, technology and communication. It gives more choices to consumers but also increases competition for small producers."
        )
    )

}
