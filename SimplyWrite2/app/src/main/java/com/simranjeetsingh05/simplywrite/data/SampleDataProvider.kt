package com.simranjeetsingh05.simplywrite.data

import java.util.*

class SampleDataProvider {

    companion object {
        private val sampleText1 = "A sample note"
        private val sampleText2 = "A note a\n line feed"
        private val sampleText3 = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam interdum mi non nisi convallis euismod. In aliquam luctus arcu. In leo urna, cursus at neque quis, semper suscipit leo. Phasellus cursus lobortis mattis. Ut condimentum ut arcu ac ornare. Curabitur efficitur magna molestie, mattis arcu et, volutpat lacus. Fusce et justo magna. Nullam at pretium leo. Morbi sit amet ultricies erat, eu semper sapien. Donec sit amet felis tortor. Aliquam erat volutpat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris volutpat tortor id malesuada pulvinar.

            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla fringilla arcu sit amet suscipit varius. Praesent et laoreet urna. Sed aliquam est mauris. Etiam porta, quam vel cursus ornare, sapien odio porta nunc, ut aliquam mauris nisl id augue. Integer vestibulum bibendum accumsan. Aenean id sapien vel enim venenatis placerat et vel velit. Curabitur enim ante, suscipit in consectetur sed, commodo eget libero. Sed vulputate congue eros gravida imperdiet. Aenean mollis quis eros ac vehicula. Integer congue, quam et dictum consequat, ipsum turpis auctor eros, pellentesque vehicula nunc lectus in leo. Mauris dapibus quam at tempor consequat.
            
        """.trimIndent()

        private fun getDate(diff:Long): Date {
            return Date(Date().time + diff)
        }
        fun getNotes() = arrayListOf(
            NoteEntity(getDate(0), sampleText1),
            NoteEntity(getDate(1), sampleText2),
            NoteEntity(getDate(2), sampleText3)
        )
    }
}