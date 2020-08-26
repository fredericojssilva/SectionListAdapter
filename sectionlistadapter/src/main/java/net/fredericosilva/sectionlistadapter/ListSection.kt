package net.fredericosilva.sectionlistadapter

internal class ListSection<T>(
    override var id: Int,
    sectionName: String,
    headerType: Int,
    private val items: List<T>
) :
    Section<T> {
    override var header = SimpleHeader(sectionName, headerType) as Section.SectionHeader
    override var position = 0

    constructor(
        id: Int,
        sectionName: String,
        items: List<T>
    ) : this(id, sectionName, 1, items)


    override fun numOfItems(): Int {
        return items.size
    }

    override fun getItem(i: Int): T {
        return items[i]
    }
}