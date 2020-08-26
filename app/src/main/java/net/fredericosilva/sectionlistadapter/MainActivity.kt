package net.fredericosilva.sectionlistadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionListAdapter = AnimalsListAdapter()

        val dogList = listOf(
            Animal("Dalmata"),
            Animal("Pitbull"),
            Animal("Poodle"),
            Animal("Chow Chow"),
            Animal("Labrador Retriever"),
            Animal("Husky"),
            Animal("German Shepard")
        )
        val catList = listOf(
            Animal("Persian"),
            Animal("Turkish Angora"),
            Animal("Siamese"),
            Animal("Bengal"),
            Animal("British Shorthair")
        )

        val birdList = listOf(
            Animal("Parrot"),
            Animal("Finch"),
            Animal("Lovebird"),
            Animal("Auk"),
            Animal("Cocktatiel"),
            Animal("Rock dove")
        )

        val monkeyList = listOf(
            Animal("Gibbon"),
            Animal("Mandrill"),
            Animal("Baboon"),
            Animal("Auk"),
            Animal("Blue Monkey"),
            Animal("Japanese Macaque")
        )

        sectionListAdapter.addSection(1, "Dogs", dogList)
        sectionListAdapter.addSection(2, "Cats", catList)
        sectionListAdapter.addSection(3, "Birds", birdList)
        sectionListAdapter.addSection(4, "Monkeys", monkeyList)

        recyclerView.adapter = sectionListAdapter
    }
}