package net.fredericosilva.sectionlistadapter

interface Section<I> {
    var id: Int
    var header: SectionHeader
    var position: Int

    fun numOfItems(): Int
    fun getItem(i: Int): I?

    interface SectionHeader {
        val headerType: Int
        val headerTitle: String?
    }

    companion object {
        const val SECTION_ITEM = 0
        const val SECTION_HEADER = 1
    }
}