package net.fredericosilva.sectionlistadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.util.*

abstract class SectionListAdapter<T, VHI : ViewHolder, VHS : ViewHolder> :
    RecyclerView.Adapter<ViewHolder>() {

    private var mSections: HashMap<Int, Section<T>> = HashMap()

    abstract fun createViewHolderForSectionItem(parent: ViewGroup?, viewType: Int): VHI

    abstract fun bindViewHolderForSectionItem(holder: VHI, item: T?, sectionId: Int)

    abstract fun createViewHolderForSectionHeader(parent: ViewGroup?, viewType: Int): VHS

    abstract fun bindViewHolderForSectionHeader(
        holder: VHS, header: Section.SectionHeader?,
        isFirst: Boolean
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == Section.SECTION_ITEM) {
            createViewHolderForSectionItem(parent, viewType)
        } else createViewHolderForSectionHeader(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val section = getSectionByPosition(position)

        if (isHeaderType(section)) {
            bindViewHolderForSectionHeader(holder as VHS, section?.header, position == 0)
        } else {
            bindViewHolderForSectionItem(
                holder as VHI, getItem(position),
                getSectionIdByPosition(position)
            )
        }
    }

    override fun getItemCount(): Int {
        if (mSections.isEmpty()) {
            return 0
        }
        var itemCount = 0
        for (section in mSections.values) {
            itemCount += section.numOfItems() + 1
        }
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {
        val section = getSectionByPosition(position)
        section?.let {
            return if (isHeaderType(it)) {
                it.header.headerType
            } else {
                Section.SECTION_ITEM
            }
        } ?: return Section.SECTION_ITEM

    }

    private fun isHeaderType(position: Int): Boolean {
        val section = getSectionByPosition(position) as Section<T>
        return isHeaderType(section)
    }

    private fun isHeaderType(section: Section<T>?): Boolean {
        return section != null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addSection(groupId: Int, sectioName: String, list: List<T>) {
        val sectionList = ListSection(groupId, sectioName, list)
        // set the position of this new section
        var calculatedPosition = 0
        for (section in mSections.values) {
            calculatedPosition += section.numOfItems() + 1
        }
        sectionList.position = calculatedPosition
        mSections[groupId] = sectionList
    }

    fun removeSection(groupId: Int) {
        mSections.remove(groupId)
    }

    fun getSections(): List<Section<T>> {
        return ArrayList(mSections.values)
    }

    private fun getSectionByPosition(position: Int): Section<T>? {
        for (section in mSections.values) {
            if (section.position == position) {
                return section
            }
        }
        return null
    }

    protected fun getSectionById(id: Int): Section<T>? {
        return mSections[id]
    }

    private fun getItem(position: Int): T? {
        for (section in mSections.values) {
            if (positionIsInsideSection(section, position)) {
                return section.getItem(
                    position - (section.position + 1)
                )
            }
        }
        return null
    }

    private fun getSectionIdByPosition(position: Int): Int {
        var sectionId = 0
        for (section in mSections.values) {
            if (positionIsInsideSection(section, position)) {
                sectionId = section.id
            }
        }
        return sectionId
    }

    private fun positionIsInsideSection(section: Section<T>, position: Int): Boolean {
        return position > section.position && position <= (section.position
                + section.numOfItems())
    }

    open fun isLastInSection(position: Int): Boolean {
        for (section in mSections.values) {
            if (position - section.position == -1) {
                return true
            }
        }
        return false
    }

    protected fun clear() {
        mSections.clear()
    }
}